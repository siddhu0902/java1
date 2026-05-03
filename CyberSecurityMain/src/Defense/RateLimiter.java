package Defense;

import Attack.Attack;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * RateLimiter - Prevents brute force attacks by limiting request frequency
 * Demonstrates rate limiting as a defense mechanism
 */
public class RateLimiter implements DefenseStrategy {

    private String defenseName;
    private int defenseLevel;
    private boolean active;
    private int threatsBlocked;
    private Map<String, UserRequestData> userRequests;
    private int maxRequestsPerMinute;
    private int lockoutDurationMinutes;
    private int suspiciousThreshold;

    // Inner class to track user request data
    private static class UserRequestData {
        int requestCount;
        long windowStartTime;
        boolean isLocked;
        long lockoutEndTime;
        int failedAttempts;

        UserRequestData() {
            this.requestCount = 0;
            this.windowStartTime = System.currentTimeMillis();
            this.isLocked = false;
            this.lockoutEndTime = 0;
            this.failedAttempts = 0;
        }
    }

    // Constructor
    public RateLimiter() {
        this.defenseName = "Rate Limiter";
        this.defenseLevel = 7;
        this.active = true;
        this.threatsBlocked = 0;
        this.userRequests = new HashMap<>();
        this.maxRequestsPerMinute = 60;    // 60 requests per minute
        this.lockoutDurationMinutes = 15;   // 15 minute lockout
        this.suspiciousThreshold = 10;      // 10 failed attempts triggers lockout
        System.out.println("[RateLimiter] Initialized: " + maxRequestsPerMinute + " req/min, " + lockoutDurationMinutes + " min lockout");
    }

    public RateLimiter(int maxRequestsPerMinute, int lockoutDurationMinutes) {
        this();
        this.maxRequestsPerMinute = maxRequestsPerMinute;
        this.lockoutDurationMinutes = lockoutDurationMinutes;
        System.out.println("[RateLimiter] Configured: " + maxRequestsPerMinute + " req/min, " + lockoutDurationMinutes + " min lockout");
    }

    @Override
    public void applyDefense() {
        System.out.println("\n┌─────────────────────────────────────────────────────────┐");
        System.out.println("│ RATE LIMITER ACTIVATED                                   │");
        System.out.println("└─────────────────────────────────────────────────────────┘");
        System.out.println("[RateLimiter] " + defenseName + " is enforcing limits");
        System.out.println("[RateLimiter] Max requests per minute: " + maxRequestsPerMinute);
        System.out.println("[RateLimiter] Lockout duration: " + lockoutDurationMinutes + " minutes");
        System.out.println("[RateLimiter] Suspicious threshold: " + suspiciousThreshold + " failures");
        System.out.println("[RateLimiter] Active users tracked: " + userRequests.size());
        System.out.println("[RateLimiter] Total requests blocked: " + threatsBlocked);
    }

    @Override
    public void applyDefense(Attack attack) {
        System.out.println("\n┌─────────────────────────────────────────────────────────┐");
        System.out.println("│ RATE LIMITER RESPONDING TO ATTACK                        │");
        System.out.println("└─────────────────────────────────────────────────────────┘");

        String attackType = attack.getAttackType().toLowerCase();

        if (attackType.contains("brute")) {
            System.out.println("[RateLimiter] Brute force attack detected!");
            System.out.println("[RateLimiter] Enforcing strict rate limits");
            // Simulate blocking excessive requests
            for (int i = 0; i < 100; i++) {
                String fakeUser = "attacker_" + i;
                if (!isRequestAllowed(fakeUser, "login")) {
                    threatsBlocked++;
                }
            }
            System.out.println("[RateLimiter] Blocked " + threatsBlocked + " excessive requests");
        } else {
            System.out.println("[RateLimiter] Standard rate limiting applied");
        }
    }

