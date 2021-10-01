package Lab_5;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(final String[] args) throws FileNotFoundException {

        /* create scanner for data input */
        final var input = new Scanner(new File("src/Lab_5/maze2.txt"));
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

        /* Create maze and attempt to solve */
        final var maze = new Maze(rawData);
        final var result = maze.solve(startRow, startCol);
        maze.print(System.out);
        System.out.println();
        System.out.println(result ? "We're so out of here!" : "Bummer, we're stuck...");
    }
}
