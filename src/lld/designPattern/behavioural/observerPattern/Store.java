package lld.designPattern.behavioural.observerPattern;

import lld.designPattern.behavioural.observerPattern.observable.Observable;
import lld.designPattern.behavioural.observerPattern.observable.impl.IphoneObservableImpl;
import lld.designPattern.behavioural.observerPattern.observer.Observer;
import lld.designPattern.behavioural.observerPattern.observer.impl.EmailObserverImpl;
import lld.designPattern.behavioural.observerPattern.observer.impl.SmsObserverImpl;

public class Store {


    public static void main(String[] arg) {

        Observable iphoneObservable = new IphoneObservableImpl();

        Observer o1 = new EmailObserverImpl("satish", iphoneObservable);
        Observer o2 = new EmailObserverImpl("sam", iphoneObservable);
        Observer o3 = new SmsObserverImpl("99023", iphoneObservable);
        Observer o4 = new SmsObserverImpl("3434", iphoneObservable);

        iphoneObservable.add(o1);
        iphoneObservable.add(o2);
        iphoneObservable.add(o3);
        iphoneObservable.add(o4);

        iphoneObservable.setStockCount(12);
        iphoneObservable.setStockCount(-5);
        iphoneObservable.setStockCount(43);


    }


}
