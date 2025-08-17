package Threads.Concurrent.Collections;

import java.util.concurrent.ConcurrentHashMap;

class MainConcurrentMapExample {
    public static void main(String[] args) throws InterruptedException {
        ConcurrentMapExample map = new ConcurrentMapExample();

        // Both threads now increment the same key "apple"
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                map.incrementCount("apple");
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                map.incrementCount("apple");
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        // The final count should be 200000, but it will be less
        // due to lost updates from race conditions
        map.displayMap();
    }
}

public class ConcurrentMapExample {
    private final ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

    public void incrementCount(String key) {
        map.merge(key, 1, (existing, newvalue) -> existing + newvalue);
    }

    public void displayMap() {
        map.forEach((key, count) -> System.out.println(key + ": " + count));
    }
}