import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    Player player;

    @BeforeEach
    void setUp() {
        player = new Player();
    }

    @Test
    void getLetterCorrectInput() {
        String testString = "Ww";
        ByteArrayInputStream in = new ByteArrayInputStream(testString.getBytes());
        assertEquals('w', player.getLetter(in));
    }

    @Test
    void getLetterIncorrectInput() {
        String testString = "1\nG";
        ByteArrayInputStream in = new ByteArrayInputStream(testString.getBytes());
        assertEquals('g', player.getLetter(in));
    }
}