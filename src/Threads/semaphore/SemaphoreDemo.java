package Threads.semaphore;

import java.util.concurrent.Semaphore;

class Worker implements Runnable {
    private final Semaphore semaphore;

    public Worker(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " waiting for permit...");
            semaphore.acquire(); // take a permit
            System.out.println(Thread.currentThread().getName() + " got permit. Working...");

            Thread.sleep(2000); // simulate work

            System.out.println(Thread.currentThread().getName() + " releasing permit...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release(); // give back permit
        }
    }
}

public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2); // only 2 permits at a time

        for (int i = 1; i <= 5; i++) {
            new Thread(new Worker(semaphore), "Thread-" + i).start();
        }
    }
}
