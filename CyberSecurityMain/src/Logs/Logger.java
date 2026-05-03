package Logs;

import Attack.Attack;
import Defense.DefenseStrategy;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Logger - Singleton class for logging all system events
 * Demonstrates SINGLETON DESIGN PATTERN
 */
public class Logger {

    // Singleton instance
    private static Logger instance = null;

    // Log storage
    private List<LogEntry> logEntries;
    private List<String> eventLog;
    private List<String> attackLog;
    private List<String> defenseLog;

    // File logging
    private boolean fileLoggingEnabled;
    private String logFilePath;
    private DateTimeFormatter formatter;

    // Statistics
    private int totalEvents;
    private int totalAttacks;
    private int totalDefenses;
    private int totalAlerts;

    // Private constructor for Singleton pattern
    private Logger() {
        this.logEntries = new ArrayList<>();
        this.eventLog = new ArrayList<>();
        this.attackLog = new ArrayList<>();
        this.defenseLog = new ArrayList<>();
        this.fileLoggingEnabled = false;
        this.logFilePath = "cybersecurity_logs.txt";
        this.formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        this.totalEvents = 0;
        this.totalAttacks = 0;
        this.totalDefenses = 0;
        this.totalAlerts = 0;

        System.out.println("[Logger] Initialized (Singleton)");
        logEvent("Logger system initialized");
    }

    // Singleton getInstance method
    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    // Log a general event
    public void logEvent(String event) {
        String timestamp = getCurrentTimestamp();
        String logEntry = String.format("[%s] [EVENT] %s", timestamp, event);

        // Store in memory
        eventLog.add(logEntry);
        logEntries.add(new LogEntry("EVENT", event, timestamp));
        totalEvents++;

        // Print to console
        System.out.println(logEntry);

        // Write to file if enabled
        if (fileLoggingEnabled) {
            writeToFile(logEntry);
        }
    }

    // Log an attack
    public void logAttack(Attack attack) {
        String timestamp = getCurrentTimestamp();
        String attackDetails = String.format(
                "[%s] [ATTACK] Type: %s | Severity: %d/10 | Success: %s | Duration: %d ms",
                timestamp,
                attack.getAttackType(),
                attack.getSeverityLevel(),
                attack.isSuccessful() ? "YES" : "NO",
                attack.getAttackDuration()
        );

        // Store in memory
        attackLog.add(attackDetails);
        logEntries.add(new LogEntry("ATTACK", attack.getAttackType(), timestamp,
                attack.getSeverityLevel(), attack.isSuccessful()));
        totalAttacks++;

        // Print to console with alert indicator
        String alertSymbol = attack.getSeverityLevel() >= 7 ? "⚠️ " : "";
        System.out.println(alertSymbol + attackDetails);

        // For high severity attacks, trigger alert
        if (attack.getSeverityLevel() >= 8) {
            totalAlerts++;
            System.out.println("[ALERT] HIGH SEVERITY ATTACK DETECTED! Immediate attention required.");
        }

        // Write to file if enabled
        if (fileLoggingEnabled) {
            writeToFile(attackDetails);
        }
    }

    // Log a defense action
    public void logDefense(DefenseStrategy defense) {
        String timestamp = getCurrentTimestamp();
        String defenseDetails = String.format(
                "[%s] [DEFENSE] Type: %s | Level: %d/10 | Active: %s | Threats Blocked: %d",
                timestamp,
                defense.getDefenseName(),
                defense.getDefenseLevel(),
                defense.isActive() ? "YES" : "NO",
                defense.getThreatsBlocked()
        );

        // Store in memory
        defenseLog.add(defenseDetails);
        logEntries.add(new LogEntry("DEFENSE", defense.getDefenseName(), timestamp, defense.getDefenseLevel()));
        totalDefenses++;

        // Print to console
        System.out.println(defenseDetails);

        // Write to file if enabled
        if (fileLoggingEnabled) {
            writeToFile(defenseDetails);
        }
    }

    // Log with custom severity
    public void log(String level, String message) {
        String timestamp = getCurrentTimestamp();
        String logEntry = String.format("[%s] [%s] %s", timestamp, level.toUpperCase(), message);

        eventLog.add(logEntry);
        logEntries.add(new LogEntry(level, message, timestamp));
        totalEvents++;

        System.out.println(logEntry);

        if (fileLoggingEnabled) {
            writeToFile(logEntry);
        }
    }

    // Log info message
    public void info(String message) {
        log("INFO", message);
    }

