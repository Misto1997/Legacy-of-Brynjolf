package com.legacy.Model;

import com.legacy.constant.Status;
import lombok.Data;

//this class will give Brynjolf's information at a given point
@Data
public class BrynjolfStatus {
    Status status;
    String sequence;

    public BrynjolfStatus(Status status, String sequence) {
        this.status = status;
        this.sequence = sequence;
    }
}
