package Threads;// Multithreading and Concurrency in Java - Hands-on Examples

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultithreadingBasics {
    public static void main(String[] args) {
        // Example 1: Creating Threads by extending Thread class
        Thread thread1 = new MyThread();
        thread1.start();

        // Example 2: Creating Threads by implementing Runnable
        Runnable task = () -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Runnable Thread: " + i);
            }
        };
        Thread thread2 = new Thread(task, "Runnable Thread");
        thread2.start();

        // Example 3: Using ExecutorService
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(() -> System.out.println("Task 1 from Executor"));
        executor.submit(() -> System.out.println("Task 2 from Executor"));
        executor.shutdown();
    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("Thread subclass: " + i);
        }
    }
}
