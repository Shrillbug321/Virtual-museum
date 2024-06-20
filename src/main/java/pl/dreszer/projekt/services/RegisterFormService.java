package pl.dreszer.projekt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import pl.dreszer.projekt.models.authorization.Role;
import pl.dreszer.projekt.models.authorization.User;
import pl.dreszer.projekt.processors.MailProcessor;
import pl.dreszer.projekt.repositories.RolesRepository;
import pl.dreszer.projekt.repositories.UsersRepository;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;

@Service
public class RegisterFormService {
    @Autowired
    UsersRepository usersRepository;

    @Autowired
    RolesRepository rolesRepository;

    @Autowired
    MailService mailService;

    @Autowired
    MailProcessor mailProcessor;

    public void showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
    }

    public String processForm(User user, BindingResult result) {
        if (result.hasErrors())
            return "failed";
        user.encodePassword(user.getPassword());
        usersRepository.save(user);
        String encodeUsername = Base64.getEncoder().encodeToString(user.getUsername().getBytes());
        mailService.sendMimeMail(user.getEmail(), "Potwierdź konto w Wirtualnym Muzeum", mailProcessor.helloInMuseum(encodeUsername));
        return "success";
    }

    public String confirm(String encodeUsername) {
        String username = new String(Base64.getDecoder().decode(encodeUsername.getBytes()), StandardCharsets.UTF_8);
        User user = usersRepository.findByUsername(username);
        if (user != null) {
            Set<Role> roles = new HashSet<>();
            roles.add(rolesRepository.getById(1));
            user.setConfirmed(true);
            user.setRoles(roles);
            usersRepository.save(user);
            return "confirmSuccess";
        }
        return "confirmFailed";
    }
}
