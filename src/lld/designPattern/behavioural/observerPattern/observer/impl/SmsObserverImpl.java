package lld.designPattern.behavioural.observerPattern.observer.impl;

import lld.designPattern.behavioural.observerPattern.observable.Observable;
import lld.designPattern.behavioural.observerPattern.observer.Observer;

public class SmsObserverImpl implements Observer {

    String mobileNumber;
    Observable observable;

    public SmsObserverImpl(String mobileNumber, Observable observer) {
        this.mobileNumber = mobileNumber;
        this.observable = observer;
    }

    @Override
    public void update() {
        sendNotification(mobileNumber);
    }

    private void sendNotification(String email) {
        System.out.println("notification sent " + mobileNumber);
    }
}
