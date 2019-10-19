package net.dpco.pdf.controller;

import net.dpco.pdf.dto.Model;
import net.dpco.pdf.service.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class PdfController {

  private PdfService pdfService;

  @Autowired
  public PdfController(PdfService pdfService) {
    this.pdfService = pdfService;
  }

    /**
     * the breif document end point
     * @param model
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
  @RequestMapping(path = "/breif_document/pdf", method = RequestMethod.POST)
  public ResponseEntity<byte[]> get(
      @RequestBody Model model, HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      return new ResponseEntity<byte[]>(
          pdfService.makeBriefPdf(model, request, response), HttpStatus.ACCEPTED);
    } catch (Exception ex) {
      throw new Exception(ex.getMessage());
    }
  }

    /**
     * the promise document end point
     * @param model
     * @param request
     * @param response
     * @return
     * @throws Exception
     */

  @RequestMapping(path = "/promise_document/pdf", method = RequestMethod.POST)
  public ResponseEntity<byte[]> promisePdf(
      @RequestBody Model model, HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      return new ResponseEntity<byte[]>(
          pdfService.makepromisePdf(model, request, response), HttpStatus.ACCEPTED);
    } catch (Exception ex) {
      throw new Exception(ex.getMessage());
    }
  }
}
