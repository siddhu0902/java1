package Attack;

/**
 * Target class - Represents the entity being attacked
 */
public class Target {

    private String targetId;
    private String targetName;
    private String targetType;  // USER, SERVER, DATABASE, NETWORK
    private String ipAddress;
    private int securityLevel;   // 1-10 (1=weak, 10=strong)
    private boolean isCompromised;
    private int failedAttempts;

    // Constructor
    public Target(String targetId, String targetName, String targetType, String ipAddress) {
        this.targetId = targetId;
        this.targetName = targetName;
        this.targetType = targetType;
        this.ipAddress = ipAddress;
        this.securityLevel = 5;  // Default medium security
        this.isCompromised = false;
        this.failedAttempts = 0;
    }

    // Constructor with security level
    public Target(String targetId, String targetName, String targetType,
                  String ipAddress, int securityLevel) {
        this(targetId, targetName, targetType, ipAddress);
        this.securityLevel = Math.max(1, Math.min(10, securityLevel));
    }

    // Getters
    public String getTargetId() { return targetId; }
    public String getTargetName() { return targetName; }
    public String getTargetType() { return targetType; }
    public String getIpAddress() { return ipAddress; }
    public int getSecurityLevel() { return securityLevel; }
    public boolean isCompromised() { return isCompromised; }
    public int getFailedAttempts() { return failedAttempts; }

    // Methods to modify target state
    public void compromise() {
        this.isCompromised = true;
        System.out.println("[!] TARGET COMPROMISED: " + targetName);
    }

    public void incrementFailedAttempts() {
        this.failedAttempts++;
        System.out.println("[*] Failed attempt #" + failedAttempts + " on " + targetName);
    }

    public void resetFailedAttempts() {
        this.failedAttempts = 0;
        System.out.println("[*] Failed attempts reset for " + targetName);
    }

    public void setSecurityLevel(int level) {
        this.securityLevel = Math.max(1, Math.min(10, level));
        System.out.println("[*] Security level for " + targetName + " set to " + securityLevel);
    }

    // Check if attack can succeed based on security level
    public boolean canBeAttacked(int attackStrength) {
        // Attack succeeds if attack strength >= security level
        return attackStrength >= securityLevel;
    }

    public void displayTargetInfo() {
        System.out.println("\n┌─────────────────────────────────────────┐");
        System.out.println("│ TARGET INFORMATION                       │");
        System.out.println("├─────────────────────────────────────────┤");
        System.out.println("│ ID: " + targetId);
        System.out.println("│ Name: " + targetName);
        System.out.println("│ Type: " + targetType);
        System.out.println("│ IP: " + ipAddress);
        System.out.println("│ Security Level: " + securityLevel + "/10");
        System.out.println("│ Compromised: " + (isCompromised ? "YES" : "NO"));
        System.out.println("│ Failed Attempts: " + failedAttempts);
        System.out.println("└─────────────────────────────────────────┘");
    }
}