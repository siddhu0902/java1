package Attack;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Phishing Attack - Simulates fake credential capture
 * Educational demonstration of social engineering attacks
 */
public class PhishingAttack implements Attack {

    private String attackType;
    private int severityLevel;
    private boolean success;
    private long attackDuration;
    private Map<String, String> capturedCredentials;
    private String phishingUrl;
    private String targetEmail;
    private boolean userFellForPhishing;

    // Constructor
    public PhishingAttack() {
        this.attackType = "Phishing Attack (Social Engineering)";
        this.severityLevel = 8;
        this.success = false;
        this.capturedCredentials = new HashMap<>();
        this.phishingUrl = "http://fake-bank-login.com/secure";
        this.userFellForPhishing = false;
    }

    public PhishingAttack(String phishingUrl, String targetEmail) {
        this();
        this.phishingUrl = phishingUrl;
        this.targetEmail = targetEmail;
    }

    @Override
    public void execute(String target) {
        System.out.println("\n╔══════════════════════════════════════════════════════════╗");
        System.out.println("║         PHISHING ATTACK SIMULATION                       ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");

        long startTime = System.currentTimeMillis();

        System.out.println("[*] Target: " + target);
        System.out.println("[*] Attack Type: " + attackType);
        System.out.println("[*] Severity Level: " + severityLevel + "/10");

        // Simulate phishing email
        sendPhishingEmail(target);

        // Simulate fake login page
        simulateFakeLoginPage();

        attackDuration = System.currentTimeMillis() - startTime;

        displayResults();
    }

    private void sendPhishingEmail(String target) {
        System.out.println("\n[📧] SENDING PHISHING EMAIL");
        System.out.println("───────────────────────────────────────");
        System.out.println("From: security@bank-alert.com (SPOOFED)");
        System.out.println("To: " + target);
        System.out.println("Subject: URGENT: Account Security Alert!");
        System.out.println("\nMessage:");
        System.out.println("Dear Customer,");
        System.out.println("We detected suspicious activity on your account.");
        System.out.println("Please verify your credentials immediately:");
        System.out.println(phishingUrl);
        System.out.println("Failure to comply will result in account suspension.");
        System.out.println("───────────────────────────────────────");
        System.out.println("[!] Phishing email sent to target");
    }

    private void simulateFakeLoginPage() {
        System.out.println("\n[🌐] FAKE LOGIN PAGE SIMULATION");
        System.out.println("URL: " + phishingUrl);
        System.out.println("(This is a FAKE banking login page)");
        System.out.println("\n┌─────────────────────────────────────┐");
        System.out.println("│         FAKE LOGIN PAGE              │");
        System.out.println("├─────────────────────────────────────┤");
        System.out.println("│  Username: [________________]       │");
        System.out.println("│  Password: [________________]       │");
        System.out.println("│         [LOGIN]                      │");
        System.out.println("└─────────────────────────────────────┘");

        // Simulate user interaction for educational demo
        System.out.println("\n[*] Educational Simulation Mode:");
        System.out.println("[*] In a real phishing attack, the victim would enter:");
        System.out.println("    - Real username");
        System.out.println("    - Real password");
        System.out.println("    - 2FA code (if available)");

        // Simulate credential capture
        userFellForPhishing = Math.random() > 0.4; // 60% success rate in simulation

        if (userFellForPhishing) {
            String fakeUsername = "john_doe_" + System.currentTimeMillis() % 10000;
            String fakePassword = "Password123!";
            capturedCredentials.put("username", fakeUsername);
            capturedCredentials.put("password", fakePassword);
            success = true;
            System.out.println("\n[!] CREDENTIALS CAPTURED!");
            System.out.println("[!] Captured Username: " + fakeUsername);
            System.out.println("[!] Captured Password: " + fakePassword);
        } else {
            System.out.println("\n[-] User recognized the phishing attempt!");
            System.out.println("[-] Attack failed - user did not enter credentials");
        }
    }

    public void demonstratePhishingRedFlags() {
        System.out.println("\n=== HOW TO IDENTIFY PHISHING ATTEMPTS ===");
        System.out.println("1. Suspicious sender email address");
        System.out.println("2. Urgent or threatening language");
        System.out.println("3. Spelling and grammar mistakes");
        System.out.println("4. Mismatched or suspicious URLs");
        System.out.println("5. Requests for personal information");
        System.out.println("6. Unexpected attachments");
        System.out.println("7. Too-good-to-be-true offers");
    }

    public void demonstrateSafePractices() {
        System.out.println("\n=== PROTECTION AGAINST PHISHING ===");
        System.out.println("✓ Always verify the sender's email address");
        System.out.println("✓ Hover over links before clicking");
        System.out.println("✓ Never enter credentials from email links");
        System.out.println("✓ Use bookmarks for important sites");
        System.out.println("✓ Enable two-factor authentication (2FA)");
        System.out.println("✓ Report suspicious emails to IT department");
    }

    private void displayResults() {
        System.out.println("\n┌─────────────────────────────────────────┐");
        System.out.println("│ PHISHING ATTACK RESULTS                  │");
        System.out.println("├─────────────────────────────────────────┤");
        System.out.println("│ Target Email: " + (targetEmail != null ? targetEmail : "Not specified"));
        System.out.println("│ Phishing URL: " + phishingUrl);
        System.out.println("│ User Fell for Phishing: " + (userFellForPhishing ? "YES" : "NO"));
        System.out.println("│ Credentials Captured: " + (success ? "YES" : "NO"));
        System.out.println("│ Attack Duration: " + attackDuration + " ms");
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
        return "Phishing is a social engineering attack where attackers disguise themselves " +
                "as legitimate entities to steal sensitive information like usernames, " +
                "passwords, and credit card details.";
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
        capturedCredentials.clear();
        userFellForPhishing = false;
        System.out.println("[*] PhishingAttack reset");
    }

    public Map<String, String> getCapturedCredentials() {
        return capturedCredentials;
    }

    public void setPhishingUrl(String url) {
        this.phishingUrl = url;
    }

    public void setTargetEmail(String email) {
        this.targetEmail = email;
    }
}