package Lab_2;

public class Mouse extends AbstractAnimal {
    public Mouse(final String name, final double weight) {
        super(name, weight);
    }
    @Override public double getDailyFoodPercentage() {
        return 0.01;
    }
    @Override public String toString() {
        return "This is " + getName() + " the mouse";
    }
}
