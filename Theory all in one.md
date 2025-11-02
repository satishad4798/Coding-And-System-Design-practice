## HashMap Overview

- Initially, a HashMap has a default capacity of 16 entries.
- HashMap capacities are always powers of 2 (e.g., 2, 4, 8, 16, etc.).
- Each bucket has a threshold limit of 8 entries.
- When a bucket exceeds this limit, its linked list is transformed into a balanced tree to achieve O(log n) lookup performance.

### When Resizing/Rehashing Happens

- Resizing/Rehashing occurs when the number of entries exceeds `capacity × load factor`.
- By default, the load factor is 0.75.
- Example: If the initial capacity is 16, resizing happens after inserting the 13th entry (16 × 0.75 = 12).
- During resizing, the capacity doubles and all entries are rehashed into the new bucket array.




## 🚀 SQL vs NoSQL (Scalability Perspective)

### **SQL (Relational Database Management Systems - RDBMS)**
- **Structured Data:** Uses fixed schema (tables, rows, columns).
- **Vertical Scaling:** Scale-up by increasing resources (CPU, RAM, SSD) on a single server.
- **ACID Transactions:** Ensures strong consistency and reliability.
- **Complex Queries:** Supports joins, aggregations, and relationships.
- **Use Cases:** Banking, ERP systems, e-commerce orders.

### **NoSQL (Not Only SQL)**
- **Flexible Schema:** Supports JSON, key-value, document, graph, and columnar formats.
- **Horizontal Scaling:** Scale-out by adding more servers/nodes.
- **High Availability & Partition Tolerance:** Follows CAP theorem principles.
- **Eventual Consistency:** May not always guarantee strict ACID compliance.
- **Use Cases:** Social media feeds, IoT, chat apps, real-time analytics.

> **Interview Tip:**  
> - Choose **SQL** for transaction-heavy, consistent, relational workloads.  
> - Choose **NoSQL** for scalable, high-traffic, unstructured/semi-structured workloads.  
> - Many companies use a **polyglot approach** (mix of both).


---

## 📦 Kafka: Serialization & Deserialization

### **Serialization in Kafka**
Serialization converts objects (Java objects, JSON, Avro, etc.) into byte arrays, enabling Kafka to store and transmit data as bytes.


**Why Serialization?**
- **Language Agnostic:** Kafka clients in different languages can communicate.
- **Efficient Transmission:** Binary formats save bandwidth and storage.
- **Schema Evolution:** Formats like Avro/Protobuf allow data structure changes over time.
- **Interoperability:** Producers and consumers can use different languages if they agree on the format.

> **Producer:** Serializes key and value before sending to Kafka.

### **Deserialization in Kafka**
Deserialization converts byte arrays from Kafka back into usable objects for consumers.

- Consumers must use the same format as producers to reconstruct data correctly.

---

**Summary Table**

| Process         | Purpose                                      | Example Formats      |
|-----------------|----------------------------------------------|---------------------|
| Serialization   | Object → Byte Array (for sending to Kafka)   | JSON, Avro, Protobuf|
| Deserialization | Byte Array → Object (for reading from Kafka) | JSON, Avro, Protobuf|

---

## 🌀 AOP (Aspect-Oriented Programming) in Spring

### **Key Annotations / Methods**

| Annotation / Method      | Usage                                                                 |
|------------------------- |-----------------------------------------------------------------------|
| `@Aspect`                | Declares a class as an Aspect                                         |
| `@Before`                | Runs before the target method execution                               |
| `@After`                 | Runs after the target method execution (always, even if exception)    |
| `@AfterReturning`        | Runs after successful execution of target method                      |
| `@AfterThrowing`         | Runs only if an exception is thrown                                   |
| `@Around`                | Runs before and after method execution (can modify args/return values)|
| `JoinPoint`              | Provides metadata about the method being executed                     |
| `ProceedingJoinPoint`    | Used inside `@Around` to control method execution                     |
| `Pointcut`               | Defines where (which methods/classes) the aspect applies              |

#### **Example: Logging Aspect in Spring**

```java
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

   // Pointcut: apply to all methods in service package
   @Pointcut("execution(* com.example.service.*.*(..))")
   public void serviceMethods() {}

   // Before advice
   @Before("serviceMethods()")
   public void logBefore(org.aspectj.lang.JoinPoint joinPoint) {
      System.out.println("Before: " + joinPoint.getSignature().getName());
   }

   // AfterReturning advice
   @AfterReturning(pointcut = "serviceMethods()", returning = "result")
   public void logAfterReturning(org.aspectj.lang.JoinPoint joinPoint, Object result) {
      System.out.println("After Returning from " + joinPoint.getSignature().getName() +
                     " with result = " + result);
   }

   // AfterThrowing advice
   @AfterThrowing(pointcut = "serviceMethods()", throwing = "ex")
   public void logAfterThrowing(org.aspectj.lang.JoinPoint joinPoint, Exception ex) {
      System.out.println("Exception in " + joinPoint.getSignature().getName() + ": " + ex.getMessage());
   }

   // Around advice
   @Around("serviceMethods()")
   public Object logAround(ProceedingJoinPoint pjp) throws Throwable {
      System.out.println("Around - Before: " + pjp.getSignature().getName());
      Object result = pjp.proceed();  // proceed with actual method
      System.out.println("Around - After: " + pjp.getSignature().getName());
      return result;
   }
}
```

