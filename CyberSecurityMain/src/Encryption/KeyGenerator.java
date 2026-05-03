package Encryption;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * KeyGenerator - Manages encryption keys
 * Demonstrates key generation, storage, and retrieval
 */
public class KeyGenerator {

    private Map<String, String> keyStore;
    private static KeyGenerator instance;

    // Private constructor for Singleton pattern
    private KeyGenerator() {
        keyStore = new HashMap<>();
        System.out.println("[KeyGenerator] Initialized");
    }

    // Singleton instance
    public static KeyGenerator getInstance() {
        if (instance == null) {
            instance = new KeyGenerator();
        }
        return instance;
    }

    // Generate a random key for symmetric encryption
    public String generateKey(String algorithm, int keySize) {
        String key = null;

        switch (algorithm.toUpperCase()) {
            case "AES":
                AES aes = new AES();
                key = aes.getKeyAsString();
                break;
            case "CAESAR":
                int shift = (int) (Math.random() * 25) + 1;
                key = String.valueOf(shift);
                break;
            case "SUBSTITUTION":
                SubstitutionCipher sub = new SubstitutionCipher();
                key = sub.getSubstitutionKey();
                break;
            default:
                System.out.println("[KeyGenerator] Unknown algorithm: " + algorithm);
                return null;
        }

        System.out.println("[KeyGenerator] Generated key for " + algorithm + ": " + key);
        return key;
    }

    // Store key in memory (simulated keystore)
    public void storeKey(String keyId, String keyValue) {
        keyStore.put(keyId, keyValue);
        System.out.println("[KeyGenerator] Key stored with ID: " + keyId);
    }

    // Retrieve key from memory
    public String retrieveKey(String keyId) {
        String key = keyStore.get(keyId);
        if (key != null) {
            System.out.println("[KeyGenerator] Key retrieved for ID: " + keyId);
        } else {
            System.out.println("[KeyGenerator] Key not found for ID: " + keyId);
        }
        return key;
    }

    // Save key to file
    public boolean saveKeyToFile(String keyId, String keyValue, String filePath) {
        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write(keyId + ":" + keyValue + "\n");
            System.out.println("[KeyGenerator] Key saved to file: " + filePath);
            return true;
        } catch (IOException e) {
            System.out.println("[KeyGenerator] Error saving key: " + e.getMessage());
            return false;
        }
    }

    // Load key from file
    public String loadKeyFromFile(String keyId, String filePath) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                System.out.println("[KeyGenerator] File not found: " + filePath);
                return null;
            }

            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            String[] lines = content.split("\n");

            for (String line : lines) {
                String[] parts = line.split(":");
                if (parts.length == 2 && parts[0].equals(keyId)) {
                    System.out.println("[KeyGenerator] Key loaded from file for ID: " + keyId);
                    return parts[1];
                }
            }
        } catch (IOException e) {
            System.out.println("[KeyGenerator] Error loading key: " + e.getMessage());
        }
        return null;
    }

    // Delete key from keystore
    public void deleteKey(String keyId) {
        if (keyStore.remove(keyId) != null) {
            System.out.println("[KeyGenerator] Key deleted: " + keyId);
        } else {
            System.out.println("[KeyGenerator] Key not found: " + keyId);
        }
    }

    // List all stored keys
    public void listAllKeys() {
        if (keyStore.isEmpty()) {
            System.out.println("[KeyGenerator] No keys stored");
        } else {
            System.out.println("\n=== Stored Keys ===");
            for (Map.Entry<String, String> entry : keyStore.entrySet()) {
                System.out.println("  ID: " + entry.getKey() + " → Key: " + entry.getValue());
            }
        }
    }

    // Generate a strong random password
    public String generateStrongPassword(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()";
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * chars.length());
            password.append(chars.charAt(index));
        }

        System.out.println("[KeyGenerator] Generated strong password: " + password);
        return password.toString();
    }

    // Key rotation demonstration
    public void rotateKey(String keyId) {
        System.out.println("[KeyGenerator] Rotating key for ID: " + keyId);
        String oldKey = retrieveKey(keyId);
        if (oldKey != null) {
            // Simulate key rotation by generating a new key
            String newKey = "ROTATED_" + System.currentTimeMillis() + "_" + oldKey;
            storeKey(keyId + "_old", oldKey);
            storeKey(keyId, newKey);
            System.out.println("[KeyGenerator] Key rotated successfully");
            System.out.println("  Old key archived as: " + keyId + "_old");
            System.out.println("  New key: " + newKey);
        } else {
            System.out.println("[KeyGenerator] Cannot rotate - key not found");
        }
    }
}