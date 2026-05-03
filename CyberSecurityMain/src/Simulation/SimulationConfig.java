package Simulation;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * SimulationConfig - Configuration settings for the simulator
 * Manages simulator parameters and preferences
 */
public class SimulationConfig {

    // Simulation parameters
    private boolean autoSaveLogs;
    private boolean verboseLogging;
    private boolean realTimeAlerts;
    private int maxConcurrentAttacks;
    private int defaultTimeoutSeconds;
    private String logLevel; // DEBUG, INFO, WARNING, ERROR
    private String reportFormat; // TEXT, HTML, CSV

    // Performance parameters
    private int simulationSpeed; // 1-10 (1=slow, 10=fast)
    private boolean enableAnimations;
    private int networkLatencyMs;

    // Security parameters
    private boolean autoBlockSuspiciousIPs;
    private int suspiciousThreshold;
    private boolean enableAuditTrail;

    // Constructor with defaults
    public SimulationConfig() {
        this.autoSaveLogs = true;
        this.verboseLogging = true;
        this.realTimeAlerts = true;
        this.maxConcurrentAttacks = 5;
        this.defaultTimeoutSeconds = 60;
        this.logLevel = "INFO";
        this.reportFormat = "TEXT";
        this.simulationSpeed = 5;
        this.enableAnimations = true;
        this.networkLatencyMs = 10;
        this.autoBlockSuspiciousIPs = true;
        this.suspiciousThreshold = 5;
        this.enableAuditTrail = true;
    }

