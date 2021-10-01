package Lab_4;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

/** A map-based class for counting word frequencies from an iterator. */
public class WordCounter {

    /** The map for storing the word counts. */
    private final Map<String, Integer> theMap;

    /** Creates a word counter instance based on the given map. */
    public WordCounter(final Map<String, Integer> theMap) {
        this.theMap = theMap;

    }

    /** Counts the frequencies of all words in the given iterator. */
    public void countWords(final Iterator<String> words) {
        while (words.hasNext()) {
            final String word = words.next();

            /* Determine current tally */
            final int current = theMap.getOrDefault(word, 0);

            /* Enter updated tally into the map */
            theMap.put(word, current+1);
        }
    }

    /**
     * Retrieve the frequency of a particular word.
     * If the word is not in the map, return 0*/
    public int getCount(final String word) {
        return (theMap.get(word) != null) ? theMap.get(word) : 0;
    }

    /** Retrieve the map representing all word frequencies. */
    public Map<String, Integer> getCounts() {
        return Collections.unmodifiableMap(theMap);
    }
}
