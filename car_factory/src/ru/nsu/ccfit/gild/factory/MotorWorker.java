package ru.nsu.ccfit.gild.factory;

public class MotorWorker extends Worker {
    private final Producer<Motor> producer;

    public MotorWorker(Initialization initialization, int delay) {
        super(MotorWorker.class.getSimpleName(),1, initialization.getMotorStorage(), initialization);
        pool.runTask(producer = new Producer<>(storage, "Motor", delay));
    }

    @Override
    public int getProductionCount() {return producer.getDetailCount();}
}
