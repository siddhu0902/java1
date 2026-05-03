package Users;

import Logs.Logger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList; // Added missing import
import java.util.HashMap;
import java.util.List;      // Added missing import
import java.util.Map;
import java.util.UUID;

/**
 * AuthenticationService - Handles user login, logout, and session management.
 * Corrected to include missing List imports and proper session handling.
 */
public class AuthenticationService {

    private UserManager userManager;
    private Logger logger;
    private Map<String, SessionInfo> activeSessions;
    private int maxLoginAttempts;
    private int lockoutDurationMinutes;
    private boolean twoFactorEnabled;

    // Inner class for session information
    private static class SessionInfo {
        String sessionToken;
        User user;
        LocalDateTime loginTime;
        LocalDateTime lastActivity;
        String ipAddress;

        SessionInfo(User user, String ipAddress) {
            this.sessionToken = generateSessionToken();
            this.user = user;
            this.loginTime = LocalDateTime.now();
            this.lastActivity = LocalDateTime.now();
            this.ipAddress = ipAddress;
        }

        private static String generateSessionToken() {
            return UUID.randomUUID().toString().replace("-", "").substring(0, 32);
        }

        boolean isExpired(int timeoutMinutes) {
            return LocalDateTime.now().isAfter(lastActivity.plusMinutes(timeoutMinutes));
        }

        void updateActivity() {
            this.lastActivity = LocalDateTime.now();
        }
    }

    // Constructor
    public AuthenticationService(UserManager userManager) {
        this.userManager = userManager;
        this.logger = Logger.getInstance();
        this.activeSessions = new HashMap<>();
        this.maxLoginAttempts = 5;
        this.lockoutDurationMinutes = 15;
        this.twoFactorEnabled = false;
        logger.logEvent("AuthenticationService initialized");
        System.out.println("[AuthenticationService] Ready with " + maxLoginAttempts + " max attempts, " +
                lockoutDurationMinutes + " min lockout");
    }

    // Constructor with custom settings
    public AuthenticationService(UserManager userManager, int maxLoginAttempts, int lockoutDurationMinutes) {
        this(userManager);
        this.maxLoginAttempts = maxLoginAttempts;
        this.lockoutDurationMinutes = lockoutDurationMinutes;
    }

    public String login(String username, String password, String ipAddress) {
        System.out.println("\n┌─────────────────────────────────────────────────────────┐");
        System.out.println("│ LOGIN ATTEMPT                                            │");
        System.out.println("├─────────────────────────────────────────────────────────┤");
        System.out.println("│ Username: " + username);
        System.out.println("│ IP Address: " + ipAddress);
        System.out.println("│ Timestamp: " + getCurrentTimestamp());
        System.out.println("└─────────────────────────────────────────────────────────┘");

        User user = userManager.findUserByUsername(username);

        if (user == null) {
            logger.logEvent("Login failed - User not found: " + username);
            System.out.println("[AuthenticationService] ❌ Login failed: User not found - " + username);
            return null;
        }

        if (!user.isActive()) {
            logger.logEvent("Login failed - Account locked: " + username);
            System.out.println("[AuthenticationService] ❌ Login failed: Account is locked - " + username);
            return null;
        }

        if (user.getLoginAttempts() >= maxLoginAttempts) {
            logger.logEvent("Login failed - Max attempts exceeded: " + username);
            System.out.println("[AuthenticationService] ❌ Login failed: Maximum attempts exceeded - " + username);
            return null;
        }

        boolean authenticated = user.login(password);

        if (authenticated) {
            SessionInfo session = new SessionInfo(user, ipAddress);
            activeSessions.put(session.sessionToken, session);

            logger.logEvent("User logged in: " + username + " from " + ipAddress);
            System.out.println("[AuthenticationService] ✅ Login successful for: " + username);

            if (twoFactorEnabled) {
                System.out.println("[AuthenticationService] 📱 Two-factor authentication required.");
            }

            return session.sessionToken;
        } else {
            logger.logEvent("Login failed - Invalid password: " + username);
            System.out.println("[AuthenticationService] ❌ Login failed: Invalid password for - " + username);

            if (user.getLoginAttempts() >= maxLoginAttempts) {
                user.setActive(false);
                logger.logEvent("Account locked: " + username);
                System.out.println("[AuthenticationService] 🔒 Account locked due to multiple failures.");
            }

            return null;
        }
    }

    public boolean logout(String sessionToken) {
        SessionInfo session = activeSessions.remove(sessionToken);

        if (session != null) {
            session.user.logout();
            logger.logEvent("User logged out: " + session.user.getUsername());
            System.out.println("[AuthenticationService] ✅ Logout successful.");
            return true;
        } else {
            System.out.println("[AuthenticationService] ❌ Logout failed: Invalid session token");
            return false;
        }
    }

    public User validateSession(String sessionToken) {
        SessionInfo session = activeSessions.get(sessionToken);

        if (session == null) {
            return null;
        }

        if (session.isExpired(30)) {
            activeSessions.remove(sessionToken);
            logger.logEvent("Session expired for: " + session.user.getUsername());
            return null;
        }

        session.updateActivity();
        return session.user;
    }

    public int terminateUserSessions(String username) {
        int count = 0;
        List<String> toRemove = new ArrayList<>();

        for (Map.Entry<String, SessionInfo> entry : activeSessions.entrySet()) {
            if (entry.getValue().user.getUsername().equals(username)) {
                toRemove.add(entry.getKey());
                count++;
            }
        }

        for (String token : toRemove) {
            activeSessions.remove(token);
        }

        logger.logEvent("Terminated " + count + " sessions for user: " + username);
        return count;
    }

    public void displayActiveSessions() {
        System.out.println("\n┌─────────────────────────────────────────────────────────┐");
        System.out.println("│ ACTIVE SESSIONS                                         │");
        System.out.println("├─────────────────────────────────────────────────────────┤");

        if (activeSessions.isEmpty()) {
            System.out.println("│ No active sessions                                      │");
        } else {
            for (SessionInfo session : activeSessions.values()) {
                System.out.printf("│ User: %-12s │ IP: %-15s │ Login: %-19s │%n",
                        session.user.getUsername(),
                        session.ipAddress,
                        session.loginTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            }
        }
        System.out.println("└─────────────────────────────────────────────────────────┘");
    }

    public void setTwoFactorEnabled(boolean enabled) {
        this.twoFactorEnabled = enabled;
        logger.logEvent("2FA " + (enabled ? "enabled" : "disabled"));
    }

    public boolean updatePassword(String sessionToken, String oldPassword, String newPassword) {
        User user = validateSession(sessionToken);
        if (user == null) return false;

        boolean result = user.updatePassword(oldPassword, newPassword);
        if (result) {
            logger.logEvent("Password changed for: " + user.getUsername());
        }
        return result;
    }

    private String getCurrentTimestamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}