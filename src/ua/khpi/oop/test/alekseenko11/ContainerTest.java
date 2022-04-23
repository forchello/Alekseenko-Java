package ua.khpi.oop.test.alekseenko11;

import org.junit.jupiter.api.Test;
import ua.khpi.oop.alekseenko11.Container;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ContainerTest {

    @Test
    void addingWithChecking() {
        Container container = new Container();

        String[] fields = {"TESLA", "2021", "0", "0", "true", "50000"};

        assertTrue(container.addingWithChecking(fields));
    }

    @Test
    void loadFromFile() throws IOException {

        Container container = new Container();

        assertEquals(0, container.toArray().length );

        final String file_path = System.getProperty("user.dir") + "\\src\\ua\\khpi\\oop\\test\\alekseenko11\\save_lab_11.txt";

        assertTrue(container.loadFromFile(file_path));
    }
}