package isel.leic.ps.repository;

import isel.leic.ps.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {

    /**
     * Finds User by username
     *
     * @param username
     * @return Optional with a user if it finds a user with username, otherwise returns Option.Empty
     */
    Optional<Users> findByUsername(String username);
}
