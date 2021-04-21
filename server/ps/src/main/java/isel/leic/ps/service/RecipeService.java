package isel.leic.ps.service;

import isel.leic.ps.model.Recipe;

public interface RecipeService {

    /**
     * Returns a recipe by it's id
     *
     * @param id
     * @return Recipe
     */
    Recipe getRecipeById(int id);

    /**
     * Returns a recipe by it's api id
     *
     * @param apiId
     * @return Recipe
     */
    Recipe getRecipeByApiId(int apiId);

    /**
     * Adds a recipe
     *
     * @param recipe
     * @return Recipe
     */
    Recipe addRecipe(Recipe recipe);

    /**
     * Updates a recipe
     *
     * @param recipe
     * @return Recipe
     */
    Recipe updateRecipe(Recipe recipe);

    /**
     * Deletes a recipe by it´s id
     *
     * @param id
     */
    void deleteRecipeById(int id);
}
