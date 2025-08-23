# Important notes
1. Never swallow InterruptedException.
  - Always restore the interrupt status after catching InterruptedException.
  - Use `Thread.currentThread().interrupt()` to restore the interrupt status.


# Threads in Java

- The `start()` method, when called, invokes the `run()` method in a new thread of execution.
- It’s important to understand that the `start()` method creates a separate thread, which runs concurrently with the
  main thread.
- If you call the `run()` method directly, it will not create a new thread; instead, it will execute in the current
  thread.
  # 🔹 How to Avoid Deadlock in Mutex/Locks

  Deadlock occurs when threads are stuck waiting for each other’s locks indefinitely. Here’s how to prevent it:

  ## 1. Lock Ordering (Most Common Fix)
  - **Always acquire locks in a consistent, predefined order across all threads.**
  - Example: If you have `lock1` and `lock2`, always acquire `lock1` before `lock2` everywhere.

  ## 2. Use `tryLock()` with Timeout
  - **Instead of blocking forever, use `tryLock(timeout)`.**
  - If the lock isn’t acquired within the timeout, give up and retry or handle gracefully.
  - This avoids threads waiting endlessly and helps break circular waits.

  ## 3. Lock Hierarchy / Disciplined Locking
  - **Define a global hierarchy for resources/locks.**
  - Threads must acquire locks following this hierarchy strictly.
  - Prevents circular dependencies and deadlocks.

  ## 4. Deadlock Detection (Rare in Java)
  - **Detect deadlocks at runtime and recover.**
  - More common in database systems than in Java applications.
  - Java provides limited support; usually handled by monitoring tools.

  ---

# Java Thread Coordination Cheat Sheet

## 1. `wait()` vs `await()`

| Feature               | `wait()` (Object)               | `await()` (Condition)                           |
|-----------------------|---------------------------------|-------------------------------------------------|
| Origin                | `java.lang.Object`              | `java.util.concurrent.locks.Condition`          |
| Lock type             | Intrinsic lock (`synchronized`) | Explicit `Lock` (`ReentrantLock`)               |
| Must be called inside | `synchronized(lock)`            | `lock.lock()` / `lock.unlock()` block           |
| Releases lock?        | Yes                             | Yes                                             |
| Wake-up method        | `notify()` / `notifyAll()`      | `signal()` / `signalAll()`                      |
| Number of queues      | 1 per object                    | Multiple `Condition`s per lock                  |
| Use case              | Simple thread coordination      | Complex coordination with multiple roles/queues |

**Summary:** `await()` is like `wait()`, but tied to `Lock` and allows multiple condition queues for finer control.

---

## 2. `notify()` vs `signal()` (single thread wake-up)

| Feature    | `notify()`                                        | `signal()`                                      |
|------------|---------------------------------------------------|-------------------------------------------------|
| Origin     | `Object` class                                    | `Condition` interface                           |
| Wakes      | One random thread waiting on the object's monitor | One thread waiting on the specific `Condition`  |
| Use case   | Simple coordination                               | Targeted wake-up when multiple conditions exist |
| Efficiency | Less controlled (may wake wrong thread)           | Controlled (wakes correct queue)                |

---

## 3. `notifyAll()` vs `signalAll()` (wake all threads)

| Feature    | `notifyAll()`                                          | `signalAll()`                                                                |
|------------|--------------------------------------------------------|------------------------------------------------------------------------------|
| Origin     | `Object` class                                         | `Condition` interface                                                        |
| Wakes      | All threads waiting on the object’s monitor            | All threads waiting on that `Condition`                                      |
| Use case   | Ensures progress if unsure which thread should run     | Ensures progress for a group waiting on specific condition                   |
| Efficiency | All threads wake and compete for lock (less efficient) | All threads wake but only those for the specific condition (more controlled) |

---

## 4. Quick Notes

- **`wait/notify`** → only **one waiting queue per object** → simpler, less control.
- **`Lock/Condition`** → **multiple queues possible** → cleaner for problems like Odd/Even printing.
- Always call `wait()` / `notify()` inside `synchronized(lock)`.
- Always call `await()` / `signal()` inside `lock.lock()` / `lock.unlock()`.
- Use `notifyAll()` / `signalAll()` when unsure which thread should be woken or to avoid missed signals.

