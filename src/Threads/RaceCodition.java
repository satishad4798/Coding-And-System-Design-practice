package Threads;

public class RaceCodition {

    static int totalSeats = 100;
    static int count = 0;

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000 && totalSeats > 0; i++) {

                count++;
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                totalSeats = totalSeats - 1;

                System.out.println(Thread.currentThread() + "booking ticket. left" + totalSeats);
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000 && totalSeats > 0; i++) {
                count++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                totalSeats = totalSeats - 1;

                System.out.println(Thread.currentThread() + "booking ticket. left" + totalSeats);
            }
        });
        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 10000 && totalSeats > 0; i++) {
                count++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                totalSeats = totalSeats - 1;

                System.out.println(Thread.currentThread() + "booking ticket. left" + totalSeats);
            }
        });
        Thread t4 = new Thread(() -> {
            for (int i = 0; i < 10000 && totalSeats > 0; i++) {
                count++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                totalSeats = totalSeats - 1;

                System.out.println(Thread.currentThread() + "booking ticket. left" + totalSeats);
            }
        });
        Thread t5 = new Thread(() -> {
            for (int i = 0; i < 10000 && totalSeats > 0; i++) {

                count++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                totalSeats = totalSeats - 1;

                System.out.println(Thread.currentThread() + "booking ticket. left" + totalSeats);
            }
        });
        Thread t6 = new Thread(() -> {
            for (int i = 0; i < 10000 && totalSeats > 0; i++) {
                count++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                totalSeats = totalSeats - 1;

                System.out.println(Thread.currentThread() + "booking ticket. left" + totalSeats);
            }
        });

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t1.join();
        t2.join();
        t3.join();
        t4.join();
        t5.join();
        t6.join();
        System.out.println("count: " + count);
        System.out.println("completed");
    }
}


