package ru.nsu.ccfit.gild.factory;

import ru.nsu.ccfit.gild.ThreadPool.Task;

public class Producer<T extends Detail> implements Task {
    private Storage<T> detailStorage;
    private int detailCount = 0;
    private String name;
    private int delay;

    private Detail createDetail() throws InterruptedException {
        return DetailFactory.createDetail(name, delay);
    }

    public int getDetailCount() {
        return detailCount;
    }

    Producer(Storage<T> newDetailStorage, String name, int delay) {
        this.detailStorage = newDetailStorage;
        this.name = name;
        this.delay = delay;
     }

    @Override
    public void run() {
        T detail;
        while (true) {
            try {
                System.out.println(name + " producer started.");
                detail = (T) createDetail();
                detailStorage.put(detail);
                System.out.println("Detail " + name + ", ID: " +
                        detail.getID() + " is on " + name + " Storage");
                detailCount++;
            } catch (InterruptedException e) {
                System.out.println(name + " producer interrupted.");
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getName() {
        return this.getClass().getName();
    }
}