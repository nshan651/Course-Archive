package pset2;
/* Node class used for linkage */
public class Node<E>{
    E info;
    Node<E> prev;
    Node<E> next;

    public Node() {
        info = null;
        prev = null;
        next = null;
    }

    // Accessors
    public Node<E> getNext() { return next;}
    public Node<E> getPrev() { return prev; }
    public E getInfo() { return info; }
    
    // Mutators
    public void setNext(Node<E> n) { next = n; }
    public void setPrev(Node<E> p) { prev = p; }
    public void setInfo(E val) { info = val; }
    
}
