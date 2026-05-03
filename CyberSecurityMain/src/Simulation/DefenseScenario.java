package Simulation;

/**
 * DefenseScenario - Factory class for predefined defense scenarios
 */
public class DefenseScenario {

    // Create Firewall Defense Scenario
    public static Scenario createFirewallDefenseScenario() {
        Scenario scenario = new Scenario(
                "DEF-001",
                "Firewall Protection Simulation",
                "Demonstrates firewall blocking malicious traffic",
                "MEDIUM"
        );

        scenario.addLogStep("Initializing firewall defense simulation");
        scenario.addDefenseStep("firewall", "Firewall activated with default rules");
        scenario.addAttackStep("bruteforce", "protected_server", "Attempting brute force attack");
        scenario.addWaitStep(500, "Firewall analyzing traffic");
        scenario.addDefenseStep("firewall", "Firewall blocks malicious IP");
        scenario.addLogStep("Firewall defense simulation completed");

        scenario.setEstimatedDuration(35);
        return scenario;
    }

    // Create IDS Defense Scenario
    public static Scenario createIDSDefenseScenario() {
        Scenario scenario = new Scenario(
                "DEF-002",
                "Intrusion Detection System Simulation",
                "Demonstrates IDS detecting and alerting on attacks",
                "MEDIUM"
        );

        scenario.addLogStep("Initializing IDS defense simulation");
        scenario.addDefenseStep("ids", "IDS monitoring network traffic");
        scenario.addAttackStep("dos", "network", "Launching DoS attack");
        scenario.addWaitStep(500, "IDS analyzing traffic patterns");
        scenario.addDefenseStep("ids", "IDS detects anomaly and sends alert");
        scenario.addLogStep("IDS defense simulation completed");

        scenario.setEstimatedDuration(35);
        return scenario;
    }

    // Create Rate Limiter Defense Scenario
    public static Scenario createRateLimiterDefenseScenario() {
        Scenario scenario = new Scenario(
                "DEF-003",
                "Rate Limiter Protection Simulation",
                "Demonstrates rate limiting preventing brute force",
                "EASY"
        );

        scenario.addLogStep("Initializing rate limiter defense simulation");
        scenario.addDefenseStep("ratelimiter", "Rate limiter configured: 10 attempts/min");
        scenario.addAttackStep("bruteforce", "login_system", "Multiple login attempts");
        scenario.addWaitStep(500, "Rate limiter tracking attempts");
        scenario.addDefenseStep("ratelimiter", "Rate limiter blocks excessive attempts");
        scenario.addLogStep("Rate limiter defense simulation completed");

        scenario.setEstimatedDuration(25);
        return scenario;
    }

    // Create WAF Defense Scenario
    public static Scenario createWAFDefenseScenario() {
        Scenario scenario = new Scenario(
                "DEF-004",
                "Web Application Firewall Simulation",
                "Demonstrates WAF blocking web attacks",
                "MEDIUM"
        );

        scenario.addLogStep("Initializing WAF defense simulation");
        scenario.addDefenseStep("waf", "WAF protecting web application");
        scenario.addAttackStep("sql_injection", "web_app", "Attempting SQL injection");
        scenario.addWaitStep(500, "WAF inspecting HTTP requests");
        scenario.addDefenseStep("waf", "WAF blocks malicious SQL payload");
        scenario.addLogStep("WAF defense simulation completed");

        scenario.setEstimatedDuration(30);
        return scenario;
    }

    // Create Complete Defense Stack Scenario
    public static Scenario createCompleteDefenseStackScenario() {
        Scenario scenario = new Scenario(
                "DEF-005",
                "Complete Defense Stack Simulation",
                "Demonstrates multiple defense layers working together",
                "EXPERT"
        );

        scenario.addLogStep("Initializing complete defense stack");
        scenario.addDefenseStep("firewall", "Firewall - First line of defense");
        scenario.addDefenseStep("ids", "IDS - Network monitoring");
        scenario.addDefenseStep("waf", "WAF - Web protection");
        scenario.addDefenseStep("ratelimiter", "Rate limiter - Access control");

        scenario.addAttackStep("bruteforce", "web_server", "Brute force attack");
        scenario.addWaitStep(500, "Rate limiter triggers");
        scenario.addAttackStep("dos", "web_server", "DoS attack");
        scenario.addWaitStep(500, "Firewall activates DDoS protection");
        scenario.addAttackStep("sql_injection", "web_server", "SQL injection attempt");
        scenario.addWaitStep(500, "WAF blocks injection");

        scenario.addLogStep("Multi-layer defense successfully mitigated all attacks");

        scenario.setEstimatedDuration(75);
        return scenario;
    }

    // Get all predefined defense scenarios
    public static Scenario[] getAllScenarios() {
        return new Scenario[]{
                createFirewallDefenseScenario(),
                createIDSDefenseScenario(),
                createRateLimiterDefenseScenario(),
                createWAFDefenseScenario(),
                createCompleteDefenseStackScenario()
        };
    }
}