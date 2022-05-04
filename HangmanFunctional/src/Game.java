import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Random;
import java.util.stream.Stream;

public class Game {
    private String[] dictionary = {"cower", "lion", "wolf", "uncertainty", "daughter", "add", "retired", "conscious",
                                    "shed", "movement"};
    private Player player;
    private String[] states;
    private int score;

    public Game() {
        player = new Player();
        states = genStates();
        score = 0;
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
        if (win) {
            System.out.println("Congratulations, you won! Your current streak is: " + ++score);
        }
        else {
            System.out.println(states[6]);
            System.out.println("Oh no! You lose! The word was " + word + ".");
            System.out.println();
            System.out.println("Your score is " + score);
            System.out.println(checkHighScore(score));
            score = 0;
        }

        System.out.println("Would you like to play again? (y/n)");
        char play = Stream.generate(() -> player.getLetter(System.in))
                .filter(n->n=='y'||n=='n').findFirst().orElse('n');
        if (play == 'y') {
            return true;
        } else {
            if (win)
                System.out.println(checkHighScore(score));
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

    /**
     * Checks a score to see if it is a high score.
     * Updates high score file if user got the high score.
     * @param score
     * @return Message indicating what the high score is
     */
    private String checkHighScore(int score) {
        try {
            Path file = Paths.get("rsc/high_scores.txt");
            if (Files.notExists(file)) {
                BufferedWriter write = Files.newBufferedWriter(file);
                write.write(player.getName()+ "," + score);
                write.close();
                return "Congratulations, you have the high score!";
            }
            else {
                String[] highScore = Files.lines(file).findFirst().orElse(",").split(",");
                if (score < Integer.parseInt(highScore[1]))
                    return "The current high score is " + highScore[1] + " belonging to " + highScore[0] + ".";
                else {
                    BufferedWriter write = Files.newBufferedWriter(file);
                    write.write(player.getName() + "," + score);
                    write.close();
                    return "Congratulations, you have the high score!";
                }
            }
        } catch (Exception e) {
            return "Could not retrieve high scores.";
        }
    }
}
