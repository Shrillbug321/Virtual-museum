package pl.dreszer.projekt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.dreszer.projekt.controllers.filters.PaintingFilter;
import pl.dreszer.projekt.models.Genre;
import pl.dreszer.projekt.models.Museum;
import pl.dreszer.projekt.models.Technique;
import pl.dreszer.projekt.services.SearchService;

import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @PostMapping(value = "/process.html")
    public String search(PaintingFilter paintingFilter, Model model) {
        searchService.search(paintingFilter, model);
        return "search/result";
    }

    @RequestMapping(value = "/form.html")
    protected String showForm(PaintingFilter paintingFilter, Model model) {
        searchService.showForm(paintingFilter, model);
        return "search/form";
    }

    @ModelAttribute("techniques")
    public List<Technique> loadTechniquesList() {
        return searchService.loadTechniquesList();
    }

    @ModelAttribute("genres")
    public List<Genre> loadGenresSet() {
        return searchService.loadGenresSet();
    }

    @ModelAttribute("museums")
    public List<Museum> loadMuseumsList() {
        return searchService.loadMuseumsList();
    }
}
