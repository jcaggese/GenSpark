import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("You are in a land full of dragons. In front of you, you see two caves." +
                "In one cave, the dragon is friendly and will share his treasure with you." +
                "The other dragon is greedy and hungry and will eat you in sight." +
                "Which cave will you go into? (1 or 2)");
        String response = fetchResponse();
        System.out.println("You approach the cave..." +
                "\nIt is dark and spooky..." +
                "\nA large dragon jumps out in front of you! He opens his jaws and...");
        System.out.println(generateOutcome(response));
    }

    /**
     * @return User response, cave 1 or 2
     */
    public static String fetchResponse() {
        String response = "1";
        try (Scanner in = new Scanner(System.in)) {
            response = in.nextLine();
            while (!response.equals("1") && !response.equals("2")) {
                System.out.println("There are only 2 caves to choose from! Please pick 1 or 2!");
                response = in.nextLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Uh oh! Error detected! Exiting game.");
            System.exit(1);
        }
        return response;
    }

    /**
     * @param response User generated response, 1 or 2
     * @return String representing win state.
     */
    public static String generateOutcome(String response) {
        if (response.equals("1"))
            return "Gobbles you down in one bite!";
        else
            return "Politely offers you some of his treasure.";
    }
}