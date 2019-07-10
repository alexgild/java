package ru.nsu.ccfit.gild.factory;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Motor implements Detail {
    private UUID ID = UUID.randomUUID();
    private String detName;

    @Override
    public String getName() {return detName;}

    Motor(int delay) throws InterruptedException {
        TimeUnit.MICROSECONDS.sleep(delay);
        detName = "motor";
    }

    @Override
    public UUID getID() {
        return ID;
    }
}

