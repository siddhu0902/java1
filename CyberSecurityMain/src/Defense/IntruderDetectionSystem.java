package Defense;

import Attack.Attack;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Intrusion Detection System (IDS) - Monitors network traffic for suspicious activity
 * Demonstrates pattern recognition and anomaly detection
 */
public class IntrusionDetectionSystem implements DefenseStrategy {

    private String defenseName;
    private int defenseLevel;
    private boolean active;
    private int threatsBlocked;
    private List<String> alertLog;
    private Map<String, Integer> trafficPatterns;
    private List<DetectionRule> detectionRules;
    private boolean anomalyDetected;
    private int alertsSent;

    // Inner class for detection rules
    public static class DetectionRule {
        private String ruleId;
        private String signature;
        private String severity; // LOW, MEDIUM, HIGH, CRITICAL
        private String action; // LOG, ALERT, BLOCK

        public DetectionRule(String ruleId, String signature, String severity, String action) {
            this.ruleId = ruleId;
            this.signature = signature;
            this.severity = severity;
            this.action = action;
        }

        public String getRuleId() { return ruleId; }
        public String getSignature() { return signature; }
        public String getSeverity() { return severity; }
        public String getAction() { return action; }

        public boolean matches(String trafficData) {
            return trafficData.toLowerCase().contains(signature.toLowerCase());
        }
    }

    // Constructor
    public IntrusionDetectionSystem() {
        this.defenseName = "Network Intrusion Detection System (NIDS)";
        this.defenseLevel = 9;
        this.active = true;
        this.threatsBlocked = 0;
        this.alertLog = new ArrayList<>();
        this.trafficPatterns = new HashMap<>();
        this.detectionRules = new ArrayList<>();
        this.anomalyDetected = false;
        this.alertsSent = 0;
        initializeDetectionRules();
        System.out.println("[IDS] Initialized with " + detectionRules.size() + " detection rules");
    }

    private void initializeDetectionRules() {
        detectionRules.add(new DetectionRule("IDS001", "SQL Injection", "HIGH", "BLOCK"));
        detectionRules.add(new DetectionRule("IDS002", "XSS", "MEDIUM", "ALERT"));
        detectionRules.add(new DetectionRule("IDS003", "Brute Force", "HIGH", "BLOCK"));
        detectionRules.add(new DetectionRule("IDS004", "Port Scan", "MEDIUM", "LOG"));
        detectionRules.add(new DetectionRule("IDS005", "Malware", "CRITICAL", "BLOCK"));
        detectionRules.add(new DetectionRule("IDS006", "Buffer Overflow", "CRITICAL", "BLOCK"));
        detectionRules.add(new DetectionRule("IDS007", "Privilege Escalation", "HIGH", "ALERT"));
        detectionRules.add(new DetectionRule("IDS008", "DoS Attack", "HIGH", "BLOCK"));
        detectionRules.add(new DetectionRule("IDS009", "Suspicious User Agent", "LOW", "LOG"));
        detectionRules.add(new DetectionRule("IDS010", "Known Exploit", "CRITICAL", "BLOCK"));
    }

    @Override
    public void applyDefense() {
        System.out.println("\n┌─────────────────────────────────────────────────────────┐");
        System.out.println("│ INTRUSION DETECTION SYSTEM ACTIVATED                     │");
        System.out.println("└─────────────────────────────────────────────────────────┘");
        System.out.println("[IDS] " + defenseName + " is monitoring");
        System.out.println("[IDS] Detection rules loaded: " + detectionRules.size());
        System.out.println("[IDS] Monitoring mode: " + (active ? "ACTIVE" : "PASSIVE"));
        System.out.println("[IDS] Alerts sent to date: " + alertsSent);
    }

    @Override
    public void applyDefense(Attack attack) {
        System.out.println("\n┌─────────────────────────────────────────────────────────┐");
        System.out.println("│ IDS ANALYZING ATTACK                                     │");
        System.out.println("└─────────────────────────────────────────────────────────┘");

        System.out.println("[IDS] Attack detected: " + attack.getAttackType());
        System.out.println("[IDS] Attack severity: " + attack.getSeverityLevel() + "/10");

        // Analyze attack type
        String attackType = attack.getAttackType().toLowerCase();

        if (attackType.contains("brute")) {
            analyzeBruteForceAttack();
        } else if (attackType.contains("dos") || attackType.contains("flood")) {
            analyzeDosAttack();
        } else if (attackType.contains("phish")) {
            analyzePhishingAttack();
        } else if (attackType.contains("mitm")) {
            analyzeMitmAttack();
        } else {
            analyzeGenericAttack();
        }

        threatsBlocked++;
    }

