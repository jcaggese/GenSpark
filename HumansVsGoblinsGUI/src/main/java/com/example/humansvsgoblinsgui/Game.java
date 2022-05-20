package com.example.humansvsgoblinsgui;

/**
 * Controls game flow
 */
public class Game {
    private Map map;
    private Human player;
    private String status;

    public Game(){
        map = new Map();
        player = map.getPlayer();
        status = "Use arrow keys to move.";
    }

    public Game(int size){
        map = new Map(size);
        player = map.getPlayer();
        status = "Use arrow keys to move.";
    }

    /**
     * Check map for goblins. This is the win condition.
     * @return True if there are goblins left on the map
     */
    public boolean checkForGoblins(){
        Object[][] grid = map.getMap();
        int size = map.getSize();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (grid[i][j] instanceof Goblin)
                    return true;
            }
        }
        return false;
    }

    /**
     * Moves the player according to input String
     * @param input
     * @return true if input was bad
     */
    public boolean playerMove(String input) {
        char move = getMove(input);
        switch (move) {
            case 'x':   System.out.println("Command not recognized. Try another.");
                        return true;
            case 'n':   return attemptMove('n');
            case 'e':   return attemptMove('e');
            case 's':   return attemptMove('s');
            case 'w':   return attemptMove('w');
            default:    return true;
        }
    }

    /**
     * Interpret input
     * Accepted commands are first letters of directions and full word directions
     * @param input
     * @return char interpretation of input
     */
    private char getMove(String input) {
        input = input.toLowerCase();
        if (input.equals("n") || input.equals("north"))
            return 'n';
        else if (input.equals("e") || input.equals("east"))
            return 'e';
        else if (input.equals("w") || input.equals("west"))
            return 'w';
        else if (input.equals("s") || input.equals("south"))
            return 's';
        else
            return 'x';
    }

    /**
     * Attempt to move in indicated direction
     * @param move
     * @return false if move was successful
     */
    private boolean attemptMove(char move) {
        int[] playerLoc = map.getPlayerLoc();
        int toX = playerLoc[0];
        int toY = playerLoc[1];
        switch(move) {
            case 'n':   toX--;
                        break;
            case 's':   toX++;
                        break;
            case 'w':   toY--;
                        break;
            case 'e':   toY++;
                        break;
            default:    return false;
        }
        if (map.moveEntity(playerLoc[0], playerLoc[1], toX, toY)) { //tile was empty
            status = "";
            return false;
        }
        else { //tile inhabited by something
            if (map.getMap()[toX][toY] instanceof Goblin) { //initiate combat
                Goblin enemy = (Goblin) map.getMap()[toX][toY];
                System.out.println("You engage in combat with " + enemy);
                int enemyHealth = enemy.getHealth();
                int playerHealth = player.getHealth();
                player.combat(enemy);
                if (enemy.getHealth() < 1) {
                    status = "You defeated the " + enemy.getClass().getSimpleName() + ".";
                    map.remove(toX, toY);
                } else {
                    status = "You attack the " + enemy.getClass().getSimpleName() + " for " +
                            (enemyHealth - enemy.getHealth()) + " damage.\n" +
                            "...but it survives with " + enemy.getHealth() + " health remaining!\n" +
                            "The " + enemy.getClass().getSimpleName() + " attacks you back for " +
                                     (playerHealth - player.getHealth()) + " damage.";
                }
                return false;
            } else { //non interactable tile
                status = "You can't move here! Something is in the way!";
                return true;
            }
        }
    }

    public Map getMap() {
        return map;
    }

    public String getStatus() {
        return status;
    }
}
