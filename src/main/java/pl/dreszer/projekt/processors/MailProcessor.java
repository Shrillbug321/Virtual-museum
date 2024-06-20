package pl.dreszer.projekt.processors;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Component
@Getter
@Setter
@NoArgsConstructor
public class MailProcessor {

    @Autowired
    private TemplateEngine templateEngine;
    @Value("${files.location}")
    private String imagesDir;

    public String helloInMuseum(String encodeUsername) {
        Context ctx = new Context();
        ctx.setVariable("imageSource", imagesDir + "/virtual_museum_logo_pl.png");
        ctx.setVariable("title", "Potwierdź konto w Wirtualnym Muzeum");
        ctx.setVariable("content", "Oto link aktywacyjny do Twojego konta w Wirtualnym Muzeum: ");
        ctx.setVariable("link", "http://localhost:8080/account/registration/confirm.html?u=" + encodeUsername);
        return templateEngine.process("mail", ctx);
    }
}