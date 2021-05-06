package isel.leic.ps.repository;

import isel.leic.ps.exceptions.EntityException;
import isel.leic.ps.model.Recipe;
import isel.leic.ps.model.UserRecipeList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {

    /**
     * Verifies if the list with id listId already contains a recipe with api id
     *
     * @param listId
     * @param apiId
     * @return true if recipe exists, false otherwise
     * @throws EntityException if the given parameters are invalid
     */
    boolean existsByIdUrlAndIdApi(int listId, int apiId);

    /**
     * Get all recipes from a user recipe list
     *
     * @param listId The id of the user recipe list
     * @return List with all recipes that belong to the user recipe list with id listId
     */
    @Query(value = "select * from recipe where id_url = ?1", nativeQuery = true)
    List<Recipe> getRecipesByUserRecipeListId(int listId);
}
