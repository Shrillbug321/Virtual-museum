package pl.dreszer.projekt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dreszer.projekt.models.Painting;

import java.util.List;

public interface PaintingsRepository extends JpaRepository<Painting, Integer> {
	//List<Painting> findByTitle(String title);
}
