package Lab_4;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class TestWordCounter {

    // TODO complete this test class
    // TODO declare a reference to the SUT (system under test), i.e., WordCounter
    Map<String, Integer> theMap = new HashMap<>();
    WordCounter wordCounter = new WordCounter(theMap);

    /** Create the test class*/
    @Before
    public void setUp() {
        wordCounter.countWords(Arrays.asList("asdf", "oiu", "qwer", "asdf", "oiu", "oiu", "qwer", "qwer", "oiu", "asdf", "asdf", "xzc").iterator());
        // TODO create the SUT instance
    }

    /** Reset the test case*/
    @After
    public void tearDown() {
        wordCounter = null;
        theMap = null;
        assertNull(theMap);
        assertNull(wordCounter);
        // TODO set the SUT instance to null
    }

    /** Test to verify that the SUT initially returns an empty map */
    @Test
    public void testGetCountEmpty() {
        /* Reset the HashMap and WordCounter Objects from the setUp*/
        theMap = new HashMap<>();
        wordCounter = new WordCounter(theMap);

        /* Use the getCounts method to retrieve the map of all word frequencies */
        Map <String, Integer> testMap = wordCounter.getCounts();

        /* boolean test to check if the map is empty {} */
        assertTrue(testMap.isEmpty());

    }

    /** Test for words that are part of the test set as well as words that do not appear*/
    @Test
    public void testGetCountNonEmpty() {
        final int count1 = wordCounter.getCount("asdf");
        final int count2 = wordCounter.getCount("oiu");
        final int count3 = wordCounter.getCount("qwer");
        final int count4 = wordCounter.getCount("xzc");
        final int count5 = wordCounter.getCount("and");
        final int count6 = wordCounter.getCount("or");
        assertEquals(4,count1);
        assertEquals(4,count2);
        assertEquals(3,count3);
        assertEquals(1,count4);
        assertEquals(0,count5);
        assertEquals(0,count6);
        // TODO run the SUT on a specific String iterator with some repeated words,
        // then use assertions to verify the correct counts
        // do this for at least two words in the iterator and two not in the iterator
    }
}
