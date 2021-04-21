package isel.leic.ps.service;

import isel.leic.ps.model.IngredientDetails;

import java.util.List;

public interface IngredientDetailsService {

    /**
     * Returns an Ingredient Detail by it's id
     *
     * @param id
     * @return Ingredient Details
     */
    IngredientDetails getIngredientDetailsById(int id);

    /**
     * Returns an Ingredient Detail by it«s api id
     *
     * @param apiId
     * @return Ingredient Details
     */
    IngredientDetails getIngredientDetailsByApiId(int apiId);

    /**
     * Returns a list of the Ingredient Details by it's recipe id
     *
     * @param recipeId
     * @return Ingredient Details list
     */
    List<IngredientDetails> getIngredientDetailsByRecipeId(int recipeId);

    /**
     * Adds an Ingredient Detail
     *
     * @param ingredientDetails
     * @return Ingredient Details
     */
    IngredientDetails addIngredientDetails(IngredientDetails ingredientDetails);

    /**
     * Updates an Ingredient Detail
     *
     * @param ingredientDetails
     * @return Ingredient Detail
     */
    IngredientDetails updateIngredientDetails(IngredientDetails ingredientDetails);

    /**
     * Deletes an Ingredient Details by it's id
     *
     * @param id
     */
    void deleteById(int id);

    /**
     * Deletes an Ingredient Details by it's recipe id
     *
     * @param recipeId
     */
    void deleteByRecipeId(int recipeId);
}
