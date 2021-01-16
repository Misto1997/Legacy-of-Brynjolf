package com.legacy.util;

import com.legacy.Model.AllPositionWrapper;
import com.legacy.Model.BrynjolfStatus;
import com.legacy.Model.Position;
import com.legacy.constant.Direction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import static com.legacy.constant.Status.*;

//Helper class to provide some common feature's
public class Helper {
    static String[][] all4Direction = {{"-1", "1", "0", "0"}, {"0", "0", "-1", "1"}, {"U", "D", "L", "R"}};

    public boolean isInputSequencePresentAndValid(String inputSequence) throws IOException {
        List<String> validInputValues = Arrays.asList("U", "D", "L", "R");
        if (!inputSequence.isEmpty()) {
            if (!validInputValues.containsAll(Arrays.asList(inputSequence.split(""))))
                throw new IOException("Invalid input! please enter valid input sequence(U,D,L,R)");
            else
                return true;
        }
        return false;
    }

    public boolean isGuardPresent(List<Position> allGuardPosition, Position brynjolfPosition) {
        return allGuardPosition.stream().anyMatch(guardPosition -> (guardPosition.getXAxis() == brynjolfPosition.getXAxis() && guardPosition.getYAxis() == brynjolfPosition.getYAxis()));
    }

    public void printRoomBluePrint(String[][] roomBluePrint) {
        for (String[] row : roomBluePrint) {
            for (String col : row) {
                System.out.print(col + " ");
            }
            System.out.println();
        }
    }

    public BrynjolfStatus getResultFollowedByInputSequence(String[][] room, String inputSequence) {
        AllPositionWrapper allPositionWrapper = new AllPositionWrapper(getAllGuardsPosition(room),
                getBrynjolfPosition(room), getExitGatePosition(room));
        BrynjolfStatus brynjolfStatus;
        String sequence = "";
        for (int i = 0; i < inputSequence.length(); i++) {
            char inputDirection = inputSequence.charAt(i);
            sequence += inputDirection;
            Direction direction = Enum.valueOf(Direction.class, "" + inputDirection);
            brynjolfStatus = move(room, direction, allPositionWrapper, sequence);
            if (brynjolfStatus != null && brynjolfStatus.getStatus() != FINDING)
                return brynjolfStatus;
        }
        return new BrynjolfStatus(UNDECIDED, inputSequence);
    }

    public List<Position> getAllGuardsPosition(String[][] room) {
        List<Position> allGuardsPosition = new ArrayList<>();
        for (int i = 0; i < room.length; i++) {
            for (int j = 0; j < room[0].length; j++) {
                if (room[i][j].equals("G")) {
                    allGuardsPosition.add(new Position(i, j));
                }
            }
        }
        return allGuardsPosition;
    }

    public Position getBrynjolfPosition(String[][] room) {
        Position brynjolfPosition = null;
        for (int i = 0; i < room.length; i++) {
            for (int j = 0; j < room[0].length; j++) {
                if (room[i][j].equals("B")) {
                    brynjolfPosition = new Position(i, j);
                }
            }
        }
        return brynjolfPosition;
    }

    public Position getExitGatePosition(String[][] room) {
        Position exitGatePosition = null;
        for (int i = 0; i < room.length; i++) {
            for (int j = 0; j < room[0].length; j++) {
                if (room[i][j].equals("E")) {
                    exitGatePosition = new Position(i, j);
                }
            }
        }
        return exitGatePosition;
    }

    private BrynjolfStatus move(String[][] room, Direction direction, AllPositionWrapper allPositionWrapper, String sequence) {
        switch (direction) {
            case U:
                return verifyAndMove(room, Integer.parseInt(all4Direction[0][0]), Integer.parseInt(all4Direction[1][0]), allPositionWrapper, sequence);
            case D:
                return verifyAndMove(room, Integer.parseInt(all4Direction[0][1]), Integer.parseInt(all4Direction[1][1]), allPositionWrapper, sequence);
            case L:
                return verifyAndMove(room, Integer.parseInt(all4Direction[0][2]), Integer.parseInt(all4Direction[1][2]), allPositionWrapper, sequence);
            case R:
                return verifyAndMove(room, Integer.parseInt(all4Direction[0][3]), Integer.parseInt(all4Direction[1][3]), allPositionWrapper, sequence);
        }
        return null;
    }

    private BrynjolfStatus verifyAndMove(String[][] room, int nextXAxis, int nextYAxmis, AllPositionWrapper allPositionWrapper, String sequence) {
        if (isBrynjolfReachedExitGate(room, allPositionWrapper.getBrynjolfPosition(), nextXAxis, nextYAxmis, allPositionWrapper.getExitGatePosition())) {
            return new BrynjolfStatus(WIN, sequence);
        } else if (moveBrynjolf(room, nextXAxis, nextYAxmis, allPositionWrapper.getAllGuardsPosition(), allPositionWrapper.getBrynjolfPosition()) &&
                moveGuard(room, nextXAxis, nextYAxmis, allPositionWrapper.getAllGuardsPosition(), allPositionWrapper.getBrynjolfPosition())) {
            return new BrynjolfStatus(FINDING, sequence);
        }
        return new BrynjolfStatus(LOSE, sequence);
    }

