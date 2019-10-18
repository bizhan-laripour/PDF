package net.dpco.pdf.generator;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import net.dpco.pdf.dto.Model;
import net.dpco.pdf.entity.Applicant;
import net.dpco.pdf.entity.Dependant;
import net.dpco.pdf.entity.ResidenceRecord;
import net.dpco.pdf.entity.Spouse;
import net.dpco.pdf.enums.ResidenceCase;
import net.dpco.pdf.service.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
@PropertySource(value = "classpath:pdf.properties", encoding = "UTF-8")
public class PdfGenerator {

  private Environment environment;

  private ApplicantService applicantService;

  @Autowired
  public PdfGenerator(Environment environment, ApplicantService applicantService) {
    this.applicantService = applicantService;
    this.environment = environment;
  }

  private static Font normal =
      FontFactory.getFont("fonts/B Nazanin.ttf", BaseFont.IDENTITY_H, 10, Font.NORMAL);
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
      addLine(document, 1.5f, 100 , BaseColor.BLACK);
      createTable(document, 1, applicantsHeader(), bold, Element.ALIGN_LEFT, 0, 10);
      List<String> applicants = applicantsInfo(model);
      createTable(document, 6, applicants, normal, Element.ALIGN_LEFT, 0, 20);
      addLine(document, 1f, 80 , BaseColor.BLACK);
      createTable(document, 1, spousesHeader(), bold, Element.ALIGN_LEFT, 0, 10);
      List<String> spouses = spouses(model);
      createTable(document, 6, spouses, normal, Element.ALIGN_LEFT, 0, 20);
      addLine(document, 1f, 80 , BaseColor.BLACK);
      createTable(document, 1, dependantsHeader(), bold, Element.ALIGN_LEFT, 0, 10);

      createTable(document, 6, dependants(model), normal, Element.ALIGN_LEFT, 0, 20);
      addLine(document, 1f, 80 , BaseColor.BLACK);
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

  private void addLine(Document document, float lineWith, int percentage , BaseColor color) throws Exception {
    try {
      document.add(
          new LineSeparator(lineWith, percentage, color, Element.ALIGN_CENTER, -5));
    } catch (Exception ex) {
      throw new Exception(ex);
    }
  }

  private List<String> applicantsInfo(Model model) throws Exception {
    try {
      List<String> list = new ArrayList<>();
      Applicant applicant = findByModel(model);
      list.add(environment.getProperty("name"));
      list.add(applicant.getFirstName());
      list.add(environment.getProperty("last_name"));
      list.add(applicant.getLastName());
      list.add(environment.getProperty("national_code"));
      list.add(applicant.getNationalCode());
      list.add(environment.getProperty("fatherName"));
      list.add(applicant.getFatherName());
      list.add(environment.getProperty("birth_date"));
      list.add(applicant.getBirthDate().toString());
      list.add(environment.getProperty("birth_place"));
      list.add(applicant.getBirthPlace());
      list.add(environment.getProperty("job"));
      list.add(applicant.getJob().getTitle());
      list.add(environment.getProperty("mobile_no"));
      list.add(applicant.getMobileNo().toString());
      list.add(environment.getProperty("birth_crtificate_no"));
      list.add(applicant.getBirthCertificateNo());
      StringBuilder builder = new StringBuilder();
      StringBuilder currentCity = new StringBuilder();
      for (ResidenceRecord residenceRecord : applicant.getResidenceRecords()) {
        if (residenceRecord.getResidenceCase().equals(ResidenceCase.CURRENT)) {
          currentCity.append(
              residenceRecord.getAddress().getCenter().getProvince()
                  + "/"
                  + residenceRecord.getAddress().getCenter().getCity());
        }
        builder.append(residenceRecord.getAddress().getAddress() + ";");
      }
      builder.delete(builder.length() - 1, builder.length());
      list.add(environment.getProperty("existance_place"));
      list.add(currentCity.toString());
      list.add(environment.getProperty("dependants_number"));
      list.add(String.valueOf(applicant.getDependants().size()));
      list.add(environment.getProperty("address"));
      list.add(builder.toString());
      list.add(" ");
      list.add(" ");

      return list;

    } catch (Exception ex) {
      throw new Exception(ex.getMessage());
    }
  }

  private List<String> spouses(Model model) throws Exception {
    try {
      List<String> list = new ArrayList<>();
      Applicant applicant = findByModel(model);
      Set<Spouse> spouses = applicant.getSpouses();
      for (Spouse spouse : spouses) {
        list.add(environment.getProperty("name"));
        list.add(spouse.getFirstName());
        list.add(environment.getProperty("last_name"));
        list.add(spouse.getLastName());
        list.add(environment.getProperty("national_code"));
        list.add(spouse.getNationalCode());
        list.add(environment.getProperty("fatherName"));
        list.add(spouse.getFatherName());
        list.add(environment.getProperty("birth_crtificate_no"));
        list.add(spouse.getBirthCertificateNo());
        list.add(environment.getProperty("mobile_no"));
        list.add(spouse.getMobileNo());
      }

      return list;
    } catch (Exception ex) {
      throw new Exception(ex.getMessage());
    }
  }

  private List<String> dependants(Model model) throws Exception {
    try {
      Applicant applicant = findByModel(model);
      Set<Dependant> dependants = applicant.getDependants();
      List<String> list = new ArrayList<>();
      for (Dependant dep : dependants) {
        list.add(environment.getProperty("name"));
        list.add(dep.getFirstName());
        list.add(environment.getProperty("last_name"));
        list.add(dep.getLastName());
        list.add(environment.getProperty("national_code"));
        list.add(dep.getNationalCode());
        list.add(environment.getProperty("birth_date"));
        list.add(dep.getBirthYear());
        list.add(environment.getProperty("gender"));
        list.add(dep.getGender().toString());
        list.add(" ");
        list.add(" ");
      }
      return list;
    } catch (Exception ex) {
      throw new Exception(ex.getMessage());
    }
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

  private Applicant findByModel(Model model) throws Exception {
    try {

      return applicantService.findByNationalCodeAndTrackingCode(
          model.getNationalCode(), model.getTrackingCode());

    } catch (Exception ex) {
      throw new Exception(ex.getMessage());
    }
  }

  private List<String> dependantsHeader() {
    List<String> dependantsHeader = new ArrayList<>();
    dependantsHeader.add(environment.getProperty("dependants_header"));
    return dependantsHeader;
  }
}
