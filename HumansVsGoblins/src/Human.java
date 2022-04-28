/**
 * The player is a Human in HvG
 * Default Health: 20
 * Default Strength: 5
 */
public class Human extends Entity{

    /**
     * Default Goblin Constructor.
     * A goblin has 5 health and 3 strength.
     */
    public Human() {
        health = 20;
        strength = 5;
    }

    /**
     * Special Constructor for potential humans with different stats.
     * @param health
     * @param strength
     */
    public Human(int health, int strength) {
        this.health = health;
        this.strength = strength;
    }

    public String toString() {
        return "A human with " + health + " and " + strength + " strength.";
    }
}