    private void analyzeBruteForceAttack() {
        System.out.println("\n[IDS] Pattern Analysis: BRUTE FORCE");
        System.out.println("[IDS] Signature match: Multiple failed login attempts");
        System.out.println("[IDS] Anomaly score: 85/100");
        System.out.println("[IDS] Recommended action: Rate limiting + Account lockout");
        sendAlert("Brute force attack detected on user account");
    }

    private void analyzeDosAttack() {
        System.out.println("\n[IDS] Pattern Analysis: DoS ATTACK");
        System.out.println("[IDS] Signature match: Traffic spike detected");
        System.out.println("[IDS] Packet rate: " + (int)(Math.random() * 50000) + " packets/sec");
        System.out.println("[IDS] Anomaly score: 95/100");
        System.out.println("[IDS] Recommended action: Enable rate limiting + SYN cookies");
        sendAlert("DoS attack detected - traffic volume异常");
    }

    private void analyzePhishingAttack() {
        System.out.println("\n[IDS] Pattern Analysis: PHISHING");
        System.out.println("[IDS] Signature match: Suspicious email content");
        System.out.println("[IDS] URL reputation: MALICIOUS");
        System.out.println("[IDS] Anomaly score: 70/100");
        System.out.println("[IDS] Recommended action: Block domain + User awareness training");
        sendAlert("Phishing attempt detected - malicious URL");
    }

    private void analyzeMitmAttack() {
        System.out.println("\n[IDS] Pattern Analysis: MITM ATTACK");
        System.out.println("[IDS] Signature match: ARP cache poisoning detected");
        System.out.println("[IDS] Certificate validation: FAILED");
        System.out.println("[IDS] Anomaly score: 90/100");
        System.out.println("[IDS] Recommended action: Enforce HTTPS + Certificate pinning");
        sendAlert("Man-in-the-Middle attack detected");
    }

    private void analyzeGenericAttack() {
        System.out.println("\n[IDS] Pattern Analysis: UNKNOWN TRAFFIC");
        System.out.println("[IDS] Running heuristic analysis...");
        double anomalyScore = Math.random() * 100;
        System.out.println("[IDS] Anomaly score: " + String.format("%.2f", anomalyScore) + "/100");

        if (anomalyScore > 75) {
            System.out.println("[IDS] ⚠ HIGH ANOMALY - Potential zero-day attack");
            sendAlert("Unusual traffic pattern detected - possible unknown attack");
        } else {
            System.out.println("[IDS] Traffic appears normal");
        }
    }

    // Monitor traffic (simulates real-time monitoring)
    public void monitorTraffic(String trafficData) {
        System.out.println("\n[IDS] Monitoring traffic: " + trafficData.substring(0, Math.min(50, trafficData.length())) + "...");

        // Update traffic patterns
        String pattern = trafficData.length() > 10 ? trafficData.substring(0, 10) : trafficData;
        trafficPatterns.put(pattern, trafficPatterns.getOrDefault(pattern, 0) + 1);

        // Check against detection rules
        for (DetectionRule rule : detectionRules) {
            if (rule.matches(trafficData)) {
                System.out.println("[IDS] ⚠ RULE MATCH: " + rule.getRuleId() + " - " + rule.getSignature());
                System.out.println("[IDS] Severity: " + rule.getSeverity());
                System.out.println("[IDS] Action: " + rule.getAction());

                if (rule.getAction().equals("ALERT")) {
                    sendAlert(rule.getSignature() + " detected");
                } else if (rule.getAction().equals("BLOCK")) {
                    System.out.println("[IDS] ✗ Traffic BLOCKED - " + rule.getSignature());
                    threatsBlocked++;
                }
            }
        }

        // Check for anomalies
        detectAnomaly();
    }

    @Override
    public boolean detectThreat() {
        System.out.println("\n[IDS] Running comprehensive threat detection...");

        boolean threatDetected = false;

        // Simulate various detection methods
        System.out.println("\n[IDS] Signature-based detection:");
        boolean signatureMatch = Math.random() > 0.7;
        System.out.println("  • Known attack signatures: " + (signatureMatch ? "MATCH FOUND" : "No match"));

        System.out.println("\n[IDS] Anomaly-based detection:");
        double anomalyScore = Math.random();
        boolean anomalyMatch = anomalyScore > 0.75;
        System.out.println("  • Baseline deviation: " + String.format("%.2f", anomalyScore * 100) + "%");
        System.out.println("  • Anomaly detected: " + (anomalyMatch ? "YES" : "NO"));

        System.out.println("\n[IDS] Heuristic analysis:");
        boolean heuristicMatch = Math.random() > 0.8;
        System.out.println("  • Suspicious behavior: " + (heuristicMatch ? "DETECTED" : "Not detected"));

        threatDetected = signatureMatch || anomalyMatch || heuristicMatch;

        if (threatDetected) {
            System.out.println("\n[IDS] ⚠⚠⚠ THREAT DETECTED! ⚠⚠⚠");
            sendAlert("Intrusion detected - Immediate action required");
        } else {
            System.out.println("\n[IDS] ✓ No threats detected - System secure");
        }

        return threatDetected;
    }

