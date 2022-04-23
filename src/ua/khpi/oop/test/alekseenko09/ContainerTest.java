package ua.khpi.oop.test.alekseenko09;

import ua.khpi.oop.alekseenko09.Auto;
import ua.khpi.oop.alekseenko09.Container;
import ua.khpi.oop.alekseenko09.Container.ListIterator;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ContainerTest {

    @org.junit.jupiter.api.Test
    void add() {
        Container container = new Container();

        container.add(new Auto("a1"));
        container.add(new Auto("b2"));
        container.add(new Auto("c3"));

        assertEquals( 3, container.toArray().length );
    }

    @org.junit.jupiter.api.Test
    void remove() {
        Container container = new Container();

        Auto forDelete = new Auto("b2");

        container.add(new Auto("a1"));
        container.add(forDelete);
        container.add(new Auto("c3"));

        Object[] arrayBefore = container.toArray();

        container.remove(forDelete);

        Object[] arrayAfter = container.toArray();

        assertFalse(Arrays.equals(arrayBefore, arrayAfter));
    }

    @org.junit.jupiter.api.Test
    void removeByPos() {
        Container container = new Container();

        container.add(new Auto("a1"));
        container.add(new Auto("b2"));
        container.add(new Auto("c3"));
        container.add(new Auto("d4"));
        container.add(new Auto("g5"));

        int expectedPos = 3;

        container.removeByPos(expectedPos);

        Container container2 = new Container();

        container2.add(new Auto("a1"));
        container2.add(new Auto("b2"));
        container2.add(new Auto("c3"));
        container2.add(new Auto("g5"));

        assertArrayEquals(container.toArray(), container2.toArray() );
    }

    @org.junit.jupiter.api.Test
    void clear() {
        Container container = new Container();

        container.add(new Auto("a1"));
        container.add(new Auto("b2"));
        container.add(new Auto("c3"));

        container.clear();
        assertEquals(0, container.toArray().length );
    }

    @org.junit.jupiter.api.Test
    void toArray() {
        Container container = new Container();
        assertTrue( container.toArray() instanceof Object[] );
    }

    @org.junit.jupiter.api.Test
    void ContainerToString() {
        Container container = new Container();
        container.add(new Auto("a1"));

        assertTrue( container.toString() instanceof String );
    }

    @org.junit.jupiter.api.Test
    void contains() {
        Container container = new Container();

        container.add(new Auto("a1"));
        container.add(new Auto("b2"));
        container.add(new Auto("c3"));
        container.add(new Auto("d4"));
        container.add(new Auto("g5"));

        container.contains(new Auto("c3") );
    }

    @org.junit.jupiter.api.Test
    void xml() {
        Container containerToRead = new Container();

        containerToRead.add(new Auto("a1"));
        containerToRead.add(new Auto("b2"));
        containerToRead.add(new Auto("c3"));
        containerToRead.add(new Auto("d4"));
        containerToRead.add(new Auto("g5"));

        final String file_path = System.getProperty("user.dir") + "\\src\\ua\\khpi\\oop\\test\\alekseenko09\\xml.xml";

        containerToRead.xml_save( file_path );

        Container containerToWrite = new Container();

        containerToWrite.xml_load( file_path );

        assertArrayEquals(containerToRead.toArray(), containerToWrite.toArray() );
    }

    @org.junit.jupiter.api.Test
    void standart() {
        Container containerToRead = new Container();

        containerToRead.add(new Auto("a1"));
        containerToRead.add(new Auto("b2"));
        containerToRead.add(new Auto("c3"));
        containerToRead.add(new Auto("d4"));
        containerToRead.add(new Auto("g5"));

        final String file_path = System.getProperty("user.dir") + "\\src\\ua\\khpi\\oop\\test\\alekseenko09\\standart.xml";

        containerToRead.standard_save( file_path );

        Container containerToWrite = new Container();

        containerToWrite.standard_load( file_path );

        assertArrayEquals(containerToRead.toArray(), containerToWrite.toArray() );
    }

    @org.junit.jupiter.api.Test
    void iterator() {

        Container container = new Container();

        container.add(new Auto("a1"));
        container.add(new Auto("b2"));
        container.add(new Auto("c3"));
        container.add(new Auto("d4"));
        container.add(new Auto("g5"));

        ListIterator iterator = container.iterator();

        int iterations_count = 0;

        while ( iterator.hasNext() ) {
            assertNotNull(iterator.next());
            iterations_count++;
        }

        assertEquals(iterations_count, container.toArray().length );

        Container container1 = new Container();

        container1.add(new Auto("a1"));
        container1.add(new Auto("b2"));
        container1.add(new Auto("c3"));
        container1.add(new Auto("d4"));
        container1.add(new Auto("g5"));

        ListIterator iterator1 = container1.iterator();

        int expectedPos = 3, i = 0;

        while ( iterator1.hasNext() ) {
            if ( expectedPos == i++ ) {
                iterator1.remove();
            }
            iterator1.next();
        }

        Container container2 = new Container();

        container2.add(new Auto("a1"));
        container2.add(new Auto("b2"));
        container2.add(new Auto("c3"));
        container2.add(new Auto("g5"));

        assertArrayEquals(container1.toArray(), container2.toArray() );
    }
}