package pl.dreszer.projekt.repositories;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.dreszer.projekt.models.Genre;
import pl.dreszer.projekt.models.Painting;
import pl.dreszer.projekt.models.Technique;
import pl.dreszer.projekt.models.authorization.Role;
import pl.dreszer.projekt.models.authorization.User;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class RepositoriesConfiguration{
    @Autowired
    PaintingsRepository paintingsRepository;
    @Autowired
    TechniquesRepository techniquesRepository;
    @Autowired
    GenresRepository genresRepository;
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    RolesRepository rolesRepository;
    @Bean
    InitializingBean init()
    {
        return () ->
        {
            genresRepository.save(new Genre(1,"Sielanka"));
            genresRepository.save(new Genre(2,"Martwa natura"));
            genresRepository.save(new Genre(3,"Rysunek"));
            if(paintingsRepository.findAll().isEmpty())
            {
                Technique technique = new Technique(1,"Olej na płótnie");
                techniquesRepository.save(technique);
                technique = new Technique(2,"Olej na miedzi");
                techniquesRepository.save(technique);
                paintingsRepository.save(createPainting());
            }

            if(rolesRepository.findAll().isEmpty())
            {
                Role roleUser = new Role(Role.Types.ROLE_USER);
                Role roleAdmin = new Role(Role.Types.ROLE_ADMIN);
                rolesRepository.save(roleUser);
                rolesRepository.save(roleAdmin);

                PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                User user = new User("user", true);
                user.setPassword(passwordEncoder.encode("123"));
                user.setRoles(new HashSet<>(Arrays.asList(roleUser)));
                User admin = new User("admin", true);
                admin.setPassword((passwordEncoder.encode("admin")));
                admin.setRoles(new HashSet<>(Arrays.asList(roleAdmin)));
                User superuser = new User("superuser", true);
                superuser.setPassword((passwordEncoder.encode("123")));
                superuser.setRoles(new HashSet<>(Arrays.asList(roleUser, roleAdmin)));

                usersRepository.save(user);
                usersRepository.save(admin);
                usersRepository.save(superuser);
            }

        };
    }

    @Bean(name="PaintingsBean")
    public Painting createPainting()
    {
        Set<Genre> a = new HashSet<Genre>();
        Genre b = genresRepository.getById(2);
        //a.add(b);
        Painting painting1 = new Painting();
        painting1.setId(1);
        painting1.setName("Słoneczniki");
        painting1.setAuthor("Van Gogh");
        painting1.setAddDate(LocalDate.of(2021, 10, 20));
        painting1.setPaintedDate(LocalDate.of(1872, 5, 15));
        painting1.setValue(1000000.99f);
        painting1.setExhibited(false);
        painting1.setTechnique(techniquesRepository.getById(1));
        //painting1.setGenres( a);
        return painting1;
        //painting1.setGenres(a);
        //paintingsRepository.save(painting1);
    }

}
