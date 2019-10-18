package net.dpco.pdf.controller;

import com.itextpdf.text.DocumentException;
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
public class MemberController {

  private PdfService pdfService;

  @Autowired
  public MemberController(PdfService pdfService) {
    this.pdfService = pdfService;
  }

  @RequestMapping(path = "/get", method = RequestMethod.POST)
  public ResponseEntity<byte[]> get(
      @RequestBody Model model, HttpServletRequest request, HttpServletResponse response)
          throws Exception {
      try{
    return new ResponseEntity<byte[]>(
        pdfService.makePdf(model, request, response), HttpStatus.ACCEPTED);
    }catch (Exception ex){
          throw new Exception(ex.getMessage());
      }
  }
}
