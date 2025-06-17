package lld.designPattern.behavioural.observerPattern.observable;

import lld.designPattern.behavioural.observerPattern.observer.Observer;

public interface Observable {

    void add(Observer observer);

    void remove(Observer observer);

    void notifySubscriber();

    int getStockCount();

    void setStockCount(int stockCount);

}
