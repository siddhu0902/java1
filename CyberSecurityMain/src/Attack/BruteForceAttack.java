package Attack;

import java.util.ArrayList;
import java.util.List;

/**
 * Brute Force Attack - Simulates password guessing attack
 * Demonstrates time complexity and password strength concepts
 */
public class BruteForceAttack implements Attack {

    private String attackType;
    private int severityLevel;
    private boolean success;
    private long attackDuration;
    private List<String> passwordAttempts;
    private String correctPassword;
    private int maxAttempts;
    private int attemptsMade;

    // Constructor
    public BruteForceAttack() {
        this.attackType = "Brute Force Password Attack";
        this.severityLevel = 7;
        this.success = false;
        this.passwordAttempts = new ArrayList<>();
        this.maxAttempts = 10000;
        this.attemptsMade = 0;
    }

    // Constructor with custom password for simulation
    public BruteForceAttack(String targetPassword) {
        this();
        this.correctPassword = targetPassword;
    }

    @Override
    public void execute(String target) {
        System.out.println("\n╔══════════════════════════════════════════════════════════╗");
        System.out.println("║         BRUTE FORCE ATTACK SIMULATION                    ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");

        long startTime = System.currentTimeMillis();

        System.out.println("[*] Target: " + target);
        System.out.println("[*] Attack Type: " + attackType);
        System.out.println("[*] Starting brute force attack...");

        // Simulate password guessing
        if (correctPassword != null) {
            performBruteForceWithKnownPassword();
        } else {
            performSimulatedBruteForce();
        }

        attackDuration = System.currentTimeMillis() - startTime;

        displayResults();
    }

    private void performBruteForceWithKnownPassword() {
        System.out.println("\n[*] Generating password attempts...");

        // Simulate trying different password combinations
        String[] commonPasswords = {
                "123456", "password", "12345678", "qwerty", "abc123",
                "monkey", "letmein", "dragon", "baseball", "master",
                "sunshine", "iloveyou", "admin", "welcome", "shadow"
        };

        for (String attempt : commonPasswords) {
            attemptsMade++;
            passwordAttempts.add(attempt);
            System.out.println("[*] Trying: " + attempt);

            if (attempt.equals(correctPassword)) {
                success = true;
                System.out.println("\n[!] SUCCESS! Password found: " + attempt);
                break;
            }

            // Simulate time between attempts
            try { Thread.sleep(10); } catch (InterruptedException e) {}

            if (attemptsMade >= maxAttempts) break;
        }

        // If not found in common passwords, try brute force combinations
        if (!success && correctPassword.length() <= 4) {
            System.out.println("\n[*] Common passwords exhausted. Trying brute force combinations...");
            success = bruteForceCombinations(correctPassword);
        }

        if (!success) {
            System.out.println("\n[-] Attack failed! Password not found within " + maxAttempts + " attempts.");
        }
    }

    private boolean bruteForceCombinations(String targetPassword) {
        // Simplified brute force for educational purposes
        String charset = "abcdefghijklmnopqrstuvwxyz0123456789";
        int maxLength = Math.min(targetPassword.length(), 4);

        for (int length = 1; length <= maxLength; length++) {
            if (generateCombinations("", length, charset, targetPassword)) {
                return true;
            }
        }
        return false;
    }

    private boolean generateCombinations(String prefix, int length, String charset, String target) {
        if (prefix.length() == length) {
            attemptsMade++;
            passwordAttempts.add(prefix);
            if (prefix.equals(target)) {
                return true;
            }
            return false;
        }

        for (int i = 0; i < charset.length(); i++) {
            if (generateCombinations(prefix + charset.charAt(i), length, charset, target)) {
                return true;
            }
        }
        return false;
    }

