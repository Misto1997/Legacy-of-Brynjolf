package util;

import com.legacy.util.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoomTest {

    private Room room;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        room = new Room();

    }

    @Test
    public void getRoomBluePrintCorrectFileTest() throws IOException {
        room.getRoomBluePrint("room.txt");
    }

    @Test
    public void getRoomBluePrintFileNotFoundTest() {
        try {
            room.getRoomBluePrint("wrongFile.txt");
        } catch (IOException exception) {
            assertEquals("room.txt file not present under resource package", exception.getMessage());
        }
    }

    @Test
    public void getRoomBluePrintInvalidInputTest() {
        try {
            room.getRoomBluePrint("roomWithInvalidInput.txt");
        } catch (IOException exception) {
            assertEquals("room.txt does not contain valid inputs", exception.getMessage());
        }
    }

    @Test
    public void getRoomBluePrintEmptyFileTest() {
        try {
            room.getRoomBluePrint("EmptyRoom.txt");
        } catch (IOException exception) {
            assertEquals("room.txt file is empty! please pass valid file in n*n form", exception.getMessage());
        }
    }

    @Test
    public void getRoomBluePrintWithoutBrynjolfTest() {
        try {
            room.getRoomBluePrint("roomWithoutBrynjolf.txt");
        } catch (IOException exception) {
            assertEquals("wrong room structure provided! one brynjolf occurrence required", exception.getMessage());
        }
    }

    @Test
    public void getRoomBluePrintWithoutExitGateTest() {
        try {
            room.getRoomBluePrint("roomWithoutExitGate.txt");
        } catch (IOException exception) {
            assertEquals("wrong room structure provided! one exit gate occurrence required", exception.getMessage());
        }
    }
}
