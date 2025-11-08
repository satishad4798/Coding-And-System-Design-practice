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

        iphoneObservable.subscribe(o1);
        iphoneObservable.subscribe(o2);
        iphoneObservable.subscribe(o3);
        iphoneObservable.subscribe(o4);
        iphoneObservable.unsubscribe(o3);

        iphoneObservable.getStockCount();
        
        iphoneObservable.setStockCount(12);
        iphoneObservable.setStockCount(-5);
        iphoneObservable.setStockCount(43);


    }


}
