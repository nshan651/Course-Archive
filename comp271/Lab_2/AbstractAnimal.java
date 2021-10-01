package Lab_2;

public abstract class AbstractAnimal implements Animal {
    private double weight;
    private final String name;

    public AbstractAnimal(final String name, final double weight) {
        this.name = name;
        this.weight = weight;
    }
    public String getName() {
        return name;
    }
    public double getWeight() {
        return weight;
    }
    public abstract double getDailyFoodPercentage();
    public void doDailyFeeding() {
        this.weight *= (1+this.getDailyFoodPercentage());

    }
    public void doDailyExercise() {
        this.weight /= (1+this.getDailyFoodPercentage());
    }
}
