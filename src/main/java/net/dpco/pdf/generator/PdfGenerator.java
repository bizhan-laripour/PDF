package net.dpco.pdf.generator;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.draw.LineSeparator;
import net.dpco.pdf.dto.Model;
import net.dpco.pdf.entity.Member;
import net.dpco.pdf.entity.Spouse;
import net.dpco.pdf.service.MemberService;
import net.dpco.pdf.service.SpousesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;
import java.util.List;

@Component
@PropertySource(value = "classpath:pdf.properties", encoding = "UTF-8")
public class PdfGenerator {

  @Autowired private Environment environment;

  @Autowired private MemberService memberService;

  @Autowired private SpousesService spousesService;

  private static Font normal =
      FontFactory.getFont("fonts/B Nazanin.ttf", BaseFont.IDENTITY_H, 12, Font.NORMAL);
  private static Font bold =
      FontFactory.getFont("fonts/B Nazanin.ttf", BaseFont.IDENTITY_H, 14, Font.BOLD);

  public byte[] generate(Model model, HttpServletRequest request, HttpServletResponse response) {

    Document document = new Document();

    response.setContentType("application/pdf");
    final ByteArrayOutputStream baos = new ByteArrayOutputStream();
    try {
      PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
      document.open();
      document.add(documentBorder());
      createTable(document, 3, header(), bold, Element.ALIGN_CENTER, 0, 0);
      addLine(document, 1f);
      createTable(document, 1, applicantsHeader(), bold, Element.ALIGN_LEFT, 0, 10);
      List<String> applicants = applicantsInfo(model);
      createTable(document, 4, applicants, normal, Element.ALIGN_LEFT, 0, 20);
      createTable(document, 1, spousesHeader(), bold, Element.ALIGN_LEFT, 0, 10);
      List<String> spouses = spouses(model);
      createTable(document, 4, spouses, normal, Element.ALIGN_LEFT, 0, 20);
      document.close();
      writer.close();
      OutputStream os = response.getOutputStream();
      baos.writeTo(os);
      os.flush();
      os.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return baos.toByteArray();
  }

  private void createTable(
      Document document,
      int size,
      List<String> list,
      Font font,
      int alignment,
      int border,
      int paddingTop)
      throws Exception {

    try {
      PdfPTable pdfPTable = new PdfPTable(size);
      pdfPTable.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
      List<PdfPCell> pdfPCells = new ArrayList<>();
      for (String item : list) {
        PdfPCell pdfPCell = new PdfPCell(new Phrase(item, font));
        pdfPCell.setHorizontalAlignment(alignment);
        pdfPCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        pdfPCell.setBorder(border);
        pdfPCell.setPaddingTop(paddingTop);
        pdfPCells.add(pdfPCell);
      }
      for (PdfPCell pdfPCell : pdfPCells) {
        pdfPTable.addCell(pdfPCell);
      }
      document.add(pdfPTable);
    } catch (Exception ex) {
      throw new Exception("ex");
    }
  }

  private void addLine(Document document, float lineWith) throws Exception {
    try {
      document.add(new LineSeparator(lineWith, 100, null, 0, -5));
    } catch (Exception ex) {
      throw new Exception(ex);
    }
  }

  private List<String> applicantsInfo(Model model) {
    List<String> list = new ArrayList<>();
    Member member =
        memberService.findByNationalCodeAndTrackingCode(
            model.getNationalCode(), model.getTrackingCode());

    list.add(environment.getProperty("name"));
    list.add(member.getName());
    list.add(environment.getProperty("last_name"));
    list.add(member.getLastName());
    list.add(environment.getProperty("national_code"));
    list.add(member.getNationalCode());
    list.add(environment.getProperty("tracking_code"));
    list.add(member.getTrackingCode());
    return list;
  }

  private List<String> spouses(Model model) {
    List<String> list = new ArrayList<>();
    Member member =
        memberService.findByNationalCodeAndTrackingCode(
            model.getNationalCode(), model.getTrackingCode());
    List<Spouse> spouses = spousesService.findByMember(member);

    for (Spouse spouse : spouses) {
      list.add(environment.getProperty("name"));
      list.add(spouse.getName());
      list.add(environment.getProperty("last_name"));
      list.add(spouse.getLastName());
      list.add(environment.getProperty("national_code"));
      list.add(spouse.getNationalCode());
      list.add(environment.getProperty("fatherName"));
      list.add(spouse.getFatherName());
    }
    return list;
  }

  private Rectangle documentBorder() {
    Rectangle rect = new Rectangle(577, 825, 18, 15); // you can resize rectangle
    rect.enableBorderSide(1);
    rect.enableBorderSide(2);
    rect.enableBorderSide(4);
    rect.enableBorderSide(8);
    rect.setBorderColor(BaseColor.BLACK);
    rect.setBorderWidth(1);
    return rect;
  }

  private List<String> header() {
    List<String> header = new ArrayList<>();
    header.add("");
    header.add(environment.getProperty("header"));
    header.add("");
    return header;
  }

  private List<String> applicantsHeader() {
    List<String> applicantsHeader = new ArrayList<>();
    applicantsHeader.add(environment.getProperty("applicants_header"));
    return applicantsHeader;
  }

  private List<String> spousesHeader() {
    List<String> spousesHeader = new ArrayList<>();
    spousesHeader.add(environment.getProperty("spouses_header"));
    return spousesHeader;
  }
}
