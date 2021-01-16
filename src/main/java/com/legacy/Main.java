package com.legacy;

import com.legacy.service.EnlightenmentImpl;
import com.legacy.service.Escape;
import com.legacy.service.EstablishmentImpl;
import com.legacy.util.Room;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        try {
            Escape escape;
            Room room = new Room();
            Scanner input = new Scanner(System.in);
            String[][] roomBluePrint = room.getRoomBluePrint("room.txt");
            String inputSequence = "";
            if (args.length != 0)
                inputSequence = args[0];

            logger.info("Enter your choice(1 or 2):\n 1. Establishment \n 2. Enlightenment");

            int choice = input.nextInt();
            if (choice == 1) {
                escape = new EstablishmentImpl();
                escape.findEscapePath(roomBluePrint, inputSequence);
            } else if (choice == 2) {
                escape = new EnlightenmentImpl();
                escape.findEscapePath(roomBluePrint, inputSequence);
            } else {
                throw new InputMismatchException("invalid input entered! please enter a valid input(1 or 2)");
            }

        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        } catch (InputMismatchException inputMismatchException) {
            logger.error("invalid input entered! please enter a valid input(1 or 2)");
        } catch (Exception exception) {
            logger.error("Something went wrong....");
        }
    }
}
