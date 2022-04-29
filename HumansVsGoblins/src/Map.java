import java.util.Arrays;

/**
 * Creates and provides tools to maintain the map
 * Map has a default square shape with side length 10.
 */
public class Map {
    private int size;
    private Object[][] map;
    private Human player;
    private int[] playerLoc = new int[2];

    private char borderSide = '|';
    private char borderTop = '—';
    private char corner = '+';
    private char emptySpace = 'X';
    private char gobChar = 'G';
    private char humChar = 'H';

    /**
     * Default constructor. Square Map with side length 10.
     */
    public Map() {
        size = 10;
        map = genMap();
        genEntities();
    }

    /**
     * Constructor for variable Map size
     * @param size
     */
    public Map(int size) {
        this.size = size;
        map = genMap();
        genEntities();
    }

    /**
     * Generate Map with border
     * @return char[][] representation of map
     */
    private Object[][] genMap() {
        Object[][] map = new Object[size][size];
        //fill array with blank space
        for(int i = 0; i < size; i++)
            Arrays.fill(map[i], emptySpace);
        //corners
        map[0][0] = corner;
        map[size - 1][size - 1] = corner;
        map[0][size - 1] = corner;
        map[size - 1][0] = corner;
        //edges
        for (int i = 1; i < size - 1; i++) {
            map[0][i] = borderTop;
            map[size - 1][i] = borderTop;
            map[i][0] = borderSide;
            map[i][size - 1] = borderSide;
        }
        return map;
    }

    /**
     * Generate and place entities on map
     * Player character is always placed at bottommost midpoint
     * (size - 3 # of) Goblins are placed randomly
     * @return The map with entities
     */
    private void genEntities() {
        player = new Human();
        playerLoc[0] = size - 2;
        playerLoc[1] = size / 2;
        map[playerLoc[0]][playerLoc[1]] = player;

        for (int i = 0; i < size - 3; i++) {
            int x;
            int y;
            do { // One goblin allowed on tile
                x = (int) (1 + Math.random() * (size - 2));
                y = (int) (1 + Math.random() * (size - 2));
            } while(!(map[x][y] instanceof Character));
            map[x][y] = new Goblin();
        }
    }

    /**
     * Attempt to move Entity from one position to another
     * @param originX
     * @param originY
     * @param toX
     * @param toY
     * @return False if origin x and y are not an entity or to X and Y are not accessible
     */
    public boolean moveEntity(int originX, int originY, int toX, int toY) {
        if (originX >= size || originY >= size || toX >= size || toY >= size) //Out of bounds
            return false;
        if (!(map[originX][originY] instanceof Entity)) //Non entities cant move
            return false;
        if (!map[toX][toY].equals(emptySpace)) //Non empty spaces cannot be moved onto
            return false;

        if (map[originX][originY] instanceof Human) { //update player location knowledge
            playerLoc[0] = toX;
            playerLoc[1] = toY;
        }
        map[toX][toY] = map[originX][originY]; //move to space
        map[originX][originY] = emptySpace; //empty space
        return true;
    }

    /**
     * Replace space on map with empty space
     * Typical usage would be to remove a dead enemy.
     * @param x
     * @param y
     */
    public void remove(int x, int y) {
        map[x][y] = emptySpace;
    }

    /**
     * @return displayable String version of map
     */
    public String toString() {
        String retStr = "";
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++)
                if (map[i][j] instanceof Human)
                    retStr += humChar + "\t";
                else if (map[i][j] instanceof Goblin)
                    retStr += gobChar + "\t";
                else
                    retStr += map[i][j] + "\t";
            retStr += "\n";
        }
        return retStr;
    }

    public int getSize() {
        return size;
    }

    public Object[][] getMap() {
        return map;
    }

    public Human getPlayer(){
        return player;
    }

    public int[] getPlayerLoc(){
        return playerLoc;
    }
}