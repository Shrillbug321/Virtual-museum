package pl.dreszer.projekt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dreszer.projekt.models.authorization.Role;

public interface RolesRepository extends JpaRepository<Role, Integer> {
}
