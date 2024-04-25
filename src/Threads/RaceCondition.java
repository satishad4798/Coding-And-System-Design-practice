package Threads;

public class RaceCondition {

    static int totalSeats = 50;
    static int count = 0;
    static int bkp_total = totalSeats;

    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        Thread t1 = new Thread(() -> {

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


        t1.start();
        t2.start();

        t1.join();
        t2.join();
        System.out.println("total seats : " + bkp_total + " + allotted :" + count);
        System.out.println("completed");
    }


}


