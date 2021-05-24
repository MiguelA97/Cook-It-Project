package isel.leic.ps.service;

import isel.leic.ps.exceptions.EntityException;
import isel.leic.ps.model.outputModel.jsonObjects.RecipeObject;

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
}
