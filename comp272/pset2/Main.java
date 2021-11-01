package pset2;
import java.util.LinkedList;
import java.math.*;

public class Main {
    public static void main(String[] args) {
        ExtLinkedList<Integer> ext = new ExtLinkedList<>();
        // 5, 10, 15, 20, 25
        ext.add(5);
        ext.add(10);
        ext.add(15);
        ext.add(20);
        ext.add(25);

        //System.out.println(ext.isEmpty());
        var sec = ext.secondHalfList();
        var odd = ext.oddList();
        var even = ext.evenList();

        //System.out.println("Second half of list: " + sec);
        //System.out.println("Odd index values: " + odd);
        //System.out.println("Even index values: " + even);
        
        String str = "kalifornia";
        String str2 = replaceChar(str, 0, 'c');
        System.out.print(str + "? More like " + str2 );
        System.out.println();
        
        /* Problem 8
        LinkedList<String> str1 = new LinkedList<>();
        LinkedList<String> str2 = new LinkedList<>();
        str1.add("Ardvark");
        str1.add("Cheetah");
        str1.add("Zebra");

        str2.add("Bryan");
        str2.add("Chad");
        str2.add("Vincent");
        str2.add("Xavier");

        LinkedList<String> combined = combine(str1, str2);
        System.out.println(combined);
        */

        /* Problem 5 
        int[][] arr1 = { { 3,2,5 }, { 1,0,4 }, {5,6,7} };
        rowSums(arr1);
        System.out.println();
        columnMins(arr1);
        System.out.println();
        */
        /* Problem 6 
        LinkedList<Integer> prefix= new LinkedList<>();
        prefix.add(5);
        prefix.add(3);
        prefix.add(2);
        prefix.add(9);
        prefix.add(3);
        prefix.add(15);
        prefix.add(22);
        //prefixSums(prefix);
        reversePrefix(prefix);
        */

        /* Problem 9 
        int[] arr1 = {1,4,9,12,6,15,5,13,17};
        int k = 4;
        uniquePairs(arr1, k);
        */
    }

    /* Question 3*/
    public static String replaceChar(String p, int k, char c) {
        // Throw an exception if argument is invalid
        if (k < 0 || k > p.length() || p.length()==0) 
            throw new IllegalArgumentException("k value out of range or p is empty");
        // Concatenate substring up to k, char c, and substring after k+1
        return p.substring(0, k) + c + p.substring(k+1, p.length());
    }
    /*
    public static String replaceChar(String p, int k, char c) {
        if(k>p.length()-1||k<0)
            throw new IllegalArgumentException("k value larger than string or is a negative/nonexistent position");
        String returnThis = p.substring(0,k) + c + p.substring(k+1);
        return returnThis;
    }
    */

    /* Question 5 */
    public static void rowSums(int[][] arr) {
        for (int i =0; i < arr.length; i++) {
            int sum =0;
            for (int j =0; j < arr[i].length-1; j++) 
                sum+=arr[i][j];
            if (i < arr.length-1)
                System.out.print(sum + ",");
            else
                System.out.print(sum);
        }
    }
    public static void columnMins(int[][] arr) {
        for (int j =0; j < arr[0].length; j++) {
            int min =arr[0][j];
            for (int i =0; i < arr.length; i++) {
                if (arr[i][j] < min)
                    min = arr[i][j];
            }
            if (j < arr[0].length-1)
                System.out.print(min + ",");
            else
                System.out.print(min);
        }
    }

    /* Question 6 */
    public static void prefixSums(LinkedList<Integer> n) {
        
        int curr =n.get(0), prev =n.get(0);
        // Base
        System.out.print(curr + ",");
        // Loop forward through the list
        for (int i =1; i < n.size(); i++) {
            curr = n.get(i);
            prev+=curr;
            if (i < n.size()-1)
                System.out.print(prev + ",");
            else
                System.out.print(prev); 
                // Print this at the end so 
                // there is no trailing comma
        }
    }

    /* Question 7 */
    public static void reversePrefix(LinkedList<Integer> n) {
        // Same thing as question 6 but in reverse order
        // starting at the end of the list
        int curr =n.get(n.size()-1), next =n.get(n.size()-1);
        System.out.print(curr + ",");
        for (int i =n.size()-2; i >=0; i--) {
            curr = n.get(i);
            next+=curr;
            if (i > 0)
                System.out.print(next + ",");
            else
                System.out.print(next);
        }
    }
    /* Question 8 */
    public static LinkedList<String> combine(LinkedList<String> a, LinkedList<String> b) {
        LinkedList<String> combine = new LinkedList<String>();
        
        int i =0, j =0;
        while (i < a.size() && j < b.size()) {
            // Lexicographic comparison
            int compare = a.get(i).compareTo(b.get(j));
            if (compare < 0) {
                combine.add(a.get(i));
                i++;
            }
            else {
                combine.add(b.get(j));
                j++;
            }   
        }
        // Add remaining strings 
        while (i < a.size()) {
            combine.add(a.get(i));
            i++;
        }
        while (j < b.size()) {
            combine.add(b.get(j));
            j++;
        }   
        return combine;
    }

    /* Question 9 */
    public static void uniquePairs(int[] arr, int k) {
        for (int i =0; i < arr.length; i++) {
            for (int j =i; j < arr.length; j++) {
                if(Math.abs(arr[i]-arr[j])==k)
                    System.out.print("("+arr[i]+","+arr[j]+")");
            }
        }
    }
 
        
}
