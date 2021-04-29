package isel.leic.ps.repository;

import isel.leic.ps.model.UserRecipeList;
import isel.leic.ps.model.UserRecipeListId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRecipeListRepository extends JpaRepository<UserRecipeList, UserRecipeListId> {

    /**
     * Verify if exists user recipe list with id url
     *
     * @param idUrl The id of the user recipe list
     * @return true if already exists a user recipe list with id url, otherwise false
     */
    boolean existsByIdUrl(int idUrl);

    /**
     * Verify if exists user recipe list with params id user and list name
     *
     * @param idUser The id of the user recipe list
     * @param listName The name of the user recipe list
     * @return true if already exists a user recipe list with id user and list name, otherwise false
     */
    boolean existsByIdUserAndListName(int idUser, String listName);

    /**
     * Find all user recipe lists from a user with username
     *
     * @param username The username of the user
     * @return List with all user recipe lists that belong to the user with username
     */
    @Query(value = "select * from user_recipe_list where id_user = (select id_user from users where username = '?1') ", nativeQuery = true)
    List<UserRecipeList> findByUsername(String username);

    /**
     * Finds user recipe list by id
     *
     * @param idUrl id of the user recipe list
     * @return Optional with a user recipe list if it finds one with the given id, otherwise returns Optional.Empty
     */
    Optional<UserRecipeList> findByIdUrl(int idUrl);

    /**
     * Deletes user recipe list by id
     *
     * @param idUrl id of the user recipe list
     */
    @Modifying
    @Query(value = "delete from USER_RECIPE_LIST where id_url = ?1", nativeQuery = true)
    void deleteByIdUrl(int idUrl);
}
