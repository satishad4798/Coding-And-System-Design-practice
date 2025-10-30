# Questions to Try for wission


---

**1. Maximum Possible Loss in Stock Prices**

You are given an array of stock prices, where each element represents the price of a stock at a specific point in time. The prices are ordered by time, meaning the first element is the earliest price, and the last element is the most recent price. Your task is to determine the maximum possible loss that could occur by buying at a higher price at an earlier time and selling at a lower price at a later time. Specifically, you need to identify the worst trade where you buy at the highest price first and then sell at the lowest price later.

**Problem Requirements:**
Find the maximum loss you could incur by making the worst possible trade in the given array of prices.

**Example:**
```java
int[] prices = {100, 180, 260, 310, 40, 535, 695, 30};
// Output: Maximum possible loss: -665
// Explanation: The maximum loss occurs when you buy at the highest price (695) and sell at the lowest price (30).
// The loss would be: 30 - 695 = -665.
// Thus, the worst possible trade results in a loss of -665.
``` 

---

**2. Group Anagrams**

Given an array of strings `strs`, group the anagrams together. You can return the answer in any order. An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

**Input:**
```java
strs = ["eat","tea","tan","ate","nat","bat"]
// Output: [["bat"],["nat","tan"],["ate","eat","tea"]]

strs = [""]
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
You have to design a solution (classes, interfaces, etc.) for DeckofCards. Each card has a suit (heart, spade, club, diamond) and a rank (ace, king, queen, jack, 10, 9, 8, 7, 6, 5, 4, 3, 2).
- Problem 1: Write a function to compare two cards and return the bigger one. Suit doesn't matter.
- Problem 2: You are given a shuffled deck of cards. You have to sort them (by suit and then by rank).

---

**6. Split an Array into Chunks**

Example:
```java
array = [1,2,3,4,5]
chunkSize = 1 // [1] [2] [3] [4] [5]
chunkSize = 2 // [1, 2] [3, 4] [5]
chunkSize = 3 // [1, 2, 3] [4, 5]
chunkSize = 4 // [1, 2, 3, 4] [5]
chunkSize = 5 // [1, 2, 3, 4, 5]
chunkSize = 6 // [1, 2, 3, 4, 5]
```

---

**7. What is the purpose of the Java Collections Framework?**
Can you explain some of the key interfaces and classes in the Collections Framework and their use cases?

---

**8. Can one use an Employee class as a key in a HashMap?**
(This is a very important question. Please cover this in depth)

---

**9. First Unique Character in a String**

Given a string `s`, find the first non-repeating character in it and return its index. If it does not exist, return -1.

**Input:**
```java
s = "abcdef" // Output: 0
s = "mississippi" // Output: 0
```

---

**10. Movie Pair for Flight**

You are on a plane and you can watch two movies during this flight. You are given `List<Integer> movieDurations` which includes all the movie durations. You are also given duration of the flight which is in `d` minutes. Now, you need to pick two movies and the total duration of two movies is less than or equal to `(d - 30min)`. Find the pair of movies with the longest total duration and return their indexes. If multiple found, return the pair with longest movie.

**Example:**
```java
movieDurations = [90, 85, 75, 60, 120, 150, 125], d = 250
// Output: [0, 6]
// Explanation: movieDuration[0] + movieDuration[6] = 90 + 125 = 215 is the maximum number within 220 (250 min - 30 min).
```

---

**11. Longest Substring Without Repeating Characters**

Given a string `s`, find the length of the longest substring without repeating characters.

**Input:**
```java
s = "abcabcbb" // Output: 3
s = ...
```