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
  public byte[] makePdf(Model model, HttpServletRequest request, HttpServletResponse response)
      throws DocumentException {
    return pdfGenerator.generate(model, request, response);
  }
}
