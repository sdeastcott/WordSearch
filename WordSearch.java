import java.util.HashSet;

public class WordSearch
{
    HashSet words;
    boolean[][] seen;
    int dimensionSize;
    String[] paths = new String[] {"n", "ne", "e", "se", "s", "sw", "w", "nw"};

    public WordSearch(HashSet words, int dimensionSize)
    {
        this.words = words;
        this.dimensionSize = dimensionSize;
        seen = new boolean[dimensionSize][dimensionSize];
    }

    /**
     * Sets each value in the 'seen' array to false and calls the recursive 'search'
     * method on the grid's starting index (0, 0)
     *
     * @param letters  word search grid
     */
    public void search(String[][] letters)
    {
        for (int i = 0; i < dimensionSize; i++)
        {
            for (int j = 0; j < dimensionSize; j++)
            {
                seen[i][j] = false;
            }
        }

        search(letters, 0, 0);
    }

    /**
     * Recursively goes through the word search, searching in all possible directions
     * from each letter. The main algorithm here is searching depth first.
     *
     * @param letters  word search grid
     * @param row      starting row
     * @param col      starting col
     */
    public void search(String[][] letters, int row, int col)
    {
        int startRow = row;
        int startCol = col;
        seen[row][col] = true;
        StringBuilder builder = new StringBuilder();

        for (String path : paths)
        {
            row = startRow;
            col = startCol;
            builder.append(letters[row][col]);
            int[] direction = getDirection(path);

            // Make sure the direction we're going doesn't take us off the grid
            while (checkValidMove(row, col, direction))
            {
                row += direction[0];
                col += direction[1];
                builder.append(letters[row][col]);

                // If our string builder is at least 4 letters and can be found in the
                // hash set, print the word with its location to the console.
                if (builder.toString().length() > 3 && words.contains(builder.toString()))
                {
                    builder.append("(" + (startCol + 1) + "," + (startRow + 1) + "," + path + ")");
                    System.out.println(builder.toString());
                }

                // If we have yet to start searching from the location of our next move,
                // recursively call this method.
                if (!seen[startRow + direction[0]][startCol + direction[1]])
                {
                    search(letters, startRow + direction[0], startCol + direction[1]);
                }
            }

            // Once you're done searching a particular path, clear the string builder.
            builder.setLength(0);
        }
    }

    /**
     * Given a row and column, this method checks to see if our next move will take us
     * out of bounds
     *
     * @param row        current row
     * @param col        current col
     * @param direction  what direction we're headed
     * @return           true if valid move, false if not
     */
    private boolean checkValidMove(int row, int col, int[] direction)
    {
        if (row + direction[0] < 0 || row + direction[0] > dimensionSize - 1) return false;
        if (col + direction[1] < 0 || col + direction[1] > dimensionSize - 1) return false;

        return true;
    }

    /**
     * Given a path, returns an int array containing row and column change
     *
     * Example: If we'd like to go north, we need to subtract 1 from our current
     *          row and stay in our current column. Therefore, we return {-1, 0}.
     *
     * @param path  cardinal direction with regards to the word search
     * @return      int array containing row and column change
     */
    private int[] getDirection(String path)
    {
        int[] direction = null;

        if (path.equals("n"))
        {
            direction = new int[] {-1, 0};
        }
        else if (path.equals("ne"))
        {
            direction = new int[] {-1, 1};
        }
        else if (path.equals("e"))
        {
            direction = new int[] {0, 1};
        }
        else if (path.equals("se"))
        {
            direction = new int[] {1, 1};
        }
        else if (path.equals("s"))
        {
            direction = new int[] {1, 0};
        }
        else if (path.equals("sw"))
        {
            direction = new int[] {1, -1};
        }
        else if (path.equals("w"))
        {
            direction = new int[] {0, -1};
        }
        else if (path.equals("nw"))
        {
            direction = new int[] {-1, -1};
        }

        return direction;
    }
}
