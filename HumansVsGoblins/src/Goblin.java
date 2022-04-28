/**
 * A goblin represents an enemy in HvG
 * A goblin has 5 health and 3 strength by default
 */
public class Goblin extends Entity{

    /**
     * Default Goblin Constructor.
     * A goblin has 5 health and 3 strength.
     */
    public Goblin() {
        health = 5;
        strength = 3;
    }

    /**
     * Special Constructor for potential goblins with different stats.
     * @param health
     * @param strength
     */
    public Goblin(int health, int strength) {
        this.health = health;
        this.strength = strength;
    }

    public String toString() {
        return "A vicious, snarling goblin with " + health + " and " + strength + " strength.";
    }
}
