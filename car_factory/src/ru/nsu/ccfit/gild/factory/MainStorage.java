package ru.nsu.ccfit.gild.factory;

import java.util.LinkedList;

public class MainStorage extends Storage<Car> {
    private int fullness = 0;
    private int capacity;
    private int totalCount = 0;
    private volatile Controller listener = null;

    private LinkedList<Car> finishedCars = availableDetails;

    MainStorage(int newCapacity) {
        super(newCapacity);
    }

    public int getFullness() {
        return finishedCars.size();
    }
    public int getTotalCount() {return totalCount;}

    @Override
    public void put(Car car) throws InterruptedException {
            super.put(car);
            System.out.println("Add car, ID: " + car.getID() + "to Auto Storage");
            listener.update(this);
    }

    public Car peek() {
        return finishedCars.peek();
    }

    @Override
    public Car get() throws InterruptedException {
        Car car = super.get();
        System.out.println("Get car, ID: "  + car.getID() + " from Auto Storage");

        listener.update(this);
        return car;
    }

    public void setListener(Controller listener) {
        this.listener = listener;
    }
}