package pl.dreszer.projekt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.dreszer.projekt.models.authorization.User;

public interface UsersRepository extends JpaRepository<User, Integer> {
    @Query("select u from User u where u.username =:username")
    User findByUsername(@Param("username") String username);
}
