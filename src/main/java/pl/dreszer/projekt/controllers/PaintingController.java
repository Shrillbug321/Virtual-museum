package pl.dreszer.projekt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.dreszer.projekt.configurations.Profiles;
import pl.dreszer.projekt.exceptions.PaintingNotFoundException;
import pl.dreszer.projekt.models.Painting;
import pl.dreszer.projekt.repositories.PaintingsRepository;
@Profile(Profiles.PLAIN_CONTROLLERS)
//myDB.list.stream().filter(x->x.get()==id).?.get();
@PropertySource("classpath:config.properties")
@Controller
public class PaintingController {
    @Autowired
    PaintingsRepository paintingsRepository;

    @Value("${files.location.paintings.max}")
    String path;
    @EntityGraph(value="pgraph", type= EntityGraph.EntityGraphType.FETCH)
    @RequestMapping("paintingDetails.html")
    public String paintingDetails(Model model, int paintingId)
    {
        Painting painting = paintingsRepository.getById(paintingId);
        if (!paintingsRepository.existsById(paintingId))
        {
            throw new PaintingNotFoundException();
        }
        model.addAttribute("painting", painting);
        model.addAttribute("path", path+"/"+paintingId+"/image.png");
        return "paintingDetails";
    }

    @EntityGraph(value="pgraph", type= EntityGraph.EntityGraphType.FETCH)
    @RequestMapping("paintings.html")
    public String paintings(Model model)
    {
        model.addAttribute("paintings", paintingsRepository.findAll());
        return "paintings";
    }


}
