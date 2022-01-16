package pl.dreszer.projekt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dreszer.projekt.models.Museum;

public interface MuseumsRepository extends JpaRepository<Museum, Integer> {
}
