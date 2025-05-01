package systemDesign.observerPattern.observer.impl;

import systemDesign.observerPattern.observable.StockObservable;
import systemDesign.observerPattern.observer.NotificationAlertObserver;

public class SmsNotificationAlertObserverImpl implements NotificationAlertObserver {

    String mobileNumber;
    StockObservable stockObservable;

    public SmsNotificationAlertObserverImpl(String mobileNumber, StockObservable observer) {
        this.mobileNumber = mobileNumber;
        this.stockObservable = observer;
    }

    @Override
    public void update() {
        sendNotification(mobileNumber);
    }

    private void sendNotification(String email) {
        System.out.println("notification sent " + mobileNumber);
    }
}
