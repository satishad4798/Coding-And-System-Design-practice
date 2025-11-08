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

### ✅ Java & Spring Boot

- Immutable classes – how to design and why?

- Overriding sort methods in collections.

- Thread-safe collections and their use cases.

- Fail-fast vs Fail-safe collections.

- Final vs Static keywords.

- Interface vs Abstract class.

- Different types of collections and their use cases.

- Methods available on Collections class.

- Java 8 Streams – methods with examples.

- How to return multiple values from a method in Java?

- Refresh on threads (theory, syntax, examples).

- different bean scope

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

#
> TO MAIN PAGE - [ Back to main page ](README.md)
