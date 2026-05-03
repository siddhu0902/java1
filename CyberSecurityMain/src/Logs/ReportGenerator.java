package Logs;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

/**
 * ReportGenerator - Generates various reports from logs
 * Demonstrates data aggregation and reporting
 */
public class ReportGenerator {

    private Logger logger;
    private LogAnalyzer analyzer;
    private DateTimeFormatter formatter;

    // Constructor
    public ReportGenerator() {
        this.logger = Logger.getInstance();
        this.analyzer = new LogAnalyzer();
        this.formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("[ReportGenerator] Initialized");
    }

    // Generate summary report
    public void generateSummaryReport() {
        analyzer.analyzeLogs();

        System.out.println("\n╔══════════════════════════════════════════════════════════╗");
        System.out.println("║                   SUMMARY REPORT                          ║");
        System.out.println("║                   " + LocalDateTime.now().format(formatter) + "              ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");

        System.out.println("\n┌─────────────────────────────────────────────────────────┐");
        System.out.println("│ SYSTEM OVERVIEW                                          │");
        System.out.println("├─────────────────────────────────────────────────────────┤");
        System.out.printf("│ Total Events:      %-37d │%n", logger.getTotalEvents());
        System.out.printf("│ Total Attacks:     %-37d │%n", logger.getTotalAttacks());
        System.out.printf("│ Total Defenses:    %-37d │%n", logger.getTotalDefenses());
        System.out.printf("│ Total Alerts:      %-37d │%n", logger.getTotalAlerts());
        System.out.printf("│ Most Common Attack: %-36s │%n", analyzer.getMostCommonAttack());
        System.out.printf("│ Threat Score:      %-37d │%n", analyzer.calculateThreatScore());
        System.out.printf("│ Threat Level:      %-37s │%n", analyzer.getThreatLevel());
        System.out.println("└─────────────────────────────────────────────────────────┘");

        analyzer.generateReport();
    }

