package pl.dreszer.projekt.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
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
	@EntityGraph(value="pgraph", type= EntityGraph.EntityGraphType.FETCH)
	List<Painting> findByNameContainingIgnoreCase(String name);//8
	List<Painting> findByTechnique(String technique);//9.1
	@EntityGraph(value="pgraph", type= EntityGraph.EntityGraphType.FETCH)
    @Query("select p from Painting p where p.author =:author")//9.2
	List<Painting> findByAuthor(@Param("author") String author);
	@EntityGraph(value="pgraph", type= EntityGraph.EntityGraphType.FETCH)
    @Query("select p from Painting p where p.value between :#{#filter.min} and :#{#filter.max}")//9.3
	List<Painting> findByValue(@Param("filter")PaintingFilter paintingFilter);
	@EntityGraph(value="pgraph", type= EntityGraph.EntityGraphType.FETCH)
	@Query("select p from Painting p where p.addDate between :minDate and :maxDate")//9.5
	Stream<Painting> findByAddDate(@Param("minDate")LocalDate minDate, @Param("maxDate")LocalDate maxDate);
}
