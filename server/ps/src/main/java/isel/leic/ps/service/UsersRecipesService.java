package isel.leic.ps.service;

import isel.leic.ps.model.UsersRecipes;

public interface UsersRecipesService {

    /**
     * Returns a User Recipes by it's user id and user recipe list id
     *
     * @param userId
     * @param userRecipeListId
     * @return UserRecipes
     */
    UsersRecipes getUserRecipesByUserIdAndUserRecipeListId(int userId, int userRecipeListId);

    /**
     * Adds a user recipe
     *
     * @param usersRecipes
     * @return User Recipes
     */
    UsersRecipes addUsersRecipes(UsersRecipes usersRecipes);

    /**
     * Deletes a user recipes by it's recipe id
     *
     * @param recipeId
     */
    void deleteUsersRecipesByRecipeId(int recipeId);

    /**
     * Deletes a user recipes by it's user id
     *
     * @param userId
     */
    void deleteUsersRecipesByUserId(int userId);

    /**
     * Deletes a user recipes by it's user recipe list id
     *
     * @param userRecipeListId
     */
    void deleteUsersRecipesByUserRecipeListId(int userRecipeListId);
}
