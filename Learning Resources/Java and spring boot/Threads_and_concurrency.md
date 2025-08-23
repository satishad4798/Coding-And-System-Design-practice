# Threads and Concurrency in Java

This guide will help you understand multithreading and concurrency in Java, from basic concepts to advanced utilities, along with interview preparation.

## Part 1: Learning Concurrency

### 1. What is Concurrency?

Concurrency is the ability of a system to execute multiple tasks or parts of a program in overlapping time periods. It doesn't necessarily mean they run at the exact same instant (that's parallelism, which requires multiple CPU cores), but rather that progress is made on multiple tasks by switching between them.

**Why use concurrency?**
- **Responsiveness:** Keep applications responsive (e.g., a UI thread remains active while background tasks run).
- **Performance:** Utilize multi-core processors to perform computations faster.
- **Resource Utilization:** Efficiently use CPU time while waiting for I/O operations (like network requests or file access).

**Process vs. Thread:**
- **Process:** An instance of a program running. Each process has its own memory space.
- **Thread:** A lightweight unit of execution within a process. Threads within the same process share the same memory space, which makes communication easier but also introduces challenges like race conditions.

### 2. Creating and Running Threads

Java provides two main ways to create threads:

**a) Extending the `Thread` class:**

```java
class MyThread extends Thread {
    private String threadName;

    public MyThread(String name) {
        this.threadName = name;
        System.out.println("Creating " +  threadName );
    }

    @Override
    public void run() {
        System.out.println("Running " +  threadName );
        try {
            for(int i = 0; i < 3; i++) {
                System.out.println("Thread: " + threadName + ", Count: " + i);
                // Let the thread sleep for a while.
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            System.out.println("Thread " +  threadName + " interrupted.");
        }
        System.out.println("Thread " +  threadName + " exiting.");
    }
}

public class TestThread {
   public static void main(String args[]) {
      MyThread thread1 = new MyThread("Thread-1");
      thread1.start(); // Calls run() method

      MyThread thread2 = new MyThread("Thread-2");
      thread2.start();
   }
}
```
**Key point:** You call `start()` to begin execution, which in turn calls the `run()` method in the new thread. Calling `run()` directly would execute the code in the current thread, not a new one.

**b) Implementing the `Runnable` interface:** (Preferred approach)

```java
class MyRunnable implements Runnable {
    private String taskName;

    public MyRunnable(String name) {
        this.taskName = name;
        System.out.println("Creating task: " +  taskName );
    }

    @Override
    public void run() {
        System.out.println("Executing task: " +  taskName );
        try {
            for(int i = 0; i < 3; i++) {
                System.out.println("Task: " + taskName + ", Step: " + i);
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            System.out.println("Task " +  taskName + " interrupted.");
        }
        System.out.println("Task " +  taskName + " exiting.");
    }
}

public class TestRunnable {
   public static void main(String args[]) {
      MyRunnable task1 = new MyRunnable("Task-A");
      Thread threadA = new Thread(task1);
      threadA.start();

      MyRunnable task2 = new MyRunnable("Task-B");
      Thread threadB = new Thread(task2);
      threadB.start();
   }
}
```
**Why `Runnable` is often preferred:**
- Java doesn't support multiple inheritance for classes. If your class already extends another class, you can't extend `Thread`. Implementing `Runnable` allows more flexibility.
- It promotes better object-oriented design by separating the task (the `Runnable`) from the execution mechanism (the `Thread`).

**Thread Lifecycle:**
- **NEW:** Thread has been created but not yet started.
- **RUNNABLE:** Thread is ready to run and waiting for CPU time. `start()` moves it here.
- **RUNNING:** Thread is currently executing.
- **BLOCKED/WAITING/TIMED_WAITING:** Thread is temporarily inactive (e.g., waiting for a lock, `Object.wait()`, `Thread.sleep()`, `Thread.join()`).
- **TERMINATED:** Thread has completed its execution or has been terminated.

