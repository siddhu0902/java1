package Encryption;

/**
 * Demonstration class to test all encryption algorithms
 * Shows OOP concepts: Abstraction, Inheritance, Polymorphism
 */
public class EncryptionDemo {

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║         ENCRYPTION PACKAGE DEMONSTRATION                 ║");
        System.out.println("║         OOP Concepts: Abstraction, Inheritance,         ║");
        System.out.println("║                    Polymorphism                          ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝\n");

        // Test data
        String secretMessage = "HelloWorld123";
        String passwordMessage = "MySecretPassword";

        // 1. CAESAR CIPHER DEMO
        System.out.println("┌─────────────────────────────────────────────────────────┐");
        System.out.println("│ 1. CAESAR CIPHER (Shift Cipher) - Educational Purpose  │");
        System.out.println("└─────────────────────────────────────────────────────────┘");
        CaesarCipher caesar = new CaesarCipher(5);
        caesar.displayAlgorithmInfo();
        String caesarEncrypted = caesar.encrypt(secretMessage);
        caesar.decrypt(caesarEncrypted);
        caesar.bruteForceDecrypt(caesarEncrypted);

        // 2. SUBSTITUTION CIPHER DEMO
        System.out.println("\n┌─────────────────────────────────────────────────────────┐");
        System.out.println("│ 2. SUBSTITUTION CIPHER - Letter Mapping                  │");
        System.out.println("└─────────────────────────────────────────────────────────┘");
        SubstitutionCipher substitution = new SubstitutionCipher();
        substitution.displayAlgorithmInfo();
        substitution.displayKey();
        String subEncrypted = substitution.encrypt(secretMessage);
        substitution.decrypt(subEncrypted);

        // 3. AES DEMO (Real encryption)
        System.out.println("\n┌─────────────────────────────────────────────────────────┐");
        System.out.println("│ 3. AES - Advanced Encryption Standard (Real Encryption) │");
        System.out.println("└─────────────────────────────────────────────────────────┘");
        AES aes = new AES();
        aes.displayAlgorithmInfo();
        aes.demonstrateSecurity(passwordMessage);
        System.out.println("Key to save for decryption: " + aes.getKeyAsString());

        // 4. RSA DEMO (Asymmetric)
        System.out.println("\n┌─────────────────────────────────────────────────────────┐");
        System.out.println("│ 4. RSA - Asymmetric Encryption (Public/Private Key)     │");
        System.out.println("└─────────────────────────────────────────────────────────┘");
        RSA rsa = new RSA();
        rsa.displayAlgorithmInfo();
        rsa.demonstrateAsymmetricEncryption(secretMessage);

        // 5. POLYMORPHISM DEMO - Using abstract class reference
        System.out.println("\n┌─────────────────────────────────────────────────────────┐");
        System.out.println("│ 5. POLYMORPHISM DEMO - Same interface, different behavior│");
        System.out.println("└─────────────────────────────────────────────────────────┘");

        EncryptionAlgorithm[] algorithms = {
                new CaesarCipher(3),
                new SubstitutionCipher(),
                new AES(),
                new RSA()
        };

        String testMessage = "Polymorphism";
        for (EncryptionAlgorithm algo : algorithms) {
            System.out.println("\n--- Using: " + algo.getAlgorithmName() + " ---");
            String encrypted = algo.encrypt(testMessage);
            if (encrypted != null) {
                algo.decrypt(encrypted);
            }
        }

        // 6. KEY GENERATOR DEMO
        System.out.println("\n┌─────────────────────────────────────────────────────────┐");
        System.out.println("│ 6. KEY GENERATOR - Key Management System                 │");
        System.out.println("└─────────────────────────────────────────────────────────┘");
        KeyGenerator keyGen = KeyGenerator.getInstance();

        // Generate different keys
        String aesKey = keyGen.generateKey("AES", 128);
        String caesarKey = keyGen.generateKey("CAESAR", 0);
        String subKey = keyGen.generateKey("SUBSTITUTION", 0);

        // Store keys
        keyGen.storeKey("db_encryption_key", aesKey);
        keyGen.storeKey("user_password_key", caesarKey);
        keyGen.storeKey("config_key", subKey);

        // List all keys
        keyGen.listAllKeys();

        // Retrieve and use a key
        String retrievedKey = keyGen.retrieveKey("db_encryption_key");
        System.out.println("Retrieved key value: " + retrievedKey);

        // Generate strong password
        String strongPassword = keyGen.generateStrongPassword(16);

        // Key rotation demo
        keyGen.rotateKey("db_encryption_key");

        // 7. ENCAPSULATION DEMO
        System.out.println("\n┌─────────────────────────────────────────────────────────┐");
        System.out.println("│ 7. ENCAPSULATION - Data hiding within classes            │");
        System.out.println("└─────────────────────────────────────────────────────────┘");
        System.out.println("The encryption keys and algorithms are encapsulated inside");
        System.out.println("their respective classes. You can't access the internal");
        System.out.println("state directly - only through public methods.");

        System.out.println("\n╔══════════════════════════════════════════════════════════╗");
        System.out.println("║              ENCRYPTION DEMO COMPLETED!                  ║");
        System.out.println("║                                                          ║");
        System.out.println("║  OOP Concepts Demonstrated:                              ║");
        System.out.println("║  ✓ ABSTRACTION (EncryptionAlgorithm abstract class)      ║");
        System.out.println("║  ✓ INHERITANCE (AES, RSA, Caesar extend abstract)        ║");
        System.out.println("║  ✓ POLYMORPHISM (Different encrypt/decrypt behaviors)    ║");
        System.out.println("║  ✓ ENCAPSULATION (Private fields, public methods)        ║");
        System.out.println("║  ✓ SINGLETON (KeyGenerator)                              ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
    }
}