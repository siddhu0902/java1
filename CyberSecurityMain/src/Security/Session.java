package Security;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Session {
    private String sessionId;
    private String sessionName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String status; // ACTIVE, TERMINATED, COMPLETED

    // Constructor
    public Session(String sessionName) {
        this.sessionName = sessionName;
        this.status = "ACTIVE";
        this.startTime = LocalDateTime.now();
        this.sessionId = generateSessionId();
        System.out.println("Session Created: " + sessionId);
        displaySessionInfo();
    }

    // Generate unique session ID
    private String generateSessionId() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        return "SESS_" + startTime.format(formatter);
    }

    // Create Session (can be called separately)
    public void createSession(String sessionName) {
        this.sessionName = sessionName;
        this.status = "ACTIVE";
        this.startTime = LocalDateTime.now();
        this.sessionId = generateSessionId();
        System.out.println("Session Created: " + sessionId);
        displaySessionInfo();
    }

    // Terminate Session
    public void terminateSession() {
        if (status.equals("ACTIVE")) {
            this.status = "TERMINATED";
            this.endTime = LocalDateTime.now();
            System.out.println("Session Terminated: " + sessionId);
            displaySessionDuration();
        } else {
            System.out.println("Session is already terminated or not active!");
        }
    }

    // Get Session Status
    public String getSessionStatus() {
        return status;
    }

    // Get Session Name
    public String getSessionName() {
        return sessionName;
    }

    // Get Session ID
    public String getSessionId() {
        return sessionId;
    }

    // Get Start Time
    public LocalDateTime getStartTime() {
        return startTime;
    }

    // Get End Time
    public LocalDateTime getEndTime() {
        return endTime;
    }

    // Display session information
    private void displaySessionInfo() {
        System.out.println("┌─────────────────────────────────────────┐");
        System.out.println("│         SESSION INFORMATION             │");
        System.out.println("├─────────────────────────────────────────┤");
        System.out.println("│ Session ID: " + sessionId);
        System.out.println("│ Session Name: " + sessionName);
        System.out.println("│ Start Time: " + startTime);
        System.out.println("│ Status: " + status);
        System.out.println("└─────────────────────────────────────────┘");
    }

    // Display session duration
    private void displaySessionDuration() {
        System.out.println("Session Duration: " + calculateDuration() + " seconds");
    }

    // Calculate session duration in seconds
    private long calculateDuration() {
        if (endTime != null) {
            java.time.Duration duration = java.time.Duration.between(startTime, endTime);
            return duration.getSeconds();
        }
        return 0;
    }

    // Check if session is active
    public boolean isActive() {
        return status.equals("ACTIVE");
    }
}