package Attack;

/**
 * AttackDemo - Demonstration class for all attack types
 * Shows Factory Pattern, Polymorphism, and all attack simulations
 */
public class AttackDemo {

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║              ATTACK PACKAGE DEMONSTRATION                ║");
        System.out.println("║                                                          ║");
        System.out.println("║  OOP Concepts: Interface, Polymorphism, Factory Pattern ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝\n");

        // Create Attack Factory
        AttackFactory factory = new AttackFactory();

        // Display available attacks
        factory.displayAvailableAttacks();

        // Test 1: Brute Force Attack
        System.out.println("\n" + "=".repeat(60));
        System.out.println("TEST CASE 1: Brute Force Attack Simulation");
        System.out.println("=".repeat(60));

        Attack bruteForce = factory.createAttack("bruteforce");
        if (bruteForce != null) {
            System.out.println("\nAttack Type: " + bruteForce.getAttackType());
            System.out.println("Description: " + bruteForce.getDescription());
            bruteForce.execute("UserLoginSystem");

            // Demonstrate password strength concept
            if (bruteForce instanceof BruteForceAttack) {
                ((BruteForceAttack) bruteForce).demonstratePasswordStrength();
            }
        }

        // Test 2: Phishing Attack
        System.out.println("\n" + "=".repeat(60));
        System.out.println("TEST CASE 2: Phishing Attack Simulation");
        System.out.println("=".repeat(60));

        Attack phishing = factory.createAttack("phishing", "victim@company.com");
        if (phishing != null) {
            System.out.println("\nAttack Type: " + phishing.getAttackType());
            System.out.println("Description: " + phishing.getDescription());
            phishing.execute("victim@company.com");

            if (phishing instanceof PhishingAttack) {
                ((PhishingAttack) phishing).demonstratePhishingRedFlags();
                ((PhishingAttack) phishing).demonstrateSafePractices();
            }
        }

        // Test 3: DoS Attack
        System.out.println("\n" + "=".repeat(60));
        System.out.println("TEST CASE 3: Denial of Service Attack Simulation");
        System.out.println("=".repeat(60));

        Attack dos = factory.createAttack("dos");
        if (dos != null) {
            System.out.println("\nAttack Type: " + dos.getAttackType());
            System.out.println("Description: " + dos.getDescription());
            dos.execute("192.168.1.100");

            if (dos instanceof DoSAttack) {
                ((DoSAttack) dos).demonstrateMitigationTechniques();
                ((DoSAttack) dos).demonstrateAttackImpact();
            }
        }

        // Test 4: SYN Flood Attack
        System.out.println("\n" + "=".repeat(60));
        System.out.println("TEST CASE 4: SYN Flood Attack Simulation");
        System.out.println("=".repeat(60));

        Attack synFlood = factory.createAttack("syn_flood");
        if (synFlood != null) {
            synFlood.execute("WebServer");
        }

        // Test 5: MITM Attack
        System.out.println("\n" + "=".repeat(60));
        System.out.println("TEST CASE 5: Man-in-the-Middle Attack Simulation");
        System.out.println("=".repeat(60));

        Attack mitm = factory.createAttack("mitm");
        if (mitm != null) {
            System.out.println("\nAttack Type: " + mitm.getAttackType());
            System.out.println("Severity: " + mitm.getSeverityLevel() + "/10");
            System.out.println("Description: " + mitm.getDescription());
            mitm.execute("Client ↔ Bank Server Communication");

            if (mitm instanceof ManInTheMiddleAttack) {
                ((ManInTheMiddleAttack) mitm).demonstrateEncryptionProtection();
                ((ManInTheMiddleAttack) mitm).demonstrateMitigation();
            }
        }

        // Test 6: Polymorphism Demonstration
        System.out.println("\n" + "=".repeat(60));
        System.out.println("TEST CASE 6: Polymorphism - Same Interface, Different Behavior");
        System.out.println("=".repeat(60));

        Attack[] attacks = {
                new BruteForceAttack(),
                new PhishingAttack(),
                new DoSAttack(),
                new ManInTheMiddleAttack()
        };

