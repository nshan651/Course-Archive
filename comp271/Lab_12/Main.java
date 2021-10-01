package Lab_12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static void main(final String[] args) throws InterruptedException, FileNotFoundException {
        // set up the scanner so that it separates words based on space and punctuation
        final Scanner input = new Scanner(new File("src/Lab_4/lesmis")).useDelimiter("[^\\p{Alnum}]+");
        //final Scanner input = new Scanner(System.in).useDelimiter("[^\\p{Alnum}]+");
        // TODO measure the performance for MyHashMap, HashMap, and TreeMap several times each!
        final Map<String, Integer> counts = new MyHashMap<>(6007); // a prime number!
        //final Map<String, Integer> counts = new TreeMap<>(); // a prime number!
        //final Map<String, Integer> counts = new HashMap<>(6007); // a prime number!

        /* Test */
        counts.put("hello", 3);
        counts.put("world", 4);
        counts.put("what", 5);
        counts.put("up", 6);

        /*
        //System.out.println(counts.containsKey(1));
        System.out.println(counts.put("howdy",25));
        System.out.println(counts.containsKey("howdy"));
        System.out.println(counts.containsValue(3));
        //System.out.println(counts.size());

         */
        //System.out.println(counts.toString());

        final var time0 = System.currentTimeMillis(); // current time
        while (input.hasNext()) {
            final var word = input.next();
            final var count = counts.get(word);
            counts.put(word, count == null ? 1 : count + 1);
        }
        final var arr = new ArrayList<Map.Entry<String, Integer>>(counts.size());
        arr.addAll(counts.entrySet());
        System.out.println(
                "time in ms: " + (System.currentTimeMillis() - time0)); // time elapsed since above
        Collections.sort(arr, new DescendingByCount());
        for (var i = 0; i < 10 && i < arr.size(); i += 1) {
            System.out.println(arr.get(i));
        }

    }

}
