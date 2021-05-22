package isel.leic.ps.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class UriBuilderUtils {

    private static String HOST;

    static {
        try {
            HOST = String.format("http://%s:8080", InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException e) {
            HOST = "http://localhost:8080";
        }
    }

    private static final String
            VERSION = "v1",
            INGREDIENTS = "ingredients",
            RECIPES = "recipes",
            LISTS = "lists",
            USERS = "users";

    /**
     * URI Template: http://127.0.0.1:8080/v1
     *
     * @return URI to index page
     */
    public static String buildIndexUri() {
        return String.format("%s/%s", HOST, VERSION);
    }

    /**
     * URI Template: http://127.0.0.1:8081/v1/users/{username}
     *
     * @param username The id of the user
     * @return The specific user
     */
    public static String buildUserUri(String username) {
        return String.format("%s/%s/%s/%s", HOST, VERSION, USERS, username);
    }

    /**
     * URI Template: http://127.0.0.1:8080/v1/users
     *
     * @return The users
     */
    public static String buildUsersUri() {
        return String.format("%s/%s/%s", HOST, VERSION, USERS);
    }

    /**
     * URI Template: http://127.0.0.1:8081/v1/users/{username}/lists
     *
     * @return URI to user recipe lists
     */
    public static String buildUserRecipeListsUri(String username) {
        return String.format("%s/%s/%s/%s/%s", HOST, VERSION, USERS, username, LISTS);
    }

    /**
     * URI Template: http://127.0.0.1:8081/v1/users/{username}/lists/{idUrl}
     *
     * @return URI to user recipe list
     */
    public static String buildUserRecipeListUri(String username, int idUrl) {
        return String.format("%s/%s/%s/%s/%s/%s", HOST, VERSION, USERS, username, LISTS, idUrl);
    }

    /**
     * URI Template: http://127.0.0.1:8081/v1/users/{username}/lists/{idUrl}/recipes
     *
     * @return URI to recipes
     */
    public static String buildRecipesUri(String username, int idUrl) {
        return String.format("%s/%s/%s/%s/%s/%s/%s", HOST, VERSION, USERS, username, LISTS, idUrl, RECIPES);
    }

    /**
     * URI Template: http://127.0.0.1:8081/v1/users/{username}/lists/{idUrl}/recipes/{recipeId}
     *
     * @return URI to recipe
     */
    public static String buildRecipeUri(String username, int idUrl, int recipeId) {
        return String.format("%s/%s/%s/%s/%s/%s/%s/%s", HOST, VERSION, USERS, username, LISTS, idUrl, RECIPES, recipeId);
    }

    /**
     * URI Template: http://127.0.0.1:8081/v1/users/{username}/lists/{idUrl}/recipes/{recipeId}/ingredients
     *
     * @return URI to recipe ingredients details
     */
    public static String buildIngredientsDetailsUri(String username, int idUrl, int recipeId) {
        return String.format("%s/%s/%s/%s/%s/%s/%s/%s/%s", HOST, VERSION, USERS, username, LISTS, idUrl, RECIPES, recipeId, INGREDIENTS);
    }

    /**
     * URI Template: http://127.0.0.1:8081/v1/users/{username}/lists/{idUrl}/recipes/{recipeId}/ingredients/{ingredientDetailsId}
     *
     * @return URI to recipe ingredient details
     */
    public static String buildIngredientDetailsUri(String username, int idUrl, int recipeId, int ingredientDetailsId) {
        return String.format("%s/%s/%s/%s/%s/%s/%s/%s/%s/%s", HOST, VERSION, USERS, username, LISTS, idUrl, RECIPES, recipeId, INGREDIENTS, ingredientDetailsId);
    }
}
