package systemDesign.observerPattern.observable.impl;

import systemDesign.observerPattern.observable.StockObservable;
import systemDesign.observerPattern.observer.NotificationAlertObserver;

import java.util.ArrayList;
import java.util.List;

public class IphoneObservableImpl implements StockObservable {

    static int iphoneCount = 0;
    List<NotificationAlertObserver> observerList = new ArrayList<>();

    @Override
    public void add(NotificationAlertObserver observer) {
        observerList.add(observer);

    }

    @Override
    public void remove(NotificationAlertObserver observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifySubscriber() {

        observerList.forEach(each -> {
            each.update();
        });

    }

    @Override
    public int getStockCount() {
        return iphoneCount;
    }

    @Override
    public void setStockCount(int stockCount) {

        //was it outOffStock stock before ? yes notify
        if (iphoneCount == 0 && stockCount > 0) {
            notifySubscriber();
        }
        iphoneCount = iphoneCount + stockCount;

    }
}