    // Check if a request is allowed
    public boolean isRequestAllowed(String userId, String action) {
        if (!active) {
            return true; // Allow all if rate limiter is inactive
        }

        UserRequestData userData = userRequests.getOrDefault(userId, new UserRequestData());
        long currentTime = System.currentTimeMillis();

        // Check if user is locked out
        if (userData.isLocked) {
            if (currentTime < userData.lockoutEndTime) {
                long remainingMinutes = TimeUnit.MILLISECONDS.toMinutes(userData.lockoutEndTime - currentTime);
                System.out.println("[RateLimiter] ❌ User " + userId + " is LOCKED for " + remainingMinutes + " more minutes");
                threatsBlocked++;
                return false;
            } else {
                // Lockout expired
                userData.isLocked = false;
                userData.requestCount = 0;
                userData.failedAttempts = 0;
                userData.windowStartTime = currentTime;
                System.out.println("[RateLimiter] ✓ Lockout expired for user: " + userId);
            }
        }

        // Reset window if time has passed
        long windowElapsed = currentTime - userData.windowStartTime;
        if (windowElapsed > 60000) { // 1 minute window
            userData.requestCount = 0;
            userData.windowStartTime = currentTime;
            System.out.println("[RateLimiter] Rate window reset for user: " + userId);
        }

        // Check rate limit
        if (userData.requestCount >= maxRequestsPerMinute) {
            System.out.println("[RateLimiter] ⚠ Rate limit EXCEEDED for user: " + userId);
            System.out.println("[RateLimiter] Requests in last minute: " + userData.requestCount);

            // Check if this is a failed attempt (for login actions)
            if (action.equals("login")) {
                userData.failedAttempts++;
                System.out.println("[RateLimiter] Failed attempts: " + userData.failedAttempts + "/" + suspiciousThreshold);

                if (userData.failedAttempts >= suspiciousThreshold) {
                    lockUser(userId);
                }
            }

            threatsBlocked++;
            return false;
        }

        // Request allowed
        userData.requestCount++;
        userRequests.put(userId, userData);

        if (userData.requestCount > maxRequestsPerMinute * 0.8) {
            System.out.println("[RateLimiter] ⚠ User " + userId + " nearing rate limit: " +
                    userData.requestCount + "/" + maxRequestsPerMinute);
        }

        return true;
    }

    // Record failed login attempt
    public void recordFailedAttempt(String userId) {
        UserRequestData userData = userRequests.getOrDefault(userId, new UserRequestData());
        userData.failedAttempts++;
        System.out.println("[RateLimiter] Failed attempt recorded for " + userId +
                " (Total: " + userData.failedAttempts + "/" + suspiciousThreshold + ")");

        if (userData.failedAttempts >= suspiciousThreshold && !userData.isLocked) {
            lockUser(userId);
        }

        userRequests.put(userId, userData);
    }

    // Lock a user account
    public void lockUser(String userId) {
        UserRequestData userData = userRequests.getOrDefault(userId, new UserRequestData());
        userData.isLocked = true;
        userData.lockoutEndTime = System.currentTimeMillis() + (lockoutDurationMinutes * 60 * 1000);
        userRequests.put(userId, userData);
        System.out.println("[RateLimiter] 🔒 User " + userId + " has been LOCKED for " + lockoutDurationMinutes + " minutes");
        System.out.println("[RateLimiter] Reason: " + suspiciousThreshold + " failed attempts");
    }

    // Unlock a user account
    public void unlockUser(String userId) {
        UserRequestData userData = userRequests.get(userId);
        if (userData != null) {
            userData.isLocked = false;
            userData.failedAttempts = 0;
            userData.requestCount = 0;
            System.out.println("[RateLimiter] 🔓 User " + userId + " has been UNLOCKED");
        } else {
            System.out.println("[RateLimiter] User not found: " + userId);
        }
    }

    // Get user status
    public void getUserStatus(String userId) {
        UserRequestData userData = userRequests.get(userId);
        if (userData == null) {
            System.out.println("[RateLimiter] No data for user: " + userId);
            return;
        }

        System.out.println("\n=== RATE LIMIT STATUS FOR: " + userId + " ===");
        System.out.println("Requests this minute: " + userData.requestCount + "/" + maxRequestsPerMinute);
        System.out.println("Failed attempts: " + userData.failedAttempts + "/" + suspiciousThreshold);
        System.out.println("Locked: " + (userData.isLocked ? "YES" : "NO"));
        if (userData.isLocked) {
            long remaining = TimeUnit.MILLISECONDS.toMinutes(userData.lockoutEndTime - System.currentTimeMillis());
            System.out.println("Lockout remaining: " + remaining + " minutes");
        }
    }

