package Users;

import Logs.Logger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * UserManager - Manages all users in the system
 * Demonstrates CRUD operations on users
 */
public class UserManager {

    private Map<String, User> usersById;
    private Map<String, User> usersByUsername;
    private Logger logger;
    private User currentLoggedInUser;

    // Constructor
    public UserManager() {
        this.usersById = new HashMap<>();
        this.usersByUsername = new HashMap<>();
        this.logger = Logger.getInstance();
        this.currentLoggedInUser = null;
        System.out.println("[UserManager] Initialized");
    }

    // Create standard user
    public boolean createUser(String username, String password, String email, String fullName) {
        if (usersByUsername.containsKey(username)) {
            System.out.println("[UserManager] User already exists: " + username);
            return false;
        }

        User newUser = new User(username, password, email, fullName);
        usersById.put(String.valueOf(newUser.getUserId()), newUser);
        usersByUsername.put(username, newUser);

        logger.logEvent("User created: " + username);
        System.out.println("[UserManager] User created successfully: " + username);
        return true;
    }

    // Create user with a specific role
    public boolean createUser(String username, String password, String email, String fullName, Role role) {
        if (usersByUsername.containsKey(username)) {
            System.out.println("[UserManager] User already exists: " + username);
            return false;
        }

        User newUser;
        if (role == Role.ADMIN) {
            newUser = new Admin(username, password, email, fullName);
        } else if (role == Role.ANALYST) {
            newUser = new SecurityAnalyst(username, password, email, fullName);
        } else {
            newUser = new User(username, password, email, fullName, role);
        }

        usersById.put(String.valueOf(newUser.getUserId()), newUser);
        usersByUsername.put(username, newUser);

        logger.logEvent("User created with role: " + username + " - " + role);
        System.out.println("[UserManager] User created: " + username + " (" + role + ")");
        return true;
    }

    // Create admin user (convenience method)
    public boolean createAdmin(String username, String password, String email, String fullName) {
        return createUser(username, password, email, fullName, Role.ADMIN);
    }

    // Create analyst user (convenience method)
    public boolean createAnalyst(String username, String password, String email, String fullName) {
        return createUser(username, password, email, fullName, Role.ANALYST);
    }

    // Find user by username
    public User findUserByUsername(String username) {
        return usersByUsername.get(username);
    }

    // Find user by ID
    public User findUserById(String userId) {
        return usersById.get(userId);
    }

    // Authenticate user
    public User authenticate(String username, String password) {
        User user = usersByUsername.get(username);
        if (user != null && user.login(password)) {
            currentLoggedInUser = user;
            logger.logEvent("User authenticated: " + username);
            return user;
        }
        logger.logEvent("Authentication failed for: " + username);
        return null;
    }

    // Logout current user
    public void logout() {
        if (currentLoggedInUser != null) {
            currentLoggedInUser.logout();
            logger.logEvent("User logged out: " + currentLoggedInUser.getUsername());
            currentLoggedInUser = null;
        }
    }

    // Delete user
    public boolean deleteUser(String username) {
        User user = usersByUsername.get(username);
        if (user == null) {
            System.out.println("[UserManager] User not found: " + username);
            return false;
        }

        usersById.remove(String.valueOf(user.getUserId()));
        usersByUsername.remove(username);

        logger.logEvent("User deleted: " + username);
        System.out.println("[UserManager] User deleted: " + username);
        return true;
    }

    // Update user details
    public boolean updateUser(String username, String email, String fullName) {
        User user = usersByUsername.get(username);
        if (user == null) {
            System.out.println("[UserManager] User not found: " + username);
            return false;
        }

        user.setEmail(email);
        user.setFullName(fullName);

        logger.logEvent("User updated: " + username);
        System.out.println("[UserManager] User updated: " + username);
        return true;
    }

    // Change user password
    public boolean changePassword(String username, String oldPassword, String newPassword) {
        User user = usersByUsername.get(username);
        if (user == null) {
            System.out.println("[UserManager] User not found: " + username);
            return false;
        }

        boolean result = user.updatePassword(oldPassword, newPassword);
        if (result) {
            logger.logEvent("Password changed for: " + username);
        }
        return result;
    }

