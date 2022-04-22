import java.util.HashSet;
import java.util.Random;

public class Game {
    private String[] dictionary = {"cower", "lion", "wolf", "uncertainty", "daughter", "add", "retired", "conscious",
                                    "shed", "movement"};
    private Player player;

    public Game() {
        player = new Player();
    }

    public void play() {
        String word = genWord();
        HashSet<Character> guesses = new HashSet<>();
        int state = 0;
        boolean win = false;

        System.out.println("H A N G M A N");
        while (state < 6) {
            System.out.println(genDisplay(state));
            getGuess(guesses);
            System.out.println(displayGuesses(guesses));
            String guessCheck = genWordDisplay(word, guesses);
            System.out.println(guessCheck);
            if (guessCheck.indexOf('_') == -1) { // no blanks left, player wins
                win = true;
                break;
            }
            state++;
        }
        if (win)
            System.out.println("Congratulations, you won!");
        else
            System.out.println("RIP :(");
    }

    /**
     * @param state Loss state of the game
     * @return A String defining a sysout display of the current game state
     */
    private String genDisplay(int state){
        String disp = "";
        disp += "+---+\n";
        if (state > 0)
            disp += " O";
        disp += "\t|\n";
        if (state == 2)
            disp += " |";
        else if (state > 2)
            disp += "-|";
        if (state > 3)
            disp += "-";
        disp += "\t|\n";
        if (state > 4)
            disp += "/";
        if (state > 5)
            disp += " \\";
        disp += "\t|\n";
        disp += "======\n";
        return disp;
    }

    /**
     * @return a random word from the game's dictionary
     */
    private String genWord() {
        Random rand = new Random();
        return dictionary[rand.nextInt(dictionary.length)];
    }

    /**
     * @param word The word to guess
     * @param guesses The set of the player's current guesses
     * @return a string representation of revealed letters
     */
    private String genWordDisplay(String word, HashSet<Character> guesses) {
        String disp = "";
        for (int i = 0; i < word.length(); i++) {
            if (guesses.contains(word.charAt(i)))
                disp += " " + word.charAt(i) + " ";
            else
                disp += " _ ";
        }
        return disp;
    }

    /**
     * Get a guess from a player and add it guesses. Do not allow user to give duplicate letters.
     * @param guesses
     */
    private void getGuess(HashSet<Character> guesses) {
        char guess;
        do {
            guess = player.getLetter(System.in);
            if (guesses.contains(guess))
                System.out.println("You already guessed that letter!");
        } while (guesses.contains(guess));
        guesses.add(guess);
    }

    /**
     * Display all guesses player has tried
     * @param guesses
     * @return String
     */
    private String displayGuesses(HashSet<Character> guesses) {
        String retStr = "Guesses: " + guesses;
        return retStr;
    }
}
