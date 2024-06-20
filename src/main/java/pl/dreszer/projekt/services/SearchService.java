package pl.dreszer.projekt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.dreszer.projekt.controllers.filters.PaintingFilter;
import pl.dreszer.projekt.controllers.filters.PaintingsSpecifications;
import pl.dreszer.projekt.models.Genre;
import pl.dreszer.projekt.models.Museum;
import pl.dreszer.projekt.models.Painting;
import pl.dreszer.projekt.models.Technique;
import pl.dreszer.projekt.repositories.GenresRepository;
import pl.dreszer.projekt.repositories.MuseumsRepository;
import pl.dreszer.projekt.repositories.PaintingsRepository;
import pl.dreszer.projekt.repositories.TechniquesRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class SearchService {
    @Autowired
    private PaintingsRepository paintingsRepository;
    @Autowired
    private TechniquesRepository techniquesRepository;
    @Autowired
    private GenresRepository genresRepository;
    @Autowired
    private MuseumsRepository museumsRepository;
    @Value("${files.location.paintings}")
    String path;

    @Transactional(readOnly = true)
    public void search(PaintingFilter paintingFilter, Model model) {
        List<Painting> foundPaintings = null;
        LocalDate minDate, maxDate;
        switch (paintingFilter.getWhere()) {
            case "name":
                foundPaintings = paintingsRepository.findByNameContainingIgnoreCase(paintingFilter.getPhrase());
                break;
            case "author":
                foundPaintings = paintingsRepository.findByAuthor(paintingFilter.getPhrase());
                break;
            case "technique":
                foundPaintings = paintingsRepository.findByTechniqueId(Integer.parseInt(paintingFilter.getPhrase()));
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
                try (Stream<Painting> paintingStream = paintingsRepository.findByAddDate(minDate, maxDate)) {
                    List<Painting> finalFoundPaintings = new ArrayList<>();
                    paintingStream.forEach(finalFoundPaintings::add);
                    foundPaintings = finalFoundPaintings;
                }
                break;
            case "exhibited":
                boolean exhibited = paintingFilter.getPhrase().equals("yes");
                foundPaintings = paintingsRepository.findByExhibited(exhibited);
                break;
            case "genre":
                foundPaintings = paintingsRepository.findByGenre(Integer.parseInt(paintingFilter.getPhrase()));
                break;
            case "museum":
                foundPaintings = paintingsRepository.findByMuseum(Integer.parseInt(paintingFilter.getPhrase()));
                break;
        }
        model.addAttribute("paintings", foundPaintings);
        model.addAttribute("path", path + "/min");
    }

    @RequestMapping(value = "form.html")
    public void showForm(PaintingFilter paintingFilter, Model model) {
        model.addAttribute("filter", paintingFilter);
    }

    public List<Technique> loadTechniquesList() {
        return techniquesRepository.findAll();
    }

    public List<Genre> loadGenresSet() {
        return genresRepository.findAll();
    }

    public List<Museum> loadMuseumsList() {
        return museumsRepository.findAll();
    }
}