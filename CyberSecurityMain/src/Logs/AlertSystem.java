package Logs;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * AlertSystem - Handles alert notifications for security events
 * Demonstrates OBSERVER PATTERN concept
 */
public class AlertSystem {

    private Logger logger;
    private List<Alert> alerts;
    private List<String> alertRecipients;
    private boolean emailEnabled;
    private boolean smsEnabled;
    private boolean consoleEnabled;
    private int highSeverityThreshold;

    // Inner class for Alert
    public static class Alert {
        private String id;
        private String title;
        private String message;
        private String severity; // CRITICAL, HIGH, MEDIUM, LOW
        private long timestamp;
        private boolean acknowledged;

        public Alert(String id, String title, String message, String severity) {
            this.id = id;
            this.title = title;
            this.message = message;
            this.severity = severity;
            this.timestamp = System.currentTimeMillis();
            this.acknowledged = false;
        }

        public String getId() { return id; }
        public String getTitle() { return title; }
        public String getMessage() { return message; }
        public String getSeverity() { return severity; }
        public long getTimestamp() { return timestamp; }
        public boolean isAcknowledged() { return acknowledged; }

        public void acknowledge() {
            this.acknowledged = true;
        }

        public String getFormattedTime() {
            return new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date(timestamp));
        }

        @Override
        public String toString() {
            return String.format("[%s] [%s] %s: %s",
                    getFormattedTime(), severity, title, message);
        }
    }

    // Constructor
    public AlertSystem() {
        this.logger = Logger.getInstance();
        this.alerts = new ArrayList<>();
        this.alertRecipients = new ArrayList<>();
        this.emailEnabled = true;
        this.smsEnabled = false;
        this.consoleEnabled = true;
        this.highSeverityThreshold = 7;
        System.out.println("[AlertSystem] Initialized");
    }

    // Send an alert
    public void sendAlert(String title, String message, String severity) {
        String alertId = generateAlertId();
        Alert alert = new Alert(alertId, title, message, severity);
        alerts.add(alert);

        // Log the alert
        logger.log("ALERT", title + ": " + message);

        // Send via enabled channels
        if (consoleEnabled) {
            sendConsoleAlert(alert);
        }

        if (emailEnabled) {
            sendEmailAlert(alert);
        }

        if (smsEnabled) {
            sendSMSAlert(alert);
        }

        // For critical alerts, trigger additional actions
        if (severity.equals("CRITICAL")) {
            handleCriticalAlert(alert);
        }
    }

    // Send console alert
    private void sendConsoleAlert(Alert alert) {
        String colorCode;
        switch (alert.getSeverity()) {
            case "CRITICAL":
                colorCode = "\u001B[31m"; // Red
                break;
            case "HIGH":
                colorCode = "\u001B[33m"; // Yellow
                break;
            default:
                colorCode = "\u001B[0m";  // Reset
        }

        System.out.println(colorCode + "\n" + "=".repeat(50));
        System.out.println("🔔 ALERT: " + alert.getTitle());
        System.out.println("   Severity: " + alert.getSeverity());
        System.out.println("   Message: " + alert.getMessage());
        System.out.println("   Time: " + alert.getFormattedTime());
        System.out.println("=".repeat(50) + "\u001B[0m");
    }

    // Simulate email alert
    private void sendEmailAlert(Alert alert) {
        System.out.println("[EMAIL] Sending alert to " + alertRecipients.size() + " recipients");
        System.out.println("   Subject: [URGENT] " + alert.getTitle());
        System.out.println("   Body: " + alert.getMessage());
    }

    // Simulate SMS alert
    private void sendSMSAlert(Alert alert) {
        System.out.println("[SMS] Sending text message alert");
        System.out.println("   Message: " + alert.getSeverity() + ": " + alert.getTitle());
    }

    // Handle critical alert
    private void handleCriticalAlert(Alert alert) {
        System.out.println("\n🚨🚨🚨 CRITICAL ALERT - IMMEDIATE ACTION REQUIRED 🚨🚨🚨");
        System.out.println("Initiating emergency response protocol...");

        // Simulate automated response
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("[Auto-Response] Critical alert acknowledged - Incident response team notified");
            }
        }, 1000);
    }

    // Generate unique alert ID
    private String generateAlertId() {
        return "ALT-" + System.currentTimeMillis();
    }

    // Add alert recipient
    public void addRecipient(String email) {
        alertRecipients.add(email);
        System.out.println("[AlertSystem] Recipient added: " + email);
    }

    // Remove alert recipient
    public void removeRecipient(String email) {
        alertRecipients.remove(email);
        System.out.println("[AlertSystem] Recipient removed: " + email);
    }

    // Enable/disable email alerts
    public void setEmailEnabled(boolean enabled) {
        this.emailEnabled = enabled;
        System.out.println("[AlertSystem] Email alerts " + (enabled ? "ENABLED" : "DISABLED"));
    }

    // Enable/disable SMS alerts
    public void setSmsEnabled(boolean enabled) {
        this.smsEnabled = enabled;
        System.out.println("[AlertSystem] SMS alerts " + (enabled ? "ENABLED" : "DISABLED"));
    }

    // Enable/disable console alerts
    public void setConsoleEnabled(boolean enabled) {
        this.consoleEnabled = enabled;
        System.out.println("[AlertSystem] Console alerts " + (enabled ? "ENABLED" : "DISABLED"));
    }

    // Get all unacknowledged alerts
    public List<Alert> getUnacknowledgedAlerts() {
        List<Alert> unacknowledged = new ArrayList<>();
        for (Alert alert : alerts) {
            if (!alert.isAcknowledged()) {
                unacknowledged.add(alert);
            }
        }
        return unacknowledged;
    }

    // Acknowledge an alert
    public void acknowledgeAlert(String alertId) {
        for (Alert alert : alerts) {
            if (alert.getId().equals(alertId)) {
                alert.acknowledge();
                System.out.println("[AlertSystem] Alert acknowledged: " + alertId);
                return;
            }
        }
        System.out.println("[AlertSystem] Alert not found: " + alertId);
    }

    // Acknowledge all alerts
    public void acknowledgeAllAlerts() {
        for (Alert alert : alerts) {
            alert.acknowledge();
        }
        System.out.println("[AlertSystem] All alerts acknowledged");
    }

    // Clear acknowledged alerts
    public void clearAcknowledgedAlerts() {
        alerts.removeIf(Alert::isAcknowledged);
        System.out.println("[AlertSystem] Cleared all acknowledged alerts");
    }

    // Get all alerts
    public List<Alert> getAllAlerts() {
        return new ArrayList<>(alerts);
    }

    // Get alerts by severity
    public List<Alert> getAlertsBySeverity(String severity) {
        List<Alert> result = new ArrayList<>();
        for (Alert alert : alerts) {
            if (alert.getSeverity().equalsIgnoreCase(severity)) {
                result.add(alert);
            }
        }
        return result;
    }

    // Get alert summary
    public void printAlertSummary() {
        int critical = 0, high = 0, medium = 0, low = 0;

        for (Alert alert : alerts) {
            switch (alert.getSeverity()) {
                case "CRITICAL": critical++; break;
                case "HIGH": high++; break;
                case "MEDIUM": medium++; break;
                case "LOW": low++; break;
            }
        }

        System.out.println("\n┌─────────────────────────────────────────────────────────┐");
        System.out.println("│ ALERT SUMMARY                                            │");
        System.out.println("├─────────────────────────────────────────────────────────┤");
        System.out.printf("│ CRITICAL: %-3d │ HIGH: %-3d │ MEDIUM: %-3d │ LOW: %-3d │%n",
                critical, high, medium, low);
        System.out.printf("│ Total Alerts: %-39d │%n", alerts.size());
        System.out.printf("│ Unacknowledged: %-36d │%n", getUnacknowledgedAlerts().size());
        System.out.println("└─────────────────────────────────────────────────────────┘");
    }

    private String repeat(String str, int times) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < times; i++) {
            sb.append(str);
        }
        return sb.toString();
    }
}