    private boolean isBrynjolfReachedExitGate(String[][] room, Position brynjolfPosition, int x, int y, Position exitGatePosition) {
        Position nextPosition = new Position(brynjolfPosition.getXAxis() + x, brynjolfPosition.getYAxis() + y);
        if (exitGatePosition.getXAxis() == nextPosition.getXAxis() && exitGatePosition.getYAxis() == nextPosition.getYAxis()) {
            room[brynjolfPosition.getXAxis()][brynjolfPosition.getYAxis()] = "0";
            return true;
        }
        return false;
    }

    private boolean moveBrynjolf(String[][] room, int nextXAxis, int nextYAxis, List<Position> allGuardsPosition, Position brynjolfPosition) {
        Position brynjolfNextPosition = new Position(brynjolfPosition.getXAxis() + nextXAxis, brynjolfPosition.getYAxis() + nextYAxis);
        if (!isWallPresentCaseBrynjolf(room, brynjolfNextPosition)) {
            for (Position guardPosition : allGuardsPosition) {
                if (isGuardAndBrynjolfCollide(guardPosition, brynjolfNextPosition)) {
                    room[brynjolfPosition.getXAxis()][brynjolfPosition.getYAxis()] = "0";
                    return false;
                }
            }
            room[brynjolfPosition.getXAxis()][brynjolfPosition.getYAxis()] = "0";
            room[brynjolfNextPosition.getXAxis()][brynjolfNextPosition.getYAxis()] = "B";
            brynjolfPosition.setXAxis(brynjolfNextPosition.getXAxis());
            brynjolfPosition.setYAxis(brynjolfNextPosition.getYAxis());
        }
        return true;
    }

    private boolean moveGuard(String[][] room, int nextXAxis, int nextYAxis, List<Position> allGuardsPosition, Position brynjolfPosition) {
        for (Position guardPosition : allGuardsPosition) {
            Position guardNextPosition = new Position(guardPosition.getXAxis() + nextXAxis, guardPosition.getYAxis() + nextYAxis);
            if (!isWallExitGateOrAnotherGuardPresentForGuard(room, guardNextPosition)) {
                room[guardPosition.getXAxis()][guardPosition.getYAxis()] = "0";
                room[guardNextPosition.getXAxis()][guardNextPosition.getYAxis()] = "G";
                guardPosition.setXAxis(guardNextPosition.getXAxis());
                guardPosition.setYAxis(guardNextPosition.getYAxis());
                if (isGuardAndBrynjolfCollide(guardNextPosition, brynjolfPosition)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isWallPresentCaseBrynjolf(String[][] room, Position position) {
        if (position.getXAxis() >= room.length || position.getYAxis() >= room.length ||
                position.getXAxis() < 0 || position.getYAxis() < 0)
            return true;
        return room[position.getXAxis()][position.getYAxis()].equals("X");
    }

    private boolean isWallPresentCaseGuard(String[][] room, Position position) {
        if (position.getXAxis() >= room.length || position.getYAxis() >= room.length ||
                position.getXAxis() < 0 || position.getYAxis() < 0)
            return true;
        return room[position.getXAxis()][position.getYAxis()].equals("X") || room[position.getXAxis()][position.getYAxis()].equals("E");
    }

    private boolean isWallExitGateOrAnotherGuardPresentForGuard(String[][] room, Position position) {
        if (position.getXAxis() >= room.length || position.getYAxis() >= room.length ||
                position.getXAxis() < 0 || position.getYAxis() < 0)
            return true;
        return room[position.getXAxis()][position.getYAxis()].equals("X") || room[position.getXAxis()][position.getYAxis()].equals("E") || room[position.getXAxis()][position.getYAxis()].equals("G");
    }

    public List<Position> getAllGuardNextPosition(String[][] room, List<Position> allGuardPosition, int x, int y) {
        List<Position> newAllGuardPosition = new ArrayList<>();
        Stack<Position> revisitRequiredForGuard = new Stack<>();
        for (Position guardCurrentPosition : allGuardPosition) {
            Position guardNextPosition = new Position(guardCurrentPosition.getXAxis() + x, guardCurrentPosition.getYAxis() + y);
            if (!isWallPresentCaseGuard(room, guardNextPosition)) {
                if (checkIfGuardInNextPosition(allGuardPosition, guardNextPosition))
                    revisitRequiredForGuard.push(guardCurrentPosition);
                else
                    newAllGuardPosition.add(guardNextPosition);
            } else {
                newAllGuardPosition.add(guardCurrentPosition);
            }
        }
        while (!revisitRequiredForGuard.isEmpty()) {
            Position guardCurrentPosition = revisitRequiredForGuard.pop();
            Position guardNextPosition = new Position(guardCurrentPosition.getXAxis() + x, guardCurrentPosition.getYAxis() + y);
            if (checkIfGuardInNextPosition(newAllGuardPosition, guardNextPosition))
                newAllGuardPosition.add(guardCurrentPosition);
            else
                newAllGuardPosition.add(guardNextPosition);
        }
        return newAllGuardPosition;
    }

    private boolean isGuardAndBrynjolfCollide(Position guardPosition, Position brynjolfPosition) {
        return guardPosition.getXAxis() == brynjolfPosition.getXAxis() && guardPosition.getYAxis() == brynjolfPosition.getYAxis();
    }

    private boolean checkIfGuardInNextPosition(List<Position> allGuardsPosition, Position nextPosition) {
        return allGuardsPosition.stream().anyMatch(guardPosition -> guardPosition.getXAxis() == nextPosition.getXAxis() && guardPosition.getYAxis() == nextPosition.getYAxis());
    }
}