    // Activate a user account
    public void activateUser(String username) {
        User user = usersByUsername.get(username);
        if (user != null) {
            user.setActive(true);
            logger.logEvent("User activated: " + username);
            System.out.println("[UserManager] User activated: " + username);
        }
    }

    // Deactivate a user account
    public void deactivateUser(String username) {
        User user = usersByUsername.get(username);
        if (user != null) {
            user.setActive(false);
            logger.logEvent("User deactivated: " + username);
            System.out.println("[UserManager] User deactivated: " + username);
        }
    }

    // Assign a new role to a user
    public void assignRole(String username, Role role) {
        User user = usersByUsername.get(username);
        if (user != null) {
            user.setRole(role);
            logger.logEvent("Role assigned to " + username + ": " + role);
            System.out.println("[UserManager] Role assigned: " + username + " -> " + role);
        }
    }

    // Get all users
    public List<User> getAllUsers() {
        return new ArrayList<>(usersByUsername.values());
    }

    // Get users by a specific role
    public List<User> getUsersByRole(Role role) {
        List<User> result = new ArrayList<>();
        for (User user : usersByUsername.values()) {
            if (user.getRole() == role) {
                result.add(user);
            }
        }
        return result;
    }

    // Get all currently active users
    public List<User> getActiveUsers() {
        List<User> result = new ArrayList<>();
        for (User user : usersByUsername.values()) {
            if (user.isActive()) {
                result.add(user);
            }
        }
        return result;
    }

    // Get all currently logged-in users
    public List<User> getLoggedInUsers() {
        List<User> result = new ArrayList<>();
        for (User user : usersByUsername.values()) {
            if (user.isLoggedIn()) {
                result.add(user);
            }
        }
        return result;
    }

    // Get the instance of the currently logged-in user
    public User getCurrentLoggedInUser() {
        return currentLoggedInUser;
    }

    // Display all users in a formatted table
    public void displayAllUsers() {
        System.out.println("\n┌─────────────────────────────────────────────────────────┐");
        System.out.println("│ ALL USERS IN SYSTEM                                     │");
        System.out.println("├─────────────────────────────────────────────────────────┤");

        if (usersByUsername.isEmpty()) {
            System.out.println("│ No users registered                                     │");
        } else {
            for (User user : usersByUsername.values()) {
                System.out.printf("│ %-15s │ %-15s │ %-10s │ %-8s │%n",
                        user.getUsername(),
                        truncate(user.getFullName(), 15),
                        user.getRole(),
                        user.isActive() ? "ACTIVE" : "INACTIVE");
            }
        }
        System.out.println("└─────────────────────────────────────────────────────────┘");

        System.out.println("\nTotal users: " + usersByUsername.size());
        System.out.println("Active users: " + getActiveUsers().size());
        System.out.println("Logged in: " + getLoggedInUsers().size());
    }

    // Print dashboard statistics
    public void printStatistics() {
        System.out.println("\n┌─────────────────────────────────────────────────────────┐");
        System.out.println("│ USER MANAGEMENT STATISTICS                              │");
        System.out.println("├─────────────────────────────────────────────────────────┤");
        System.out.printf("│ Total Users:        %-36d │%n", usersByUsername.size());
        System.out.printf("│ Active Users:       %-36d │%n", getActiveUsers().size());
        System.out.printf("│ Logged In Users:    %-36d │%n", getLoggedInUsers().size());
        System.out.printf("│ Admins:             %-36d │%n", getUsersByRole(Role.ADMIN).size());
        System.out.printf("│ Analysts:           %-36d │%n", getUsersByRole(Role.ANALYST).size());
        System.out.printf("│ Regular Users:      %-36d │%n", getUsersByRole(Role.USER).size());
        System.out.println("└─────────────────────────────────────────────────────────┘");
    }

    // Helper method to truncate long strings for the display table
    private String truncate(String str, int length) {
        if (str == null) return "N/A"; // Null safety added here
        if (str.length() <= length) return str;
        return str.substring(0, length - 3) + "...";
    }
}