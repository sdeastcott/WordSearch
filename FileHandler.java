import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

public class FileHandler
{
    private int dimensionSize;

    /**
     * @param fileName  file containing grid of letters
     * @return          2d array representation of the word search
     */
    public String[][] readPuzzle(String fileName)
    {
        int i = 0;
        String line;
        String[][] letters = null;

        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            dimensionSize = Integer.parseInt(reader.readLine());
            letters = new String[dimensionSize][dimensionSize];

            // Reads and splits the file line by line and stores each row
            // into a 2d array.
            while ((line = reader.readLine()) != null)
            {
                // If there are accidental blank lines in the file, not
                // accounting for them will break the program.
                if (!line.equals(""))
                {
                    letters[i] = line.trim().split("\\s+");
                    i++;
                }
            }
        }
        catch (FileNotFoundException exception)
        {
            System.out.println(exception.getMessage());
            System.exit(1);
        }
        catch (IOException exception)
        {
            System.out.println(exception.getMessage());
            System.exit(1);
        }
        catch (IndexOutOfBoundsException exception)
        {
            System.out.println(exception.getMessage());
            System.exit(1);
        }

        return letters;
    }

    /**
     * @param fileName  file containing words
     * @return          hash set of words
     */
    public HashSet readWords(String fileName)
    {
        String word;
        String line;
        HashSet<String> words = new HashSet<String>();

        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            // Reads the file line by line, adding each word to the hash set
            while ((line = reader.readLine()) != null)
            {
                // If there are accidental blank lines in the file, not
                // accounting for them will break the program.
                if (!line.equals(""))
                {
                    word = line.trim();
                    words.add(word);
                }
            }
        }
        catch (FileNotFoundException exception)
        {
            System.out.println(exception.getMessage());
            System.exit(1);
        }
        catch (IOException exception)
        {
            System.out.println(exception.getMessage());
            System.exit(1);
        }

        return words;
    }

    /**
     * Returns the dimension size of the word search. We only need one dimension
     * (length OR width) since the word search is a square grid.
     *
     * @return  dimension size
     */
    public int getDimensionSize()
    {
        return dimensionSize;
    }
}
