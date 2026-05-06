package Users;

import Security.SecurityManager;
import java.util.HashMap;
import java.util.Map;

public class AuthenticationService {
    private SecurityManager securityManager;
    private Map<String, String> sessionTokens;
    private Map<String, Integer> loginAttempts;

    public AuthenticationService() {
        this.securityManager = SecurityManager.getInstance();
        this.sessionTokens = new HashMap<>();
        this.loginAttempts = new HashMap<>();
    }

    // Login method
    public User login(String username, String password) {
        // Track login attempts
        int attempts = loginAttempts.getOrDefault(username, 0);

        if (attempts >= 5) {
            System.out.println("[AUTH] Account " + username + " is locked due to too many failed attempts!");
            return null;
        }

        User user = securityManager.authenticateUser(username, password);

        if (user != null) {
            // Generate session token
            String token = generateSessionToken(username);
            sessionTokens.put(username, token);
            loginAttempts.remove(username);
            System.out.println("[AUTH] User " + username + " logged in successfully");
            return user;
        } else {
            loginAttempts.put(username, attempts + 1);
            System.out.println("[AUTH] Failed login attempt " + (attempts + 1) + " for " + username);
            return null;
        }
    }

    // Logout method
    public void logout(String username) {
        if (sessionTokens.containsKey(username)) {
            sessionTokens.remove(username);
            System.out.println("[AUTH] User " + username + " logged out");
        } else {
            System.out.println("[AUTH] User " + username + " is not logged in");
        }
    }

    // Check if user is authenticated
    public boolean isAuthenticated(String username) {
        return sessionTokens.containsKey(username);
    }

    // Validate session token
    public boolean validateSession(String username, String token) {
        return sessionTokens.containsKey(username) && sessionTokens.get(username).equals(token);
    }

    // Generate session token
    private String generateSessionToken(String username) {
        return username + "_" + System.currentTimeMillis() + "_" + Math.random();
    }

    // Change password
    public boolean changePassword(String username, String oldPassword, String newPassword) {
        User user = securityManager.authenticateUser(username, oldPassword);
        if (user != null) {
            user.setPassword(newPassword);
            System.out.println("[AUTH] Password changed for user: " + username);
            return true;
        }
        System.out.println("[AUTH] Password change failed for user: " + username);
        return false;
    }

    // Reset password (admin only)
    public boolean resetPassword(String username, String newPassword) {
        User user = null;
        for (User u : securityManager.getRegisteredUsers()) {
            if (u.getUsername().equals(username)) {
                user = u;
                break;
            }
        }

        if (user != null) {
            user.setPassword(newPassword);
            System.out.println("[AUTH] Password reset for user: " + username);
            return true;
        }
        System.out.println("[AUTH] User not found: " + username);
        return false;
    }

    // Get active sessions count
    public int getActiveSessionsCount() {
        return sessionTokens.size();
    }

    // Display active sessions
    public void displayActiveSessions() {
        System.out.println("\n=== Active Sessions ===");
        if (sessionTokens.isEmpty()) {
            System.out.println("No active sessions");
        } else {
            for (String username : sessionTokens.keySet()) {
                System.out.println("  - " + username);
            }
        }
    }

    // Get failed login attempts for a user
    public int getFailedLoginAttempts(String username) {
        return loginAttempts.getOrDefault(username, 0);
    }

    // Reset failed login attempts
    public void resetFailedLoginAttempts(String username) {
        loginAttempts.remove(username);
        System.out.println("[AUTH] Failed login attempts reset for: " + username);
    }
}