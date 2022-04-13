package ua.khpi.oop.alekseenko13;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Container implements Iterable<Auto>  {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_BLUE = "\u001B[34m";

    private static final long DIVIDER = 1_000_000;

    private LinkedList<Auto> container = new LinkedList();
//    private Iterator<Auto> iterator = container.iterator();

    // добавление машины в гараж
    public void add( Auto data ) {
        container.push(data);
    }

    // удаление машины из гаража если такая переданная машина имеется в списке
    public void remove( Auto data ) {
        container.remove(data);
    }

    public void removeByPos( int data ) {
        container.removeByPos(data);
    }

    // очищение списка
    public void clear() {
        container.clear();
    }

    // список в массив
    public Object[] toArray() {
        return container.toArray();
    }

    //из массива в список
    public void fromArray( Object[] o ) { container.fromArray(o); }

    // список в строку
    public String toString() {
        return container.toString();
    }

    // проверка на наличие объекта в списке
    public boolean Contains( Auto i ) {
        return container.contains(i);
    }

    public void carList() {
        container.printList();
    }

    public void xml_save ( String path ) {
        System.out.println(ANSI_GREEN + "XML SAVING...\n" + ANSI_RESET);
        try (XMLEncoder xmlEncoder = new XMLEncoder(new FileOutputStream(path))) {
            xmlEncoder.writeObject( container.toArray() );
            xmlEncoder.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(ANSI_GREEN + "XML SAVING SUCCESSFULLY ENDED\n" + ANSI_RESET);
    }

    public void xml_load ( String path ) {
        System.out.println("LOADING...");
        try (XMLDecoder xmlDecoder = new XMLDecoder(new FileInputStream(path))) {
            container.fromArray((Object[]) xmlDecoder.readObject());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("LOADING SUCCESSFULLY ENDED\n");
    }

    public void standard_save(String path ) {
        System.out.println(ANSI_GREEN + "TXT SAVING...\n" + ANSI_RESET);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream( path );
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(container.toArray());
            objectOutputStream.close();
        } catch (Exception e) {
            System.err.println(e);
        }
        System.out.println(ANSI_GREEN + "TXT SAVING SUCCESSFULLY ENDED\n" + ANSI_RESET);
    }

    public void standard_load ( String path ) {
        try {
            System.out.println("LOADING...");
            FileInputStream fileInputStream = new FileInputStream( path );
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            container.fromArray((Object[]) objectInputStream.readObject());
        } catch (Exception e) {
            System.err.println(e);
        }
        System.out.println("LOADING SUCCESSFULLY ENDED\n");
    }

    public void sortByReleaseYear () {

        Object[] array = container.toArray();
        Object temp = null;

        for ( int i = 0; i < array.length; i++ ) {
            for( int j = i + 1; j < array.length; j++) {
                if ( ((Auto) array[i]).getReleaseYear().compareTo(((Auto) array[j]).getReleaseYear() ) > 0 )
                {
                    temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }

        container.fromArray(array);
    }

    public void sortByFuel () {

        Object[] array = container.toArray();
        Object temp = null;

        for ( int i = 0; i < array.length; i++ ) {
            for( int j = i + 1; j < array.length; j++) {

                final int IFuel = (Integer) ((Auto) array[i]).getUrbanFuel() + (Integer) ((Auto) array[i]).getSubUrbanFuel();
                final int JFuel = (Integer) ((Auto) array[j]).getUrbanFuel() + (Integer) ((Auto) array[j]).getSubUrbanFuel();

                if ( IFuel > JFuel )
                {
                    temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }

        container.fromArray(array);
    }

    public void sortByPrice () {

        // P.S.
        // относительно этого метода сортировки не было точного понятия,
        // как можно отсортировать массив по заданной цене.
        // Поэтому сортировка происходит от дешевой машины к дорогой.

        Object[] array = container.toArray();
        Object temp = null;

        for ( int i = 0; i < array.length; i++ ) {
            for( int j = i + 1; j < array.length; j++) {

                if ( ((Auto) array[i]).getPrice().compareTo(((Auto) array[j]).getPrice() ) > 0 )
                {
                    temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }

        container.fromArray(array);
    }

    public boolean loadFromFile(String path) throws IOException {
        String result = null;

        DataInputStream reader = new DataInputStream(new FileInputStream(path));
        int nBytesToRead = reader.available();

        if(nBytesToRead > 0) {
            byte[] bytes = new byte[nBytesToRead];
            reader.read(bytes);
            result = new String(bytes);
        }

        for ( String line : result.split("\n") ) {
            if ( !addingWithChecking(line.split(", ")) ) {
                System.out.println("loadFromFile - FAILED");
                return false;
            }
        }

        System.out.println("loadFromFile - SUCCESS");
        return true;
    }

    public boolean addingWithChecking( String[] fields ) {

        Auto ADDED_CAR = new Auto();
        int i = 0;

        Pattern MODEL_NAME_PATTERN = Pattern.compile("[A-ZА-Я\\s\\d-]+");
        Matcher MODEL_NAME_MATCHER = MODEL_NAME_PATTERN.matcher(fields[i]);

        if ( MODEL_NAME_MATCHER.matches() ) {
            ADDED_CAR.setModel( fields[i++] );
        } else return ERROR_MESS();

        Pattern YEAR_PATTERN = Pattern.compile("\\d\\d\\d\\d");
        Matcher YEAR_MATCHER = YEAR_PATTERN.matcher(fields[i]);

        if ( YEAR_MATCHER.matches() ) {
            ADDED_CAR.setReleaseYear( Integer.parseInt(fields[i++]) );
        } else return ERROR_MESS();

        Pattern URBAN_PATTERN = Pattern.compile("\\d+");
        Matcher URBAN_MATCHER = URBAN_PATTERN.matcher(fields[i]);

        if ( URBAN_MATCHER.find() ) {
            ADDED_CAR.setUrbanFuel( Integer.parseInt(fields[i++]) );
        } else return ERROR_MESS();

        Pattern SUBURBAN_PATTERN = Pattern.compile("\\d+");
        Matcher SUBURBAN_MATCHER = SUBURBAN_PATTERN.matcher(fields[i]);

        if ( SUBURBAN_MATCHER.find() ) {
            ADDED_CAR.setSubUrbanFuel( Integer.parseInt(fields[i++]) );
        } else return ERROR_MESS();

        ADDED_CAR.setTechnicalCondition(Boolean.valueOf(fields[i++]));

        Pattern PRICE_PATTERN = Pattern.compile("\\d\\d\\d\\d\\d");
        Matcher PRICE_MATCHER = PRICE_PATTERN.matcher(fields[i]);

        if ( PRICE_MATCHER.find() ) {
            ADDED_CAR.setPrice( Integer.parseInt(fields[i++]) );
        } else return ERROR_MESS();

        this.add(ADDED_CAR);
        return true;
    }

    private static boolean ERROR_MESS () {
        System.err.println("Error. Incorrect input.");
        return false;
    }

    private static boolean TimeIsOver (long start_time_ms, long max_time_ms) {
        long time_spent = System.nanoTime() / DIVIDER - start_time_ms;
        System.out.println(ANSI_GREEN + "Time Spent From Start --> "+ ANSI_YELLOW + time_spent + " ms");

        return max_time_ms > time_spent;
    }

    public boolean findCar(long max_time_ms) {

        long start_time_ms = System.nanoTime() / DIVIDER;

        System.out.println(ANSI_GREEN + "Time Max --> " + ANSI_BLUE + max_time_ms + " ms");

        Object[] car_array = container.toArray();

        for ( Object car : car_array ) {

            if( TimeIsOver( start_time_ms, max_time_ms ) ) {
                final int fuel = (Integer) ((Auto) car).getUrbanFuel() + (Integer) ((Auto) car).getSubUrbanFuel();
                // если топливо не тратиться - значит машина єто електрокар

                if (fuel == 0) {

                    Pattern YEAR_PATTERN = Pattern.compile("2021");
                    Matcher YEAR_MATCHER = YEAR_PATTERN.matcher(((Auto) car).getReleaseYear().toString());

                    if (YEAR_MATCHER.find()) {
                        System.out.println("\n" + ANSI_YELLOW + car.toString() + ANSI_RESET + "\n");
                    }
                }
            } else return false;
        }

        System.out.println();
        return true;
    }

    public Iterator<Auto> iterator() {
        return new ListIterator();
    };

    private class ListIterator implements Iterator<Auto> {
        private int index;
        private Object[] object = container.toArray();

        @Override
        public boolean hasNext() {
            //System.out.println( index + "| " + container.getSize());

            if ( index < container.getSize() ) {
                return true;
            } else return false;
        }

        public Auto next() {
            if ( hasNext() ) {
                return (Auto) object[index++];
            }
            return null;
        }

        public void remove() {
            removeByPos(index);
        }

//        public void reset() {
//            index = 0;
//        }
    }
}
