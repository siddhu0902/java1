package Simulation;

import Attack.Attack;
import Attack.AttackFactory;
import Defense.DefenseStrategy;
import Defense.DefenseFactory;
import Logs.Logger;
import Logs.LogAnalyzer;
import Logs.AlertSystem;
import Security.SecurityManager;
import Security.Session;
import Users.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Simulator - Main execution engine for cybersecurity simulations
 * Demonstrates main controller pattern
 */
public class Simulator {

    private String simulatorName;
    private boolean isRunning;
    private SecurityManager securityManager;
    private AttackFactory attackFactory;
    private DefenseFactory defenseFactory;
    private Logger logger;
    private LogAnalyzer logAnalyzer;
    private AlertSystem alertSystem;
    private List<Scenario> scenarios;
    private List<SimulationResult> results;
    private SimulationConfig config;
    private int totalScenariosRun;

    // Constructor
    public Simulator() {
        this.simulatorName = "Cybersecurity Attack Simulator v1.0";
        this.isRunning = false;
        this.securityManager = SecurityManager.getInstance();
        this.attackFactory = new AttackFactory();
        this.defenseFactory = new DefenseFactory();
        this.logger = Logger.getInstance();
        this.logAnalyzer = new LogAnalyzer();
        this.alertSystem = new AlertSystem();
        this.scenarios = new ArrayList<>();
        this.results = new ArrayList<>();
        this.config = new SimulationConfig();
        this.totalScenariosRun = 0;

        logger.logEvent("Simulator initialized: " + simulatorName);
        System.out.println("🎮 " + simulatorName + " ready!");
    }

    // Run simulation
    public void runSimulation() {
        System.out.println("\n╔══════════════════════════════════════════════════════════╗");
        System.out.println("║              STARTING CYBERSECURITY SIMULATION           ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");

        if (isRunning) {
            System.out.println("⚠️ Simulation is already running!");
            return;
        }

        isRunning = true;
        securityManager.startSimulation();

        // Create a simulation session
        securityManager.createSession("Simulation_Session_" + System.currentTimeMillis());

        logger.logEvent("Simulation run started");
        System.out.println("\n✅ Simulation is now ACTIVE");
        System.out.println("📝 All events will be logged for analysis\n");
    }

    // Stop simulation
    public void stopSimulation() {
        System.out.println("\n╔══════════════════════════════════════════════════════════╗");
        System.out.println("║               STOPPING CYBERSECURITY SIMULATION          ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");

        if (!isRunning) {
            System.out.println("⚠️ Simulation is not running!");
            return;
        }

        isRunning = false;
        securityManager.stopSimulation();
        securityManager.terminateSession();

        logger.logEvent("Simulation run stopped");

        // Generate final report
        System.out.println("\n📊 Generating final simulation report...");
        logAnalyzer.analyzeLogs();
        logAnalyzer.generateReport();

        System.out.println("\n✅ Simulation stopped successfully!");
    }

    // Simulate attack scenario
    public void simulateAttackScenario(String attackType, String target) {
        if (!isRunning) {
            System.out.println("⚠️ Please start the simulation first using runSimulation()");
            return;
        }

        System.out.println("\n┌─────────────────────────────────────────────────────────┐");
        System.out.println("│ SIMULATING ATTACK SCENARIO                               │");
        System.out.println("└─────────────────────────────────────────────────────────┘");

        logger.logEvent("Starting attack scenario: " + attackType + " on " + target);

        Attack attack = attackFactory.createAttack(attackType);
        if (attack != null) {
            attack.execute(target);
            logger.logAttack(attack);

            // Send alert for high severity attacks
            if (attack.getSeverityLevel() >= 8) {
                alertSystem.sendAlert(
                        "High Severity Attack Detected",
                        attack.getAttackType() + " on " + target + " - Severity: " + attack.getSeverityLevel(),
                        "HIGH"
                );
            }
        } else {
            logger.error("Unknown attack type: " + attackType);
        }
    }