**Common Thread Methods:**
- `Thread.sleep(long millis)`: Pauses the current thread for a specified time. It can throw `InterruptedException`.
- `thread.join()`: Waits for the specified thread `thread` to terminate.
- `thread.interrupt()`: Interrupts a thread. If the thread is in a waiting/sleeping state, it throws `InterruptedException`. Threads should periodically check `Thread.currentThread().isInterrupted()`.

### 3. Synchronization and Shared Memory

When multiple threads access and modify shared data, you can run into problems like **race conditions**.

**Race Condition Example:**

```java
class Counter {
    private int count = 0;

    public void increment() {
        // This operation (count++) is not atomic.
        // It involves: 1. Read count, 2. Increment value, 3. Write count back.
        // Threads can interleave these steps, leading to incorrect results.
        count++;
    }

    public int getCount() {
        return count;
    }
}

public class RaceConditionDemo {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        Runnable task = () -> {
            for (int i = 0; i < 10000; i++) {
                counter.increment();
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();

        t1.join(); // Wait for t1 to finish
        t2.join(); // Wait for t2 to finish

        // Expected: 20000, Actual: Might be less due to race condition
        System.out.println("Final count: " + counter.getCount());
    }
}
```

To prevent race conditions, we use synchronization.

**a) `synchronized` Keyword:**
Provides mutual exclusion (only one thread can execute a synchronized block/method on a given object at a time) and visibility (changes made by one thread are visible to others).

- **Synchronized Method:**
  ```java
  class SynchronizedCounter {
      private int count = 0;

      // Only one thread can execute this method on an instance of SynchronizedCounter at a time
      public synchronized void increment() {
          count++;
      }

      public synchronized int getCount() {
          return count;
      }
  }
  ```
  When a synchronized method is called, the thread acquires the intrinsic lock (monitor) of the object.

- **Synchronized Block:**
  Allows finer-grained locking on a specific object.
  ```java
  class FineGrainedCounter {
      private int c1 = 0;
      private int c2 = 0;
      private final Object lock1 = new Object();
      private final Object lock2 = new Object();

      public void incrementC1() {
          synchronized (lock1) { // Lock on lock1 object
              c1++;
          }
      }
      public void incrementC2() {
          synchronized (lock2) { // Lock on lock2 object
              c2++;
          }
      }
      // ... getters
  }
  ```
  You can also synchronize on `this` (the current instance) or a class literal (`SynchronizedCounter.class` for static synchronized methods).

**b) `volatile` Keyword:**
Ensures visibility of changes to a variable across threads. When a `volatile` variable is written, the change is flushed to main memory, and when read, it's read directly from main memory. It also prevents certain instruction reordering.

```java
class StatusFlag {
    // Ensures that changes to 'running' are visible across threads
    private volatile boolean running = true;

    public void stopRunning() {
        running = false; // Write is flushed to main memory
    }

    public void work() {
        while (running) { // Read gets the latest value from main memory
            // do some work
            System.out.println("Working...");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                running = false;
            }
        }
        System.out.println("Stopped working.");
    }
}

public class VolatileDemo {
    public static void main(String[] args) throws InterruptedException {
        StatusFlag status = new StatusFlag();
        Thread workerThread = new Thread(status::work);
        workerThread.start();

        Thread.sleep(500); // Let the worker run for a bit
        System.out.println("Requesting stop...");
        status.stopRunning();

        workerThread.join();
        System.out.println("Main thread finished.");
    }
}
```
**`volatile` vs. `synchronized`:**
- `volatile` guarantees visibility and some ordering but **not atomicity** for compound operations (like `count++`). It's lighter weight than `synchronized` as it doesn't involve locking.
- `synchronized` guarantees atomicity (for the synchronized block/method) and visibility.

**c) Atomic Variables (`java.util.concurrent.atomic`):**
Provide lock-free, thread-safe operations on single variables using low-level atomic hardware instructions (Compare-And-Swap - CAS).

