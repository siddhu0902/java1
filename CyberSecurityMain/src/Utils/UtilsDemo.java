package Utils;

/**
 * UtilsDemo - Demonstration class for Utils package
 */
public class UtilsDemo {

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║                  UTILS PACKAGE DEMONSTRATION             ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝\n");

        // Test 1: PasswordUtils
        System.out.println("=".repeat(60));
        System.out.println("TEST CASE 1: Password Utilities");
        System.out.println("=".repeat(60));

        System.out.println("\nGenerated Strong Password: " + PasswordUtils.generateStrongPassword(12));
        System.out.println("Generated Memorable Password: " + PasswordUtils.generateMemorablePassword());
        System.out.println("Generated PIN: " + PasswordUtils.generatePin(6));

        // Test password strength
        String[] testPasswords = {"12345", "password", "Password123", "Str0ng!P@ssw0rd"};
        for (String pwd : testPasswords) {
            System.out.println("\n" + PasswordUtils.getDetailedAnalysis(pwd));
            System.out.println("Estimated crack time: " + PasswordUtils.estimateCrackTime(pwd));
        }

        // Test 2: NetworkUtils
        System.out.println("\n" + "=".repeat(60));
        System.out.println("TEST CASE 2: Network Utilities");
        System.out.println("=".repeat(60));

        String testIP = "192.168.1.100";
        System.out.println("\nValid IP " + testIP + ": " + NetworkUtils.isValidIP(testIP));
        System.out.println("Is private: " + NetworkUtils.isPrivateIP(testIP));
        System.out.println("Random IP: " + NetworkUtils.generateRandomIPv4());
        System.out.println("Private IP: " + NetworkUtils.generatePrivateIPv4());
        System.out.println("Random MAC: " + NetworkUtils.generateRandomMAC());
        System.out.println("Port 80 service: " + NetworkUtils.getServiceName(80));
        System.out.println("CIDR /24 mask: " + NetworkUtils.cidrToSubnetMask(24));

        System.out.println(NetworkUtils.simulateTraffic(100, "192.168.1.10", "10.0.0.5"));

        // Test 3: EncryptionUtils
        System.out.println("\n" + "=".repeat(60));
        System.out.println("TEST CASE 3: Encryption Utilities");
        System.out.println("=".repeat(60));

        String plaintext = "Hello World!";
        System.out.println("\nOriginal: " + plaintext);
        System.out.println("Caesar (shift 3): " + EncryptionUtils.caesarEncrypt(plaintext, 3));
        System.out.println("XOR (key 'K'): " + EncryptionUtils.xorEncrypt(plaintext, 'K'));
        System.out.println("Base64: " + EncryptionUtils.toBase64(plaintext));
        System.out.println("ROT13: " + EncryptionUtils.rot13(plaintext));
        System.out.println("Simple hash: " + EncryptionUtils.simpleHash(plaintext));

        // Test 4: TimeUtils
        System.out.println("\n" + "=".repeat(60));
        System.out.println("TEST CASE 4: Time Utilities");
        System.out.println("=".repeat(60));

        System.out.println("\nCurrent timestamp: " + TimeUtils.getCurrentTimestamp());
        System.out.println("File timestamp: " + TimeUtils.getFileTimestamp());
        System.out.println("Log timestamp: " + TimeUtils.getLogTimestamp());
        System.out.println("Time of day: " + TimeUtils.getTimeOfDay());
        System.out.println("Business hours: " + TimeUtils.isBusinessHours());
        System.out.println("Format 150000ms: " + TimeUtils.formatDuration(150000));

        // Test 5: ValidationUtils
        System.out.println("\n" + "=".repeat(60));
        System.out.println("TEST CASE 5: Validation Utilities");
        System.out.println("=".repeat(60));

        System.out.println("\nEmail 'test@example.com': " + ValidationUtils.isValidEmail("test@example.com"));
        System.out.println("Email 'invalid': " + ValidationUtils.isValidEmail("invalid"));
        System.out.println("Username 'john_123': " + ValidationUtils.isValidUsername("john_123"));
        System.out.println("Credit card '4111111111111111': " + ValidationUtils.isValidCreditCard("4111111111111111"));
        System.out.println("Is digits only '12345': " + ValidationUtils.isOnlyDigits("12345"));

        // Test 6: DataGenerator
        System.out.println("\n" + "=".repeat(60));
        System.out.println("TEST CASE 6: Data Generator");
        System.out.println("=".repeat(60));

        System.out.println("\nRandom name: " + DataGenerator.randomFullName());
        System.out.println("Random email: " + DataGenerator.randomEmail());
        System.out.println("Random phone: " + DataGenerator.randomPhoneNumber());
        System.out.println("Random UUID: " + DataGenerator.randomUUID());
        System.out.println("Random string: " + DataGenerator.randomString(10));
        System.out.println("Random password: " + DataGenerator.randomPassword());
        System.out.println("Random user agent: " + DataGenerator.randomUserAgent());

        // Test 7: FileUtils (simulated - no actual file operations in demo)
        System.out.println("\n" + "=".repeat(60));
        System.out.println("TEST CASE 7: File Utilities (Info only)");
        System.out.println("=".repeat(60));

        System.out.println("\nFileUtils provides:");
        System.out.println("  • readFile / writeToFile");
        System.out.println("  • copyFile / moveFile / deleteFile");
        System.out.println("  • getFileSize / getFileExtension");
        System.out.println("  • createDirectory / listFiles");

        // Summary
        System.out.println("\n╔══════════════════════════════════════════════════════════╗");
        System.out.println("║                    DEMO COMPLETED!                        ║");
        System.out.println("║                                                          ║");
        System.out.println("║  Utils Package Features:                                ║");
        System.out.println("║  ✓ Password Generation & Strength Checking              ║");
        System.out.println("║  ✓ Network Utilities (IP validation, traffic sim)       ║");
        System.out.println("║  ✓ Encryption Helpers (Caesar, Base64, XOR)             ║");
        System.out.println("║  ✓ File Operations                                      ║");
        System.out.println("║  ✓ Time Formatting & Duration                           ║");
        System.out.println("║  ✓ Input Validation (Email, Username, etc.)             ║");
        System.out.println("║  ✓ Random Data Generation                               ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
    }

    private static String repeat(String str, int times) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < times; i++) {
            sb.append(str);
        }
        return sb.toString();
    }
}