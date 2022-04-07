package ua.khpi.oop.alekseenko08;

import java.io.File;
import java.util.Scanner;

import java.util.regex.*;

public class Menu {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_BLUE = "\u001B[34m";

    private static Garage garage;
    public static void create () {
        garage = new Garage();

        while (true) {
            System.out.println(ANSI_GREEN + "CHOOSE ANY TASK:" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "[1] Add Car");
            System.out.println("[2] Remove Car");
            System.out.println("[3] Car List");
            System.out.println("[4] Save");
            System.out.println("[5] Load");
            System.out.println("[6] Close" + ANSI_RESET + "\n");

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
                case 2: garage.removeCar( inputCarNumber() ); break;
                case 3: {
                    garage.carList();
                    break;
                }
                case 4: SAVING(); break;
                case 5: LOADING(); break;
                case 6:
                    return;
                default: {
                    System.err.println("ERROR");
                    System.out.println("- Please select the correct command!");
                }
            }
        }
    }

    private static boolean carAdding () {
        Auto ADDED_CAR = new Auto();

        Scanner scan = new Scanner(System.in);

        System.out.println("Please type your car model:");
        String model = scan.nextLine();
        if ( model.equals("") ) {
            ERROR_MESS();
            return false;
        } else ADDED_CAR.setModel( model );

        System.out.println("Please type your car release car:");
        String year = scan.nextLine();
        ADDED_CAR.setReleaseYear( year.equals("") ? 0 : Integer.parseInt(year) );

        System.out.println("Please type your car urban fuel:");
        String urbanFuel = scan.nextLine();
        ADDED_CAR.setUrbanFuel( urbanFuel.equals("") ? 0 : Integer.parseInt(urbanFuel) );

        System.out.println("Please type your car suburban fuel:");
        String suburbanFuel = scan.nextLine();
        ADDED_CAR.setSubUrbanFuel( suburbanFuel.equals("") ? 0 : Integer.parseInt(suburbanFuel) );

        System.out.println("Please choose your car state:");
        System.out.println("[1] Good");
        System.out.println("[2] Bad");
        String technicalCondition = scan.nextLine();
        ADDED_CAR.setTechnicalCondition(Integer.parseInt(technicalCondition) == 1);

        System.out.println("Please type your car price:");
        String price = scan.nextLine();
        ADDED_CAR.setPrice( price.equals("") ? 0 : Integer.parseInt(price) );


        garage.addCar(ADDED_CAR);
        return true;
    }

    private static int inputCarNumber () {
        Scanner scan = new Scanner(System.in);
        String number = scan.nextLine();

        if ( number.equals("") ) {
            return 0;
        } else return Integer.parseInt(number);
    }

    private static void ERROR_MESS () {
        System.err.println("Error");
    }

    private static void SAVING() {
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

            Pattern TOUCH_XML_PATTERN = Pattern.compile("touch\\s.+\\.xml");
            Matcher TOUCH_XML_MATCHER = TOUCH_XML_PATTERN.matcher(command);

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
                        }
                    }
                }
            }
            else if (CD_MATCHER.find()) {
                String cd_string = command.substring(CD_MATCHER.start() + 3, CD_MATCHER.end());
                PATH = PATH + "/" + cd_string;
            } else if (TOUCH_XML_MATCHER.find()) {
                String file_name = command.substring(TOUCH_XML_MATCHER.start() + 6, TOUCH_XML_MATCHER.end());

                File file_path = new File(PATH + "/" + file_name);

                if ( file_path.exists() ) {
                    System.out.println(ANSI_YELLOW + "WARNING" + ANSI_RESET);
                    System.out.println("The file already exists at the given path. Recreate?");
                    System.out.println("[1] - yes");
                    System.out.println("[2] - no");

                    Scanner recreate = new Scanner(System.in);
                    int returned = recreate.nextInt();

                    if ( returned == 1 ) {
                        garage.save(PATH + "/" + file_name);
                    }
                } else garage.save(PATH + "/" + file_name);

            }

            // -----------------------------------------
        }
    }
    private static void LOADING() {
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

            Pattern CAT_XML_PATTERN = Pattern.compile("cat\\s.+\\.xml");
            Matcher CAT_XML_MATCHER = CAT_XML_PATTERN.matcher(command);

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
                        }
                    }
                }
            }
            else if (CD_MATCHER.find()) {
                String cd_string = command.substring(CD_MATCHER.start() + 3, CD_MATCHER.end());
                PATH = PATH + "/" + cd_string;
            } else if (CAT_XML_MATCHER.find()) {
                String file_name = command.substring(CAT_XML_MATCHER.start() + 4, CAT_XML_MATCHER.end());
                garage.load(PATH + "/" + file_name);
            }

            // -----------------------------------------
        }
    }
}