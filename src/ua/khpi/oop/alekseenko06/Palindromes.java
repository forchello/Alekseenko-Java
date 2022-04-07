package ua.khpi.oop.alekseenko06;

import javax.management.ObjectName;
import java.io.FileInputStream;
import java.util.Scanner;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

public class Palindromes {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_WHITE = "\u001B[37m";

    private Container container = new Container();
    private Iterator iterator = container.getIterator();

    public void Save () throws Exception {
        FileOutputStream fileOutputStream = new FileOutputStream("save");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(container);
        objectOutputStream.close();
    }

    public void Load () throws Exception {
        FileInputStream fileInputStream = new FileInputStream("save");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        container = (Container) objectInputStream.readObject();
    }

    public  void InputText () {
        System.out.println(ANSI_YELLOW + "WARNING:" + ANSI_RESET);
        System.out.println("To finish entering text, leave a blank line and press ENTER.\n" + ANSI_YELLOW
                + "---------------" + ANSI_RESET);

        final int BUFFER_SIZE = 100;

        Scanner scan = new Scanner(System.in);

        for ( int i = 0; i < BUFFER_SIZE; i++ ) {
            container.add( scan.nextLine() );
            if ( container.contains("") ) {
                container.setSize(i);
                break;
            }
        }

        System.out.print("\n");
    }

    public void ShowText () {

        System.out.println(ANSI_GREEN + "SHOW TEXT:" + ANSI_RESET);
        System.out.println(container.textToString() == null ? "null" : container.textToString());
        System.out.println(ANSI_WHITE + "- " + ( container.textToString().equals("")
                ? ( ANSI_RED + "null" + ANSI_RESET ) : container.textToString() + ANSI_RESET));

        System.out.println();
    }

    public void ShowFind () {
        int result = Find ( true );
        if ( !(result == -1) ) {
            System.out.println(ANSI_GREEN + "SHOW FOUND COUNT:" + ANSI_RESET);
            System.out.println("- " + result + "\n");
        }
    }

    public int Find ( boolean DEBUG ) {
        if (container.getSize() == 0) {
            System.err.println("ERROR");
            System.out.println("- Please input text first [1]\n");
            return -1;
        }

        int count = 0;
        boolean isFirstWord = true;

        while ( iterator.hasNext() ) {

            String help = iterator.next();
            StringBuilder str = new StringBuilder(help);

            while (!str.toString().equals("")) {

                int SpaceIndex = str.indexOf(" ");
                StringBuilder word = new StringBuilder(SpaceIndex == -1 ? str : str.substring(0, SpaceIndex));
                StringBuilder wordReverse = new StringBuilder(SpaceIndex == -1 ? str : str.substring(0, SpaceIndex));

                wordReverse.reverse();

                if (word.toString().equals(wordReverse.toString())) {
                    count++;
                    if (DEBUG) {
                        if (isFirstWord) {
                            isFirstWord = false;
                            System.out.println(ANSI_GREEN + "FOUND PALINDROMES:" + ANSI_RESET);
                        }
                        System.out.println("- " + word);
                    }
                }

                if (SpaceIndex == -1) {
                    str.delete(0, str.length());
                } else {
                    str.delete(0, SpaceIndex + 1);
                }
            }

            if (count == 0 && DEBUG) {
                System.out.println("\n" + ANSI_YELLOW + "WARNING:" + ANSI_RESET);
                System.out.println("There are no palindrome words in this text.\n");
            }
        }

        System.out.println();
        iterator.reset();
        return count;
    }
}
