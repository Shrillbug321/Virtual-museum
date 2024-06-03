package pl.dreszer.projekt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import pl.dreszer.projekt.models.authorization.User;
import pl.dreszer.projekt.repositories.UsersRepository;

@Controller
public class LoginController {

	@Autowired
	UsersRepository usersRepository;
	@GetMapping("/checkUserConfirm")
	public String checkUserConfirm()
	{
		if (isAuthenticated())
		{
			UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			System.out.println(userDetails.getUsername());
			User user = usersRepository.findByUsername(userDetails.getUsername());
			if (user.isConfirmed())
				return "index";
			return "account/registration/confirm/accountNotConfirmed";
		}
		else
			return "account/authorization/login";
	}

	private boolean isAuthenticated()
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || AnonymousAuthenticationToken.class.isAssignableFrom(authentication.getClass()))
		{
			return false;
		}
		return true;
	}
}
