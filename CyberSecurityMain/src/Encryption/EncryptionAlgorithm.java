package Encryption;
public abstract class EncryptionAlgorithm {

    protected String algorithmName;
    protected int keySize;

    public EncryptionAlgorithm(String algorithmName, int keySize) {
        this.algorithmName = algorithmName;
        this.keySize = keySize;
    }

    // Abstract methods - to be implemented by child classes
    public abstract String encrypt(String data);
    public abstract String decrypt(String encryptedData);

    // Concrete methods - common to all encryption algorithms
    public String getAlgorithmName() {
        return algorithmName;
    }

    public int getKeySize() {
        return keySize;
    }

    public void displayAlgorithmInfo() {
        System.out.println("┌─────────────────────────────────────────┐");
        System.out.println("│ Algorithm: " + algorithmName);
        System.out.println("│ Key Size: " + keySize + " bits");
        System.out.println("└─────────────────────────────────────────┘");
    }

    // Helper method to validate input
    protected boolean validateInput(String data) {
        if (data == null || data.isEmpty()) {
            System.out.println("Error: Input data cannot be null or empty!");
            return false;
        }
        return true;
    }
}