package Defense;

import Attack.Attack;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Web Application Firewall (WAF) - Specialized firewall for web applications
 * Protects against SQL Injection, XSS, and other web attacks
 */
public class WebApplicationFirewall implements DefenseStrategy {

    private String defenseName;
    private int defenseLevel;
    private boolean active;
    private int threatsBlocked;
    private List<String> blockedPayloads;
    private List<String> sqlInjectionPatterns;
    private List<String> xssPatterns;
    private boolean learningMode;

    // Constructor
    public WebApplicationFirewall() {
        this.defenseName = "Web Application Firewall (WAF)";
        this.defenseLevel = 9;
        this.active = true;
        this.threatsBlocked = 0;
        this.blockedPayloads = new ArrayList<>();
        this.learningMode = false;
        initializePatterns();
        System.out.println("[WAF] Initialized with " + sqlInjectionPatterns.size() + " SQLi patterns and " +
                xssPatterns.size() + " XSS patterns");
    }

    private void initializePatterns() {
        // SQL Injection patterns
        sqlInjectionPatterns = new ArrayList<>();
        sqlInjectionPatterns.add("'.*--");
        sqlInjectionPatterns.add("'.*;.*--");
        sqlInjectionPatterns.add("1'.*OR.*'1'='1");
        sqlInjectionPatterns.add(".*UNION.*SELECT.*");
        sqlInjectionPatterns.add(".*DROP.*TABLE.*");
        sqlInjectionPatterns.add(".*INSERT.*INTO.*");
        sqlInjectionPatterns.add(".*DELETE.*FROM.*");
        sqlInjectionPatterns.add(".*xp_cmdshell.*");
        sqlInjectionPatterns.add(".*WAITFOR.*DELAY.*");

        // XSS patterns
        xssPatterns = new ArrayList<>();
        xssPatterns.add("<script.*>.*</script>");
        xssPatterns.add("javascript:.*");
        xssPatterns.add("onload=.*");
        xssPatterns.add("onerror=.*");
        xssPatterns.add("<img.*src=.*");
        xssPatterns.add("alert\\(.*\\)");
        xssPatterns.add("document\\.cookie");
        xssPatterns.add("eval\\(.*\\)");
    }

    @Override
    public void applyDefense() {
        System.out.println("\n┌─────────────────────────────────────────────────────────┐");
        System.out.println("│ WEB APPLICATION FIREWALL ACTIVATED                       │");
        System.out.println("└─────────────────────────────────────────────────────────┘");
        System.out.println("[WAF] " + defenseName + " is protecting web applications");
        System.out.println("[WAF] Learning mode: " + (learningMode ? "ON" : "OFF"));
        System.out.println("[WAF] SQL Injection patterns: " + sqlInjectionPatterns.size());
        System.out.println("[WAF] XSS patterns: " + xssPatterns.size());
        System.out.println("[WAF] Total threats blocked: " + threatsBlocked);
    }

    @Override
    public void applyDefense(Attack attack) {
        System.out.println("\n┌─────────────────────────────────────────────────────────┐");
        System.out.println("│ WAF RESPONDING TO ATTACK                                 │");
        System.out.println("└─────────────────────────────────────────────────────────┘");

        String attackType = attack.getAttackType().toLowerCase();

        if (attackType.contains("sql") || attackType.contains("injection")) {
            handleSQLInjection();
        } else if (attackType.contains("xss") || attackType.contains("cross")) {
            handleXSS();
        } else {
            System.out.println("[WAF] Analyzing web attack...");
            analyzeWebRequest("GET /index.php?id=1' OR '1'='1");
        }

        threatsBlocked++;
        System.out.println("[WAF] Attack blocked! Total: " + threatsBlocked);
    }

    // Analyze HTTP request
    public boolean analyzeWebRequest(String request) {
        System.out.println("\n[WAF] Analyzing request: " + request);

        // Check for SQL Injection
        for (String pattern : sqlInjectionPatterns) {
            if (Pattern.compile(pattern, Pattern.CASE_INSENSITIVE).matcher(request).find()) {
                System.out.println("[WAF] ⚠ SQL INJECTION DETECTED!");
                System.out.println("[WAF] Pattern matched: " + pattern);
                blockRequest(request);
                return false;
            }
        }

        // Check for XSS
        for (String pattern : xssPatterns) {
            if (Pattern.compile(pattern, Pattern.CASE_INSENSITIVE).matcher(request).find()) {
                System.out.println("[WAF] ⚠ XSS ATTACK DETECTED!");
                System.out.println("[WAF] Pattern matched: " + pattern);
                blockRequest(request);
                return false;
            }
        }

        // Check for path traversal
        if (request.contains("../") || request.contains("..\\")) {
            System.out.println("[WAF] ⚠ PATH TRAVERSAL DETECTED!");
            blockRequest(request);
            return false;
        }

        System.out.println("[WAF] ✓ Request appears safe");
        return true;
    }

