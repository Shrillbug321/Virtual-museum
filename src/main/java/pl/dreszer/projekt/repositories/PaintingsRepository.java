package pl.dreszer.projekt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.dreszer.projekt.controllers.filters.PaintingFilter;
import pl.dreszer.projekt.models.Painting;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

public interface PaintingsRepository extends JpaRepository<Painting, Integer>, JpaSpecificationExecutor<Painting> {
    List<Painting> findByNameContainingIgnoreCase(String name);

    @Query("select p from Painting p where p.author =:author")
    List<Painting> findByAuthor(@Param("author") String author);

    @Query("select p from Painting p where p.technique.id =:technique_id")
    List<Painting> findByTechniqueId(@Param("technique_id") int technique_id);

    @Query("select p from Painting p where p.value between :#{#filter.min} and :#{#filter.max}")
    List<Painting> findByValue(@Param("filter") PaintingFilter paintingFilter);

    @Query("select p from Painting p where p.addDate between :minDate and :maxDate")
    Stream<Painting> findByAddDate(@Param("minDate") LocalDate minDate, @Param("maxDate") LocalDate maxDate);

    @Query("select p from Painting p where p.exhibited = :exhibited")
    List<Painting> findByExhibited(@Param("exhibited") boolean exhibited);

    @Query("select p from Painting p inner join p.genres g where g.genreId = :genreId")
    List<Painting> findByGenre(@Param("genreId") int genreId);

    @Query("select p from Painting p inner join p.museum m where m.museumId = :museumId")
    List<Painting> findByMuseum(@Param("museumId") int museumId);
}