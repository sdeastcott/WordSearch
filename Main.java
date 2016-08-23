//****************************
// Project #3 - Word Search
//
// Author: Steven Eastcott
// CWID:   11491519
// Course: CS 360
//****************************

import java.util.HashSet;

public class Main
{
    public static void main(String[] args)
    {
        FileHandler fileHandler = new FileHandler();
        String[][] letters = fileHandler.readPuzzle("puzzle.txt");
        HashSet words = fileHandler.readWords("words.txt");
        int dimensionSize = fileHandler.getDimensionSize();
        WordSearch wordSearch = new WordSearch(words, dimensionSize);
        wordSearch.search(letters);
    }
}
