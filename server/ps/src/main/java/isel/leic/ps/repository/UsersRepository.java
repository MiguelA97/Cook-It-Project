package isel.leic.ps.repository;

import isel.leic.ps.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {

    /**
     * Verify if exists user with param username
     *
     * @param username The username of the user
     * @return true if already exists a user with username, otherwise false
     */
    boolean existsByUsername(String username);

    /**
     * Verify if exists user with param email
     *
     * @param email The email of the user
     * @return true if already exists a user with email, otherwise false
     */
    boolean existsByEmail(String email);

    /**
     * Finds User by username
     *
     * @param username
     * @return Optional with a user if it finds a user with username, otherwise returns Option.Empty
     */
    Optional<Users> findByUsername(String username);

    /**
     * Deletes User by username
     *
     * @param username
     */
    @Modifying
    @Query(value = "delete from USERS where username = ?1", nativeQuery = true)
    void deleteByUsername(String username);
}
