package isel.leic.ps.repository;

import isel.leic.ps.exceptions.EntityException;
import isel.leic.ps.model.IngredientDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientDetailsRepository extends JpaRepository<IngredientDetails, Integer> {

    /**
     * Verifies if for the given recipe already exists an ingredient with the given name
     *
     * @param recipeId
     * @param ingredientName
     * @return true if ingredient exists, false otherwise
     * @throws EntityException if the given parameters are invalid
     */
    boolean existsByRecipeIdAndIngredientName(int recipeId, String ingredientName);

    /**
     * Get all ingredients from a recipe
     *
     * @param recipeId The id of the recipe
     * @return List with all ingredients from the recipe with id recipeId
     */
    List<IngredientDetails> findByRecipeId(int recipeId);
}
