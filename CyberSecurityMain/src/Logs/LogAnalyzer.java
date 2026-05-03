package Logs;

import java.util.*;
import java.util.stream.Collectors;

/**
 * LogAnalyzer - Analyzes logs and detects patterns
 * Demonstrates data analysis and pattern recognition
 */
public class LogAnalyzer {

    private Logger logger;
    private Map<String, Integer> attackFrequency;
    private Map<String, Integer> eventFrequency;
    private List<String> detectedPatterns;

    // Constructor
    public LogAnalyzer() {
        this.logger = Logger.getInstance();
        this.attackFrequency = new HashMap<>();
        this.eventFrequency = new HashMap<>();
        this.detectedPatterns = new ArrayList<>();
        System.out.println("[LogAnalyzer] Initialized");
    }

    // Analyze all logs
    public void analyzeLogs() {
        System.out.println("\n╔══════════════════════════════════════════════════════════╗");
        System.out.println("║              LOG ANALYSIS STARTED                         ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");

        List<LogEntry> entries = logger.getAllLogEntries();

        if (entries.isEmpty()) {
            System.out.println("[LogAnalyzer] No logs to analyze");
            return;
        }

        // Reset statistics
        attackFrequency.clear();
        eventFrequency.clear();
        detectedPatterns.clear();

        // Analyze each entry
        for (LogEntry entry : entries) {
            // Count by type
            eventFrequency.put(entry.getType(),
                    eventFrequency.getOrDefault(entry.getType(), 0) + 1);

            // For attacks, track specific attack types
            if (entry.getType().equals("ATTACK")) {
                String attackName = extractAttackName(entry.getMessage());
                attackFrequency.put(attackName,
                        attackFrequency.getOrDefault(attackName, 0) + 1);
            }
        }

        // Detect patterns
        detectPatterns();

        System.out.println("[LogAnalyzer] Analysis complete!");
    }

    // Detect patterns in logs
    public void detectPatterns() {
        System.out.println("\n[LogAnalyzer] Detecting patterns...");

        List<LogEntry> entries = logger.getAllLogEntries();

        // Pattern 1: Multiple attacks in short time
        detectAttackSpikes(entries);

        // Pattern 2: Repeated failed attempts
        detectRepeatedFailures(entries);

        // Pattern 3: Attack-success patterns
        detectAttackSuccessPatterns(entries);

        // Pattern 4: Time-based patterns
        detectTimeBasedPatterns(entries);

        // Display detected patterns
        displayDetectedPatterns();
    }

    private void detectAttackSpikes(List<LogEntry> entries) {
        // Look for 5 or more attacks within a short time window
        int attackCount = 0;
        String lastTimestamp = null;

        for (LogEntry entry : entries) {
            if (entry.getType().equals("ATTACK")) {
                attackCount++;
                lastTimestamp = entry.getTimestamp();
            }
        }

        if (attackCount >= 5) {
            String pattern = "Multiple attacks detected (" + attackCount + " total)";
            detectedPatterns.add(pattern);
            logger.warning("PATTERN DETECTED: " + pattern);
        }
    }

    private void detectRepeatedFailures(List<LogEntry> entries) {
        int failureCount = 0;

        for (LogEntry entry : entries) {
            if (!entry.isSuccess() && entry.getType().equals("ATTACK")) {
                failureCount++;
            }
        }

        if (failureCount >= 3) {
            String pattern = "Repeated attack failures (" + failureCount + " consecutive)";
            detectedPatterns.add(pattern);
            logger.warning("PATTERN DETECTED: " + pattern);
        }
    }

    private void detectAttackSuccessPatterns(List<LogEntry> entries) {
        int successCount = 0;

        for (LogEntry entry : entries) {
            if (entry.isSuccess() && entry.getType().equals("ATTACK")) {
                successCount++;
            }
        }

        if (successCount >= 2) {
            String pattern = "Multiple successful attacks (" + successCount + ") - Security breach possible";
            detectedPatterns.add(pattern);
            logger.error("CRITICAL PATTERN: " + pattern);
        }
    }

    private void detectTimeBasedPatterns(List<LogEntry> entries) {
        // Check for attacks during unusual hours (simplified)
        Map<Integer, Integer> hourDistribution = new HashMap<>();

        for (LogEntry entry : entries) {
            if (entry.getType().equals("ATTACK")) {
                try {
                    String hourStr = entry.getTimestamp().substring(11, 13);
                    int hour = Integer.parseInt(hourStr);
                    hourDistribution.put(hour, hourDistribution.getOrDefault(hour, 0) + 1);
                } catch (Exception e) {
                    // Ignore parsing errors
                }
            }
        }

        // Check for midnight attacks (between 12 AM and 5 AM)
        int midnightAttacks = 0;
        for (Map.Entry<Integer, Integer> entry : hourDistribution.entrySet()) {
            if (entry.getKey() >= 0 && entry.getKey() <= 5) {
                midnightAttacks += entry.getValue();
            }
        }

        if (midnightAttacks >= 2) {
            String pattern = "Suspicious activity during off-hours (" + midnightAttacks + " attacks between 12 AM-5 AM)";
            detectedPatterns.add(pattern);
            logger.warning("PATTERN DETECTED: " + pattern);
        }
    }

