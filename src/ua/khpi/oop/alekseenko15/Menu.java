package ua.khpi.oop.alekseenko15;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Menu {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_RED = "\u001b[31m";

    private static volatile Container container = new Container();

    public static void create (String[] args) throws IOException, InterruptedException {

        String ConsArg = args.length==0 ? "" : args[0];

        switch (ConsArg) {
            case "-auto" -> CarContainerInit();
            case "" -> {
                System.out.println("\n" + ANSI_YELLOW + "WARNING:" + ANSI_RESET);
                System.out.println("The program was launched without flags.\n");
            }
            default -> {
                System.out.println("\n" + ANSI_YELLOW + "WARNING:" + ANSI_RESET);
                System.out.println("Invalid flags.\n");
            }
        }

        while (true) {
            System.out.println(ANSI_GREEN + "CHOOSE ANY TASK:" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "[1] Add Car");
            System.out.println("[2] Remove Car");
            System.out.println("[3] Car List");
            System.out.println("[4] Find new electrocar");
            System.out.println("[5] Sort by Price");
            System.out.println("[6] Standard Save");
            System.out.println("[7] Standard Load");
            System.out.println("[8] XML Save");
            System.out.println("[9] XML Load");
            System.out.println("[0] Close" + ANSI_RESET + "\n");

            Scanner scan = new Scanner(System.in);
            int chooseNumber = scan.nextInt();

            Auto car = new Auto();

            switch (chooseNumber) {
                case 1:
                    if (carAdding()) {
                        System.out.println(ANSI_GREEN + "ADDED!\n" + ANSI_RESET );
                    } else {
                        System.err.println("Please try add correctly");
                    }
                    break;
                case 2: container.remove( inputCarNumber() ); break;
                case 3: {
                    container.carList();
                    break;
                }
                case 4: container.findCar(); break;
                case 5: container.sortByPrice(); break;
                case 6: SAVING( true ); break;
                case 7: LOADING( true ); break;
                case 8: SAVING( false ); break;
                case 9: LOADING( false ); break;
                case 0:
                    return;
                default: {
                    System.err.println("ERROR");
                    System.out.println("- Please select the correct command!");
                }
            }
        }
    }

    private static boolean carAdding () {

        int i = 0;
        String[] result = new String[6];

        Scanner scan = new Scanner(System.in);

        System.out.println("Please type your car model:");
        result[i++] = scan.nextLine();

        System.out.println("Please type your car release car:");
        result[i++] = scan.nextLine();

        System.out.println("Please type your car urban fuel:");
        result[i++] = scan.nextLine();

        System.out.println("Please type your car suburban fuel:");
        result[i++] = scan.nextLine();

        System.out.println("Please choose your car state:");
        System.out.println("[1] Good");
        System.out.println("[2] Bad");

        result[i++] = scan.nextLine();

        System.out.println("Please type your car price:");
        result[i++] = scan.nextLine();

        if ( !container.addingWithChecking(result) ) {
            System.out.println("loadFromFile - FAILED\n");
            return false;
        }

        return true;
    }

    private static int inputCarNumber () {
        Scanner scan = new Scanner(System.in);
        String number = scan.nextLine();

        if ( number.equals("") ) {
            return 0;
        } else return Integer.parseInt(number);
    }

    private static void SAVING( boolean standard ) {
        System.out.println("Enter the path to your file:");
        String PATH = "C:/Users/FORCH/Desktop";

        while (true) {
            System.out.print(PATH + "/");
            Scanner scan = new Scanner(System.in);
            String command = scan.nextLine();

            if ( command.equals("exit") ) {
                System.out.println("");
                return;
            }

            // REGEX -----------------------------------

            Pattern CD_PATTERN = Pattern.compile("cd\\s.+");
            Matcher CD_MATCHER = CD_PATTERN.matcher(command);

            Pattern TOUCH_PATTERN = Pattern.compile("touch\\s.+\\..+");
            Matcher TOUCH_MATCHER = TOUCH_PATTERN.matcher(command);

            // -----------------------------------------
            if ( command.equals("ls") ) {
                File dir = new File(PATH);
                for(File item : dir.listFiles()){
                    if(item.isDirectory()){
                        System.out.println( ANSI_GREEN + "- " + item.getName() + ANSI_RESET);
                    } else {
                        Pattern LS_XML_PATTERN = Pattern.compile(".+\\.xml");
                        Matcher LS_XML_MATCHER = LS_XML_PATTERN.matcher(item.getName());

                        if ( LS_XML_MATCHER.find() ) {
                            System.out.println(ANSI_BLUE + "- " + item.getName() + ANSI_RESET);
                        } else System.out.println(ANSI_YELLOW + "- " + item.getName() + ANSI_RESET);
                    }
                }
            }
            else if (CD_MATCHER.find()) {
                String cd_string = command.substring(CD_MATCHER.start() + 3, CD_MATCHER.end());
                PATH = PATH + "/" + cd_string;
            } else if (TOUCH_MATCHER.find()) {
                String file_name = command.substring(TOUCH_MATCHER.start() + 6, TOUCH_MATCHER.end());

                File file_path = new File(PATH + "/" + file_name);

                if ( file_path.exists() ) {
                    System.out.println(ANSI_YELLOW + "WARNING" + ANSI_RESET);
                    System.out.println("The file already exists at the given path. Recreate?");
                    System.out.println("[1] - yes");
                    System.out.println("[2] - no");

                    Scanner recreate = new Scanner(System.in);
                    int returned = recreate.nextInt();

                    if ( returned == 1 ) {
                        if ( standard ) {
                            container.standard_save(PATH + "/" + file_name);
                        } else {
                            container.xml_save(PATH + "/" + file_name);
                        }
                    }
                } else {
                    if ( standard ) {
                        container.standard_save(PATH + "/" + file_name);
                    } else {
                        container.xml_save(PATH + "/" + file_name);
                    }
                }

            }

            // -----------------------------------------
        }
    }

    private static void LOADING( boolean standard ) {
        System.out.println("Enter the path to your file:");
        String PATH = "C:/Users/FORCH/Desktop";

        while (true) {
            System.out.print(PATH + "/");
            Scanner scan = new Scanner(System.in);
            String command = scan.nextLine();

            if ( command.equals("exit") ) {
                System.out.println("");
                return;
            }

            // REGEX -----------------------------------

            Pattern CD_PATTERN = Pattern.compile("cd\\s.+");
            Matcher CD_MATCHER = CD_PATTERN.matcher(command);

            Pattern CAT_PATTERN = Pattern.compile("cat\\s.+\\..+");
            Matcher CAT_MATCHER = CAT_PATTERN.matcher(command);

            // -----------------------------------------
            if ( command.equals("ls") ) {
                File dir = new File(PATH);
                for(File item : dir.listFiles()){
                    if(item.isDirectory()){
                        System.out.println( ANSI_GREEN + "- " + item.getName() + ANSI_RESET);
                    } else {
                        Pattern LS_XML_PATTERN = Pattern.compile(".+\\.xml");
                        Matcher LS_XML_MATCHER = LS_XML_PATTERN.matcher(item.getName());

                        if ( LS_XML_MATCHER.find() ) {
                            System.out.println(ANSI_BLUE + "- " + item.getName() + ANSI_RESET);
                        } else System.out.println(ANSI_YELLOW + "- " + item.getName() + ANSI_RESET);
                    }
                }
            }
            else if (CD_MATCHER.find()) {
                String cd_string = command.substring(CD_MATCHER.start() + 3, CD_MATCHER.end());
                PATH = PATH + "/" + cd_string;
            } else if (CAT_MATCHER.find()) {
                String file_name = command.substring(CAT_MATCHER.start() + 4, CAT_MATCHER.end());

                if ( standard ) {
                    container.standard_load(PATH + "/" + file_name);
                } else {
                    container.xml_load(PATH + "/" + file_name);
                }
            }

            // -----------------------------------------
        }
    }

    private static void CarContainerInit() throws IOException {

        System.out.println("Welcome to automatic mode!");

        container.xml_load("lab15.xml");

        if (carAdding()) {
            System.out.println(ANSI_GREEN + "ADDED!\n" + ANSI_RESET );
        } else {
            System.err.println("Please try add correctly");
        }

        container.carList();

        container.findCar();

        container.sortByPrice();

        container.carList();

        System.exit(0);
    }
}