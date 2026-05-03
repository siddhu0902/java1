package Utils;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * PasswordUtils - Password generation and strength checking utilities
 * Demonstrates utility class pattern
 */
public class PasswordUtils {

    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL = "!@#$%^&*()_+-=[]{}|;:,.<>?";
    private static final String ALL_CHARS = LOWERCASE + UPPERCASE + DIGITS + SPECIAL;

    private static SecureRandom random = new SecureRandom();

    /**
     * Generate a strong random password
     * @param length Desired password length
     * @return Generated password
     */
    public static String generateStrongPassword(int length) {
        if (length < 8) {
            length = 8;
        }

        StringBuilder password = new StringBuilder(length);

        // Ensure at least one character from each category
        password.append(LOWERCASE.charAt(random.nextInt(LOWERCASE.length())));
        password.append(UPPERCASE.charAt(random.nextInt(UPPERCASE.length())));
        password.append(DIGITS.charAt(random.nextInt(DIGITS.length())));
        password.append(SPECIAL.charAt(random.nextInt(SPECIAL.length())));

        // Fill the rest
        for (int i = 4; i < length; i++) {
            password.append(ALL_CHARS.charAt(random.nextInt(ALL_CHARS.length())));
        }

        // Shuffle the password
        return shuffleString(password.toString());
    }

    /**
     * Generate a memorable password (words + numbers)
     * @return Memorable password
     */
    public static String generateMemorablePassword() {
        String[] words = {"Blue", "Tiger", "Summer", "Ocean", "Forest", "Mountain",
                "Eagle", "Phoenix", "Dragon", "Star", "Cloud", "Thunder"};
        String[] separators = {"!", "@", "#", "$", "%"};

        String word1 = words[random.nextInt(words.length)];
        String word2 = words[random.nextInt(words.length)];
        String separator = separators[random.nextInt(separators.length)];
        int number = random.nextInt(1000);

        return word1 + separator + word2 + number;
    }

    /**
     * Generate a PIN code
     * @param length Length of PIN (4-8)
     * @return PIN code
     */
    public static String generatePin(int length) {
        if (length < 4) length = 4;
        if (length > 8) length = 8;

        StringBuilder pin = new StringBuilder();
        for (int i = 0; i < length; i++) {
            pin.append(random.nextInt(10));
        }
        return pin.toString();
    }

    /**
     * Check password strength
     * @param password The password to check
     * @return Strength level (0-4): 0=Very Weak, 1=Weak, 2=Medium, 3=Strong, 4=Very Strong
     */
    public static int checkStrength(String password) {
        if (password == null || password.isEmpty()) {
            return 0;
        }

        int score = 0;

        // Length check
        if (password.length() >= 8) score++;
        if (password.length() >= 12) score++;
        if (password.length() >= 16) score++;

        // Character variety checks
        boolean hasLower = false, hasUpper = false, hasDigit = false, hasSpecial = false;

        for (char c : password.toCharArray()) {
            if (Character.isLowerCase(c)) hasLower = true;
            else if (Character.isUpperCase(c)) hasUpper = true;
            else if (Character.isDigit(c)) hasDigit = true;
            else hasSpecial = true;
        }

        if (hasLower) score++;
        if (hasUpper) score++;
        if (hasDigit) score++;
        if (hasSpecial) score++;

        // Bonus for mixed case
        if (hasLower && hasUpper) score++;

        // Normalize score to 0-4
        if (score <= 2) return 0;
        if (score <= 4) return 1;
        if (score <= 6) return 2;
        if (score <= 8) return 3;
        return 4;
    }

    /**
     * Get password strength as string
     * @param password The password
     * @return Strength description
     */
    public static String getStrengthString(String password) {
        int strength = checkStrength(password);
        switch (strength) {
            case 0: return "VERY WEAK";
            case 1: return "WEAK";
            case 2: return "MEDIUM";
            case 3: return "STRONG";
            case 4: return "VERY STRONG";
            default: return "UNKNOWN";
        }
    }

