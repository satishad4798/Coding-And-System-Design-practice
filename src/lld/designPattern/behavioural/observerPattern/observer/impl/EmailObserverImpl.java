package lld.designPattern.behavioural.observerPattern.observer.impl;

import lld.designPattern.behavioural.observerPattern.observable.Observable;
import lld.designPattern.behavioural.observerPattern.observer.Observer;

public class EmailObserverImpl implements Observer {

    String email;
    Observable observable;

    public EmailObserverImpl(String email, Observable observable) {
        this.email = email;
        this.observable = observable;
    }

    @Override
    public void update() {
        sendEmail(email);

    }

    private void sendEmail(String email) {
        System.out.println("email  sent to " + email + "product is in stock");
    }
}
