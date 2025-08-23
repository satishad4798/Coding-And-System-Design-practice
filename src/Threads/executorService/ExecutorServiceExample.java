package Threads.executorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceExample {

    public static void main(String[] args) {


        ExecutorService es = Executors.newFixedThreadPool(3);


        for (int i = 0; i < 10; i++) {
            es.submit(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + " started");
                    Thread.sleep(7000);
                    System.out.println("Operation successful");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }, "Thread" + (i + 1));

        }

        es.shutdown();
    }
}
