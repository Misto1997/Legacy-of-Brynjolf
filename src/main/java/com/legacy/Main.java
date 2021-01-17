package com.legacy;

import com.legacy.service.Driver;
import com.legacy.service.EnlightenmentImpl;
import com.legacy.service.EstablishmentImpl;
import com.legacy.util.Helper;
import com.legacy.util.Room;

public class Main {
    public static void main(String[] args) {
        Helper helper = new Helper();
        Driver driver = new Driver(new Room(), new EstablishmentImpl(helper), new EnlightenmentImpl(helper));
        driver.runDriver(args);
    }
}
