package pl.dreszer.projekt.controllers;

import pl.dreszer.projekt.models.Painting;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.dreszer.projekt.models.PaintingsList;
//myDB.list.stream().filter(x->x.get()==id).?.get();
@Controller
public class PaintingController {
    @RequestMapping("paintingDetails.html")
    public String handleRequest(Model model)
    {
        model.addAttribute("painting", PaintingsList.paintingsList.get(0));
        return "paintingDetails";
    }

    @RequestMapping("paintings.html")
    public String handleRequest()
    {
        return "paintings";
    }


}
