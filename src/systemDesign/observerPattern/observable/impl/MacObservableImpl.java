package systemDesign.observerPattern.observable.impl;

import systemDesign.observerPattern.observable.StockObservable;
import systemDesign.observerPattern.observer.NotificationAlertObserver;

import java.util.ArrayList;
import java.util.List;

public class MacObservableImpl implements StockObservable {

    static int macCount = 0;
    List<NotificationAlertObserver> observerList = new ArrayList<>();

    @Override
    public void add(NotificationAlertObserver observer) {
        observerList.add(observer);

    }

    @Override
    public void remove(NotificationAlertObserver observer) {
        observerList.add(observer);
    }

    @Override
    public void notifySubscriber() {

        observerList.forEach(each -> each.update());
    }

    @Override
    public int getStockCount() {
        return macCount;
    }

    @Override
    public void setStockCount(int stockCount) {

        //was it outOffStock stock before ? yes notify
        if (macCount == 0) {
            notifySubscriber();
        }
        macCount = macCount + stockCount;

    }
}
