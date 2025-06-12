<!-- filepath: /Users/satishAd47/CODING_WORSPACES/Coding_And_System_Design/rish_notes.md -->
# Java & System Design Interview Study Guide

---

## 📑 Table of Contents

- [Java Language Features](#1-java-language-features)
- [SOLID Principles](#solid-principles)
- [Java OOP Pillars](#java-oop-pillars)
- [Abstract Class vs. Interface](#abstract-class-vs-interface)
- [Relationships in Java](#relationships-in-java)
- [Modifiers: Access, Static, Final](#modifiers-access-static-final)
- [Variable Arguments (VARARGS)](#variable-arguments-varargs)
- [String and Immutability](#string-and-immutability)
- [Primitive vs. Reference Data Types](#primitive-vs-reference-data-types)
- [Memory Management](#memory-management)
- [Exception Handling](#exception-handling)
- [Java Streams](#java-streams)
- [Functional Interfaces](#functional-interfaces)
- [Design Patterns](#design-patterns)
- [Custom Annotations](#custom-annotations)
- [Sorting in Java](#sorting-in-java)
- [Working of HashMap](#working-of-hashmap)
- [Multithreading](#multithreading)
- [High-Level Design (HLD)](#high-level-design-hld)
- [Network Protocols](#141-network-protocols)
- [CAP Theorem](#cap-theorem)
- [Scaling a System (From Zero to Million)](#scaling-a-system-from-zero-to-million)
- [Sharding (Horizontal Scaling / Partitioning)](#sharding-horizontal-scaling--partitioning)
- [Key-Value Data Store (High-Level Architecture)](#key-value-data-store-high-level-architecture)
- [SQL vs. NoSQL Databases](#146-sql-vs-nosql-databases)
- [How Database Stores Data](#how-database-stores-data)
- [Database and Data Storage](#database-and-data-storage)
- [Microservice Architecture and System Design](#microservice-architecture-and-system-design)
- [Redis Cache Patterns](#redis-cache-patterns)
- [Exception Handling (Spring)](#exception-handling-1)
- [Leadership and Agile Methodologies](#leadership-and-agile-methodologies)

---

# Java & System Design Interview Study Guide

This guide covers key concepts in Java programming, memory management, concurrency, and system design, drawing information from the provided sources. It aims to provide detailed explanations and examples where possible, making it suitable for interview preparation.

---

## 1. Java Language Features

*   **Java 8 Features**: Introduced major functional programming features.
    *   Lambda expression.
    *   Streams API.
    *   Date-time API.
    *   Optional class to handle null values safely.
    *   Functional Interfaces with default and static methods in interfaces.


*   **Java 17 Features**: Being an LTS (Long-Term Support) release.
    *   More focused on **performance and security improvements**.
    *   **Sealed classes** for controlled inheritance.
    *   **Pattern matching** for switch statements.
    *   A new random generator API.

## SOLID Principles

*   **S - Single Responsibility Principle (SRP)**  
    *Single Responsibility Principle (SRP): A class should have only one reason to change, meaning it should have only one responsibility.
    * **Example:**  
        ```java
        class InvoicePrinter { // Only prints invoices
            void print(Invoice invoice) { ... }
        }
        class InvoiceSaver { // Only saves invoices
            void save(Invoice invoice) { ... }
        }
        ```

*   **O - Open/Closed Principle (OCP)**  
    *Classes should be open for extension, but closed for modification.*
    * **Example:**  
        ```java
        abstract class Shape { abstract double area(); }
        class Circle extends Shape { ... }
        class Square extends Shape { ... }
        // Add new shapes by extending, not modifying Shape
        ```
    * **Memory Aid:**  
        *"Extend, don’t modify."*  
        Think: "OCP = Open for extension, Closed for modification."

*   **L - Liskov Substitution Principle (LSP)**  
    *Subclasses should be substitutable for their base classes without breaking the program.*
    * **Example:**  
        ```java
        class Bird { void fly() { ... } }
        class Sparrow extends Bird { ... }
        // Don’t make Penguin extends Bird if Penguin can’t fly!
        ```
    * **Memory Aid:**  
        *"Child can stand in for parent."*  
        Think: "LSP = Let Subclasses be Parents."

*   **I - Interface Segregation Principle (ISP)**  
    - A class should not be forced to implement interfaces it doesn't use.
    * **Example (Restaurant Employee):**  
        ```java
        interface Waiter { void takeOrder(); }
        interface Chef { void cook(); }
        class RestaurantWaiter implements Waiter { public void takeOrder() { ... } }
        class RestaurantChef implements Chef { public void cook() { ... } }
        // No class is forced to implement both takeOrder and cook if it doesn't need to.
        ```
    * **Memory Aid:**  
        *"Many small interfaces are better than one big one."*  
        Think: "ISP = Interface Should be Petite."

*   **D - Dependency Inversion Principle (DIP)**  
    *Depend on abstractions, not on concrete classes.*
    * **Example:**  
        ```java
        interface Keyboard { ... }
        class WiredKeyboard implements Keyboard { ... }
        class Computer {
            private Keyboard keyboard;
            Computer(Keyboard k) { this.keyboard = k; }
        }
        ```
    * **Memory Aid:**  
        *"High-level code shouldn’t depend on low-level details."*  
        Think: "DIP = Depend on Interfaces/abstractions."

---

**Quick Interview Recap Table:**

| Principle | Key Idea | Example | Memory Aid |
|-----------|----------|---------|------------|
| SRP | One job per class | Separate Printer/Saver | "Single Reason" |
| OCP | Extend, don’t modify | Add new Shape subclass | "Open/Closed" |
| LSP | Subclass can replace parent | Don’t make Penguin a Bird if can’t fly | "Let Subclasses be Parents" |
| ISP | Small, focused interfaces | Printer/Scanner split | "Interface Should be Petite" |
| DIP | Depend on abstractions | Computer uses Keyboard interface | "Depend on Interfaces" |


## Java OOP Pillars

*   **Data Abstraction**:
    *   It hides the internal implementation and shows only the essential functionality.
    *   Can be achieved using **interfaces** and **abstract classes**.
*   **Encapsulation**:
    *   Also known as **data hiding**.
    *   Achieved by declaring variables as **private** and providing **getters and setters**.
*   **Inheritance**:
    *   **Multiple inheritance is not allowed** in Java directly, because the object will not know which parent class method to invoke. It can be achieved through interfaces.
    *   Always, the parent class constructor will be initialized first.
    *   Inside a child constructor, the `super()` keyword will always call the default constructor of the parent class.
    *   **Pros**: Code reusability and polymorphism.
*   **Polymorphism**:
    *   **Compile-time / Static / Overloading**: Occurs within the same class.
    *   **Runtime / Dynamic / Overriding**: Occurs in a child class.

## Abstract Class vs. Interface

*   **Abstract Class**:
    *   Cannot be instantiated – it serves as a blueprint for other classes.
    *   Can have **abstract methods** (will be implemented by subclasses).
    *   Can have **concrete methods** (can have full implementation).
    *   Can have **constructors** (when a child class is instantiated, the super constructor will be called).
    *   Can have **instance variables** (unlike interfaces).
    *   Supports inheritance, but not multiple inheritance like interfaces.
    *   Allows **partial implementation**; a subclass can either implement or remain abstract itself.
*   **Interface**:
    *   Cannot have instance variables, only **constants** (static final variables).
    *   Before Java 8, only abstract methods; in Java 8+, default and static methods are allowed.
    *   No constructors, since they cannot be instantiated.
*   **When to use What**:
    *   **Abstract Class**: Use when multiple related classes share common behavior but still need some method enforcement.
    *   **Interface**: Use when multiple unrelated classes share common behavior but don't share implementation.

## Relationships in Java

*   **Is-a relationship**: Achieved through inheritance.
*   **Has-a relationship**: Whenever an object is used in another class.

## Modifiers: Access, Static, Final

*   **Access Modifiers**:
    *   `public`: Accessible by all.
    *   `private`: Accessible only within the class.
    *   `protected`: Accessible within the package and child class.
    *   `default`: Accessible only within the package.
*   **Static**:
    *   Methods/variables are associated with the class.
    *   Can be accessed only by the class name.
    *   A static method cannot access non-static instance variables and methods.
    *   A static variable is shared among all objects of a class (single copy).
    *   Static methods cannot be overridden.
    *   **When to use**: When a method is only dependent on its arguments (not on instance variables).
*   **Final**:
    *   Once assigned, the value cannot be changed.
    *   A `final` method cannot be overridden.
    *   A `final` class cannot be extended.

## Variable Arguments (VARARGS)

*   Only one varargs can be present in one method.
*   It should be the last argument.

## String and Immutability

*   **String Literal**: A string value written directly in double quotes `("")`.
*   **String Constant Pool (SCP)**: If a new literal is created, Java first checks if it already exists in the SCP. If it exists, Java uses the same object instead of creating a new one. Literals are stored in SCP to avoid duplication.
*   A string created using `new String()` is not stored in the SCP unless `intern()` is called.
*   **Why String is Immutable**:
    *   Changing a single reference would affect all references, which will break consistency.
    *   Memory usage would increase since every modification would create a new instance anyway.
    *   HashMap lookups would become unreliable because their `hashCode()` could change.

## Primitive vs. Reference Data Types

*   **Primitive Data Types**: Stored in **stack memory**.
*   **Reference (Wrapper) Data Types**: Stored in **heap memory**, so they hold memory addresses.
*   Collections work only with objects.
*   **Autoboxing**: Automatic conversion from primitive to wrapper object (e.g., `int x = 5; Integer y = x;`).
*   **Unboxing**: Automatic conversion from wrapper object to primitive (e.g., `Integer m = 5; int n = m;`).

## Memory Management

*   **Stack**:
    *   Stores temporary variables.
    *   Stores primitive data types.
    *   Stores references of heap objects.
    *   Each thread has its own stack memory.
*   **Heap**:
    *   Stores objects.
    *   No particular order for allocating memory.
    *   **Garbage Collector (GC)** is used to delete unreferenced objects from the heap.
    *   Shared among all threads.
    *   It contains the String Pool.
*   **Metaspace (Non-heap)**: Stores class variables and constants.
*   **Garbage Collector (GC) Process**:
    *   Objects first come into the **Eden** space.
    *   When GC is invoked, unreferenced objects are marked and swapped (deleted).
    *   Referenced objects will move to **Survivor (S0)** and then **S1**, and their age will be increased.
*   **GC Versions**:
    *   Serial GC (single thread).
    *   Parallel GC (parallel).
    *   Concurrent Mark Sweep (CMS).
    *   G1 Garbage Collector.

## Exception Handling

*   The hierarchy starts from **`Object`** -> **`Throwable`**.
*   **`Throwable`** branches into **`Error`** and **`Exception`**.
    *   **`Error`**: Related to JVM issues.
        *   Examples: `StackOverflow`, `OutOfMemory`.
    *   **`Exception`**:
        *   **Checked / Compile-time Exceptions**: Must be handled or declared (e.g., with `throws`).
            *   Examples: `ClassNotFoundException`, `IOException`, `EOFException`, `FileNotFoundException`.
        *   **Unchecked / Runtime Exceptions**: Do not require explicit handling or declaration.
            *   Examples: `ClassCastException`, `ArithmeticException`, `NullPointerException`.
*   **`throws` keyword**: Tells that a method *might* throw an exception.
*   **`throw` keyword**: Used to throw an exception explicitly.
*   A `try` block can exist without a `catch` block only if it has a `finally` block.
*   If an exception occurs in a `finally` block, it overrides any exception from the `try` or `catch` block. The best practice is to wrap the `finally` block in `try/catch`.
*   If a `finally` block has a `return` statement, it overrides the return value from the `try/catch` block.
*   **Difference between Error and Exception**:
    *   **Error**:
        *   Represents serious problems that a reasonable application should not try to catch.
        *   Usually related to the JVM environment (e.g., `OutOfMemoryError`, `StackOverflowError`).
        *   Errors are unchecked and indicate issues that are typically outside the application's control.
        *   Recovery is usually not possible; the application is expected to terminate.
        *   **Example:**
            ```java
            public class ErrorExample {
                public static void main(String[] args) {
                    // This will cause StackOverflowError
                    causeError();
                }
                static void causeError() {
                    causeError(); // infinite recursion
                }
            }
            // Output: Exception in thread "main" java.lang.StackOverflowError
            ```
    *   **Exception**:
        *   Represents conditions that a program might want to catch and handle.
        *   Can be checked (must be handled or declared) or unchecked (runtime exceptions).
        *   Exceptions are usually caused by application bugs, invalid user input, or resource issues, and can often be recovered from.
        *   **Checked Exceptions (Compile-time):** Must be handled or declared using `throws`.
            *   Examples: `IOException`, `SQLException`, `ClassNotFoundException`, `FileNotFoundException`.
            *   **Example:**
                ```java
                import java.io.*;
                public class CheckedExceptionExample {
                    public static void main(String[] args) {
                        try {
                            FileReader fr = new FileReader("file.txt");
                        } catch (FileNotFoundException e) {
                            System.out.println("File not found!");
                        }
                    }
                }
                // Output: File not found!
                ```
        *   **Unchecked Exceptions (Runtime):** Do not require explicit handling or declaration.
            *   Examples: `NullPointerException`, `ArrayIndexOutOfBoundsException`, `ArithmeticException`, `ClassCastException`.
            *   **Example:**
                ```java
                public class UncheckedExceptionExample {
                    public static void main(String[] args) {
                        int[] arr = new int[2];
                        System.out.println(arr[5]); // ArrayIndexOutOfBoundsException
                    }
                }
                // Output: Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException
                ```

## Java Streams

*   **Stream Operations**:
    *   Create stream.
    *   Intermediate operation.
    *   Terminal operation.
*   **Different ways to create a Stream**:
    *   From Collection (e.g., `salaryList.stream()`).
    *   From Array (e.g., `Arrays.stream(Array)`).
    *   From Static method (e.g., `Stream.of()`).
    *   From Stream Builder (e.g., `Stream.builder().add(1).build()`).
    *   From Stream.iterate (e.g., `Stream.iterate(1000, (Integer n) -> n + 5000).limit(5)`).
*   **Parallel Stream**: Divides the data into small chunks using the `spliterator()` method, then processes them in parallel using the fork-join pool. This allows multiple threads to process elements concurrently, improving performance for large datasets on multi-core processors.
    *   **Example 1:**
        ```java
        import java.util.Arrays;
        import java.util.List;

        public class ParallelStreamExample {
            public static void main(String[] args) {
                List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
                // Using parallelStream to sum all numbers
                int sum = numbers.parallelStream()
                                 .mapToInt(Integer::intValue)
                                 .sum();
                System.out.println("Sum: " + sum);
            }
        }
        // Output: Sum: 55
        // Note: The processing of elements may happen out of order, but the result is correct.
        ```
    *   **Example 2 (filter and print):**
        ```java
        import java.util.Arrays;
        import java.util.List;

        public class ParallelStreamFilterExample {
            public static void main(String[] args) {
                List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Eve");
                // Print names that start with 'C' or later, in parallel
                names.parallelStream()
                     .filter(name -> name.compareTo("C") >= 0)
                     .forEach(System.out::println);
            }
        }
        // Output: (order may vary)
        // Charlie
        // David
        // Eve
        ```
    *   **When to use:** Parallel streams are beneficial for large collections and CPU-intensive operations, but may add overhead for small datasets or simple tasks.

## Functional Interfaces

*   An interface which has **only one abstract method**. It can have default and static methods.

*   **Predicate Interface (`Predicate<T>`)**: Functional interface with `boolean test(T t)` method. Takes an argument and always returns a boolean.
*   **Function Interface (`Function<T, R>`)**: Functional interface with `R apply(T t)` method. Takes an argument and returns an output.
*   **Consumer Interface (`Consumer<T>`)**: Functional interface with `void accept(T t)` method. Takes an argument and processes it.
*   **Supplier Interface (`Supplier<T>`)**: (Implied from the diagram) No arguments, produces a result.



**Why Functional Interfaces? What Purpose Do They Serve?**

- **Purpose:**  
  Functional interfaces define a single abstract method, making them the foundation for lambda expressions and method references in Java. They enable you to treat behavior as data—passing code as an argument, returning it from methods, or storing it in variables.
- **Why Needed:**  
  Before Java 8, you had to use anonymous inner classes for callbacks or event handling, which was verbose. Functional interfaces allow concise, readable code using lambdas, making APIs like Streams, Collections, and concurrency utilities more expressive and flexible.
- **Where Used:**  
  - **Streams API:** Filtering, mapping, reducing collections.
  - **Event handling:** GUI listeners, asynchronous callbacks.
  - **Custom business logic:** Passing strategies, predicates, or actions to methods.

**Example Usage:**

1. **Using Predicate in Streams:**
    ```java
    List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
    names.stream()
         .filter(name -> name.startsWith("A")) // Predicate<String>
         .forEach(System.out::println);
    // Output: Alice
    ```

2. **Passing Behavior to a Method:**
    ```java
    // Custom functional interface
    @FunctionalInterface
    interface MathOperation {
        int operate(int a, int b);
    }

    // Method that takes a functional interface
    int compute(int a, int b, MathOperation op) {
        return op.operate(a, b);
    }

    // Usage with lambda
    int sum = compute(5, 3, (x, y) -> x + y); // sum = 8
    ```

3. **Runnable for Threading:**
    ```java
    Runnable task = () -> System.out.println("Running in a thread!");
    new Thread(task).start();
    ```

**Summary Table:**

| Interface         | Abstract Method         | Example Usage                        |
|-------------------|------------------------|--------------------------------------|
| Predicate<T>      | boolean test(T t)      | Filtering in streams                 |
| Function<T, R>    | R apply(T t)           | Mapping values in streams            |
| Consumer<T>       | void accept(T t)       | Performing actions (e.g., printing)  |
| Supplier<T>       | T get()                | Supplying values (e.g., random nums) |
| Runnable          | void run()             | Thread/task execution                |





## Design Patterns

## Design Patterns: Summary Table

Below is a summary of the most important design patterns, grouped by category, with a one-line description and a real-world analogy for each. Use this as a quick reference before interviews.

| Pattern Group   | Pattern Name         | Purpose/Intent                                              | Real-World Analogy                                  |
|-----------------|---------------------|-------------------------------------------------------------|-----------------------------------------------------|
| **Creational**  | Singleton           | Only one instance per JVM                                   | President of a country (only one at a time)         |
|                 | Factory             | Create objects without exposing instantiation logic         | Car factory producing different car models          |
|                 | Abstract Factory    | Create families of related objects                          | Furniture set factory (modern/classic sets)         |
|                 | Builder             | Step-by-step construction of complex objects                | Restaurant meal builder (choose starter, main, etc) |
|                 | Prototype           | Clone objects instead of creating new ones                  | Photocopying a document                             |
| **Structural**  | Adapter             | Make incompatible interfaces work together                  | Power plug adapter (US to EU plug)                  |
|                 | Decorator           | Add new behavior to objects dynamically                     | Adding toppings to a pizza                          |
|                 | Proxy               | Control access to another object                            | Security guard controlling building access          |
|                 | Facade              | Simplify interface to a complex subsystem                   | Universal remote controlling multiple devices       |
|                 | Composite           | Treat individual and composite objects uniformly            | Folder structure (folders and files)                |
|                 | Bridge              | Decouple abstraction from implementation                    | TV remote (remote and TV are independent)           |
|                 | Flyweight           | Share objects to save memory                                | Shared bike system in a city                        |
| **Behavioral**  | Observer            | Notify dependents of state changes                          | News agency and subscribers                         |
|                 | Strategy            | Select algorithm at runtime                                 | Payment method selection (card, cash, UPI)          |
|                 | Command             | Encapsulate a request as an object                          | Restaurant order slip given to chef                 |
|                 | Template Method     | Skeleton of algorithm, steps deferred to subclasses         | Coffee machine (fixed steps, some customizable)     |
|                 | State               | Change behavior when internal state changes                 | Traffic light changing states                       |
|                 | Chain of Responsibility | Pass request along a chain of handlers                  | Customer support escalation                         |
|                 | Iterator            | Sequentially access elements of a collection                | TV remote channel surfing                           |
|                 | Mediator            | Coordinate interaction between objects                      | Air traffic controller                              |
|                 | Memento             | Capture and restore object state                            | Undo feature in text editors                        |
|                 | Visitor             | Separate algorithm from object structure                    | Tax auditor visiting different businesses           |
|                 | Interpreter         | Implement grammar for interpreting sentences                | Calculator parsing and evaluating expressions       |

---



## Custom Annotations

*   `@Target`: Specifies where an annotation can be used (e.g., `ElementType.TYPE` for classes or interfaces).
*   `@AnnotationType`: An annotation that can be used on top of another annotation.
*   **`@Retention`**: Dictates how an annotation will be stored in Java.
    *   `RetentionPolicy.SOURCE`: Will not be a part of the `.class` file.
    *   `RetentionPolicy.CLASS`: Will be recorded in the `.class` file but will not be used at runtime.
    *   `RetentionPolicy.RUNTIME`: Will be recorded in the `.class` files and available during runtime.
*   **`@Inherited`**: Used when you want the annotation specified on a parent class to be used by its child class.

## Sorting in Java

*   **`Arrays.sort(arr)`**:
    *   Only works for primitive arrays.
    *   Only sorts in ascending order.
        *   **Multiple sorting strategies** are supported.

## Working of HashMap

*   A HashMap is a set of **buckets**, which are typically implemented as an array of linked lists.
*   It computes the `hashCode()` of a key to find the appropriate bucket.
*   **Inserting a pair**:
    *   If the bucket is empty, the pair is stored.
    *   If the bucket is not empty, the pair is stored in a linked list or tree within the same bucket.
*   **Collision Handling**:
    *   It first uses a **linked list** to handle collisions.
    *   It only converts to a **tree** (red-black tree) if a bucket contains **more than 8 elements**.
    *   If a bucket shrinks back to **below 6 elements**, it converts back to a linked list.
*   **Rehashing**:
    *   Occurs when the number of elements exceeds the **load factor** multiplied by the current capacity.
    *   Default load factor = `0.75`.
    *   Default capacity = `16`.
    *   Rehashing occurs when `12` elements (`16 * 0.75`) are added, doubling the capacity to `32`, then `64`, `128`, and so on.
*   **`equals()` method**:
    *   When overridden, it is used to compare keys inside a bucket when a collision occurs.
    *   During a `put(K,V)` operation, `equals()` is called.
        *   If the key exists, it updates the value.
        *   If the key does not exist, it adds a new entry.
    *   If the `equals()` method is **not overridden** (e.g., for a custom `Employee` object used as a key), even if objects have the same content, they will be treated as different keys because Java falls back to the default implementation which checks memory reference.

## Multithreading

![alt text](static/thread.png)

*   **Processes** do not share memory resources.
*   **JVM Instance Architecture**:
    *   **Heap Memory**: Stores new objects and is **shared** among all threads within a JVM instance.
    *   **Code Segment**: Contains the compiled **BYTECODE** (machine code).
    *   **Data Segment**: Contains global and static variables. All threads under the same process **share the same data segment**.
    *   **Per-Thread Components**:
        *   **Stack**: Each thread has its own stack. It manages method calls and local variables.
        *   **Register**: Helps in context switching. When the CPU register holds the instruction of Thread 1, Thread 1 goes to sleep for some time while Thread 2 runs. Once Thread 2 completes its instructions, they are loaded back to the Thread register for Thread 1 to resume.
        *   **Counter (Program Counter)**: It points to the instruction which is getting executed. It increments its counter after successfully executing an instruction. It holds the address of the code segment instruction that is supposed to be executed by the thread.
*   **Ways to create a Thread**:
    *   **1) Implementing `Runnable` Interface**:
        *   Create a class which implements `Runnable` interface.
        *   Override the `public void run()` method.
        *   Create an object of the class that implements `Runnable`.
        *   Pass the `Runnable` object to the `Thread` constructor (e.g., `Thread t = new Thread(mtcObj);`).
        *   Call `t.start()`.
    *   **2) Extending `Thread` class**:
        *   Create a class that extends `Thread`.
        *   Override the `run` method and put logic inside.
        *   In the main class, create an instance of the extended class and call the `start()` method.
*   **Why two ways**:
    *   In Java, multiple inheritance is not supported, so extending the `Thread` class prevents a class from extending another class.
    *   Using the `Runnable` interface allows a class to extend another class and still have the functionality of multithreading.
*   **Thread Lifecycle**:
    *   **New**: Thread is created.
    *   **Runnable**: Thread is ready to run, waiting for CPU. (Transitions from New via `start()` method).
    *   **Running**: Thread is currently executing (has acquired CPU). (Transitions from Runnable via `run()` method).
    *   **Blocked**: Waiting for an I/O task or to acquire a lock. (Transitions from Running).
    *   **Waiting**: Waiting indefinitely for another thread to perform a particular action (e.g., `join()`, `wait()`). (Transitions from Running via `yield()` or `wait()`).
    *   **Timed Waiting**: Waiting for a specified period of time (e.g., `sleep(long)`, `wait(long)`). (Transitions from Running via `sleep()`).
    *   **Terminated**: Thread has finished execution. (Can transition from Running, Blocked, Waiting, Timed Waiting via `stop()`).
    *   Blocked, Waiting, and Timed Waiting states can transition back to Runnable.
*   **Monitor Locks**:
    *   At the **Blocked** and **Waiting** states, threads release all **MONITOR LOCKS**.
    *   At the **Timed Waiting** state, threads **do NOT release** any MONITOR LOCKS.
    *   Every object has a monitor lock. It ensures that only one thread can enter a particular section of code (a synchronized block or method) at a time.

## High-Level Design (HLD)

## 14.1 Network Protocols

**Application Layer (AL)**:
*   **Client-side**: HTTP (Browsing), FTP (File Transfer), SMTP/IMAP (Email), WebSockets (Whatsapp).
*   **Peer-to-peer**: WebRTC (Streaming).

**Transport Layer (TL)**:

| Feature           | TCP/IP                               | UDP/IC                                |
| :---------------- | :----------------------------------- | :------------------------------------ |
| **Connection**    | Virtual connection (connection-oriented) | No connection (connectionless)   |
| **Data Transfer** | Sequential data transfer        | Parallel data transfer           |
| **Reliability**   | Supports retry (retransmission) | No retry                         |
| **Acknowledgement** | Acknowledge                     | No acknowledge                   |
| **Speed**         | Slower (due to overhead)             | **Faster** (minimal overhead)    |

## CAP Theorem

*   States that a distributed system can guarantee at most **two of the following three properties** at the same time:
    *   **C - Consistency**: Every read receives the most recent write or an error. (Ensures correct balance in applications like banking).
    *   **A - Availability**: Every request gets a response, but the data might not be the latest. (The system always responds, even if network failures occur).
    *   **P - Partition Tolerance**: The system continues to work despite network failures. (The system works even if network issues exist).
*   **Trade-offs**:
    *   **CP (Consistency + Partition Tolerance)**: Example: MongoDB. The system ensures consistency even with network partitions but may reject some requests, thus not always available.
    *   **AP (Availability + Partition Tolerance)**: Example: DynamoDB. The system always responds even with network partitions but might show outdated data (not always consistent).
    *   **CA (Consistency + Availability)**: The system is always available and consistent, but only works if the network never fails (not practical in real-world distributed systems where partitions are unavoidable).
*   **Partition Tolerance (P) is unavoidable** in distributed systems, so there is always a trade-off between Consistency (C) and Availability (A).

## Scaling a System (From Zero to Million)

*   **1) Application & DB server separation**: Removes dependency between application and database servers.
*   **2) Load Balancers**: Distribute traffic and provide a layer of security.
*   **3) Database Replication**:
    *   A **Master DB** handles all create/update operations.
    *   **Slave DB(s)** handle all fetch operations.
    *   Data is synced from the Master to the Slave(s).
    *   If the Master DB goes down, one of the Slave DBs will be promoted as Master.
*   **4) Cache**: First checks in cache to reduce expensive database calls.
*   **5) CDN (Content Delivery Network)**: Helps in caching.
    *   Initially, data is taken from the nearest CDN. If not available, it takes from another CDN, or else goes to the original server.
*   **6) Data Centre**: Different data centers based on geographical location. If one goes down, traffic reads out to the nearest one. Database data is always in sync across data centers.
*   **7) Messaging Queue**: Used to achieve asynchronous flow.
*   **8) Database Scaling**:
    *   **Vertical Scaling**: Increasing CPU and RAM of a single server.
    *   **Horizontal Scaling**: Increasing the number of NODES, primarily achieved by **Sharding**.

## Sharding (Horizontal Scaling / Partitioning)

*   A way to split large data into smaller, manageable parts and store them across multiple servers.
*   Helps in **scalability and performance**.
*   **Types of Sharding**:
    *   **1) Range-based**: Data is divided based on a range of values (e.g., IDs 0-100 in one shard, 101-200 in another).
        *   **Problem**: Uneven load if some ranges have more data.
        *   **Fix**:
            *   **Dynamic Rebalancing**: If one shard gets overloaded, data is migrated to another.
            *   **Sub-sharding**: Large shards are split into smaller ones to balance the load.
    *   **2) Hash-based**: A hash function assigns each data entry to a specific shard.
        *   **Problem**: Adding/removing a shard requires the entire dataset to be rehashed, which is inefficient.
        *   **Fix (Consistent Hashing)**:
            *   When adding/removing a shard, only a few keys move.
            *   If a shard fails, a nearby shard will take over its keys.
            *   New shards can be added without major changes.
    *   **3) Geo-Based**: Data is split based on location (e.g., users in the USA go to one shard, users from the EU to another).

## Key-Value Data Store (High-Level Architecture)

*   A system will be divided into multiple nodes, each storing a subset of key-value pairs.
*   To distribute data efficiently, **consistent hashing** will be used, which helps dynamically add or remove nodes without major rebalancing.
*   For **fault tolerance**, **replication** will be used, ensuring each key is stored in multiple nodes. This allows the system to remain available even if some nodes fail.

**Read & Write Flows**:
*   **For a write operation**:
    *   The **key will be hashed** and assigned to the appropriate node.
    *   Data is **replicated to multiple nodes** based on a replication factor.
    *   Use **Hinted Handoff** for temporary storage if a replica is down.
*   **For a read operation**:
    *   The request will go to **multiple replicas**.
    *   A **quorum-based read (W+R > N)** will ensure consistency. (W = number of writes required to succeed, R = number of reads required to succeed, N = total replicas).

**For failures**:
*   **Vector Clock** will be used to resolve conflicts (determine which data version is the latest).
*   **Merkle trees** will be used to sync the data between replicas.

**Scaling, Consistency, Availability, Performance Considerations**:
*   **Scaling**: Ensure horizontal scaling by sharding data using consistent hashing (to avoid hot spots). Use a load balancer to distribute incoming traffic to the right node.
*   **Consistency & Availability**: Design for **high availability**, which follows the **AP model** of the CAP theorem. This means there will be less consistency, but tunable consistency can be achieved using **quorum-based reads/writes (W+R > N)**.
*   **Performance**: Use **caching for fast reads**.

## 14.6 SQL vs. NoSQL Databases

| Feature          | SQL (Relational Databases)             | NoSQL (Non-relational Databases)                 |
| :--------------- | :------------------------------------- | :----------------------------------------------- |
| **Queries**      | Supports **complex queries**      | Typically for **basic queries**             |
| **Schema**       | Tightly dependent (structured schema) | Not much linking (flexible/schema-less)     |
| **Data Integrity** | Adheres to **ACID properties** (Atomicity, Consistency, Isolation, Durability) | Adheres to **BASE properties** (Basically Available, Soft state, Eventually consistent) |
| **Consistency**  | **No inconsistency** (strong consistency) | Highly Available/Fast with **some inconsistency** (eventual consistency) |
| **Scaling**      | **Vertical scaling** (scale up)   | **Horizontal scaling** (scale out)          |

## How Database Stores Data

*   Database (DB) stores data hierarchically: DB -> Tables -> Rows -> Pages -> Blocks -> Disk.
*   **Block**: The smallest unit of disk storage (typically 4KB - 16KB).
*   **Pages**: The basic unit of storage in RDBMS (typically 8KB - 16KB).
*   **Internal Storage Breakdown (Blocks)**:
    *   DB stores data in fixed-size blocks on the disk.
    *   A block contains multiple pages.
    *   When reading data, the DB fetches the entire block into memory.


Here are study notes generated from the provided sources:

### **Database and Data Storage**

*   **Pages**:
    *   Each page contains:
        *   **Page Header**: Includes metadata like pagetype and table ID.
        *   **Data**: The actual table data.
        *   **Free Space**: Reserved for future row updates.
        *   **Row Directory**: Keeps track of row positions.
    *   **Large Storage in Pages**:
        *   If a row is too large for a single page, it uses **Overflow pages** for large values.
        *   **LOB storage** is used for very large objects.
    *   **Mapping**: Pages are mapped to blocks, e.g., Page 1 to Block 1, Page 2 to Block 1.

*   **Indexing**:
    *   **Performance Comparison**:
        *   Without indexing: O(N) complexity.
        *   With indexing: O(logN) complexity, typically using a **B+Tree**.
    *   (Source 2 provides a visual representation of how a B+Tree is constructed and balanced with a sequence of numbers).

*   **Normalization**:
    *   **1NF (First Normal Form)**:
        *   Every column must have a **single value**.
        *   Each row must be **unique**.
    *   **2NF (Second Normal Form)**:
        *   Must be in **1NF**.
        *   **Non-Key columns**: Columns that are not part of the primary or candidate key.
        *   **Candidate Key**: A set of columns that uniquely identify a row.
        *   Rule: If non-key columns are partially dependent on a candidate key, they should be split into different tables.
        *   Each table should have **primary and foreign keys**.
    *   **3NF (Third Normal Form)**:
        *   Should be in **2NF**.
        *   Requires the removal of **transitive dependency** (where A determines B, and B determines C, meaning A indirectly determines C).
    *   **Denormalization**: (Mentioned as a concept related to normalization).
    *   **Anomalies** (Problems addressed by normalization):
        *   **Insert Anomaly**: Adding new data might require introducing nulls if related information (e.g., product data in an order/customer table) isn't yet available.
        *   **Delete Anomaly**: Deleting specific data (e.g., order details) might unintentionally delete other related, important data.
        *   **Update Anomaly**: Updating a piece of data (e.g., product price) might require changes in many places, leading to inconsistencies if not all instances are updated.

## **Microservice Architecture and System Design**

*   **API Gateway**:
    *   Acts as a **central entry point** for client requests in a microservice architecture.
    *   Handles various functions: **authentication, request routing, rate limiting, and response transformation**.
    *   **Distinction from Load Balancers**: Unlike load balancers which only distribute traffic, an API Gateway can **enforce security policies** and **orchestrate multiple backend services** before responding to the client.
    *   **Examples**: Routing `/orders` to an order microservice or `/users` to a user microservice.
    *   **Key Concepts**: API Composition (client-dependent), Rate Limiting, Service Discovery.
    *   (Source 5 visually places API Gateway between DNS/Client and Service Discovery/Load Balancers).

*   **Load Balancer**:
    *   Designed to **distribute incoming network traffic** across multiple backend servers.
    *   Ensures **high availability and scalability**.
    *   **Benefit**: Prevents a single application instance from being overwhelmed, improving system reliability.
    *   Supports **Sticky Sessions**.
    *   (Source 5 shows Load Balancers distributing traffic to microservices within an Availability Zone).

*   **Current Microservice Architecture Drawbacks**:
    *   Each microservice often has its own service client for routing, leading to:
        *   Increased Latency.
        *   Extra Deployment and Maintenance overhead.
        *   Not truly decoupled.

*   **Better Approaches for Microservice Communication**:
    *   **Direct Inter-service Communication**: Using **FEIGN CLIENT IN SB**.
    *   **Event-Driven Communication**: Utilizing **Kafka**.
    *   **Service Mesh**: Using **ISTIO**.
        *   Recommended if **security is a concern**.
        *   Provides **automatic retries, security, and monitoring**.

*   **System Architecture Diagram**:
    *   Illustrates a multi-region, multi-availability zone setup:
        *   **Client** interacts with **DNS**.
        *   DNS directs to an **API Gateway**.
        *   API Gateway interacts with **Service Discovery (SD)**.
        *   SD directs requests to **Load Balancers (LB)**.
        *   LBs distribute requests to **Microservices** (e.g., Microservice 1, Microservice 2) within an **Availability Zone (AZ1)** in **Region 1**.
        *   The architecture also includes another **Availability Zone (AZ2)** in Region 1 and **Region 2**, indicating disaster recovery or multi-region deployment.

## **Redis Cache Patterns**

*   **1) Cache Aside**:
    *   **Process**: Application first checks the cache.
        *   **Cache Hit**: If data is found, return it from the cache.
        *   **Cache Miss**: If not found, call the DB, then store the data back to the cache and return.
    *   **Pros**:
        *   If the cache is down, requests will still function by directly hitting the DB.
        *   Caching can be cheaper than storing all data in the main DB.
    *   **Cons**:
        *   Always a cache miss for new data.
        *   Risk of **inconsistency** between cache and DB.

*   **2) Read Through**:
    *   **Process**: Application first checks the cache.
        *   **Cache Hit**: If data is found, return it.
        *   **Cache Miss**: If not found, the cache library itself fetches data from the DB, stores it back to the cache, and returns it.
    *   **Pros**: Logic for fetching data from the DB and updating the cache is abstracted away from the application.
    *   **Cons**:
        *   Always a cache miss for new data.
        *   Potential for **inconsistency** between cache and DB.
        *   The cache document structure should ideally be the same as the DB structure.

*   **3) Write Around**:
    *   **Process**: Data is written directly into the DB. When writing, it invalidates (removes) the old cached data.
    *   **Pros**: Resolves data inconsistency.

*   **4) Write Through**:
    *   **Process**: Data is first written into the cache, and then written into the DB **synchronously**.
    *   **Pros**: Resolves inconsistency (always in sync).
    *   **Cons**: If one write operation fails (either to cache or DB), both will be **rolled back**.

*   **5) Write Back (or Write Behind)**:
    *   **Process**: Data is first written into the cache, and then **asynchronously** written into the DB using a queue.

## **Design Patterns**

*   **Singleton Pattern**: Ensures that a class has only one instance and provides a global point of access to it.
    *   **1) Eager Initialization**:
        *   The instance is created at the time of class loading.
        *   **Example (Direct)**: `private static SingletonEager instance = new SingletonEager();`.
        *   **Example (With Static Block)**: `static { instance = new SingletonStatic(); }`.
    *   **2) Lazy Initialization**:
        *   The instance is created only when the `getInstance()` method is called for the first time.
        *   **Note**: This implementation is **NOT thread-safe**.
    *   **3) Thread Safe Initialization**:
        *   Achieved by using the `synchronized` keyword on the `getInstance()` method.
    *   **4) Bill Pugh Initialization**:
        *   Uses an **inner static class** to hold the instance.
        *   The JVM loads the inner class only when `getInstance()` is accessed, ensuring **lazy loading** and **thread safety WITHOUT explicit synchronization**.

*   **Factory Pattern**: (Mentioned as a design pattern, but no details are provided).

## **Exception Handling**

*   **Exception Handling Flow**:
    *   When **Any Exception** occurs, it goes to the **Exception Handler Resolver**.
    *   **If a handler is present (YES)**: The exception is handled, and a return occurs.
    *   **If no handler is present (NO)**: It checks if it's handled by JBoss.
        *   **If YES**: It goes to a **Default Handler**, and a return occurs.
        *   **If NO**: It proceeds to set a **Response Status (Resolve)**, followed by **Default Error Handling**, and then a return occurs.
    *   **Notes**: Only Controller Handles/Exception Handler Resolver are handled by this flow. Only `@ResponseStatus` is handled directly in this context.

*   **Exception Handling Priority**:
    *   **1) Priority: Inside Controllers**:
        *   Handled using `@ExceptionHandler(Custom_Error.class)` annotation on a private method within the controller.
    *   **2) Priority: Globally**:
        *   Handled using `@ControllerAdvice` annotation on a public class (e.g., `Controller Exception`), with `@ExceptionHandler(Custom_Java.class)` on a private method inside that class.

## **Leadership and Agile Methodologies**

*   **Leadership Aspects**:
    *   Tough decisions.
    *   Clear communications.
    *   Conflict resolution.
    *   Handling underperforming individuals.
    *   Taking responsibility for failures.
    *   Managing sudden changes.
    *   Fostering collaboration in teams.
    *   (Also poses the question: "Major challenge?").

*   **Agile Concepts**:
    *   Handling **changing requirements**.
    *   Team estimation using **stories**.
    *   Challenges faced in an agile environment.
    *   Ensuring effective **collaboration**.
    *   Managing **dependencies** between teams.
    *   Using **metrics**.
    *   Addressing situations where the **sprint goal is at risk**.
    *   Resolving **conflicts**.
    *   Managing **changing priorities**.
    *   Defining and achieving **success**.
    *   Working with a **distributed team**.
    *   Embracing **continuous improvement**.
    *   Addressing situations where the team is **struggling**.
    *   Implementing **best practices**.