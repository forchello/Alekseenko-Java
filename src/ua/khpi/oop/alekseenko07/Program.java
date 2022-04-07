package ua.khpi.oop.alekseenko07;

public class Program {
    public static void main ( String[] args ) {
        Auto test1 = new Auto();
        test1.setModel("Tesla");
        test1.setReleaseYear(2020);

        Auto test2 = new Auto();
        test2.setModel("Tesla");
        test2.setReleaseYear(2020);

        System.out.println(test1.toString());
        System.out.println(test2.toString());

        System.out.println(test1.equals(test2) + "\n");

        // РАБОТА С МАССИВАМИ

        Auto auto1 = new Auto();
        Auto auto2 = new Auto();
        Auto auto3 = new Auto();
        Auto auto4 = new Auto();
        Auto auto5 = new Auto();

        Auto[] cars = {auto1,auto2,auto3,auto4,auto5};
        System.out.println(cars.length);

        int year = 2017;
        int price = 50000;
        for ( Auto car : cars ) {
            car.setModel("Tesla");
            car.setPrice(price);
            price += 10000;
            car.setReleaseYear(year++);
        }

        for ( Auto car : cars ) {
            System.out.println(car.toString());
        }

    }
}