    /**
     * Get detailed password strength analysis
     * @param password The password
     * @return Analysis string
     */
    public static String getDetailedAnalysis(String password) {
        StringBuilder analysis = new StringBuilder();

        analysis.append("\n┌─────────────────────────────────────────────────────────┐");
        analysis.append("\n│ PASSWORD STRENGTH ANALYSIS                              │");
        analysis.append("\n├─────────────────────────────────────────────────────────┤");
        analysis.append("\n│ Password: ").append(maskPassword(password));
        analysis.append("\n│ Length: ").append(password.length()).append(" characters");
        analysis.append("\n│ Strength: ").append(getStrengthString(password));
        analysis.append("\n├─────────────────────────────────────────────────────────┤");

        // Character checks
        boolean hasLower = false, hasUpper = false, hasDigit = false, hasSpecial = false;
        for (char c : password.toCharArray()) {
            if (Character.isLowerCase(c)) hasLower = true;
            else if (Character.isUpperCase(c)) hasUpper = true;
            else if (Character.isDigit(c)) hasDigit = true;
            else hasSpecial = true;
        }

        analysis.append("\n│ Lowercase:    ").append(hasLower ? "✓" : "✗");
        analysis.append("\n│ Uppercase:    ").append(hasUpper ? "✓" : "✗");
        analysis.append("\n│ Digits:       ").append(hasDigit ? "✓" : "✗");
        analysis.append("\n│ Special chars:").append(hasSpecial ? "✓" : "✗");

        // Recommendations
        analysis.append("\n├─────────────────────────────────────────────────────────┤");
        analysis.append("\n│ RECOMMENDATIONS:                                        │");

        if (password.length() < 8) {
            analysis.append("\n│ • Use at least 8 characters                          │");
        }
        if (!hasLower) {
            analysis.append("\n│ • Add lowercase letters                              │");
        }
        if (!hasUpper) {
            analysis.append("\n│ • Add uppercase letters                              │");
        }
        if (!hasDigit) {
            analysis.append("\n│ • Add numbers                                         │");
        }
        if (!hasSpecial) {
            analysis.append("\n│ • Add special characters (!@#$% etc.)                │");
        }

        if (hasLower && hasUpper && hasDigit && hasSpecial && password.length() >= 12) {
            analysis.append("\n│ ✓ Password is strong!                                 │");
        }

        analysis.append("\n└─────────────────────────────────────────────────────────┘");

        return analysis.toString();
    }

    /**
     * Check if password is strong enough
     * @param password The password
     * @return true if strong enough (strength >= 2)
     */
    public static boolean isStrongPassword(String password) {
        return checkStrength(password) >= 2;
    }

    /**
     * Check if password is very strong
     * @param password The password
     * @return true if very strong (strength >= 3)
     */
    public static boolean isVeryStrongPassword(String password) {
        return checkStrength(password) >= 3;
    }

    /**
     * Estimate crack time for a password
     * @param password The password
     * @return Estimated crack time description
     */
    public static String estimateCrackTime(String password) {
        int strength = checkStrength(password);

        switch (strength) {
            case 0: return "Instant - milliseconds";
            case 1: return "Seconds to minutes";
            case 2: return "Hours to days";
            case 3: return "Years to decades";
            case 4: return "Centuries or more";
            default: return "Unknown";
        }
    }

    /**
     * Mask password for display (show only first and last character)
     * @param password The password
     * @return Masked password
     */
    public static String maskPassword(String password) {
        if (password == null || password.length() <= 2) {
            return "***";
        }
        return password.charAt(0) + "***" + password.charAt(password.length() - 1);
    }

    /**
     * Shuffle a string
     * @param input Input string
     * @return Shuffled string
     */
    private static String shuffleString(String input) {
        char[] chars = input.toCharArray();
        for (int i = chars.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
        }
        return new String(chars);
    }

    /**
     * Generate common password list (for demonstration)
     * @return List of common weak passwords
     */
    public static List<String> getCommonWeakPasswords() {
        List<String> weakPasswords = new ArrayList<>();
        weakPasswords.add("password");
        weakPasswords.add("123456");
        weakPasswords.add("qwerty");
        weakPasswords.add("admin");
        weakPasswords.add("letmein");
        weakPasswords.add("welcome");
        weakPasswords.add("monkey");
        weakPasswords.add("dragon");
        weakPasswords.add("sunshine");
        weakPasswords.add("iloveyou");
        return weakPasswords;
    }

    /**
     * Check if password is commonly used (weak)
     * @param password The password
     * @return true if common weak password
     */
    public static boolean isCommonWeakPassword(String password) {
        return getCommonWeakPasswords().contains(password.toLowerCase());
    }
}