    // Detect anomalies in traffic patterns
    public void detectAnomaly() {
        // Check for sudden spikes in similar traffic
        for (Map.Entry<String, Integer> entry : trafficPatterns.entrySet()) {
            if (entry.getValue() > 100) {
                anomalyDetected = true;
                System.out.println("[IDS] ⚠ ANOMALY: Traffic spike detected for pattern: " + entry.getKey());
                System.out.println("[IDS] Frequency: " + entry.getValue() + " occurrences");
                sendAlert("Traffic anomaly detected - possible attack in progress");
                break;
            }
        }
    }

    // Send alert to admin
    public void alertAdmin() {
        System.out.println("\n[IDS] 📢 SENDING ADMIN ALERT 📢");
        System.out.println("[IDS] Alert type: URGENT SECURITY NOTIFICATION");
        System.out.println("[IDS] Timestamp: " + java.time.LocalDateTime.now());
        System.out.println("[IDS] Recommended action: Investigate immediately");
        alertsSent++;
        System.out.println("[IDS] Total alerts sent: " + alertsSent);
    }

    private void sendAlert(String message) {
        alertLog.add(message);
        alertsSent++;
        System.out.println("[IDS] 🔔 ALERT: " + message);

        // For high severity, also alert admin
        if (message.contains("CRITICAL") || message.contains("critical")) {
            alertAdmin();
        }
    }

    // Get alert history
    public void getAlertHistory() {
        System.out.println("\n=== IDS ALERT HISTORY ===");
        if (alertLog.isEmpty()) {
            System.out.println("No alerts recorded");
        } else {
            for (int i = 0; i < alertLog.size(); i++) {
                System.out.println((i + 1) + ". " + alertLog.get(i));
            }
        }
    }

    // Clear alert log
    public void clearAlertLog() {
        alertLog.clear();
        System.out.println("[IDS] Alert log cleared");
    }

    // Add custom detection rule
    public void addDetectionRule(DetectionRule rule) {
        detectionRules.add(rule);
        System.out.println("[IDS] Detection rule added: " + rule.getRuleId());
    }

    // List all detection rules
    public void listDetectionRules() {
        System.out.println("\n┌─────────────────────────────────────────────────────────┐");
        System.out.println("│ IDS DETECTION RULES                                     │");
        System.out.println("├─────────────────────────────────────────────────────────┤");
        for (DetectionRule rule : detectionRules) {
            System.out.printf("│ %-6s │ %-15s │ %-8s │ %-5s │%n",
                    rule.getRuleId(),
                    truncate(rule.getSignature(), 15),
                    rule.getSeverity(),
                    rule.getAction());
        }
        System.out.println("└─────────────────────────────────────────────────────────┘");
    }

    // Get traffic statistics
    public void getTrafficStats() {
        System.out.println("\n=== TRAFFIC STATISTICS ===");
        System.out.println("Unique traffic patterns: " + trafficPatterns.size());
        System.out.println("Total packets analyzed: " + trafficPatterns.values().stream().mapToInt(Integer::intValue).sum());
        System.out.println("Active detection rules: " + detectionRules.size());
        System.out.println("Alerts generated: " + alertsSent);
        System.out.println("Threats blocked: " + threatsBlocked);
    }

    private String truncate(String str, int length) {
        if (str.length() <= length) return str;
        return str.substring(0, length - 3) + "...";
    }

    @Override
    public String getDefenseName() {
        return defenseName;
    }

    @Override
    public int getDefenseLevel() {
        return defenseLevel;
    }

    @Override
    public String getDescription() {
        return "An Intrusion Detection System (IDS) monitors network traffic for suspicious " +
                "activity and known attack signatures. It can detect various attacks including " +
                "brute force, DoS, and malware infections.";
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public void setActive(boolean active) {
        this.active = active;
        System.out.println("[IDS] System " + (active ? "ACTIVATED" : "DEACTIVATED"));
    }

    @Override
    public int getThreatsBlocked() {
        return threatsBlocked;
    }

    @Override
    public void resetStats() {
        threatsBlocked = 0;
        alertsSent = 0;
        trafficPatterns.clear();
        System.out.println("[IDS] Statistics reset");
    }

    public boolean isAnomalyDetected() {
        return anomalyDetected;
    }

    public int getAlertsSent() {
        return alertsSent;
    }
}