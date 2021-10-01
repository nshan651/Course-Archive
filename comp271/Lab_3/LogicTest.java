package Lab_3;
import org.junit.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.Assert.*;

/** Test class */
public class LogicTest {

    final static double DELTA = 0.000000001;


    @Test // test single positive entry
    public void logicTest1() {
        final List<Double> test = Collections.singletonList(3.4);
        final RainfallStats actual = Logic.calculateStats(test);
        assert actual != null;
        assertEquals(3.4, actual.average, DELTA);
        assertEquals(0, actual.numRainy);
        assertEquals(0, actual.numDry);
        assertEquals(1, actual.daysRained);
        assertEquals(3.4, actual.totalRainfall, DELTA);
    }

    @Test // test multiple entries
    public void logicTest2() {
        final List<Double> test = Arrays.asList(3.4, 3.6);
        final RainfallStats actual = Logic.calculateStats(test);
        assert actual != null;
        assertEquals(3.5, actual.average, DELTA);
        assertEquals(1, actual.numRainy);
        assertEquals(0, actual.numDry);
        assertEquals(2, actual.daysRained);
        assertEquals(7.0, actual.totalRainfall, DELTA);
    }

    @Test // test negative
    public void logicTest3() {
        final List<Double> test = Arrays.asList(3.4, 3.6, -5.0, -20.0);
        final RainfallStats actual = Logic.calculateStats(test);
        assert actual != null;
        assertEquals(3.5, actual.average, DELTA);
        assertEquals(1, actual.numRainy);
        assertEquals(0, actual.numDry);
        assertEquals(2, actual.daysRained);
        assertEquals(7.0, actual.totalRainfall, DELTA);
    }

    @Test // test for zero arguments
    public void logicTest4() {
        final List<Double> test = Collections.emptyList();
        final RainfallStats actual = Logic.calculateStats(test);
        assertNull(actual);
    }

    @Test // test for no valid arguments
    public void logicTest5() {
        final List<Double> test = Arrays.asList(-5.0,-6.0,-7.0);
        final RainfallStats actual = Logic.calculateStats(test);
        assertNull(actual);
    }

}

