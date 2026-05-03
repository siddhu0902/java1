package Attack;

/**
 * Denial of Service (DoS) Attack - Simulates traffic overload
 * Demonstrates network flooding and resource exhaustion concepts
 */
public class DoSAttack implements Attack {

    private String attackType;
    private int severityLevel;
    private boolean success;
    private long attackDuration;
    private int packetsSent;
    private int targetPort;
    private String attackMethod; // SYN_FLOOD, HTTP_FLOOD, UDP_FLOOD
    private boolean serverCrashed;

    // Constructor
    public DoSAttack() {
        this.attackType = "Denial of Service (DoS) Attack";
        this.severityLevel = 9;
        this.success = false;
        this.packetsSent = 0;
        this.targetPort = 80;
        this.attackMethod = "SYN_FLOOD";
        this.serverCrashed = false;
    }

    public DoSAttack(String attackMethod, int targetPort) {
        this();
        this.attackMethod = attackMethod;
        this.targetPort = targetPort;
    }

    @Override
    public void execute(String target) {
        System.out.println("\n╔══════════════════════════════════════════════════════════╗");
        System.out.println("║         DENIAL OF SERVICE (DoS) ATTACK SIMULATION        ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");

        long startTime = System.currentTimeMillis();

        System.out.println("[*] Target: " + target);
        System.out.println("[*] Attack Type: " + attackType);
        System.out.println("[*] Attack Method: " + attackMethod);
        System.out.println("[*] Target Port: " + targetPort);

        // Perform different types of DoS attacks
        switch (attackMethod) {
            case "SYN_FLOOD":
                performSynFlood(target);
                break;
            case "HTTP_FLOOD":
                performHttpFlood(target);
                break;
            case "UDP_FLOOD":
                performUdpFlood(target);
                break;
            default:
                performSynFlood(target);
        }

        attackDuration = System.currentTimeMillis() - startTime;

        displayResults();
    }

    private void performSynFlood(String target) {
        System.out.println("\n[🌊] SYN FLOOD ATTACK INITIATED");
        System.out.println("Exploiting TCP three-way handshake vulnerability...");

        int maxPackets = 50000;
        int batchSize = 1000;

        for (int i = 0; i < maxPackets; i += batchSize) {
            packetsSent += batchSize;

            if (packetsSent % 10000 == 0) {
                System.out.println("[*] Sent " + packetsSent + " SYN packets to " + target + ":" + targetPort);
            }

            // Simulate server response
            if (packetsSent > 30000 && !serverCrashed) {
                serverCrashed = true;
                success = true;
                System.out.println("\n[!] SERVER CRASHED! Target overwhelmed by SYN requests");
                System.out.println("[!] Connection table full - legitimate users cannot connect");
                break;
            }

            // Simulate network delay
            try { Thread.sleep(5); } catch (InterruptedException e) {}
        }

        if (!success) {
            System.out.println("\n[-] Attack partially successful - server slowed but not crashed");
        }
    }

    private void performHttpFlood(String target) {
        System.out.println("\n[🌊] HTTP FLOOD ATTACK INITIATED");
        System.out.println("Overwhelming web server with HTTP requests...");

        int maxRequests = 100000;

        for (int i = 0; i < maxRequests; i += 1000) {
            packetsSent += 1000;

            if (packetsSent % 20000 == 0) {
                System.out.println("[*] Sent " + packetsSent + " HTTP requests to " + target);
            }

            // Simulate server degradation
            if (packetsSent > 50000 && !serverCrashed) {
                serverCrashed = true;
                success = true;
                System.out.println("\n[!] WEB SERVER CRASHED! HTTP requests queue full");
                break;
            }
        }
    }

    private void performUdpFlood(String target) {
        System.out.println("\n[🌊] UDP FLOOD ATTACK INITIATED");
        System.out.println("Flooding target with UDP packets...");

        int maxPackets = 80000;

        for (int i = 0; i < maxPackets; i += 5000) {
            packetsSent += 5000;

            if (packetsSent % 25000 == 0) {
                System.out.println("[*] Sent " + packetsSent + " UDP packets to " + target);
            }

            if (packetsSent > 60000 && !serverCrashed) {
                serverCrashed = true;
                success = true;
                System.out.println("\n[!] TARGET UNRESPONSIVE! Bandwidth exhausted");
                break;
            }
        }
    }

    public void demonstrateMitigationTechniques() {
        System.out.println("\n=== DoS ATTACK MITIGATION TECHNIQUES ===");
        System.out.println("1. Rate Limiting - Limit requests per IP");
        System.out.println("2. Web Application Firewall (WAF)");
        System.out.println("3. Load Balancers - Distribute traffic");
        System.out.println("4. Anycast Network Distribution");
        System.out.println("5. SYN Cookies - Prevent SYN flood");
        System.out.println("6. CAPTCHA - Differentiate humans from bots");
        System.out.println("7. CDN Services (Cloudflare, Akamai, etc.)");
    }

    public void demonstrateAttackImpact() {
        System.out.println("\n=== IMPACT OF SUCCESSFUL DoS ATTACK ===");
        System.out.println("💰 Financial Loss: $5,000 - $1,000,000+ per hour");
        System.out.println("📉 Customer Trust: Significant decrease");
        System.out.println("📊 Revenue Loss: 25-40% during attack");
        System.out.println("🏢 Reputation Damage: Long-term brand impact");
        System.out.println("⚖️ Legal Consequences: Potential lawsuits");
    }

    private void displayResults() {
        System.out.println("\n┌─────────────────────────────────────────┐");
        System.out.println("│ DoS ATTACK RESULTS                       │");
        System.out.println("├─────────────────────────────────────────┤");
        System.out.println("│ Attack Method: " + attackMethod);
        System.out.println("│ Packets/Requests Sent: " + packetsSent);
        System.out.println("│ Server Crashed: " + (serverCrashed ? "YES" : "NO"));
        System.out.println("│ Attack Successful: " + (success ? "YES" : "PARTIAL"));
        System.out.println("│ Attack Duration: " + attackDuration + " ms");
        System.out.println("│ Packets per second: " + (packetsSent * 1000 / Math.max(1, attackDuration)));
        System.out.println("└─────────────────────────────────────────┘");
    }

    @Override
    public String getAttackType() {
        return attackType + " (" + attackMethod + ")";
    }

    @Override
    public int getSeverityLevel() {
        return severityLevel;
    }

    @Override
    public String getDescription() {
        return "A Denial of Service (DoS) attack aims to make a machine or network resource " +
                "unavailable by overwhelming it with a flood of illegitimate requests, " +
                "consuming its resources and preventing legitimate users from accessing services.";
    }

    @Override
    public boolean isSuccessful() {
        return success;
    }

    @Override
    public long getAttackDuration() {
        return attackDuration;
    }

    @Override
    public void resetAttack() {
        success = false;
        attackDuration = 0;
        packetsSent = 0;
        serverCrashed = false;
        System.out.println("[*] DoSAttack reset");
    }

    public int getPacketsSent() {
        return packetsSent;
    }

    public void setAttackMethod(String method) {
        this.attackMethod = method;
    }

    public void setTargetPort(int port) {
        this.targetPort = port;
    }
}