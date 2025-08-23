package Threads;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class RaceSimulation {

    public static final int race_trace_distance = 100;
    volatile static boolean winnerFound = false;

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(1);
        Random random = new Random();
        Object lock = new Object();
        Runnable racer = () -> {
            int distanceCovered = 0;
            try {
                latch.await();
                System.out.println("thread" + Thread.currentThread().getName() + "started race");
                while (distanceCovered < race_trace_distance && (!winnerFound)) {

                    int currentDistance = random.nextInt(10);
                    distanceCovered = distanceCovered + currentDistance;
                    System.out.println(Thread.currentThread().getName() + ":" + distanceCovered);
                    Thread.sleep(currentDistance);

                }
                if (distanceCovered >= race_trace_distance) {
                    synchronized (lock) {
                        if (!winnerFound) {
                            System.out.println(Thread.currentThread().getName() + " won the race");
                            winnerFound = true;
                        }
                    }
                }
            } catch (InterruptedException e) {

                //   System.out.println("thread interrupted");
            }
        };

        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(racer, "racer-" + (i + 1));
            t.start();
        }
        Thread.sleep(2000);
        latch.countDown();


    }
}
