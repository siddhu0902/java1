package Security;

import Attack.Attack;
import Attack.AttackFactory;
import Defense.DefenseStrategy;
import Defense.DefenseFactory;
import Logs.Logger;
import Users.User;
import Simulation.Simulator;
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

    // Register User
    public boolean registerUser(String username, String password) {
        // Check if user already exists
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

    // Authenticate User
    public User authenticateUser(String username, String password) {
        for (User user : registeredUsers) {
            if (user.getUsername().equals(username) && user.authenticate(password)) {
                logger.logEvent("User authenticated: " + username);
                return user;
            }
        }
        logger.logEvent("Failed authentication attempt for: " + username);
        return null;
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

    // Create Session
    public void createSession(String sessionName) {
        if (currentSession != null && currentSession.getSessionStatus().equals("ACTIVE")) {
            System.out.println("A session is already active! Terminate it first.");
            return;
        }
        currentSession = new Session(sessionName);
        logger.logEvent("Session created: " + sessionName);
    }

    // Terminate Session
    public void terminateSession() {
        if (currentSession != null) {
            currentSession.terminateSession();
            logger.logEvent("Session terminated: " + currentSession.getSessionName());
            currentSession = null;
        }
    }

    // Get Session Status
    public String getSessionStatus() {
        if (currentSession != null) {
            return currentSession.getSessionStatus();
        }
        return "NO_ACTIVE_SESSION";
    }

    // Get Current Session
    public Session getCurrentSession() {
        return currentSession;
    }

    // Check if simulation is running
    public boolean isSimulationRunning() {
        return simulationRunning;
    }

    // Get registered users list
    public List<User> getRegisteredUsers() {
        return registeredUsers;
    }
}