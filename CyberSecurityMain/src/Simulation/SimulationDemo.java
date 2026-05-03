package Simulation;

import Logs.Logger;

/**
 * SimulationDemo - Demonstration class for Simulation package
 * Shows complete simulation workflow
 */
public class SimulationDemo {

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║              SIMULATION PACKAGE DEMONSTRATION            ║");
        System.out.println("║                                                          ║");
        System.out.println("║  Main Execution Engine, Scenarios, Results Tracking    ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝\n");

        // Test 1: Create Simulator
        System.out.println("=".repeat(60));
        System.out.println("TEST CASE 1: Creating and Starting Simulator");
        System.out.println("=".repeat(60));

        Simulator simulator = new Simulator();
        simulator.runSimulation();

        // Test 2: Single Attack Scenario
        System.out.println("\n" + "=".repeat(60));
        System.out.println("TEST CASE 2: Single Attack Scenario");
        System.out.println("=".repeat(60));

        simulator.simulateAttackScenario("bruteforce", "user_database");

        // Test 3: Single Defense Scenario
        System.out.println("\n" + "=".repeat(60));
        System.out.println("TEST CASE 3: Single Defense Scenario");
        System.out.println("=".repeat(60));

        simulator.simulateDefenseScenario("firewall");

        // Test 4: Complete Attack-Defense Scenario
        System.out.println("\n" + "=".repeat(60));
        System.out.println("TEST CASE 4: Complete Attack-Defense Scenario");
        System.out.println("=".repeat(60));

        simulator.simulateFullScenario("bruteforce", "ratelimiter", "login_portal");
        simulator.simulateFullScenario("dos", "firewall", "web_server");
        simulator.simulateFullScenario("phishing", "ids", "employees");

        // Test 5: Predefined Attack Scenarios
        System.out.println("\n" + "=".repeat(60));
        System.out.println("TEST CASE 5: Predefined Attack Scenarios");
        System.out.println("=".repeat(60));

        Scenario bruteForceScenario = AttackScenario.createBruteForceScenario();
        bruteForceScenario.displayInfo();
        simulator.loadScenario(bruteForceScenario);
        simulator.executeScenario(bruteForceScenario);

        // Test 6: Predefined Defense Scenarios
        System.out.println("\n" + "=".repeat(60));
        System.out.println("TEST CASE 6: Predefined Defense Scenarios");
        System.out.println("=".repeat(60));

        Scenario firewallScenario = DefenseScenario.createFirewallDefenseScenario();
        firewallScenario.displayInfo();
        simulator.loadScenario(firewallScenario);
        simulator.executeScenario(firewallScenario);

        // Test 7: Scenario Runner with Queue
        System.out.println("\n" + "=".repeat(60));
        System.out.println("TEST CASE 7: Scenario Runner - Batch Execution");
        System.out.println("=".repeat(60));

        ScenarioRunner runner = new ScenarioRunner(simulator);

        // Add scenarios to queue
        runner.addScenario(AttackScenario.createPhishingScenario());
        runner.addScenario(DefenseScenario.createRateLimiterDefenseScenario());
        runner.addScenario(AttackScenario.createMITMAttackScenario());
        runner.addScenario(DefenseScenario.createWAFDefenseScenario());

        runner.displayQueueStatus();
        runner.runAllScenarios();

        // Test 8: Configuration Management
        System.out.println("\n" + "=".repeat(60));
        System.out.println("TEST CASE 8: Configuration Management");
        System.out.println("=".repeat(60));

        SimulationConfig config = new SimulationConfig();
        config.displayConfig();
        config.setSimulationSpeed(8);
        config.setVerboseLogging(false);
        config.setLogLevel("DEBUG");
        System.out.println("\nUpdated Configuration:");
        config.displayConfig();

        // Save and load config
        config.saveToFile("simulator_config.properties");
        SimulationConfig loadedConfig = new SimulationConfig();
        loadedConfig.loadFromFile("simulator_config.properties");

