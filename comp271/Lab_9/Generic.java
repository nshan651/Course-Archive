package Lab_9;

import java.util.*;

public class Generic {
    public static void main(String[] args) {

        /* Single-line approach */
        Node<String> node = new Node<>("hello", new Node<>("Nick", new Node<>("what", new Node<>("up"))));
        /* Multi-line approach*/
        Node<String> head = new Node<>("hello");
        Node<String> n1 = new Node<>("what", head); // Step 12-13: remove "what" and add it back in at the end
        Node<String> n2 = new Node<>("up", n1);
        Node<String> n3 = new Node<>("Nick", n2);
        head.next = n3;

        printNode(head);

        /* Iterative approach using Linked List */
        final List<String> myListLinked = new LinkedList<>(Arrays.asList("hello", "Nick", "what", "up"));
        final Iterator<String> i = myListLinked.iterator();
        System.out.println("\nPrinting iteratively using Linked List:");
        while (i.hasNext()) {
            System.out.println(i.next());
        }
        /* Iterative approach using ArrayList */
        /* while loop using i.hasNext() and i.next() */
        final List<String> myArrayList = new ArrayList<>(Arrays.asList("hello", "Nick", "what", "up"));
        final Iterator<String> j = myArrayList.iterator();
        System.out.println("\nPrinting iteratively using Array List:");
        while (j.hasNext()) {
            System.out.println(j.next());
        }

    }
    public static <E> void printNode(Node<E> curr) {
        while(curr!= null) {
            System.out.println(curr.data);
            curr = curr.next;
        }
    }

    public static <E> void printNodeCycle(Node<E> curr) {
        final Node<E> temp = curr;
        // Print head
        System.out.println(curr.data);
        curr = curr.next;
        // Print one cycle of remaining nodes
        while(curr!= null && curr!=temp) {
            System.out.println(curr.data);
            curr = curr.next;
        }
    }
}

class Node<E> {

    public E data;
    public Node<E> next;
    public Node(final E data, final Node<E> next) {
        if (data == null) throw new IllegalArgumentException("data is null");
            this.data = data;
            this.next = next;
    }
    public Node(final E data) { this(data, null); }

    public String toString() {
        return "Node@" + hashCode() + "(" + data +
                (next != null ? ", Node@" + next.hashCode() + ")" : ")");
    }
}