```java
import java.util.concurrent.atomic.AtomicInteger;

class AtomicCounter {
    private AtomicInteger count = new AtomicInteger(0);

    public void increment() {
        count.incrementAndGet(); // Atomically increments
    }

    public int getCount() {
        return count.get();
    }
}

public class AtomicDemo {
    public static void main(String[] args) throws InterruptedException {
        AtomicCounter counter = new AtomicCounter();
        Runnable task = () -> {
            for (int i = 0; i < 10000; i++) {
                counter.increment();
            }
        };
        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("Final atomic count: " + counter.getCount()); // Expected: 20000
    }
}
```

### 4. Executor Framework (`java.util.concurrent`)

Manually creating and managing threads can be complex and error-prone. The Executor framework abstracts thread management and provides thread pools.

- **`Executor`:** A simple interface for executing `Runnable` tasks.
- **`ExecutorService`:** Extends `Executor`, providing methods to manage the lifecycle of the executor and tasks (e.g., submit `Callable`, get `Future`, shutdown).
- **`Executors`:** A utility class with factory methods for creating common `ExecutorService` instances.

**Benefits of Thread Pools:**
- **Improved Performance:** Reduces overhead of creating new threads for each task.
- **Resource Management:** Controls the number of active threads, preventing resource exhaustion.
- **Simplified Thread Management:** Abstracts away thread creation and lifecycle.

**Example:**

```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ExecutionException;

public class ExecutorServiceDemo {
    public static void main(String[] args) {
        // Create a fixed-size thread pool with 2 threads
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // Submit Runnable tasks
        for (int i = 0; i < 5; i++) {
            final int taskId = i;
            executor.submit(() -> {
                System.out.println("Executing task " + taskId + " by " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println("Finished task " + taskId);
            });
        }

        // Submit Callable tasks (can return a result)
        Callable<String> callableTask = () -> {
            Thread.sleep(2000);
            return "Callable task result from " + Thread.currentThread().getName();
        };
        Future<String> future = executor.submit(callableTask);

        try {
            System.out.println("Waiting for callable result...");
            String result = future.get(); // Blocks until the task completes
            System.out.println("Callable Result: " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        // Shutdown the executor
        executor.shutdown(); // Initiates an orderly shutdown, previously submitted tasks execute
        try {
            // Wait for all tasks to complete or timeout
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow(); // Tries to stop all actively executing tasks
            }
        } catch (InterruptedException ie) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
        System.out.println("All tasks finished.");
    }
}
```

**Types of Thread Pools:**
- `Executors.newFixedThreadPool(int nThreads)`: Pool with a fixed number of threads.
- `Executors.newCachedThreadPool()`: Pool that creates new threads as needed and reuses idle threads. Good for many short-lived tasks.
- `Executors.newSingleThreadExecutor()`: Pool with a single thread, ensuring tasks execute sequentially.
- `Executors.newScheduledThreadPool(int corePoolSize)`: Pool for scheduling tasks to run after a delay or periodically.

### 5. Virtual Threads (Project Loom - Java 19+ Preview, Java 21+ Standard)

Virtual threads are lightweight threads managed by the JVM, not directly by the OS. They are designed to significantly increase the throughput of concurrent applications, especially those that are I/O-bound (spend a lot of time waiting for network or disk).

- **Key Idea:** Many virtual threads can run on a small number of OS (platform) threads. When a virtual thread blocks on I/O, its underlying OS thread is freed up to run another virtual thread.

**Creating Virtual Threads:**

```java
// Available from Java 19 (preview) / Java 21 (standard)
public class VirtualThreadsDemo {
    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> {
            System.out.println("Executing task in: " + Thread.currentThread());
            try {
                Thread.sleep(100); // Simulates I/O-bound work
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        // Option 1: Thread.startVirtualThread()
        Thread virtualThread1 = Thread.startVirtualThread(task);

        // Option 2: Thread.ofVirtual().start()
        Thread virtualThread2 = Thread.ofVirtual().name("my-virtual-thread").start(task);

        // Option 3: Using an ExecutorService that creates virtual threads
        // try-with-resources ensures the executor is shut down
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < 5; i++) {
                final int taskId = i;
                executor.submit(() -> {
                    System.out.println("Task " + taskId + " running in: " + Thread.currentThread());
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
            }
        } // executor.close() is called automatically, which shuts it down and waits for tasks

        virtualThread1.join();
        virtualThread2.join();
        System.out.println("Main thread finished.");
    }
}
```

