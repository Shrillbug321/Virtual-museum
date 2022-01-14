package pl.dreszer.projekt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailService {
	@Autowired
	private JavaMailSender javaMailSender;

	public void sendMimeMail(String to, String subject, String text)
	{
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
		try {
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(text, true);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		javaMailSender.send(message);
	}
}
