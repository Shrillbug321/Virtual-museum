/*
package pl.dreszer.projekt.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.dreszer.projekt.models.Authorization;
import pl.dreszer.projekt.models.Painting;

import javax.validation.Valid;

@Controller
public class LoginController {
	@RequestMapping("/login.html")
	public String login(Model model)
	{
		model.addAttribute("authorization", new Authorization());
		return "login";
	}

	@PostMapping("/login.html")
	public String login(Model model, @ModelAttribute("authorization") @Valid Authorization authorization, BindingResult result)
	{
		if (result.hasErrors())
			return "login";
		return "index";
	}
}
*/
