package service;

import com.legacy.service.EnlightenmentImpl;
import com.legacy.service.Escape;
import com.legacy.util.Helper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnlightenmentImplTest {

    Helper helper = new Helper();

    Escape enlightenment;
    String[][] room;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        room = new String[][]{{"O", "O", "O", "X"}, {"G", "O", "O", "E"}, {"O", "B", "O", "O"}, {"X", "O", "G", "O"}};
        enlightenment = new EnlightenmentImpl(helper);
    }

    @Test
    public void findEscapePathInputSequenceWithWin() throws IOException {
        enlightenment.findEscapePath(room, "U");
        assertEquals(true, helper.isInputSequencePresentAndValid("U"));

    }

    @Test
    public void findEscapePathInputSequenceWithLose() throws IOException {
        enlightenment.findEscapePath(room, "DR");
        assertEquals(true, helper.isInputSequencePresentAndValid("DR"));

    }

    @Test
    public void findEscapePathInputSequenceWithStuck() throws IOException {
        enlightenment.findEscapePath(room, "D");
        assertEquals(true, helper.isInputSequencePresentAndValid("D"));

    }

    @Test
    public void findEscapePathWithoutInputSequenceWithWin() throws IOException {
        enlightenment.findEscapePath(room, "");
        assertEquals(false, helper.isInputSequencePresentAndValid(""));
    }
}

