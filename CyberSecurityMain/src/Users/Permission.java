package Users;

/**
 * Permission - Fine-grained permissions for users
 * Demonstrates granular access control
 */
public enum Permission {
    // User permissions
    USER_VIEW_PROFILE("view_profile"),
    USER_EDIT_PROFILE("edit_profile"),
    USER_CHANGE_PASSWORD("change_password"),

    // Log permissions
    LOG_VIEW_EVENTS("view_events"),
    LOG_VIEW_ATTACKS("view_attacks"),
    LOG_VIEW_DEFENSES("view_defenses"),
    LOG_EXPORT("export_logs"),

    // Security permissions
    SECURITY_VIEW_ALERTS("view_alerts"),
    SECURITY_ACKNOWLEDGE_ALERTS("acknowledge_alerts"),
    SECURITY_RUN_DIAGNOSTICS("run_diagnostics"),

    // Configuration permissions
    CONFIG_VIEW_SETTINGS("view_settings"),
    CONFIG_EDIT_SETTINGS("edit_settings"),
    CONFIG_FIREWALL_RULES("firewall_rules"),
    CONFIG_IDS_THRESHOLDS("ids_thresholds"),

    // Admin permissions
    ADMIN_MANAGE_USERS("manage_users"),
    ADMIN_CREATE_USER("create_user"),
    ADMIN_DELETE_USER("delete_user"),
    ADMIN_ASSIGN_ROLES("assign_roles"),
    ADMIN_VIEW_ALL_LOGS("view_all_logs"),

    // Report permissions
    REPORT_GENERATE_SUMMARY("generate_summary"),
    REPORT_GENERATE_DETAILED("generate_detailed"),
    REPORT_GENERATE_INCIDENT("generate_incident");

    private final String permissionName;

    Permission(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getPermissionName() {
        return permissionName;
    }

    @Override
    public String toString() {
        return permissionName;
    }
}