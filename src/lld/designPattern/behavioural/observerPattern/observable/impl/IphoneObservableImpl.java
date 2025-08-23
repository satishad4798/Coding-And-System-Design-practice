package lld.designPattern.behavioural.observerPattern.observable.impl;

import lld.designPattern.behavioural.observerPattern.observable.Observable;
import lld.designPattern.behavioural.observerPattern.observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class IphoneObservableImpl implements Observable {

    static int iphoneCount = 0;
    List<Observer> observerList = new ArrayList<>();

    @Override
    public void subscribe(Observer observer) {
        observerList.add(observer);

    }

    @Override
    public void unsubscribe(Observer observer) {
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
