package lld.designPattern.behavioural.observerPattern.observable.impl;

import lld.designPattern.behavioural.observerPattern.observable.Observable;
import lld.designPattern.behavioural.observerPattern.observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class MacObservableImpl implements Observable {

    static int macCount = 0;
    List<Observer> observerList = new ArrayList<>();

    @Override
    public void add(Observer observer) {
        observerList.add(observer);

    }

    @Override
    public void remove(Observer observer) {
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