    // Log warning message
    public void warning(String message) {
        log("WARNING", message);
    }

    // Log error message
    public void error(String message) {
        log("ERROR", message);
    }

    // Log debug message
    public void debug(String message) {
        log("DEBUG", message);
    }

    // Write log to file
    private void writeToFile(String logEntry) {
        try (FileWriter fw = new FileWriter(logFilePath, true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println(logEntry);
        } catch (IOException e) {
            System.err.println("[Logger] Failed to write to file: " + e.getMessage());
        }
    }

    // Enable file logging
    public void enableFileLogging() {
        this.fileLoggingEnabled = true;
        logEvent("File logging enabled - Log file: " + logFilePath);
    }

    // Disable file logging
    public void disableFileLogging() {
        logEvent("File logging disabled");
        this.fileLoggingEnabled = false;
    }

    // Set log file path
    public void setLogFilePath(String path) {
        this.logFilePath = path;
        logEvent("Log file path changed to: " + path);
    }

    // Get current timestamp
    private String getCurrentTimestamp() {
        return LocalDateTime.now().format(formatter);
    }

    // Get all events
    public List<String> getAllEvents() {
        return new ArrayList<>(eventLog);
    }

    // Get all attacks
    public List<String> getAllAttacks() {
        return new ArrayList<>(attackLog);
    }

    // Get all defenses
    public List<String> getAllDefenses() {
        return new ArrayList<>(defenseLog);
    }

    // Get all log entries
    public List<LogEntry> getAllLogEntries() {
        return new ArrayList<>(logEntries);
    }

    // Get events by type
    public List<LogEntry> getEventsByType(String type) {
        List<LogEntry> result = new ArrayList<>();
        for (LogEntry entry : logEntries) {
            if (entry.getType().equalsIgnoreCase(type)) {
                result.add(entry);
            }
        }
        return result;
    }

    // Clear all logs
    public void clearLogs() {
        logEntries.clear();
        eventLog.clear();
        attackLog.clear();
        defenseLog.clear();
        totalEvents = 0;
        totalAttacks = 0;
        totalDefenses = 0;
        System.out.println("[Logger] All logs cleared");
        logEvent("Logs cleared by user");
    }

    // Clear logs older than specified days
    public void clearOldLogs(int days) {
        LocalDateTime cutoff = LocalDateTime.now().minusDays(days);
        logEntries.removeIf(entry -> entry.getTimestampAsDateTime().isBefore(cutoff));
        System.out.println("[Logger] Cleared logs older than " + days + " days");
        logEvent("Cleared logs older than " + days + " days");
    }

    // Get statistics
    public void printStatistics() {
        System.out.println("\n┌─────────────────────────────────────────────────────────┐");
        System.out.println("│ LOGGER STATISTICS                                        │");
        System.out.println("├─────────────────────────────────────────────────────────┤");
        System.out.printf("│ Total Events:   %-40d │%n", totalEvents);
        System.out.printf("│ Total Attacks:  %-40d │%n", totalAttacks);
        System.out.printf("│ Total Defenses: %-40d │%n", totalDefenses);
        System.out.printf("│ Total Alerts:   %-40d │%n", totalAlerts);
        System.out.printf("│ File Logging:   %-40s │%n", fileLoggingEnabled ? "ENABLED" : "DISABLED");
        System.out.printf("│ Log File Path:  %-40s │%n", logFilePath);
        System.out.println("└─────────────────────────────────────────────────────────┘");
    }

    // Export logs to CSV
    public void exportToCSV(String filename) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            // Write header
            pw.println("Timestamp,Type,Message,Severity,Success");

            // Write data
            for (LogEntry entry : logEntries) {
                pw.printf("%s,%s,%s,%d,%s%n",
                        entry.getTimestamp(),
                        entry.getType(),
                        entry.getMessage().replace(",", ";"),
                        entry.getSeverity(),
                        entry.isSuccess() ? "YES" : "NO"
                );
            }
            System.out.println("[Logger] Logs exported to CSV: " + filename);
            logEvent("Logs exported to " + filename);
        } catch (IOException e) {
            System.err.println("[Logger] Failed to export CSV: " + e.getMessage());
        }
    }

    // Get total events count
    public int getTotalEvents() {
        return totalEvents;
    }

    public int getTotalAttacks() {
        return totalAttacks;
    }

    public int getTotalDefenses() {
        return totalDefenses;
    }

    public int getTotalAlerts() {
        return totalAlerts;
    }
}