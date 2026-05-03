package Encryption;

/**
 * Caesar Cipher - Simplest encryption technique
 * Shifts each letter by a fixed number of positions
 * Educational purpose only - NOT secure for real use
 */
public class CaesarCipher extends EncryptionAlgorithm {

    private int shift;

    // Constructor with default shift of 3
    public CaesarCipher() {
        super("Caesar Cipher", 8); // 8 bits equivalent
        this.shift = 3;
    }

    // Constructor with custom shift
    public CaesarCipher(int shift) {
        super("Caesar Cipher", 8);
        this.shift = shift % 26; // Ensure shift is within 0-25
    }

    @Override
    public String encrypt(String data) {
        if (!validateInput(data)) {
            return null;
        }

        StringBuilder encrypted = new StringBuilder();

        for (char c : data.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                char shifted = (char) ((c - base + shift) % 26 + base);
                encrypted.append(shifted);
            } else {
                encrypted.append(c); // Keep non-letters unchanged
            }
        }

        System.out.println("[CaesarCipher] Encrypted: " + data + " → " + encrypted.toString());
        return encrypted.toString();
    }

    @Override
    public String decrypt(String encryptedData) {
        if (!validateInput(encryptedData)) {
            return null;
        }

        StringBuilder decrypted = new StringBuilder();

        for (char c : encryptedData.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                char shifted = (char) ((c - base - shift + 26) % 26 + base);
                decrypted.append(shifted);
            } else {
                decrypted.append(c);
            }
        }

        System.out.println("[CaesarCipher] Decrypted: " + encryptedData + " → " + decrypted.toString());
        return decrypted.toString();
    }

    // Additional method to brute force Caesar cipher (for educational purposes)
    public void bruteForceDecrypt(String encryptedData) {
        System.out.println("\n--- Brute Force Attack on Caesar Cipher ---");
        for (int possibleShift = 0; possibleShift < 26; possibleShift++) {
            StringBuilder attempt = new StringBuilder();
            for (char c : encryptedData.toCharArray()) {
                if (Character.isLetter(c)) {
                    char base = Character.isUpperCase(c) ? 'A' : 'a';
                    char shifted = (char) ((c - base - possibleShift + 26) % 26 + base);
                    attempt.append(shifted);
                } else {
                    attempt.append(c);
                }
            }
            System.out.printf("Shift %2d: %s%n", possibleShift, attempt.toString());
        }
    }

    public int getShift() {
        return shift;
    }

    public void setShift(int shift) {
        this.shift = shift % 26;
        System.out.println("[CaesarCipher] Shift changed to: " + this.shift);
    }
}