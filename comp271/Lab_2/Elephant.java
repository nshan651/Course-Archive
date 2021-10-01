package Lab_2;

public class Elephant extends AbstractAnimal {
    public Elephant(final String name, final double weight) {
        super(name, weight);
    }
    @Override public double getDailyFoodPercentage() {
        return 0.1;
    }
    @Override public String toString() {
        return "This is " + getName() + " the elephant";
    }
}
