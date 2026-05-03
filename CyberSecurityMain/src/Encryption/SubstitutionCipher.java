package Encryption;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Substitution Cipher - Maps each letter to another letter
 * More complex than Caesar cipher but still not secure for real use
 */
public class SubstitutionCipher extends EncryptionAlgorithm {

    private Map<Character, Character> encryptionMap;
    private Map<Character, Character> decryptionMap;
    private String substitutionKey;

    // Constructor with random substitution key
    public SubstitutionCipher() {
        super("Substitution Cipher", 10);
        generateRandomKey();
        buildMaps();
    }

    // Constructor with custom key
    public SubstitutionCipher(String key) {
        super("Substitution Cipher", 10);
        if (key.length() == 26 && key.matches("[A-Z]+")) {
            this.substitutionKey = key;
        } else {
            System.out.println("Invalid key! Generating random key...");
            generateRandomKey();
        }
        buildMaps();
    }

    private void generateRandomKey() {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder shuffled = new StringBuilder(alphabet);
        Random rand = new Random();

        // Fisher-Yates shuffle
        for (int i = shuffled.length() - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            char temp = shuffled.charAt(i);
            shuffled.setCharAt(i, shuffled.charAt(j));
            shuffled.setCharAt(j, temp);
        }
        this.substitutionKey = shuffled.toString();
        System.out.println("[SubstitutionCipher] Generated random key: " + substitutionKey);
    }

    private void buildMaps() {
        encryptionMap = new HashMap<>();
        decryptionMap = new HashMap<>();

        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        for (int i = 0; i < 26; i++) {
            char original = alphabet.charAt(i);
            char substituted = substitutionKey.charAt(i);
            encryptionMap.put(original, substituted);
            encryptionMap.put(Character.toLowerCase(original), Character.toLowerCase(substituted));
            decryptionMap.put(substituted, original);
            decryptionMap.put(Character.toLowerCase(substituted), Character.toLowerCase(original));
        }
    }

    @Override
    public String encrypt(String data) {
        if (!validateInput(data)) {
            return null;
        }

        StringBuilder encrypted = new StringBuilder();

        for (char c : data.toCharArray()) {
            if (encryptionMap.containsKey(c)) {
                encrypted.append(encryptionMap.get(c));
            } else {
                encrypted.append(c);
            }
        }

        System.out.println("[SubstitutionCipher] Encrypted: " + data + " → " + encrypted.toString());
        return encrypted.toString();
    }

    @Override
    public String decrypt(String encryptedData) {
        if (!validateInput(encryptedData)) {
            return null;
        }

        StringBuilder decrypted = new StringBuilder();

        for (char c : encryptedData.toCharArray()) {
            if (decryptionMap.containsKey(c)) {
                decrypted.append(decryptionMap.get(c));
            } else {
                decrypted.append(c);
            }
        }

        System.out.println("[SubstitutionCipher] Decrypted: " + encryptedData + " → " + decrypted.toString());
        return decrypted.toString();
    }

    public String getSubstitutionKey() {
        return substitutionKey;
    }

    public void displayKey() {
        System.out.println("Substitution Key (A-Z): " + substitutionKey);
        System.out.println("Original: ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        System.out.println("Mapped:   " + substitutionKey);
    }
}