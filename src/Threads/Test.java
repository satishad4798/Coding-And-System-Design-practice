package Threads;

public class Test {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread());
        System.out.println("main start");

        Thread t2 = new Thread(new Thread2());

        t2.start();

        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread() + ":" + i);
            }
        });
        t3.start();
        System.out.println("main ended");
    }
}


