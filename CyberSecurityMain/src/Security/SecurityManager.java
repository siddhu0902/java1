package Security;

import Attack.Attack;
import Attack.AttackFactory;
import Defense.DefenseStrategy;
import Defense.DefenseFactory;
import Logs.Logger;
import Users.User;
import java.util.ArrayList;
import java.util.List;

public class SecurityManager {
    // Singleton instance
    private static SecurityManager instance = null;
    private boolean simulationRunning = false;
    private Session currentSession;
    private List<User> registeredUsers;
    private AttackFactory attackFactory;
    private DefenseFactory defenseFactory;
    private Logger logger;

    // Private constructor for Singleton pattern
    private SecurityManager() {
        registeredUsers = new ArrayList<>();
        attackFactory = new AttackFactory();
        defenseFactory = new DefenseFactory();
        logger = Logger.getInstance();
        System.out.println("Security Manager Initialized");
    }

    // Singleton getInstance method
    public static SecurityManager getInstance() {
        if (instance == null) {
            instance = new SecurityManager();
        }
        return instance;
    }

    // Start Simulation
    public void startSimulation() {
        if (!simulationRunning) {
            simulationRunning = true;
            logger.logEvent("Simulation Started");
            System.out.println("=== Cybersecurity Attack Simulator Started ===");
        } else {
            System.out.println("Simulation is already running!");
        }
    }

    // Stop Simulation
    public void stopSimulation() {
        if (simulationRunning) {
            simulationRunning = false;
            if (currentSession != null) {
                currentSession.terminateSession();
            }
            logger.logEvent("Simulation Stopped");
            System.out.println("=== Cybersecurity Attack Simulator Stopped ===");
        } else {
            System.out.println("No simulation is currently running!");
        }
    }

    // Register User - Version 1: username and password only
    public boolean registerUser(String username, String password) {
        if (username == null || username.trim().isEmpty()) {
            System.out.println("Username cannot be empty!");
            return false;
        }
        if (password == null || password.length() < 4) {
            System.out.println("Password must be at least 4 characters long!");
            return false;
        }

        for (User user : registeredUsers) {
            if (user.getUsername().equals(username)) {
                System.out.println("User " + username + " already exists!");
                return false;
            }
        }

        User newUser = new User(username, password);
        registeredUsers.add(newUser);
        logger.logEvent("New user registered: " + username);
        System.out.println("User " + username + " registered successfully!");
        return true;
    }

    // Register User - Version 2: username, password, and role
    public boolean registerUser(String username, String password, String role) {
        if (username == null || username.trim().isEmpty()) {
            System.out.println("Username cannot be empty!");
            return false;
        }
        if (password == null || password.length() < 4) {
            System.out.println("Password must be at least 4 characters long!");
            return false;
        }

        for (User user : registeredUsers) {
            if (user.getUsername().equals(username)) {
                System.out.println("User " + username + " already exists!");
                return false;
            }
        }

        User newUser = new User(username, password, role);
        registeredUsers.add(newUser);
        logger.logEvent("New user registered: " + username + " with role: " + role);
        System.out.println("User " + username + " registered successfully with role: " + role);
        return true;
    }

    // Register User - Version 3: full details
    public boolean registerUser(String username, String password, String email, String role, String department, int employeeId) {
        if (username == null || username.trim().isEmpty()) {
            System.out.println("Username cannot be empty!");
            return false;
        }
        if (password == null || password.length() < 4) {
            System.out.println("Password must be at least 4 characters long!");
            return false;
        }

        for (User user : registeredUsers) {
            if (user.getUsername().equals(username)) {
                System.out.println("User " + username + " already exists!");
                return false;
            }
        }

        User newUser = new User(username, password, email, role, department, employeeId);
        registeredUsers.add(newUser);
        logger.logEvent("New user registered: " + username);
        System.out.println("User " + username + " registered successfully!");
        return true;
    }

    // Authenticate User
    public User authenticateUser(String username, String password) {
        if (username == null || password == null) {
            System.out.println("Username and password cannot be null!");
            return null;
        }

        for (User user : registeredUsers) {
            if (user.getUsername().equals(username)) {
                if (user.authenticate(password)) {
                    logger.logEvent("User authenticated: " + username);
                    System.out.println("User " + username + " authenticated successfully!");
                    return user;
                } else {
                    System.out.println("Invalid password for user: " + username);
                    return null;
                }
            }
        }
        logger.logEvent("Failed authentication attempt for: " + username);
        System.out.println("User not found: " + username);
        return null;
    }

    // Get registered users list
    public List<User> getRegisteredUsers() {
        return new ArrayList<>(registeredUsers);
    }

    // Get total registered users count
    public int getRegisteredUsersCount() {
        return registeredUsers.size();
    }

    // Display simulation status
    public void displaySimulationStatus() {
        System.out.println("\n=== SIMULATION STATUS ===");
        System.out.println("Simulation Running: " + (simulationRunning ? "YES" : "NO"));
        System.out.println("Active Session: " + (currentSession != null ? currentSession.getSessionName() : "None"));
        System.out.println("Session Status: " + getSessionStatus());
        System.out.println("Registered Users: " + registeredUsers.size());
        for (User user : registeredUsers) {
            System.out.println("  - " + user.getUsername() + " (" + user.getRole() + ")");
        }
    }

    // Get Session Status
    public String getSessionStatus() {
        if (currentSession != null) {
            return currentSession.getSessionStatus();
        }
        return "NO_ACTIVE_SESSION";
    }

    // Create Session
    public void createSession(String sessionName) {
        if (sessionName == null || sessionName.trim().isEmpty()) {
            System.out.println("Session name cannot be empty!");
            return;
        }

        if (currentSession != null && currentSession.getSessionStatus().equals("ACTIVE")) {
            System.out.println("A session is already active! Terminate it first.");
            return;
        }
        currentSession = new Session(sessionName);
        logger.logEvent("Session created: " + sessionName);
        System.out.println("Session '" + sessionName + "' created successfully!");
    }

    // Terminate Session
    public void terminateSession() {
        if (currentSession != null) {
            String sessionName = currentSession.getSessionName();
            currentSession.terminateSession();
            logger.logEvent("Session terminated: " + sessionName);
            System.out.println("Session '" + sessionName + "' terminated.");
            currentSession = null;
        } else {
            System.out.println("No active session to terminate!");
        }
    }

    // Trigger Attack
    public void triggerAttack(String attackType, String target) {
        if (!simulationRunning) {
            System.out.println("Please start the simulation first!");
            return;
        }

        Attack attack = attackFactory.createAttack(attackType);
        if (attack != null) {
            logger.logAttack(attack);
            System.out.println("\n--- Triggering Attack: " + attackType + " ---");
            attack.execute(target);
        } else {
            System.out.println("Unknown attack type: " + attackType);
        }
    }

    // Apply Defense
    public void applyDefense(String defenseType) {
        if (!simulationRunning) {
            System.out.println("Please start the simulation first!");
            return;
        }

        DefenseStrategy defense = defenseFactory.createDefense(defenseType);
        if (defense != null) {
            logger.logDefense(defense);
            System.out.println("\n--- Applying Defense: " + defenseType + " ---");
            defense.applyDefense();
            defense.detectThreat();
        } else {
            System.out.println("Unknown defense type: " + defenseType);
        }
    }
}