    // Load configuration from file
    public void loadFromFile(String filename) {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(filename)) {
            props.load(fis);

            autoSaveLogs = Boolean.parseBoolean(props.getProperty("autoSaveLogs", "true"));
            verboseLogging = Boolean.parseBoolean(props.getProperty("verboseLogging", "true"));
            realTimeAlerts = Boolean.parseBoolean(props.getProperty("realTimeAlerts", "true"));
            maxConcurrentAttacks = Integer.parseInt(props.getProperty("maxConcurrentAttacks", "5"));
            defaultTimeoutSeconds = Integer.parseInt(props.getProperty("defaultTimeoutSeconds", "60"));
            logLevel = props.getProperty("logLevel", "INFO");
            reportFormat = props.getProperty("reportFormat", "TEXT");
            simulationSpeed = Integer.parseInt(props.getProperty("simulationSpeed", "5"));
            enableAnimations = Boolean.parseBoolean(props.getProperty("enableAnimations", "true"));
            networkLatencyMs = Integer.parseInt(props.getProperty("networkLatencyMs", "10"));
            autoBlockSuspiciousIPs = Boolean.parseBoolean(props.getProperty("autoBlockSuspiciousIPs", "true"));
            suspiciousThreshold = Integer.parseInt(props.getProperty("suspiciousThreshold", "5"));
            enableAuditTrail = Boolean.parseBoolean(props.getProperty("enableAuditTrail", "true"));

            System.out.println("[Config] Loaded configuration from: " + filename);
        } catch (IOException e) {
            System.out.println("[Config] Could not load config file, using defaults");
        }
    }

    // Save configuration to file
    public void saveToFile(String filename) {
        Properties props = new Properties();

        props.setProperty("autoSaveLogs", String.valueOf(autoSaveLogs));
        props.setProperty("verboseLogging", String.valueOf(verboseLogging));
        props.setProperty("realTimeAlerts", String.valueOf(realTimeAlerts));
        props.setProperty("maxConcurrentAttacks", String.valueOf(maxConcurrentAttacks));
        props.setProperty("defaultTimeoutSeconds", String.valueOf(defaultTimeoutSeconds));
        props.setProperty("logLevel", logLevel);
        props.setProperty("reportFormat", reportFormat);
        props.setProperty("simulationSpeed", String.valueOf(simulationSpeed));
        props.setProperty("enableAnimations", String.valueOf(enableAnimations));
        props.setProperty("networkLatencyMs", String.valueOf(networkLatencyMs));
        props.setProperty("autoBlockSuspiciousIPs", String.valueOf(autoBlockSuspiciousIPs));
        props.setProperty("suspiciousThreshold", String.valueOf(suspiciousThreshold));
        props.setProperty("enableAuditTrail", String.valueOf(enableAuditTrail));

        try (FileOutputStream fos = new FileOutputStream(filename)) {
            props.store(fos, "Cybersecurity Simulator Configuration");
            System.out.println("[Config] Saved configuration to: " + filename);
        } catch (IOException e) {
            System.err.println("[Config] Failed to save configuration: " + e.getMessage());
        }
    }

    // Display current configuration
    public void displayConfig() {
        System.out.println("\n┌─────────────────────────────────────────────────────────┐");
        System.out.println("│ SIMULATION CONFIGURATION                                │");
        System.out.println("├─────────────────────────────────────────────────────────┤");
        System.out.println("│ LOGGING & ALERTS                                        │");
        System.out.printf("│   Auto-save logs:    %-36s │%n", autoSaveLogs ? "ENABLED" : "DISABLED");
        System.out.printf("│   Verbose logging:   %-36s │%n", verboseLogging ? "ENABLED" : "DISABLED");
        System.out.printf("│   Real-time alerts:  %-36s │%n", realTimeAlerts ? "ENABLED" : "DISABLED");
        System.out.printf("│   Log level:         %-36s │%n", logLevel);
        System.out.println("│ PERFORMANCE                                             │");
        System.out.printf("│   Simulation speed:  %-36d │%n", simulationSpeed);
        System.out.printf("│   Animations:        %-36s │%n", enableAnimations ? "ENABLED" : "DISABLED");
        System.out.printf("│   Network latency:   %-36d ms │%n", networkLatencyMs);
        System.out.println("│ SECURITY                                                │");
        System.out.printf("│   Auto-block IPs:    %-36s │%n", autoBlockSuspiciousIPs ? "ENABLED" : "DISABLED");
        System.out.printf("│   Suspicious threshold: %-30d │%n", suspiciousThreshold);
        System.out.printf("│   Audit trail:       %-36s │%n", enableAuditTrail ? "ENABLED" : "DISABLED");
        System.out.println("│ LIMITS                                                  │");
        System.out.printf("│   Max concurrent attacks: %-30d │%n", maxConcurrentAttacks);
        System.out.printf("│   Default timeout:      %-30d sec │%n", defaultTimeoutSeconds);
        System.out.println("└─────────────────────────────────────────────────────────┘");
    }

    // Getters and Setters
    public boolean isAutoSaveLogs() { return autoSaveLogs; }
    public void setAutoSaveLogs(boolean autoSaveLogs) { this.autoSaveLogs = autoSaveLogs; }

    public boolean isVerboseLogging() { return verboseLogging; }
    public void setVerboseLogging(boolean verboseLogging) { this.verboseLogging = verboseLogging; }

    public boolean isRealTimeAlerts() { return realTimeAlerts; }
    public void setRealTimeAlerts(boolean realTimeAlerts) { this.realTimeAlerts = realTimeAlerts; }

    public int getMaxConcurrentAttacks() { return maxConcurrentAttacks; }
    public void setMaxConcurrentAttacks(int maxConcurrentAttacks) { this.maxConcurrentAttacks = maxConcurrentAttacks; }

    public int getDefaultTimeoutSeconds() { return defaultTimeoutSeconds; }
    public void setDefaultTimeoutSeconds(int defaultTimeoutSeconds) { this.defaultTimeoutSeconds = defaultTimeoutSeconds; }

    public String getLogLevel() { return logLevel; }
    public void setLogLevel(String logLevel) { this.logLevel = logLevel; }

    public String getReportFormat() { return reportFormat; }
    public void setReportFormat(String reportFormat) { this.reportFormat = reportFormat; }

    public int getSimulationSpeed() { return simulationSpeed; }
    public void setSimulationSpeed(int simulationSpeed) {
        this.simulationSpeed = Math.max(1, Math.min(10, simulationSpeed));
    }

    public boolean isEnableAnimations() { return enableAnimations; }
    public void setEnableAnimations(boolean enableAnimations) { this.enableAnimations = enableAnimations; }

    public int getNetworkLatencyMs() { return networkLatencyMs; }
    public void setNetworkLatencyMs(int networkLatencyMs) { this.networkLatencyMs = networkLatencyMs; }

    public boolean isAutoBlockSuspiciousIPs() { return autoBlockSuspiciousIPs; }
    public void setAutoBlockSuspiciousIPs(boolean autoBlockSuspiciousIPs) { this.autoBlockSuspiciousIPs = autoBlockSuspiciousIPs; }

    public int getSuspiciousThreshold() { return suspiciousThreshold; }
    public void setSuspiciousThreshold(int suspiciousThreshold) { this.suspiciousThreshold = suspiciousThreshold; }

    public boolean isEnableAuditTrail() { return enableAuditTrail; }
    public void setEnableAuditTrail(boolean enableAuditTrail) { this.enableAuditTrail = enableAuditTrail; }
}