> TO MAIN PAGE - [ Back to main page ](README.md)


# Table of Contents

- [Questions asked in interview](#questions-asked-in-interview)
- [Oracle](#oracle)
- [M-pocket](#m-pocket-)
- [M-pocket 2nd round](#m-pocket-2nd-round)
- [Load share](#load-share)
- [Clear water](#clear-water)
- [Wission](#wission)
- [Asked in interview](#asked-in-interview)
  - [Microservice](#microservice)
  - [Java & Spring Boot](#java--spring-boot)
  - [Databases & SQL](#databases--sql)
  - [Coding / Data Structures](#coding--data-structures)
  - [General Concepts](#general-concepts)

# Questions asked in interview:

> Open for collaboration : raise mr to Add your experience.

## oracle

1. given array of timeline and window size.
   find window when max no.of request are made.

2. given context with pages a reward reading each context.
   find maximum no.of reward which you can get.

3. find longest palindrome.

## m-pocket :

- for scalable app which one is better sql or nosql ? explain
- sql functional and views .
- why so we do serialization ?

- aspect methods

- explain cap theorem. with example.

### 2nd round
- builder patter withe example
- db design
- how to implement spring security

## load share

- circuit breaker pattern
- thread
- idempotency in rest api

## clear water

- garbage collection in java

## wission

**1. Maximum Possible Loss in Stock Prices**

You are given an array of stock prices, where each element represents the price of a stock at a specific point in time.
The prices are ordered by time, meaning the first element is the earliest price, and the last element is the most recent
price. Your task is to determine the maximum possible loss that could occur by buying at a higher price at an earlier
time and selling at a lower price at a later time. Specifically, you need to identify the worst trade where you buy at
the highest price first and then sell at the lowest price later.

**2. Group Anagrams**

Given an array of strings `strs`, group the anagrams together. You can return the answer in any order. An Anagram is a
word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters
exactly once.

**Input:**

```java
strs =["eat","tea","tan","ate","nat","bat"]
// Output: [["bat"],["nat","tan"],["ate","eat","tea"]]

strs =[""]
// Output: [[""]]
```

---

**3. What is the purpose of the Java Collections Framework?**
Can you explain some of the key interfaces and classes in the Collections Framework and their use cases?

---

**4. Can one use an Employee class as a key in a HashMap?**
(This is a very important question. Please cover this in depth)

---

**5. Deck of Cards Design**
You have to design a solution (classes, interfaces, etc.) for DeckofCards. Each card has a suit (heart, spade, club,
diamond) and a rank (ace, king, queen, jack, 10, 9, 8, 7, 6, 5, 4, 3, 2).

- Problem 1: Write a function to compare two cards and return the bigger one. Suit doesn't matter.
- Problem 2: You are given a shuffled deck of cards. You have to sort them (by suit and then by rank).

**6. First Unique Character in a String**

Given a string `s`, find the first non-repeating character in it and return its index. If it does not exist, return -1.

**Input:**

```java
s ="abcdef" // Output: 0
s ="mississippi" // Output: 0
```

---

**10. Movie Pair for Flight**

You are on a plane and you can watch two movies during this flight. You are given `List<Integer> movieDurations` which
includes all the movie durations. You are also given duration of the flight which is in `d` minutes. Now, you need to
pick two movies and the total duration of two movies is less than or equal to `(d - 30min)`. Find the pair of movies
with the longest total duration and return their indexes. If multiple found, return the pair with longest movie.

**Example:**

```java
movieDurations =[90,85,75,60,120,150,125],d =250
// Output: [0, 6]
// Explanation: movieDuration[0] + movieDuration[6] = 90 + 125 = 215 is the maximum number within 220 (250 min - 30 min).
```

---

**11. Longest Substring Without Repeating Characters**

Given a string `s`, find the length of the longest substring without repeating characters.

**Input:**

```java
s ="abcabcbb" // Output: 3
s =...
```

## Other question asked in interview

### microservice

- Microservices & System Design

- How will you secure a microservice?

- How will you avoid circular dependency in a microservice?

- How will you ensure exactly-once message processing in Kafka?

- How is idempotency achieved in Kafka/distributed systems?

- Consumer was down for 1 day and comes back – how to read only fresh events, not old ones?

- What is Kafka event retention period? What happens to unread events?

- How to troubleshoot when a microservice works for 1M users but fails beyond?

= How to identify performance bottlenecks in a Spring Boot backend?

- When to use WebSockets in HLD? What are the drawbacks?

- In a chat app with many users, do we create multiple WebSocket connections? How does the server handle millions of
  them?

- Note: I explained these step-by-step, focusing on real-world relevance (e.g., how `HashMap` resizing can affect latency if not considered in production).

### ✅ Databases & SQL

- SQL basics – e.g., SELECT * FROM A, B.

- N+1 query problem in Hibernate – how to solve?

- Database encryption / securing sensitive data.

- Avoiding DB-level circular dependencies between microservices.

### ✅ Coding / Data Structures

- Merge intervals problem (optimization).

- Sorting k linked lists (heap vs TreeSet approach).

- Linked List problems:

- Triplet sum problem.

- Convert stream to linked list.

- Kth largest element in an array.

- Pseudocode for IP address validation.

### ✅ General Concepts

- SOLID principles in OOP.

- Difference between HashMap and ConcurrentHashMap.

- How idempotency is achieved in distributed systems.

- Exactly-once vs At-least-once vs At-most-once semantics.

- Security headers for microservices.


- Secret management (Vault, K8s secrets).

### ✅ Java & Spring Boot

> Content merged from `Learning_Resources/Java_and_spring_boot/asked_in_interview.md` — checklist and common Q&A.

## 📌 Master SDE-2 Java + Spring Boot Interview Tracker

------------------------------------------------------------------------
### 3. Common Interview Q&A

- **Why is HashMap faster than TreeMap?**
  - HashMap: O(1) average, TreeMap: O(log n).  
  - Use TreeMap only when sorting required.

- **If you need sorted keys, which map?**
  - TreeMap.

- **If you want insertion order preserved?**
  - LinkedHashMap.

- **Implement LRU Cache:**
  - Extend LinkedHashMap with `accessOrder=true` and override `removeEldestEntry()`.

- **Why ConcurrentHashMap doesn’t allow null keys/values?**
  - To avoid ambiguity in concurrent reads (`null` → absent or present?).

- **WeakHashMap vs HashMap:**
  - WeakHashMap removes entries when key is GC’d → good for caches.

- **When to use IdentityHashMap?**
  - When key equality is based on reference (`==`) not logical equality.


## 1. Core Collections & Hashing

1.  ✅ How does **HashMap** work internally?
2.  ✅ What happens if two keys have the **same hashcode**?
3.  ✅ Case study: Two objects with same/different `hashCode()` and `equals()` → what happens in HashMap?
4.  ❌ Difference between **HashMap** and **ConcurrentHashMap**
5.  ❌ Different types of **collections** and their use cases
6.  ❌ Methods available on **Collections** class
7.  ❌ Converting **Map** to **List** (keys, values, entries)
8.  ❌ Thread-safe collections and their use cases
9.  ❌ **Fail-fast** vs **Fail-safe** collections
10. ❌ Explain difference between **HashMap**, **LinkedHashMap** and **TreeMap**
11. ❌ How do you handle thread safety in Java Collections?
12. ❌ Explain immutability in Java and how to create immutable classes

------------------------------------------------------------------------

## 2. Memory & JVM

10. ❌ How does **Garbage Collection** work in JVM?
11. ❌ Explain the **Java Memory Model (JMM)** briefly
12. ❌ Difference between **Heap, Stack, Metaspace, and Direct Memory**
13. ❌ What is **Stop-the-world (STW) pause** in GC?
14. ❌ Difference between **Strong, Weak, Soft, and Phantom References**

------------------------------------------------------------------------

## 3. Concurrency & Multithreading

15. ✅ What is **ExecutorService**? Deep dive and use cases
16. ✅ Explain **Thread Pools** in detail
17. ✅ Difference between **synchronized** block, `Lock`, and `ConcurrentHashMap`
18. ✅ **Deadlock, Livelock, Starvation** -- causes and prevention
19. ✅ **Mutex vs Semaphore** -- differences with examples
20. ✅ **Reader-Writer problem** using Semaphore
21. ✅ **Virtual Threads** -- what, why, and when to use
22. ✅ Multithreading in Spring Boot -- examples and implementations
23. ✅ Custom thread pool in Spring Boot
24. ✅ Refresh on threads (theory, syntax, examples)
25. ❌ How do you implement Producer–Consumer in Java?
26. ❌ Explain difference between **ExecutorService**, **ForkJoinPool** and **CompletableFuture**

------------------------------------------------------------------------

## 4. Java 8 & Beyond

25. ✅ **Streams API** -- methods with examples
26. ✅ Converting complex Map structures (`Map<Integer, List<Integer>>` → `List<List<Integer>>`)
27. ✅ Real-world coding problems using Streams
28. ✅ **Optional** and its use cases
29. ✅ **Functional Interfaces**
30. ✅ **Method References** and **Lambdas**
31. 🟡 **CompletableFuture**
32. ✅ **Default methods** in interfaces

------------------------------------------------------------------------

## 5. Design & Best Practices

33. ✅ How to design **Immutable Classes** and why?
34. ✅ **SOLID Principles** in OOP with examples
35. ✅ **Final** vs **Static** keywords
36. ✅ **Interface** vs **Abstract class**
37. ✅ **Thread-safe Singleton** design

------------------------------------------------------------------------

## 6. Spring Boot & Backend

38. ❌ How to secure a microservice?
39. ❌ How to avoid circular dependency in microservices?
40. ❌ Performance troubleshooting at scale (1M+ users)
41. ❌ Identifying performance bottlenecks
42. ❌ Load testing tools (Apache Bench, JMeter)
43. ❌ Spring Boot annotations and their use cases
44. ❌ Security headers for microservices
45. ❌ Secret management (Vault, K8s secrets)
46. ❌ Difference between **@Controller**, **@RestController** and **@ControllerAdvice**
47. ❌ How do you implement global exception handling in Spring Boot?
48. ❌ How do you configure multiple data sources in Spring Boot?
49. ❌ How to secure REST APIs using JWT or OAuth2?
50. ❌ How do you implement caching in Spring Boot (Redis / Caffeine)?

------------------------------------------------------------------------

## 7. Message Processing & Kafka

46. ❌ Ensuring exactly-once message processing
47. ❌ Achieving idempotency in distributed systems
48. ❌ Handling fresh events after downtime
49. ❌ Kafka event retention and unread events
50. ❌ WebSocket usage in HLD -- benefits and drawbacks
51. ❌ Scaling WebSocket connections for chat applications
52. ❌ Difference between synchronous and asynchronous communication
53. ❌ How do you implement service discovery?
54. ❌ Explain message brokers (Kafka/RabbitMQ) in microservices
55. ❌ How do you design a fault-tolerant API Gateway?
56. ❌ Explain circuit breaker and retry patterns

------------------------------------------------------------------------

## 8. Database & Hibernate

52. ❌ SQL basics and optimization
53. ❌ N+1 query problem and solutions
54. ❌ Database encryption and security
55. ❌ Avoiding DB-level circular dependencies
56. ❌ Hibernate relationships between tables
57. ❌ Difference between **save()**, **persist()**, and **merge()**
58. ❌ Explain lazy loading vs eager loading
59. ❌ How do you implement batch inserts/updates in Hibernate?
60. ❌ Explain **@OneToOne**, **@OneToMany**, and **@ManyToMany** mappings
61. ❌ What are entity states in Hibernate?

------------------------------------------------------------------------

## 9. Spring Boot Transactions

57. ❌ Transaction handling in Spring Boot
58. ❌ `@Transactional` annotation deep dive
59. ❌ Isolation levels and propagation
60. ❌ Transaction properties for money transfer scenarios

------------------------------------------------------------------------

## 10. Additional Java & Coding Problems

61. ❌ Overriding sort methods in collections
62. ❌ Returning multiple values from a method in Java
63. ❌ Merge intervals optimization
64. ❌ Sorting k linked lists (heap vs TreeSet)
65. ❌ Triplet sum problem
66. ❌ Converting stream to linked list
67. ❌ Kth largest element in an array
68. ❌ IP address validation implementation
69. ❌ Group patients by insurance plan and count them
70. ❌ Find duplicate records in a large data stream
71. ❌ Implement REST endpoint for last 10 activities
72. ❌ Design healthcare alerts system for millions of users
73. ❌ Implement thread-safe cache with expiration policy

------------------------------------------------------------------------

## 11. Cloud & DevOps

74. ❌ Deploy Spring Boot microservices to cloud platforms
75. ❌ Explain CI/CD pipeline steps
76. ❌ Secure secrets and environment variables in cloud
77. ❌ Explain vertical vs horizontal scaling
78. ❌ Monitor and trace microservices in production

------------------------------------------------------------------------

#
> TO MAIN PAGE - [ Back to main page ](README.md)
