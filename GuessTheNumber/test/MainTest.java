import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void getNumericInput() {
        String testString = "2";
        ByteArrayInputStream in = new ByteArrayInputStream(testString.getBytes());
        assertEquals(2, Main.getNumericInput(new Scanner(in)));
    }

    @Test
    void getNumericInputWithError() {
        String testString = "Word\n11";
        ByteArrayInputStream in = new ByteArrayInputStream(testString.getBytes());
        assertEquals(11, Main.getNumericInput(new Scanner(in)));
    }

    @Test
    void playAgain() {
        String testString = "Y";
        ByteArrayInputStream in = new ByteArrayInputStream(testString.getBytes());
        assertEquals(true, Main.playAgain(new Scanner(in)));
    }
}