**Benefits of Virtual Threads:**
- **High Throughput:** Handle many concurrent I/O-bound tasks with fewer OS threads.
- **Simpler Code:** Write straightforward blocking code that scales well, without complex asynchronous programming.
- **Resource Efficiency:** Virtual threads have a small memory footprint.

**When to use Virtual Threads:**
- Ideal for applications with many concurrent tasks that spend most of their time waiting for I/O (e.g., web servers, microservices handling many requests).
- Not a silver bullet for CPU-bound tasks; platform threads are still suitable for those.

### 6. Deadlocks

A deadlock is a situation where two or more threads are blocked forever, each waiting for a resource held by another thread in the cycle.

**Conditions for Deadlock:**
1.  **Mutual Exclusion:** At least one resource must be held in a non-sharable mode.
2.  **Hold and Wait:** A thread holds at least one resource and is waiting to acquire additional resources held by other threads.
3.  **No Preemption:** Resources cannot be forcibly taken away from threads holding them.
4.  **Circular Wait:** A set of threads {T0, T1, ..., Tn} exists such that T0 is waiting for a resource held by T1, T1 is waiting for T2, ..., Tn is waiting for T0.

**Example of Deadlock:**

```java
public class DeadlockDemo {
    private static final Object resource1 = new Object();
    private static final Object resource2 = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (resource1) {
                System.out.println("Thread 1: Locked resource 1");
                try { Thread.sleep(100); } catch (InterruptedException e) {}
                System.out.println("Thread 1: Waiting for resource 2...");
                synchronized (resource2) {
                    System.out.println("Thread 1: Locked resource 2");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (resource2) { // Acquires locks in different order
                System.out.println("Thread 2: Locked resource 2");
                try { Thread.sleep(100); } catch (InterruptedException e) {}
                System.out.println("Thread 2: Waiting for resource 1...");
                synchronized (resource1) {
                    System.out.println("Thread 2: Locked resource 1");
                }
            }
        });

        t1.start();
        t2.start();
        // These threads will likely deadlock.
    }
}
```

**Avoiding Deadlocks:**
- **Lock Ordering:** Acquire locks in a consistent global order.
- **Lock Timeout:** Use `tryLock()` with a timeout (e.g., `ReentrantLock.tryLock(timeout, unit)`) to avoid indefinite waiting.
- **Deadlock Detection:** More complex; JVM can sometimes detect deadlocks (thread dumps can help).

### 7. Concurrent File Editing (Handling Conflicts)

Your question about "current editing a file by multiple people (whose edit will be given higher precedence)" is more of an application-level design problem than a core Java concurrency primitive for simultaneous text merging. However, Java provides mechanisms to control exclusive access to files.

**Strategies for Concurrent File Access/Editing:**

