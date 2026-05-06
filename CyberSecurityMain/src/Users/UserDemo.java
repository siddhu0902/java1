package Users;

import Security.SecurityManager;

public class UserDemo {
    public static void main(String[] args) {
        SecurityManager sm = SecurityManager.getInstance();
        sm.startSimulation();

        System.out.println("\n--- Registering Users ---");
        sm.registerUser("alice", "alice123");
        sm.registerUser("bob", "bob123");
        sm.registerUser("charlie", "charlie123");

        sm.displaySimulationStatus();

        System.out.println("\n--- Authentication ---");
        Users.User authUser = sm.authenticateUser("alice", "alice123");
        if (authUser != null) {
            System.out.println("Successfully authenticated: " + authUser.getUsername());
        }

        sm.stopSimulation();
    }
}