package com.legacy.service;

import com.legacy.util.Room;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Driver {

    private static final Logger logger = LoggerFactory.getLogger(Driver.class);
    Room room;
    EstablishmentImpl establishment;
    EnlightenmentImpl enlightenment;

    public Driver(Room room, EstablishmentImpl establishment, EnlightenmentImpl enlightenment) {
        this.room = room;
        this.establishment = establishment;
        this.enlightenment = enlightenment;
    }

    public int getChoice() {
        Scanner input = new Scanner(System.in);
        return input.nextInt();
    }

    public void runDriver(String[] args) {
        try {
            Escape escape;
            String[][] roomBluePrint = room.getRoomBluePrint("room.txt");
            String inputSequence = "";
            if (args.length != 0)
                inputSequence = args[0];

            logger.info("Enter your choice(1 or 2):\n 1. Establishment \n 2. Enlightenment");

            int choice = getChoice();
            if (choice == 1) {
                escape = establishment;
                escape.findEscapePath(roomBluePrint, inputSequence);
            } else if (choice == 2) {
                escape = enlightenment;
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

