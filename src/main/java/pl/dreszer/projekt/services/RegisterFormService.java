package pl.dreszer.projekt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import pl.dreszer.projekt.models.authorization.User;
import pl.dreszer.projekt.repositories.UsersRepository;

@Service
public class RegisterFormService {
	@Autowired
	UsersRepository usersRepository;

	@Autowired
	MailService mailService;

	public void showRegistrationForm(Model model)
	{
		model.addAttribute("user", new User());
	}

	public String processForm(User user, BindingResult result)
	{
		if (result.hasErrors())
			return "failed";
		user.encodePassword(user.getPassword());
		usersRepository.save(user);
		mailService.sendMail(user.getEmail(), "Potwierdź konto w Wirtualnym Muzeum", "", false);
		return "success";
	}
}
