package Threads;

class MainClass {
    public static void main(String[] args) throws InterruptedException {

        int numPhilosophers = 5;
        Philosopher[] philosophers = new Philosopher[numPhilosophers];
        Object[] forks = new Object[numPhilosophers];
        Thread[] threads = new Thread[numPhilosophers];


        for (int i = 0; i < numPhilosophers; i++) {
            forks[i] = new Object();
        }

        // Initialize philosophers and start threads
        for (int i = 0; i < numPhilosophers; i++) {
            Object leftFork = forks[i];
            Object rightFork = forks[(i + 1) % numPhilosophers];
            philosophers[i] = new Philosopher(i + 1, leftFork, rightFork); // Philosopher IDs from 1 to 5
            threads[i] = new Thread(philosophers[i], "Philosopher " + (i + 1)); // Adjust thread names as well
            threads[i].start();
        }

        // Let the philosophers run for a while (e.g., 5 seconds)
        Thread.sleep(5000);

        // Interrupt all philosopher threads to stop them
        for (Thread thread : threads) {
            thread.interrupt();
        }

        // Wait for all philosopher threads to finish
        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("All philosophers have finished.");
    }
}

public class Philosopher implements Runnable {
    private final Object leftFork;
    private final Object rightFork;
    private final int id;

    public Philosopher(int id, Object leftFork, Object rightFork) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    private void think() throws InterruptedException {
        System.out.println("Philosopher " + id + " is thinking");
        Thread.sleep((long) (Math.random() * 100));
    }

    private void eat() throws InterruptedException {
        System.out.println("Philosopher " + id + " is eating");
        Thread.sleep((long) (Math.random() * 100));
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                // Philosopher starts thinking
                think();

                // Determine the order of picking up forks to avoid deadlock
                Object firstFork = leftFork;
                Object secondFork = rightFork;
                if (System.identityHashCode(leftFork) > System.identityHashCode(rightFork)) {
                    firstFork = rightFork;
                    secondFork = leftFork;
                }

                // Philosopher picks up the first fork
                synchronized (firstFork) {
                    System.out.println("Philosopher " + id + " picked up the first fork");

                    // Philosopher picks up the second fork
                    synchronized (secondFork) {
                        System.out.println("Philosopher " + id + " picked up the second fork");

                        // Philosopher starts eating
                        eat();

                        // Philosopher puts down the second fork
                        System.out.println("Philosopher " + id + " put down the second fork");
                    }

                    // Philosopher puts down the first fork
                    System.out.println("Philosopher " + id + " put down the first fork");
                }

                // Back to thinking
                System.out.println("Philosopher " + id + " goes back to thinking");
            }
        } catch (InterruptedException e) {
            System.out.println("Philosopher " + id + " was interrupted and is stopping.");
            Thread.currentThread().interrupt(); // Restore the interrupt status.
        }
    }
}