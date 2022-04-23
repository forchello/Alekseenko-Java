package ua.khpi.oop.test.alekseenko12;

import org.junit.jupiter.api.Test;
import ua.khpi.oop.alekseenko12.Container;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ContainerTest {

    @Test
    void findCar() throws IOException {
        Container container = new Container();

        final String file_path = System.getProperty("user.dir") + "\\src\\ua\\khpi\\oop\\test\\alekseenko12\\save_lab_12.txt";

        assertTrue(container.loadFromFile(file_path));
        assertTrue(container.findCar());
    }
}