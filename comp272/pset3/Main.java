package pset3;

public class Main {
    
    public static void main(String[] args) {

        
        /* MyBigInteger test */
        String str = "0000383023322";
        MyBigInteger bigI = new MyBigInteger("42069");
        MyBigInteger other = new MyBigInteger("42069");
        //System.out.println(bigI.equals(other));
        //var bigger = bigI.add(other);
        //System.out.println(bigI.lessThan(other));
        //System.out.println(bigger.toString());
        
        MyLinkedList<Integer> mlist = new MyLinkedList<>();
        MyLinkedList<Integer> test = new MyLinkedList<>();    

        
        mlist.addFirst(69);
        mlist.addFirst(35);
        mlist.addFirst(25);

        
        test.addFirst(69);
        test.addFirst(35);
        test.addFirst(25);
        
        
        //mlist.clear();

        //System.out.println(mlist.contains(70));
        //System.out.println(test.toString());
        System.out.println(mlist.equals(test));
        //mlist.printListForward();
        //System.out.println();
        //test.printListForward();
        //System.out.println();
        //mlist.printListBackward();

        //System.out.println(a + "," + b + "," + c);
        //System.out.println(mlist.get(1));
        
    }
}
