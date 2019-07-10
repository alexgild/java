package ru.nsu.ccfit.gild.ThreadPool;

public interface Task extends Runnable {
    String name = null;

    default String getName() {
        return name;
    }

    void run();
}
