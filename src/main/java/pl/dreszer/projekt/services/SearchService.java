package pl.dreszer.projekt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.dreszer.projekt.controllers.filters.PaintingFilter;
import pl.dreszer.projekt.controllers.filters.PaintingsSpecifications;
import pl.dreszer.projekt.models.Painting;
import pl.dreszer.projekt.repositories.PaintingsRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
@Service
public class SearchService {
    @Autowired
    private PaintingsRepository paintingsRepository;

    @Transactional(readOnly = true)
    public void search(PaintingFilter paintingFilter, Model model)
    {
        System.out.println(paintingFilter.getPhrase());
        List<Painting> foundPaintings = null;
        LocalDate minDate, maxDate;
        switch (paintingFilter.getWhere())
        {
            case "name":
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
                break;
        }
        model.addAttribute("paintings", foundPaintings);
    }

    @RequestMapping(value="form.html")
    public void showForm(PaintingFilter paintingFilter, Model model)
    {
        model.addAttribute("filter", paintingFilter);
    }
}
