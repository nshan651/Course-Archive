package Lab_6;

import Lab_5.Maze;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.Assert.*;

public class MazeTest {

    @NotNull
    private static ArrayList<String> init_maze (String path) throws FileNotFoundException {
        /* create scanner for data input */
        final var input = new Scanner(new File(path));
        /* Create raw data ArrayList */
        final var rawData = new ArrayList<String>();
        /* Read starting point from file header */
        final var startRow = input.nextInt();
        final var startCol = input.nextInt();
        System.out.println("starting row: " + startRow + " starting col: " + startCol);

        /* Read line-by-line into raw data
         * Ignore the header, which gives the coordinates of the start
         */
        while (input.hasNext()) {
            final boolean header = input.nextLine().startsWith(String.valueOf(startCol));
            if (!header) {
                String str = input.next();
                rawData.add(str);
            }
        }
        return rawData;
    }
    @Test
    public void testMazeGeta() {
        final var data = new String[] {"***", "*..", "***"};
        final var sut = new Lab_6.Maze(data);
        assertEquals(Lab_6.Maze.EMPTY, sut.get(1, 1));
        assertEquals(Lab_6.Maze.WALL, sut.get(0, 0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testMazeGetb() {
        final var data = new String[] {"***", "*..", "***"};
        final var sut = new Lab_6.Maze(data);
        sut.get(3, 4);
    }

    @Test
    public void testMaze1x1a() {
        final var data = new String[] {"*"};
        final var sut = new Lab_6.Maze(data);
        assertFalse(sut.solve(0, 0));
    }

    @Test
    public void testMaze1x1b() {
        final var data = new String[] {"."};
        final var sut = new Lab_6.Maze(data);
        assertTrue(sut.solve(0, 0));
    }

    @Test
    public void testMaze3x3a() {
        final var data = new String[] {"***", "*.*", "***"};
        final var sut = new Lab_6.Maze(data);
        assertFalse(sut.solve(1, 1));
    }

    @Test
    public void testMaze3x3b() {
        final var data = new String[] {"***", "*..", "***"};
        final var sut = new Lab_6.Maze(data);
        assertTrue(sut.solve(1, 1));
        assertEquals(Lab_6.Maze.VISITED, sut.get(1, 2));
    }

    /** Test maze1 */
    @Test
    public void testMaze1() throws FileNotFoundException {
        final var data = init_maze("src/Lab_6/maze1.txt");
        final var sut = new Lab_6.Maze(data);
        assertTrue(sut.solve(4,4));
    }

    /** Test for when all exits are blocked in maze1 */
    @Test
    public void testMaze1Blocked() throws FileNotFoundException {
        final var data = init_maze("src/Lab_6/maze1_blocked.txt");
        final var sut = new Lab_6.Maze(data);
        assertFalse(sut.solve(4,4));
    }

    /** Test my own 20x15 maze */
    @Test
    public void testMaze2() throws FileNotFoundException {
        final var data = init_maze("src/Lab_6/maze2.txt");
        final var sut = new Lab_6.Maze(data);
        assertTrue(sut.solve(11,11));
    }

    /** Test my own 20x15 maze */
    @Test
    public void testMaze2Blocked() throws FileNotFoundException {
        final var data = init_maze("src/Lab_6/maze2_blocked.txt");
        final var sut = new Maze(data);
        assertFalse(sut.solve(11,11));
    }

    /** Test rectangle maze */
    @Test
    public void testMaze3() throws FileNotFoundException {
        final var data = init_maze("src/Lab_6/maze3.txt");
        final var sut = new Maze(data);
        assertTrue(sut.solve(1,1));
    }

    /** Test blocked rectangle maze */
    @Test
    public void testMaze3Blocked() throws FileNotFoundException {
        final var data = init_maze("src/Lab_6/maze3_blocked.txt");
        final var sut = new Maze(data);
        assertFalse(sut.solve(1,1));
    }

}
