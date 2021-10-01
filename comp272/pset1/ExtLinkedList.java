package pset1;

import java.util.LinkedList;

public class ExtLinkedList<E> extends LinkedList<E>{

    /* Default constructor */
    public ExtLinkedList() { super(); }

    public ExtLinkedList<E> secondHalfList() {
        // Initialize second half of the list
        ExtLinkedList<E> secondHalf = new ExtLinkedList<>();
        // Return null if list is empty; first item if only one exists
        if (this.size()==0) return null;
        if (this.size()==1) {
            secondHalf.add(this.get(0));
            return secondHalf;
        } 

        // Set middle based on whether size is even or odd
        // if size odd, truncate the middle
        int middle = (this.size()%2==0) ? this.size()/2 : (this.size()/2)+1;
        
        // Loop from middle to second half
        for (int i = middle; i < this.size(); i++) 
            secondHalf.add(this.get(i));
        
        return secondHalf;
    }

    public ExtLinkedList<E> oddList() {
        ExtLinkedList<E> oddList = new ExtLinkedList<>();
        // Return an empty list if size is 0 or 1
        // 0 meaning list is empty, 1 because position 0 is even
        if (this.size()==0 || this.size()==1) return oddList;

        // Iterate through odd index values
        for (int i = 1; i < this.size(); i+=2) 
            oddList.add(this.get(i));
        
        return oddList;
    }

    public ExtLinkedList<E> evenList() {
        ExtLinkedList<E> evenList = new ExtLinkedList<>();
        // Return an empty list if size is 0 or 1
        // 0 meaning list is empty, 1 because position 0 is even
        if (this.size()==0) return evenList;

        // Iterate through odd index values
        for (int i = 0; i < this.size(); i+=2) 
            evenList.add(this.get(i));
        
        return evenList;
    }



}

