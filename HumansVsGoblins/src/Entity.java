/**
 * Abstract class representing a combat and movement-based entity
 */
abstract class Entity {
    protected int health;
    protected int strength;

    /**
     * Formula for damage is Strength * random[0, 1]
     * Attacker gets advantage, if they initiate combat and enemy dies from attack,
     * they suffer no damage in turn.
     * @param enemy The enemy to be attacked.
     */
    public void combat(Entity enemy) {
        int damage = (int) (strength * Math.random());
        int oppHealth =  enemy.getHealth() - damage;
        if (oppHealth > 0) { //attacker takes damage
            health -= enemy.getStrength() * Math.random();
        }
        enemy.setHealth(oppHealth);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getStrength() {
        return strength;
    }
}
