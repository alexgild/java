package ru.nsu.ccfit.gild.factory;

public class AccessoryWorker extends Worker<Accessory>{
    private final Producer[] producers;

    public AccessoryWorker(Initialization initialization, int delay) {
        super(AccessoryWorker.class.getSimpleName(), initialization.getSuppliersCount(),
                initialization.getAccessoryStorage(), initialization);

        producers = new Producer[initialization.getSuppliersCount()];

        for(int i = 0; i < initialization.getSuppliersCount(); ++i){
            pool.runTask(producers[i] = new Producer<>(storage, "Accessory", delay));
        }
    }

    @Override
    public int getProductionCount() {
        int count = 0;
        for(Producer<Accessory> producer : producers)
        {
            count += producer.getDetailCount();
        }
        return count;
    }
}
