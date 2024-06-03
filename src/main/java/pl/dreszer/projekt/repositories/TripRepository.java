package pl.dreszer.projekt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dreszer.projekt.models.Trip;

public interface TripRepository extends JpaRepository<Trip, Integer> {
}
