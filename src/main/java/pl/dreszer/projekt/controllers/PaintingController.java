package pl.dreszer.projekt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.dreszer.projekt.configurations.Profiles;
import pl.dreszer.projekt.services.PaintingsService;

@Profile(Profiles.PLAIN_CONTROLLERS)
@PropertySource("classpath:config.properties")
@RequestMapping("/paintings")
@Controller
public class PaintingController {
    @Autowired
    PaintingsService paintingsService;

    @RequestMapping("/details.html")
    public String paintingDetails(Model model, int paintingId)
    {
        paintingsService.paintingDetails(model, paintingId);
        return "paintings/details";
    }

    @EntityGraph(value="pgraph", type= EntityGraph.EntityGraphType.FETCH)
    @RequestMapping("list.html")
    public String paintings(Model model)
    {
        paintingsService.paintings(model);
        return "paintings/list";
    }

    @GetMapping(path="delete.html", params={"paintingId"})
    public String delete(Model model, @RequestParam int paintingId)
    {
        paintingsService.delete(model, paintingId);
        return "paintings/delete";
    }
}
