package ru.nsu.ccfit.gild.factory;

import java.util.LinkedList;

public class Storage<T extends Detail> {
    final LinkedList<T> availableDetails;
    private int fullness = 0;
    private final int capacity;
    private int totalCount;
    private Controller listener;
    public Storage(int capacity) {
        this.capacity = capacity;
        availableDetails = new LinkedList<>();
    }


    public T peek() {
        try {
            return availableDetails.peek();
        }
        catch (NullPointerException e){
            System.out.println("The storage is empty");
            return null;
        }
    }

    public int getFullness() {
        synchronized (availableDetails) {
            return fullness;
        }
    }

    public int getTotalCount() {return totalCount;}

    public void put(T detail) throws InterruptedException {
        synchronized (availableDetails){
            while (fullness == capacity)
                    availableDetails.wait();

            totalCount++;
            fullness++;
            availableDetails.add(detail);
            availableDetails.notifyAll();
        }
    }

    public T get() throws InterruptedException {
        T detail;
        synchronized (availableDetails) {
            while (availableDetails.isEmpty())
                    availableDetails.wait();

            fullness--;
            detail = availableDetails.remove();
            System.out.println("Get " + detail.getName() + ", ID: " + detail.getID());
            availableDetails.notifyAll();
        }
        return detail;
    }

    public void setListener(Controller listener) {
        this.listener = listener;
    }
}
