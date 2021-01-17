package service;

import com.legacy.service.Driver;
import com.legacy.service.EnlightenmentImpl;
import com.legacy.service.EstablishmentImpl;
import com.legacy.util.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class DriverTest {

    Driver driver;
    @Mock
    private EnlightenmentImpl enlightenment;
    @Mock
    private EstablishmentImpl establishment;
    @Mock
    private Room room;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        driver = new Driver(room, establishment, enlightenment);
    }

    @Test
    public void runDriverFileNotExistTest() throws IOException {
        int input = 1;
        InputStream in = new ByteArrayInputStream(String.valueOf(input).getBytes());
        System.setIn(in);
        given(room.getRoomBluePrint("room.txt")).willThrow(new IOException());
        driver.runDriver(new String[]{"U"});
        verify(room, times(1)).getRoomBluePrint("room.txt");
    }

    @Test
    public void runDriverInvalidInputTest() throws IOException {
        int input = 3;
        InputStream in = new ByteArrayInputStream(String.valueOf(input).getBytes());
        System.setIn(in);
        driver.runDriver(new String[]{"U"});
        verify(room, times(1)).getRoomBluePrint("room.txt");
    }

    @Test
    public void runDriverEstablishmentCalledTest() throws IOException {
        int input = 1;
        InputStream in = new ByteArrayInputStream(String.valueOf(input).getBytes());
        System.setIn(in);
        driver.runDriver(new String[]{"U"});
        verify(establishment, times(1)).findEscapePath(null, "U");
    }

    @Test
    public void runDriverEnlightmentCalledTest() throws IOException {
        int input = 2;
        InputStream in = new ByteArrayInputStream(String.valueOf(input).getBytes());
        System.setIn(in);
        driver.runDriver(new String[]{"U"});
        verify(enlightenment, times(1)).findEscapePath(null, "U");

    }
}
