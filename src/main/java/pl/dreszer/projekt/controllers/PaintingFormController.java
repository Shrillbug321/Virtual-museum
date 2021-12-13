package pl.dreszer.projekt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pl.dreszer.projekt.controllers.filters.PaintingFilter;
import pl.dreszer.projekt.controllers.filters.PaintingsSpecifications;
import pl.dreszer.projekt.models.Genre;
import pl.dreszer.projekt.models.Painting;
import pl.dreszer.projekt.models.Technique;
import pl.dreszer.projekt.repositories.PaintingsRepository;
import pl.dreszer.projekt.validators.PaintingValidator;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

@Controller
public class PaintingFormController
{
	@Autowired
	private PaintingsRepository paintingsRepository;
	//@Autowired
	private PaintingsSpecifications paintingsSpecifications;
	private Specification<Painting> specification;
	private Stream<Painting> paintingStream;
	@InitBinder("painting")
	public void initBinder(WebDataBinder binder)
	{
		binder.addValidators(new PaintingValidator());
	}

	@Secured("ROLE_ADMIN")
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

	@Secured("ROLE_ADMIN")
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
	@PostMapping(value="processSearch.html")
	@Transactional(readOnly = true)
	public String searchForm(PaintingFilter paintingFilter, Model model)
	{
		System.out.println(paintingFilter.getPhrase());
		List<Painting> foundPaintings = null;
		LocalDate minDate, maxDate;
		switch (paintingFilter.getWhere())
		{
			case "name":
				//foundPaintings = paintingsRepository.findAll(PaintingsSpecifications.findByPhrase(paintingFilter.getPhrase()));
				foundPaintings = paintingsRepository.findByNameContainingIgnoreCase(paintingFilter.getPhrase());
				break;
			case "technique":
				foundPaintings = paintingsRepository.findByTechnique(paintingFilter.getPhrase());
				break;
			case "author":
				foundPaintings = paintingsRepository.findByAuthor(paintingFilter.getPhrase());
				break;
			case "paintingValue":
				foundPaintings = paintingsRepository.findByValue(paintingFilter);
				break;
			case "paintedDate":
				minDate = LocalDate.parse(paintingFilter.getMinDate());
				maxDate = LocalDate.parse(paintingFilter.getMaxDate());
				foundPaintings = paintingsRepository.findAll(PaintingsSpecifications.findByPaintedDate(minDate, maxDate));
				break;
			case "addDate":
				minDate = LocalDate.parse(paintingFilter.getMinDate());
				maxDate = LocalDate.parse(paintingFilter.getMaxDate());
				try (Stream<Painting> paintingStream = paintingsRepository.findByAddDate(minDate,maxDate))
				{
					List<Painting> finalFoundPaintings = new ArrayList<>();
					paintingStream.forEach(painting -> finalFoundPaintings.add(painting));
					foundPaintings = finalFoundPaintings;
				}
				//foundPaintings = paintingsRepository.findAll(PaintingsSpecifications.findByPaintedDate(minDate, maxDate));
				break;
		}

		model.addAttribute("paintings", foundPaintings);
		return "searchResult";
	}

	@RequestMapping(value="searchForm.html")
	protected String showSearchForm(PaintingFilter paintingFilter, Model model)
	{
		model.addAttribute("filter", paintingFilter);
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