    // Generate detailed report to file
    public void generateDetailedReport(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("╔════════════════════════════════════════════════════════════════╗");
            writer.println("║                    DETAILED SECURITY REPORT                    ║");
            writer.println("║                    Generated: " + LocalDateTime.now().format(formatter) + "              ║");
            writer.println("╚════════════════════════════════════════════════════════════════╝");
            writer.println();

            // Write statistics
            writer.println("=== STATISTICS ===");
            writer.println("Total Events: " + logger.getTotalEvents());
            writer.println("Total Attacks: " + logger.getTotalAttacks());
            writer.println("Total Defenses: " + logger.getTotalDefenses());
            writer.println("Total Alerts: " + logger.getTotalAlerts());
            writer.println();

            // Write all log entries
            writer.println("=== DETAILED LOGS ===");
            for (LogEntry entry : logger.getAllLogEntries()) {
                writer.println(entry.toString());
            }
            writer.println();

            // Write attack summary
            writer.println("=== ATTACK SUMMARY ===");
            Map<String, Integer> attackFreq = analyzer.getAttackFrequency();
            for (Map.Entry<String, Integer> entry : attackFreq.entrySet()) {
                writer.println(entry.getKey() + ": " + entry.getValue() + " occurrence(s)");
            }
            writer.println();

            // Write detected patterns
            writer.println("=== DETECTED PATTERNS ===");
            List<String> patterns = analyzer.getDetectedPatterns();
            if (patterns.isEmpty()) {
                writer.println("No suspicious patterns detected.");
            } else {
                for (String pattern : patterns) {
                    writer.println("- " + pattern);
                }
            }
            writer.println();

            // Write recommendations
            writer.println("=== RECOMMENDATIONS ===");
            writeRecommendations(writer);

            System.out.println("[ReportGenerator] Detailed report saved to: " + filename);
            logger.logEvent("Detailed report generated: " + filename);

        } catch (Exception e) {
            System.err.println("[ReportGenerator] Error generating report: " + e.getMessage());
        }
    }

    // Write recommendations based on analysis
    private void writeRecommendations(PrintWriter writer) {
        int threatScore = analyzer.calculateThreatScore();

        if (threatScore >= 75) {
            writer.println("⚠️ CRITICAL: Immediate action required!");
            writer.println("1. Conduct emergency security audit");
            writer.println("2. Isolate affected systems");
            writer.println("3. Implement additional firewall rules");
            writer.println("4. Reset all compromised credentials");
            writer.println("5. Notify incident response team");
        } else if (threatScore >= 50) {
            writer.println("⚠️ HIGH: Security posture needs improvement");
            writer.println("1. Review and update firewall rules");
            writer.println("2. Enforce stronger password policies");
            writer.println("3. Enable additional logging");
            writer.println("4. Schedule security training");
        } else if (threatScore >= 25) {
            writer.println("ℹ️ MEDIUM: Some improvements recommended");
            writer.println("1. Regular log review schedule");
            writer.println("2. Update detection signatures");
            writer.println("3. Conduct periodic vulnerability scans");
        } else {
            writer.println("✓ LOW: Security posture is good");
            writer.println("1. Continue regular monitoring");
            writer.println("2. Maintain current security measures");
        }
    }

    // Generate security incident report
    public void generateIncidentReport(String filename, String incidentId, String description) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("INCIDENT REPORT");
            writer.println("===============");
            writer.println("Incident ID: " + incidentId);
            writer.println("Date/Time: " + LocalDateTime.now().format(formatter));
            writer.println("Description: " + description);
            writer.println();

            writer.println("AFFECTED SYSTEMS:");
            writer.println("- Security events during incident period");
            writer.println();

            writer.println("ROOT CAUSE ANALYSIS:");
            writer.println("- " + analyzer.getMostCommonAttack() + " identified as primary threat");
            writer.println();

            writer.println("CORRECTIVE ACTIONS:");
            writer.println("1. Security patches applied");
            writer.println("2. Firewall rules updated");
            writer.println("3. Access controls reviewed");
            writer.println();

            writer.println("STATUS: Investigation in progress");

            System.out.println("[ReportGenerator] Incident report saved: " + filename);
            logger.logEvent("Incident report generated: " + incidentId);

        } catch (Exception e) {
            System.err.println("[ReportGenerator] Error generating incident report: " + e.getMessage());
        }
    }

    // Generate executive summary (simplified)
    public void generateExecutiveSummary() {
        System.out.println("\n╔══════════════════════════════════════════════════════════╗");
        System.out.println("║                 EXECUTIVE SUMMARY                         ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");

        System.out.println("\nDear Security Team,");
        System.out.println("\nThis report summarizes the security events from the past simulation period.\n");

        System.out.println("Key Findings:");
        System.out.println("-------------");
        System.out.printf("• Total security events: %d\n", logger.getTotalEvents());
        System.out.printf("• Unique attack types: %d\n", analyzer.getAttackFrequency().size());
        System.out.printf("• Overall threat level: %s\n", analyzer.getThreatLevel());
        System.out.printf("• Patterns detected: %d\n", analyzer.getDetectedPatterns().size());

        System.out.println("\nRecommendations:");
        System.out.println("----------------");
        if (analyzer.calculateThreatScore() > 30) {
            System.out.println("• Increase monitoring frequency");
            System.out.println("• Review security policies");
            System.out.println("• Conduct security awareness training");
        } else {
            System.out.println("• Maintain current security posture");
            System.out.println("• Continue regular log reviews");
        }

        System.out.println("\nFor detailed analysis, please refer to the attached report.");
        System.out.println("\nRespectfully,");
        System.out.println("Cybersecurity Attack Simulator");
    }

    // Generate HTML report
    public void generateHTMLReport(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("<!DOCTYPE html>");
            writer.println("<html>");
            writer.println("<head>");
            writer.println("<title>Security Report</title>");
            writer.println("<style>");
            writer.println("body { font-family: Arial, sans-serif; margin: 20px; }");
            writer.println("h1 { color: #333; }");
            writer.println("table { border-collapse: collapse; width: 100%; }");
            writer.println("th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }");
            writer.println("th { background-color: #4CAF50; color: white; }");
            writer.println(".critical { background-color: #ffcccc; }");
            writer.println(".high { background-color: #ffe6cc; }");
            writer.println(".medium { background-color: #ffffcc; }");
            writer.println(".low { background-color: #ccffcc; }");
            writer.println("</style>");
            writer.println("</head>");
            writer.println("<body>");
            writer.println("<h1>Cybersecurity Attack Simulator - Security Report</h1>");
            writer.println("<p>Generated: " + LocalDateTime.now().format(formatter) + "</p>");

            writer.println("<h2>Statistics</h2>");
            writer.println("<ul>");
            writer.println("<li>Total Events: " + logger.getTotalEvents() + "</li>");
            writer.println("<li>Total Attacks: " + logger.getTotalAttacks() + "</li>");
            writer.println("<li>Total Defenses: " + logger.getTotalDefenses() + "</li>");
            writer.println("<li>Threat Level: " + analyzer.getThreatLevel() + "</li>");
            writer.println("</ul>");

            writer.println("<h2>Log Entries</h2>");
            writer.println("<table>");
            writer.println("<tr><th>Timestamp</th><th>Type</th><th>Message</th><th>Severity</th><th>Success</th></tr>");

            for (LogEntry entry : logger.getAllLogEntries()) {
                writer.println(entry.toHTMLRow());
            }

            writer.println("</table>");
            writer.println("</body>");
            writer.println("</html>");

            System.out.println("[ReportGenerator] HTML report saved to: " + filename);

        } catch (Exception e) {
            System.err.println("[ReportGenerator] Error generating HTML report: " + e.getMessage());
        }
    }
}