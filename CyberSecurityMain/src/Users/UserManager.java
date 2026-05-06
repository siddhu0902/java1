package Users;

import Security.SecurityManager;
import java.util.List;

public class UserManager {
    private SecurityManager securityManager;

    public UserManager() {
        this.securityManager = SecurityManager.getInstance();
    }

    // Add a new user
    public boolean addUser(String username, String password) {
        return securityManager.registerUser(username, password);
    }

    public boolean addUser(String username, String password, String role) {
        return securityManager.registerUser(username, password, role);
    }

    // Remove a user
    public boolean removeUser(String username) {
        List<User> users = securityManager.getRegisteredUsers();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(username)) {
                users.remove(i);
                System.out.println("User " + username + " removed successfully!");
                return true;
            }
        }
        System.out.println("User " + username + " not found!");
        return false;
    }

    // Get user by username
    public User getUser(String username) {
        List<User> users = securityManager.getRegisteredUsers();
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    // List all users
    public void listAllUsers() {
        List<User> users = securityManager.getRegisteredUsers();
        System.out.println("\n=== All Registered Users ===");
        if (users.isEmpty()) {
            System.out.println("No users registered.");
        } else {
            for (User user : users) {
                System.out.println("  - " + user.getUsername() + " (Role: " + user.getRole() + ")");
            }
        }
    }

    // Get total user count
    public int getUserCount() {
        return securityManager.getRegisteredUsersCount();
    }

    // Authenticate user
    public User authenticateUser(String username, String password) {
        return securityManager.authenticateUser(username, password);
    }

    // Update user password
    public boolean updatePassword(String username, String newPassword) {
        User user = getUser(username);
        if (user != null) {
            user.setPassword(newPassword);
            System.out.println("Password updated for user: " + username);
            return true;
        }
        System.out.println("User not found: " + username);
        return false;
    }
}