package Users;

public class User {
    private String username;
    private String password;
    private String email;
    private String role;
    private String department;
    private int employeeId;
    private boolean isAuthenticated;

    // Constructor 1: Just username and password
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.email = "";
        this.role = "USER";
        this.department = "";
        this.employeeId = 0;
        this.isAuthenticated = false;
    }

    // Constructor 2: Username, password, and role
    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.email = "";
        this.department = "";
        this.employeeId = 0;
        this.isAuthenticated = false;
    }

    // Constructor 3: All fields
    public User(String username, String password, String email, String role, String department, int employeeId) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.department = department;
        this.employeeId = employeeId;
        this.isAuthenticated = false;
    }

    // Authenticate method
    public boolean authenticate(String password) {
        this.isAuthenticated = this.password.equals(password);
        return this.isAuthenticated;
    }

    // Getters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public String getDepartment() {
        return department;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    // Setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", role='" + role + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}