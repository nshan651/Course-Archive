import java.util.*;

public class MaxHeap<E extends Comparable<E>> extends ArrayList<E>   {
    
    private ArrayList<E> heap;

    /*construct an empty Heap using ArrayList
      with root at index 0.
     don't use binary tree for implementing the heap.
    */
    public MaxHeap(){
        this.heap = new ArrayList<>();    
    }

    // returns max value
    public E findMax() throws NoSuchElementException {
        if (heap==null)
            throw new NullPointerException();
        if (heap.size()==0) 
            throw new NoSuchElementException();
        return heap.get(0);
    }

    // adds a new value to the heap at the end of the Heap and 
    // adjusts values up to the root to ensure Max heap property is satisfied.
    // parent of node at i is given by the formula (i-1)/2
    // throw appropriate exception
    public void addHeap(E val) {
        if (val == null)
            throw new NullPointerException();
        heap.add(val);
        // Index of last non-leaf node    
        int nleaf = (heap.size()/2)-1;
        // Iterate up the tree
        for (int i=nleaf; i >= 0; i--)
            heapify(i, heap.size());
    }

    /* Helper method to adjust values up to the root to ensure max heap */
    private void heapify(int i, int len) {
        int max = i; // Initialize max as root
        int left = (2*i) + 1 ; // left = 2*i + 1
        int right = (2*i) + 2; // right = 2*i + 2
  
        // If left child is larger than root
        if (left < len && heap.get(left).compareTo(heap.get(max)) > 0 )
            max = left;
  
        // If right child is larger than max so far
        if (right < len && heap.get(right).compareTo(heap.get(max)) > 0)
            max = right;
  
        // If max is not root
        if (max != i) {
            E swap = heap.get(i);
            heap.set(i, heap.get(max));
            heap.set(max, swap);
  
            // Recursively heapify the affected sub-tree
            heapify(max, len);
        }
    }
    
    //returns the max value at the root of the heap by swapping the last value 
    // and percolating the value down from the root to preserve max heap property
    // children of node at i are given by the formula 2i+1,2i+2, to not exceed the
    // bounds of the Heap index, namely, 0 ... size()-1.
    // throw appropriate exception
    public E removeHeap() {
        if (heap==null)
            throw new NullPointerException();
        else if (heap.size()==0) 
            throw new NoSuchElementException();
        else if (heap.size() == 1)
            return heap.set(0, null);
        
        // Old root
        E root = heap.get(0);
        // Set root = last element
        heap.set(0, heap.get(heap.size()-1));
        // remove last element
        heap.remove(heap.size()-1);

        heapify(0, heap.size());

        return root;
    }
    
    // takes a list of items E and builds the heap and then prints 
    // decreasing values of E with calls to removeHeap().  
    public void heapSort(List<E> list){
        if (list == null)
            throw new NullPointerException();
        else if (list.size() == 0)
            throw new NoSuchElementException();
        // Build heap
        this.buildHeap(list);
        // Sift down the tree and remove the roots
        for (int i =heap.size()-1; i >= 0; i--) {
            E root = this.removeHeap();
            System.out.print(root + " ");
        }
    }
    
    // merges the other maxheap with this maxheap to produce a new maxHeap.  
    public void heapMerge(MaxHeap<E> other) {
        for (int i =0; i < other.heap.size(); i++) 
            this.addHeap(other.heap.get(i));
    }
    
    //takes a list of items E and builds the heap by calls to addHeap(..)
    public void buildHeap(List<E> list) {
        if (list == null)
            throw new NullPointerException();
        else if (list.size() == 0)
            throw new NoSuchElementException();
        for (int i =0; i < list.size(); i++) 
            this.addHeap(list.get(i));
    }

    public void printHeap() {
        for (int i =0; i < heap.size()-1; i++) 
            System.out.print(heap.get(i) + ",");
        System.out.print(heap.get(heap.size()-1));
    }

    public static void main(String[] args) {
        MaxHeap<Integer> mheap1 = new MaxHeap<>();
        MaxHeap<Integer> mheap2 = new MaxHeap<>();
        // Test lists
        int[] a = {1,3,5,4,6,13,10,9,8,15,17};
        int[] b = {4,10,3,5,1};
        int[] c = {3,10,4,5,6,7,8,9,5};
        ArrayList<Integer> ls = new ArrayList<>(Arrays.asList(1,3,5,4,6,13,10,9,8,15,17));
        ArrayList<Integer> m1 = new ArrayList<>(Arrays.asList(1,3,5,4,6,13));
        ArrayList<Integer> m2 = new ArrayList<>(Arrays.asList(10,9,8,15,17));

        /* Merge test */
        mheap1.buildHeap(m1);
        mheap2.buildHeap(m2);
        mheap1.printHeap();
        System.out.println();
        mheap1.heapMerge(mheap2);
        mheap1.printHeap();

        /* Max test 
        mheap1.buildHeap(ls);
        System.out.println("Max is " + mheap1.findMax());
        */

        /* Remove test 
        mheap1.buildHeap(ls);
        System.out.println();
        System.out.print("root is " + mheap1.removeHeap());
        System.out.println("\nnew heap");
        mheap1.printHeap();
        */

        /* Sort test 
        System.out.println("Before sorting");
        mheap.printHeap();
        System.out.println("\nAfter sorting");
        mheap1.heapSort(ls);
        //System.out.println("\nAfter sorting");
        //mheap1.printHeap();
        */

        //mheap2.printHeap();
        //mheap1.printHeap();
        //System.out.println();
        
        
    }


    
}
