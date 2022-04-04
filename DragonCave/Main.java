package genspark.projects.GenSpark.DragonCave;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            System.out.println("You are in a land full of dragons. In front of you, you see two caves." +
                    "In one cave, the dragon is friendly and will share his treasure with you." +
                    "The other dragon is greedy and hungry and will eat you in sight." +
                    "Which cave will you go into? (1 or 2)");
            int response = in.nextInt();
            while (response != 1 && response != 2) {
                System.out.println("There are only 2 caves to choose from! Please pick 1 or 2!");
                response = in.nextInt();
            }
                System.out.println("You approach the cave..." +
                        "\nIt is dark and spooky..." +
                        "\nA large dragon jumps out in front of you! He opens his jaws and...");
            if (response == 1)
                System.out.println("Gobbles you down in one bite!");
            else
                System.out.println("Politely offers you some of his treasure.");
        }
    }
}