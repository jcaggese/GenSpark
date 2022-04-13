import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Random rand = new Random();
        try (Scanner in = new Scanner(System.in)) {
            System.out.println("Hello! What is your name?");
            String user = in.nextLine();
            boolean play = true;
            while (play) {
                System.out.printf("Well, %s, I am thinking of a number between 1 and 20. Take a guess.%n", user);
                playGame(in, user);
                System.out.println("Would you like to play again? (Y/N)");
                play = playAgain(in);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Uh oh! Error detected! Exiting game.");
        }
    }

    /**
     * Allows user to input numbers below 1 and above 20 if they really want
     * @param in Scanner to generate user input
     * @return a number between 1 and 20
     */
    private static int getNumericInput(Scanner in) {
        boolean valid = false;
        int guess = -1;
        while(!valid) { //enforce numeric input
            String input = in.nextLine();
            try {
                guess = Integer.parseInt(input);
                valid = true;
            } catch (NumberFormatException e) {
                System.out.println("Guess a number between 1 and 20!");
            }
        }
        return guess;
    }

    /**
     * Main game loop
     * @param in Scanner to get user response
     * @param user User's name
     */
    private static void playGame(Scanner in, String user) {
        int num = new Random().nextInt(20) + 1;
        int i = 0;
        boolean wrong = true;
        while (i++ < 6 && wrong) {
            int guess = getNumericInput(in);
            if (guess < num) {
                System.out.println("Your guess is too low. Try again." +
                        "\n You have " + (6 - i) + " guess(es) remaining.");
            } else if (guess > num) {
                System.out.println("Your guess is too high. Try again." +
                        "\n You have " + (6 - i) + " guess(es) remaining.");
            } else {
                System.out.printf("Good job, %s, you got the number in %d guess(es)%n", user, i);
                wrong = false;
            }
        }
        if (wrong)
            System.out.printf("You failed to guess the number in 6 tries.%nThe number was: %d.%n", num);

    }

    /**
     * @param in Scanner to get user input
     * @return True if user wants to play again, False if not
     */
    private static boolean playAgain(Scanner in) {
        while (true) {
            String again = in.nextLine();
            if (again.equals("Y") || again.equals("y")) {
                return true;
            }
            else if (again.equals("N") || again.equals("n")){
                System.out.println("Thanks for playing.");
                return false;
            }
        }
    }
}
