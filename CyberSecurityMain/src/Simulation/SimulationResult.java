package Simulation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * SimulationResult - Tracks results of simulation scenarios
 */
public class SimulationResult {

    private String id;
    private String attackType;
    private String defenseType;
    private String target;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private long duration;
    private boolean attackSuccessful;
    private String status; // SUCCESS, FAILED, PARTIAL, ERROR
    private String notes;

    // Constructor
    public SimulationResult(String attackType, String defenseType, String target) {
        this.id = generateId();
        this.attackType = attackType;
        this.defenseType = defenseType;
        this.target = target;
        this.status = "PENDING";
        this.notes = "";
    }

    // Start the simulation
    public void start() {
        this.startTime = LocalDateTime.now();
        this.status = "RUNNING";
    }

    // Complete the simulation
    public void complete(boolean attackSuccessful, long duration) {
        this.endTime = LocalDateTime.now();
        this.duration = duration;
        this.attackSuccessful = attackSuccessful;
        this.status = attackSuccessful ? "ATTACK_SUCCESSFUL" : "DEFENSE_SUCCESSFUL";
    }

    // Complete with error
    public void error(String errorMessage) {
        this.status = "ERROR";
        this.notes = errorMessage;
    }

    // Generate unique ID
    private String generateId() {
        return "RES_" + System.currentTimeMillis();
    }

    // Getters
    public String getId() { return id; }
    public String getAttackType() { return attackType; }
    public String getDefenseType() { return defenseType; }
    public String getTarget() { return target; }
    public LocalDateTime getStartTime() { return startTime; }
    public LocalDateTime getEndTime() { return endTime; }
    public long getDuration() { return duration; }
    public boolean isAttackSuccessful() { return attackSuccessful; }
    public String getStatus() { return status; }
    public String getNotes() { return notes; }

    public void setNotes(String notes) { this.notes = notes; }

    // Get formatted duration
    public String getFormattedDuration() {
        if (duration < 1000) {
            return duration + " ms";
        } else {
            return String.format("%.2f seconds", duration / 1000.0);
        }
    }

    // Get formatted start time
    public String getFormattedStartTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return startTime != null ? startTime.format(formatter) : "N/A";
    }

    // Get formatted end time
    public String getFormattedEndTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return endTime != null ? endTime.format(formatter) : "N/A";
    }

    // Get result summary
    public String getSummary() {
        return String.format("%s | Attack: %s | Defense: %s | Result: %s | Duration: %s",
                getFormattedStartTime(), attackType, defenseType,
                attackSuccessful ? "Attack Succeeded" : "Attack Blocked",
                getFormattedDuration());
    }

    // Display result
    public void display() {
        System.out.println("\n┌─────────────────────────────────────────────────────────┐");
        System.out.println("│ SIMULATION RESULT                                       │");
        System.out.println("├─────────────────────────────────────────────────────────┤");
        System.out.println("│ ID:           " + id);
        System.out.println("│ Attack:       " + attackType);
        System.out.println("│ Defense:      " + defenseType);
        System.out.println("│ Target:       " + target);
        System.out.println("│ Start Time:   " + getFormattedStartTime());
        System.out.println("│ Duration:     " + getFormattedDuration());
        System.out.println("│ Status:       " + status);
        System.out.println("│ Attack Success: " + (attackSuccessful ? "YES" : "NO"));
        if (!notes.isEmpty()) {
            System.out.println("│ Notes:        " + notes);
        }
        System.out.println("└─────────────────────────────────────────────────────────┘");
    }

    @Override
    public String toString() {
        return getSummary();
    }
}