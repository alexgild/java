package ru.nsu.ccfit.gild.factory;

import ru.nsu.ccfit.gild.ThreadPool.Task;

public class CarMaker implements Task {
    private final MainStorage autoStorage;
    private final Storage<Body> bodyStorage;
    private final Storage<Motor> motorStorage;
    private final Storage<Accessory> accessoryStorage;
    Initialization initialization;
    private volatile boolean available;

    CarMaker(Initialization initialization) {
        this.initialization = initialization;
        autoStorage = initialization.getAutoStorage();
        bodyStorage = initialization.getBodyStorage();
        motorStorage = initialization.getMotorStorage();
        accessoryStorage = initialization.getAccessoryStorage();
    }

    private Car newCar() throws InterruptedException {
        available = true;
        Body body = bodyStorage.get();
        Motor motor = motorStorage.get();
        Accessory accessory = accessoryStorage.get();
        System.out.println("Preparing details to make a car ...");
        return new Car(body, motor, accessory);
    }

    public boolean isAvailable() { return available; }

    @Override
    public void run() {
        System.out.println("Car worker started.");
        available = true;
        Car car = null;
        try {
            car = newCar();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assert car != null;
        System.out.println("**********New car, ID: " + car.getID() + " has been created!**********");
        available = false;
        try {
            autoStorage.put(car);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Car worker stopped.");
    }
}