package Lab_2;

public class Zoo {
    public static void main(final String[] args) {

        // create animal instances
        final Animal[] zoo = new Animal[] {
                new Elephant("Huey", 505.2),
                new Elephant("Dewey", 612.9),
                new Tiger("Alex", 200),
                new Mouse("Mickey", 10),
        };

        System.out.println("Roll call");
        for (int i = 0; i < zoo.length; i ++) {
            final Animal current = zoo[i];
            System.out.println("This is " + current + " weighing " + current.getWeight() + " kg");
        }

        System.out.println("\nReady for feeding");
        double food = 0;
        for (int i = 0; i < zoo.length; i++) {
            final Animal current = zoo[i];
            food += current.getDailyFoodPercentage() * current.getWeight();
            current.doDailyFeeding();
        }
        System.out.println("Today we used " + food + " kg of food");

        // check weight again
        System.out.println("\nReady to check weight");
        for (int i = 0; i < zoo.length; i ++) {
            final Animal current = zoo[i];
            System.out.println("This is " + current + " weighing " + current.getWeight() + " kg");
        }
        // TODO exercise
        System.out.println("\nReady to work out");
        for (int i = 0; i < zoo.length; i ++) {
            final Animal current = zoo[i];
            current.doDailyExercise();
        }
        // check weight again
        System.out.println("\nReady to check weight");
        for (int i = 0; i < zoo.length; i ++) {
            final Animal current = zoo[i];
            System.out.println("This is " + current + " weighing " + current.getWeight() + " kg");
        }
    }
}
