import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Random;
import java.util.stream.Stream;

public class Game {
    private String[] dictionary = {"cower", "lion", "wolf", "uncertainty", "daughter", "add", "retired", "conscious",
                                    "shed", "movement"};
    private Player player;
    private String[] states;

    public Game() {
        player = new Player();
        states = genStates();
    }

    /**
     * Ridiculous, hacky solution to maintain one while loop requirement that is supposed to exist
     * in main play method while also maintaining replayability functionality from original game.
     */
    public void playLoop(){
        Stream.generate(() -> play())
                .filter(n->n==false).findFirst();
    }

    /**
     * Main play flow (single game)
     * @return True if the user wants to play again
     */
    private boolean play() {
        String word = genWord();
        HashSet<Character> guesses = new HashSet<>();
        int state = 0;
        boolean win = false;

        System.out.println("H A N G M A N");
        while (state < 6) {
            System.out.println(states[state]);
            String guessCheck = genWordDisplay(word, guesses);
            System.out.println(guessCheck);
            if (guessCheck.indexOf('_') == -1) { // no blanks left, player wins
                win = true;
                break;
            }
            char guess = getGuess(guesses);
            if (word.indexOf(guess) == -1)
                state++; //only advance lose state on incorrect guess
            System.out.println(displayGuesses(guesses));
        }
        if (win)
            System.out.println("Congratulations, you won!");
        else {
            System.out.println(states[6]);
            System.out.println("Oh no! You lose! The word was " + word + ".");
        }

        System.out.println("Would you like to play again? (y/n)");
        char play = Stream.generate(() -> player.getLetter(System.in))
                .filter(n->n=='y'||n=='n').findFirst().orElse('n');
        if (play == 'y') {
            return true;
        } else {
            System.out.println("Thanks for playing!");
            player.finish();
            return false;
        }
    }

    /**
     * Pull games states from a file
     * @return array of states
     */
    private String[] genStates() {
        try {
            return Files.lines(Paths.get("rsc/Hangman_States.txt"))
                    .map(n->n.replaceAll("n", System.lineSeparator()))
                    .toArray(String[]::new);
        } catch (IOException e) {
            System.err.println("Could not access games states.");
            System.exit(1);
        }
        return null;
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
        return Stream.of(word.split("")).map(n->{
            if (guesses.contains(n.charAt(0)))
                return " " + n + " ";
            else
                return " _ ";
        }).reduce(String::concat).orElse("");
    }

    /**
     * Get a guess from a player and add it guesses. Do not allow user to give duplicate letters.
     * @param guesses
     */
    private char getGuess(HashSet<Character> guesses) {
        char guess = Stream.generate(() -> player.getLetter(System.in))
                .filter(n->{
                    if (!guesses.contains(n))
                        return true;
                    else {
                        System.out.println("You already guessed that letter");
                        return false;
                    }}).findFirst().orElse('a');
        guesses.add(guess);
        return guess;
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
