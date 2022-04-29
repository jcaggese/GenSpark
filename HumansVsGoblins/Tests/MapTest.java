import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {
    Map map;

    @BeforeEach
    void setUp() {
        map = new Map(10);
    }

    @Test
    void testGeneration() {
        Map map = new Map(3);
        String mapTest = "+\t—\t+\t\n" +
                        "|\tH\t|\t\n" +
                        "+\t—\t+\t\n";
        assertTrue(mapTest.equals(map.toString()));
    }

    @Test
    void testGoblinCount() {
        Object[][] mapObj = map.getMap();
        int goblinCount = 0;
        int size = map.getSize();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (mapObj[i][j] instanceof Goblin)
                    goblinCount++;
            }
        }
        assertEquals(size - 3, goblinCount);
    }

    @Test
    void validMove(){
        int size = map.getSize();
        map.remove(size - 3, size/2);
        assertTrue(map.moveEntity(size - 2, size / 2, size - 3, size / 2));
    }

    @Test
    void invalidMove(){
        int size = map.getSize();
        assertAll(()->assertFalse(map.moveEntity(size - 2, size / 2, size - 1, size / 2)),
                ()->assertFalse(map.moveEntity(size - 1, size / 2, size - 3, size / 2)),
                ()->assertFalse(map.moveEntity(size, size, size, size)));
    }
}