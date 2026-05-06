package Users;

public class Admin extends User {
    private String adminLevel; // SUPER_ADMIN, MODERATOR, SUPPORT
    private String permissions[];
    private int adminId;

    // Constructor 1: Basic admin
    public Admin(String username, String password) {
        super(username, password);
        this.adminLevel = "MODERATOR";
        this.adminId = 0;
        this.permissions = new String[]{"VIEW_LOGS", "MANAGE_USERS"};
        this.setRole("ADMIN");
    }

    // Constructor 2: Admin with level
    public Admin(String username, String password, String adminLevel) {
        super(username, password);
        this.adminLevel = adminLevel;
        this.adminId = 0;
        if (adminLevel.equals("SUPER_ADMIN")) {
            this.permissions = new String[]{"VIEW_LOGS", "MANAGE_USERS", "DELETE_USERS", "CONFIGURE_SYSTEM"};
        } else if (adminLevel.equals("MODERATOR")) {
            this.permissions = new String[]{"VIEW_LOGS", "MANAGE_USERS"};
        } else {
            this.permissions = new String[]{"VIEW_LOGS"};
        }
        this.setRole("ADMIN");
    }

    // Constructor 3: Full admin
    public Admin(String username, String password, String email, String department,
                 int employeeId, String adminLevel, int adminId) {
        super(username, password, email, "ADMIN", department, employeeId);
        this.adminLevel = adminLevel;
        this.adminId = adminId;
        if (adminLevel.equals("SUPER_ADMIN")) {
            this.permissions = new String[]{"VIEW_LOGS", "MANAGE_USERS", "DELETE_USERS", "CONFIGURE_SYSTEM"};
        } else if (adminLevel.equals("MODERATOR")) {
            this.permissions = new String[]{"VIEW_LOGS", "MANAGE_USERS"};
        } else {
            this.permissions = new String[]{"VIEW_LOGS"};
        }
    }

    // Admin specific methods
    public void manageUsers() {
        System.out.println("[ADMIN] " + getUsername() + " is managing users...");
    }

    public void viewLogs() {
        System.out.println("[ADMIN] " + getUsername() + " is viewing system logs...");
    }

    public void configureSystem() {
        if (adminLevel.equals("SUPER_ADMIN")) {
            System.out.println("[ADMIN] " + getUsername() + " is configuring system settings...");
        } else {
            System.out.println("[ADMIN] Insufficient permissions to configure system!");
        }
    }

    public void deleteUser(String username) {
        if (adminLevel.equals("SUPER_ADMIN")) {
            System.out.println("[ADMIN] " + getUsername() + " deleted user: " + username);
        } else {
            System.out.println("[ADMIN] Insufficient permissions to delete users!");
        }
    }

    // Getters
    public String getAdminLevel() {
        return adminLevel;
    }

    public String[] getPermissions() {
        return permissions;
    }

    public int getAdminId() {
        return adminId;
    }

    // Setters
    public void setAdminLevel(String adminLevel) {
        this.adminLevel = adminLevel;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    // Display admin info
    public void displayAdminInfo() {
        System.out.println("Admin Information:");
        System.out.println("  Username: " + getUsername());
        System.out.println("  Role: " + getRole());
        System.out.println("  Admin Level: " + adminLevel);
        System.out.println("  Admin ID: " + adminId);
        System.out.println("  Department: " + getDepartment());
        System.out.println("  Permissions: ");
        for (String perm : permissions) {
            System.out.println("    - " + perm);
        }
    }

    @Override
    public String toString() {
        return "Admin{" +
                "username='" + getUsername() + '\'' +
                ", adminLevel='" + adminLevel + '\'' +
                ", adminId=" + adminId +
                ", role='" + getRole() + '\'' +
                '}';
    }
}