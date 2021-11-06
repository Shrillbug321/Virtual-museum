package pl.dreszer.projekt.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pl.dreszer.projekt.models.Painting;
import pl.dreszer.projekt.models.Technique;
import pl.dreszer.projekt.validators.PaintingValidator;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PaintingFormController
{
	@InitBinder("painting")
	public void initBinder(WebDataBinder binder)
	{
		binder.addValidators(new PaintingValidator());
	}
	@RequestMapping(value="paintingForm.html", params = {"edit"})
	protected String showForm(@RequestParam(value="paintingId", required = false, defaultValue="-1") int imagedId, Model model)
	{
		if (imagedId > -1)
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
			model.addAttribute("painting", painting1);
		}
		else
		{
			Painting painting = new Painting();
			model.addAttribute("painting", painting);
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
	public List<Technique> loadFramesList()
	{
		List<Technique> techniquesList = new ArrayList<>();
		techniquesList.add(new Technique(1,"Olej na płótnie"));
		techniquesList.add(new Technique(2,"Olej na miedzi"));
		return techniquesList;
	}

}
