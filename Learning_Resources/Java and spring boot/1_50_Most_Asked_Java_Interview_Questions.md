# 50 Most Asked Java Interview Questions (0-3 Years Experience)

## Core Java Basics
- What are the key features of Java?
- Explain the concept of JVM, JRE, and JDK.
- What is the difference between `==` and `.equals()` in Java?

  **Answer:**
  - `==` checks if two references point to the same object in memory (reference equality).
  - `.equals()` checks if two objects are logically equal (content equality), as defined by the class’s `equals()` method.

  **Example:**
  ```java
  String a = new String("hello");
  String b = new String("hello");

  System.out.println(a == b);        // false (different objects in memory)
  System.out.println(a.equals(b));   // true  (same content)
  ```

  **Summary:**
  - Use `==` for reference comparison.
  - Use `.equals()` for value/content comparison.
  - For objects like `String`, `Integer`, and custom classes (with overridden `equals()`), always use `.equals()` to compare values.

- Explain the concept of `final`, `finally`, and `finalize`.

  **Answer:**
  - `final`: A keyword used for variables, methods, and classes.
    - A final variable cannot be reassigned.
    - A final method cannot be overridden by subclasses.
    - A final class cannot be subclassed (inherited).
    
    **Example:**
    ```java
    final int x = 10;
    // x = 20; // Error: cannot assign a value to final variable

    final class MyClass {}
    // class SubClass extends MyClass {} // Error: cannot inherit from final class
    ```

  - `finally`: A block used in exception handling. Code inside `finally` always executes after the `try` and `catch` blocks, regardless of whether an exception was thrown or caught. It is typically used to release resources.
    
    **Example:**
    ```java
    try {
        int a = 5 / 0;
    } catch (ArithmeticException e) {
        System.out.println("Exception caught");
    } finally {
        System.out.println("This will always execute");
    }
    ```

  - `finalize()`: A method defined in the `Object` class. It is called by the garbage collector before an object is destroyed. It is rarely used in modern Java (deprecated in Java 9 and removed in Java 18).
    
    **Example:**
    ```java
    @Override
    protected void finalize() throws Throwable {
        System.out.println("Object is being garbage collected");
    }
    ```

  **Summary:**
  - `final` is for constants, preventing overriding or inheritance.
  - `finally` is for cleanup code in exception handling.
  - `finalize()` was for cleanup before garbage collection (now obsolete).

- What is the difference between `String`, `StringBuilder`, and `StringBuffer`?

    **Answer:**
    - **String:**
        - Immutable (cannot be changed after creation).
        - Each modification creates a new String object.
        - Thread-safe due to immutability.
        

    
    - **StringBuilder:**
        - Mutable (can be modified).
        - Not synchronized, hence not thread-safe.
        - More efficient for string manipulation in single-threaded scenarios.
        
         ```
    
    - **StringBuffer:**
        - Mutable like StringBuilder.
        - Synchronized, hence thread-safe.
        - Slightly slower than StringBuilder due to synchronization overhead.
        
           ```
    
    **Summary Table:**
    
    | Feature          | String         | StringBuilder   | StringBuffer   |
    |------------------|----------------|------------------|-----------------|
    | Mutability       | Immutable      | Mutable          | Mutable         |
    | Thread Safety    | Yes            | No               | Yes             |
    | Performance      | Slower on concat| Faster on concat | Slower on concat|
    | Primary Use	    | Fixed text      | Frequent changes| Thread-safe changes|
    **Use Cases:**
    - Use `String` for fixed text that does not change.
    - Use `StringBuilder` for efficient string manipulation in single-threaded applications.
    - Use `StringBuffer` when you need thread safety in string manipulation.

