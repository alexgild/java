package ru.nsu.ccfit.gild.ThreadPool;

import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

public class ThreadPool {
    private final int poolSize;
    private final PooledThread[] pool;
    private final ThreadGroup group;

    public ThreadPool(String name, int size) {
        group = new ThreadGroup(ThreadPool.class.getSimpleName() + name);
        this.poolSize = size;
        pool = new PooledThread[poolSize];

        for(int i = 0; i < poolSize; ++i) {
            pool[i] = new PooledThread(group, i);
            System.out.println("Thread " + name + " №" + i + " added to pool");
        }
    }

    public int getPoolSize() {return poolSize;}

    public void runTask(Task task) {
        PooledThread thread = null;
        synchronized (group) {
            while(thread == null) {
                for(PooledThread currentThread : pool) {
                    if(currentThread.isFree()) {
                        thread = currentThread;
                        break;
                    }
                }

                if(thread == null) {
                    try {
                        group.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            thread.setTask(task);
        }
    }

    public void stopAll() throws InterruptedException {
        group.interrupt();
            for (PooledThread thread : pool) {
                    thread.join();
            }
    }
}