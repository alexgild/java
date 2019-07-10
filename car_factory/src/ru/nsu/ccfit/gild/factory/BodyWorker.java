package ru.nsu.ccfit.gild.factory;

public class BodyWorker extends Worker {
    private final Producer<Body> producer;

    public BodyWorker(Initialization initialization, int delay) {
        super(BodyWorker.class.getSimpleName(),1, initialization.getBodyStorage(), initialization);
        pool.runTask(producer = new Producer<>(storage, "Body", delay));
    }

    @Override
    public int getProductionCount() {return producer.getDetailCount();}
}
