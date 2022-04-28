import java.util.Arrays;

/**
 * Creates and provides tools to maintain the map
 * Map has a default square shape with side length 10.
 */
public class Map {
    int size;
    char[][] map;

    /**
     * Default constructor. Square Map with side length 10.
     */
    public Map() {
        size = 10;
        map = genMap();
    }

    /**
     * Constructor for variable Map size
     * @param size
     */
    public Map(int size) {
        this.size = size;
        map = genMap();
    }

    /**
     * Generate Map with border
     * @return char[][] representation of map
     */
    private char[][] genMap() {
        char[][] map = new char[size][size];
        //fill array with blank space
        for(int i = 0; i < size; i++)
            Arrays.fill(map[i], 'X');
        //corners
        map[0][0] = '+';
        map[size - 1][size - 1] = '+';
        map[0][size - 1] = '+';
        map[size - 1][0] = '+';
        //edges
        for (int i = 1; i < size - 1; i++) {
            map[0][i] = '-';
            map[size - 1][i] = '-';
            map[i][0] = '|';
            map[i][size - 1] = '|';
        }
        return map;
    }

    /**
     * @return displayable string version of map
     */
    public String toString() {
        String retStr = "";
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++)
                retStr += map[i][j] + "\t";
            retStr += "\n";
        }
        return retStr;
    }
}
