package Simulation;

import Logs.Logger;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;

/**
 * ScenarioRunner - Executes scenarios with queue management
 */
public class ScenarioRunner {

    private Simulator simulator;
    private Logger logger;
    private Queue<Scenario> scenarioQueue;
    private List<SimulationResult> completedResults;
    private boolean isRunning;
    private Scenario currentScenario;

    // Constructor
    public ScenarioRunner(Simulator simulator) {
        this.simulator = simulator;
        this.logger = Logger.getInstance();
        this.scenarioQueue = new LinkedList<>();
        this.completedResults = new ArrayList<>();
        this.isRunning = false;
        this.currentScenario = null;

        logger.logEvent("ScenarioRunner initialized");
    }

    // Add scenario to queue
    public void addScenario(Scenario scenario) {
        scenarioQueue.offer(scenario);
        logger.logEvent("Scenario added to queue: " + scenario.getName());
        System.out.println("📋 Added to queue: " + scenario.getName());
    }

    // Add multiple scenarios
    public void addScenarios(Scenario... scenarios) {
        for (Scenario scenario : scenarios) {
            addScenario(scenario);
        }
    }

    // Run all scenarios in queue
    public void runAllScenarios() {
        if (scenarioQueue.isEmpty()) {
            System.out.println("No scenarios in queue to run.");
            return;
        }

        if (!simulator.isRunning()) {
            simulator.runSimulation();
        }

        isRunning = true;
        logger.logEvent("Starting batch scenario execution - " + scenarioQueue.size() + " scenarios");

        System.out.println("\n╔══════════════════════════════════════════════════════════╗");
        System.out.println("║           BATCH SCENARIO EXECUTION STARTED                ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");

        int scenarioNumber = 1;
        int totalScenarios = scenarioQueue.size();

        while (!scenarioQueue.isEmpty()) {
            currentScenario = scenarioQueue.poll();

            System.out.println("\n" + "=".repeat(60));
            System.out.printf("Executing Scenario %d/%d: %s%n", scenarioNumber, totalScenarios, currentScenario.getName());
            System.out.println("=".repeat(60));

            simulator.executeScenario(currentScenario);
            currentScenario.setCompleted(true);

            // Record result
            SimulationResult result = new SimulationResult(
                    "multiple", "multiple", currentScenario.getName()
            );
            result.start();
            result.complete(true, 0);
            completedResults.add(result);

            scenarioNumber++;

            // Small delay between scenarios
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }

        isRunning = false;
        currentScenario = null;

        System.out.println("\n╔══════════════════════════════════════════════════════════╗");
        System.out.println("║           BATCH SCENARIO EXECUTION COMPLETED              ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");

        displayBatchSummary();
    }

    // Run a single scenario
    public void runScenario(Scenario scenario) {
        if (!simulator.isRunning()) {
            simulator.runSimulation();
        }

        currentScenario = scenario;
        simulator.executeScenario(scenario);
        currentScenario.setCompleted(true);

        SimulationResult result = new SimulationResult(
                "multiple", "multiple", scenario.getName()
        );
        result.start();
        result.complete(true, 0);
        completedResults.add(result);

        currentScenario = null;
    }

    // Run scenario with custom configuration
    public void runScenario(Scenario scenario, SimulationConfig config) {
        SimulationConfig originalConfig = simulator.getConfig();
        simulator.setConfig(config);
        runScenario(scenario);
        simulator.setConfig(originalConfig);
    }

    // Display batch execution summary
    private void displayBatchSummary() {
        System.out.println("\n┌─────────────────────────────────────────────────────────┐");
        System.out.println("│ BATCH EXECUTION SUMMARY                                 │");
        System.out.println("├─────────────────────────────────────────────────────────┤");
        System.out.printf("│ Total Scenarios:    %-37d │%n", completedResults.size());
        System.out.printf("│ Successfully Executed: %-30d │%n", completedResults.size());
        System.out.println("└─────────────────────────────────────────────────────────┘");

        System.out.println("\nExecuted Scenarios:");
        for (int i = 0; i < completedResults.size(); i++) {
            System.out.printf("  %d. %s%n", i + 1, completedResults.get(i).getTarget());
        }
    }

    // Get queue status
    public void displayQueueStatus() {
        System.out.println("\n┌─────────────────────────────────────────────────────────┐");
        System.out.println("│ SCENARIO QUEUE STATUS                                   │");
        System.out.println("├─────────────────────────────────────────────────────────┤");
        System.out.printf("│ Scenarios in queue: %-36d │%n", scenarioQueue.size());
        System.out.printf("│ Completed:          %-36d │%n", completedResults.size());
        System.out.printf("│ Currently running:  %-36s │%n", isRunning ? "YES" : "NO");
        if (currentScenario != null) {
            System.out.printf("│ Current scenario:   %-36s │%n", currentScenario.getName());
        }
        System.out.println("└─────────────────────────────────────────────────────────┘");

        if (!scenarioQueue.isEmpty()) {
            System.out.println("\nQueued Scenarios:");
            int index = 1;
            for (Scenario s : scenarioQueue) {
                System.out.printf("  %d. %s (%s)%n", index++, s.getName(), s.getDifficulty());
            }
        }
    }

    // Clear scenario queue
    public void clearQueue() {
        scenarioQueue.clear();
        logger.logEvent("Scenario queue cleared");
        System.out.println("Scenario queue cleared.");
    }

    // Get completed results
    public List<SimulationResult> getCompletedResults() {
        return new ArrayList<>(completedResults);
    }

    // Check if runner is running
    public boolean isRunning() {
        return isRunning;
    }

    // Get queue size
    public int getQueueSize() {
        return scenarioQueue.size();
    }
}