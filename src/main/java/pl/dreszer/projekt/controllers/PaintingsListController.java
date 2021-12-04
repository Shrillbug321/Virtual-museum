/*package pl.dreszer.projekt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.dreszer.projekt.repositories.PaintingsRepository;

@Controller
public class PaintingsListController {

    @Autowired
    PaintingsRepository paintingsRepository;

    @RequestMapping("table.html")
    public String handleRequest(Model model)
    {
        model.addAttribute("paintings", paintingsRepository.findAll());
        return "table";
    }

    @RequestMapping(name="delete.html", params={"imageId"})
    public String deletePainting(Model model, int imageId)
    {
        paintingsRepository.deleteById(imageId);
        model.addAttribute("imageId", imageId);
        return "delete";
    }
    @RequestMapping(name="searchForm.html", params={"phrase"})
    public String deletePainting(Model model, String phrase)
    {
        model.addAttribute("phrase", phrase);
        return "delete";
    }


}*/