---

### **Typical Interview Questions (SDE-2 Level)**

- **What is AOP, and why is it used in Spring?**  
  Aspect-Oriented Programming separates cross-cutting concerns (logging, caching, security, transactions) from business logic.

- **Difference between @Around, @Before, @After?**  
  - `@Before`: Runs before method.  
  - `@After`: Always runs after method (success/failure).  
  - `@AfterReturning`: Runs only if success.  
  - `@AfterThrowing`: Runs only on exception.  
  - `@Around`: Can wrap, modify args/return, decide whether to proceed.

- **What is a Pointcut? How do you define one?**  
  Pointcut defines where the aspect applies.  
  - Example: `execution(* com.example.service.*.*(..))` (all methods in service package)  
  - Example: `@annotation(org.springframework.web.bind.annotation.GetMapping)` (all methods with `@GetMapping`)

- **Can we change method arguments/return value in AOP?**  
  Yes, only with `@Around` using `ProceedingJoinPoint`.

- **Difference between Spring AOP and AspectJ?**  
  - Spring AOP: Proxy-based (runtime weaving), works only with Spring beans.  
  - AspectJ: Compile-time/load-time weaving, more powerful, works outside Spring.

- **How is transaction management related to AOP in Spring?**  
  `@Transactional` uses AOP to wrap methods with transaction logic.

- **Limitations of Spring AOP?**  
  - Only supports method-level interception (not field/constructor).  
  - Only works with Spring-managed beans.  
  - By default, only public methods are proxied.

- **What if two aspects apply to the same method?**  
  Use `@Order` annotation to define precedence.

---

## SQL (Relational Databases)

### 1. Normalization & Schema Design
- **1NF, 2NF, 3NF** (and when to denormalize).
- Why **joins are expensive** in large-scale systems.
- When you would use **denormalization** for read-heavy workloads.

### 2. Indexes
- **Types:** B-Tree, Hash, Composite, Covering indexes.
- When to use **clustered vs non-clustered** index.
- **Tradeoffs:** read vs write performance.

### 3. Transactions
- **ACID properties** (Atomicity, Consistency, Isolation, Durability).
- **Isolation levels:** Read Uncommitted, Read Committed, Repeatable Read, Serializable.
- **Problems:** dirty reads, phantom reads, lost updates.

### 4. Joins and Query Performance
- **INNER, LEFT, RIGHT, FULL** joins.
- Why **joins don’t scale** in distributed systems (HLD implication).
- How to reduce query cost with **indexes or pre-computation**.

### 5. Sharding & Replication
- **Horizontal vs vertical scaling**.
- **Primary-Replica setup** for scaling reads.
- **Sharding strategies:** range-based, hash-based, consistent hashing.
- **Trade-offs:** cross-shard queries.

### 6. Caching & Materialized Views
- Using **Redis/Memcached** to reduce DB load.
- **Materialized views** for fast reporting.

---

## 🔹 NoSQL (Non-Relational Databases)

### 1. Types of NoSQL
- **Key-Value Store** (Redis, DynamoDB) → caching, session storage.
- **Document Store** (MongoDB, Couchbase) → flexible schema, JSON-based apps.
- **Column Store** (Cassandra, HBase) → time-series, large-scale analytics.
- **Graph DB** (Neo4j, JanusGraph) → social networks, recommendation systems.

### 2. CAP Theorem
- **Consistency, Availability, Partition Tolerance**.
- Why you can’t have all three.
- **Examples:** Cassandra (AP), MongoDB (CP by default).

### 3. Eventual Consistency
- **Read-your-writes, monotonic reads, strong vs eventual consistency**.
- **Real-world example:** Amazon Dynamo-style quorum reads/writes.

### 4. Data Modeling in NoSQL
- **Query-driven design** (model based on access patterns).
- **Embedding vs referencing** in MongoDB.
- **Wide-row design** in Cassandra.

### 5. Secondary Indexes
- **Pros/cons in NoSQL** (can slow down writes).
- When to **denormalize instead**.

### 6. Replication & Sharding
- How **MongoDB replica sets** work.
- **Cassandra’s partitioners and replication factor**.
- **Trade-offs** between write-heavy vs read-heavy workloads.

---

## 🔹 Cross-Cutting Topics (SQL + NoSQL)

- **When to choose SQL vs NoSQL:**
    - **SQL** → complex relationships, strong consistency, financial systems.
    - **NoSQL** → large-scale, high availability, flexible schema, event data.
- **Polyglot Persistence** → using SQL + NoSQL together.
- **Data partitioning and locality** → minimize cross-node joins.
- **Caching strategy with DBs** (write-through, write-back, write-around)

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