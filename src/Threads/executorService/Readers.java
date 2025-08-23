package Threads.executorService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Readers {

    public static void main(String[] args) {

        Semaphore s = new Semaphore(3);
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Thread t1 = new Thread(() -> {
                try {
                    s.acquire();
                    System.out.println(Thread.currentThread().getName() + " is reading..");
                    Thread.sleep(7000);
                    System.out.println(Thread.currentThread().getName() + " reading done");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    s.release();

                }
            });
            t1.start();
            threads.add(t1);
        }

        threads.forEach((t) -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

    }
}
