package ua.khpi.oop.alekseenko03;

import java.util.Scanner;
import java.lang.StringBuilder;

class Palindromes {
    static final String ANSI_RESET = "\u001B[0m";
    static final String ANSI_YELLOW = "\u001B[33m";
    static final String ANSI_GREEN = "\u001B[32m";
    static final String ANSI_RED = "\u001B[31m";

    private static String INPUT_TEXT () {
        Scanner scan = new Scanner(System.in);
        String S = scan.nextLine();
        scan.close();
        return S;
    }

    public static int Find ( boolean DEBUG ) {
        int count = 0;

        StringBuilder str = new StringBuilder( INPUT_TEXT() );

        for ( int i = 0; !str.toString().equals(""); i++ ) {

            int SpaceIndex = str.indexOf(" ");
            StringBuilder word = new StringBuilder( SpaceIndex == -1 ? str : str.substring(0, SpaceIndex) );
            StringBuilder wordReverse = new StringBuilder( SpaceIndex == -1 ? str : str.substring(0, SpaceIndex) );

            wordReverse.reverse();

            if ( word.toString().equals(wordReverse.toString()) ) {
                count++;
                boolean isFirstWord = true;
                if ( DEBUG ) {
                    if ( isFirstWord ) {
                        isFirstWord = false;
                        System.out.println("\n" + ANSI_GREEN + "FOUND PALINDROMES:" + ANSI_RESET);
                    }
                    System.out.println("- " + word + "\n");
                }
            }

            if ( SpaceIndex == -1 ) {
                str.delete(0, str.length());
            } else {
                str.delete(0, SpaceIndex + 1);
            }
        }

        if ( count == 0 && DEBUG ) {
            System.out.println("\n" + ANSI_YELLOW + "WARNING:" + ANSI_RESET);
            System.out.println("There are no palindrome words in this text.\n");
        }

        return count;
    }
}

public class Program {

    public static void main ( String[] args ) {
        Palindromes palindromes = new Palindromes();

        int countPalindromes = palindromes.Find( true );
        System.out.println(countPalindromes);
    }
}