- What are Java's access modifiers, and how do they work?

  **Answer:**
  Java provides four main access modifiers to control the visibility of classes, methods, and variables:

  | Modifier     | Class | Package | Subclass | World |
  |--------------|:-----:|:-------:|:--------:|:-----:|
  | `public`     |  Yes  |   Yes   |   Yes    |  Yes  |
  | `protected`  |  Yes  |   Yes   |   Yes    |  No   |
  | (default)*   |  Yes  |   Yes   |   No     |  No   |
  | `private`    |  Yes  |   No    |   No     |  No   |

  *Default (no modifier) is also called package-private.

  - `public`: The member is accessible from any other class.
  - `protected`: The member is accessible within the same package and by subclasses (even if they are in different packages).
  - (default): If no modifier is specified, the member is accessible only within the same package.
  - `private`: The member is accessible only within the same class.



  **Summary:**
  - Use access modifiers to enforce encapsulation and control visibility of your code.

- Explain the concept of overloading and overriding in Java.

  **Answer:**
  - **Overloading:**
    - Occurs when two or more methods in the same class have the same name but different parameter lists (type, number, or order).
    - It is resolved at compile time (static polymorphism).
    
    **Example:**
    ```java
    class MathUtil {
        int add(int a, int b) { return a + b; }
        double add(double a, double b) { return a + b; }
    }
    ```

  - **Overriding:**
    - Occurs when a subclass provides a specific implementation for a method that is already defined in its superclass.
    - The method signature must be the same.
    - It is resolved at runtime (dynamic polymorphism).
    
    **Example:**
    ```java
    class Animal {
        void sound() { System.out.println("Animal sound"); }
    }
    class Dog extends Animal {
        @Override
        void sound() { System.out.println("Bark"); }
    }
    ```

  **Summary:**
  - Overloading: same method name, different parameters, same class.
  - Overriding: same method name and parameters, subclass changes superclass behavior.

- What is the difference between static and instance methods?

  **Answer:**
  - **Static methods:**
    - Belong to the class, not to any specific object.
    - Can be called without creating an instance of the class.
    - Can only access static variables and other static methods directly.
    - Cannot use `this` or `super` keywords.
    
    **Example:**
    ```java
    class MathUtil {
        static int square(int x) { return x * x; }
    }
    // Usage: MathUtil.square(5);
    ```

  - **Instance methods:**
    - Belong to an object (instance) of the class.
    - Can access instance variables and methods, as well as static members.
    - Can use `this` and `super` keywords.
    
    **Example:**
    ```java
    class Counter {
        private int count = 0;
        int increment() { count++; return count; }
    }
    // Usage: new Counter().increment();
    ```

  **Summary:**
  - Static methods are called on the class and cannot access instance data directly.
  - Instance methods are called on objects and can access both instance and static data.

- How does Java achieve platform independence?

  **Answer:**
  - Java achieves platform independence through the use of the Java Virtual Machine (JVM).
  - Java source code is compiled into byte code (`.class` files) by the Java compiler.
  - This byte code is not specific to any operating system or hardware. Instead, it can be executed on any system that has a compatible JVM.
  - The JVM acts as an interpreter between the byte code and the underlying operating system, allowing the same Java program to run on Windows, macOS, Linux, etc., without modification.

  **Diagram:**
  ```
  Java Source Code (.java)
           |
           v
      Java Compiler
           |
           v
      Byte code (.class)
           |
           v
      JVM (on any OS)
           |
           v
      Machine Code (OS/Hardware)
  ```

  **Summary:**
  - Write once, run anywhere: Java code runs on any platform with a JVM, making it platform independent.

- What is the difference between abstract classes and interfaces?

## Object-Oriented Programming (OOP)
- What are the four pillars of OOP in Java?

  **Answer:**
  1. **Encapsulation:** Bundling data (fields) and methods that operate on the data into a single unit (class), and restricting access to some of the object's components. Achieved using access modifiers (private, public, etc.).
  2. **Abstraction:** Hiding complex implementation details and showing only the necessary features. Achieved using abstract classes and interfaces.
  3. **Inheritance:** Mechanism where one class (child/subclass) acquires the properties and behaviors (fields and methods) of another class (parent/superclass). Promotes code reuse.
  4. **Polymorphism:** Ability of an object to take many forms. Achieved via method overloading (compile-time)

