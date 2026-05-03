package Defense;

/**
 * DefenseFactory - Demonstrates FACTORY DESIGN PATTERN
 * Creates different types of defense mechanisms dynamically
 */
public class DefenseFactory {

    // Create defense based on type string
    public DefenseStrategy createDefense(String defenseType) {
        DefenseStrategy defense = null;

        switch (defenseType.toLowerCase()) {
            case "firewall":
            case "fw":
                defense = new Firewall();
                System.out.println("[DefenseFactory] Created Firewall");
                break;

            case "ids":
            case "intrusion":
                defense = new IntrusionDetectionSystem();
                System.out.println("[DefenseFactory] Created Intrusion Detection System");
                break;

            case "ratelimiter":
            case "rate":
            case "rl":
                defense = new RateLimiter();
                System.out.println("[DefenseFactory] Created RateLimiter");
                break;

            case "waf":
            case "webappfirewall":
                defense = new WebApplicationFirewall();
                System.out.println("[DefenseFactory] Created Web Application Firewall");
                break;

            default:
                System.out.println("[DefenseFactory] Unknown defense type: " + defenseType);
                System.out.println("Available types: firewall, ids, ratelimiter, waf");
                return null;
        }

        return defense;
    }

    // Create defense with custom configuration
    public DefenseStrategy createDefense(String defenseType, Object config) {
        DefenseStrategy defense = null;

        switch (defenseType.toLowerCase()) {
            case "ratelimiter":
                if (config instanceof Integer) {
                    defense = new RateLimiter((Integer) config, 15);
                } else {
                    defense = new RateLimiter();
                }
                System.out.println("[DefenseFactory] Created RateLimiter with custom config");
                break;

            default:
                defense = createDefense(defenseType);
        }

        return defense;
    }

    // Create defense with multiple parameters
    public DefenseStrategy createDefense(String defenseType, int param1, int param2) {
        DefenseStrategy defense = null;

        switch (defenseType.toLowerCase()) {
            case "ratelimiter":
                defense = new RateLimiter(param1, param2);
                System.out.println("[DefenseFactory] Created RateLimiter: " + param1 + " req/min, " + param2 + " min lockout");
                break;

            default:
                defense = createDefense(defenseType);
        }

        return defense;
    }

    // Get list of available defense types
    public String[] getAvailableDefenseTypes() {
        return new String[]{
                "firewall", "ids", "ratelimiter", "waf"
        };
    }

    // Display all available defenses with descriptions
    public void displayAvailableDefenses() {
        System.out.println("\n╔══════════════════════════════════════════════════════════╗");
        System.out.println("║              AVAILABLE DEFENSE TYPES                      ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");

        System.out.println("\n┌─────────────────────────────────────────────────────────┐");
        System.out.println("│ 1. FIREWALL (firewall, fw)                               │");
        System.out.println("│    - Network traffic filtering                          │");
        System.out.println("│    - IP blocking and allowlisting                       │");
        System.out.println("│    - Rule-based packet inspection                       │");
        System.out.println("├─────────────────────────────────────────────────────────┤");
        System.out.println("│ 2. INTRUSION DETECTION SYSTEM (ids, intrusion)          │");
        System.out.println("│    - Signature-based detection                          │");
        System.out.println("│    - Anomaly detection                                  │");
        System.out.println("│    - Real-time alerting                                 │");
        System.out.println("├─────────────────────────────────────────────────────────┤");
        System.out.println("│ 3. RATE LIMITER (ratelimiter, rate, rl)                 │");
        System.out.println("│    - Prevents brute force attacks                       │");
        System.out.println("│    - Account lockout after failures                     │");
        System.out.println("│    - Request throttling                                 │");
        System.out.println("├─────────────────────────────────────────────────────────┤");
        System.out.println("│ 4. WEB APPLICATION FIREWALL (waf, webappfirewall)       │");
        System.out.println("│    - SQL Injection protection                           │");
        System.out.println("│    - XSS protection                                     │");
        System.out.println("│    - Web request filtering                              │");
        System.out.println("└─────────────────────────────────────────────────────────┘");
    }
}