package pl.dreszer.projekt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.dreszer.projekt.services.PDFService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class PDFController {

    @Autowired
    PDFService pdfService;

    public PDFController() {
    }

    @GetMapping("paintings/details/createPdf.html")
    public ModelAndView createPdfFromPainting(@RequestParam int paintingId, ModelMap model) throws IOException {
        String name = pdfService.createPdfFromPainting(paintingId);
        model.addAttribute("name", name);
        return new ModelAndView("redirect:/pdf/success.html", model);
    }

    @GetMapping("paintings/list/createPdf.html")
    public ModelAndView createPdfFromList(ModelMap model) throws IOException {
        pdfService.createPdfFromList();
        model.addAttribute("name", "list");
        return new ModelAndView("redirect:/pdf/success.html", model);
    }

    @GetMapping("pdf/success.html")
    public String redirect() {
        return "/pdf/success";
    }

    @GetMapping("pdf/open")
    public void openPdfFile(@RequestParam String name, HttpServletResponse response) throws IOException {
        pdfService.openPdfFile(name, response);
    }
}
