package Users;

/**
 * UsersDemo - Demonstration class for Users package
 * Shows User Management, Inheritance, Roles, and Permissions
 */
public class UsersDemo {

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║                  USERS PACKAGE DEMONSTRATION             ║");
        System.out.println("║                                                          ║");
        System.out.println("║  OOP Concepts: Encapsulation, Inheritance, Polymorphism  ║");
        System.out.println("║                Roles, Permissions, User Management       ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝\n");

        // Test 1: Create UserManager
        System.out.println("=".repeat(60));
        System.out.println("TEST CASE 1: Creating UserManager");
        System.out.println("=".repeat(60));

        UserManager userManager = new UserManager();

        // Test 2: Creating Users
        System.out.println("\n" + "=".repeat(60));
        System.out.println("TEST CASE 2: Creating Users");
        System.out.println("=".repeat(60));

        userManager.createUser("alice", "password123", "alice@example.com", "Alice Johnson");
        userManager.createUser("bob", "secret456", "bob@example.com", "Bob Smith");
        userManager.createAdmin("admin1", "admin123", "admin@system.com", "System Admin");
        userManager.createAnalyst("analyst1", "analyst123", "analyst@security.com", "Security Analyst");

        // Test 3: Display All Users
        System.out.println("\n" + "=".repeat(60));
        System.out.println("TEST CASE 3: Displaying All Users");
        System.out.println("=".repeat(60));

        userManager.displayAllUsers();

        // Test 4: User Authentication
        System.out.println("\n" + "=".repeat(60));
        System.out.println("TEST CASE 4: User Authentication");
        System.out.println("=".repeat(60));

        User authenticatedUser = userManager.authenticate("alice", "password123");
        if (authenticatedUser != null) {
            System.out.println("✓ Authentication successful for: alice");
            authenticatedUser.displayInfo(); // Requires displayInfo() in User class
        }

        // Test failed authentication
        User failedAuth = userManager.authenticate("alice", "wrongpassword");
        if (failedAuth == null) {
            System.out.println("✓ Failed authentication correctly rejected");
        }

        // Test 5: Admin Privileges
        System.out.println("\n" + "=".repeat(60));
        System.out.println("TEST CASE 5: Admin Privileges (Inheritance)");
        System.out.println("=".repeat(60));

        Admin admin = (Admin) userManager.findUserByUsername("admin1");
        if (admin != null) {
            admin.displayInfo();
            admin.viewLogs();
            admin.configureSystem();
            admin.generateSecurityReport();
            admin.runDiagnostics();
        }

        // Test 6: Security Analyst Privileges
        System.out.println("\n" + "=".repeat(60));
        System.out.println("TEST CASE 6: Security Analyst Privileges");
        System.out.println("=".repeat(60));

        SecurityAnalyst analyst = (SecurityAnalyst) userManager.findUserByUsername("analyst1");
        if (analyst != null) {
            analyst.displayInfo();
            analyst.analyzeLogs();
            analyst.checkAlerts();
            analyst.getThreatIntelligence();
            analyst.runVulnerabilityAssessment();
        }

        // Test 7: Password Change
        System.out.println("\n" + "=".repeat(60));
        System.out.println("TEST CASE 7: Password Change");
        System.out.println("=".repeat(60));

        boolean passwordChanged = userManager.changePassword("alice", "password123", "newPassword789");
        System.out.println("Password change result: " + (passwordChanged ? "SUCCESS" : "FAILED"));

        // Test 8: User Management by Admin
        System.out.println("\n" + "=".repeat(60));
        System.out.println("TEST CASE 8: User Management (Admin Actions)");
        System.out.println("=".repeat(60));

        if (admin != null) {
            admin.manageUsers(userManager, "deactivate", "alice");
            admin.manageUsers(userManager, "activate", "alice");
            admin.promoteToAdmin(userManager, "bob");
        }

        // Test 9: Role and Permission System
        System.out.println("\n" + "=".repeat(60));
        System.out.println("TEST CASE 9: Role-Based Access Control");
        System.out.println("=".repeat(60));

        System.out.println("\nAvailable Roles:");
        for (Role role : Role.values()) {
            System.out.printf("  • %-15s - Level %d - Logs: %s, Config: %s, Users: %s%n",
                    role.getDisplayName(),
                    role.getLevel(),
                    role.canViewLogs() ? "✓" : "✗",
                    role.canConfigureSystem() ? "✓" : "✗",
                    role.canManageUsers() ? "✓" : "✗");
        }

        // Uncomment if you have a Permission enum created
        /*
        System.out.println("\nAvailable Permissions:");
        for (Permission perm : Permission.values()) {
            System.out.printf("  • %s%n", perm.getPermissionName());
        }
        */

        // Test 10: User Statistics
        System.out.println("\n" + "=".repeat(60));
        System.out.println("TEST CASE 10: User Statistics");
        System.out.println("=".repeat(60));

        userManager.printStatistics();

        // Test 11: Activity Logs
        System.out.println("\n" + "=".repeat(60));
        System.out.println("TEST CASE 11: User Activity Logs");
        System.out.println("=".repeat(60));

        User alice = userManager.findUserByUsername("alice");
        if (alice != null) {
            alice.displayActivityLog();
        }

        if (admin != null) {
            admin.getAuditTrail();
        }

        // Test 12: Logout
        System.out.println("\n" + "=".repeat(60));
        System.out.println("TEST CASE 12: Logout");
        System.out.println("=".repeat(60));

        userManager.logout();
        System.out.println("Current logged in user: " + userManager.getCurrentLoggedInUser());

        // Summary
        System.out.println("\n╔══════════════════════════════════════════════════════════╗");
        System.out.println("║                    DEMO COMPLETED!                       ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
    }
}