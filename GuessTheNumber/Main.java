package genspark.projects.GenSpark.GuessTheNumber;

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
                int num = rand.nextInt(20) + 1;
                int i = 0;
                boolean wrong = true;
                while (i++ < 6 && wrong) {
                    int guess = in.nextInt();
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

                System.out.println("Would you like to play again? (Y/N)");
                boolean accept = false;
                while (!accept) {
                    String again = in.nextLine();
                    if (again.equals("Y") || again.equals("y")) {
                        accept = true;
                    }
                    else if (again.equals("N") || again.equals("n")){
                        accept = true;
                        play = false;
                        System.out.println("Thanks for playing.");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
