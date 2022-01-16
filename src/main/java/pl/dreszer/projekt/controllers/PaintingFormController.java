package pl.dreszer.projekt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.dreszer.projekt.controllers.filters.PaintingFilter;
import pl.dreszer.projekt.models.Genre;
import pl.dreszer.projekt.models.Museum;
import pl.dreszer.projekt.models.Painting;
import pl.dreszer.projekt.models.Technique;
import pl.dreszer.projekt.services.PaintingFormService;

import javax.validation.Valid;
import java.util.*;
@PropertySource("classpath:config.properties")
@RequestMapping("/paintings")
@Controller
public class PaintingFormController
{
	@Autowired
	private PaintingFormService paintingFormService;

	@Secured("ROLE_ADMIN")
	@RequestMapping(value="form.html", params = {"edit"})
	protected String showForm(@RequestParam(value="paintingId", required = false, defaultValue="-1") int paintingId,
							  Model model, @RequestParam(value = "edit") boolean edit)
	{
		paintingFormService.showForm(paintingId, model, edit);
		return "paintings/form";
	}

	@Secured("ROLE_ADMIN")
	@PostMapping(value="form.html", params = {"edit"})
	protected String processForm(Model model, @Valid @ModelAttribute("painting") Painting painting,
								 BindingResult result, @RequestParam(value = "edit") boolean edit, MultipartFile multipartFile)
	{
		String strResult = paintingFormService.processForm(model, painting, result, edit, multipartFile);
		switch(strResult)
		{
			case "success" : return "paintings/success";
			default: return "paintings/form";
		}
	}

	@ModelAttribute("techniques")
	public List<Technique> loadTechniquesList()
	{
		return paintingFormService.loadTechniquesList();
	}

	@ModelAttribute("genres")
	public List<Genre> loadGenresSet()
	{
		return paintingFormService.loadGenresSet();
	}

	@ModelAttribute("museums")
	public List<Museum> loadMuseumsList()
	{
		return paintingFormService.loadMuseumsList();
	}
}
