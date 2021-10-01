// Nick Shannon; 1-25-2021
// Average Rainfall.Rainfall Calculator
package Lab_0;
import java.util.Scanner;

public class Rainfall {
    public static void main(String[] args) {
        double totalRain =0, avg;
        int numOfEntries =0;

        final Scanner input = new Scanner(System.in);
        System.out.println("Enter the amount of rainfall in inches");
        while (input.hasNextDouble()) {
            final double num = input.nextDouble();
            if (num >= 0) {
                // add num to running total of rainfall increase counter for numOfEntries
                totalRain+=num;
                numOfEntries++;
            }
        }
        // compute the average
        avg = totalRain/numOfEntries;
        if (avg >= 0) { // only print result if there is one or more valid inputs
            System.out.println("The average rainfall in inches was " + avg + " in.");
        }
    }
} // end class
