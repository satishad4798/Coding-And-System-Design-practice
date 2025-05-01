package systemDesign.observerPattern.observer.impl;

import systemDesign.observerPattern.observable.StockObservable;
import systemDesign.observerPattern.observer.NotificationAlertObserver;

public class EmailNotificationAlertObserverImpl implements NotificationAlertObserver {

    String email;
    StockObservable stockObservable;

    public EmailNotificationAlertObserverImpl(String email, StockObservable stockObservable) {
        this.email = email;
        this.stockObservable = stockObservable;
    }

    @Override
    public void update() {
        sendEmail(email);

    }

    private void sendEmail(String email) {
        System.out.println("email  sent to " + email + "product is in stock");
    }
}
