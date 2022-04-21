import java.io.InputStream;
import java.util.Scanner;

public class Player {
    public Player() {

    }

    /**
     * Repeats until user supplies a letter.
     * @param source Input source, most likely System.in
     * @return a letter (lowercase) supplied by user
     */
    public char getLetter(InputStream source) {
        char letter;
        try (Scanner in = new Scanner(source)) {
            do {
                System.out.println("Guess a new letter!");
                letter = in.next().charAt(0);
                System.out.println(letter);
            } while(!Character.isLetter(letter));
        } catch (Exception e) {
            System.err.println("Input Failed. Prediction: You guessed A.");
            letter = 'A';
        }
        return Character.toLowerCase(letter);
    }
}
