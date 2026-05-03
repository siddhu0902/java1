package Logs;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * LogEntry - Represents a single log entry
 * Demonstrates ENCAPSULATION
 */
public class LogEntry {

    private String type;        // EVENT, ATTACK, DEFENSE, INFO, WARNING, ERROR
    private String message;
    private String timestamp;
    private int severity;       // 1-10 (10 = most severe)
    private boolean success;
    private String source;      // Source of the log (optional)

    // Constructors
    public LogEntry(String type, String message, String timestamp) {
        this.type = type;
        this.message = message;
        this.timestamp = timestamp;
        this.severity = 5;      // Default medium severity
        this.success = true;
        this.source = "SYSTEM";
    }

    public LogEntry(String type, String message, String timestamp, int severity) {
        this(type, message, timestamp);
        this.severity = Math.max(1, Math.min(10, severity));
    }

    public LogEntry(String type, String message, String timestamp, int severity, boolean success) {
        this(type, message, timestamp, severity);
        this.success = success;
    }

    public LogEntry(String type, String message, String timestamp, String source) {
        this(type, message, timestamp);
        this.source = source;
    }

    // Getters
    public String getType() { return type; }
    public String getMessage() { return message; }
    public String getTimestamp() { return timestamp; }
    public int getSeverity() { return severity; }
    public boolean isSuccess() { return success; }
    public String getSource() { return source; }

    // Setters
    public void setSeverity(int severity) {
        this.severity = Math.max(1, Math.min(10, severity));
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setSource(String source) {
        this.source = source;
    }

    // Get timestamp as LocalDateTime
    public LocalDateTime getTimestampAsDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        try {
            return LocalDateTime.parse(timestamp, formatter);
        } catch (Exception e) {
            return LocalDateTime.now();
        }
    }

    // Get severity as string
    public String getSeverityAsString() {
        if (severity >= 9) return "CRITICAL";
        if (severity >= 7) return "HIGH";
        if (severity >= 5) return "MEDIUM";
        if (severity >= 3) return "LOW";
        return "INFO";
    }

    // Get severity icon
    public String getSeverityIcon() {
        if (severity >= 9) return "🔴 CRITICAL";
        if (severity >= 7) return "🟠 HIGH";
        if (severity >= 5) return "🟡 MEDIUM";
        if (severity >= 3) return "🟢 LOW";
        return "ℹ️ INFO";
    }

    @Override
    public String toString() {
        return String.format("[%s] [%s] [%s] %s",
                timestamp,
                type,
                getSeverityIcon(),
                message);
    }

    // Format for CSV export
    public String toCSV() {
        return String.format("\"%s\",\"%s\",\"%s\",%d,\"%s\"",
                timestamp,
                type,
                message.replace("\"", "\"\""),
                severity,
                success ? "SUCCESS" : "FAILURE"
        );
    }

    // Format for HTML report
    public String toHTMLRow() {
        String colorClass;
        if (severity >= 9) colorClass = "critical";
        else if (severity >= 7) colorClass = "high";
        else if (severity >= 5) colorClass = "medium";
        else if (severity >= 3) colorClass = "low";
        else colorClass = "info";

        return String.format(
                "<tr class='%s'><td>%s</td><td>%s</td><td>%s</td><td>%d</td><td>%s</td></tr>",
                colorClass, timestamp, type, message, severity, success ? "Yes" : "No"
        );
    }
}