1.  **Pessimistic Locking (Exclusive Access):**
    - Only one user/process can modify the file at a time.
    - Java's `java.nio.channels.FileChannel` provides `lock()` or `tryLock()` for this. This creates an OS-level lock on the file or a region of it.
    - **Precedence:** The one who acquires the lock gets to edit. Others wait or are denied.

    ```java
    import java.io.RandomAccessFile;
    import java.nio.channels.FileChannel;
    import java.nio.channels.FileLock;
    import java.nio.file.Path;
    import java.nio.file.Paths;
    import java.nio.file.StandardOpenOption;

    public class FileLockExample {
        public static void main(String[] args) {
            Path path = Paths.get("sharedfile.txt");
            // Ensure file exists for RandomAccessFile, or use Files.newByteChannel
            try (RandomAccessFile raf = new RandomAccessFile(path.toFile(), "rw");
                 FileChannel channel = raf.getChannel()) {

                System.out.println(Thread.currentThread().getName() + " trying to lock file...");
                FileLock lock = null;
                try {
                    // Attempt to acquire an exclusive lock on the entire file
                    lock = channel.lock(); // or channel.tryLock();

                    if (lock != null) {
                        System.out.println(Thread.currentThread().getName() + " acquired lock. Editing file...");
                        // Simulate editing
                        raf.seek(raf.length()); // Go to end of file
                        raf.writeBytes(Thread.currentThread().getName() + " wrote this at " + System.currentTimeMillis() + "\n");
                        Thread.sleep(2000); // Simulate work
                        System.out.println(Thread.currentThread().getName() + " finished editing.");
                    } else {
                        System.out.println(Thread.currentThread().getName() + " could not acquire lock.");
                    }
                } catch (Exception e) {
                    System.err.println(Thread.currentThread().getName() + " Error: " + e.getMessage());
                } finally {
                    if (lock != null && lock.isValid()) {
                        lock.release();
                        System.out.println(Thread.currentThread().getName() + " released lock.");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    // To test this, run multiple instances of this main method concurrently.
    // You'll see only one can acquire the lock at a time.
    ```

2.  **Optimistic Locking:**
    - Multiple users can "edit" a copy of the data.
    - When saving, the system checks if the underlying data has changed since it was read (e.g., using version numbers, timestamps, or checksums).
    - **Precedence/Conflict Resolution:**
        - **Last Write Wins:** The latest save overwrites previous changes (can lead to data loss).
        - **Merge:** If a conflict is detected, the user might be prompted to merge changes (complex, like Git).
        - **Fail and Retry:** The save operation fails, and the user has to re-apply their changes to the latest version.
    - This is typically implemented at the application layer, not directly with low-level file locks for the entire editing session.

3.  **Application-Specific Logic:**
    - For collaborative editors (like Google Docs), the "precedence" is often handled by sophisticated algorithms that merge changes in real-time or near real-time, often breaking down changes into smaller operations. This is far beyond simple file locking.

**In summary for file editing:**
- Java's `FileLock` helps ensure exclusive access at the OS level, preventing simultaneous writes that could corrupt the file.
- Deciding "whose edit gets higher precedence" when conflicts arise (if simultaneous editing is allowed conceptually) is an application design choice (e.g., last-write-wins, timestamp-based, manual merge).

---

## Part 2: Interview Preparation - Common Questions

Here are some frequently asked questions about Java concurrency:

1.  **What is the difference between a Process and a Thread?**
    - **Process:** An independent program execution with its own memory space.
    - **Thread:** A lightweight execution unit within a process, sharing the process's memory space. Context switching between threads is generally faster than between processes.

2.  **How can you create a thread in Java? Which way is preferred?**
    - **Extending `Thread` class:** Override `run()`.
    - **Implementing `Runnable` interface:** Implement `run()` and pass an instance to a `Thread` constructor.
    - **Implementing `Runnable` is generally preferred:**
        - Allows multiple inheritance of behavior (a class can implement multiple interfaces).
        - Promotes separation of concerns (task vs. execution mechanism).
        - More flexible if your class already needs to extend another class.

3.  **Explain the thread lifecycle in Java.**
    - **NEW:** Created, not yet started (`new Thread(r)`).
    - **RUNNABLE:** Ready to run, waiting for CPU (`thread.start()`).
    - **RUNNING:** Actively executing code.
    - **BLOCKED/WAITING/TIMED_WAITING:** Temporarily inactive (e.g., `synchronized` block entry, `Object.wait()`, `Thread.sleep()`, `thread.join()`).
    - **TERMINATED:** Execution finished or an unhandled exception occurred.

4.  **What is `Thread.join()`?**
    - Causes the current thread to pause execution until the thread on which `join()` is called terminates.

5.  **What is `Thread.sleep()`? Does it release locks?**
    - Pauses the current thread for a specified duration.
    - It does **not** release any monitor locks the thread holds. If a thread sleeps within a `synchronized` block, it keeps the lock.