    // Simulate defense scenario
    public void simulateDefenseScenario(String defenseType) {
        if (!isRunning) {
            System.out.println("⚠️ Please start the simulation first using runSimulation()");
            return;
        }

        System.out.println("\n┌─────────────────────────────────────────────────────────┐");
        System.out.println("│ SIMULATING DEFENSE SCENARIO                              │");
        System.out.println("└─────────────────────────────────────────────────────────┘");

        logger.logEvent("Starting defense scenario: " + defenseType);

        DefenseStrategy defense = defenseFactory.createDefense(defenseType);
        if (defense != null) {
            defense.applyDefense();
            logger.logDefense(defense);
        } else {
            logger.error("Unknown defense type: " + defenseType);
        }
    }

    // Simulate complete attack-defense scenario
    public void simulateFullScenario(String attackType, String defenseType, String target) {
        System.out.println("\n╔══════════════════════════════════════════════════════════╗");
        System.out.println("║           SIMULATING COMPLETE ATTACK-DEFENSE SCENARIO    ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");

        SimulationResult result = new SimulationResult(attackType, defenseType, target);
        result.start();

        // Step 1: Apply defense first
        System.out.println("\n🛡️ STEP 1: Activating Defense Mechanism");
        DefenseStrategy defense = defenseFactory.createDefense(defenseType);
        if (defense != null) {
            defense.applyDefense();
            logger.logDefense(defense);
        }

        // Step 2: Launch attack
        System.out.println("\n⚔️ STEP 2: Launching Attack");
        Attack attack = attackFactory.createAttack(attackType);
        if (attack != null) {
            attack.execute(target);
            logger.logAttack(attack);

            // Step 3: Defense responds to attack
            System.out.println("\n🔄 STEP 3: Defense Responding to Attack");
            if (defense != null) {
                defense.applyDefense(attack);
            }

            result.complete(attack.isSuccessful(), attack.getAttackDuration());
        } else {
            result.complete(false, 0);
        }

        results.add(result);
        totalScenariosRun++;

        displayScenarioResult(result);
    }

    // Display scenario result
    private void displayScenarioResult(SimulationResult result) {
        System.out.println("\n┌─────────────────────────────────────────────────────────┐");
        System.out.println("│ SCENARIO RESULT                                          │");
        System.out.println("├─────────────────────────────────────────────────────────┤");
        System.out.println("│ Attack:     " + result.getAttackType());
        System.out.println("│ Defense:    " + result.getDefenseType());
        System.out.println("│ Target:     " + result.getTarget());
        System.out.println("│ Duration:   " + result.getDuration() + " ms");
        System.out.println("│ Successful: " + (result.isAttackSuccessful() ? "YES" : "NO"));
        System.out.println("│ Status:     " + result.getStatus());
        System.out.println("└─────────────────────────────────────────────────────────┘");
    }

    // Load a predefined scenario
    public void loadScenario(Scenario scenario) {
        scenarios.add(scenario);
        logger.logEvent("Scenario loaded: " + scenario.getName());
        System.out.println("📂 Scenario loaded: " + scenario.getName());
        System.out.println("   Description: " + scenario.getDescription());
    }

