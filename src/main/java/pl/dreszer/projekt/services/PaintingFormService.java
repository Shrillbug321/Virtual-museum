package pl.dreszer.projekt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import pl.dreszer.projekt.controllers.filters.PaintingFilter;
import pl.dreszer.projekt.controllers.filters.PaintingsSpecifications;
import pl.dreszer.projekt.models.Genre;
import pl.dreszer.projekt.models.Painting;
import pl.dreszer.projekt.models.Technique;
import pl.dreszer.projekt.repositories.GenresRepository;
import pl.dreszer.projekt.repositories.PaintingsRepository;
import pl.dreszer.projekt.repositories.TechniquesRepository;
import pl.dreszer.projekt.validators.PaintingValidator;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class PaintingFormService {

	@Autowired
	private PaintingsRepository paintingsRepository;
	@Autowired
	private TechniquesRepository techniquesRepository;
	@Autowired
	private GenresRepository genresRepository;
	@Autowired
	private FileServiceImpl fileService;
	@Value("${files.location.paintings.med}")
	String filepath;
	@InitBinder("painting")
	public void initBinder(WebDataBinder binder)
	{
		binder.addValidators(new PaintingValidator());
	}

	public void showForm(int paintingId, Model model, boolean edit)
	{
		if (edit && paintingId > 0)
		{
			model.addAttribute("painting", paintingsRepository.getById(paintingId));
		}
		else
		{
			model.addAttribute("painting", new Painting());
		}
		model.addAttribute("edit", edit);
		model.addAttribute("filepath", filepath+"/"+paintingId+"/image.jpg");
	}

	public String processForm(Model model, Painting painting, BindingResult result, boolean edit, MultipartFile multipartFile)
	{
		if (result.hasErrors())
			return "form";
		paintingsRepository.save(painting);
		if (!multipartFile.isEmpty())
		{
			try {
				fileService.saveFile(multipartFile, painting.getPaintingId());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		model.addAttribute("painting", painting);
		model.addAttribute("edit", edit);
		return "success";
	}

	public List<Technique> loadTechniquesList()
	{
		return techniquesRepository.findAll();
	}

	public List<Genre> loadGenresSet()
	{
		return genresRepository.findAll();
	}
}
