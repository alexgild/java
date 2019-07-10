package ru.nsu.ccfit.gild.ThreadPool;

class PooledThread extends Thread {
    private final ThreadGroup group;
    private final Object mutex = new Object();

    private volatile boolean free = true;
    private volatile boolean interrupted = false;
    private Task task;


    PooledThread(ThreadGroup group, int number) {
        super(group, PooledThread.class.getSimpleName() + number);
        this.group = group;
        this.start();
    }

    public boolean isFree() {return free;}

    public void setTask(Task newTask) {
        synchronized (mutex) {
            task = newTask;
            free = false;
            mutex.notify();
        }
    }

    @Override
    public void run() {
        while (true) {
            synchronized (mutex) {
                while (this.free) {
                    try {
                        mutex.wait();
                    } catch (InterruptedException e) {
                        System.out.println("Interrupting while waiting of elements in the task queue");
                    }
                }

                System.out.println("Task " + task.getClass().getSimpleName() + " start running");
                task.run();
                try {
                    synchronized (group) {
                       free = true;
                        if(interrupted) {
                            return;
                        }

                        group.notify();
                    }
                } catch (RuntimeException e) {
                    System.out.println("Task execution error. Thread pool was interrupted");
                }
            }
        }

    }
}