package Threads;

import java.util.ArrayList;
import java.util.List;


class MainParking {
    public static void main(String[] args) throws InterruptedException {
        ParkingLotWithThreads parkingLot = new ParkingLotWithThreads(3);
        List<Thread> threds = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String carNumber = "Car " + i;

            Runnable car = () -> {

                try {
                    parkingLot.enterParkingLot(carNumber);
                    Thread.sleep(2000);
                    parkingLot.exitParkingLot(Thread.currentThread().getName());
                } catch (Exception e) {
                    // TODO: handle exception
                    Thread.currentThread().interrupt();
                }

            };

            Thread t = new Thread(car);
            t.start();
            threds.add(t);

        }
        threds.forEach(t -> {
            try {
                t.join();
            } catch (Exception e) {
                // TODO: handle exception
            }
        });

    }


}


public class ParkingLotWithThreads {


    static int totalAvailableSpots;

    ParkingLotWithThreads(int totalSlots) {
        totalAvailableSpots = totalSlots;
    }

    synchronized void enterParkingLot(String carNumber) throws InterruptedException {
        while (totalAvailableSpots <= 0) {
            System.out.println(carNumber + " is waiting for a parking spot");
            wait();
        }
        totalAvailableSpots--;
        System.out.println(carNumber + " parked successfully");


    }

    synchronized void exitParkingLot(String carNumber) {
        System.out.println(carNumber + " has left");
        totalAvailableSpots++;
        notify();
    }
    // This method should increment the available spots, print "<car> has left. Available spots: <number>", and then notify waiting cars.
}

