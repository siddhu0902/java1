package Security;

public class Session {
    private String sessionName;
    private String sessionStatus;
    private long startTime;

    public Session(String sessionName) {
        this.sessionName = sessionName;
        this.sessionStatus = "ACTIVE";
        this.startTime = System.currentTimeMillis();
        System.out.println("[Session] Created: " + sessionName);
    }

    public void terminateSession() {
        this.sessionStatus = "TERMINATED";
        long duration = (System.currentTimeMillis() - startTime) / 1000;
        System.out.println("[Session] Terminated: " + sessionName + " (Duration: " + duration + " seconds)");
    }

    public String getSessionName() {
        return sessionName;
    }

    public String getSessionStatus() {
        return sessionStatus;
    }

    public long getStartTime() {
        return startTime;
    }
}