package Encryption;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES (Advanced Encryption Standard) - Industry standard encryption
 * This is a REAL encryption implementation using Java's Crypto API
 */
public class AES extends EncryptionAlgorithm {

    private SecretKey secretKey;
    private String transformation;

    // Constructor with auto-generated key
    public AES() {
        super("AES (Advanced Encryption Standard)", 128);
        this.transformation = "AES";
        generateKey();
    }

    // Constructor with custom key
    public AES(String base64Key) {
        super("AES (Advanced Encryption Standard)", 128);
        this.transformation = "AES";
        setKeyFromString(base64Key);
    }

    // Generate new AES key
    private void generateKey() {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(128); // 128-bit key
            this.secretKey = keyGen.generateKey();
            System.out.println("[AES] New encryption key generated");
        } catch (Exception e) {
            System.out.println("[AES] Error generating key: " + e.getMessage());
        }
    }

    // Set key from Base64 string
    private void setKeyFromString(String base64Key) {
        try {
            byte[] decodedKey = Base64.getDecoder().decode(base64Key);
            this.secretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
            System.out.println("[AES] Key set from provided string");
        } catch (Exception e) {
            System.out.println("[AES] Invalid key format! Generating new key...");
            generateKey();
        }
    }

    @Override
    public String encrypt(String data) {
        if (!validateInput(data)) {
            return null;
        }

        try {
            Cipher cipher = Cipher.getInstance(transformation);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedBytes = cipher.doFinal(data.getBytes());
            String encryptedBase64 = Base64.getEncoder().encodeToString(encryptedBytes);
            System.out.println("[AES] Encrypted successfully");
            return encryptedBase64;
        } catch (Exception e) {
            System.out.println("[AES] Encryption error: " + e.getMessage());
            return null;
        }
    }

    @Override
    public String decrypt(String encryptedData) {
        if (!validateInput(encryptedData)) {
            return null;
        }

        try {
            byte[] encryptedBytes = Base64.getDecoder().decode(encryptedData);
            Cipher cipher = Cipher.getInstance(transformation);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            String decrypted = new String(decryptedBytes);
            System.out.println("[AES] Decrypted successfully");
            return decrypted;
        } catch (Exception e) {
            System.out.println("[AES] Decryption error: " + e.getMessage());
            return null;
        }
    }

    // Get current key as Base64 string
    public String getKeyAsString() {
        return Base64.getEncoder().encodeToString(secretKey.getEncoded());
    }

    // Get SecretKey object
    public SecretKey getSecretKey() {
        return secretKey;
    }

    // Demonstrate AES security
    public void demonstrateSecurity(String message) {
        System.out.println("\n=== AES Security Demonstration ===");
        System.out.println("Original Message: " + message);
        String encrypted = encrypt(message);
        System.out.println("Encrypted (Ciphertext): " + encrypted);
        String decrypted = decrypt(encrypted);
        System.out.println("Decrypted: " + decrypted);
        System.out.println("Key (Base64): " + getKeyAsString());
    }
}