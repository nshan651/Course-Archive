package Lab_4;

import java.util.Comparator;
import java.util.Map;
import java.util.Set;

/** The comparison strategy for sorting the array by the word count. */
public class DescendingByCount implements Comparator<Map.Entry<String, Integer>> {

    /** Overwrite the compare() method in java.util.Comparator to compare to map entries*/
    public int compare(final Map.Entry<String, Integer> l, final Map.Entry<String, Integer> r) {
        return r.getValue() - l.getValue();
    }
}