    // Execute a loaded scenario
    public void executeScenario(Scenario scenario) {
        if (!isRunning) {
            System.out.println("⚠️ Please start the simulation first!");
            return;
        }

        System.out.println("\n🎯 EXECUTING SCENARIO: " + scenario.getName());
        System.out.println("📝 " + scenario.getDescription());

        for (Scenario.ScenarioStep step : scenario.getSteps()) {
            System.out.println("\n▶️ Step: " + step.getDescription());

            switch (step.getType()) {
                case "attack":
                    simulateAttackScenario(step.getAction(), step.getTarget());
                    break;
                case "defense":
                    simulateDefenseScenario(step.getAction());
                    break;
                case "wait":
                    try {
                        Thread.sleep(step.getDelay());
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    break;
                default:
                    logger.warning("Unknown step type: " + step.getType());
            }
        }

        logger.logEvent("Scenario execution completed: " + scenario.getName());
        System.out.println("\n✅ Scenario execution completed!");
    }

    // Get simulation status
    public String getSimulationStatus() {
        if (isRunning) {
            return "ACTIVE";
        } else {
            return "INACTIVE";
        }
    }

    // Display simulation dashboard
    public void displayDashboard() {
        System.out.println("\n╔══════════════════════════════════════════════════════════╗");
        System.out.println("║                 SIMULATION DASHBOARD                     ║");
        System.out.println("╠══════════════════════════════════════════════════════════╣");
        System.out.printf("║ Status:           %-40s ║%n", getSimulationStatus());
        System.out.printf("║ Simulator:        %-40s ║%n", simulatorName);
        System.out.printf("║ Scenarios Loaded: %-40d ║%n", scenarios.size());
        System.out.printf("║ Scenarios Run:    %-40d ║%n", totalScenariosRun);
        System.out.printf("║ Session Status:   %-40s ║%n", securityManager.getSessionStatus());
        System.out.printf("║ Total Events:     %-40d ║%n", logger.getTotalEvents());
        System.out.printf("║ Total Attacks:    %-40d ║%n", logger.getTotalAttacks());
        System.out.printf("║ Total Defenses:   %-40d ║%n", logger.getTotalDefenses());
        System.out.printf("║ Total Alerts:     %-40d ║%n", logger.getTotalAlerts());
        System.out.println("╚══════════════════════════════════════════════════════════╝");
    }

    // Get all simulation results
    public List<SimulationResult> getResults() {
        return new ArrayList<>(results);
    }

    // Get simulation statistics
    public void printStatistics() {
        if (results.isEmpty()) {
            System.out.println("No simulation results available.");
            return;
        }

        int successCount = 0;
        long totalDuration = 0;

        for (SimulationResult result : results) {
            if (result.isAttackSuccessful()) {
                successCount++;
            }
            totalDuration += result.getDuration();
        }

        System.out.println("\n┌─────────────────────────────────────────────────────────┐");
        System.out.println("│ SIMULATION STATISTICS                                   │");
        System.out.println("├─────────────────────────────────────────────────────────┤");
        System.out.printf("│ Total Scenarios:    %-37d │%n", results.size());
        System.out.printf("│ Successful Attacks: %-37d │%n", successCount);
        System.out.printf("│ Success Rate:       %-37.1f%% │%n",
                (successCount * 100.0 / results.size()));
        System.out.printf("│ Avg Duration:       %-37d ms │%n",
                totalDuration / results.size());
        System.out.println("└─────────────────────────────────────────────────────────┘");
    }

    // Interactive simulation mode
    public void startInteractiveMode() {
        System.out.println("\n🎮 Starting Interactive Simulation Mode");
        System.out.println("Type 'help' for commands, 'exit' to quit\n");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("sim> ");
            String command = scanner.nextLine().trim().toLowerCase();

            if (command.equals("exit")) {
                if (isRunning) {
                    stopSimulation();
                }
                System.out.println("👋 Exiting interactive mode...");
                break;
            } else if (command.equals("help")) {
                displayHelp();
            } else if (command.equals("start")) {
                runSimulation();
            } else if (command.equals("stop")) {
                stopSimulation();
            } else if (command.equals("status")) {
                displayDashboard();
            } else if (command.startsWith("attack ")) {
                String[] parts = command.split(" ", 3);
                if (parts.length >= 2) {
                    String target = parts.length >= 3 ? parts[2] : "default_target";
                    simulateAttackScenario(parts[1], target);
                }
            } else if (command.startsWith("defense ")) {
                String[] parts = command.split(" ", 2);
                if (parts.length >= 2) {
                    simulateDefenseScenario(parts[1]);
                }
            } else if (command.startsWith("scenario ")) {
                String[] parts = command.split(" ", 3);
                if (parts.length >= 3) {
                    simulateFullScenario(parts[1], parts[2], "target_system");
                }
            } else {
                System.out.println("Unknown command. Type 'help' for available commands.");
            }
        }
        scanner.close();
    }

    private void displayHelp() {
        System.out.println("\nAvailable Commands:");
        System.out.println("  start              - Start simulation");
        System.out.println("  stop               - Stop simulation");
        System.out.println("  status             - Show simulation dashboard");
        System.out.println("  attack <type> [target] - Simulate attack");
        System.out.println("  defense <type>     - Simulate defense");
        System.out.println("  scenario <attack> <defense> - Run full scenario");
        System.out.println("  exit               - Exit interactive mode");
        System.out.println("\nAttack Types: bruteforce, phishing, dos, mitm");
        System.out.println("Defense Types: firewall, ids, ratelimiter, waf");
    }

    // Getters and Setters
    public String getSimulatorName() { return simulatorName; }
    public boolean isRunning() { return isRunning; }
    public SimulationConfig getConfig() { return config; }
    public void setConfig(SimulationConfig config) { this.config = config; }
}