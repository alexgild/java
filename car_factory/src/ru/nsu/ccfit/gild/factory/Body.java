package ru.nsu.ccfit.gild.factory;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Body implements Detail{
    private UUID ID = UUID.randomUUID();
    private String detName;

    @Override
    public String getName() {return detName;}

    Body(int delay) throws InterruptedException {
        TimeUnit.MICROSECONDS.sleep(delay);
        detName = "body";
    }

    @Override
    public UUID getID() {
        return ID;
    }
}
