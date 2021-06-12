package isel.leic.ps.utils;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Utility class with domain restrictions
 */
public class RestrictionUtils {

    // USERS
    public static final short USER_USERNAME_MAX_LENGTH = 25;
    public static final short USER_PASSWORD_MAX_LENGTH = 320;
    public static final short USER_EMAIL_MAX_LENGTH = 320;
    public static final short USER_NAME_MAX_LENGTH = 100;
    public static final short USER_ID_MIN = 1;

    //USER_RECIPE_LIST
    public static final short URL_LIST_NAME_MAX_LENGTH = 25;
    public static final short URL_VISIBILITY_MAX_LENGTH = 7;
    public static final short USER_RECIPE_LIST_ID_MIN = 1;

    //RECIPE
    public static final short RECIPE_NAME_MAX_LENGTH = 100;
    public static final short RECIPE_ID_MIN = 1;

    //INGREDIENT_DETAILS
    public static final short INGREDIENT_DETAILS_AISLE_MAX_LENGTH = 30;
    public static final short INGREDIENT_DETAILS_INGREDIENT_NAME_MAX_LENGTH = 50;
    public static final short INGREDIENT_DETAILS_UNIT_MAX_LENGTH = 20;
    public static final short INGREDIENT_DETAILS_ID_MIN = 1;

    //API
    public static final short API_RETURNED_RECIPES_MAX_NUMBER = 100;
    public static final short API_RETURNED_RECIPES_MIN_NUMBER = 1;
    public static final short API_RETURNED_RECIPES_MAX_OFFSET = 900;
    public static final short API_RETURNED_RECIPES_MIN_OFFSET = 0;
    public static final LinkedList<String> API_ACCEPTED_DIETS = new LinkedList<>(Arrays.asList("pescetarian", "lacto vegetarian", "ovo vegetarian", "vegan", "vegetarian"));
    public static final LinkedList<String> API_ACCEPTED_INTOLERANCES = new LinkedList<>(Arrays.asList("dairy", "egg", "gluten", "peanut", "sesame", "seafood", "shellfish", "soy", "sulfite", "tree nut", "wheat"));
    public static final LinkedList<String> API_ACCEPTED_TYPES = new LinkedList<>(Arrays.asList("main course", "side dish", "dessert", "appetizer", "salad", "bread", "breakfast", "soup", "beverage", "sauce", "drink"));
    public static final LinkedList<String> API_ACCEPTED_CUISINES = new LinkedList<>(Arrays.asList("african", "chinese", "japanese", "korean", "vietnamese", "thai", "indian", "british", "irish", "french", "italian", "mexican", "spanish", "middle eastern", "jewish", "american", "cajun", "southern", "greek", "german", "nordic", "eastern european", "caribbean", "latin american"));
}