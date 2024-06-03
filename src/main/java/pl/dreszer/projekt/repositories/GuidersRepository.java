package pl.dreszer.projekt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dreszer.projekt.models.Guider;

public interface GuidersRepository extends JpaRepository<Guider, Integer> {
}
