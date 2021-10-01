package Lab_10;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestIterator {

    private List<Integer> arrayList;
    private List<Integer> linked;
    // See the Java List Interface documentation to understand what all the List methods do ...

    @Before
    public void setUp() throws Exception {
        arrayList = new ArrayList<>();
        linked = new LinkedList<>();
        // TODO also try with a LinkedList - does it make any difference?

    }

    @After
    public void tearDown() throws Exception {
        arrayList = null;
        linked = null;
    }

    @Test
    public void testEmpty() {
        List<Integer> list = arrayList;

        final var i = list.iterator();
        assertFalse(i.hasNext());
    }

    @Test
    public void testNonempty() {
        List<Integer> list = arrayList;

        list.add(33);
        list.add(77);
        list.add(44);
        list.add(77);
        list.add(55);
        list.add(77);
        list.add(66);
        final var i = list.iterator();
        assertTrue(i.hasNext());
        assertEquals(33, i.next().intValue());
        // fixed the expected values in the assertions below
        assertTrue(i.hasNext());
        assertEquals(77, i.next().intValue());
        assertTrue(i.hasNext());
        assertEquals(44, i.next().intValue());
        assertTrue(i.hasNext());
        assertEquals(77, i.next().intValue());
        assertTrue(i.hasNext());
        assertEquals(55, i.next().intValue());
        assertTrue(i.hasNext());
        assertEquals(77, i.next().intValue());
        assertTrue(i.hasNext());
        assertEquals(66, i.next().intValue());
        assertFalse(i.hasNext());
    }

    @Test
    public void testRemove() {
        List<Integer> list = arrayList;

        list.add(33);
        list.add(77);
        list.add(44);
        list.add(77);
        list.add(55);
        list.add(77);
        list.add(66);
        final var i = list.iterator();

        while (i.hasNext()) {
            if (i.next() == 77) {
                i.remove();
                /* What happens if you use list.remove(Integer.valueOf(77))?
                   list.remove(Integer.valueOf(77)); // <-- ConcurrentModificationException; does not work!
                */
            }
        }
        //list.remove(Integer.valueOf(77));
        // Using assertEquals and Arrays.asList, express which values are left in the list
        assertEquals(Arrays.asList(33,44,55,66), list);
        // See TestList.java for examples of how to use Arrays.asList; also see the Java Arrays
        // class for more information
    }

    @Test
    public void testAverageValues() {
        List<Integer> list = arrayList;

        list.add(33);
        list.add(77);
        list.add(44);
        list.add(77);
        list.add(55);
        list.add(77);
        list.add(66);
        var sum = 0d;
        //var avg = 0d;
        var n = 0;
        /* Uses an iterator and a while loop to compute the average (mean) of the values*/
        final var i = list.iterator();
        while (i.hasNext()) {
            sum+=i.next();
            n++;
        }
        final var avg = sum/n;
        // (defined as the sum of the items divided by the number of items)
        // testNonempty shows how to use an iterator; use i.hasNext() in the while loop condition
        assertEquals(61.3, avg, 0.1);
        assertEquals(7, n);
    }
}
