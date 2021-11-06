package pl.dreszer.projekt.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import static pl.dreszer.projekt.controllers.PaintingController.paintingList;

@Controller
public class listController {
    @RequestMapping("table.html")
    public String handleRequest(Model model)
    {
        model.addAttribute("paintings", paintingList);
        return "table";
    }}
