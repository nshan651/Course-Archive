package Lab_10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestList {

    private List<Integer> arrayList;
    private List<Integer> linked;

    @Before
    public void setUp() throws Exception {
        arrayList = new ArrayList<>();
        linked = new LinkedList<>();

        // also try with a LinkedList - does it make any difference?
    }

    @After
    public void tearDown() throws Exception {
        arrayList = null;
        linked = null;
    }

    @Test
    public void testSizeEmpty() {
        List<Integer> list = linked;

        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
        try {
            list.get(0);
            fail("there should not be any items in the list");
        } catch (Exception ex) {
        }
    }

    @Test
    public void testSizeNonEmpty() {
        List<Integer> list = linked;

        list.add(77);
        assertFalse(list.isEmpty());
        assertEquals(1, list.size());
        assertEquals(77, list.get(0).intValue());
    }

    @Test
    public void testContains() {
        List<Integer> list = linked;

        /*
           Assertions using list.contains(77)
           that hold before and after adding 77 to the list
        */
        list.add(33);
        assertFalse(list.contains(77));
        list.add(77);
        assertTrue(list.contains(77));
    }

    @Test
    public void testAddMultiple() {
        List<Integer> list = linked;

        list.add(77);
        list.add(77);
        list.add(77);

        assertEquals(3, list.size());
        assertEquals(0, list.indexOf(77)); // <- gets first occurrence of 77
        assertEquals(77, list.get(1).intValue());
        assertEquals(2, list.lastIndexOf(77));
    }

    @Test
    public void testAddMultiple2() {
        List<Integer> list = linked;

        list.add(33);
        list.add(77);
        list.add(44);
        list.add(77);
        list.add(55);
        list.add(77);
        list.add(66);

        assertEquals(7, list.size());
        assertEquals(1, list.indexOf(77)); // <- FIRST occurrence of 77
        assertEquals(5, list.lastIndexOf(77));
        assertEquals(44, list.get(2).intValue());
        assertEquals(77, list.get(3).intValue());
        assertEquals(Arrays.asList(33, 77, 44, 77, 55, 77, 66), list);
    }

    @Test
    public void testRemoveObject() {
        List<Integer> list = linked;

        list.add(3);
        list.add(77);
        list.add(4);
        list.add(77);
        list.add(5);
        list.add(77);
        list.add(6);
        list.remove(5); // what does this method do?

        assertEquals(6, list.size());
        assertEquals(1, list.indexOf(77));
        assertEquals(3, list.lastIndexOf(77));
        assertEquals(4, list.get(2).intValue());
        assertEquals(77, list.get(3).intValue());
        list.remove(Integer.valueOf(5)); // what does this one do?

        assertEquals(5, list.size());
        assertEquals(1, list.indexOf(77));
        assertEquals(3, list.lastIndexOf(77));
        assertEquals(4, list.get(2).intValue());
        assertEquals(77, list.get(3).intValue());
    }

    @Test
    public void testContainsAll() {
        List<Integer> list = linked;

        list.add(33);
        list.add(77);
        list.add(44);
        list.add(77);
        list.add(55);
        list.add(77);
        list.add(66);
        assertTrue(list.containsAll(Arrays.asList(33, 77, 44, 77, 55, 77, 66)));
        assertFalse(list.containsAll(Arrays.asList(11,22,33)));
        // TODO using containsAll and Arrays.asList (see above),
        // 1) assert that list contains all five different numbers added
        // 2) assert that list does not contain all of 11, 22, and 33
        //fail("Not yet implemented"); // remove this line when done
    }

    @Test
    public void testAddAll() {
        List<Integer> list = linked;

        /* In a single statement using addAll and Arrays.asList,
           add items to the list to make the following assertions pass
           (without touching the assertions themselves)
        */

        list.addAll(Arrays.asList(33,77,44,77,55,77,66));

        assertEquals(7, list.size());
        assertEquals(33, list.get(0).intValue());
        assertEquals(77, list.get(1).intValue());
        assertEquals(44, list.get(2).intValue());
        assertEquals(77, list.get(3).intValue());
        assertEquals(55, list.get(4).intValue());
        assertEquals(77, list.get(5).intValue());
        assertEquals(66, list.get(6).intValue());
    }

    @Test
    public void testRemoveAll() {
        List<Integer> list = linked;

        list.add(33);
        list.add(77);
        list.add(44);
        list.add(77);
        list.add(55);
        list.add(77);
        list.add(66);
        /*in a single statement using removeAll and Arrays.asList,
          remove items from the list to make the following assertions pass
         (without touching the assertions themselves)
         */
        list.removeAll(Arrays.asList(33,44,55,66));
        assertEquals(3, list.size());
        assertEquals(Arrays.asList(77, 77, 77), list);
    }

    @Test
    public void testRetainAll() {
        List<Integer> list = linked;

        list.add(33);
        list.add(77);
        list.add(44);
        list.add(77);
        list.add(55);
        list.add(77);
        list.add(66);
        /*in a single statement using retainAll and Arrays.asList,
         remove items from the list to make the following assertions pass
         (without touching the assertions themselves)
        */

        list.retainAll(Arrays.asList(77,77,77));
        assertEquals(3, list.size());
        assertEquals(Arrays.asList(77, 77, 77), list);
    }

    @Test
    public void testSet() {
        List<Integer> list = linked;

        list.add(33);
        list.add(77);
        list.add(44);
        list.add(77);
        list.add(55);
        list.add(77);
        list.add(66);
        /* Use the set method to change specific elements in the list
         such that the following assertions pass
         (without touching the assertions themselves)
        */
        list.set(1, 99);
        list.set(3, 99);
        list.set(5, 99);

        assertEquals(7, list.size());
        assertEquals(33, list.get(0).intValue());
        assertEquals(99, list.get(1).intValue());
        assertEquals(44, list.get(2).intValue());
        assertEquals(99, list.get(3).intValue());
        assertEquals(55, list.get(4).intValue());
        assertEquals(99, list.get(5).intValue());
        assertEquals(66, list.get(6).intValue());
    }

    @Test
    public void testSubList() {
        List<Integer> list = linked;

        list.add(33);
        list.add(77);
        list.add(44);
        list.add(77);
        list.add(55);
        list.add(77);
        list.add(66);

        assertEquals(Arrays.asList(44, 77, 55), list.subList(2, 5));
    }
}
