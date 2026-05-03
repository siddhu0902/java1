package Defense;

import Attack.Attack;
import java.util.ArrayList;
import java.util.List;

/**
 * Firewall - Network security system that monitors and controls traffic
 * based on predefined rules. Demonstrates rule-based filtering.
 */
public class Firewall implements DefenseStrategy {

    private String defenseName;
    private int defenseLevel;
    private boolean active;
    private int threatsBlocked;
    private List<String> blockedIPs;
    private List<String> allowedIPs;
    private List<FirewallRule> rules;
    private boolean packetFilteringEnabled;

    // Inner class for firewall rules
    public static class FirewallRule {
        private String ruleId;
        private String sourceIP;
        private String destinationIP;
        private int port;
        private String protocol; // TCP, UDP, ICMP
        private String action; // ALLOW, DENY
        private String description;

        public FirewallRule(String ruleId, String sourceIP, String destinationIP,
                            int port, String protocol, String action, String description) {
            this.ruleId = ruleId;
            this.sourceIP = sourceIP;
            this.destinationIP = destinationIP;
            this.port = port;
            this.protocol = protocol;
            this.action = action;
            this.description = description;
        }

        public String getRuleId() { return ruleId; }
        public String getSourceIP() { return sourceIP; }
        public String getDestinationIP() { return destinationIP; }
        public int getPort() { return port; }
        public String getProtocol() { return protocol; }
        public String getAction() { return action; }
        public String getDescription() { return description; }

        public boolean matches(String sourceIP, String destIP, int port, String protocol) {
            boolean sourceMatch = this.sourceIP.equals("*") || this.sourceIP.equals(sourceIP);
            boolean destMatch = this.destinationIP.equals("*") || this.destinationIP.equals(destIP);
            boolean portMatch = this.port == -1 || this.port == port;
            boolean protocolMatch = this.protocol.equals("*") || this.protocol.equalsIgnoreCase(protocol);
            return sourceMatch && destMatch && portMatch && protocolMatch;
        }

        @Override
        public String toString() {
            return String.format("Rule[%s]: %s -> %s:%d (%s) = %s",
                    ruleId, sourceIP, destinationIP, port, protocol, action);
        }
    }

    // Constructor
    public Firewall() {
        this.defenseName = "Enterprise Firewall";
        this.defenseLevel = 8;
        this.active = true;
        this.threatsBlocked = 0;
        this.blockedIPs = new ArrayList<>();
        this.allowedIPs = new ArrayList<>();
        this.rules = new ArrayList<>();
        this.packetFilteringEnabled = true;
        initializeDefaultRules();
        System.out.println("[Firewall] Initialized with " + rules.size() + " default rules");
    }

    private void initializeDefaultRules() {
        // Default security rules
        rules.add(new FirewallRule("R001", "*", "*", 445, "TCP", "DENY", "Block SMB (Ransomware protection)"));
        rules.add(new FirewallRule("R002", "*", "*", 3389, "TCP", "DENY", "Block RDP from external"));
        rules.add(new FirewallRule("R003", "*", "*", 23, "TCP", "DENY", "Block Telnet (insecure)"));
        rules.add(new FirewallRule("R004", "*", "*", 22, "TCP", "ALLOW", "Allow SSH"));
        rules.add(new FirewallRule("R005", "*", "*", 80, "TCP", "ALLOW", "Allow HTTP"));
        rules.add(new FirewallRule("R006", "*", "*", 443, "TCP", "ALLOW", "Allow HTTPS"));
        rules.add(new FirewallRule("R007", "10.0.0.0/8", "*", -1, "*", "ALLOW", "Allow internal network"));
        rules.add(new FirewallRule("R008", "*", "192.168.1.1", 3306, "TCP", "DENY", "Block database from external"));

        // Block known malicious IPs (simulated)
        blockedIPs.add("45.33.22.11");
        blockedIPs.add("185.130.5.253");
        blockedIPs.add("94.102.61.78");
        blockedIPs.add("193.42.33.18");
    }

