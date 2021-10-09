public class BinarySearchTree<E extends Comparable<E>> extends BinaryTree<E> {

    public BinarySearchTree() {
        super();
    }
    public BinarySearchTree(E val) {
        super(val);
    }
    
    // returns true if BST has val else false.
    public boolean contains (E val) {
        Node<E> n = this.root;
        while (n != null) {
            int dif = val.compareTo((E)n.getInfo());
            if (dif < 0) 
                n = n.getLeft();
            else if (dif > 0) 
                n = n.getRight();
            else 
                return true;
        }
        return false;
    }

    public void insert(E val) { 
        this.root = insertHelper(this.root, val);
        size++;       
    }

    private Node<E> insertHelper(Node<E> n, E val) {
        // Return new node if the tree is empty
        if (n == null) return new Node<E>(val);
        // Else, recurse down the tree
        int dif = val.compareTo((E)n.getInfo());
        if (dif < 0)
            n.left = insertHelper(n.getLeft(), val);
        else if (dif > 0)
            n.right = insertHelper(n.getRight(), val);
        // Return the root pointer
        return n;
    }

    // returns the minimum value stored in the tree
    public E findMin() {
        if (this.root == null)
            throw new NullPointerException("Tree is empty");
        Node<E> n = this.root;
        while (n.getLeft() != null) 
            n = n.getLeft();
        return n.getInfo();
    }

    // returns the maximum value stored in the tree
    public E findMax() {
        if (this.root == null)
            throw new NullPointerException("Tree is empty");
        Node<E> n = this.root;
        while (n.getRight() != null) 
            n = n.getRight();
        return n.getInfo();
    }
    
    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
            
        bst.insert(5);
        bst.insert(10);
        bst.insert(3);
        bst.insert(20);
        bst.insert(8);
        bst.insert(4);
        
        bst.inOrder(bst.root);
        System.out.println("\nMin is " + bst.findMin() + " Max is " + bst.findMax());
        System.out.println("bst size is " + bst.size);
    }
    
             
}