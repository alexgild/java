package ru.nsu.ccfit.gild.factory;

public class Controller extends Thread  {

    private final CarWorker carWorker;
    private volatile boolean update;

    public Controller(CarWorker carWorker) {
        this.carWorker = carWorker;
        System.out.println("Storage controller start");
        this.start();
    }

    void update(MainStorage storage) {
        synchronized (storage) {
            update = true;
            storage.notify();
        }
    }

    @Override
    public void run() {
        try {
            while(true) {
                for (int i = 0; i < carWorker.getWorkersCount(); ++i) {
                    carWorker.workerSearch();
                }
                if(!update) {
                    synchronized (carWorker) {
                        carWorker.wait();
                    }
                }
                update = false;
            }

        } catch (InterruptedException e) {
            System.out.println("Interrupted during waiting for making a car.");
        }
    }
}


