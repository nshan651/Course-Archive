package pset3;
public class MyBigInteger{

    MyLinkedList<Integer> bigI;
    
    public MyBigInteger () {
        bigI = new MyLinkedList<>();
    }
    
    /* takes a numerically valued String p and stores it one digit at a time in the linked list
       example, MyBigInteger("383023322") will store the integer 383023322 in the linked list 
       one digit per node. 
    */
     public MyBigInteger(String p) {
        bigI = new MyLinkedList<>(); 
        boolean leadZeros = true;
        int num;
        for (int i =0; i < p.length(); i++) {
            num = Character.getNumericValue(p.charAt(i));
            if (!leadZeros) 
                bigI.addLast(num);
            else if (num != 0 && bigI.size==0) {
                leadZeros=false;
                bigI.addLast(num);
            }
        }
    }

    @Override 
    public String toString() {
        Node<Integer> curr = bigI.last;
        if (curr == null) return "[ ]";
        String str = "[ ";
        while (curr != null) {
            str = str + curr.info + " ";
            curr = curr.prev;
        }
        return str + "]";
    }

    // add(..) adds this MyBigInteger to other MyBigInteger and returns the sum as a MyBigInteger
    // the original Big Integers don't change.
    public MyBigInteger add(MyBigInteger other) {
        MyBigInteger bigger = new MyBigInteger();
        // Starts from the right-most digit; goes right to left
        Node<Integer> a = bigI.last;
        Node<Integer> b = other.bigI.last;
        int carry = 0;
        int sum;
        while(a !=null || b != null) {
            // If a is empty, add b to sum and carry
            if(a==null)
                sum =(int)b.getInfo()+carry;
            // If b is empty, add a to sum and carry
            else if (b==null)
                sum = (int)a.getInfo()+carry;
            // If a and b exist, add them both to carry
            else 
                sum = (int) a.getInfo() + (int) b.getInfo() + carry;
            // Get carry at the end of the operation and add single-digit sum to list
            carry = sum/10;
            bigger.bigI.addLast(sum%10);

            // Advance to next node (if possible)
            if(a!=null) a=a.getPrev();
            if(b!=null) b=b.getPrev();
        }
        return bigger;
    
    }
    
    // returns true if and only if the two big integers are equal
    @Override
    public boolean equals(Object other) {
        return bigI.equals(((MyBigInteger)other).bigI);
    }

    // returns true if and only if this MyBigInteger is less than other MyBigInteger
    public boolean lessThan(MyBigInteger other) {
        Node<Integer> a = bigI.last;
        Node<Integer> b = other.bigI.last;
        int lenA = bigI.size;
        int lenB = other.bigI.size;

        // If sizeA has fewer digits, return true
        if (lenA < lenB) return true;
        // compare the strings
        while(a !=null && (bigI.size == other.bigI.size)) {
            if (a.getInfo() < b.getInfo()) return true;
            if (a.getInfo() > b.getInfo()) return false;
            // If digits are equal, keep searching
            a=a.getPrev();
            b=b.getPrev();
        }
        return false;
    }
    
}
