package com.legacy.service;

import java.io.IOException;

//parent interface for both escape options i.e Establishment and Enlightement
public interface Escape {
    void findEscapePath(String[][] room, String inputSequence) throws IOException;
}
