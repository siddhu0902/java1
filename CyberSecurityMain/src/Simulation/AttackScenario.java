package Simulation;

/**
 * AttackScenario - Factory class for predefined attack scenarios
 */
public class AttackScenario {

    // Create Brute Force Attack Scenario
    public static Scenario createBruteForceScenario() {
        Scenario scenario = new Scenario(
                "SCEN-001",
                "Brute Force Attack Simulation",
                "Simulates a password brute force attack against a user account",
                "MEDIUM"
        );

        scenario.addLogStep("Initializing brute force attack simulation");
        scenario.addAttackStep("bruteforce", "user_login_system", "Launching password guessing attack");
        scenario.addWaitStep(500, "Waiting for attack execution");
        scenario.addDefenseStep("ratelimiter", "Rate limiter detects multiple failed attempts");
        scenario.addDefenseStep("firewall", "Firewall blocks suspicious IP");
        scenario.addLogStep("Brute force attack simulation completed");

        scenario.setEstimatedDuration(45);
        return scenario;
    }

    // Create DoS Attack Scenario
    public static Scenario createDoSAttackScenario() {
        Scenario scenario = new Scenario(
                "SCEN-002",
                "Denial of Service Attack Simulation",
                "Simulates a DoS attack flooding a web server",
                "HARD"
        );

        scenario.addLogStep("Initializing DoS attack simulation");
        scenario.addAttackStep("dos", "web_server", "Launching SYN flood attack");
        scenario.addWaitStep(1000, "Attack in progress...");
        scenario.addDefenseStep("firewall", "Firewall detects abnormal traffic patterns");
        scenario.addDefenseStep("ids", "IDS alerts security team");
        scenario.addAttackStep("http_flood", "web_server", "Secondary HTTP flood attack");
        scenario.addDefenseStep("waf", "WAF filters malicious requests");
        scenario.addLogStep("DoS attack simulation completed");

        scenario.setEstimatedDuration(60);
        return scenario;
    }

    // Create Phishing Attack Scenario
    public static Scenario createPhishingScenario() {
        Scenario scenario = new Scenario(
                "SCEN-003",
                "Phishing Attack Simulation",
                "Simulates a phishing email targeting employees",
                "EASY"
        );

        scenario.addLogStep("Initializing phishing attack simulation");
        scenario.addAttackStep("phishing", "employees@company.com", "Sending phishing emails");
        scenario.addWaitStep(500, "User interaction simulation");
        scenario.addDefenseStep("firewall", "Email gateway blocks malicious links");
        scenario.addDefenseStep("ids", "IDS detects suspicious email patterns");
        scenario.addLogStep("Phishing attack simulation completed");

        scenario.setEstimatedDuration(30);
        return scenario;
    }

    // Create MITM Attack Scenario
    public static Scenario createMITMAttackScenario() {
        Scenario scenario = new Scenario(
                "SCEN-004",
                "Man-in-the-Middle Attack Simulation",
                "Simulates interception of network communication",
                "HARD"
        );

        scenario.addLogStep("Initializing MITM attack simulation");
        scenario.addAttackStep("mitm", "client_server_communication", "Establishing MITM position");
        scenario.addWaitStep(500, "Intercepting communication");
        scenario.addDefenseStep("ids", "IDS detects ARP spoofing");
        scenario.addDefenseStep("firewall", "Firewall enforces encrypted communication");
        scenario.addLogStep("MITM attack simulation completed");

        scenario.setEstimatedDuration(40);
        return scenario;
    }

    // Create Multi-Layer Attack Scenario
    public static Scenario createMultiLayerAttackScenario() {
        Scenario scenario = new Scenario(
                "SCEN-005",
                "Multi-Layer Attack Simulation",
                "Simulates coordinated multiple attack types",
                "EXPERT"
        );

        scenario.addLogStep("Starting multi-layer attack simulation");
        scenario.addAttackStep("phishing", "employees", "Initial reconnaissance via phishing");
        scenario.addWaitStep(1000, "Waiting for initial compromise");
        scenario.addAttackStep("bruteforce", "internal_system", "Brute force on compromised credentials");
        scenario.addDefenseStep("ratelimiter", "Rate limiter triggers account lockout");
        scenario.addAttackStep("dos", "web_server", "DoS attack as distraction");
        scenario.addDefenseStep("firewall", "Firewall activates DDoS protection");
        scenario.addDefenseStep("ids", "IDS correlates multiple attack vectors");
        scenario.addLogStep("Multi-layer attack simulation completed");

        scenario.setEstimatedDuration(90);
        return scenario;
    }

    // Get all predefined scenarios
    public static Scenario[] getAllScenarios() {
        return new Scenario[]{
                createBruteForceScenario(),
                createDoSAttackScenario(),
                createPhishingScenario(),
                createMITMAttackScenario(),
                createMultiLayerAttackScenario()
        };
    }
}