- Explain the difference between composition and inheritance.

  **Answer:**
  - **Inheritance:** Represents an "is-a" relationship. A subclass inherits fields and methods from a superclass. Example: `Dog extends Animal` (Dog is an Animal).
  - **Composition:** Represents a "has-a" relationship. A class contains a reference to another class as a field. Example: `Car has an Engine` (Car contains an Engine object).

- What is polymorphism? Provide examples.

  **Answer:**
  - Polymorphism allows objects to be treated as instances of their parent class rather than their actual class. The actual method that gets called is determined at runtime (dynamic polymorphism).
  - **Example:**
    ```java
    Animal a = new Dog();
    a.sound(); // Calls Dog's sound() if Dog overrides Animal's sound()
    ```
  - Compile-time polymorphism is achieved via method overloading.

- How does encapsulation improve code security?

  **Answer:**
  - Encapsulation restricts direct access to an object's data and only allows it through well-defined methods (getters/setters). This prevents unauthorized or invalid changes, making the code more secure and maintainable.
  
  **Example:**
  ```java
  public class Account {
      private double balance; // private field, not accessible directly

      // public getter
      public double getBalance() {
          return balance;
      }

      // public setter with validation
      public void deposit(double amount) {
          if (amount > 0) {
              balance += amount;
          }
      }
  }
  // Usage:
  // Account acc = new Account();
  // acc.deposit(100);
  // System.out.println(acc.getBalance());
  // acc.balance = -100; // Error: balance has private access
  ```

  **Summary:**
  - Encapsulation hides internal data, exposes only what is necessary, and allows validation, improving code security and maintainability.

- Can you explain the concept of multiple inheritance in Java?

  **Answer:**
  - Java does not support multiple inheritance with classes (a class cannot extend more than one class) to avoid ambiguity. However, a class can implement multiple interfaces, achieving multiple inheritance of type.

- What are the rules for method overriding in Java?

  **Answer:**
  - The method must have the same name, return type, and parameters as in the parent class.
  - The overriding method cannot have a more restrictive access modifier.
  - The overriding method can throw fewer or narrower checked exceptions.
  - The method must not be static or final in the parent class.
  - Use `@Override` annotation for clarity (optional but recommended).

- What is the difference between is-a and has-a relationships in Java?

  **Answer:**
  - **is-a:** Inheritance relationship. Example: `Car is a Vehicle` (Car extends Vehicle).
  - **has-a:** Composition relationship. Example: `Car has an Engine` (Car contains an Engine object).

- Explain the purpose of the `super` and `this` keywords.

  **Answer:**
  - `super`: Refers to the parent class. Used to access parent class methods, fields, or constructors.
  - `this`: Refers to the current object. Used to access current class fields, methods, or constructors.

- What is an inner class, and why would you use it?

  **Answer:**
  - An inner class is a class defined within another class. Used to logically group classes that are only used in one place, increase encapsulation, and can access members of the outer class.

- How does Java implement abstraction?

  **Answer:**
  - Java implements abstraction using abstract classes and interfaces. Abstract classes can have abstract methods (without implementation) and concrete methods. Interfaces define a contract that implementing classes must fulfill. Both hide implementation details and expose only the required functionality.

## Collections Framework

- What are the main differences between `ArrayList` and `LinkedList`?
  - **ArrayList** is backed by a dynamic array, while **LinkedList** is backed by a doubly-linked list.
  - **Memory:** ArrayList uses less memory per element (just data), LinkedList uses more (data + two pointers per node).
  - **Access Time:** ArrayList provides O(1) random access (get/set), LinkedList is O(n) for access.
  - **Insertion/Deletion:** ArrayList is O(n) for insert/delete (except at end), LinkedList is O(1) for insert/delete at ends (if node reference is known), but O(n) if searching for position.
  - **Iteration:** Both are O(n), but ArrayList is generally faster due to better cache locality.
  - **Queue Operations:** LinkedList supports queue methods (addFirst, removeLast, etc.), ArrayList does not.
  - **Use Cases:** Use ArrayList for frequent access and infrequent insert/delete. Use LinkedList for frequent insert/delete, especially at the start or middle.
  - **Thread Safety:** Neither is thread-safe by default.
  - **Interview Tip:** Be ready to discuss time complexity, memory overhead, and when to use each.

