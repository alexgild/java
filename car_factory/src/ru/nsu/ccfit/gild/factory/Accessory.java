package ru.nsu.ccfit.gild.factory;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Accessory implements Detail {
    private UUID ID = UUID.randomUUID();
    private String detName;

    @Override
    public String getName() {return detName;}

    Accessory(int delay) throws InterruptedException {
        TimeUnit.MICROSECONDS.sleep(delay);
        detName = "accessory";
    }

    @Override
    public UUID getID() {
        return ID;
    }
}
