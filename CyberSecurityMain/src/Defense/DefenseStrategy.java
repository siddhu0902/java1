package Defense;

import Attack.Attack;

/**
 * DefenseStrategy Interface - Demonstrates ABSTRACTION and POLYMORPHISM
 * All defense mechanisms must implement this interface
 */
public interface DefenseStrategy {

    // Apply defense against an attack
    void applyDefense();

    // Apply defense against a specific attack
    void applyDefense(Attack attack);

    // Detect threat
    boolean detectThreat();

    // Get defense name
    String getDefenseName();

    // Get defense level (1-10)
    int getDefenseLevel();

    // Get defense description
    String getDescription();

    // Check if defense is active
    boolean isActive();

    // Activate/Deactivate defense
    void setActive(boolean active);

    // Get number of threats blocked
    int getThreatsBlocked();

    // Reset defense statistics
    void resetStats();
}