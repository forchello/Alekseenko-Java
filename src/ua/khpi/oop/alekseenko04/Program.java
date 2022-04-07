package ua.khpi.oop.alekseenko04;

import java.util.Scanner;

class Palindromes {
    static final String ANSI_RESET = "\u001B[0m";
    static final String ANSI_YELLOW = "\u001B[33m";
    static final String ANSI_GREEN = "\u001B[32m";
    static final String ANSI_RED = "\u001B[31m";
    static final String ANSI_WHITE = "\u001B[37m";

    private static String text = "";

    public static void InputText () {
        Scanner scan = new Scanner(System.in);
        text = scan.nextLine();
        System.out.print("\n");
    }

    public static void ShowText () {
        System.out.println(ANSI_GREEN + "SHOW TEXT:" + ANSI_RESET);
        System.out.println(ANSI_WHITE + "- " + ( text.equals("") ? ( ANSI_RED + "null" + ANSI_RESET ) : text ) + ANSI_RESET + "\n");
    }

    public static void ShowFind () {
        int result = Find ( true );
        if ( result == -1  ) {
            return;
        } else {
            System.out.println(ANSI_GREEN + "SHOW FOUND COUNT:" + ANSI_RESET);
            System.out.println(ANSI_WHITE + "- " + result + ANSI_RESET + "\n");
        }
    }

    public static int Find ( boolean DEBUG ) {
        if ( text.equals("") ) {
            System.err.println("ERROR");
            System.out.println("- Please input text first [1]\n");
            return -1;
        }

        System.out.println("\n");

        int count = 0;

        StringBuilder str = new StringBuilder( text );

        while ( !str.toString().equals("") ) {

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

class Menu {

    static final String ANSI_RESET = "\u001B[0m";
    static final String ANSI_YELLOW = "\u001B[33m";
    static final String ANSI_GREEN = "\u001B[32m";

    static void GetInfo () {
        System.out.println(ANSI_GREEN + "INFO" + ANSI_RESET);
        System.out.println(ANSI_YELLOW+ "- THE PROGRAM CREATED BY\n" + ANSI_RESET +
                "- a student of NTU KhPI\n" +
                "- Group 120-V\n" +
                "- Alekseenko Nikita\n");

        System.out.println(ANSI_GREEN + "LINE OPTIONS" + ANSI_RESET);
        System.out.println("-h / -help - help panel output");
        System.out.println("-d / -debug - debug panel output\n");
    }

    public static void MenuCreate ( String[] args ) {

        boolean DEBUG = false;

        String ConsArg = args.length==0 ? "" : args[0];

        switch ( ConsArg ) {
            case "-d":
            case "-debug": DEBUG = true; break;
            case "-h":
            case "-help": GetInfo(); break;
            case "" : {
                System.out.println("\n" + ANSI_YELLOW + "WARNING:" + ANSI_RESET);
                System.out.println("The program was launched without flags.\n");
                break;
            }
        }

        while ( true ) {

            System.out.println(ANSI_GREEN + "CHOOSE ANY TASK:" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "[1] Input Text");
            System.out.println("[2] Show Entered");
            System.out.println("[3] Find Palindromes");
            System.out.println("[4] Show Count of Palindromes");
            System.out.println("[5] Close" + ANSI_RESET + "\n");

            Scanner scan = new Scanner(System.in);
            int chooseNumber = scan.nextInt();

            switch (chooseNumber) {
                case 1: Palindromes.InputText(); break;
                case 2: Palindromes.ShowText(); break;
                case 3: Palindromes.Find( DEBUG ); break;
                case 4: Palindromes.ShowFind(); break;
                case 5: return;
                default: {
                    System.err.println("ERROR");
                    System.out.println("- Please select the correct command!");
                }
            }
        }
    }
}

public class Program {

    public static void main ( String[] args ) {
        Menu.MenuCreate(args);
    }
}
