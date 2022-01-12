package pl.dreszer.projekt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
	@Autowired
	private JavaMailSender javaMailSender;

	public void sendMail(String to, String subject, String text, boolean isHtmlContent)
	{
		SimpleMailMessage message = new SimpleMailMessage();
		//message.setFrom("jakubiak-123@wp.pl");
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
		javaMailSender.send(message);
	}
}
