package com.legacy.Model;

import lombok.Getter;

import java.util.List;

//this class will give all object's location
@Getter
public class AllPositionWrapper {

    List<Position> allGuardsPosition;
    Position brynjolfPosition;
    Position exitGatePosition;

    public AllPositionWrapper(List<Position> allGuardsPosition, Position brynjolfPosition, Position exitGatePosition) {
        this.allGuardsPosition = allGuardsPosition;
        this.brynjolfPosition = brynjolfPosition;
        this.exitGatePosition = exitGatePosition;
    }
}
