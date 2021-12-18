package pl.dreszer.projekt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.dreszer.projekt.models.authorization.User;
import pl.dreszer.projekt.repositories.UsersRepository;

@Controller
public class RegisterController {

	@Autowired
	UsersRepository usersRepository;
	@GetMapping("/registerForm.html")
	public String showRegisterForm(Model model)
	{
		model.addAttribute("user", new User());
		return "authorization/registerForm";
	}

	@PostMapping("/registerForm.html") String processForm(Model model, User user, BindingResult result)
	{
		if (result.hasErrors())
			return "authorization/failedRegistered";
		user.encodePassword(user.getPassword());
		usersRepository.save(user);
		return "authorization/successRegistered";
	}
}
