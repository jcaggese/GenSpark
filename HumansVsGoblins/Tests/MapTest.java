import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {
    Map map;

    @BeforeEach
    void setUp() {
        map = new Map(3);
    }

    @Test
    void testGeneration() {
        String mapTest = "+\t-\t+\t\n" +
                        "|\tX\t|\t\n" +
                        "+\t-\t+\t\n";
        assertTrue(mapTest.equals(map.toString()));
    }
}