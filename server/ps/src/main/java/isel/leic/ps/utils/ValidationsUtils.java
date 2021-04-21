package isel.leic.ps.utils;

import isel.leic.ps.components.MessageSourceHolder;
import isel.leic.ps.exceptions.EntityException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

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
    public static void validateUserId(Long id) throws EntityException {
        MessageSource messageSource = MessageSourceHolder.getMessageSource();
        Locale locale = LocaleContextHolder.getLocale();
        if (id == null)
            throw new EntityException("User id is required.", messageSource.getMessage("user_Required", null, locale));
        if (id < RestrictionUtils.USER_ID_MIN)
            throw new EntityException("Invalid user id.", messageSource.getMessage("invalid_User_Id", null, locale));
    }

    /**
     * Valida username
     *
     * @param username username a validar
     * @throws EntityException se o username não for válido
     */
    public static void validateUserUsername(String username) throws EntityException {
        MessageSource messageSource = MessageSourceHolder.getMessageSource();
        Locale locale = LocaleContextHolder.getLocale();
        if (username == null)
            throw new EntityException("Username is required.", messageSource.getMessage("username_Required", null, locale));
        if (username.length() > RestrictionUtils.USER_USERNAME_MAX_LENGTH)
            throw new EntityException(String.format("Invalid Username. Username must contain a maximum of %d characters.", RestrictionUtils.USER_USERNAME_MAX_LENGTH), messageSource.getMessage("invalid_Username", new Object[]{RestrictionUtils.USER_USERNAME_MAX_LENGTH}, locale));
    }

    /**
     * Valida email
     *
     * @param email email a validar
     * @throws EntityException se o email não for válido
     */
    public static void validateUserEmail(String email) throws EntityException {
        MessageSource messageSource = MessageSourceHolder.getMessageSource();
        Locale locale = LocaleContextHolder.getLocale();
        if (email == null)
            throw new EntityException("Email is required.", messageSource.getMessage("email_Required", null, locale));
        if (email.length() > RestrictionUtils.USER_EMAIL_MAX_LENGTH)
            throw new EntityException("Invalid email.", messageSource.getMessage("invalid_Email", null, locale));
        if (!EmailUtils.isStringValidEmail(email))
            throw new EntityException("Invalid email.", messageSource.getMessage("invalid_Email", null, locale));
    }

    /**
     * Valida nome
     *
     * @param name nome a validar
     * @throws EntityException se o nome não for válido
     */
    public static void validateUserName(String name) throws EntityException {
        MessageSource messageSource = MessageSourceHolder.getMessageSource();
        Locale locale = LocaleContextHolder.getLocale();
        if (name == null)
            throw new EntityException("User's name is required.", messageSource.getMessage("user_Name_Required", null, locale));
        if (name.length() > RestrictionUtils.USER_NAME_MAX_LENGTH)
            throw new EntityException(String.format("Invalid name. Name must contain a maximum of %d characters.", RestrictionUtils.USER_NAME_MAX_LENGTH), messageSource.getMessage("invalid_User_Name", new Object[]{RestrictionUtils.USER_NAME_MAX_LENGTH}, locale));
    }

    /**
     * Valida a password
     *
     * @param password password a validar
     * @throws EntityException se a password não for válida
     */
    public static void validateUserPassword(String password) throws EntityException {
        MessageSource messageSource = MessageSourceHolder.getMessageSource();
        Locale locale = LocaleContextHolder.getLocale();
        if (password == null)
            throw new EntityException("Password is required.", messageSource.getMessage("password_Required", null, locale));
        if (password.length() > RestrictionUtils.USER_PASSWORD_MAX_LENGTH)
            throw new EntityException(String.format("Password is too long. Password must contain a maximum of %d characters.", RestrictionUtils.USER_PASSWORD_MAX_LENGTH), messageSource.getMessage("invalid_Password", new Object[]{RestrictionUtils.USER_PASSWORD_MAX_LENGTH}, locale));
    }
}
