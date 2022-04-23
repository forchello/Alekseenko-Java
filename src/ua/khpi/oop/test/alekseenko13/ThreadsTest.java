package ua.khpi.oop.test.alekseenko13;

import org.junit.jupiter.api.Test;
import ua.khpi.oop.alekseenko13.Menu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ThreadsTest {

    @Test
    void ThreadTests() throws IOException {
        assertEquals(0, checkIdUniqueness(Menu.CarContainerInit( 1 )));
    }

    private int checkIdUniqueness( List<Long> ids ) {
        Object[] array = ids.toArray();

        for ( int i = 0; i < ids.size(); i++ ) {
            for ( int j = i + 1; j < ids.size(); j++ ) {

                String current_id = String.valueOf(array[i]);
                String next_id = String.valueOf(array[j]);

                if (current_id.equals(next_id)) return -1;
            }
        }

        return 0;
    }
}