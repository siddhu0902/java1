package Users;

public class SecurityAnalyst extends User {
    private String certification;
    private String[] specializations;
    private int yearsOfExperience;

    // Constructor 1
    public SecurityAnalyst(String username, String password) {
        super(username, password);
        this.setRole("SECURITY_ANALYST");
        this.certification = "None";
        this.specializations = new String[]{"Incident Response"};
        this.yearsOfExperience = 0;
    }

    // Constructor 2
    public SecurityAnalyst(String username, String password, String certification) {
        super(username, password);
        this.setRole("SECURITY_ANALYST");
        this.certification = certification;
        this.specializations = new String[]{"Incident Response", "Threat Hunting"};
        this.yearsOfExperience = 1;
    }

    // Constructor 3
    public SecurityAnalyst(String username, String password, String email, String department,
                           int employeeId, String certification, int yearsOfExperience) {
        super(username, password, email, "SECURITY_ANALYST", department, employeeId);
        this.certification = certification;
        this.specializations = new String[]{"Incident Response", "Threat Hunting", "Vulnerability Assessment"};
        this.yearsOfExperience = yearsOfExperience;
    }

    // Analyst specific methods
    public void analyzeThreats() {
        System.out.println("[SECURITY ANALYST] " + getUsername() + " is analyzing security threats...");
    }

    public void generateReport() {
        System.out.println("[SECURITY ANALYST] " + getUsername() + " is generating security report...");
    }

    public void investigateIncident(String incidentId) {
        System.out.println("[SECURITY ANALYST] " + getUsername() + " is investigating incident: " + incidentId);
    }

    // Getters
    public String getCertification() {
        return certification;
    }

    public String[] getSpecializations() {
        return specializations;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    // Setters
    public void setCertification(String certification) {
        this.certification = certification;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    // Display analyst info
    public void displayAnalystInfo() {
        System.out.println("Security Analyst Information:");
        System.out.println("  Username: " + getUsername());
        System.out.println("  Role: " + getRole());
        System.out.println("  Certification: " + certification);
        System.out.println("  Years of Experience: " + yearsOfExperience);
        System.out.println("  Specializations: ");
        for (String spec : specializations) {
            System.out.println("    - " + spec);
        }
    }

    @Override
    public String toString() {
        return "SecurityAnalyst{" +
                "username='" + getUsername() + '\'' +
                ", certification='" + certification + '\'' +
                ", experience=" + yearsOfExperience +
                '}';
    }
}