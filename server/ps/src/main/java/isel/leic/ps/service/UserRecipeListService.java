package isel.leic.ps.service;

import java.util.List;
import isel.leic.ps.model.UserRecipeList;

public interface UserRecipeListService {

    /**
     * Returns a list of the users recipe lists
     *
     * @param userId
     * @return Users recipe lists
     */
    List<UserRecipeList> getUserRecipeListsByUserId(int userId);

    /**
     * Returns a user recipe list by it's id
     *
     * @param id
     * @return User recipe list
     */
    UserRecipeList getUserRecipeListByUserRecipeListId(int id);

    /**
     * Adds a user recipe list
     *
     * @param userRecipeList
     * @return created user recipe list
     */
    UserRecipeList addUserRecipeList(UserRecipeList userRecipeList);

    /**
     * Updates a user recipe list
     *
     * @param userRecipeList
     * @return updated user recipe list
     */
    UserRecipeList updateUserRecipeList(UserRecipeList userRecipeList);

    /**
     * Deletes a user recipe list by it's id
     *
     * @param id
     */
    void deleteUserRecipeListByUserRecipeListId(int id);
}