    private void displayDetectedPatterns() {
        System.out.println("\n┌─────────────────────────────────────────────────────────┐");
        System.out.println("│ DETECTED PATTERNS                                        │");
        System.out.println("├─────────────────────────────────────────────────────────┤");

        if (detectedPatterns.isEmpty()) {
            System.out.println("│ No suspicious patterns detected                         │");
        } else {
            for (int i = 0; i < detectedPatterns.size(); i++) {
                String pattern = detectedPatterns.get(i);
                if (pattern.length() > 55) {
                    pattern = pattern.substring(0, 52) + "...";
                }
                System.out.printf("│ %d. %-53s │%n", i + 1, pattern);
            }
        }
        System.out.println("└─────────────────────────────────────────────────────────┘");
    }

    // Generate analysis report
    public void generateReport() {
        System.out.println("\n╔══════════════════════════════════════════════════════════╗");
        System.out.println("║                   LOG ANALYSIS REPORT                     ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");

        System.out.println("\n┌─────────────────────────────────────────────────────────┐");
        System.out.println("│ EVENT DISTRIBUTION                                       │");
        System.out.println("├─────────────────────────────────────────────────────────┤");

        for (Map.Entry<String, Integer> entry : eventFrequency.entrySet()) {
            String bar = "█".repeat(Math.min(50, entry.getValue() / 5));
            System.out.printf("│ %-10s │ %3d │ %-35s │%n",
                    entry.getKey(),
                    entry.getValue(),
                    bar);
        }
        System.out.println("└─────────────────────────────────────────────────────────┘");

        System.out.println("\n┌─────────────────────────────────────────────────────────┐");
        System.out.println("│ ATTACK FREQUENCY DISTRIBUTION                            │");
        System.out.println("├─────────────────────────────────────────────────────────┤");

        if (attackFrequency.isEmpty()) {
            System.out.println("│ No attacks recorded                                      │");
        } else {
            for (Map.Entry<String, Integer> entry : attackFrequency.entrySet()) {
                String bar = "█".repeat(Math.min(40, entry.getValue() * 5));
                System.out.printf("│ %-20s │ %3d │ %-30s │%n",
                        entry.getKey(),
                        entry.getValue(),
                        bar);
            }
        }
        System.out.println("└─────────────────────────────────────────────────────────┘");

        // Summary statistics
        System.out.println("\n┌─────────────────────────────────────────────────────────┐");
        System.out.println("│ SUMMARY STATISTICS                                       │");
        System.out.println("├─────────────────────────────────────────────────────────┤");
        System.out.printf("│ Total Logs Analyzed: %-37d │%n", logger.getTotalEvents());
        System.out.printf("│ Unique Event Types:  %-37d │%n", eventFrequency.size());
        System.out.printf("│ Unique Attack Types: %-37d │%n", attackFrequency.size());
        System.out.printf("│ Patterns Detected:   %-37d │%n", detectedPatterns.size());
        System.out.println("└─────────────────────────────────────────────────────────┘");
    }

    // Get most common attack
    public String getMostCommonAttack() {
        return attackFrequency.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("No attacks recorded");
    }

    // Get attack frequency
    public Map<String, Integer> getAttackFrequency() {
        return new HashMap<>(attackFrequency);
    }

    // Get event frequency
    public Map<String, Integer> getEventFrequency() {
        return new HashMap<>(eventFrequency);
    }

    // Get detected patterns
    public List<String> getDetectedPatterns() {
        return new ArrayList<>(detectedPatterns);
    }

    // Extract attack name from message
    private String extractAttackName(String message) {
        if (message.contains("Brute")) return "Brute Force";
        if (message.contains("Phish")) return "Phishing";
        if (message.contains("DoS")) return "Denial of Service";
        if (message.contains("MITM")) return "Man-in-the-Middle";
        return "Unknown Attack";
    }

    // Calculate threat score (0-100)
    public int calculateThreatScore() {
        int score = 0;

        // Add points for each attack
        score += logger.getTotalAttacks() * 5;

        // Add points for high severity events
        for (LogEntry entry : logger.getAllLogEntries()) {
            if (entry.getSeverity() >= 8) {
                score += 10;
            } else if (entry.getSeverity() >= 6) {
                score += 5;
            }
        }

        // Add points for detected patterns
        score += detectedPatterns.size() * 15;

        return Math.min(100, score);
    }

    // Get threat level
    public String getThreatLevel() {
        int score = calculateThreatScore();
        if (score >= 75) return "CRITICAL";
        if (score >= 50) return "HIGH";
        if (score >= 25) return "MEDIUM";
        if (score >= 10) return "LOW";
        return "SAFE";
    }
}