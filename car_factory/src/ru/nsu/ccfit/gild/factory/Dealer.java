package ru.nsu.ccfit.gild.factory;

import ru.nsu.ccfit.gild.ThreadPool.Task;

import java.util.LinkedList;

public class Dealer implements Task {
    private Storage<Car> mainStorage;
    private LinkedList<Car> takenCar;
    private int number;
    Initialization initialization;
    private int dealerTime;

    Dealer(Storage<Car> newMainStorage, Initialization initialization, int delay, int number) {
        this.mainStorage = newMainStorage;
        this.initialization = initialization;
        dealerTime = delay;
        takenCar = new LinkedList<>();
        this.number = number;
    }

    public void setDealerTime(int time) {
        dealerTime = time;
    }

    @Override
    public void run() {
        System.out.println("Dealer started.");
        while(true) {
            try {
                takenCar.add(mainStorage.get());
                System.out.println("Dealer " + number + ": Auto ID:" + takenCar.peek().getID() +
                        "(Body ID:" + takenCar.peek().getBodyID() + ", Motor ID:" + takenCar.peek().getMotorID() +
                        ", Accessory ID:" + takenCar.peek().getAccessoryID().toString() + ")");
                Thread.sleep(dealerTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("Dealer stopped.");
            }
        }
    }

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    public int getProductionCount() {
        return takenCar.size();
    }
}
