package pl.dreszer.projekt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dreszer.projekt.models.Genre;

public interface GenresRepository extends JpaRepository<Genre, Integer> {
}
