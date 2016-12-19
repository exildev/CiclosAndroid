package com.nativapps.arpia.util;

import java.util.regex.Pattern;

/**
 * The <strong>TextUtil</strong> class provided static methods to validates
 * string values.
 *
 * @author Luis Alfonso Lenes Salas <luislenes02@gmail.com>
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 *
 * @version 1.0.1
 */
public class TextUtil {

    /**
     * Regular expression to validate emails.
     */
    private static final String VALID_EMAIL_REGEX
            = "^([\\w\\.-]+)@((?:[\\w]+\\.)+)([a-zA-Z]{2,4})$";

    /**
     * Regular expression to validate number phones.
     */
    private static final String VALID_PHONE_REGEX
            = "^(\\+{1}\\d{1,3})?(\\d{0,3})?(\\d{7})$";

    /**
     * Regular expression to validate username.
     */
    private static final String VALID_USERNAME_REGEX
            = "^(?!\\d*$)[_a-z\\d]{3,12}$$";

    /**
     * Don't let anyone instantiate this class.
     */
    private TextUtil() { }

    /**
     * Returns true if the string is null or 0-length.
     *
     * @param value the string to be examined.
     * @return {@code true}, if {@code value} is empty.
     */
    public static boolean isEmpty(String value) {
        return value == null || value.trim().length() == 0;
    }

    /**
     * Returns true if the string value is a email.
     *
     * @param email the email to be examined.
     * @return {@code true}, if {@code email} is valid.
     */
    public static boolean isEmail(String email) {
        return Pattern.matches(VALID_EMAIL_REGEX, email);
    }

    /**
     * Returns true if the string value is a number phone.
     *
     * @param phone the phone to be examined.
     * @return {@code true}, if {@code phone} is valid.
     */
    public static boolean isPhone(String phone) {
        return Pattern.matches(VALID_PHONE_REGEX, phone);
    }

    /**
     * Returns true if the string value is valid to username.
     * <p>
     * The usernames that are valid have the following rules:
     * <ul>
     * <li>This can have alphanumeric characters.
     * <li>This may have the "_" symbol.
     * <lI>This must have 3 to 12 characters.
     * <li>This can't be just numbers.
     * </ul>
     *
     * @param username the string to be examined.
     * @return {@code true}, if {@code username} is valid.
     */
    public static boolean isUsername(String username) {
        return Pattern.matches(VALID_USERNAME_REGEX, username.toLowerCase());
    }

}
