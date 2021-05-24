package isel.leic.ps.utils;

import isel.leic.ps.components.MessageSourceHolder;
import isel.leic.ps.exceptions.EntityException;
import org.springframework.context.MessageSource;

import java.util.Locale;

public class ValidationsUtils {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////                                            User                                                            ////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Validates the id
     *
     * @param id id to validate
     * @throws EntityException if id is not valid
     */
    public static void validateUserId(Integer id) throws EntityException {
        MessageSource messageSource = MessageSourceHolder.getMessageSource();
        if (id == null)
            throw new EntityException(messageSource.getMessage("user_Required", null, Locale.ENGLISH));
        if (id < RestrictionUtils.USER_ID_MIN)
            throw new EntityException(messageSource.getMessage("invalid_User_Id", null, Locale.ENGLISH));
    }

    /**
     * Validates username
     *
     * @param username username to validate
     * @throws EntityException if username isn't valid
     */
    public static void validateUserUsername(String username) throws EntityException {
        MessageSource messageSource = MessageSourceHolder.getMessageSource();
        if (username == null)
            throw new EntityException(messageSource.getMessage("username_Required", null, Locale.ENGLISH));
        if (username.length() > RestrictionUtils.USER_USERNAME_MAX_LENGTH)
            throw new EntityException(messageSource.getMessage("invalid_Username", new Object[]{RestrictionUtils.USER_USERNAME_MAX_LENGTH}, Locale.ENGLISH));
    }

    /**
     * Validates email
     *
     * @param email email to validate
     * @throws EntityException if email isn't valid
     */
    public static void validateUserEmail(String email) throws EntityException {
        MessageSource messageSource = MessageSourceHolder.getMessageSource();
        if (email == null)
            throw new EntityException(messageSource.getMessage("email_Required", null, Locale.ENGLISH));
        if (email.length() > RestrictionUtils.USER_EMAIL_MAX_LENGTH)
            throw new EntityException(messageSource.getMessage("invalid_Email", null, Locale.ENGLISH));
        if (!EmailUtils.isStringValidEmail(email))
            throw new EntityException(messageSource.getMessage("invalid_Email", null, Locale.ENGLISH));
    }

    /**
     * Validate name
     *
     * @param name nome to validate
     * @throws EntityException if name isn't valid
     */
    public static void validateUserName(String name) throws EntityException {
        MessageSource messageSource = MessageSourceHolder.getMessageSource();
        if (name == null)
            throw new EntityException(messageSource.getMessage("user_Name_Required", null, Locale.ENGLISH));
        if (name.length() > RestrictionUtils.USER_NAME_MAX_LENGTH)
            throw new EntityException(messageSource.getMessage("invalid_User_Name", new Object[]{RestrictionUtils.USER_NAME_MAX_LENGTH}, Locale.ENGLISH));
    }

