package Attack;

/**
 * Attack Interface - Demonstrates ABSTRACTION and POLYMORPHISM
 * All attack types must implement this interface
 */
public interface Attack {

    // Execute attack on a target
    void execute(String target);

    // Get attack type name
    String getAttackType();

    // Get attack severity level (1-10)
    int getSeverityLevel();

    // Get attack description
    String getDescription();

    // Check if attack was successful
    boolean isSuccessful();

    // Get attack duration in milliseconds
    long getAttackDuration();

    // Reset attack for new simulation
    void resetAttack();
}