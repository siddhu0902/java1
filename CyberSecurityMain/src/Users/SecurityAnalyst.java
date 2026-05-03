package Users;

import Logs.LogAnalyzer;
import Logs.AlertSystem;
import java.util.List;

/**
 * SecurityAnalyst - Role for security monitoring and analysis
 * Demonstrates specialized role with specific permissions
 */
public class SecurityAnalyst extends User {

    private String specialization; // NETWORK, APPLICATION, INCIDENT_RESPONSE
    private boolean canViewAlerts;
    private boolean canAnalyzeLogs;
    private boolean canGenerateReports;
    private AlertSystem alertSystem;
    private LogAnalyzer logAnalyzer;

    // Constructor
    public SecurityAnalyst(String username, String password, String email, String fullName) {
        super(username, password, email, fullName, Role.ANALYST);
        this.specialization = "NETWORK";
        this.canViewAlerts = true;
        this.canAnalyzeLogs = true;
        this.canGenerateReports = true;

        // Make sure you have these classes created in your Logs package!
        this.alertSystem = new AlertSystem();
        this.logAnalyzer = new LogAnalyzer();

        System.out.println("[SecurityAnalyst] Created analyst: " + username + " (Specialization: " + specialization + ")");
    }

    // Constructor with specialization
    public SecurityAnalyst(String username, String password, String email, String fullName, String specialization) {
        this(username, password, email, fullName);
        this.specialization = specialization;
    }

    // Analyze logs
    public void analyzeLogs() {
        System.out.println("\n╔══════════════════════════════════════════════════════════╗");
        System.out.println("║         LOG ANALYSIS (Security Analyst)                  ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");

        if (!canAnalyzeLogs) {
            System.out.println("Access denied: Log analysis permission required");
            return;
        }

        logAnalyzer.analyzeLogs();
        logActivity("Performed log analysis");
    }

    // Generate report
    public void generateReport() {
        System.out.println("\n╔══════════════════════════════════════════════════════════╗");
        System.out.println("║         GENERATING SECURITY REPORT (Analyst)             ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");

        if (!canGenerateReports) {
            System.out.println("Access denied: Report generation permission required");
            return;
        }

        logAnalyzer.generateReport();
        logActivity("Generated security report");
    }

    // Check alerts
    public void checkAlerts() {
        System.out.println("\n╔══════════════════════════════════════════════════════════╗");
        System.out.println("║           CHECKING SECURITY ALERTS (Analyst)             ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");

        if (!canViewAlerts) {
            System.out.println("Access denied: Alert viewing permission required");
            return;
        }

        alertSystem.printAlertSummary();

        List<AlertSystem.Alert> unacknowledged = alertSystem.getUnacknowledgedAlerts();
        if (unacknowledged != null && !unacknowledged.isEmpty()) {
            System.out.println("\n⚠️ UNACKNOWLEDGED ALERTS:");
            for (AlertSystem.Alert alert : unacknowledged) {
                System.out.println("  • " + alert.toString());
            }
        }

        logActivity("Checked security alerts");
    }

    // Acknowledge alert
    public void acknowledgeAlert(String alertId) {
        alertSystem.acknowledgeAlert(alertId);
        logActivity("Acknowledged alert: " + alertId);
    }

    // Investigate incident
    public void investigateIncident(String incidentId) {
        System.out.println("\n🔍 INVESTIGATING INCIDENT: " + incidentId);
        System.out.println("  • Reviewing logs...");
        System.out.println("  • Analyzing attack patterns...");
        System.out.println("  • Identifying affected systems...");
        System.out.println("  • Documenting findings...");

        logActivity("Investigated incident: " + incidentId);
    }

    // Create incident report
    public void createIncidentReport(String incidentId, String description) {
        System.out.println("\n📝 CREATING INCIDENT REPORT");
        System.out.println("  Incident ID: " + incidentId);
        System.out.println("  Description: " + description);
        System.out.println("  Analyst: " + getUsername());
        System.out.println("  Status: Draft");

        logActivity("Created incident report: " + incidentId);
    }

    // Get threat intelligence
    public void getThreatIntelligence() {
        System.out.println("\n🕵️ THREAT INTELLIGENCE SUMMARY");
        System.out.println("  • Threat Score: " + logAnalyzer.calculateThreatScore() + "/100");
        System.out.println("  • Threat Level: " + logAnalyzer.getThreatLevel());
        System.out.println("  • Most Common Attack: " + logAnalyzer.getMostCommonAttack());

        logActivity("Retrieved threat intelligence");
    }

    // Run vulnerability assessment
    public void runVulnerabilityAssessment() {
        System.out.println("\n🔧 RUNNING VULNERABILITY ASSESSMENT");
        System.out.println("  Scanning for vulnerabilities...");

        String[] checks = {
                "Weak password policies",
                "Unpatched systems",
                "Open ports",
                "Default credentials",
                "Missing encryption"
        };

        for (String check : checks) {
            boolean vulnerable = Math.random() > 0.7;
            System.out.println("  " + (vulnerable ? "⚠️" : "✅") + " " + check +
                    (vulnerable ? " - VULNERABLE" : " - Secure"));
        }

        logActivity("Performed vulnerability assessment");
    }

    // Helper method to resolve the missing logActivity error
    private void logActivity(String action) {
        System.out.println("[Activity Log] " + getUsername() + ": " + action);
    }

    // Getters and Setters
    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public boolean isCanViewAlerts() { return canViewAlerts; }
    public void setCanViewAlerts(boolean canViewAlerts) { this.canViewAlerts = canViewAlerts; }

    public boolean isCanAnalyzeLogs() { return canAnalyzeLogs; }
    public void setCanAnalyzeLogs(boolean canAnalyzeLogs) { this.canAnalyzeLogs = canAnalyzeLogs; }

    public boolean isCanGenerateReports() { return canGenerateReports; }
    public void setCanGenerateReports(boolean canGenerateReports) { this.canGenerateReports = canGenerateReports; }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("│ Specialization: " + specialization);
        System.out.println("│ Privileges:     Alerts=" + canViewAlerts +
                ", Analyze=" + canAnalyzeLogs + ", Reports=" + canGenerateReports);
        System.out.println("└─────────────────────────────────────────────────────────┘");
    }

    @Override
    public String toString() {
        return String.format("SecurityAnalyst[%s] %s - Specialization: %s",
                getUserId(), getUsername(), specialization);
    }
}