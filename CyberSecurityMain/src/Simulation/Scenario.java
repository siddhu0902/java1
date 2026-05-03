package Simulation;

import java.util.ArrayList;
import java.util.List;

/**
 * Scenario - Defines test cases for simulation
 * Represents a complete cybersecurity scenario with multiple steps
 */
public class Scenario {

    private String id;
    private String name;
    private String description;
    private String difficulty; // EASY, MEDIUM, HARD, EXPERT
    private int estimatedDuration; // in seconds
    private List<ScenarioStep> steps;
    private boolean isCompleted;

    // Inner class for scenario steps
    public static class ScenarioStep {
        private String type; // attack, defense, wait, log, alert
        private String action;
        private String target;
        private String description;
        private long delay; // in milliseconds

        public ScenarioStep(String type, String action, String description) {
            this.type = type;
            this.action = action;
            this.description = description;
            this.target = "default";
            this.delay = 0;
        }

        public ScenarioStep(String type, String action, String target, String description) {
            this(type, action, description);
            this.target = target;
        }

        public ScenarioStep(String type, String action, String target, String description, long delay) {
            this(type, action, target, description);
            this.delay = delay;
        }

        // Getters
        public String getType() { return type; }
        public String getAction() { return action; }
        public String getTarget() { return target; }
        public String getDescription() { return description; }
        public long getDelay() { return delay; }

        public void setDelay(long delay) { this.delay = delay; }

        @Override
        public String toString() {
            return String.format("[%s] %s → %s", type.toUpperCase(), action, description);
        }
    }

    // Constructor
    public Scenario(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.difficulty = "MEDIUM";
        this.estimatedDuration = 30;
        this.steps = new ArrayList<>();
        this.isCompleted = false;
    }

    public Scenario(String id, String name, String description, String difficulty) {
        this(id, name, description);
        this.difficulty = difficulty;
    }

    // Add a step to the scenario
    public void addStep(ScenarioStep step) {
        steps.add(step);
    }

    // Add attack step
    public void addAttackStep(String attackType, String target, String description) {
        steps.add(new ScenarioStep("attack", attackType, target, description));
    }

    // Add defense step
    public void addDefenseStep(String defenseType, String description) {
        steps.add(new ScenarioStep("defense", defenseType, description));
    }

    // Add wait step
    public void addWaitStep(long milliseconds, String description) {
        ScenarioStep waitStep = new ScenarioStep("wait", "wait", description);
        waitStep.setDelay(milliseconds);
        steps.add(waitStep);
    }

    // Add log step
    public void addLogStep(String message) {
        steps.add(new ScenarioStep("log", message, message));
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getDifficulty() { return difficulty; }
    public int getEstimatedDuration() { return estimatedDuration; }
    public List<ScenarioStep> getSteps() { return new ArrayList<>(steps); }
    public boolean isCompleted() { return isCompleted; }

    public void setCompleted(boolean completed) { isCompleted = completed; }
    public void setEstimatedDuration(int seconds) { this.estimatedDuration = seconds; }

    // Get step count
    public int getStepCount() {
        return steps.size();
    }

    // Get attack steps count
    public int getAttackStepCount() {
        return (int) steps.stream().filter(s -> s.getType().equals("attack")).count();
    }

    // Get defense steps count
    public int getDefenseStepCount() {
        return (int) steps.stream().filter(s -> s.getType().equals("defense")).count();
    }

    // Validate scenario (check if all steps are valid)
    public boolean isValid() {
        if (steps.isEmpty()) {
            return false;
        }

        for (ScenarioStep step : steps) {
            if (step.getAction() == null || step.getAction().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    // Display scenario information
    public void displayInfo() {
        System.out.println("\n┌─────────────────────────────────────────────────────────┐");
        System.out.println("│ SCENARIO: " + name);
        System.out.println("├─────────────────────────────────────────────────────────┤");
        System.out.println("│ ID: " + id);
        System.out.println("│ Description: " + description);
        System.out.println("│ Difficulty: " + difficulty);
        System.out.println("│ Steps: " + steps.size());
        System.out.println("│ Attack Steps: " + getAttackStepCount());
        System.out.println("│ Defense Steps: " + getDefenseStepCount());
        System.out.println("│ Est. Duration: " + estimatedDuration + " seconds");
        System.out.println("└─────────────────────────────────────────────────────────┘");

        System.out.println("\nScenario Steps:");
        for (int i = 0; i < steps.size(); i++) {
            System.out.printf("  %d. %s%n", i + 1, steps.get(i));
        }
    }

    @Override
    public String toString() {
        return String.format("Scenario[%s]: %s (%s)", id, name, difficulty);
    }
}