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
        if (stream == null)
            throw new IOException("room.txt file not present under resource package");
        BufferedReader buffer = new BufferedReader(new InputStreamReader(stream));
        List<String> validInputValues = Arrays.asList("X", "O", "E", "G", "B");
        String[][] room = null;
        String line;
        int row = 0;
        int size = 0;
        while ((line = buffer.readLine()) != null) {
            String[] inputValue = line.split(" ");
            if (!validInputValues.containsAll(Arrays.asList(inputValue))) {
                throw new IOException("room.txt does not contain valid inputs");
            }
            if (room == null) {
                size = inputValue.length;
                room = new String[size][size];
            }
            System.arraycopy(inputValue, 0, room[row], 0, size);
            row++;
        }
        if (room == null)
            throw new IOException("room.txt file is empty! please pass valid file in n*n form");
        return room;
    }
}
