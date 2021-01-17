package com.legacy.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class Room {
    //This method will return room blueprint as 2D array
    public String[][] getRoomBluePrint(String fileName) throws IOException {
        InputStream stream = ClassLoader.getSystemResourceAsStream(fileName);
        checkIfRoomTxtFilePresent(stream);
        BufferedReader buffer = new BufferedReader(new InputStreamReader(stream));
        String[][] room = null;
        String line;
        int row = 0;
        int size = 0;
        while ((line = buffer.readLine()) != null) {
            String[] inputValue = line.split(" ");
            checkValidInput(inputValue);
            if (room == null) {
                size = inputValue.length;
                room = new String[size][size];
            }
            System.arraycopy(inputValue, 0, room[row], 0, size);
            row++;
        }
        checkIfRoomIsEmpty(room);
        checkValidOccurrence(room);
        return room;
    }

    private void checkIfRoomTxtFilePresent(InputStream stream) throws IOException {
        if (stream == null)
            throw new IOException("room.txt file not present under resource package");
    }

    private void checkValidInput(String[] inputValue) throws IOException {
        List<String> validInputValues = Arrays.asList("X", "O", "E", "G", "B");
        if (!validInputValues.containsAll(Arrays.asList(inputValue))) {
            throw new IOException("room.txt does not contain valid inputs");
        }
    }

    private void checkIfRoomIsEmpty(String[][] room) throws IOException {
        if (room == null)
            throw new IOException("room.txt file is empty! please pass valid file in n*n form");
    }

    private void checkValidOccurrence(String[][] room) throws IOException {
        int brynjolfOccurrenceCount = 0;
        int exitGateOccurrenceCount = 0;
        for (String[] row : room) {
            for (String col : row) {
                if (col.equals("B"))
                    brynjolfOccurrenceCount++;
                else if (col.equals("E"))
                    exitGateOccurrenceCount++;
            }
        }
        if (brynjolfOccurrenceCount != 1)
            throw new IOException("wrong room structure provided! one brynjolf occurrence required");
        if (exitGateOccurrenceCount != 1)
            throw new IOException("wrong room structure provided! one exit gate occurrence required");
    }

}
