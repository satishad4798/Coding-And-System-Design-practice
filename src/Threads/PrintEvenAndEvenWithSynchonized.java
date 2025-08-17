package Threads;

import java.util.Random;

public class PrintEvenAndEvenWithSynchonized {
    volatile static boolean isEvenTurn = false; // Flag to control turn
    static Random random = new Random();
    static Object lockObject = new Object(); // Using an object as a lock

    public static void main(String[] args) {


        Thread t1 = new Thread(() -> {
            for (int i = 2; i <= 100; i += 2) {
                synchronized (lockObject) {
                    try {
                        while (!isEvenTurn) {
                            try {
                                lockObject.wait();
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                            }
                        }
                        Thread.sleep(random.nextInt(10));
                        System.out.println("Even Thread: " + i);
                        isEvenTurn = false;
                        lockObject.notifyAll();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }

            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 1; i <= 100; i += 2) {
                synchronized (lockObject) {
                    try {
                        while (isEvenTurn) {
                            try {
                                lockObject.wait();
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                            }
                        }
                        Thread.sleep(random.nextInt(10));
                        System.out.println("Odd Thread: " + i);
                        isEvenTurn = true;
                        lockObject.notifyAll();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }

            }
        });
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Main thread interrupted");
        }
    }
}