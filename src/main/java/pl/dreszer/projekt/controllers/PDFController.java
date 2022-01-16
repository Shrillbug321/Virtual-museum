package pl.dreszer.projekt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.dreszer.projekt.services.PDFService;

import java.io.IOException;

@Controller
public class PDFController {

	@Autowired
	PDFService pdfService;

	public PDFController() throws IOException {
	}

	@GetMapping("paintings/details/createPdf.html")
	public ModelAndView createPdfFromPainting(@RequestParam int paintingId, Model model) throws IOException
	{
		pdfService.createPdfFromPainting(paintingId);
		model.addAttribute("attribute", "redirectWithRedirectPrefix");
		return new ModelAndView("redirect:/pdf/success.html");
	}

	@GetMapping("paintings/list/createPdf.html")
	public ModelAndView createPdfFromList(Model model) throws IOException
	{
		pdfService.createPdfFromList();
		model.addAttribute("attribute", "redirectWithRedirectPrefix");
		return new ModelAndView("redirect:/pdf/success.html");
	}

	@GetMapping("pdf/success.html")
	public String redirect()
	{
		return "/pdf/success";
	}

}
