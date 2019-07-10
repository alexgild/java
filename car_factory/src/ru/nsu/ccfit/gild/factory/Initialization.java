package ru.nsu.ccfit.gild.factory;

import java.io.FileNotFoundException;

public class Initialization {
    private final ConfigFileReader fileRead;
    private final Storage<Body> bodyStorage;
    private final Storage<Motor> motorStorage;
    private final Storage<Accessory> accessoryStorage;
    private final MainStorage autoStorage;
//    private volatile int bodyDelay;
//    private volatile int motorDelay;
//    private volatile int accessoryDelay;
//    private volatile int dealerDelay;

//    public void setBodyDelay(int bodyDelay) {
//        this.bodyDelay = bodyDelay;
//    }
//
//    public void setMotorDelay(int motorDelay) {
//        this.motorDelay = motorDelay;
//    }
//
//    public void setAccessoryDelay(int accessoryDelay) {
//        this.accessoryDelay = accessoryDelay;
//    }
//
//    public void setDealerDelay(int dealerDelay) {
//        this.dealerDelay = dealerDelay;
//    }

    public ConfigFileReader getFileRead() {
        return fileRead;
    }

    public Storage<Body> getBodyStorage() {
        return bodyStorage;
    }

    public Storage<Motor> getMotorStorage() {
        return motorStorage;
    }

    public Storage<Accessory> getAccessoryStorage() {
        return accessoryStorage;
    }

    public MainStorage getAutoStorage() {
        return autoStorage;
    }

    public int getWorkerCount() {
        return fileRead.getCountByName("workers");
    }

    public int getDealerCount() {
        return fileRead.getCountByName("dealers");
    }

    public int getSuppliersCount() {
        return fileRead.getCountByName("suppliers");
    }

//    public int getBodyDelay() {
//        return bodyDelay;
//    }
//
//    public int getMotorDelay() {
//        return motorDelay;
//    }
//
//    public int getAccessoryDelay() {
//        return accessoryDelay;
//    }
//
//    public int getDealerDelay () {
//        return dealerDelay;
//    }

    public Initialization() throws FileNotFoundException {
        fileRead = new ConfigFileReader("C:\\Users\\hiltchen\\repos\\oop_gild_16207\\Java\\car_factory\\src\\sample\\configFile.txt");
        bodyStorage = new Storage<>(fileRead.getCountByName("body"));
        motorStorage = new Storage<>(fileRead.getCountByName("motor"));
        accessoryStorage = new Storage<>(fileRead.getCountByName("accessory"));
        autoStorage = new MainStorage(fileRead.getCountByName("auto"));
//        bodyDelay = 100;
//        motorDelay = 100;
//        accessoryDelay = 50;
//        dealerDelay = 100;
    }
}
