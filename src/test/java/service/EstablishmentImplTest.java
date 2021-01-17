package service;

import com.legacy.service.Escape;
import com.legacy.service.EstablishmentImpl;
import com.legacy.util.Helper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EstablishmentImplTest {
    Helper helper = new Helper();

    Escape establishment;
    String[][] room;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        room = new String[][]{{"O", "O", "O", "X"}, {"G", "O", "O", "E"}, {"O", "B", "O", "O"}, {"X", "O", "G", "O"}};
        establishment = new EstablishmentImpl(helper);
    }

    @Test
    public void findEscapePathWithWin() throws IOException {
        establishment.findEscapePath(room, "URR");
        assertEquals(true, helper.isInputSequencePresentAndValid("URR"));
    }

    @Test
    public void findEscapePathWithLose() throws IOException {
        establishment.findEscapePath(room, "DR");
        assertEquals(true, helper.isInputSequencePresentAndValid("DR"));
    }

    @Test
    public void findEscapePathWithUndecided() throws IOException {
        establishment.findEscapePath(room, "D");
        assertEquals(true, helper.isInputSequencePresentAndValid("D"));
    }
}
