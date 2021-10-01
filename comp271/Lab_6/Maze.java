package Lab_6;

import java.io.PrintStream;
import java.util.List;

public class Maze {

    public static final char WALL = '*', EMPTY = '.', VISITED = '+', DEADEND = '-', START = '0';

    /** Representation of maze as a mutable char array. */
    private final char[][] mazeData;

    /**
     * Constructs a maze instance from a nonempty list of same-length strings. Each string represents
     * a row in the maze.
     *
     * @param rawData a nonempty list of same-length strings
     * @throws IllegalArgumentException if rawData is empty or some strings are longer than others
     */
    public Maze(final List<String> rawData) {
        this(rawData.toArray(new String[0]));
    }

    /**
     * Constructs a maze instance from a nonempty array of same-length strings. Each string represents
     * a row in the maze.
     *
     * @param rawData a nonempty array of same-length strings
     * @throws IllegalArgumentException if rawData is empty or some strings are longer than others
     */
    public Maze(final String[] rawData) {
        /* Make sure raw data is not empty */
        assert(rawData != null);
        this.mazeData = new char[rawData.length][];
        if (!(rawData.length == 0)) {
            final int width = rawData[0].length();
            for (int i = 0; i < rawData.length; i++) {
                /* check for consistent width */
                if (width == rawData[i].length()) {
                    this.mazeData[i] = rawData[i].toCharArray();
                } else {
                    throw new IllegalArgumentException("Width is not consistent");
                }
            }
        } else {
            throw new IllegalArgumentException("Empty maze");
        }
    }

    /**
     * Prints the maze to the given destination.
     *
     * @param output the output destination
     */
    public void print(final PrintStream output) {
        for (int i = 0; i < mazeData.length; i++) {
            for (int j = 0; j < mazeData[i].length; j++) {
                System.out.print(mazeData[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * Attempts to find a way out of this maze from the given starting position.
     *
     * @param row the row (y) index of the starting position
     * @param col the row (x) index of the starting position
     * @return whether or not there is a way out
     * @throws IllegalArgumentException if the starting position is outside the maze
     */
    public boolean solve(final int row, final int col) {
        /* validate arguments */
        final int width = mazeData[0].length;
        final int height = mazeData.length;
        /* Ensure starting position is within maze area */
        if (row >= height || col >= width || row < 0 || col < 0) {
            System.out.println("ERROR: starting position outside of maze area");
            return false;
        }
        else if (mazeData[row][col] == WALL) {
            System.out.println("ERROR: starting position is on a wall");
            mazeData[row][col] = START;
            return false;
        }
        /* invoke recursive method starting at row and col */
        final var solution = solve1(row, col);
        /* mark starting position and return result*/
        mazeData[row][col] = START;
        return solution;
    }

    /** Looks for a way out of the maze from the current position using recursive backtracking. */
    protected boolean solve1(final int row, final int col) {
        /* if we're not allowed to be here, then this is not a way out */
        if (mazeData[row][col] == WALL || mazeData[row][col] == VISITED) {
           return false;
        }
        else {
            /* otherwise mark this place as visited (drop a breadcrumb) */
            mazeData[row][col] = VISITED;
            /* if we're already on the perimeter, then this is a way out */
            if (row >= mazeData.length-1 || row == 0 || col == mazeData[row].length-1
                    || col == 0) {
                System.out.println("Escaped!");
                return true;
            }
        }
        /* Check four cardinal directions */
        boolean result = (
                solve1(row-1, col) ||   // up
                solve1(row, col+1)  ||  // right
                solve1(row+1, col) ||  // down
                solve1(row, col-1)     // left
        );
        /* If no cardinal directions are valid, mark as dead end */
        if (!result) {
            mazeData[row][col] = DEADEND;
        }
        return result;
    }

    /**
     * Returns the char at the given row and column. Mainly for testing.
     *
     * @param row the row
     * @param col the column
     * @return the char at the given row and column
     * @throws IndexOutOfBoundsException if the row or column are outside the valid range
     */
    public char get(final int row, final int col) { return mazeData[row][col]; }
}
