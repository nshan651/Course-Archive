package Lab_7;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestTeam {

    Team makeTeamFixture(final String name, final String headcoach, final int funding) {
        return new Team(name, headcoach, funding);
    }

    /** Validate team name */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorValidName() {
        new Team(null, "Klinsmann", 500);
    }

    /** Validate HC */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorValidHeadCoach() {
        new Team("USA", null, 500);
    }
    /** Validate funding */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorValidFunding() {
        new Team("USA", "Klinsmann", -1);
    }

    @Test
    public void testGetName() {
        final String name = "USA";
        final Team t = makeTeamFixture(name, "Klinsmann", 500);
        assertEquals(name, t.getName());
    }

    @Test
    public void testGetHeadCoach() {
        final String coach = "Klinsmann";
        final Team t = makeTeamFixture("USA", coach, 500);
        assertEquals(coach, t.getHeadcoach());
    }

    @Test
    public void testGetFunding() {
        final int funding = 500;
        final Team t = makeTeamFixture("USA", "Klinsmann", funding);
        assertEquals(funding, t.getFunding());
    }

}