# Java `Condition` Quick Reference

## 1. What is `Condition`?

- Part of `java.util.concurrent.locks`.
- Works **with a `Lock`** (e.g., `ReentrantLock`) instead of `synchronized`.
- Provides a way for threads to **wait for a specific condition** and be signaled to continue.
- More flexible than `wait/notify`:
    - Allows **multiple waiting queues** per lock (`Condition` objects).
    - Can signal threads selectively.

---

## 2. Key Methods

| Method        | Description                                                        |
|---------------|--------------------------------------------------------------------|
| `await()`     | Puts the thread to sleep, releases the lock, waits until signaled. |
| `signal()`    | Wakes **one thread** waiting on this `Condition`.                  |
| `signalAll()` | Wakes **all threads** waiting on this `Condition`.                 |

**Notes:**

- Must hold the associated `Lock` when calling these methods.
- Throws `InterruptedException` when interrupted while waiting.

---

# Atomic Operations and Compare-And-Swap (CAS)

## What are Atomic Operations?

Atomic operations are indivisible operations that happen entirely at once, without any other thread being able to
intervene in the middle. They are fundamental to thread-safe programming.

## Understanding Compare-And-Swap (CAS)

Compare-And-Swap (CAS) is a hardware-level atomic instruction that ensures thread-safe updates to shared variables. It's
the foundation of lock-free programming in Java.

### How CAS Works

CAS operation takes three operands:

- **M**: Memory location to be updated
- **A**: Expected current value
- **B**: New value to be set

```java
// Pseudocode for CAS operation
boolean compareAndSwap(MemoryLocation M, Value A, Value B) {
    synchronized (M) {  // This synchronization is handled by hardware
        if (M.current == A) {
            M.current = B;
            return true;    // Update successful
        }
        return false;       // Update failed
    }
}
```

### Key Points About CAS

1. **Atomicity**: The entire operation happens in one indivisible step
2. **Optimistic Locking**: No explicit locks needed
3. **Hardware Support**: Implemented at CPU level for efficiency
4. **Thread Safety**: Guarantees thread-safe updates without blocking

### Usage in Java

```java
AtomicInteger counter = new AtomicInteger(0);
// This internally uses CAS


incrementAndGet();  // Thread-safe increment
```

### Advantages

- More efficient than traditional locking
- Prevents deadlocks
- Better scalability in high-contention scenarios
- Foundation for lock-free data structures

> **Note**: CAS forms the backbone of Java's atomic variables (`AtomicInteger`, `AtomicLong`, etc.), enabling
> thread-safe operations without explicit locks.

# CopyOnWrite Collections

```java

//list
private final List<String> subscribers = new CopyOnWriteArrayList<>();

// set
private final Set<String> subscribers = new CopyOnWriteArraySet<>();

```

- Building on your foundational knowledge of synchronized and concurrent collections,
  you're now ready to explore CopyOnWrite collections in Java.
- This type of collection is particularly effective for scenarios involving frequent reads and infrequent writes,
  offering thread safety without the performance drawbacks of locking.

### Interview one-liner

- CopyOnWrite collections provide thread safety with snapshot-based eventual consistency.
- They’re ideal for read-heavy, write-rare use cases where eventual consistency is acceptable,
  but not suitable when strong consistency is mandatory (like financial or transactional systems).

### Removing Elements During Iteration

- While CopyOnWriteArrayList allows you to add elements safely during iteration, removing elements during iteration will
  throw an UnsupportedOperationException.

- This happens because the CopyOnWriteArrayList disallows modifications (like removing elements) during iteration to
  prevent unexpected behavior.
- The iterator holds a snapshot of the list at the time of its creation, and modifying that snapshot directly isn't
  supported.

# Blocking Queues

A Blocking Queue, such as LinkedBlockingQueue, is a queue that controls thread execution by blocking operations when
certain conditions are met.

- A thread attempting to remove an element from an empty queue will block until an element is available.
- Similarly, if the queue has a fixed capacity, a thread trying to add an element to a full queue will block until space
  becomes available.
- This behavior ensures efficient task management without overwhelming system resources.