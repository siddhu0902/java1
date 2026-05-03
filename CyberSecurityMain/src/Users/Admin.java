package Users;

public class Admin extends User {

    public Admin(String username, String password, String email, String fullName) {
        // Calls the User class constructor and sets the role to ADMIN
        super(username, password, email, fullName, Role.ADMIN);
    }

    // Methods requested by UsersDemo
    public void viewLogs() {
        System.out.println("[Admin] Viewing system logs...");
    }

    public void configureSystem() {
        System.out.println("[Admin] Configuring system parameters...");
    }

    public void generateSecurityReport() {
        System.out.println("[Admin] Generating comprehensive security report...");
    }

    public void runDiagnostics() {
        System.out.println("[Admin] Running system diagnostics...");
    }

    public void manageUsers(UserManager um, String action, String targetUsername) {
        System.out.println("[Admin] Action '" + action + "' performed on user: " + targetUsername);
        if (action.equalsIgnoreCase("deactivate")) {
            um.deactivateUser(targetUsername);
        } else if (action.equalsIgnoreCase("activate")) {
            um.activateUser(targetUsername);
        }
    }

    public void promoteToAdmin(UserManager um, String targetUsername) {
        System.out.println("[Admin] Promoting " + targetUsername + " to ADMIN role...");
        um.assignRole(targetUsername, Role.ADMIN);
    }

    public void getAuditTrail() {
        System.out.println("[Admin] Fetching full audit trail...");
    }
}