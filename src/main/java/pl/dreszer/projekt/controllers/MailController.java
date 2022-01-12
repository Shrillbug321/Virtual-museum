package pl.dreszer.projekt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.dreszer.projekt.services.MailService;

@Controller
public class MailController {
	@Autowired
	MailService mailService;

	@RequestMapping("/mail.html")
	public void mail()
	{
		mailService.sendMail("salmarolmo@vusra.com", "ghgh", "iui", false);
	}
}
