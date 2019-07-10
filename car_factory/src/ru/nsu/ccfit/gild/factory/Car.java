package ru.nsu.ccfit.gild.factory;

import java.util.UUID;

public class Car implements Detail {
    private Motor motor;
    private Body body;
    private Accessory accessory;
    private UUID ID = UUID.randomUUID();
    private String detName;

    @Override
    public String getName() {return detName;}

    public Car(Body body, Motor motor, Accessory accessory) {
           this.body = body;
           this.motor = motor;
           this.accessory = accessory;

           detName = "car";
    }

    public UUID getID() {
        return ID;
    }

    public UUID getBodyID() {
        return body.getID();
    }

    public UUID getAccessoryID() {
        return accessory.getID();
    }

    public UUID getMotorID() {
        return motor.getID();
    }
}