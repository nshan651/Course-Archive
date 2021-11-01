package pset6;
import java.util.*;
import java.security.*;
import java.io.*;

public class WordHash<E> {
    /*
    Table sizes:
    
    1. 262,127 / 524,287
    2 & 3. 262,144/524,288 (2^19)

        *** and 524,287 (Prime, 2^19-1)
    */
    public final int tableSize;
    public int collisions = 0;
    public ArrayList<LinkedList<E>> htab;

    public WordHash() {
        this.tableSize = 262127;
        initTable(tableSize);
    }

    public WordHash(int size) {
        this.tableSize = size;
        initTable(tableSize);
    }

    /** Initialize the table with empty linked lists */
    private void initTable(int size) {
        this.htab = new ArrayList<LinkedList<E>>(size);
        for (int i =0; i < size; i++)
            htab.add(new LinkedList<>());
    }

    /** Find the average linked list size */
    public double computeAverage() {
        double avg=0;
        int filledList = 0;
        for (LinkedList<E> entry : htab) {
            if (!entry.isEmpty()) {
                avg+=entry.size();
                filledList++;
            }
        }
        return avg/filledList;
    }

    /** Insert value by key */
    public void insertValue(E key, int hash) {
        
        LinkedList<E> bucket = htab.get(hash);
        
        if (!bucket.contains(key)) {
            if (bucket.size() > 0)
                collisions++;
            bucket.addLast(key);
        }
    }

    /** Print stats */
    public void printResult(long start, long end) {
        System.out.println("Time (ms): " + (end-start));
        System.out.println("Average linked list size: " + computeAverage());
        System.out.println("Collisions: " + collisions);
    }

    public static void main(String[] args) throws FileNotFoundException {

        // Number of words in the file
        final int WORDS = 251885;

        // Init WordHash objects
        WordHash<String> wh1 = new WordHash<>(524287);  // 262127
        WordHash<String> wh2 = new WordHash<>(524287); 
        WordHash<String> wh3 = new WordHash<>(524288);  //262144
        // Init temp array to store scanned words
        String[] arry = new String[WORDS];

        // Create File and Buffer objects
        File file = new File("./comp272/pset6/EnglishWordList.txt");
        Scanner sc = new Scanner(file);
        long start, end;
        int count=0;
        
        // Load words into String array
        while(sc.hasNext()) {
            String word = sc.nextLine();
            arry[count]=word;
            count++;
        }
        sc.close();

        // Time the hash (separate loop so Scanner doesn't interfere)
        // Part 1
        start = System.currentTimeMillis();
        for (String word : arry) {
            int code = hashCode1(word, wh1.tableSize);
            wh1.insertValue(word, code);
        }
        end = System.currentTimeMillis();
        System.out.println("Code 1: ");
        wh1.printResult(start, end);

        // Part 2
        start = System.currentTimeMillis();
        for (String word : arry) {
            int code = hashCode2(word, wh2.tableSize);
            wh2.insertValue(word, code);
        }
        end = System.currentTimeMillis();
        System.out.println("\nCode 2: ");
        wh2.printResult(start, end);

        // Part 3
        start = System.currentTimeMillis();
        for (String word : arry) {
            int code = hashCode3(word);
            wh3.insertValue(word, code);
        }
        end = System.currentTimeMillis();
        System.out.println("\nCode 3: ");
        wh3.printResult(start, end);
    }

    /** First hash code */
    public static int hashCode1(Object key, int size) {
        return (key==null) ? 0 : (Math.abs(key.hashCode())%size); 
    }

    /** Second hash code */
    public static int hashCode2(Object key, int size) {
        int h;
        return (key==null) ? 0 : Math.abs((h=key.hashCode())^(h>>>16)) % size; 
    }

    /** Final hash code (SHA-256) */
    public static int hashCode3(String s) { 
        /* Notes:
        {2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61};
        {2,7,17,29,41,53,67,79,97,107,127,139,157,173,191,199,227,239};
        {2,7,17,29,41,53,67,79,97,107,127,139,157,173,191,199,227,239,241};
        */

        int[] firstNPrimes = {2,7,17,29,41,53,67,79,97,107,127,139,157,173,191,199,227,239,241};
        int result = 0;
        byte[] sb=s.getBytes(); 
        byte[] key = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256"); 
            key=md.digest(sb); 
        } 
        catch (NoSuchAlgorithmException e) { 
                e.printStackTrace(); 
        }
        
        BitSet bs = BitSet.valueOf(key); 
        
        for (int i =0; i < firstNPrimes.length; i++) {
            int prime = firstNPrimes[i];
            boolean flippedBit = bs.get(prime);
            if (flippedBit)
                result+=Math.pow(2,i); 
        }
        return result;

        /* using the BitSet bs, you will extract 18 bits based on the 
        get() method of BitSet.  The 18 bits are extracted at the first 
        18 prime numbers 2, 7,17,29,41,53,67,79,97,107,127,139,157, 
        173,191, 199,227,239.  Put the bits together in that order to 
        form an integer and return it.  That will be the hash value of 
        */
    }
}
