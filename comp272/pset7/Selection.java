package pset7;
import java.util.*;

public class Selection<E extends Comparable<E>> {
    int k;
    ArrayList<E> input;

    public Selection() {
        this.k = 1;
        this.input = null;
    }

    public Selection(int num, ArrayList<E> list) {
        if (num < 1 || num > list.size() || list==null) throw new IllegalArgumentException();
        this.k = num;
        this.input = list;
    }

    /** algorithm 1 methods -- implement 1B */
    public void algorithm1B() {
        long start, end;
        start = System.currentTimeMillis();

        ArrayList<E> arry = new ArrayList<>();

        // Add elements up to kth element
        for (int i =0; i < k; i++)
            arry.add(input.get(i));
        // Sort (descending)
        Collections.sort(arry, Collections.reverseOrder());
        // Go through remaining items
        for (int i =k; i < input.size(); i++) {
            System.out.println("step " + i);
            // if curr is greater than k, replace k with curr
            if (input.get(i).compareTo(arry.get(k-1)) > 0) {
                arry.set(k-1, input.get(i));
                // Adjust elements
                for (int j =k-1; j > 0; j--) {
                    if (arry.get(j).compareTo(arry.get(j-1)) < 0) break;
                    E swap = arry.get(j);
                    arry.set(j, arry.get(j-1));
                    arry.set(j-1, swap);
                }
            }
        }
        end = System.currentTimeMillis();
        System.out.println(k + "kth largest is " + arry.get(k-1));
        System.out.println("Found in " + (end-start) + "ms");
    }

    /**  algorithm 2 methods -- 6A -- change the algorithm to do kth largest 
    not kth smallest that is described here
    */ 
    public void algorithm6A() {
        long start, end;
        start = System.currentTimeMillis();

        // Build minHeap
        PriorityQueue<E> minHeap = new PriorityQueue<>();
        minHeap.addAll(input);
        // Poll elements until kth element is reached
        for (int i =0; i < (k-1); i++) 
            minHeap.poll();
        
        end = System.currentTimeMillis();
        System.out.println(k + " kth smallest element is " + minHeap.peek());
        System.out.println("Found in " + (end-start) + "ms");
    }

    /** algorithm 3 methods – 6B */
    public void algorithm6B() {
        long start, end;
        start = System.currentTimeMillis();

        // Build minHeap up to kth element
        PriorityQueue<E> minHeap = new PriorityQueue<>();
        for (int i =0; i < k; i++)
            minHeap.add(input.get(i));
        // Go through remaining elements of input
        for (int i =k; i < input.size(); i++) {
            // if curr is greater than k, pop min and add input at i
            if (input.get(i).compareTo(minHeap.peek()) > 0) {
                minHeap.poll();
                minHeap.add(input.get(i));
            }
        }
        end = System.currentTimeMillis();
        System.out.println(k + " kth largest element is " + minHeap.peek());
        System.out.println("Found in " + (end-start) + "ms");
    }

    public static void benchmark() {
        ArrayList<Integer> longList = new ArrayList<>(10000000);
        int k=100000;
        for(int i =0;i<1000000;i++){
            longList.add((int) Math.floor(Math.random() * (1000)));
        }
        
        Selection<Integer> select = new Selection<>(k, longList);
        //select.algorithm1B();
        select.algorithm6A();
        select.algorithm6B();

    }

    public static void main(String[] args) {
        /*
        ArrayList<Integer> test = new ArrayList<>(Arrays.asList(5,4,1,3,45,69,72,44));
        int k = 2;
        Selection<Integer> select = new Selection<>(k,test);

        select.algorithm1B();
        select.algorithm6A();
        select.algorithm6B();
        */

        benchmark();
    }

}