package Attack;

import java.util.HashMap;
import java.util.Map;

/**
 * Man-in-the-Middle (MITM) Attack - Simulates interception of communication
 * Demonstrates the importance of encryption
 */
public class ManInTheMiddleAttack implements Attack {

    private String attackType;
    private int severityLevel;
    private boolean success;
    private long attackDuration;
    private Map<String, String> interceptedData;
    private String interceptedMessage;
    private boolean encryptionBypassed;

    // Constructor
    public ManInTheMiddleAttack() {
        this.attackType = "Man-in-the-Middle (MITM) Attack";
        this.severityLevel = 10;
        this.success = false;
        this.interceptedData = new HashMap<>();
        this.encryptionBypassed = false;
    }

    @Override
    public void execute(String target) {
        System.out.println("\n╔══════════════════════════════════════════════════════════╗");
        System.out.println("║      MAN-IN-THE-MIDDLE (MITM) ATTACK SIMULATION          ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");

        long startTime = System.currentTimeMillis();

        System.out.println("[*] Target Communication: " + target);
        System.out.println("[*] Attack Type: " + attackType);
        System.out.println("[*] Severity Level: " + severityLevel + "/10");

        // Simulate MITM attack
        performArpSpoofing();
        interceptCommunication();

        attackDuration = System.currentTimeMillis() - startTime;

        displayResults();
    }

    private void performArpSpoofing() {
        System.out.println("\n[🔧] ARP SPOOFING - Establishing MITM position");
        System.out.println("[*] Attacker sends fake ARP messages to the network");
        System.out.println("[*] Router now thinks attacker is the client");
        System.out.println("[*] Client now thinks attacker is the router");
        System.out.println("[!] Attacker is now in the middle of all communications!");
    }

    private void interceptCommunication() {
        System.out.println("\n[📡] INTERCEPTING COMMUNICATION");

        String[] sampleMessages = {
                "User: What's my account balance?",
                "Server: Your balance is $5,000",
                "User: Transfer $1000 to account 12345",
                "Server: Transfer confirmed"
        };

        System.out.println("\n[*] Intercepted messages:");
        for (String msg : sampleMessages) {
            System.out.println("  📨 " + msg);
            interceptedData.put("msg_" + System.currentTimeMillis(), msg);

            // Modify messages if encryption is bypassed
            if (!encryptionBypassed && msg.contains("Transfer")) {
                String modifiedMsg = msg.replace("1000", "10000");
                System.out.println("  ✏️ MODIFIED: " + modifiedMsg);
                interceptedMessage = modifiedMsg;
                success = true;
            }
        }

        if (!encryptionBypassed) {
            System.out.println("\n[!] ATTACK SUCCESSFUL - No encryption detected!");
            System.out.println("[!] Attacker modified transaction amount!");
        } else {
            System.out.println("\n[-] Encryption detected - Cannot read/modify messages");
        }
    }

    public void demonstrateEncryptionProtection() {
        System.out.println("\n=== HOW ENCRYPTION PREVENTS MITM ATTACKS ===");
        System.out.println("\n🔓 WITHOUT ENCRYPTION (HTTP):");
        System.out.println("   Attacker can see: 'Credit card: 4111-1111-1111-1111'");
        System.out.println("   Attacker can modify: Change amount, recipient, etc.");

        System.out.println("\n🔒 WITH ENCRYPTION (HTTPS/TLS):");
        System.out.println("   Attacker sees: '§4¥πƒ©∆†¥¨©†®˚∆©†' (gibberish)");
        System.out.println("   Attacker cannot modify - would break encryption");
    }

    public void demonstrateMitigation() {
        System.out.println("\n=== PROTECTING AGAINST MITM ATTACKS ===");
        System.out.println("✓ Use HTTPS (TLS/SSL encryption)");
        System.out.println("✓ Verify SSL certificates");
        System.out.println("✓ Use VPN on public Wi-Fi");
        System.out.println("✓ Implement Certificate Pinning");
        System.out.println("✓ Use HSTS (HTTP Strict Transport Security)");
        System.out.println("✓ Avoid public Wi-Fi for sensitive transactions");
    }

    private void displayResults() {
        System.out.println("\n┌─────────────────────────────────────────┐");
        System.out.println("│ MITM ATTACK RESULTS                      │");
        System.out.println("├─────────────────────────────────────────┤");
        System.out.println("│ Data Packets Intercepted: " + interceptedData.size());
        System.out.println("│ Encryption Bypassed: " + (!encryptionBypassed ? "YES" : "NO"));
        System.out.println("│ Attack Successful: " + (success ? "YES" : "NO"));
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
        return "A Man-in-the-Middle (MITM) attack occurs when an attacker secretly relays " +
                "and possibly alters the communication between two parties who believe they " +
                "are directly communicating with each other.";
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
        interceptedData.clear();
        interceptedMessage = null;
        encryptionBypassed = false;
        System.out.println("[*] ManInTheMiddleAttack reset");
    }
}