6.  **What is the difference between `wait()`, `notify()`, and `notifyAll()`?**
    - These methods are defined in the `Object` class and are used for inter-thread communication.
    - They must be called from within a `synchronized` block or method on the object whose lock is held.
    - **`wait()`:** Causes the current thread to release the lock and wait until another thread invokes `notify()` or `notifyAll()` on the same object, or until a timeout occurs.
    - **`notify()`:** Wakes up a single thread that is waiting on this object's monitor. The choice of which thread is arbitrary.
    - **`notifyAll()`:** Wakes up all threads that are waiting on this object's monitor.

7.  **What is a race condition? How can you prevent it?**
    - A situation where the outcome of a computation depends on the non-deterministic relative timing of operations by multiple threads accessing shared mutable data.
    - **Prevention:**
        - **Synchronization:** Using `synchronized` blocks/methods.
        - **Locks:** Using explicit locks from `java.util.concurrent.locks` (e.g., `ReentrantLock`).
        - **Atomic Variables:** Using classes from `java.util.concurrent.atomic` for atomic operations on single variables.
        - **Immutable Objects:** Making shared data immutable.
        - **Thread-Safe Collections:** Using concurrent collections from `java.util.concurrent`.

8.  **Explain the `synchronized` keyword.**
    - A Java keyword used to control access to shared resources by multiple threads.
    - **Synchronized Methods:** The lock is on the `this` object (for instance methods) or the `Class` object (for static methods).
    - **Synchronized Blocks:** `synchronized(object) { ... }`. The lock is on the specified `object`.
    - It ensures **mutual exclusion** (only one thread executes the synchronized code on the lock at a time) and **visibility** (changes made by one thread are visible to others after they acquire the same lock).

9.  **Explain the `volatile` keyword. What is the difference between `synchronized` and `volatile`?**
    - **`volatile`:**
        - Guarantees **visibility**: Reads of a volatile variable see the latest write by any thread.
        - Guarantees **ordering**: Prevents certain compiler and CPU instruction reorderings around reads/writes of the volatile variable.
        - Does **not** provide atomicity for compound operations (e.g., `i++`).
    - **Differences:**
        | Feature         | `synchronized`                                     | `volatile`                                          |
        |-----------------|----------------------------------------------------|-----------------------------------------------------|
        | **Primary Use** | Mutual exclusion, atomicity for blocks, visibility | Visibility of a single variable, ordering guarantees |
        | **Locking**     | Yes (acquires/releases a monitor lock)             | No                                                  |
        | **Atomicity**   | Guarantees atomicity for the synchronized block/method | Only for single read/write of the volatile variable |
        | **Visibility**  | Guarantees visibility for *all* variables accessed within the synchronized block | Guarantees visibility for *the specific* volatile variable |
        | **Performance** | Can be higher overhead due to locking              | Generally lower overhead                            |
        | **Scope**       | Methods, code blocks                               | Variables                                           |

10. **What are atomic operations? Explain `java.util.concurrent.atomic` package.**
    - An atomic operation is one that appears to the rest of the system to occur indivisibly.
    - The `java.util.concurrent.atomic` package (e.g., `AtomicInteger`, `AtomicLong`, `AtomicBoolean`, `AtomicReference`) provides classes that support lock-free thread-safe programming on single variables. They often use hardware-level Compare-And-Swap (CAS) operations for efficiency.

11. **What is the Executor Framework? What are its benefits?**
    - A framework in `java.util.concurrent` for managing and executing asynchronous tasks (Runnables or Callables) using thread pools.
    - **Benefits:**
        - **Decoupling task submission from execution:** You submit tasks without worrying about thread creation details.
        - **Thread Pool Management:** Reuses threads, reducing overhead of thread creation/destruction.
        - **Resource Control:** Limits the number of concurrent threads, preventing resource exhaustion.
        - **Lifecycle Management:** Provides ways to shut down the executor and wait for task completion.

