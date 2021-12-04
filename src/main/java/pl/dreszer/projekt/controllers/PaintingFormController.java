package pl.dreszer.projekt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pl.dreszer.projekt.controllers.filters.PaintingFilter;
import pl.dreszer.projekt.models.Genre;
import pl.dreszer.projekt.models.Painting;
import pl.dreszer.projekt.models.Technique;
import pl.dreszer.projekt.repositories.PaintingsRepository;
import pl.dreszer.projekt.validators.PaintingValidator;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.*;

@Controller
public class PaintingFormController
{
	@Autowired
	private PaintingsRepository paintingsRepository;
	@InitBinder("painting")
	public void initBinder(WebDataBinder binder)
	{
		binder.addValidators(new PaintingValidator());
	}
	@RequestMapping(value="paintingForm.html", params = {"edit"})
	protected String showForm(@RequestParam(value="paintingId", required = false, defaultValue="-1") int paintingId,
							  Model model, @RequestParam(value = "edit") boolean edit)
	{
		if (edit && paintingId > 0)
		{
			model.addAttribute("painting", paintingsRepository.getById(paintingId));
		}
		else
		{
			model.addAttribute("painting", new Painting());
		}
		return "paintingForm";
	}

	@PostMapping(value="paintingForm.html", params = {"edit"})
	protected String processForm(Model model, @ModelAttribute("painting") @Valid Painting painting, BindingResult result)
	{
		System.out.println(result.getAllErrors());
		if (result.hasErrors())
			return "paintingForm";
		//if (painting.getId() != 0)
			paintingsRepository.save(painting);
		model.addAttribute("painting", painting);
		return "successPaintingForm";
	}

	@PostMapping(value="processSearch.html", params="name")
	protected String searchForm(PaintingFilter paintingFilter, Model model)
	{
		//System.out.println(paintingFilter.getPhrase());
		model.addAttribute("paintings", paintingsRepository.findByName("Słoneczniki"));
		//System.out.println(paintingsRepository.findByName(paintingFilter.getPhrase()).getName());
		return "searchResult";
	}

	@RequestMapping(value="searchForm.html")
	protected String showSearchForm(PaintingFilter paintingFilter, Model model)
	{
		model.addAttribute("paintingFilter", paintingFilter);
		return "searchForm";
	}

	@RequestMapping(value="byName.html")
	protected String showSeaarchForm(PaintingFilter paintingFilter, Model model)
	{
		model.addAttribute("paintingFilter", paintingFilter);
		return "searchForm";
	}

	@ModelAttribute("techniques")
	public List<Technique> loadTechniquesList()
	{
		List<Technique> techniquesList = new ArrayList<>();
		techniquesList.add(new Technique(1,"Olej na płótnie"));
		techniquesList.add(new Technique(2,"Olej na miedzi"));
		return techniquesList;
	}

	@ModelAttribute("genres")
	public List<Genre> loadGenresSet()
	{
		List<Genre> techniquesList = new ArrayList<>();
		techniquesList.add(new Genre(1,"Sielanka"));
		techniquesList.add(new Genre(2,"Martwa natura"));
		techniquesList.add(new Genre(3,"Rysunek"));
		return techniquesList;
	}

}