    @Override
    public void applyDefense() {
        System.out.println("\n┌─────────────────────────────────────────────────────────┐");
        System.out.println("│ FIREWALL DEFENSE ACTIVATED                               │");
        System.out.println("└─────────────────────────────────────────────────────────┘");
        System.out.println("[Firewall] " + defenseName + " is running");
        System.out.println("[Firewall] Packet filtering: " + (packetFilteringEnabled ? "ENABLED" : "DISABLED"));
        System.out.println("[Firewall] Active rules: " + rules.size());
        System.out.println("[Firewall] Blocked IPs: " + blockedIPs.size());
        System.out.println("[Firewall] Allowed IPs: " + allowedIPs.size());
        System.out.println("[Firewall] Threats blocked to date: " + threatsBlocked);
    }

    @Override
    public void applyDefense(Attack attack) {
        System.out.println("\n┌─────────────────────────────────────────────────────────┐");
        System.out.println("│ FIREWALL RESPONDING TO ATTACK                            │");
        System.out.println("└─────────────────────────────────────────────────────────┘");

        System.out.println("[Firewall] Detected attack: " + attack.getAttackType());
        System.out.println("[Firewall] Attack severity: " + attack.getSeverityLevel() + "/10");

        // Analyze attack type and respond
        String attackType = attack.getAttackType().toLowerCase();

        if (attackType.contains("brute")) {
            handleBruteForceAttack();
        } else if (attackType.contains("dos") || attackType.contains("flood")) {
            handleDosAttack();
        } else if (attackType.contains("phish")) {
            handlePhishingAttempt();
        } else {
            handleGenericAttack();
        }

        threatsBlocked++;
        System.out.println("[Firewall] Attack mitigated! Total threats blocked: " + threatsBlocked);
    }

    private void handleBruteForceAttack() {
        System.out.println("\n[Firewall] BRUTE FORCE DETECTED!");
        System.out.println("[Firewall] Implementing rate limiting on suspicious IPs");
        System.out.println("[Firewall] Adding temporary block for repeated failures");
        blockIP("192.168.1." + (int)(Math.random() * 255));
        System.out.println("[Firewall] Brute force attack neutralized");
    }

    private void handleDosAttack() {
        System.out.println("\n[Firewall] DoS ATTACK DETECTED!");
        System.out.println("[Firewall] Activating DDoS protection mode");
        System.out.println("[Firewall] Enabling SYN cookies");
        System.out.println("[Firewall] Throttling incoming traffic from suspicious sources");
        System.out.println("[Firewall] DoS attack mitigated - traffic normalized");
    }

    private void handlePhishingAttempt() {
        System.out.println("\n[Firewall] PHISHING ATTEMPT DETECTED!");
        System.out.println("[Firewall] Blocking known phishing domains");
        System.out.println("[Firewall] Scanning email attachments");
        System.out.println("[Firewall] Phishing attempt blocked");
    }

    private void handleGenericAttack() {
        System.out.println("\n[Firewall] SUSPICIOUS TRAFFIC DETECTED!");
        System.out.println("[Firewall] Applying default deny policy");
        System.out.println("[Firewall] Logging incident for review");
    }

    @Override
    public boolean detectThreat() {
        System.out.println("\n[Firewall] Running threat detection scan...");

        // Simulate threat detection
        double threatScore = Math.random();
        boolean threatDetected = threatScore > 0.3;

        if (threatDetected) {
            System.out.println("[Firewall] ⚠ THREAT DETECTED! Threat score: " + String.format("%.2f", threatScore));
            System.out.println("[Firewall] Taking defensive actions...");
        } else {
            System.out.println("[Firewall] ✓ No threats detected. Threat score: " + String.format("%.2f", threatScore));
        }

        return threatDetected;
    }

    // Block an IP address
    public void blockIP(String ipAddress) {
        if (!blockedIPs.contains(ipAddress)) {
            blockedIPs.add(ipAddress);
            System.out.println("[Firewall] IP BLOCKED: " + ipAddress);
        } else {
            System.out.println("[Firewall] IP already blocked: " + ipAddress);
        }
    }

    // Unblock an IP address
    public void unblockIP(String ipAddress) {
        if (blockedIPs.remove(ipAddress)) {
            System.out.println("[Firewall] IP UNBLOCKED: " + ipAddress);
        } else {
            System.out.println("[Firewall] IP not found in blocklist: " + ipAddress);
        }
    }

    // Allow traffic from IP
    public void allowTraffic(String ipAddress) {
        if (!allowedIPs.contains(ipAddress)) {
            allowedIPs.add(ipAddress);
            System.out.println("[Firewall] Traffic ALLOWED from: " + ipAddress);
        }
    }

