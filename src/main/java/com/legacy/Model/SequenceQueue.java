package com.legacy.Model;

import lombok.Data;

import java.util.List;

//this class will give brynjolf and guard's location in 2D array
@Data
public class SequenceQueue {
    Position brynjolfPosition;
    String winningSequence;
    List<Position> allGuardsPositionInstance;

    public SequenceQueue(Position brynjolfPosition, String winningSequence, List<Position> allGuardsPositionInstance) {
        this.brynjolfPosition = brynjolfPosition;
        this.winningSequence = winningSequence;
        this.allGuardsPositionInstance = allGuardsPositionInstance;
    }
}