    // Get statistics
    public void getStatistics() {
        System.out.println("\n=== RATE LIMITER STATISTICS ===");
        System.out.println("Active users tracked: " + userRequests.size());
        System.out.println("Total requests blocked: " + threatsBlocked);
        System.out.println("Current rate limit: " + maxRequestsPerMinute + " req/min");
        System.out.println("Lockout duration: " + lockoutDurationMinutes + " min");
        System.out.println("Suspicious threshold: " + suspiciousThreshold + " failures");

        int lockedUsers = 0;
        for (UserRequestData data : userRequests.values()) {
            if (data.isLocked) lockedUsers++;
        }
        System.out.println("Currently locked users: " + lockedUsers);
    }

    // Update rate limit configuration
    public void updateConfig(int maxRequestsPerMinute, int lockoutDurationMinutes, int suspiciousThreshold) {
        this.maxRequestsPerMinute = maxRequestsPerMinute;
        this.lockoutDurationMinutes = lockoutDurationMinutes;
        this.suspiciousThreshold = suspiciousThreshold;
        System.out.println("[RateLimiter] Configuration updated:");
        System.out.println("  • Max requests/min: " + maxRequestsPerMinute);
        System.out.println("  • Lockout duration: " + lockoutDurationMinutes + " min");
        System.out.println("  • Suspicious threshold: " + suspiciousThreshold + " failures");
    }

    // Simulate brute force prevention
    public void demonstrateBruteForcePrevention() {
        System.out.println("\n=== DEMONSTRATING BRUTE FORCE PREVENTION ===");
        String testUser = "test_user";

        System.out.println("\n[*] Simulating login attempts for: " + testUser);

        for (int i = 1; i <= 15; i++) {
            boolean allowed = isRequestAllowed(testUser, "login");
            if (allowed) {
                // Simulate failed login
                recordFailedAttempt(testUser);
                System.out.println("Attempt " + i + ": Access DENIED (wrong password)");
            } else {
                System.out.println("Attempt " + i + ": REJECTED by rate limiter");
            }

            if (i == suspiciousThreshold) {
                System.out.println("\n[!] User should be locked after this attempt");
            }
        }

        getUserStatus(testUser);
    }

    @Override
    public boolean detectThreat() {
        System.out.println("\n[RateLimiter] Analyzing traffic patterns for threats...");

        boolean threatDetected = false;

        // Check for unusual request patterns
        int totalRequests = userRequests.values().stream().mapToInt(data -> data.requestCount).sum();
        int highVolumeUsers = 0;

        for (Map.Entry<String, UserRequestData> entry : userRequests.entrySet()) {
            if (entry.getValue().requestCount > maxRequestsPerMinute * 0.9) {
                highVolumeUsers++;
                System.out.println("[RateLimiter] ⚠ High-volume user detected: " + entry.getKey());
                threatDetected = true;
            }
        }

        System.out.println("[RateLimiter] Total requests: " + totalRequests);
        System.out.println("[RateLimiter] High-volume users: " + highVolumeUsers);

        if (threatDetected) {
            System.out.println("[RateLimiter] ⚠ Potential brute force attack detected!");
        } else {
            System.out.println("[RateLimiter] ✓ Traffic patterns normal");
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
        return "Rate limiting prevents brute force attacks by restricting the number of " +
                "requests a user can make within a specific time window. It also implements " +
                "account lockout after multiple failed attempts.";
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public void setActive(boolean active) {
        this.active = active;
        System.out.println("[RateLimiter] Defense " + (active ? "ACTIVATED" : "DEACTIVATED"));
    }

    @Override
    public int getThreatsBlocked() {
        return threatsBlocked;
    }

    @Override
    public void resetStats() {
        threatsBlocked = 0;
        userRequests.clear();
        System.out.println("[RateLimiter] Statistics reset");
    }
}