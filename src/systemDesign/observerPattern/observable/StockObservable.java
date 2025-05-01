package systemDesign.observerPattern.observable;

import systemDesign.observerPattern.observer.NotificationAlertObserver;

public interface StockObservable {

    void add(NotificationAlertObserver observer);

    void remove(NotificationAlertObserver observer);

    void notifySubscriber();

    int getStockCount();

    void setStockCount(int stockCount);

}
