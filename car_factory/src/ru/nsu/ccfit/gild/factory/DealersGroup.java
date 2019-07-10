package ru.nsu.ccfit.gild.factory;

public class DealersGroup extends Worker<Car> {
    private final Dealer[] dealers;

    public DealersGroup(Initialization initialization, int delay) {
        super("Cars dealing", initialization.getDealerCount(), initialization.getAutoStorage(), initialization);

        dealers = new Dealer[initialization.getDealerCount()];

        for(int i = 0; i < initialization.getDealerCount(); ++i) {
            pool.runTask(dealers[i] = new Dealer(storage, initialization, delay, i));
        }
    }

    @Override
    public int getProductionCount () {
        int count = 0;
        for (Dealer dealer : dealers) {
            count += dealer.getProductionCount();
        }
        return count;
    }
}
