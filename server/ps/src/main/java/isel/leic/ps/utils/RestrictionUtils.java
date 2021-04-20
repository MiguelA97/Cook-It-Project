package isel.leic.ps.utils;

/**
 * Utility class with domain restrictions
 */
public class RestrictionUtils {

    // USERS
    public static final short USER_USERNAME_MAX_LENGTH = 25;
    public static final short USER_PASSWORD_MAX_LENGTH = 25;
    public static final short USER_EMAIL_MAX_LENGTH = 320;
    public static final short USER_NAME_MAX_LENGTH = 100;

    //USER_RECIPE_LIST
    public static final short URL_LIST_NAME_MAX_LENGTH = 25;
    public static final short URL_VISIBILITY_MAX_LENGTH = 7;

    //RECIPE
    public static final short RECIPE_NAME_MAX_LENGTH = 100;

    //INGREDIENT_DETAILS
    public static final short INGREDIENT_DETAILS_AISLE_MAX_LENGTH = 30;
    public static final short INGREDIENT_DETAILS_INGREDIENT_NAME_MAX_LENGTH = 50;
    public static final short INGREDIENT_DETAILS_UNIT_MAX_LENGTH = 20;
}