        for (Attack attack : attacks) {
            System.out.println("\n--- " + attack.getAttackType() + " ---");
            System.out.println("  Severity: " + attack.getSeverityLevel() + "/10");
            System.out.println("  Description Preview: " +
                    attack.getDescription().substring(0, Math.min(50, attack.getDescription().length())) + "...");
        }

        // Test 7: Attack with Target Object
        System.out.println("\n" + "=".repeat(60));
        System.out.println("TEST CASE 7: Attack with Target Object");
        System.out.println("=".repeat(60));

        Target target = new Target("TGT-001", "Corporate Database", "DATABASE", "10.0.0.5", 6);
        target.displayTargetInfo();

        Attack targetedAttack = factory.createAttack("bruteforce");
        if (targetedAttack != null) {
            System.out.println("\n[!] Launching attack on target: " + target.getTargetName());
            targetedAttack.execute(target.getTargetName());

            if (targetedAttack.isSuccessful()) {
                target.compromise();
            } else {
                target.incrementFailedAttempts();
            }
        }

        // Test 8: Reset and Re-attack
        System.out.println("\n" + "=".repeat(60));
        System.out.println("TEST CASE 8: Reset Attack and Re-execute");
        System.out.println("=".repeat(60));

        Attack resetAttack = factory.createAttack("bruteforce");
        if (resetAttack != null) {
            System.out.println("\nFirst execution:");
            resetAttack.execute("TestTarget");
            System.out.println("Success: " + resetAttack.isSuccessful());
            System.out.println("Duration: " + resetAttack.getAttackDuration() + " ms");

            System.out.println("\nResetting attack...");
            resetAttack.resetAttack();

            System.out.println("\nSecond execution after reset:");
            resetAttack.execute("TestTarget");
            System.out.println("Success: " + resetAttack.isSuccessful());
            System.out.println("Duration: " + resetAttack.getAttackDuration() + " ms");
        }

        // Test 9: Attack Comparison
        System.out.println("\n" + "=".repeat(60));
        System.out.println("TEST CASE 9: Attack Severity Comparison");
        System.out.println("=".repeat(60));

        System.out.println("\n┌─────────────────────────────────────────────────────────┐");
        System.out.println("│ ATTACK SEVERITY COMPARISON                               │");
        System.out.println("├─────────────────────────────────────────────────────────┤");

        Attack[] allAttacks = {
                new BruteForceAttack(),
                new PhishingAttack(),
                new DoSAttack(),
                new ManInTheMiddleAttack()
        };

        for (Attack a : allAttacks) {
            String severityBar = "█".repeat(a.getSeverityLevel()) + "░".repeat(10 - a.getSeverityLevel());
            System.out.printf("│ %-25s │ %2d/10 │ %-10s │%n",
                    a.getAttackType().substring(0, Math.min(23, a.getAttackType().length())),
                    a.getSeverityLevel(),
                    severityBar);
        }
        System.out.println("└─────────────────────────────────────────────────────────┘");

        // Summary
        System.out.println("\n╔══════════════════════════════════════════════════════════╗");
        System.out.println("║                    DEMO COMPLETED!                        ║");
        System.out.println("║                                                          ║");
        System.out.println("║  OOP Concepts Demonstrated in Attack Package:           ║");
        System.out.println("║  ✓ INTERFACE (Attack interface with contracts)          ║");
        System.out.println("║  ✓ POLYMORPHISM (Different execute() behaviors)         ║");
        System.out.println("║  ✓ FACTORY PATTERN (AttackFactory creates attacks)      ║");
        System.out.println("║  ✓ ENCAPSULATION (Private fields with getters/setters)  ║");
        System.out.println("║                                                          ║");
        System.out.println("║  Attack Types Simulated:                                ║");
        System.out.println("║  • Brute Force - Password guessing                      ║");
        System.out.println("║  • Phishing - Social engineering                        ║");
        System.out.println("║  • DoS/SYN Flood - Network flooding                     ║");
        System.out.println("║  • MITM - Communication interception                    ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
    }

    // Helper method to repeat strings (for Java 10 and below compatibility)
    private static String repeat(String str, int times) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < times; i++) {
            sb.append(str);
        }
        return sb.toString();
    }
}