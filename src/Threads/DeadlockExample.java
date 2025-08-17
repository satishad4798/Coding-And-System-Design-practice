package Threads;

public class DeadlockExample {
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    public void methodOne() {
        synchronized (lock1) {
            System.out.println("Thread 1 acquired lock1");
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
            }
            synchronized (lock2) {
                System.out.println("Thread 1 acquired lock2");
            }
        }
    }

    public void methodTwo() {
        synchronized (lock2) {
            System.out.println("Thread 2 acquired lock2");
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
            }
            synchronized (lock1) {
                System.out.println("Thread 2 acquired lock1");
            }
        }
    }
}

class Main {

    public static void main(String[] args) throws InterruptedException {
        DeadlockExample deadlockExample = new DeadlockExample();
        Thread t1 = new Thread(() -> {
            deadlockExample.methodOne();
        });
        Thread t2 = new Thread(() -> {
            deadlockExample.methodTwo();
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

    }

}