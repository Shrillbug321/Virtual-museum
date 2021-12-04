package pl.dreszer.projekt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.dreszer.projekt.models.Painting;

import java.util.List;
public interface PaintingsRepository extends JpaRepository<Painting, Integer> {
	//List<Painting> findByTitle(String title);
    Painting findByName(String name);
    @Query("select p from Painting p where p.author =:author")
    Painting findByTitle(@Param("author") String author);
    @Query("select p from Painting p where p.value :#(#filter.phrase))
}
