package pl.dreszer.projekt.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pl.dreszer.projekt.models.Genre;
import pl.dreszer.projekt.models.Painting;
import pl.dreszer.projekt.models.PaintingsList;
import pl.dreszer.projekt.models.Technique;
import pl.dreszer.projekt.validators.PaintingValidator;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.*;

@Controller
public class PaintingFormController
{
	@InitBinder("painting")
	public void initBinder(WebDataBinder binder)
	{
		binder.addValidators(new PaintingValidator());
	}
	@RequestMapping(value="paintingForm.html", params = {"edit"})
	protected String showForm(@RequestParam(value="paintingId", required = false, defaultValue="-1") int paintingId,
							  Optional<Painting> painting, Model model, @RequestParam(value = "edit") boolean edit)
	{
		if (edit)
		{
			model.addAttribute("painting", painting.orElse(new Painting()));
			/*if (paintingId == -1)
			{
				Painting painting1 = new Painting();
				painting1.setId(1);
				painting1.setName("Słoneczniki");
				painting1.setAuthor("Van Gogh");
				painting1.setAddDate(LocalDate.of(2021, 10, 20));
				painting1.setPaintedDate(LocalDate.of(1872, 5, 15));
				painting1.setValue(1000000.99f);
				painting1.setExhibited(false);
				painting1.setTechnique(new Technique(1,"Olej na płótnie"));
				Set<Genre> a = new HashSet<>();
				a.add(new Genre(1,"lll"));
				//painting1.setGenres(a);
				model.addAttribute("painting", painting1);
			}*/
			//else
			{
				//model.addAttribute("painting", PaintingsList.paintingsList.get(paintingId));
			}
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
		if (result.hasErrors())
			return "paintingForm";
		if (painting.getId() != 0)
			painting.setId(2);
		model.addAttribute("painting", painting);
		return "successPaintingForm";
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
