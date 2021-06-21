package isel.leic.ps.service;

import isel.leic.ps.exceptions.EntityException;
import isel.leic.ps.exceptions.EntityNotFoundException;
import isel.leic.ps.model.outputModel.jsonObjects.RecipeInformationObject;
import isel.leic.ps.model.outputModel.jsonObjects.RecipeObject;

import java.util.List;

public interface APIService {

    /**
     * Returns a list of search recipes objects from a query or ingredients
     *
     * @param query query to search
     * @param number number of recipes to return (between 0 and 100)
     * @param offset number of results to skip (between 0 and 900)
     * @param diet diet to which the recipes must be compliant
     * @param intolerances a comma-separated list of intolerances
     * @param type type of the recipes
     * @param cuisine cuisine(s) of the recipes
     * @param ingredients ingredient(s) of the recipe
     * @return Search Recipe Objects list
     * @throws EntityException if query is null
     */
    List<RecipeObject> searchRecipes(String query, String number, String offset, String diet, String intolerances, String type, String cuisine, String ingredients) throws EntityException;

    /**
     * Returns the recipe's information
     *
     * @param recipeId
     * @return Recipe information object
     * @throws EntityException if the given parameter is invalid
     * @throws EntityNotFoundException if no recipe with recipeId is found
     */
    RecipeInformationObject getRecipeInformation(int recipeId) throws EntityException, EntityNotFoundException;
}