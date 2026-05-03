package Utils;

import java.util.regex.Pattern;

/**
 * ValidationUtils - Input validation utilities
 */
public class ValidationUtils {

    // Email regex pattern
    private static final String EMAIL_PATTERN =
            "^[A-Za-z0-9+_.-]+@(.+)$";

    // Username pattern (alphanumeric, 3-20 chars)
    private static final String USERNAME_PATTERN =
            "^[a-zA-Z0-9]{3,20}$";

    // Phone pattern (US format)
    private static final String PHONE_PATTERN =
            "^\\+?[0-9]{10,15}$";

    // URL pattern
    private static final String URL_PATTERN =
            "^(https?://)?([\\da-z.-]+)\\.([a-z.]{2,6})([/\\w .-]*)*/?$";

    private static Pattern emailRegex = Pattern.compile(EMAIL_PATTERN);
    private static Pattern usernameRegex = Pattern.compile(USERNAME_PATTERN);
    private static Pattern phoneRegex = Pattern.compile(PHONE_PATTERN);
    private static Pattern urlRegex = Pattern.compile(URL_PATTERN);

    /**
     * Validate email address
     * @param email Email to validate     * @return true if valid
     */
    public static boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        return emailRegex.matcher(email).matches();
    }

    /**
     * Validate username
     * @param username Username to validate
     * @return true if valid
     */
    public static boolean isValidUsername(String username) {
        if (username == null || username.isEmpty()) {
            return false;
        }
        return usernameRegex.matcher(username).matches();
    }

    /**
     * Validate phone number
     * @param phone Phone to validate
     * @return true if valid
     */
    public static boolean isValidPhone(String phone) {
        if (phone == null || phone.isEmpty()) {
            return false;
        }
        return phoneRegex.matcher(phone).matches();
    }

    /**
     * Validate URL
     * @param url URL to validate
     * @return true if valid
     */
    public static boolean isValidURL(String url) {
        if (url == null || url.isEmpty()) {
            return false;
        }
        return urlRegex.matcher(url).matches();
    }

    /**
     * Validate credit card number (Luhn algorithm)
     * @param cardNumber Credit card number
     * @return true if valid
     */
    public static boolean isValidCreditCard(String cardNumber) {
        if (cardNumber == null) return false;

        // Remove spaces and dashes
        cardNumber = cardNumber.replaceAll("[-\\s]", "");

        if (!cardNumber.matches("\\d+")) return false;

        // Luhn algorithm
        int sum = 0;
        boolean alternate = false;

        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            int digit = Integer.parseInt(cardNumber.substring(i, i + 1));

            if (alternate) {
                digit *= 2;
                if (digit > 9) {
                    digit = (digit % 10) + 1;
                }
            }
            sum += digit;
            alternate = !alternate;
        }

        return (sum % 10 == 0);
    }

    /**
     * Check if string is null or empty
     * @param str String to check
     * @return true if null or empty
     */
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    /**
     * Check if string is within length limits
     * @param str String to check
     * @param min Minimum length
     * @param max Maximum length
     * @return true if within limits
     */
    public static boolean isWithinLength(String str, int min, int max) {
        if (str == null) return false;
        int len = str.length();
        return len >= min && len <= max;
    }

    /**
     * Check if string contains only letters
     * @param str String to check
     * @return true if only letters
     */
    public static boolean isOnlyLetters(String str) {
        if (str == null) return false;
        return str.matches("^[a-zA-Z]+$");
    }

    /**
     * Check if string contains only alphanumeric characters
     * @param str String to check
     * @return true if alphanumeric
     */
    public static boolean isAlphanumeric(String str) {
        if (str == null) return false;
        return str.matches("^[a-zA-Z0-9]+$");
    }

    /**
     * Check if string contains only digits
     * @param str String to check
     * @return true if only digits
     */
    public static boolean isOnlyDigits(String str) {
        if (str == null) return false;
        return str.matches("^\\d+$");
    }

    /**
     * Validate integer range
     * @param value Value to check
     * @param min Minimum value
     * @param max Maximum value
     * @return true if within range
     */
    public static boolean isInRange(int value, int min, int max) {
        return value >= min && value <= max;
    }

    /**
     * Get validation error message for email
     * @param email Email to validate
     * @return Error message or null if valid
     */
    public static String getEmailErrorMessage(String email) {
        if (isNullOrEmpty(email)) {
            return "Email cannot be empty";
        }
        if (!isValidEmail(email)) {
            return "Invalid email format";
        }
        return null;
    }

    /**
     * Get validation error message for username
     * @param username Username to validate
     * @return Error message or null if valid
     */
    public static String getUsernameErrorMessage(String username) {
        if (isNullOrEmpty(username)) {
            return "Username cannot be empty";
        }
        if (username.length() < 3) {
            return "Username must be at least 3 characters";
        }
        if (username.length() > 20) {
            return "Username cannot exceed 20 characters";
        }
        if (!isAlphanumeric(username)) {
            return "Username can only contain letters and numbers";
        }
        return null;
    }
}