package isel.leic.ps.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class for Emails
 */
public class EmailUtils {

    /**
     * Verifies if the email is vali
     *
     * @param email email to validate
     * @return true if @param email is a valid, false otherwise
     */
    public static boolean isStringValidEmail(String email) {
        Matcher matcher = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE).matcher(email);
        return matcher.matches();
    }
}
