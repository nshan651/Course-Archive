package Lab_3;

/** Class containing average rainfall, num of rainy days, and num of dry days, days rained, and total rainfall */
public class RainfallStats {
    public final double average, totalRainfall;
    public final int numRainy, numDry, daysRained;

    public RainfallStats(final double average, final int numRainy, final int numDry, final int daysRained, final double totalRainfall) {
        this.average = average;
        this.numRainy = numRainy;
        this.numDry = numDry;
        this.daysRained = daysRained;
        this.totalRainfall = totalRainfall;
    }

}
