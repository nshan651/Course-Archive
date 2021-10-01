package Lab_7;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestSearch {

    /** Array Fixture */
    Team[] makeArrayFixture(final int size) {
        final var array = new Team[size];
        for (var i = 0; i < size; i++) {
            final var s = Integer.toString(i);
            array[i] = new Team("Team " + s, "Coach " + s, i * 100 + 50);
        }
        return array;
    }

    /** List fixture */
    List<Team> makeListFixture(final int size) {
        final List<Team> list = new ArrayList<>();
        for (var i = 0; i < size; i++) {
            final var s = Integer.toString(i);
            list.add(i, new Team("Team " + s, "Coach " + s, i * 100 + 50));
        }
        return list;
    }
    // TODO makeListFixture

    @Test
    public void testFindPositionArray0() {
        final var arr = makeArrayFixture(0);
        assertFalse(Search.findTeamPosition(arr, "Team 5").isPresent());
    }

    @Test
    public void testFindPositionArray10s() {
        final var arr = makeArrayFixture(10);
        assertTrue(Search.findTeamPosition(arr, "Team 5").isPresent());
    }

    @Test
    public void testFindPositionArray10f() {
        final var arr = makeArrayFixture(10);
        assertFalse(Search.findTeamPosition(arr, "Team 11").isPresent());
    }

    /** Test empty list */
    @Test
    public void testFindPositionList0() {
        final var arr = makeListFixture(0);
        assertFalse(Search.findTeamPosition(arr, "Team 5").isPresent());
    }

    /** Test List size 10 success */
    @Test
    public void testFindPositionList10s() {
        final var list = makeListFixture(10);
        assertTrue(Search.findTeamPosition(list, "Team 5").isPresent());
    }

    /** Test list size 10 failure */
    @Test
    public void testFindPositionList10f() {
        final var list = makeListFixture(10);
        assertFalse(Search.findTeamPosition(list, "Team 11").isPresent());
    }

    /** Test min funding with empty fixture */
    @Test
    public void testFindMinFundingArray0() {
        final var arr = makeArrayFixture(0);
        assertFalse(Search.findTeamMinFunding(arr, 0).isPresent());
    }

    /** Test min funding success*/
    @Test
    public void testFindMinFundingArray10s() {
        final var arr = makeArrayFixture(10);
        assertTrue(Search.findTeamMinFunding(arr, 50).isPresent());
    }

    /** Test min funding failure */
    @Test
    public void testFindMinFundingArray10f() {
        final var arr = makeArrayFixture(10);
        assertFalse(Search.findTeamMinFunding(arr, 1000).isPresent());
    }

    /** Test minFundingFast with empty fixture */
    @Test
    public void testFindMinFundingArrayFast0() {
        final var arr = makeArrayFixture(0);
        assertFalse(Search.findTeamMinFundingFast(arr, 0).isPresent());
    }
    /** Test minFundingFast success*/
    @Test
    public void testFindMinFundingArrayFast10s() {
        final var arr = makeArrayFixture(10);
        assertTrue(Search.findTeamMinFundingFast(arr, 50).isPresent());
    }

    /** Test minFundingFast failure */
    @Test
    public void testFindMinFundingArrayFast10f() {
        final var arr = makeArrayFixture(10);
        assertFalse(Search.findTeamMinFundingFast(arr, 1000).isPresent());
    }
}
