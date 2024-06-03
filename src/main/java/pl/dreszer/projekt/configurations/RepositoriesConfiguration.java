package pl.dreszer.projekt.configurations;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.dreszer.projekt.models.*;
import pl.dreszer.projekt.models.authorization.Role;
import pl.dreszer.projekt.models.authorization.User;
import pl.dreszer.projekt.repositories.*;

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
    @Autowired
    MuseumsRepository museumsRepository;
    @Bean
    InitializingBean init()
    {
        return () ->
        {
            if (genresRepository.findAll().isEmpty())
            {
                genresRepository.save(new Genre(1,"Sielanka"));
                genresRepository.save(new Genre(2,"Martwa natura"));
                genresRepository.save(new Genre(3,"Rysunek"));
            }
            if (museumsRepository.findAll().isEmpty())
            {
                museumsRepository.save(new Museum(1, "Luwr", "Paryż"));
                museumsRepository.save(new Museum(2, "Etruskie", "Watykan"));
                museumsRepository.save(new Museum(3, "Miejskie Muzeum Sztuki", "Nowy Jork"));
                museumsRepository.save(new Museum(4, "Galeria Uffizi", "Florencja"));
            }
            if(paintingsRepository.findAll().isEmpty())
            {
                techniquesRepository.save(new Technique(1,"Olej na płótnie"));
                techniquesRepository.save(new Technique(2,"Olej na miedzi"));
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
                user.setConfirmed(true);

                User admin = new User("admin", true);
                admin.setPassword((passwordEncoder.encode("admin")));
                admin.setRoles(new HashSet<>(Arrays.asList(roleAdmin)));
                admin.setConfirmed(true);
/*
DROP TABLE USERS_ROLES ;
drop table users ;
drop table roles
DROP TABLE PAINTINGS_GENRES ;
drop table paintings ;
*/
                User superuser = new User("superuser", true);
                superuser.setPassword((passwordEncoder.encode("123")));
                superuser.setRoles(new HashSet<>(Arrays.asList(roleUser, roleAdmin)));
                superuser.setConfirmed(true);

                usersRepository.save(user);
                usersRepository.save(admin);
                usersRepository.save(superuser);
            }
        };
    }

    @Bean(name="PaintingsBean")
    public Painting createPainting()
    {
        Set<Genre> a = new HashSet<>();
        a.add(genresRepository.getById(2));
        Painting painting1 = new Painting();
        painting1.setPaintingId(1);
        painting1.setName("Słoneczniki");
        painting1.setAuthor("Van Gogh");
        painting1.setAddDate(LocalDate.of(2021, 10, 20));
        painting1.setPaintedDate(LocalDate.of(1872, 5, 15));
        painting1.setValue(1000000.99f);
        painting1.setExhibited(false);
        painting1.setDimensions("5x5");
        painting1.setTechnique(techniquesRepository.getById(1));
        painting1.setGenres(a);
        painting1.setMuseum(museumsRepository.getById(1));
        painting1.setExemplars(2);
        return painting1;
    }

}
