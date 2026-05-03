package Defense;

import Attack.Attack;
import Attack.BruteForceAttack;
import Attack.DoSAttack;
import Attack.PhishingAttack;

/**
 * DefenseDemo - Demonstration class for all defense mechanisms
 * Shows Factory Pattern, Polymorphism, and all defense simulations
 */
public class DefenseDemo {

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║              DEFENSE PACKAGE DEMONSTRATION               ║");
        System.out.println("║                                                          ║");
        System.out.println("║  OOP Concepts: Interface, Polymorphism, Factory Pattern ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝\n");

        // Create Defense Factory
        DefenseFactory factory = new DefenseFactory();

        // Display available defenses
        factory.displayAvailableDefenses();

        // Test 1: Firewall Defense
        System.out.println("\n" + "=".repeat(60));
        System.out.println("TEST CASE 1: Firewall Defense");
        System.out.println("=".repeat(60));

        DefenseStrategy firewall = factory.createDefense("firewall");
        if (firewall != null) {
            System.out.println("\nDefense: " + firewall.getDefenseName());
            System.out.println("Level: " + firewall.getDefenseLevel() + "/10");
            firewall.applyDefense();
            firewall.detectThreat();

            // Test firewall specific features
            if (firewall instanceof Firewall) {
                Firewall fw = (Firewall) firewall;
                fw.listRules();
                fw.filterPackets("192.168.1.100", "10.0.0.1", 80, "TCP", "GET /index.html");
                fw.filterPackets("45.33.22.11", "10.0.0.1", 443, "TCP", "POST /login");
                fw.listBlockedIPs();
                fw.demonstrateSecurityFeatures();
            }
        }

        // Test 2: IDS Defense
        System.out.println("\n" + "=".repeat(60));
        System.out.println("TEST CASE 2: Intrusion Detection System");
        System.out.println("=".repeat(60));

        DefenseStrategy ids = factory.createDefense("ids");
        if (ids != null) {
            System.out.println("\nDefense: " + ids.getDefenseName());
            ids.applyDefense();

            if (ids instanceof IntrusionDetectionSystem) {
                IntrusionDetectionSystem idsSystem = (IntrusionDetectionSystem) ids;
                idsSystem.monitorTraffic("GET /login.php?user=admin' OR '1'='1");
                idsSystem.monitorTraffic("<script>alert('XSS')</script>");
                idsSystem.getAlertHistory();
                idsSystem.listDetectionRules();
                idsSystem.getTrafficStats();
            }

            ids.detectThreat();
        }

        // Test 3: Rate Limiter Defense
        System.out.println("\n" + "=".repeat(60));
        System.out.println("TEST CASE 3: Rate Limiter - Brute Force Prevention");
        System.out.println("=".repeat(60));

        DefenseStrategy rateLimiter = factory.createDefense("ratelimiter");
        if (rateLimiter != null) {
            System.out.println("\nDefense: " + rateLimiter.getDefenseName());
            rateLimiter.applyDefense();

            if (rateLimiter instanceof RateLimiter) {
                RateLimiter rl = (RateLimiter) rateLimiter;
                rl.demonstrateBruteForcePrevention();
                rl.getStatistics();
            }
        }

        // Test 4: WAF Defense
        System.out.println("\n" + "=".repeat(60));
        System.out.println("TEST CASE 4: Web Application Firewall");
        System.out.println("=".repeat(60));

        DefenseStrategy waf = factory.createDefense("waf");
        if (waf != null) {
            System.out.println("\nDefense: " + waf.getDefenseName());
            waf.applyDefense();

            if (waf instanceof WebApplicationFirewall) {
                WebApplicationFirewall webWaf = (WebApplicationFirewall) waf;
                webWaf.demonstrateOWASPProtection();
                webWaf.getBlockedPayloads();
            }

            waf.detectThreat();
        }

        // Test 5: Defense Against Specific Attacks
        System.out.println("\n" + "=".repeat(60));
        System.out.println("TEST CASE 5: Defending Against Specific Attacks");
        System.out.println("=".repeat(60));

        // Create attacks
        Attack bruteForce = new BruteForceAttack();
        Attack dosAttack = new DoSAttack();
        Attack phishing = new PhishingAttack();

        // Create defenses
        DefenseStrategy firewall2 = factory.createDefense("firewall");
        DefenseStrategy ids2 = factory.createDefense("ids");
        DefenseStrategy rl2 = factory.createDefense("ratelimiter");