- How does a `HashMap` work internally in Java?
- What is the difference between `HashSet` and `TreeSet`?

  **Answer:**
  - **Underlying Data Structure:**
    - `HashSet` uses a hash table.
    - `TreeSet` uses a Red-Black tree (self-balancing binary search tree).
  - **Ordering:**
    - `HashSet` does not guarantee any order of elements.
    - `TreeSet` stores elements in sorted (natural or custom comparator) order.
  - **Performance:**
    - `HashSet`: O(1) for add, remove, contains (on average).
    - `TreeSet`: O(log n) for add, remove, contains.
  - **Null Elements:**
    - `HashSet` allows one null element.
    - `TreeSet` does not allow null elements (throws `NullPointerException`).
  - **Use Cases:**
    - Use `HashSet` for fast operations without caring about order.
    - Use `TreeSet` when you need elements in a sorted order.

- Explain the concept of fail-fast and fail-safe iterators.

  **Answer:**
  - **Fail-Fast Iterators:**
    - These iterators immediately throw a `ConcurrentModificationException` if the collection is structurally modified after the iterator is created (except through the iterator's own `remove()` method).
    - They operate directly on the collection and use a modification count (modCount) to detect changes.
    - Most Java collections in `java.util` (like `ArrayList`, `HashSet`, `HashMap`) provide fail-fast iterators.
    - **Example:**
      ```java
      List<Integer> list = new ArrayList<>();
      list.add(1);
      list.add(2);
      Iterator<Integer> it = list.iterator();
      list.add(3); // Structural modification after iterator creation
      while (it.hasNext()) {
          System.out.println(it.next()); // Throws ConcurrentModificationException
      }
      ```

  - **Fail-Safe Iterators:**
    - These iterators do **not** throw exceptions if the collection is modified during iteration.
    - They operate on a **clone** or a **snapshot** of the collection, so changes to the original collection are not reflected in the iterator.
    - Collections from the `java.util.concurrent` package (like `CopyOnWriteArrayList`, `ConcurrentHashMap`) provide fail-safe iterators.
    - **Example:**
      ```java
      CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();
      list.add(1);
      list.add(2);
      Iterator<Integer> it = list.iterator();
      list.add(3); // Modification after iterator creation
      while (it.hasNext()) {
          System.out.println(it.next()); // No exception, prints 1 and 2
      }
      ```

  **Summary Table:**

  | Feature                | Fail-Fast Iterator                  | Fail-Safe Iterator                      |
  |------------------------|-------------------------------------|-----------------------------------------|
  | Throws exception?      | Yes (`ConcurrentModificationException`) | No                                      |
  | Works on snapshot?     | No                                  | Yes                                     |
  | Collections            | `ArrayList`, `HashSet`, `HashMap`   | `CopyOnWriteArrayList`, `ConcurrentHashMap` |
  | Reflects modifications | Yes                                 | No (iterates over original snapshot)    |

  **Interview Tip:**  
  - Use fail-fast iterators for single-threaded scenarios or when you want to detect concurrent modifications.
  - Use fail-safe iterators for concurrent scenarios where modifications may happen during iteration.
  
- What is the difference between `List`, `Set`, and `Map` in Java?
- How would you implement a custom comparator for sorting?

  **Answer:**
  - In Java, you can implement a custom comparator by creating a class that implements the `Comparator<T>` interface and overriding its `compare()` method.
  - This allows you to define custom sorting logic for objects, which can be used with sorting methods like `Collections.sort()` or `Arrays.sort()`.

  **Example:**
  ```java
  import java.util.*;

  class Person {
      String name;
      int age;
      Person(String name, int age) {
          this.name = name;
          this.age = age;
      }
  }

  // Comparator to sort by age
  class AgeComparator implements Comparator<Person> {
      @Override
      public int compare(Person p1, Person p2) {
          return Integer.compare(p1.age, p2.age);
      }
  }

  // Usage
  List<Person> people = Arrays.asList(
      new Person("Alice", 30),
      new Person("Bob", 25)
  );
  Collections.sort(people, new AgeComparator());
  ```

  **Java 8+ Lambda Example:**
  ```java
  Collections.sort(people, (p1, p2) -> Integer.compare(p1.age, p2.age));
  ```

  **Summary:**
  - Implement `Comparator<T>` and override `compare()`.
  - Pass the comparator to sorting methods.
  - Lambdas can simplify comparator creation in Java 8+.

- What is the difference between `ConcurrentHashMap` and `HashMap`?

- What is the role of `Iterator` and `ListIterator`?

  **Answer:**
  - `Iterator` and `ListIterator` are interfaces used to traverse collections in Java.

  **Iterator:**
  - Allows sequential access to elements in a collection (e.g., `List`, `Set`).
  - Methods: `hasNext()`, `next()`, and `remove()`.
  - Traverses in one direction (forward only).
  - Works with most collections.

  **ListIterator:**
  - Extends `Iterator` and is only available for `List` types (e.g., `ArrayList`, `LinkedList`).
  - Methods: All `Iterator` methods plus `hasPrevious()`, `previous()`, `add()`, `set()`, and `nextIndex()/previousIndex()`.
  - Can traverse in both directions (forward and backward).
  - Allows modification (add, set, remove) during iteration.

  **Example:**
  ```java
  List<String> list = new ArrayList<>();
  list.add("A");
  list.add("B");

  // Using Iterator
  Iterator<String> it = list.iterator();
  while (it.hasNext()) {
      System.out.println(it.next());
  }

  // Using ListIterator (bidirectional)
  ListIterator<String> lit = list.listIterator();
  while (lit.hasNext()) {
      System.out.println(lit.next());
  }
  while (lit.hasPrevious()) {
      System.out.println(lit.previous());
  }
  ```

  **Summary Table:**

  | Feature         | Iterator         | ListIterator         |
  |-----------------|------------------|---------------------|
  | Direction       | Forward only     | Forward & backward  |
  | Collections     | List, Set, etc.  | List only           |
  | Add/set/remove  | remove() only    | add(), set(), remove() |
  | Index access    | No               | Yes (nextIndex, previousIndex) |

- How does `TreeMap` maintain order?
- What are the differences between `Vector` and `ArrayList`?


## Exception Handling
- What is the difference between checked and unchecked exceptions?
- How does the try-with-resources statement work?
- What are the best practices for exception handling in Java?
- What is the purpose of the `throw` and `throws` keywords?
- Can you create a custom exception in Java? How?
- What is the difference between `Error` and `Exception`?

Error: Imagine your car's engine completely seizes up. It's a fundamental, unrecoverable problem that means the car cannot continue to function. You can't just "handle" this on the road; the car needs to be taken to a garage or scrapped.
Exception: Imagine your car runs out of fuel. It's a problem that disrupts your journey, but it's recoverable. You can pull over, call for roadside assistance, or walk to the nearest gas station, and then continue your journey.


- Explain the concept of a stack trace in exception handling.
- How does exception chaining work in Java?
- What is the difference between `RuntimeException` and other exceptions?
- How do you handle multiple exceptions in a single catch block?
   - Since Java 7, you can catch multiple exceptions in a single catch block using the pipe (|) symbol.

```java
try {
    // Code that may throw multiple exceptions
} catch (IOException | SQLException e) {
    // Handle both IOException and SQLException
    e.printStackTrace();
}
```
---