package net.dpco.pdf.service.impl;

import com.itextpdf.text.DocumentException;
import net.dpco.pdf.dto.Model;
import net.dpco.pdf.generator.PdfGenerator;
import net.dpco.pdf.service.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class PdfServiceImpl implements PdfService {

  private PdfGenerator pdfGenerator;

  @Autowired
  public PdfServiceImpl(PdfGenerator pdfGenerator) {
    this.pdfGenerator = pdfGenerator;
  }

  @Override
  public byte[] makeBriefPdf(Model model, HttpServletRequest request, HttpServletResponse response)
          throws Exception {
    try{
    return pdfGenerator.generateBriefPdf(model, request, response);
      }catch (Exception ex){
      throw new Exception(ex.getMessage());
    }
  }

  @Override
  public byte[] makepromisePdf(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
    try{
      return pdfGenerator.generatePromisePdf(model, request, response);
    }catch (Exception ex){
      throw new Exception(ex.getMessage());
    }
  }
}
