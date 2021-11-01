package pset3;
import java.util.*;

public class MyLinkedList<E>    {

    Node<E> first;
    Node<E> last;
    int size;
    
    public MyLinkedList() {
       first = null;
       last = null;
       size=0;
    }

    public Node<E> getFirst() { return this.first; }
    
    public Node<E> getLast() { return this.last; }

    
    @Override 
    public String toString() {
        Node<E> curr = this.first;
        if (curr == null) return "[ ]";
        String str = "[ ";
        while (curr != null) {
            str = str + curr.info + " ";
            curr = curr.next;
        }
        return str + "]";
    }

    public boolean isEmpty() { return (size==0); }
    
    public void addFirst(E info) {
        
        Node<E> n =new Node<>();
        n.setInfo(info);
        
        if (isEmpty())  
            first = n;
        else {
            n.setNext(first);
            first.setPrev(n);
        }
        first=n;
        size++;    
    }

    public E removeFirst() {
        
        if (!isEmpty()) {
            
            E val = first.getInfo();
            if (size>1) {
                first.getNext().setPrev(null);
                first=first.getNext();
                size--;
            }
            else if (size==1) {
                first=null;
                last=null;
                size--;          
            }
            return val;  
        }
        else 
            throw new NoSuchElementException();
    }
    
    public E removeLast() {
        
        if (!isEmpty()) {

            E val = last.getInfo();
            if (size>1) {
                last.getPrev().setNext(null);
                last=last.getPrev();
                size--;
            }
            else if (size==1) {
                first=null;
                last=null;
                size--;
            }
            return val;
        }
        else 
            throw new NoSuchElementException();
    }   
    
    //incomplete code below
    //handle empty list situation   
    public E remove(int k) {
        
        if (!isEmpty()) {
        Node<E> temp = first;
        
        if ((k>=0) && (k<size)) {
            if (k==0) return removeFirst();
            else if (k==size-1) return removeLast();
            else {
                // get to k
               // int i=0;
                for (int i=0;i<k;i++) 
                temp = temp.getNext();
                E val=temp.getInfo();
              temp.getPrev().setNext(temp.getNext());
              temp.getNext().setPrev(temp.getPrev());
              size--;
              return val;   
            }
        }
        else throw new IndexOutOfBoundsException(); }
            else {
                System.out.println("list empty ..");
                throw new NoSuchElementException();
        }
    }
    
    // adds an item to the end of the list with info field set to info
    public void addLast(E info) {
        
        Node<E> n =new Node<>();
        n.setInfo(info);
        
        if (isEmpty()) 
            first=n;
        else {
            last.setNext(n);
            n.setPrev(last);
        }
        last = n;
        size++;
        
    }
    
    // prints all items in the list from first to last taking care of situations when the list is empty
    // use exception handling
    public void printListForward() {
        Node<E> n = this.first;
        while (n != null) {
            System.out.print(n.info + " ");
            n = n.next;
        }  
    }
    
    // prints all items in the list from last to first taking care of situations when the list is em
    // use exception handling
    public void printListBackward() {
        Node<E> n = this.last;
        while (n != null) {
            System.out.print(n.info + " ");
            n = n.prev;
        }     
    }
    
    //returns true if and only if the list contains at least one element e such that 
    //Objects.equals(o,e)
    //return false if the list is empty
    public boolean contains(Object o) {
        Node<E> head = first;
        while (head != null) {
            if (head.info.equals(o)) 
                return true;
            head = head.next;
        }
        return false;
    }
    
    // Brings the current list back to an empty list
    public void clear() {
        Node<E> temp = new Node<>();
        while (this.first != null) {
            temp = this.first;
            this.first = this.first.next;
            temp = null;
        }
    }

    /* Returns info of val stored at node i; throws IndexOutOfBounds exception of i is out of bounds or the list is empty */
    public E get(int i) {
        if (i >= this.size || this.first == null)
            throw new IndexOutOfBoundsException("index " + i + " out of range or list is empty");
        int pos = 0;
        Node<E> curr = this.first;
        while (pos < i) {
            curr = curr.next;
            pos++;
        }
        E data = curr.getInfo();
        return data;
    }
    
    // compares this MyLinkedList with the parameter otherList 
    // and returns true if the linkedlists have identical values from beginning to end
    // same size and this.get(i).equals(otherList.get(i)) should be true for all i
    // lists can be empty in which case return true
    //should run in O(n) time where n is the size of each list.
    @Override
    public boolean equals(Object otherList) {
        // Check that otherList is of type MyLinkedList
        if (otherList instanceof MyLinkedList) {
            MyLinkedList other = (MyLinkedList) otherList;
            // If this and other are empty, return true
            if (this.size == 0 && other.size == 0) return true;
            // If they have different sizes, return false
            if(this.size != other.size) return false;
            int i = 0;
            // Else, compare node by node
            while(i < this.size){
                if(!(this.get(i).equals(other.get(i))))
                    return false;
                i++;
            }
            return true; 
        }
        // If not instanceof, return false
        else
            return false;  
    }       
    
}
    
    
