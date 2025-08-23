package lld.designPattern.behavioural.observerPattern.observable;

import lld.designPattern.behavioural.observerPattern.observer.Observer;

public interface Observable {

    void subscribe(Observer observer);

    void unsubscribe(Observer observer);

    void notifySubscriber();

    int getStockCount();

    void setStockCount(int stockCount);

}
