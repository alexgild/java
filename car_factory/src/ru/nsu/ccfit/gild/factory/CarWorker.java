package ru.nsu.ccfit.gild.factory;

public class CarWorker extends Worker<Car> {
    private final Controller controller;
    private final CarMaker[] carMakers;
    private final MainStorage storage;

    public CarWorker(Initialization initialization) {
        super("Car making", initialization.getWorkerCount(), null, initialization);
        storage = initialization.getAutoStorage();
        carMakers = new CarMaker[initialization.getWorkerCount()];
        for(int i = 0; i < initialization.getWorkerCount(); ++i) {
            carMakers[i] = new CarMaker(initialization);
        }
        controller = new Controller(this);
        storage.setListener(controller);
        controller.update(storage);
    }

    void workerSearch () {
        for (CarMaker maker : carMakers) {
            if(!maker.isAvailable()) {
                System.out.println("Worker is ready to make a car!");
                pool.runTask(maker);
                break;
            }
        }
    }

    @Override
    public void stopStorage() throws InterruptedException {
        controller.interrupt();
        try {
            controller.join();
        } catch (InterruptedException ignored) {}
        System.out.println("Auto storage has been stopped.");
        super.stopStorage();
    }

    @Override
    public int getProductionCount() {
        return storage.getTotalCount();
    }
}
