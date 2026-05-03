package Users;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * User - Represents base user in the system
 * Demonstrates ENCAPSULATION principle
 */
public class User {

    // Private fields - Encapsulation
    private String userId;
    private String username;
    private String passwordHash;
    private String email;
    private String fullName;
    private Role role;
    private boolean isActive;
    private boolean isLoggedIn;
    private LocalDateTime lastLogin;
    private LocalDateTime accountCreated;
    private LocalDateTime passwordLastChanged;
    private int loginAttempts;
    private List<String> activityLog;

    // Constructor
    public User(String username, String password, String email, String fullName) {
        this.userId = generateUserId();
        this.username = username;
        this.passwordHash = hashPassword(password);
        this.email = email;
        this.fullName = fullName;
        this.role = Role.USER;
        this.isActive = true;
        this.isLoggedIn = false;
        this.accountCreated = LocalDateTime.now();
        this.passwordLastChanged = LocalDateTime.now();
        this.loginAttempts = 0;
        this.activityLog = new ArrayList<>();

        logActivity("Account created");
        System.out.println("[User] Created: " + username + " (" + userId + ")");
    }

    // Constructor with role
    public User(String username, String password, String email, String fullName, Role role) {
        this(username, password, email, fullName);
        this.role = role;
    }

    // Generate unique user ID
    private String generateUserId() {
        return "USR-" + System.currentTimeMillis() + "-" + (int)(Math.random() * 1000);
    }

    // Simple password hashing (for demonstration)
    private String hashPassword(String password) {
        // In real implementation, use proper hashing (BCrypt, etc.)
        return "HASH_" + Integer.toHexString(password.hashCode());
    }

    // Verify password
    public boolean verifyPassword(String password) {
        String hash = hashPassword(password);
        return hash.equals(this.passwordHash);
    }

    // Login method
    public boolean login(String password) {
        if (!isActive) {
            System.out.println("[User] Account is deactivated: " + username);
            return false;
        }

        if (verifyPassword(password)) {
            this.isLoggedIn = true;
            this.lastLogin = LocalDateTime.now();
            this.loginAttempts = 0;
            logActivity("Logged in successfully");
            System.out.println("[User] Login successful: " + username);
            return true;
        } else {
            this.loginAttempts++;
            logActivity("Failed login attempt #" + loginAttempts);
            System.out.println("[User] Login failed for: " + username);

            // Lock account after 5 failed attempts
            if (loginAttempts >= 5) {
                this.isActive = false;
                logActivity("Account locked due to too many failed attempts");
                System.out.println("[User] Account locked: " + username);
            }
            return false;
        }
    }

    // Logout method
    public void logout() {
        if (isLoggedIn) {
            this.isLoggedIn = false;
            logActivity("Logged out");
            System.out.println("[User] Logged out: " + username);
        }
    }

    // Update password
    public boolean updatePassword(String oldPassword, String newPassword) {
        if (!verifyPassword(oldPassword)) {
            System.out.println("[User] Password update failed - incorrect old password");
            return false;
        }

        if (newPassword.length() < 8) {
            System.out.println("[User] Password too short - minimum 8 characters");
            return false;
        }

        this.passwordHash = hashPassword(newPassword);
        this.passwordLastChanged = LocalDateTime.now();
        logActivity("Password changed");
        System.out.println("[User] Password updated for: " + username);
        return true;
    }

    // Force password change (by admin)
    public void forcePasswordChange(String newPassword) {
        this.passwordHash = hashPassword(newPassword);
        this.passwordLastChanged = LocalDateTime.now();
        logActivity("Password force-changed by admin");
        System.out.println("[User] Password force-changed for: " + username);
    }

    // Log activity
    private void logActivity(String activity) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        activityLog.add("[" + timestamp + "] " + activity);
    }

    // Get activity log
    public List<String> getActivityLog() {
        return new ArrayList<>(activityLog);
    }

    // Display activity log
    public void displayActivityLog() {
        System.out.println("\n┌─────────────────────────────────────────────────────────┐");
        System.out.println("│ ACTIVITY LOG FOR: " + username);
        System.out.println("├─────────────────────────────────────────────────────────┤");
        if (activityLog.isEmpty()) {
            System.out.println("│ No activities recorded                                 │");
        } else {
            for (String log : activityLog) {
                System.out.println("│ " + log);
            }
        }
        System.out.println("└─────────────────────────────────────────────────────────┘");
    }

    // Getters
    public String getUserId() { return userId; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getFullName() { return fullName; }
    public Role getRole() { return role; }
    public boolean isActive() { return isActive; }
    public boolean isLoggedIn() { return isLoggedIn; }
    public LocalDateTime getLastLogin() { return lastLogin; }
    public LocalDateTime getAccountCreated() { return accountCreated; }
    public int getLoginAttempts() { return loginAttempts; }

    // Setters with validation
    public void setEmail(String email) {
        if (email != null && email.contains("@")) {
            this.email = email;
            logActivity("Email updated");
        }
    }

    public void setFullName(String fullName) {
        if (fullName != null && !fullName.isEmpty()) {
            this.fullName = fullName;
            logActivity("Name updated");
        }
    }

    public void setActive(boolean active) {
        this.isActive = active;
        logActivity(active ? "Account activated" : "Account deactivated");
    }

    public void setRole(Role role) {
        this.role = role;
        logActivity("Role changed to: " + role);
    }

    // Display user info
    public void displayInfo() {
        System.out.println("\n┌─────────────────────────────────────────────────────────┐");
        System.out.println("│ USER INFORMATION                                        │");
        System.out.println("├─────────────────────────────────────────────────────────┤");
        System.out.println("│ User ID:     " + userId);
        System.out.println("│ Username:    " + username);
        System.out.println("│ Full Name:   " + fullName);
        System.out.println("│ Email:       " + email);
        System.out.println("│ Role:        " + role);
        System.out.println("│ Status:      " + (isActive ? "ACTIVE" : "INACTIVE"));
        System.out.println("│ Logged In:   " + (isLoggedIn ? "YES" : "NO"));
        System.out.println("│ Created:     " + formatDateTime(accountCreated));
        if (lastLogin != null) {
            System.out.println("│ Last Login:  " + formatDateTime(lastLogin));
        }
        System.out.println("│ Failed Attempts: " + loginAttempts);
        System.out.println("└─────────────────────────────────────────────────────────┘");
    }

    private String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    @Override
    public String toString() {
        return String.format("User[%s] %s (%s) - %s", userId, username, fullName, role);
    }
}