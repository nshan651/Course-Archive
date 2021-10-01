package Lab_4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/** Main method */
public class Main {
    public static void main(final String[] args) throws InterruptedException, FileNotFoundException {

        /*
         * Set up the scanner so that it separates words based on space and punctuation.
         * Each instance of mapMyWords requires a different scanner or else it will return an empty map
         */
        final Scanner hashText = new Scanner(new File("src/Lab_4/lesmis")).useDelimiter("[^\\p{Alnum}]+");
        final Scanner treeText = new Scanner(new File("src/Lab_4/lesmis")).useDelimiter("[^\\p{Alnum}]+");
        //final Scanner input = new Scanner(System.in).useDelimiter("[^\\p{Alnum}]+");  // <-- console IO test Scanner

        /* Create instances of a hash map and a tree map */
        final Map<String, Integer> hashWords = new HashMap<>();
        final Map<String, Integer> treeWords = new TreeMap<>();

        /* Create two static methods, one to test the hash map and one to test the tree map */
        mapMyWords(hashWords, hashText);
        mapMyWords(treeWords, treeText);

    }

    /** Static method using WordCounter and DescendingByCount to find the top 10 most frequent words of an input */
    public static void mapMyWords(Map<String, Integer> theMap, Iterator<String> input) {
        /* Get the time of the program */
        final long start = System.currentTimeMillis();

        /* Instantiate WordCounter */
        final WordCounter wordCounter = new WordCounter(theMap);

        /* Count words in the input scanner */
        wordCounter.countWords(input);

        //System.err.println(theMap); // test by printing values of the map

        /* Determine size of the resulting map */
        final int size = theMap.size();

        /* Create an ArrayList with the entry set of theMap */
        final ArrayList<Map.Entry<String, Integer>> items = new ArrayList<>(theMap.entrySet());

        /* Sort the ArrayList in descending order by count */
        Collections.sort(items, new DescendingByCount());

        /* Print (up to) the 10 most frequently found words */
        System.out.println("Top 10 words");
        for (int i = 0; i < 10 && i < items.size(); i++) {
            System.out.println(items.get(i));
        }

        /* Calculate runtime ms */
        final long end = System.currentTimeMillis() - start;
        System.out.println("Program executed in " + end + " milliseconds\n");
    }

}
