package pl.dreszer.projekt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.dreszer.projekt.controllers.filters.PaintingFilter;
import pl.dreszer.projekt.services.SearchService;

@Controller
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SearchService searchService;
    @PostMapping(value="/process.html")
    public String search(PaintingFilter paintingFilter, Model model)
    {
        searchService.search(paintingFilter, model);
        return "search/result";
    }

    @RequestMapping(value="/form.html")
    protected String showForm(PaintingFilter paintingFilter, Model model)
    {
        searchService.showForm(paintingFilter, model);
        return "search/form";
    }
}
