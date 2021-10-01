package Lab_5;

import java.io.PrintStream;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Maze {

    public static final char WALL = '*', EMPTY = '.', VISITED = '+',
            DEADEND = '-', START = '0';

    /** Representation of maze as a mutable char array. */
    private char[][] mazeData; // = new char[MAX_WIDTH][]; // <- initialize this!

    /**
     * Constructs a maze instance from a nonempty list of same-length strings. Each string represents
     * a row in the maze.
     * we want the element to be the same type as the argument
     * so we must pass in a "template" of new String[0]
     * @param rawData a nonempty list of same-length strings
     * @throws IllegalArgumentException if rawData is empty or some strings are longer than others
     */
    public Maze(final List<String> rawData) { this(rawData.toArray(new String[0])); }

    /**
     * Constructs a maze instance from a nonempty array of same-length strings. Each string represents
     * a row in the maze.
     *
     * @param rawData a nonempty array of same-length strings
     * @throws IllegalArgumentException if rawData is empty or some strings are longer than others
     */
    public Maze(final String[] rawData) throws IllegalArgumentException{
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
    public boolean solve(final int startRow, final int startCol) {
        final int width = mazeData[0].length;
        final int height = mazeData.length;
        /* Ensure starting position is within maze area */
        if (startRow >= height || startCol >= width) {
            System.out.println("ERROR: starting position outside of maze area");
            return false;
        }
        else if (mazeData[startRow][startCol] == WALL) {
            System.out.println("ERROR: starting position is on a wall");
            mazeData[startRow][startCol] = START;
            return false;
        }

        /* Local class for row/col positions as well as overridden toString method*/
        class Position {
            final private int row, col;
            public Position(final int row, final int col) {
                this.row = row;
                this.col = col;
            }
            @Override
            public String toString() {
                return row + ", " + col;
            }
        }

        /* Define the stack (work queue) */
        final var queue = Collections.asLifoQueue(new LinkedList<Position>());
        /* Define traditional FIFO queue */
        //Queue<Position> queue2 = new LinkedList<>();

        /* Put starting position in queue */
        //queue.add(new Position(startRow, startCol));
        queue.add(new Position(startRow, startCol));
        /* look for a way out of the maze from the current position */
        while (!queue.isEmpty()) {
            /*
            * TODO: Deadend logic:
            * If there is a wall on three sides and a visited position on the other
            * Then mark this and backtracked positions with DEADEND
            * */
            final var pos = queue.remove();

            /* if this is a wall, then ignore and go to next iteration (NOT VALID SPACE) */
            if (mazeData[pos.row][pos.col] == WALL) {
                continue;
            }

            /* if we've already visited this position, do not visit again */
            if ( (mazeData[pos.row][pos.col] == VISITED) || (mazeData[pos.row][pos.col] == START)) {
                continue;
            }
            /* otherwise mark this place as visited (drop a breadcrumb) */
            else if (mazeData[pos.row][pos.col] != START) {
                mazeData[pos.row][pos.col] = VISITED;
            }

            /* if we're already on the perimeter, then this is a way out and we should return true right away */
            if (pos.row >= mazeData.length-1 || pos.row == 0 || pos.col == mazeData[pos.row].length-1
                || pos.col == 0) {
                System.out.println("Escaped!");
                mazeData[startRow][startCol] = START; // mark the start and return true
                return true;
            }

            /*
             * DEADEND if surrounded by three walls and a VISITED position
             * Backtrack from deadend if surrounded by two visited positions and two walls
             */
            final boolean deadEnd = (
                    /* Check lower */
                    (mazeData[pos.row+1][pos.col] == WALL && mazeData[pos.row-1][pos.col] == WALL &&
                            mazeData[pos.row][pos.col+1] == WALL && mazeData[pos.row][pos.col-1] == VISITED) ||
                            /* Check upper */
                            (mazeData[pos.row+1][pos.col] == WALL && mazeData[pos.row-1][pos.col] == WALL &&
                                    mazeData[pos.row][pos.col-1] == WALL && mazeData[pos.row][pos.col+1] == VISITED) ||
                            /* Check left */
                            (mazeData[pos.row+1][pos.col] == WALL && mazeData[pos.row][pos.col-1] == WALL &&
                                    mazeData[pos.row][pos.col+1] == WALL && mazeData[pos.row-1][pos.col] == VISITED) ||
                            /* Check right */
                            (mazeData[pos.row][pos.col-1] == WALL && mazeData[pos.row-1][pos.col] == WALL &&
                                    mazeData[pos.row][pos.col+1] == WALL && mazeData[pos.row+1][pos.col] == VISITED)
            );
            /* Mark dead end */
            if (deadEnd) { mazeData[pos.row][pos.col] = DEADEND; }
            /* if we've already visited this position, do not visit again */

            /* else try each of our four neighbors (cardinal directions) by adding them to the stack */
            queue.add(new Position(pos.row + 1, pos.col)); // right
            queue.add(new Position(pos.row - 1, pos.col)); // left
            queue.add(new Position(pos.row, pos.col + 1)); //  up
            queue.add(new Position(pos.row, pos.col - 1)); //  down

        }
        /*
         *if we've looked everywhere but haven't gotten out,
         *then return false and mark the start
         */
        System.out.println("No escape!");
        mazeData[startRow][startCol] = START;
        return false;
    }

    /**
     * Returns the char at the given row and column. Mainly for testing.
     *
     * @param row the row
     * @param col the column
     * @return the char at the given row and column
     * @throws IndexOutOfBoundsException if the row or column are outside the valid range
     */
    public char get(final int row, final int col) {
        return mazeData[row][col];
    }
}
