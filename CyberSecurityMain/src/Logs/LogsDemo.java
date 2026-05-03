package Logs;

import Attack.BruteForceAttack;
import Attack.DoSAttack;
import Attack.PhishingAttack;
import Defense.Firewall;
import Defense.IntrusionDetectionSystem;
import Defense.RateLimiter;

/**
 * LogsDemo - Demonstration class for all logging features
 * Shows Singleton Pattern, Log Analysis, Alerts, and Reporting
 */
public class LogsDemo {

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║                  LOGS PACKAGE DEMONSTRATION              ║");
        System.out.println("║                                                          ║");
        System.out.println("║  OOP Concepts: Singleton Pattern, Log Analysis,         ║");
        System.out.println("║                Alert System, Report Generation          ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝\n");

        // Test 1: Singleton Logger
        System.out.println("=".repeat(60));
        System.out.println("TEST CASE 1: Singleton Logger - Multiple instances return same object");
        System.out.println("=".repeat(60));

        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();

        System.out.println("logger1 == logger2: " + (logger1 == logger2));
        System.out.println("Both refer to the same Singleton instance!");

        // Test 2: Logging various events
        System.out.println("\n" + "=".repeat(60));
        System.out.println("TEST CASE 2: Logging Events");
        System.out.println("=".repeat(60));

        logger1.logEvent("System started");
        logger1.logEvent("User 'admin' logged in");
        logger1.info("This is an info message");
        logger1.warning("This is a warning message");
        logger1.error("This is an error message");

        // Test 3: Logging Attacks
        System.out.println("\n" + "=".repeat(60));
        System.out.println("TEST CASE 3: Logging Attacks");
        System.out.println("=".repeat(60));

        BruteForceAttack bruteForce = new BruteForceAttack();
        DoSAttack dosAttack = new DoSAttack();
        PhishingAttack phishing = new PhishingAttack();

        bruteForce.execute("test_target");
        logger1.logAttack(bruteForce);

        dosAttack.execute("server");
        logger1.logAttack(dosAttack);

        phishing.execute("user@example.com");
        logger1.logAttack(phishing);

        // Test 4: Logging Defenses
        System.out.println("\n" + "=".repeat(60));
        System.out.println("TEST CASE 4: Logging Defenses");
        System.out.println("=".repeat(60));

        Firewall firewall = new Firewall();
        IntrusionDetectionSystem ids = new IntrusionDetectionSystem();
        RateLimiter rateLimiter = new RateLimiter();

        logger1.logDefense(firewall);
        logger1.logDefense(ids);
        logger1.logDefense(rateLimiter);

        // Test 5: Log Analyzer
        System.out.println("\n" + "=".repeat(60));
        System.out.println("TEST CASE 5: Log Analyzer - Pattern Detection");
        System.out.println("=".repeat(60));

        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.analyzeLogs();
        analyzer.generateReport();

        System.out.println("\nMost Common Attack: " + analyzer.getMostCommonAttack());
        System.out.println("Threat Score: " + analyzer.calculateThreatScore());
        System.out.println("Threat Level: " + analyzer.getThreatLevel());

        // Test 6: Alert System
        System.out.println("\n" + "=".repeat(60));
        System.out.println("TEST CASE 6: Alert System - Security Notifications");
        System.out.println("=".repeat(60));

        AlertSystem alertSystem = new AlertSystem();
        alertSystem.addRecipient("admin@company.com");
        alertSystem.addRecipient("security@company.com");

        alertSystem.sendAlert("Brute Force Attack Detected",
                "Multiple failed login attempts from IP 192.168.1.100", "HIGH");

        alertSystem.sendAlert("System Breach",
                "Unauthorized access detected on database server", "CRITICAL");

        alertSystem.sendAlert("Suspicious Activity",
                "Unusual outbound traffic detected", "MEDIUM");

        alertSystem.printAlertSummary();

        System.out.println("\nUnacknowledged Alerts: " + alertSystem.getUnacknowledgedAlerts().size());

        // Test 7: Report Generation
        System.out.println("\n" + "=".repeat(60));
        System.out.println("TEST CASE 7: Report Generation");
        System.out.println("=".repeat(60));

        ReportGenerator reportGen = new ReportGenerator();
        reportGen.generateSummaryReport();
        reportGen.generateExecutiveSummary();

        // Generate file reports
        reportGen.generateDetailedReport("security_report_detailed.txt");
        reportGen.generateIncidentReport("incident_001.txt", "INC-001", "Multiple brute force attacks detected");
        reportGen.generateHTMLReport("security_report.html");

        // Test 8: File Logging
        System.out.println("\n" + "=".repeat(60));
        System.out.println("TEST CASE 8: File Logging - Persistence");
        System.out.println("=".repeat(60));

        logger1.enableFileLogging();
        logger1.logEvent("Testing file logging functionality");
        logger1.logEvent("This event will be written to file");
        logger1.disableFileLogging();

        // Test 9: Export functionality
        System.out.println("\n" + "=".repeat(60));
        System.out.println("TEST CASE 9: Export Logs to CSV");
        System.out.println("=".repeat(60));

        logger1.exportToCSV("security_logs.csv");

        // Test 10: Logger Statistics
        System.out.println("\n" + "=".repeat(60));
        System.out.println("TEST CASE 10: Logger Statistics");
        System.out.println("=".repeat(60));

        logger1.printStatistics();

        // Summary
        System.out.println("\n╔══════════════════════════════════════════════════════════╗");
        System.out.println("║                    DEMO COMPLETED!                        ║");
        System.out.println("║                                                          ║");
        System.out.println("║  OOP Concepts Demonstrated in Logs Package:             ║");
        System.out.println("║  ✓ SINGLETON PATTERN (Logger single instance)           ║");
        System.out.println("║  ✓ ENCAPSULATION (LogEntry with private fields)         ║");
        System.out.println("║  ✓ OBSERVER PATTERN (AlertSystem notifications)         ║");
        System.out.println("║                                                          ║");
        System.out.println("║  Features Implemented:                                  ║");
        System.out.println("║  • Event/Attack/Defense Logging                         ║");
        System.out.println("║  • Log Analysis & Pattern Detection                     ║");
        System.out.println("║  • Alert System with Multiple Channels                  ║");
        System.out.println("║  • Report Generation (Text, HTML, CSV)                  ║");
        System.out.println("║  • File-based Log Persistence                           ║");
        System.out.println("║  • Threat Scoring & Risk Assessment                     ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");

        System.out.println("\n📁 Generated Files:");
        System.out.println("  • security_logs.txt - Main log file");
        System.out.println("  • security_report_detailed.txt - Detailed report");
        System.out.println("  • incident_001.txt - Incident report");
        System.out.println("  • security_report.html - HTML report");
        System.out.println("  • security_logs.csv - CSV export");
    }

    private static String repeat(String str, int times) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < times; i++) {
            sb.append(str);
        }
        return sb.toString();
    }
}