package pl.dreszer.projekt.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.dreszer.projekt.configurations.Profiles;
import pl.dreszer.projekt.exceptions.PaintingNotFoundException;
import pl.dreszer.projekt.models.Painting;
import pl.dreszer.projekt.repositories.PaintingsRepository;

import java.util.List;

@Profile(Profiles.REST_CONTROLLERS)
@RestController
@RequestMapping("/paintings")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PaintingController {
    @Autowired
    PaintingsRepository paintingsRepository;

    @Value("${files.location.paintings.max}")
    String path;
    @GetMapping("/{paintingId}")
    public Painting paintingDetails(@PathVariable int paintingId)
    {
        Painting painting = paintingsRepository.getById(paintingId);
        if (!paintingsRepository.existsById(paintingId))
        {
            throw new PaintingNotFoundException();
        }
        return painting;
    }

    @GetMapping()
    public List<Painting> paintings()
    {
        return paintingsRepository.findAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePainting(@PathVariable int id) {
        paintingsRepository.deleteById(id);
    }

}

