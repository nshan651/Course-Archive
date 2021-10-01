package Lab_2;

public class Tiger extends AbstractAnimal {
    public Tiger(final String name, final double weight) {
        super(name, weight);
    }
    @Override public double getDailyFoodPercentage() {
        return 0.05;
    }
    @Override public String toString() {
        return "This is " + getName() + " the tiger";
    }
}
