package pl.dreszer.projekt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.dreszer.projekt.exceptions.PaintingNotFoundException;
import pl.dreszer.projekt.models.Painting;
import pl.dreszer.projekt.repositories.PaintingsRepository;

@Service
public class PaintingsService {
	@Autowired
	PaintingsRepository paintingsRepository;
	@Value("${files.location.paintings}")
	String path;
	public void paintingDetails(Model model, int paintingId)
	{
		Painting painting = paintingsRepository.getById(paintingId);
		if (!paintingsRepository.existsById(paintingId))
		{
			throw new PaintingNotFoundException();
		}
		model.addAttribute("painting", painting);
		model.addAttribute("filepath", path+"/max/"+paintingId+"/image.jpg");
	}

	public void paintings(Model model)
	{
		model.addAttribute("paintings", paintingsRepository.findAll());
		model.addAttribute("path", path+"/min");
	}

	public void delete(Model model, int paintingId)
	{
		Painting painting = paintingsRepository.getById(paintingId);
		model.addAttribute("name", painting.getName());
		paintingsRepository.delete(painting);
	}
}
