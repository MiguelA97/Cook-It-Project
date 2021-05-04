package isel.leic.ps.service;

import isel.leic.ps.exceptions.EntityAlreadyExistsException;
import isel.leic.ps.exceptions.EntityException;
import isel.leic.ps.exceptions.EntityNotFoundException;
import isel.leic.ps.model.Recipe;

import java.util.List;

public interface RecipeService {

    /**
     * Verifies if the given recipe exists by it's id
     *
     * @param id
     * @return true if recipe exists, false otherwise
     * @throws EntityException if the given parameters are invalid
     */
     boolean existsRecipeById(int id) throws EntityException;

    /**
     * Returns a recipe by it's id
     *
     * @param id
     * @return Recipe
     * @throws EntityException if the given parameters are invalid
     * @throws EntityNotFoundException if no recipe with id is found
     */
    Recipe getRecipeById(int id) throws EntityException, EntityNotFoundException;

    /**
     * Returns a list's recipes by the list id
     *
     * @param listId
     * @throws EntityException if the given parameters are invalid
     * @throws EntityNotFoundException if no list with list id is found
     */
    List<Recipe> getRecipesByUserRecipeListId(int listId) throws EntityException, EntityNotFoundException;

    /**
     * Adds a recipe
     *
     * @param listId
     * @param recipe
     * @return Recipe
     * @throws EntityException if the given parameters are invalid
     * @throws EntityAlreadyExistsException if a recipe (from API) already exists in that list
     * @throws EntityNotFoundException if the parameters username or listId don't exist
     */
    Recipe addRecipe(int listId, Recipe recipe) throws EntityException, EntityAlreadyExistsException, EntityNotFoundException;

    /**
     * Updates a recipe
     *
     * @param recipe
     * @paran id
     * @return Recipe
     * @throws EntityException if the given parameters are invalid
     * @throws EntityNotFoundException if no recipe with id is found
     */
    Recipe updateRecipe(int id, Recipe recipe) throws EntityException, EntityNotFoundException;

    /**
     * Deletes a recipe from the list with the id listId, and if there are no more recipes in any list from the user with the given username, deletes the recipe with id recipeId
     *
     * @param username
     * @param listId
     * @param recipeId
     * @throws EntityException if the given parameters are invalid
     * @throws EntityNotFoundException if no recipe with id is found
     */
    void deleteRecipeById(String username, int listId, int recipeId) throws EntityException, EntityNotFoundException;
}