    /**
     * Validate password
     *
     * @param password password to validate
     * @throws EntityException if password isn't valid
     */
    public static void validateUserPassword(String password) throws EntityException {
        MessageSource messageSource = MessageSourceHolder.getMessageSource();
        if (password == null)
            throw new EntityException(messageSource.getMessage("password_Required", null, Locale.ENGLISH));
        if (password.length() > RestrictionUtils.USER_PASSWORD_MAX_LENGTH)
            throw new EntityException(messageSource.getMessage("invalid_Password", new Object[]{RestrictionUtils.USER_PASSWORD_MAX_LENGTH}, Locale.ENGLISH));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////                                        User Recipe List                                                    ////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Validates the idUrl
     *
     * @param idUrl id to validate
     * @throws EntityException if idUrl is not valid
     */
    public static void validateUserRecipeListId(Integer idUrl) throws EntityException {
        MessageSource messageSource = MessageSourceHolder.getMessageSource();
        if (idUrl == null)
            throw new EntityException(messageSource.getMessage("user_recipe_list_Required", null, Locale.ENGLISH));
        if (idUrl < RestrictionUtils.USER_RECIPE_LIST_ID_MIN)
            throw new EntityException(messageSource.getMessage("invalid_User_Recipe_List_Id", null, Locale.ENGLISH));
    }

    /**
     * Validates list name
     *
     * @param listName name of the list to validate
     * @throws EntityException if list name isn't valid
     */
    public static void validateUserRecipeListName(String listName) throws EntityException {
        MessageSource messageSource = MessageSourceHolder.getMessageSource();
        if (listName == null)
            throw new EntityException(messageSource.getMessage("list_name_Required", null, Locale.ENGLISH));
        if (listName.length() > RestrictionUtils.URL_LIST_NAME_MAX_LENGTH)
            throw new EntityException(messageSource.getMessage("invalid_list_name", new Object[]{RestrictionUtils.URL_LIST_NAME_MAX_LENGTH}, Locale.ENGLISH));
    }

    /**
     * Validate visibility
     *
     * @param visibility visibility to validate
     * @throws EntityException if visibility isn't valid
     */
    public static void validateUserRecipeListVisibility(String visibility) throws EntityException {
        MessageSource messageSource = MessageSourceHolder.getMessageSource();
        if (visibility != null) {
            if (visibility.length() > RestrictionUtils.URL_VISIBILITY_MAX_LENGTH)
                throw new EntityException(messageSource.getMessage("invalid_visibility_length", new Object[]{RestrictionUtils.URL_VISIBILITY_MAX_LENGTH}, Locale.ENGLISH));
            if (!visibility.equalsIgnoreCase(messageSource.getMessage("visibility_private", null, Locale.ENGLISH)) && !visibility.equalsIgnoreCase(messageSource.getMessage("visibility_public", null, Locale.ENGLISH)))
                throw new EntityException(messageSource.getMessage("invalid_visibility_value", null, Locale.ENGLISH));
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////                                            Recipe                                                          ////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Validates the idUrl
     *
     * @param id id to validate
     * @throws EntityException if id is not valid
     */
    public static void validateRecipeId(Integer id) throws EntityException {
        MessageSource messageSource = MessageSourceHolder.getMessageSource();
        if (id == null)
            throw new EntityException(messageSource.getMessage("recipe_Required", null, Locale.ENGLISH));
        if (id < RestrictionUtils.RECIPE_ID_MIN)
            throw new EntityException(messageSource.getMessage("invalid_recipe_Id", null, Locale.ENGLISH));
    }

    /**
     * Validates recipe name
     *
     * @param name name of the recipe to validate
     * @throws EntityException if name isn't valid
     */
    public static void validateRecipeName(String name) throws EntityException {
        MessageSource messageSource = MessageSourceHolder.getMessageSource();
        if (name == null)
            throw new EntityException(messageSource.getMessage("recipe_name_Required", null, Locale.ENGLISH));
        if (name.length() > RestrictionUtils.RECIPE_NAME_MAX_LENGTH)
            throw new EntityException(messageSource.getMessage("invalid_recipe_name", new Object[]{RestrictionUtils.RECIPE_NAME_MAX_LENGTH}, Locale.ENGLISH));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////                                       Ingredient Details                                                   ////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Validates the ingredient detail id
     *
     * @param id id to validate
     * @throws EntityException if id is not valid
     */
    public static void validateIngredientDetailsId(Integer id) throws EntityException {
        MessageSource messageSource = MessageSourceHolder.getMessageSource();
        if (id == null)
            throw new EntityException(messageSource.getMessage("ingredient_detail_Required", null, Locale.ENGLISH));
        if (id < RestrictionUtils.INGREDIENT_DETAILS_ID_MIN)
            throw new EntityException(messageSource.getMessage("invalid_ingredient_detail_Id", null, Locale.ENGLISH));
    }

    /**
     * Validates ingredient details name
     *
     * @param name name of the ingredient to validate
     * @throws EntityException if name isn't valid
     */
    public static void validateIngredientDetailsName(String name) throws EntityException {
        MessageSource messageSource = MessageSourceHolder.getMessageSource();
        if (name == null)
            throw new EntityException(messageSource.getMessage("ingredient_detail_name_Required", null, Locale.ENGLISH));
        if (name.length() > RestrictionUtils.INGREDIENT_DETAILS_INGREDIENT_NAME_MAX_LENGTH)
            throw new EntityException(messageSource.getMessage("invalid_ingredient_detail_name", new Object[]{RestrictionUtils.INGREDIENT_DETAILS_INGREDIENT_NAME_MAX_LENGTH}, Locale.ENGLISH));
    }

    /**
     * Validates ingredient details aisle
     *
     * @param aisle aisle of the ingredient to validate
     * @throws EntityException if aisle isn't valid
     */
    public static void validateIngredientDetailsAisle(String aisle) throws EntityException {
        MessageSource messageSource = MessageSourceHolder.getMessageSource();
        if (aisle.length() > RestrictionUtils.INGREDIENT_DETAILS_AISLE_MAX_LENGTH)
            throw new EntityException(messageSource.getMessage("invalid_ingredient_detail_aisle", new Object[]{RestrictionUtils.INGREDIENT_DETAILS_AISLE_MAX_LENGTH}, Locale.ENGLISH));
    }

    /**
     * Validates ingredient details name
     *
     * @param unit unit of the ingredient to validate
     * @throws EntityException if name isn't valid
     */
    public static void validateIngredientDetailsUnit(String unit) throws EntityException {
        MessageSource messageSource = MessageSourceHolder.getMessageSource();
        if (unit == null)
            throw new EntityException(messageSource.getMessage("ingredient_detail_unit_Required", null, Locale.ENGLISH));
        if (unit.length() > RestrictionUtils.INGREDIENT_DETAILS_UNIT_MAX_LENGTH)
            throw new EntityException(messageSource.getMessage("invalid_ingredient_detail_unit", new Object[]{RestrictionUtils.INGREDIENT_DETAILS_UNIT_MAX_LENGTH}, Locale.ENGLISH));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////                                             API                                                            ////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Validates search recipes query
     *
     * @param query query of search recipes request to validate
     * @throws EntityException if query isn't valid
     */
    public static void validateSearchRecipesQuery(String query) throws EntityException {
        MessageSource messageSource = MessageSourceHolder.getMessageSource();
        if (query == null)
            throw new EntityException(messageSource.getMessage("search_recipes_query_Required", null, Locale.ENGLISH));
    }
}
