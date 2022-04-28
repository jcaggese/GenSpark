import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EntityTest {
    Entity self;

    @BeforeEach
    void setUp() {
        self = new Human(999, 999999); //high stats to mitigate random 0
    }

    @Test
    void combatKillTest() {
        int health = self.getHealth();
        Entity gob = new Goblin(1, 999999);
        self.combat(gob);
        assertAll(()->assertEquals(self.getHealth(), health),
                ()->assertTrue(gob.getHealth() <= 0));
    }

    @Test
    void combatSurviveTest() {
        int health = self.getHealth();
        Entity gob = new Goblin(9999999, 999999); //very strong gob, unlikely to hit 0
        self.combat(gob);
        assertAll(()->assertTrue(self.getHealth() < health),
                ()->assertTrue(gob.getHealth() < 9999999));
    }
}