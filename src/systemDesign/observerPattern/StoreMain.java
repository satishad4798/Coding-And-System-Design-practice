package systemDesign.observerPattern;

import systemDesign.observerPattern.observable.StockObservable;
import systemDesign.observerPattern.observable.impl.IphoneObservableImpl;
import systemDesign.observerPattern.observer.NotificationAlertObserver;
import systemDesign.observerPattern.observer.impl.EmailNotificationAlertObserverImpl;
import systemDesign.observerPattern.observer.impl.SmsNotificationAlertObserverImpl;

public class StoreMain {


    public static void main(String[] arg) {
        StockObservable iphoneObservable = new IphoneObservableImpl();

        NotificationAlertObserver o1 = new EmailNotificationAlertObserverImpl("satish", iphoneObservable);
        NotificationAlertObserver o2 = new EmailNotificationAlertObserverImpl("sam", iphoneObservable);
        NotificationAlertObserver o3 = new SmsNotificationAlertObserverImpl("99023", iphoneObservable);
        NotificationAlertObserver o4 = new SmsNotificationAlertObserverImpl("3434", iphoneObservable);

        iphoneObservable.add(o1);
        iphoneObservable.add(o2);
        iphoneObservable.add(o3);
        iphoneObservable.add(o4);

        iphoneObservable.setStockCount(12);
        iphoneObservable.setStockCount(-5);
        iphoneObservable.setStockCount(43);


    }


}
