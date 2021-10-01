package Lab_3;
import java.util.ArrayList;
import java.util.List;

/** Logic class to perform rainfall calculations */
public class Logic {
    public static RainfallStats calculateStats(final List<Double> values) {
        double sum = 0;
        int validListSize = 0;

        // return null if list is empty
        if (values.size() == 0) {
            System.out.println("ERROR: no entries added");
            return null;
        }

        // calculate cumulative rainfall and size of valid entries
        for (final double current: values) {
            if (current >= 0) {
                sum += current;
                validListSize++;
            }
        }

        // return null if list size is zero. This means that there were only invalid inputs
        if (validListSize == 0) {
            System.out.println("ERROR: no valid entries to compute");
            return null;
        }

        // compute average
        final double average = sum / validListSize;
        int countRainy = 0, countDry =0;

        // compute rainy days and dry days
        for (final double current: values) {
            if (current >= 0) {
                countRainy = (current > average) ? countRainy + 1 : countRainy; // ++ rainy count if current above avg
                countDry = (current <= average * 0.05) ? countDry + 1 : countDry; // ++ dry count if current <= 5% of avg
            }
        }
        return new RainfallStats(average, countRainy, countDry, validListSize, sum);
    }
}
