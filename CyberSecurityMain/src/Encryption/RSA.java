package Encryption;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import javax.crypto.Cipher;

/**
 * RSA - Asymmetric encryption algorithm
 * Uses public key for encryption, private key for decryption
 */
public class RSA extends EncryptionAlgorithm {

    private PublicKey publicKey;
    private PrivateKey privateKey;
    private String transformation;

    // Constructor - generates new RSA key pair
    public RSA() {
        super("RSA (Rivest-Shamir-Adleman)", 2048);
        this.transformation = "RSA";
        generateKeyPair();
    }

    // Generate RSA key pair (public + private)
    private void generateKeyPair() {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(2048); // 2048-bit key size
            KeyPair keyPair = keyGen.generateKeyPair();
            this.publicKey = keyPair.getPublic();
            this.privateKey = keyPair.getPrivate();
            System.out.println("[RSA] New key pair generated");
            System.out.println("[RSA] Public Key: " + getPublicKeyAsString().substring(0, 50) + "...");
            System.out.println("[RSA] Private Key: " + getPrivateKeyAsString().substring(0, 50) + "...");
        } catch (Exception e) {
            System.out.println("[RSA] Error generating key pair: " + e.getMessage());
        }
    }

    @Override
    public String encrypt(String data) {
        if (!validateInput(data)) {
            return null;
        }

        try {
            Cipher cipher = Cipher.getInstance(transformation);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] encryptedBytes = cipher.doFinal(data.getBytes());
            String encryptedBase64 = Base64.getEncoder().encodeToString(encryptedBytes);
            System.out.println("[RSA] Encrypted with PUBLIC key");
            return encryptedBase64;
        } catch (Exception e) {
            System.out.println("[RSA] Encryption error: " + e.getMessage());
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
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            String decrypted = new String(decryptedBytes);
            System.out.println("[RSA] Decrypted with PRIVATE key");
            return decrypted;
        } catch (Exception e) {
            System.out.println("[RSA] Decryption error: " + e.getMessage());
            return null;
        }
    }

    // Get Public Key as Base64 string
    public String getPublicKeyAsString() {
        return Base64.getEncoder().encodeToString(publicKey.getEncoded());
    }

    // Get Private Key as Base64 string
    public String getPrivateKeyAsString() {
        return Base64.getEncoder().encodeToString(privateKey.getEncoded());
    }

    // Get Public Key object
    public PublicKey getPublicKey() {
        return publicKey;
    }

    // Get Private Key object
    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    // Demonstrate RSA asymmetric encryption
    public void demonstrateAsymmetricEncryption(String message) {
        System.out.println("\n=== RSA Asymmetric Encryption Demonstration ===");
        System.out.println("Original Message: " + message);
        System.out.println("\nStep 1: Encrypt with PUBLIC key (anyone can do this)");
        String encrypted = encrypt(message);
        System.out.println("Encrypted: " + encrypted);
        System.out.println("\nStep 2: Decrypt with PRIVATE key (only receiver can do this)");
        String decrypted = decrypt(encrypted);
        System.out.println("Decrypted: " + decrypted);
    }
}