    private void performSimulatedBruteForce() {
        // Simulate attack without actual password
        System.out.println("[*] Simulating brute force attack on target...");

        int simulatedAttempts = (int)(Math.random() * 5000) + 100;

        for (int i = 0; i < simulatedAttempts; i++) {
            attemptsMade++;
            if (i % 1000 == 0 && i > 0) {
                System.out.println("[*] Attempts made: " + attemptsMade);
            }
        }

        // Random success based on attempts
        if (attemptsMade < 1000) {
            success = Math.random() > 0.7;
        } else if (attemptsMade < 5000) {
            success = Math.random() > 0.3;
        } else {
            success = Math.random() > 0.1;
        }

        if (success) {
            System.out.println("\n[!] SUCCESS! Target compromised after " + attemptsMade + " attempts.");
        } else {
            System.out.println("\n[-] Attack failed after " + attemptsMade + " attempts.");
        }
    }

    // Generate password attempts for educational demonstration
    public List<String> generatePasswordAttempts() {
        System.out.println("\n[*] Generating password attempt patterns...");
        List<String> attempts = new ArrayList<>();

        // Common patterns
        attempts.add("password");
        attempts.add("123456");
        attempts.add("qwerty");
        attempts.add("admin");
        attempts.add("letmein");

        // Dictionary words
        String[] dict = {"apple", "banana", "orange", "grape", "melon"};
        for (String word : dict) {
            attempts.add(word);
            attempts.add(word + "123");
            attempts.add(word + "!");
        }

        System.out.println("[*] Generated " + attempts.size() + " password attempts");
        return attempts;
    }

    // Demonstrate password strength impact
    public void demonstratePasswordStrength() {
        System.out.println("\n=== PASSWORD STRENGTH DEMONSTRATION ===");

        String weakPassword = "cat";
        String mediumPassword = "kitty123";
        String strongPassword = "K1tty@2024!Secure";

        System.out.println("Weak Password ('" + weakPassword + "'):");
        System.out.println("  - Estimated crack time: milliseconds");
        System.out.println("  - Combinations needed: ~" + Math.pow(26, weakPassword.length()));

        System.out.println("\nMedium Password ('" + mediumPassword + "'):");
        System.out.println("  - Estimated crack time: hours");
        System.out.println("  - Combinations needed: ~" + Math.pow(36, mediumPassword.length()));

        System.out.println("\nStrong Password ('" + strongPassword + "'):");
        System.out.println("  - Estimated crack time: centuries");
        System.out.println("  - Combinations needed: ~" + Math.pow(72, strongPassword.length()));

        System.out.println("\n[!] Lesson: Strong passwords exponentially increase attack time!");
    }

    private void displayResults() {
        System.out.println("\n┌─────────────────────────────────────────┐");
        System.out.println("│ BRUTE FORCE ATTACK RESULTS              │");
        System.out.println("├─────────────────────────────────────────┤");
        System.out.println("│ Total Attempts: " + attemptsMade);
        System.out.println("│ Attack Duration: " + attackDuration + " ms");
        System.out.println("│ Success: " + (success ? "YES" : "NO"));
        System.out.println("│ Attempts per second: " + (attemptsMade * 1000 / Math.max(1, attackDuration)));
        System.out.println("└─────────────────────────────────────────┘");
    }

    @Override
    public String getAttackType() {
        return attackType;
    }

    @Override
    public int getSeverityLevel() {
        return severityLevel;
    }

    @Override
    public String getDescription() {
        return "A brute force attack systematically tries all possible password combinations " +
                "until the correct one is found. This attack demonstrates the importance of " +
                "strong, complex passwords.";
    }

    @Override
    public boolean isSuccessful() {
        return success;
    }

    @Override
    public long getAttackDuration() {
        return attackDuration;
    }

    @Override
    public void resetAttack() {
        success = false;
        attackDuration = 0;
        passwordAttempts.clear();
        attemptsMade = 0;
        System.out.println("[*] BruteForceAttack reset");
    }

    public int getAttemptsMade() {
        return attemptsMade;
    }

    public void setCorrectPassword(String password) {
        this.correctPassword = password;
    }
}