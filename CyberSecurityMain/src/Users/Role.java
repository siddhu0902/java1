package Users;

/**
 * Role - Enumeration of user roles in the system
 * Demonstrates ENUM usage
 */
public enum Role {
    USER("Standard User", 1, false, false, false),
    ANALYST("Security Analyst", 2, true, false, false),
    ADMIN("System Administrator", 3, true, true, true),
    SUPER_ADMIN("Super Administrator", 4, true, true, true);

    private final String displayName;
    private final int level;
    private final boolean canViewLogs;
    private final boolean canConfigureSystem;
    private final boolean canManageUsers;

    // Constructor
    Role(String displayName, int level, boolean canViewLogs, boolean canConfigureSystem, boolean canManageUsers) {
        this.displayName = displayName;
        this.level = level;
        this.canViewLogs = canViewLogs;
        this.canConfigureSystem = canConfigureSystem;
        this.canManageUsers = canManageUsers;
    }

    // Getters
    public String getDisplayName() { return displayName; }
    public int getLevel() { return level; }
    public boolean canViewLogs() { return canViewLogs; }
    public boolean canConfigureSystem() { return canConfigureSystem; }
    public boolean canManageUsers() { return canManageUsers; }

    // Check if role has privilege
    public boolean hasPrivilege(String privilege) {
        switch (privilege.toLowerCase()) {
            case "view_logs": return canViewLogs;
            case "configure": return canConfigureSystem;
            case "manage_users": return canManageUsers;
            default: return false;
        }
    }

    // Get role by level
    public static Role fromLevel(int level) {
        for (Role role : values()) {
            if (role.level == level) {
                return role;
            }
        }
        return USER;
    }

    // Get role by name
    public static Role fromString(String name) {
        try {
            return Role.valueOf(name.toUpperCase());
        } catch (IllegalArgumentException e) {
            return USER;
        }
    }

    @Override
    public String toString() {
        return displayName + " (Level " + level + ")";
    }
}