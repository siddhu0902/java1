package Utils;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * EncryptionUtils - Helper methods for encryption operations
 */
public class EncryptionUtils {

    /**
     * Simple XOR encryption (for educational purposes only - NOT secure)
     * @param data Data to encrypt
     * @param key Encryption key
     * @return Encrypted/Decrypted data
     */
    public static String xorEncrypt(String data, char key) {
        if (data == null) return null;

        char[] chars = data.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char)(chars[i] ^ key);
        }
        return new String(chars);
    }

    /**
     * Simple Caesar cipher encryption
     * @param text Text to encrypt
     * @param shift Shift amount
     * @return Encrypted text
     */
    public static String caesarEncrypt(String text, int shift) {
        if (text == null) return null;

        StringBuilder result = new StringBuilder();
        shift = shift % 26;

        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                c = (char)((c - base + shift) % 26 + base);
            }
            result.append(c);
        }
        return result.toString();
    }

    /**
     * Simple Caesar cipher decryption
     * @param text Text to decrypt
     * @param shift Shift amount
     * @return Decrypted text
     */
    public static String caesarDecrypt(String text, int shift) {
        return caesarEncrypt(text, 26 - (shift % 26));
    }

    /**
     * Calculate simple hash (for demonstration)
     * @param input Input string
     * @return Simple hash value
     */
    public static int simpleHash(String input) {
        if (input == null) return 0;

        int hash = 0;
        for (char c : input.toCharArray()) {
            hash = (hash * 31) + c;
        }
        return Math.abs(hash);
    }

    /**
     * Encode string to Base64
     * @param input Input string
     * @return Base64 encoded string
     */
    public static String toBase64(String input) {
        if (input == null) return null;
        return Base64.getEncoder().encodeToString(input.getBytes());
    }

    /**
     * Decode Base64 string
     * @param base64Input Base64 encoded string
     * @return Decoded string
     */
    public static String fromBase64(String base64Input) {
        if (base64Input == null) return null;
        try {
            return new String(Base64.getDecoder().decode(base64Input));
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    /**
     * Generate random encryption key (simulated)
     * @return Random key string
     */
    public static String generateRandomKey() {
        byte[] keyBytes = new byte[32];
        new java.security.SecureRandom().nextBytes(keyBytes);
        return Base64.getEncoder().encodeToString(keyBytes);
    }

    /**
     * Rot13 encryption (special case of Caesar with shift 13)
     * @param text Text to encrypt/decrypt
     * @return Rot13 transformed text
     */
    public static String rot13(String text) {
        return caesarEncrypt(text, 13);
    }
}