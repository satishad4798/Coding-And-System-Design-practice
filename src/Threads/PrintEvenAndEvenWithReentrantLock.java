package Threads;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintEvenAndEvenWithReentrantLock {
    volatile static boolean isEvenTurn = false; // Flag to control turn
    static Lock lock = new ReentrantLock();
    static Random random = new Random();
    static Condition eventCondition = lock.newCondition();
    static Condition oddCondition = lock.newCondition();

    public static void main(String[] args) {


        Thread t1 = new Thread(() -> {
            for (int i = 2; i <= 100; i += 2) {
                // Wait for the even turn                lock.lock();
                try {

                    lock.lock();
                    while (!isEvenTurn) {
                        eventCondition.await();
                    }
                    // Random delay between 0-10 milliseconds
                    Thread.sleep(random.nextInt(10));
                    System.out.println("Even Thread: " + i);
                    isEvenTurn = false;
                } catch (InterruptedException e) {
                    System.out.println("Thread interrupted: " + e.getMessage());
                } finally {
                    oddCondition.signalAll();
                }
            }
        });


        Thread t2 = new Thread(() -> {
            for (int i = 1; i <= 100; i += 2) {
                // Wait for the even turn                lock.lock();
                try {

                    lock.lock();
                    while (isEvenTurn) {
                        oddCondition.await();
                    }
                    // Random delay between 0-10 milliseconds
                    Thread.sleep(random.nextInt(10));
                    System.out.println("odd Thread: " + i);
                    isEvenTurn = true;
                } catch (InterruptedException e) {
                    System.out.println("Thread interrupted: " + e.getMessage());
                } finally {
                    eventCondition.signalAll();
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