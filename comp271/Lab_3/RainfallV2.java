package Lab_3;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/** Driver program */
public class RainfallV2 {
    public static void main(final String[] args) {
        // read data
        final Scanner console = new Scanner(System.in);
        final List<Double> rainfalls = new ArrayList<>(); // list to store days' temperatures
        while (console.hasNextDouble()) {    // read/store each day's temperature
            final double value = console.nextDouble();
            rainfalls.add(value);
            final int pos = rainfalls.size() - 1;
            System.out.println("rainfalls[" + pos + "] = " + value);
        }

        // calculate results
        final RainfallStats result = Logic.calculateStats(rainfalls);

        // report results
        if (result != null) {
            System.out.printf("Average rainfall = %.1f inches\n", result.average);
            System.out.println(result.numRainy + " rainy days");
            System.out.println(result.numDry + " dry days");
            System.out.println(result.totalRainfall + " inches of rainfall accumulated over " + result.daysRained + " days");
        }
    }
}

