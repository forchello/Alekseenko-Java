package ua.khpi.oop.alekseenko08;

import java.beans.XMLEncoder;
import java.beans.XMLDecoder;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.FileInputStream;

public class Garage {
    int carsCount = 0;
    private Auto[] cars;

    public void addCar ( Auto car ) {
        if (carsCount == 0) {
            carsCount++;
            cars = new Auto[carsCount];
            cars[0] = car;
        } else {
            Auto[] temp = cars;
            carsCount++;
            cars = new Auto[carsCount];

            for ( int i = 0; i < carsCount - 1; i++ ) {
                cars[i] = temp[i];
            }

            cars[carsCount-1] = car;
        }
    }
    public boolean removeCar ( int number ) {
        for ( int i = 0; i < carsCount; i++ ) {
            if ( i == number ) {
                // удаление со сдвигом+

                Auto[] temp = new Auto[carsCount-1];

                //arraycopy(1 массив, исходная позиция в 1 массиве, 2 массив, исходная позиция во 2 массиве, длина)

                System.arraycopy(cars, 0, temp, 0, i);
                System.arraycopy(cars, i + 1, temp, i, carsCount - i - 1);

                cars = temp;
                carsCount--;
                return true;
            }
        }
        return false;
    }
    public void carList () {
        if ( cars == null ) {
            System.err.println("No cars in your garage!\n");
            return;
        }

        for ( Auto car : cars ) {
            System.out.println(car.toString());
        }

        System.out.print("\n");
    }

    public void save ( String path ) {
        System.out.println("SAVING...\n");
        try (XMLEncoder xmlEncoder = new XMLEncoder(new FileOutputStream(path))) {
            xmlEncoder.writeObject(cars);
            xmlEncoder.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load ( String path ) {
        System.out.println("LOADING...\n");
        try (XMLDecoder xmlDecoder = new XMLDecoder(new FileInputStream(path))) {
            cars = (Auto[]) xmlDecoder.readObject();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

