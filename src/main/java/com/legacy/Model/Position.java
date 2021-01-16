package com.legacy.Model;

import lombok.Data;


//this class will give coordinates for object's in 2D array
@Data
public class Position {
    int xAxis;
    int yAxis;

    public Position(int xAxis, int yAxis) {
        this.xAxis = xAxis;
        this.yAxis = yAxis;
    }
}
