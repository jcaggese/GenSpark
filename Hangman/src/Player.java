import java.io.InputStream;
import java.util.Scanner;

public class Player {
    private Scanner in;

    public Player() {

    }

    /**
     * Repeats until user supplies a letter.
     * @param source Input source, most likely System.in
     * @return a letter (lowercase) supplied by user
     */
    public char getLetter(InputStream source) {
        char letter;
        in = new Scanner(source);
        try {
            do {
                letter = in.next().charAt(0);
                if (!Character.isLetter(letter))
                    System.out.println("That wasn't a letter! Try again!");
            } while(!Character.isLetter(letter));
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Input Failed. Prediction: You guessed A.");
            letter = 'A';
        }
        return Character.toLowerCase(letter);
    }

    /**
     * Closes scanner. This also closes the scanners source, so it must be its own method to be called at game end.
     */
    public void finish() {
        in.close();
    }
}