        System.out.println("\n--- Defending Against Brute Force Attack ---");
        firewall2.applyDefense(bruteForce);
        ids2.applyDefense(bruteForce);
        rl2.applyDefense(bruteForce);

        System.out.println("\n--- Defending Against DoS Attack ---");
        firewall2.applyDefense(dosAttack);
        ids2.applyDefense(dosAttack);

        System.out.println("\n--- Defending Against Phishing Attack ---");
        firewall2.applyDefense(phishing);
        ids2.applyDefense(phishing);

        // Test 6: Polymorphism Demonstration
        System.out.println("\n" + "=".repeat(60));
        System.out.println("TEST CASE 6: Polymorphism - Same Interface, Different Behavior");
        System.out.println("=".repeat(60));

        DefenseStrategy[] defenses = {
                new Firewall(),
                new IntrusionDetectionSystem(),
                new RateLimiter(),
                new WebApplicationFirewall()
        };

        for (DefenseStrategy defense : defenses) {
            System.out.println("\n--- " + defense.getDefenseName() + " ---");
            System.out.println("  Defense Level: " + defense.getDefenseLevel() + "/10");
            System.out.println("  Description Preview: " +
                    defense.getDescription().substring(0, Math.min(50, defense.getDescription().length())) + "...");
            System.out.println("  Threat Detection: " + (defense.detectThreat() ? "Threat Found" : "No Threat"));
        }

        // Test 7: Defense Statistics
        System.out.println("\n" + "=".repeat(60));
        System.out.println("TEST CASE 7: Defense Statistics");
        System.out.println("=".repeat(60));

        System.out.println("\n┌─────────────────────────────────────────────────────────┐");
        System.out.println("│ DEFENSE STATISTICS SUMMARY                               │");
        System.out.println("├─────────────────────────────────────────────────────────┤");

        for (DefenseStrategy defense : defenses) {
            System.out.printf("│ %-30s │ Threats Blocked: %-6d │%n",
                    defense.getDefenseName().substring(0, Math.min(28, defense.getDefenseName().length())),
                    defense.getThreatsBlocked());
        }
        System.out.println("└─────────────────────────────────────────────────────────┘");

        // Test 8: Defense Comparison
        System.out.println("\n" + "=".repeat(60));
        System.out.println("TEST CASE 8: Defense Effectiveness Comparison");
        System.out.println("=".repeat(60));

        System.out.println("\n┌─────────────────────────────────────────────────────────┐");
        System.out.println("│ DEFENSE EFFECTIVENESS RATING                             │");
        System.out.println("├─────────────────────────────────────────────────────────┤");

        for (DefenseStrategy defense : defenses) {
            String effectivenessBar = "█".repeat(defense.getDefenseLevel() / 2) +
                    "░".repeat(5 - (defense.getDefenseLevel() / 2));
            System.out.printf("│ %-25s │ %2d/10 │ %-10s │%n",
                    defense.getDefenseName().substring(0, Math.min(23, defense.getDefenseName().length())),
                    defense.getDefenseLevel(),
                    effectivenessBar);
        }
        System.out.println("└─────────────────────────────────────────────────────────┘");

        // Summary
        System.out.println("\n╔══════════════════════════════════════════════════════════╗");
        System.out.println("║                    DEMO COMPLETED!                        ║");
        System.out.println("║                                                          ║");
        System.out.println("║  OOP Concepts Demonstrated in Defense Package:          ║");
        System.out.println("║  ✓ INTERFACE (DefenseStrategy interface)                ║");
        System.out.println("║  ✓ POLYMORPHISM (Different applyDefense() behaviors)    ║");
        System.out.println("║  ✓ FACTORY PATTERN (DefenseFactory creates defenses)    ║");
        System.out.println("║  ✓ ENCAPSULATION (Private fields with methods)          ║");
        System.out.println("║                                                          ║");
        System.out.println("║  Defense Types Simulated:                               ║");
        System.out.println("║  • Firewall - Network filtering                         ║");
        System.out.println("║  • IDS - Intrusion detection                            ║");
        System.out.println("║  • Rate Limiter - Brute force prevention                ║");
        System.out.println("║  • WAF - Web application protection                     ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
    }

    // Helper method for string repetition (for compatibility)
    private static String repeat(String str, int times) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < times; i++) {
            sb.append(str);
        }
        return sb.toString();
    }
}