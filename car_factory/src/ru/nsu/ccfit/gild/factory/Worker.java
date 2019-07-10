package ru.nsu.ccfit.gild.factory;

import ru.nsu.ccfit.gild.ThreadPool.ThreadPool;

public abstract class Worker<T extends Detail> {
    final ThreadPool pool;
    final Storage<T> storage;
    final Initialization initialization;
    final String name;

    public Worker(String name, int size, Storage<T> storage, Initialization initialization) {
        pool = new ThreadPool(name, size);
        this.storage = storage;
        this.initialization = initialization;
        this.name = name;
    }

    public void stopStorage() throws InterruptedException {
        System.out.println("/////////////////////////////" + name + " STOPPED \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\");
        pool.stopAll();
    }

    public int getWorkersCount() {return pool.getPoolSize();}

    public Storage<T> getStorage() {return storage;}

    public int getProductionCount() { return storage.getTotalCount();}
}
