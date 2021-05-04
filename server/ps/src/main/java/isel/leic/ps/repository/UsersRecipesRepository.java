package isel.leic.ps.repository;

import isel.leic.ps.model.UsersRecipes;
import isel.leic.ps.model.UsersRecipesId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UsersRecipesRepository extends JpaRepository<UsersRecipes, UsersRecipesId> {

    /**
     * Finds all recipes id's from a list with the id idUrl
     *
     * @param idUrl id of the user recipe list
     * @return List with all recipes id's that belong to the list with the given id
     */
    @Query(value = "select id_recipe from users_recipes where id_url = ?1", nativeQuery = true)
    List<Integer> findByIdUrl(int idUrl);

    /**
     * Verify if a recipe exists in any lists of an user with the given id's
     *
     * @param idUser The id of the user
     * @param idRecipe The id of the recipe
     * @return true if exists a recipe list for the given parameters, otherwise false
     */
    boolean existsByIdUserAndIdRecipes(int idUser, int idRecipe);

    /**
     * Deletes a recipe from a list by it's id
     *
     * @param idUrl id of the user recipe list
     */
    @Modifying
    @Query(value = "delete from users_recipes where id_url = ?1 and id_recipe = ?2", nativeQuery = true)
    void deleteRecipeFromListByIdUrlAndRecipeId(int idUrl, int recipeId);
}