    private void handleSQLInjection() {
        System.out.println("\n[WAF] SQL INJECTION ATTACK DETECTED!");
        System.out.println("[WAF] Attack pattern: Attempting to manipulate database queries");
        System.out.println("[WAF] Blocking malicious SQL payload");
        System.out.println("[WAF] Logging source IP for review");
    }

    private void handleXSS() {
        System.out.println("\n[WAF] CROSS-SITE SCRIPTING (XSS) DETECTED!");
        System.out.println("[WAF] Attack pattern: Attempting to inject malicious scripts");
        System.out.println("[WAF] Sanitizing output and blocking script execution");
    }

    private void blockRequest(String request) {
        blockedPayloads.add(request);
        threatsBlocked++;
        System.out.println("[WAF] ✗ Request BLOCKED");
        System.out.println("[WAF] Blocked payload: " + request.substring(0, Math.min(100, request.length())));
    }

    // Add custom SQL injection pattern
    public void addSQLInjectionPattern(String pattern) {
        sqlInjectionPatterns.add(pattern);
        System.out.println("[WAF] SQL Injection pattern added: " + pattern);
    }

    // Add custom XSS pattern
    public void addXSSPattern(String pattern) {
        xssPatterns.add(pattern);
        System.out.println("[WAF] XSS pattern added: " + pattern);
    }

    // Get blocked payloads
    public void getBlockedPayloads() {
        System.out.println("\n=== BLOCKED PAYLOADS ===");
        if (blockedPayloads.isEmpty()) {
            System.out.println("No payloads blocked yet");
        } else {
            for (int i = 0; i < blockedPayloads.size(); i++) {
                System.out.println((i + 1) + ". " + blockedPayloads.get(i));
            }
        }
    }

    // Enable/Disable learning mode
    public void setLearningMode(boolean enabled) {
        this.learningMode = enabled;
        System.out.println("[WAF] Learning mode " + (enabled ? "ENABLED" : "DISABLED"));
        if (enabled) {
            System.out.println("[WAF] WAF will learn normal traffic patterns");
        }
    }

    // Simulate OWASP Top 10 protection
    public void demonstrateOWASPProtection() {
        System.out.println("\n=== OWASP TOP 10 PROTECTIONS ===");
        System.out.println("1. A1:2021 - Broken Access Control ✓");
        System.out.println("2. A2:2021 - Cryptographic Failures ✓");
        System.out.println("3. A3:2021 - Injection (SQL, NoSQL, OS) ✓");
        System.out.println("4. A4:2021 - Insecure Design ✓");
        System.out.println("5. A5:2021 - Security Misconfiguration ✓");
        System.out.println("6. A6:2021 - Vulnerable Components ✓");
        System.out.println("7. A7:2021 - Identification Failures ✓");
        System.out.println("8. A8:2021 - Software Integrity Failures ✓");
        System.out.println("9. A9:2021 - Monitoring Failures ✓");
        System.out.println("10. A10:2021 - SSRF ✓");
    }

    @Override
    public boolean detectThreat() {
        System.out.println("\n[WAF] Scanning incoming requests for threats...");

        String[] testRequests = {
                "GET /search?q=iphone",
                "POST /login user=admin' OR '1'='1",
                "GET /profile?name=<script>alert('XSS')</script>",
                "GET /download?file=../../../etc/passwd"
        };

        boolean threatDetected = false;
        for (String request : testRequests) {
            if (!analyzeWebRequest(request)) {
                threatDetected = true;
            }
        }

        return threatDetected;
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
        return "A Web Application Firewall (WAF) protects web applications by filtering " +
                "and monitoring HTTP traffic between a web application and the Internet. " +
                "It protects against SQL injection, cross-site scripting (XSS), and other " +
                "web-based attacks.";
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public void setActive(boolean active) {
        this.active = active;
        System.out.println("[WAF] " + (active ? "ACTIVATED" : "DEACTIVATED"));
    }

    @Override
    public int getThreatsBlocked() {
        return threatsBlocked;
    }

    @Override
    public void resetStats() {
        threatsBlocked = 0;
        blockedPayloads.clear();
        System.out.println("[WAF] Statistics reset");
    }
}