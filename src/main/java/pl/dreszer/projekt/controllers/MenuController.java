package pl.dreszer.projekt.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MenuController {

	@RequestMapping("index.html")
	public ModelAndView home(Model model)
	{
		model.addAttribute("attribute", "redirectWithRedirectPrefix");
		return new ModelAndView("redirect:/checkUserConfirm");
	}
	@RequestMapping("/")
	public ModelAndView home2(Model model)
	{
		model.addAttribute("attribute", "redirectWithRedirectPrefix");
		return new ModelAndView("redirect:/checkUserConfirm");
	}
}
