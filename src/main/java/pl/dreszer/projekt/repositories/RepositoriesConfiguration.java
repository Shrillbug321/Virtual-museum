package pl.dreszer.projekt.repositories;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.dreszer.projekt.models.Genre;
import pl.dreszer.projekt.models.Painting;
import pl.dreszer.projekt.models.Technique;

import java.time.LocalDate;
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
    @Bean
    InitializingBean init()
    {
        return () ->
        {
            genresRepository.save(new Genre(1,"Sielanka"));
            genresRepository.save(new Genre(2,"Martwa natura"));
            genresRepository.save(new Genre(3,"Rysunek"));
            //a.add(new Genre(1,"hj"));
            if(paintingsRepository.findAll().isEmpty())
            {
                //paintingsRepository.save(createPainting());
                //Technique technique = new Technique(1,"55");
                //paintingsRepository.save(technique);
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
