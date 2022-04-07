package ua.khpi.oop.alekseenko10;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.Iterator;


public class Container implements Iterable<Auto> {
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
        System.out.println("SAVING...");
        try (XMLEncoder xmlEncoder = new XMLEncoder(new FileOutputStream(path))) {
            xmlEncoder.writeObject( container.toArray() );
            xmlEncoder.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("SAVING SUCCESSFULLY ENDED\n");
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
        System.out.println("SAVING...");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream( path );
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(container.toArray());
            objectOutputStream.close();
        } catch (Exception e) {
            System.err.println(e);
        }
        System.out.println("SAVING SUCCESSFULLY ENDED\n");
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
