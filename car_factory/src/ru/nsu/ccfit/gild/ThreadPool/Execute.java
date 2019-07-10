package ru.nsu.ccfit.gild.ThreadPool;

import ru.nsu.ccfit.gild.factory.*;

import java.io.FileNotFoundException;

public class Execute {

    private final Initialization initialize;
    private final Worker[] workers;
    private int bodyDelay;
    private int motorDelay;
    private int accessoryDelay;
    private int dealerDelay;

    public void setBodyDelay(int bodyDelay) {
        this.bodyDelay = bodyDelay;
    }

    public void setMotorDelay(int motorDelay) {
        this.motorDelay = motorDelay;
    }

    public void setAccessoryDelay(int accessoryDelay) {
        this.accessoryDelay = accessoryDelay;
    }

    public void setDealerDelay(int dealerDelay) {
        this.dealerDelay = dealerDelay;
    }

    public Execute() throws FileNotFoundException {
        initialize = new Initialization();
        final BodyWorker bodyWorker = new BodyWorker(initialize, bodyDelay);
        final MotorWorker motorWorker = new MotorWorker(initialize, motorDelay);
        final AccessoryWorker accessoryWorker = new AccessoryWorker(initialize, accessoryDelay);
        final CarWorker carWorker = new CarWorker(initialize);
        final DealersGroup dealersGroup = new DealersGroup(initialize, dealerDelay);

        workers = new Worker[]{bodyWorker, motorWorker, accessoryWorker, carWorker, dealersGroup};
    }

    public Initialization getInitialization() {
        return initialize;
    }

    public void stopFactory() throws InterruptedException {
        for(Worker worker : workers) {
            worker.stopStorage();
        }
        System.out.println("__________!!!!!THE FACTORY STOPPED!!!!!__________");
    }

//    public static void stopFactory() {
//
//    }
}
