package ua.khpi.oop.alekseenko02;

import java.util.Random;

public class Program {

    public static boolean isPrimeNumber ( int number ) {
        for ( int i = 2; i < number; i++ ) {
            if ( number % i == 0 ) return false;
            else break;
        }
        return true;
    }

    public static void main(String[] args) {
        Random rand = new Random();
        final int listSize = 1000;

        for ( int i = 0; i < listSize; i++ ) {
            int RandomNumber = rand.nextInt(100000);
            System.out.print( RandomNumber + " - ");
            System.out.println( isPrimeNumber( RandomNumber) ? "простое" : "не простое");
        }
    }
}
