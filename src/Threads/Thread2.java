package Threads;

import java.time.LocalDateTime;
import java.util.Date;

public class Thread2 implements Runnable {


    @Override
    public void run() {
        Date test = new Date();
        LocalDateTime now = LocalDateTime.now();

        int i = 0;
        while (i < 10) {
            System.out.println(Thread.currentThread() + " : " + i);
            i++;
        }
    }
}
