package Utils;

import java.util.Random;
import java.util.UUID;

/**
 * DataGenerator - Random data generation utilities
 */
public class DataGenerator {

    private static Random random = new Random();

    private static final String[] FIRST_NAMES = {
            "Alice", "Bob", "Charlie", "Diana", "Edward", "Fiona", "George", "Hannah",
            "Ian", "Julia", "Kevin", "Laura", "Michael", "Nina", "Oliver", "Patricia"
    };

    private static final String[] LAST_NAMES = {
            "Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis",
            "Rodriguez", "Martinez", "Hernandez", "Lopez", "Gonzalez", "Wilson", "Anderson"
    };

    private static final String[] DOMAINS = {
            "example.com", "test.com", "demo.com", "company.com", "email.com"
    };

    /**
     * Generate random integer between min and max (inclusive)
     * @param min Minimum value
     * @param max Maximum value
     * @return Random integer
     */
    public static int randomInt(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    /**
     * Generate random double between min and max
     * @param min Minimum value
     * @param max Maximum value
     * @return Random double
     */
    public static double randomDouble(double min, double max) {
        return min + (max - min) * random.nextDouble();
    }

    /**
     * Generate random boolean
     * @return Random boolean
     */
    public static boolean randomBoolean() {
        return random.nextBoolean();
    }

    /**
     * Generate random string of specified length
     * @param length Length of string
     * @return Random string
     */
    public static String randomString(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }

    /**
     * Generate random UUID
     * @return UUID string
     */
    public static String randomUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * Generate random first name
     * @return Random first name
     */
    public static String randomFirstName() {
        return FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
    }

    /**
     * Generate random last name
     * @return Random last name
     */
    public static String randomLastName() {
        return LAST_NAMES[random.nextInt(LAST_NAMES.length)];
    }

    /**
     * Generate random full name
     * @return Random full name
     */
    public static String randomFullName() {
        return randomFirstName() + " " + randomLastName();
    }

    /**
     * Generate random email
     * @return Random email
     */
    public static String randomEmail() {
        return randomFirstName().toLowerCase() + "." +
                randomLastName().toLowerCase() + "@" +
                DOMAINS[random.nextInt(DOMAINS.length)];
    }

    /**
     * Generate random phone number
     * @return Random phone number
     */
    public static String randomPhoneNumber() {
        return String.format("%03d-%03d-%04d",
                random.nextInt(900) + 100,
                random.nextInt(900) + 100,
                random.nextInt(10000));
    }

    /**
     * Generate random IP address
     * @return Random IP
     */
    public static String randomIP() {
        return random.nextInt(256) + "." +
                random.nextInt(256) + "." +
                random.nextInt(256) + "." +
                random.nextInt(256);
    }

    /**
     * Generate random MAC address
     * @return Random MAC
     */
    public static String randomMAC() {
        byte[] mac = new byte[6];
        random.nextBytes(mac);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mac.length; i++) {
            sb.append(String.format("%02X", mac[i] & 0xFF));
            if (i < mac.length - 1) sb.append(":");
        }
        return sb.toString();
    }

    /**
     * Generate random user agent string
     * @return Random user agent
     */
    public static String randomUserAgent() {
        String[] browsers = {"Chrome", "Firefox", "Safari", "Edge"};
        String[] os = {"Windows", "Mac OS", "Linux", "Android", "iOS"};

        String browser = browsers[random.nextInt(browsers.length)];
        String osName = os[random.nextInt(os.length)];
        int version = 90 + random.nextInt(30);

        return String.format("Mozilla/5.0 (%s) AppleWebKit/537.36 (KHTML, like Gecko) %s/%d Safari/537.36",
                osName, browser, version);
    }

    /**
     * Generate random item from array
     * @param array Array of items
     * @return Random item
     */
    public static <T> T randomFromArray(T[] array) {
        if (array == null || array.length == 0) return null;
        return array[random.nextInt(array.length)];
    }

    /**
     * Generate random password (8-16 characters)
     * @return Random password
     */
    public static String randomPassword() {
        int length = randomInt(8, 16);
        return PasswordUtils.generateStrongPassword(length);
    }
}