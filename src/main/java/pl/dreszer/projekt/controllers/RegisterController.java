package pl.dreszer.projekt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.dreszer.projekt.models.authorization.User;
import pl.dreszer.projekt.services.MailService;
import pl.dreszer.projekt.services.RegisterFormService;

@Controller
@RequestMapping("/account/registration")
public class RegisterController
{
	@Autowired
	RegisterFormService registerFormService;
	@GetMapping("/form.html")
	public String showRegistrationForm(Model model)
	{
		registerFormService.showRegistrationForm(model);
		return "account/registration/form";
	}

	@PostMapping("/form.html")
	String processForm(User user, BindingResult result)
	{
		if (registerFormService.processForm(user, result).equals("failed"))
			return "account/registration/failed";
		return "account/registration/success";
	}
}
