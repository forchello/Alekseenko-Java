package ua.khpi.oop.alekseenko06;

import java.util.Scanner;

public class Menu {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_GREEN = "\u001B[32m";

    private static void GetInfo () {
        System.out.println(ANSI_GREEN + "INFO" + ANSI_RESET);
        System.out.println(ANSI_YELLOW+ "- THE PROGRAM CREATED BY\n" + ANSI_RESET +
                "- a student of NTU KhPI\n" +
                "- Group 120-V\n" +
                "- Alekseenko Nikita\n");

        System.out.println(ANSI_GREEN + "LINE OPTIONS" + ANSI_RESET);
        System.out.println("-h / -help - help panel output");
        System.out.println("-d / -debug - debug panel output\n");
    }

    public static void MenuCreate ( String[] args ) throws Exception {

        boolean DEBUG = false;

        String ConsArg = args.length==0 ? "" : args[0];

        switch (ConsArg) {
            case "-d", "-debug" -> DEBUG = true;
            case "-h", "-help" -> GetInfo();
            case "" -> {
                System.out.println("\n" + ANSI_YELLOW + "WARNING:" + ANSI_RESET);
                System.out.println("The program was launched without flags.\n");
            }
            default -> {
                System.out.println("\n" + ANSI_YELLOW + "WARNING:" + ANSI_RESET);
                System.out.println("Invalid flags.\n");
            }
        }

        Palindromes palindromes = new Palindromes();

        while ( true ) {

            System.out.println(ANSI_GREEN + "CHOOSE ANY TASK:" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "[1] Input Text");
            System.out.println("[2] Show Entered");
            System.out.println("[3] Find Palindromes");
            System.out.println("[4] Show Count of Palindromes");
            System.out.println("[5] Save");
            System.out.println("[6] Load");
            System.out.println("[7] Close" + ANSI_RESET + "\n");

            Scanner scan = new Scanner(System.in);
            int chooseNumber = scan.nextInt();

            switch (chooseNumber) {
                case 1: palindromes.InputText(); break;
                case 2: palindromes.ShowText(); break;
                case 3: palindromes.Find( DEBUG ); break;
                case 4: palindromes.ShowFind(); break;
                case 5: palindromes.Save(); break;
                case 6: palindromes.Load(); break;
                case 7: return;
                default: {
                    System.err.println("ERROR");
                    System.out.println("- Please select the correct command!");
                }
            }
        }
    }
}