    // Deny traffic from IP
    public void denyTraffic(String ipAddress) {
        blockIP(ipAddress);
        allowedIPs.remove(ipAddress);
        System.out.println("[Firewall] Traffic DENIED from: " + ipAddress);
    }

    // Filter packets (simulate packet inspection)
    public boolean filterPackets(String sourceIP, String destIP, int port, String protocol, String payload) {
        System.out.println("\n[Firewall] Filtering packet: " + sourceIP + " → " + destIP + ":" + port + " (" + protocol + ")");

        // Check if source IP is blocked
        if (blockedIPs.contains(sourceIP)) {
            System.out.println("[Firewall] ✗ Packet DROPPED - Source IP is blocked");
            threatsBlocked++;
            return false;
        }

        // Check if destination IP is blocked
        if (blockedIPs.contains(destIP)) {
            System.out.println("[Firewall] ✗ Packet DROPPED - Destination IP is blocked");
            threatsBlocked++;
            return false;
        }

        // Apply firewall rules
        for (FirewallRule rule : rules) {
            if (rule.matches(sourceIP, destIP, port, protocol)) {
                if (rule.action.equals("DENY")) {
                    System.out.println("[Firewall] ✗ Packet DROPPED - Rule: " + rule.getDescription());
                    threatsBlocked++;
                    return false;
                } else if (rule.action.equals("ALLOW")) {
                    System.out.println("[Firewall] ✓ Packet ALLOWED - Rule: " + rule.getDescription());
                    return true;
                }
            }
        }

        // Default deny
        System.out.println("[Firewall] ✗ Packet DROPPED - No matching allow rule");
        threatsBlocked++;
        return false;
    }

    // Add custom rule
    public void addRule(FirewallRule rule) {
        rules.add(rule);
        System.out.println("[Firewall] Rule added: " + rule);
    }

    // Remove rule
    public void removeRule(String ruleId) {
        rules.removeIf(rule -> rule.getRuleId().equals(ruleId));
        System.out.println("[Firewall] Rule removed: " + ruleId);
    }

    // List all rules
    public void listRules() {
        System.out.println("\n┌─────────────────────────────────────────────────────────┐");
        System.out.println("│ FIREWALL RULES                                          │");
        System.out.println("├─────────────────────────────────────────────────────────┤");
        for (FirewallRule rule : rules) {
            System.out.println("│ " + rule);
        }
        System.out.println("└─────────────────────────────────────────────────────────┘");
    }

    // List blocked IPs
    public void listBlockedIPs() {
        System.out.println("\n=== BLOCKED IP ADDRESSES ===");
        if (blockedIPs.isEmpty()) {
            System.out.println("No IPs currently blocked");
        } else {
            for (String ip : blockedIPs) {
                System.out.println("  • " + ip);
            }
        }
    }

    // Enable/Disable packet filtering
    public void setPacketFilteringEnabled(boolean enabled) {
        this.packetFilteringEnabled = enabled;
        System.out.println("[Firewall] Packet filtering " + (enabled ? "ENABLED" : "DISABLED"));
    }

    // Demonstrate firewall security features
    public void demonstrateSecurityFeatures() {
        System.out.println("\n=== FIREWALL SECURITY FEATURES ===");
        System.out.println("1. Stateful Packet Inspection (SPI)");
        System.out.println("2. Network Address Translation (NAT)");
        System.out.println("3. Access Control Lists (ACLs)");
        System.out.println("4. Deep Packet Inspection (DPI)");
        System.out.println("5. VPN Support");
        System.out.println("6. Intrusion Prevention System (IPS) integration");
        System.out.println("7. Application-aware filtering");
        System.out.println("8. Geo-IP blocking");
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
        return "A firewall monitors and controls incoming/outgoing network traffic based on " +
                "predetermined security rules. It acts as a barrier between trusted internal " +
                "networks and untrusted external networks.";
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public void setActive(boolean active) {
        this.active = active;
        System.out.println("[Firewall] Defense " + (active ? "ACTIVATED" : "DEACTIVATED"));
    }

    @Override
    public int getThreatsBlocked() {
        return threatsBlocked;
    }

    @Override
    public void resetStats() {
        threatsBlocked = 0;
        System.out.println("[Firewall] Statistics reset");
    }
}