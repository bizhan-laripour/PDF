package net.dpco.pdf.service;

import com.itextpdf.text.DocumentException;
import net.dpco.pdf.dto.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface PdfService {

  byte[] makeBriefPdf(Model model, HttpServletRequest request, HttpServletResponse response)
          throws Exception;

  byte[] makepromisePdf(Model model, HttpServletRequest request, HttpServletResponse response)
          throws Exception;
}
