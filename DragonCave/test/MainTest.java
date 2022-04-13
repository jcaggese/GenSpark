import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Test
    void fetchResponse() {
        String testString = "2"; // test just correct response
        System.setIn(new ByteArrayInputStream(testString.getBytes()));
        assertEquals("2", Main.fetchResponse());
    }

    @Test
    void fetchUserErrorResponse() {
        String testString = "3\n1"; // test incorrect response followed by correct
        System.setIn(new ByteArrayInputStream(testString.getBytes()));
        assertEquals("1", Main.fetchResponse());
    }

    @Test
    void generateOutcome() {
        assertEquals("Gobbles you down in one bite!", Main.generateOutcome("1"));
        assertEquals("Politely offers you some of his treasure.", Main.generateOutcome("2"));
    }
}