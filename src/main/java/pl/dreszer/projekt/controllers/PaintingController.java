package pl.dreszer.projekt.controllers;

import pl.dreszer.projekt.models.Painting;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.dreszer.projekt.models.Technique;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
//myDB.list.stream().filter(x->x.get()==id).?.get();
@Controller
public class PaintingController {
    @RequestMapping("paintingDetails.html")
    public String handleRequest(Model model)
    {
        model.addAttribute("painting", paintingList.get(0));
        return "paintingDetails";
    }
    static List<Painting> paintingList;
    static
    {
        paintingList = new ArrayList<>();
        Painting painting1 = new Painting();
        painting1.setId(1);
        painting1.setName("Słoneczniki");
        painting1.setAuthor("Van Gogh");
        painting1.setAddDate(LocalDate.of(2021, 10, 20));
        painting1.setPaintedDate(LocalDate.of(1872, 5, 15));
        painting1.setValue(1000000.99f);
        painting1.setExhibited(false);
        painting1.setTechnique(new Technique(1, "Olej na płótnie"));
        paintingList.add(painting1);
    }

    @RequestMapping("paintings.html")
    public String handleRequest()
    {
        return "paintings";
    }


}
