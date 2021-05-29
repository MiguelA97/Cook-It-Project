package isel.leic.ps.service;

import isel.leic.ps.exceptions.EntityException;
import isel.leic.ps.exceptions.EntityNotFoundException;
import isel.leic.ps.model.outputModel.jsonObjects.RecipeInformationObject;
import isel.leic.ps.model.outputModel.jsonObjects.RecipeObject;
import isel.leic.ps.model.outputModel.jsonObjects.SearchRecipesByIngredientsObject;

import java.util.List;

public interface APIService {

    /**
     * Returns a list of search recipes objects from a query
     *
     * @param query
     * @return Search Recipe Objects list
     * @throws EntityException if query is null
     */
    List<RecipeObject> searchRecipes(String query) throws EntityException;

    /**
     * Returns the recipe's information
     *
     * @param recipeId
     * @return Recipe information object
     * @throws EntityException if the given parameter is invalid
     * @throws EntityNotFoundException if no recipe with recipeId is found
     */
    RecipeInformationObject getRecipeInformation(int recipeId) throws EntityException, EntityNotFoundException;

    /**
     * Returns a list of search recipes by ingredients objects from a list of ingredients
     *
     * @param ingredients
     * @return Search Recipe by Ingredients Objects list
     * @throws EntityException if ingredients is null
     */
    List<SearchRecipesByIngredientsObject> searchRecipesByIngredients(String ingredients) throws EntityException;
}
