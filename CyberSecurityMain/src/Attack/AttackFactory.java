package Attack;

/**
 * AttackFactory - Demonstrates FACTORY DESIGN PATTERN
 * Creates different types of attack objects dynamically
 */
public class AttackFactory {

    // Create attack based on type string
    public Attack createAttack(String attackType) {
        Attack attack = null;

        switch (attackType.toLowerCase()) {
            case "bruteforce":
            case "brute":
            case "brute_force":
                attack = new BruteForceAttack();
                System.out.println("[AttackFactory] Created BruteForceAttack");
                break;

            case "phishing":
            case "phish":
                attack = new PhishingAttack();
                System.out.println("[AttackFactory] Created PhishingAttack");
                break;

            case "dos":
            case "denial":
            case "denial_of_service":
                attack = new DoSAttack();
                System.out.println("[AttackFactory] Created DoSAttack");
                break;

            case "syn_flood":
                attack = new DoSAttack("SYN_FLOOD", 80);
                System.out.println("[AttackFactory] Created DoSAttack (SYN_FLOOD)");
                break;

            case "http_flood":
                attack = new DoSAttack("HTTP_FLOOD", 443);
                System.out.println("[AttackFactory] Created DoSAttack (HTTP_FLOOD)");
                break;

            case "mitm":
            case "man_in_the_middle":
                attack = new ManInTheMiddleAttack();
                System.out.println("[AttackFactory] Created ManInTheMiddleAttack");
                break;

            default:
                System.out.println("[AttackFactory] Unknown attack type: " + attackType);
                System.out.println("Available types: bruteforce, phishing, dos, syn_flood, http_flood, mitm");
                return null;
        }

        return attack;
    }

    // Create attack with custom parameters
    public Attack createAttack(String attackType, String parameter) {
        Attack attack = null;

        switch (attackType.toLowerCase()) {
            case "bruteforce":
            case "brute":
            case "brute_force":
                BruteForceAttack bfAttack = new BruteForceAttack();
                bfAttack.setCorrectPassword(parameter);
                attack = bfAttack;
                System.out.println("[AttackFactory] Created BruteForceAttack with target password");
                break;

            case "phishing":
            case "phish":
                PhishingAttack pAttack = new PhishingAttack();
                pAttack.setTargetEmail(parameter);
                attack = pAttack;
                System.out.println("[AttackFactory] Created PhishingAttack for email: " + parameter);
                break;

            default:
                System.out.println("[AttackFactory] Unknown attack type with parameter: " + attackType);
                return null;
        }

        return attack;
    }

    // Create attack with multiple parameters
    public Attack createAttack(String attackType, String param1, int param2) {
        Attack attack = null;

        switch (attackType.toLowerCase()) {
            case "dos":
            case "denial":
                DoSAttack dosAttack = new DoSAttack(param1, param2);
                attack = dosAttack;
                System.out.println("[AttackFactory] Created DoSAttack with method: " + param1 + ", port: " + param2);
                break;

            case "phishing":
                PhishingAttack pAttack = new PhishingAttack(param1, param2 + "@example.com");
                attack = pAttack;
                System.out.println("[AttackFactory] Created PhishingAttack with URL: " + param1);
                break;

            default:
                System.out.println("[AttackFactory] Unknown attack type for multi-param: " + attackType);
                return null;
        }

        return attack;
    }

    // Get list of available attack types
    public String[] getAvailableAttackTypes() {
        return new String[]{
                "bruteforce", "phishing", "dos", "syn_flood", "http_flood", "mitm"
        };
    }

    // Display all available attacks with descriptions
    public void displayAvailableAttacks() {
        System.out.println("\n╔══════════════════════════════════════════════════════════╗");
        System.out.println("║              AVAILABLE ATTACK TYPES                       ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");

        System.out.println("\n┌─────────────────────────────────────────────────────────┐");
        System.out.println("│ 1. BRUTEFORCE (brute, brute_force)                       │");
        System.out.println("│    - Simulates password guessing attack                  │");
        System.out.println("│    - Demonstrates password strength importance          │");
        System.out.println("├─────────────────────────────────────────────────────────┤");
        System.out.println("│ 2. PHISHING (phish)                                      │");
        System.out.println("│    - Simulates fake credential capture                  │");
        System.out.println("│    - Demonstrates social engineering risks              │");
        System.out.println("├─────────────────────────────────────────────────────────┤");
        System.out.println("│ 3. DoS - Denial of Service (dos, denial)                │");
        System.out.println("│    - Simulates traffic overload attack                  │");
        System.out.println("│    - Demonstrates network flooding                      │");
        System.out.println("├─────────────────────────────────────────────────────────┤");
        System.out.println("│ 4. SYN_FLOOD (syn_flood)                                 │");
        System.out.println("│    - Specific TCP SYN flood attack                      │");
        System.out.println("│    - Exploits TCP handshake vulnerability               │");
        System.out.println("├─────────────────────────────────────────────────────────┤");
        System.out.println("│ 5. HTTP_FLOOD (http_flood)                               │");
        System.out.println("│    - Web server request flooding                        │");
        System.out.println("│    - Overwhelms web application resources               │");
        System.out.println("├─────────────────────────────────────────────────────────┤");
        System.out.println("│ 6. MITM (mitm, man_in_the_middle)                       │");
        System.out.println("│    - Simulates communication interception              │");
        System.out.println("│    - Demonstrates encryption importance                 │");
        System.out.println("└─────────────────────────────────────────────────────────┘");
    }
}