        // Test 9: Simulation Dashboard
        System.out.println("\n" + "=".repeat(60));
        System.out.println("TEST CASE 9: Simulation Dashboard");
        System.out.println("=".repeat(60));

        simulator.displayDashboard();

        // Test 10: Simulation Statistics
        System.out.println("\n" + "=".repeat(60));
        System.out.println("TEST CASE 10: Simulation Statistics");
        System.out.println("=".repeat(60));

        simulator.printStatistics();

        // Test 11: Complex Multi-Step Scenario
        System.out.println("\n" + "=".repeat(60));
        System.out.println("TEST CASE 11: Complex Multi-Step Scenario");
        System.out.println("=".repeat(60));

        Scenario complexScenario = new Scenario(
                "SCEN-COMPLEX-001",
                "Advanced Persistent Threat Simulation",
                "Simulates a sophisticated APT attack with multiple phases",
                "EXPERT"
        );

        complexScenario.addLogStep("Phase 1: Reconnaissance");
        complexScenario.addAttackStep("phishing", "employees", "Initial foothold via spear-phishing");
        complexScenario.addWaitStep(1000, "Establishing persistence");

        complexScenario.addLogStep("Phase 2: Lateral Movement");
        complexScenario.addAttackStep("bruteforce", "internal_servers", "Credential harvesting");
        complexScenario.addDefenseStep("ids", "IDS detects unusual lateral movement");

        complexScenario.addLogStep("Phase 3: Privilege Escalation");
        complexScenario.addAttackStep("mitm", "domain_controller", "Attempting privilege escalation");
        complexScenario.addDefenseStep("firewall", "Firewall enforces network segmentation");

        complexScenario.addLogStep("Phase 4: Data Exfiltration");
        complexScenario.addAttackStep("dos", "security_appliances", "Distraction attack");
        complexScenario.addDefenseStep("waf", "WAF prevents data leakage");

        complexScenario.addLogStep("Phase 5: Incident Response");
        complexScenario.addDefenseStep("ids", "IDS triggers full incident response");

        complexScenario.displayInfo();
        simulator.loadScenario(complexScenario);
        simulator.executeScenario(complexScenario);

        // Test 12: Stop Simulation
        System.out.println("\n" + "=".repeat(60));
        System.out.println("TEST CASE 12: Stopping Simulation");
        System.out.println("=".repeat(60));

        simulator.stopSimulation();

        // Summary
        System.out.println("\n╔══════════════════════════════════════════════════════════╗");
        System.out.println("║                    DEMO COMPLETED!                        ║");
        System.out.println("║                                                          ║");
        System.out.println("║  OOP Concepts Demonstrated in Simulation Package:       ║");
        System.out.println("║  ✓ MAIN CONTROLLER (Simulator as central engine)        ║");
        System.out.println("║  ✓ FACTORY PATTERN (AttackScenario/DefenseScenario)     ║");
        System.out.println("║  ✓ QUEUE MANAGEMENT (ScenarioRunner)                    ║");
        System.out.println("║  ✓ CONFIGURATION MANAGEMENT (SimulationConfig)          ║");
        System.out.println("║  ✓ RESULT TRACKING (SimulationResult)                   ║");
        System.out.println("║                                                          ║");
        System.out.println("║  Features Implemented:                                  ║");
        System.out.println("║  • Interactive/Headless Simulation Modes                ║");
        System.out.println("║  • Predefined Attack & Defense Scenarios                ║");
        System.out.println("║  • Custom Scenario Creation                             ║");
        System.out.println("║  • Batch Processing with Queue                          ║");
        System.out.println("║  • Real-time Dashboard & Statistics                     ║");
        System.out.println("║  • Configuration Persistence                            ║");
        System.out.println("║  • Result Tracking & Reporting                          ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");

        System.out.println("\n📁 Generated Files:");
        System.out.println("  • simulator_config.properties - Saved configuration");
    }

    private static String repeat(String str, int times) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < times; i++) {
            sb.append(str);
        }
        return sb.toString();
    }
}