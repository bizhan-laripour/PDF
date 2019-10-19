package net.dpco.pdf.generator;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.draw.LineSeparator;
import net.dpco.pdf.dto.Model;
import net.dpco.pdf.entity.Applicant;
import net.dpco.pdf.entity.Dependant;
import net.dpco.pdf.entity.ResidenceRecord;
import net.dpco.pdf.entity.Spouse;
import net.dpco.pdf.enums.Gender;
import net.dpco.pdf.enums.MaritalStatus;
import net.dpco.pdf.enums.PhysicalStatus;
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
  private static Font promiseFont =
          FontFactory.getFont("fonts/B Nazanin.ttf", BaseFont.IDENTITY_H, 12, Font.BOLD);

  public byte[] generateBriefPdf(
      Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
    Document document = new Document();
    response.setContentType("application/pdf");
    final ByteArrayOutputStream baos = new ByteArrayOutputStream();
    try {
      PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
      document.open();
      border(writer, document);
      createTable(document, 3, header(), bold, Element.ALIGN_CENTER, 0, 0);
      addLine(document, 1.5f, 100, BaseColor.BLACK);
      createTable(document, 1, applicantsHeader(), bold, Element.ALIGN_LEFT, 0, 10);
      createTable(document, 6, applicantsInfo(model), normal, Element.ALIGN_LEFT, 0, 20);
      addLine(document, 1f, 80, BaseColor.BLACK);
      createTable(document, 1, spousesHeader(), bold, Element.ALIGN_LEFT, 0, 10);
      createTable(document, 6, spouses(model), normal, Element.ALIGN_LEFT, 0, 20);
      addLine(document, 1f, 80, BaseColor.BLACK);
      createTable(document, 1, dependantsHeader(), bold, Element.ALIGN_LEFT, 0, 10);
      createTable(document, 6, dependants(model), normal, Element.ALIGN_LEFT, 0, 20);
      addLine(document, 1f, 80, BaseColor.BLACK);
      createTable(document, 1, addTextHeader(), bold, Element.ALIGN_LEFT, 0, 10);
      createTable(document, 1, textList(), normal, Element.ALIGN_LEFT, 0, 20);
      addLine(document, 1f, 80, BaseColor.BLACK);
      createTable(document, 3, signList(), promiseFont, Element.ALIGN_CENTER, 0, 20);
      border(writer, document);
      document.close();
      writer.close();
      OutputStream os = response.getOutputStream();
      baos.writeTo(os);
      os.flush();
      os.close();
    } catch (Exception e) {
      throw new Exception(e.getMessage());
    }
    return baos.toByteArray();
  }

  public byte[] generatePromisePdf(
      Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
    Document document = new Document();
    response.setContentType("application/pdf");
    final ByteArrayOutputStream baos = new ByteArrayOutputStream();
    try {
      PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
      document.open();
      border(writer, document);
      createTable(document, 3, promiseHeader(), bold, Element.ALIGN_CENTER, 0, 0);
      addLine(document, 1.5f, 100, BaseColor.BLACK);
      createTable(document, 6, promiseList(model), promiseFont, Element.ALIGN_LEFT, 0, 20);
      createTable(document, 1, promiseText(), promiseFont, Element.ALIGN_LEFT, 0, 20);
      createTable(document, 3, promiseSign(), promiseFont, Element.ALIGN_CENTER, 0, 20);

      document.close();
      writer.close();
      OutputStream os = response.getOutputStream();
      baos.writeTo(os);
      os.flush();
      os.close();
    } catch (Exception e) {
      throw new Exception(e.getMessage());
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

  /**
   * adding the line wherever you want you can choose the color and
   *
   * @param document
   * @param lineWith
   * @param percentage
   * @param color
   * @throws Exception
   */
  private void addLine(Document document, float lineWith, int percentage, BaseColor color)
      throws Exception {
    try {
      document.add(new LineSeparator(lineWith, percentage, color, Element.ALIGN_CENTER, -5));
    } catch (Exception ex) {
      throw new Exception(ex);
    }
  }

  /**
   * make list of applicants info to set in the tables
   *
   * @param model
   * @return
   * @throws Exception
   */
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
      list.add(environment.getProperty("birth_certificate_no"));
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
      list.add(environment.getProperty("marital_status"));
      if (applicant.getMaritalStatus().equals(MaritalStatus.SINGLE)) {
        list.add(environment.getProperty("single"));
      } else {
        list.add(environment.getProperty("married"));
      }
      list.add(environment.getProperty("gender"));
      if (applicant.getGender().equals(Gender.MALE)) {
        list.add(environment.getProperty("male"));
      } else {
        list.add(environment.getProperty("female"));
      }
      list.add(environment.getProperty("physical_status"));
      if (applicant.getPhysicalStatus().equals(PhysicalStatus.HEALTHY)) {
        list.add(environment.getProperty("healthy"));
      } else {
        list.add(environment.getProperty("disabled"));
      }
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
        list.add(environment.getProperty("birth_certificate_no"));
        list.add(spouse.getBirthCertificateNo());
        list.add(environment.getProperty("mobile_no"));
        list.add(spouse.getMobileNo());
        list.add(environment.getProperty("birth_place"));
        list.add(spouse.getBirthPlace());
        list.add(environment.getProperty("birth_date"));
        list.add(spouse.getBirthDate().toString());
        list.add(" ");
        list.add(" ");
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

  private void border(PdfWriter writer, Document document) {
    PdfContentByte canvas = writer.getDirectContent();
    Rectangle rect = new Rectangle(document.getPageSize());
    rect.setBorder(Rectangle.BOX); // left, right, top, bottom border
    rect.setBorderWidth(5); // a width of 5 user units
    rect.setBorderColor(BaseColor.BLACK); // a red border
    rect.setUseVariableBorders(true); // the full width will be visible
    canvas.rectangle(rect);
  }

  private List<String> header() {
    List<String> header = new ArrayList<>();
    header.add("");
    header.add(environment.getProperty("brief"));
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

  private List<String> addTextHeader() {
    List<String> first = new ArrayList<>();
    first.add(environment.getProperty("promises"));
    return first;
  }

  private List<String> textList() {
    List<String> textList = new ArrayList<>();
    textList.add(environment.getProperty("first_line_text"));
    textList.add(environment.getProperty("second_line"));
    textList.add(environment.getProperty("third_line"));
    textList.add(environment.getProperty("forth_line"));
    textList.add(environment.getProperty("fifth_line"));
    textList.add(environment.getProperty("sixth_line"));
    textList.add(environment.getProperty("seventh_line"));
    textList.add(environment.getProperty("eighth_line"));
    textList.add(environment.getProperty("nine_line"));
    textList.add(environment.getProperty("ten_line"));
    textList.add(environment.getProperty("eleven_line"));
    textList.add(environment.getProperty("twelve_line"));
    textList.add(environment.getProperty("thirteen_line"));
    textList.add(environment.getProperty("fortheen_line"));
    textList.add(environment.getProperty("fiftheen_line"));
    textList.add(environment.getProperty("sixteen_line"));
    textList.add(environment.getProperty("seventeen_line"));

    return textList;
  }

  private List<String> signList() {
    List<String> signList = new ArrayList<>();
    signList.add(environment.getProperty("manager_sign"));
    signList.add(environment.getProperty("expert_sign"));
    signList.add(environment.getProperty("applicant_sign"));
    return signList;
  }

  private List<String> promiseList(Model model) throws Exception {
    try {

      List<String> promiseList = new ArrayList<>();
      Applicant applicant = findByModel(model);
      promiseList.add(environment.getProperty("promise_first"));
      promiseList.add(applicant.getFirstName() + " " + applicant.getLastName());
      promiseList.add(environment.getProperty("promise_second"));
      promiseList.add(applicant.getBirthPlace());
      promiseList.add(environment.getProperty("promise_third"));
      promiseList.add(applicant.getBirthCertificateNo());
      promiseList.add(environment.getProperty("promise_forth"));
      promiseList.add(applicant.getNationalCode());
      promiseList.add(environment.getProperty("promise_fifth"));
      StringBuilder currentCity = new StringBuilder();
      for (ResidenceRecord residenceRecord : applicant.getResidenceRecords()) {
        if (residenceRecord.getResidenceCase().equals(ResidenceCase.CURRENT)) {
          currentCity.append(
              residenceRecord.getAddress().getCenter().getProvince()
                  + "/"
                  + residenceRecord.getAddress().getCenter().getCity());
        }
      }
      promiseList.add(currentCity.toString());
      promiseList.add(environment.getProperty("promise_sixth"));
      promiseList.add(applicant.getIssuedPlace());
      promiseList.add(environment.getProperty("promise_seventh"));

      return promiseList;
    } catch (Exception ex) {
      throw new Exception(ex.getMessage());
    }
  }

  private List<String> promiseText(){
    List<String> promiseText = new ArrayList<>();
    promiseText.add(environment.getProperty("promise_seventh"));
    promiseText.add(environment.getProperty("promise_first_line"));
    promiseText.add(environment.getProperty("promise_second_line"));
    promiseText.add(environment.getProperty("promise_third_line"));
    promiseText.add(environment.getProperty("promise_forth_line"));
    return promiseText;
  }

  private List<String> promiseSign(){
    List<String> list = new ArrayList<>();
    list.add(" ");
    list.add(" ");
    list.add(environment.getProperty("applicant_sign"));
    return list;
  }
  private List<String> promiseHeader(){
    List<String> promiseHeader = new ArrayList<>();
    promiseHeader.add(" ");
    promiseHeader.add(environment.getProperty("header"));
    promiseHeader.add(" ");
    return promiseHeader;
  }
}
