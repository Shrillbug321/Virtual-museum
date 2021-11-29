package pl.dreszer.projekt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dreszer.projekt.models.Technique;

public interface TechniquesRepository  extends JpaRepository<Technique, Integer> {
}