12. **What are different types of thread pools you can create using `Executors`?**
    - `Executors.newFixedThreadPool(int nThreads)`: Fixed number of threads.
    - `Executors.newCachedThreadPool()`: Creates threads as needed, reuses idle ones. Good for many short tasks.
    - `Executors.newSingleThreadExecutor()`: Uses a single worker thread. Tasks execute sequentially.
    - `Executors.newScheduledThreadPool(int corePoolSize)`: For scheduling tasks to run at a future time or periodically.

13. **What is the difference between `Callable` and `Runnable`? What is `Future`?**
    - **`Runnable`:** Its `run()` method does not return a value and cannot throw checked exceptions.
    - **`Callable`:** Its `call()` method can return a value (of generic type `V`) and can throw checked exceptions.
    - **`Future<V>`:** Represents the result of an asynchronous computation. When a `Callable` is submitted to an `ExecutorService`, a `Future` is returned.
        - `isDone()`: Checks if the task is complete.
        - `get()`: Waits for the task to complete and returns its result (blocks).
        - `get(long timeout, TimeUnit unit)`: Waits for a specified time.
        - `cancel(boolean mayInterruptIfRunning)`: Attempts to cancel the task.

14. **What is a deadlock? How can you avoid it?**
    - A situation where two or more threads are blocked indefinitely, each waiting for a resource held by another.
    - **Avoidance Strategies:**
        - **Lock Ordering:** All threads acquire locks in the same predefined order.
        - **Lock Timeout:** Use `tryLock()` with a timeout to prevent indefinite waiting.
        - **Reduce Lock Scope:** Hold locks for the shortest possible time.
        - **Avoid Nested Locks:** If possible, or be extremely careful with their order.

15. **What is `ConcurrentHashMap`? How is it different from `Collections.synchronizedMap(new HashMap())`?**
    - **`ConcurrentHashMap`:**
        - A thread-safe `Map` implementation designed for high concurrency.
        - Uses fine-grained locking (lock striping or segmenting). Different parts of the map can be locked independently, allowing multiple threads to access/modify different parts concurrently.
        - Iterators are typically fail-safe or weakly consistent (do not throw `ConcurrentModificationException` but may not reflect very recent updates).
        - Generally offers better performance in highly concurrent scenarios.
    - **`Collections.synchronizedMap(new HashMap())`:**
        - A wrapper that makes a `HashMap` thread-safe by synchronizing every public method on a single lock (the map object itself).
        - This means only one thread can access the map at a time, leading to lower concurrency and potential bottlenecks.
        - Iterators are fail-fast (can throw `ConcurrentModificationException` if the map is modified during iteration by another thread).

16. **What are Virtual Threads (Project Loom)? What are their advantages?**
    - Lightweight threads managed by the JVM, not directly by OS threads. Many virtual threads can run on a few platform (OS) threads.
    - **Advantages:**
        - **High Throughput for I/O-bound tasks:** Can handle millions of concurrent tasks that spend time waiting.
        - **Simplified Code:** Allows writing simple, blocking-style code that scales well.
        - **Resource Efficiency:** Small memory footprint per virtual thread.
        - **Easy Adoption:** Minimal changes needed to existing code that uses `Thread` or `ExecutorService`.
    - Available as a preview feature in Java 19/20 and a standard feature in Java 21+.

17. **Explain some other concurrency utilities like `CountDownLatch`, `CyclicBarrier`, `Semaphore`.**
    - **`CountDownLatch`:** A synchronization aid that allows one or more threads to wait until a set of operations being performed in other threads completes. Initialized with a count; `countDown()` decrements it, `await()` blocks until the count reaches zero.
    - **`CyclicBarrier`:** Allows a set of threads to all wait for each other to reach a common barrier point. It's "cyclic" because it can be reset and reused after threads are released.
    - **`Semaphore`:** Maintains a set of permits. `acquire()` blocks until a permit is available, then takes one. `release()` adds a permit, potentially releasing a blocking acquirer. Used to control access to a limited number of resources.

This detailed explanation and Q&A should give you a solid foundation in Java concurrency and help you prepare for interviews. Remember to practice writing concurrent code to solidify your understanding!