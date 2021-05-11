package isel.leic.ps.service;

import isel.leic.ps.exceptions.EntityAlreadyExistsException;
import isel.leic.ps.exceptions.EntityException;
import isel.leic.ps.exceptions.EntityNotFoundException;
import isel.leic.ps.model.IngredientDetails;
import isel.leic.ps.utils.ValidationsUtils;

import java.util.List;

public interface IngredientDetailsService {

    /**
     * Verifies if the given ingredient details exists by it's id
     *
     * @param id
     * @return true if ingredient details exists, false otherwise
     * @throws EntityException if the given parameters are invalid
     */
    boolean existsIngredientDetailsById(int id) throws EntityException;

    /**
     * Verifies if for the given recipe already exists an ingredient with the given name
     *
     * @param recipeId
     * @param ingredientName
     * @return true if ingredient exists, false otherwise
     * @throws EntityException if the given parameters are invalid
     */
    boolean existsIngredientDetailsByRecipeIdAndIngredientName(int recipeId, String ingredientName) throws EntityException;

    /**
     * Returns an Ingredient Detail by it's id
     *
     * @param id
     * @return Ingredient Details
     * @throws EntityException if the given parameters are invalid
     * @throws EntityNotFoundException if no ingredient details with id is found
     */
    IngredientDetails getIngredientDetailsById(int id) throws EntityException, EntityNotFoundException;

    /**
     * Returns a list of the Ingredient Details by it's recipe id
     *
     * @param recipeId
     * @return Ingredient Details list
     * @throws EntityException if the given parameters are invalid
     * @throws EntityNotFoundException if no recipe with recipeId is found
     */
    List<IngredientDetails> getIngredientDetailsByRecipeId(int recipeId) throws EntityException, EntityNotFoundException;

    /**
     * Adds an Ingredient Detail
     *
     * @param recipeId
     * @param ingredientDetails
     * @return Ingredient Details
     * @throws EntityException if the given parameters are invalid
     * @throws EntityAlreadyExistsException if an ingredient with the same name already exists for that recipe
     * @throws EntityNotFoundException if the parameter recipeId don't exist
     */
    IngredientDetails addIngredientDetails(int recipeId, IngredientDetails ingredientDetails) throws EntityException, EntityAlreadyExistsException, EntityNotFoundException;

    /**
     * Updates an Ingredient Detail
     *
     * @param ingredientDetailsId
     * @param ingredientDetails
     * @return Ingredient Detail
     * @throws EntityException if the given parameters are invalid
     * @throws EntityAlreadyExistsException if an ingredient with the same name already exists for that recipe
     * @throws EntityNotFoundException if the parameter ingredientDetailsId don't exist
     */
    IngredientDetails updateIngredientDetails(int ingredientDetailsId, IngredientDetails ingredientDetails) throws EntityException, EntityAlreadyExistsException, EntityNotFoundException;

    /**
     * Deletes an Ingredient Details by it's id
     *
     * @param id
     * @throws EntityException if the given parameters are invalid
     * @throws EntityNotFoundException if no ingredient details with id is found
     */
    void deleteById(int id) throws EntityException, EntityNotFoundException;
}
