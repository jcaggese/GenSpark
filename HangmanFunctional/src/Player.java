import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Player {
    private BufferedReader in;
    private String name;

    public Player() {
        promptName();
    }

    public Player(String name) {
        this.name = name;
    }

    /**
     * Repeats until user supplies a letter.
     * @param source Input source, most likely System.in
     * @return a letter (lowercase) supplied by user
     */
    public char getLetter(InputStream source) {
        char letter;
        in = new BufferedReader(new InputStreamReader(source));
        try {
            letter = in.lines().filter(n -> !n.isBlank())
                    .map(n -> n.charAt(0))
                    .filter(n -> {
                        if (Character.isLetter(n))
                            return true;
                        else {
                            System.out.println("That wasn't a letter! Try again.");
                            return false;
                        }
                    })
                    .findFirst().orElse('A');
        } catch (Exception e) {
            System.err.println("Input Failed. Prediction: You guessed A.");
            letter = 'A';
        }
        return Character.toLowerCase(letter);
    }

    /**
     * Prompts the user for a name
     */
    private void promptName() {
        System.out.println("Enter player name: ");
        try {
            in = new BufferedReader(new InputStreamReader(System.in));
            name = in.readLine();
        } catch (IOException e) {
            System.err.println("Could not retrieve name. Your name is Greg now.");
            name = "greg.";
        }
    }

    /**
     * @return String name
     */
    public String getName() {
        return name;
    }

    /**
     * Closes scanner. This also closes the scanners source, so it must be its own method to be called at game end.
     */
    public void finish() {
        try {
            in.close();
        } catch (IOException e) {
            System.err.println("Error: Player Buffered Reader may still be open.");
        }
    }
}
