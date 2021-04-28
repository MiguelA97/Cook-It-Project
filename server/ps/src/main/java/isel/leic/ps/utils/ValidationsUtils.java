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
    public static void validateUserId(Long id) throws EntityException {
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
}
