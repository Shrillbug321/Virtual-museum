package pl.dreszer.projekt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.dreszer.projekt.repositories.PaintingsRepository;

//myDB.list.stream().filter(x->x.get()==id).?.get();
@Controller
public class PaintingController {
    @Autowired
    PaintingsRepository paintingsRepository;
    @RequestMapping("paintingDetails.html")
    public String paintingDetails(Model model)
    {
        model.addAttribute("painting", paintingsRepository.getById(1));
        return "paintingDetails";
    }

    @RequestMapping("paintings.html")
    public String paintings(Model model)
    {
        model.addAttribute("paintings", paintingsRepository.findAll());
        return "paintings";
    }


}
