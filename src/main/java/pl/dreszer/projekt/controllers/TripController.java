package pl.dreszer.projekt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.dreszer.projekt.models.Guider;
import pl.dreszer.projekt.models.Trip;
import pl.dreszer.projekt.services.TripService;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/trips")
@Controller
public class TripController {
    @Autowired
    TripService tripService;

    @GetMapping("list.html")
    public String showList(Model model) {
        tripService.trips(model);
        return "trips/list";
    }

    @GetMapping("form.html")
    public String showForm(@RequestParam(value = "tripId", required = false, defaultValue = "-1") int tripId,
                           Model model, @RequestParam(value = "edit") boolean edit) {
        tripService.showForm(tripId, model, edit);
        return "trips/form";
    }

    @PostMapping("form.html")
    public String processForm(Model model, @Valid @ModelAttribute("trip") Trip trip,
                              BindingResult result, @RequestParam(value = "edit") boolean edit) {
        if (tripService.processForm(model, trip, result, edit).equals("success"))
            return "redirect:/trips/list.html";
        else
            return "trips/form";
    }

    @RequestMapping("/details.html")
    public String paintingDetails(Model model, int tripId) {
        tripService.tripDetails(model, tripId);
        return "trips/details";
    }

    @GetMapping("save.html")
    public String save(@RequestParam int tripId) {
        tripService.save(tripId);
        return "redirect:/trips/list.html";
    }

    @GetMapping("unsave.html")
    public String unsave(@RequestParam int tripId) {
        tripService.unsave(tripId);
        return "redirect:/trips/list.html";
    }

    @GetMapping(path = "delete.html", params = {"tripId"})
    public String delete(Model model, @RequestParam int tripId) {
        tripService.delete(model, tripId);
        return "redirect:/trips/list.html";
    }

    @ModelAttribute("guiders")
    public List<Guider> loadGuiders() {
        return tripService.loadGuiders();
    }
}
