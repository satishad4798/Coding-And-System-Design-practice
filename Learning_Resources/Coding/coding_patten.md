
# Data structure and when to use

## Stack
 - if you need keep track of previous element.
 - looking got next greater or smaller element.
 - if you need keep track of minimum or maximum element.

## Hashmap
 - if you need to keep track of frequency of elements.
 - if you need to check if an element exists or not in O(1) time.
 - if you need to group elements based on some property.


 ## Priority Queue
 - if you need to keep track of largest or smallest elements.
 - you need top K elements.
 - need to pull smallest or largest element at any point of time. ( where insertion and deletion happens frequently
 c)


# Coding patterns

## Two pointer approach
-  This method uses two pointers to traverse an array or a list from `different ends or directions`.
  1. Fast & Slow Pointers
        - two pointers move at` different speeds` in a data structure   
  2. left and right pointer
        - one pointer starts from the `beginning` and the other from the `end` of the data structure and they move
        towards each other.
  3. Forward Pointer
        - both pointers move in the `same direction` through the data structure, often with a fixed distance between them.
  4. Sliding Window
        - both pointers move in the `same direction` through the data structure, but the distance between them can vary
        based on certain conditions.
  5. Collision Pointer
        - both pointers start from `opposite ends` of the data structure and move towards each other until they meet.

- example questions :
  1. Two Sum II - Input array is sorted
  2. Reverse a String
  3. Valid Palindrome
  4. Merge Sorted Array
  5. Container With Most Water
  6. Trapping Rain Water
  7. Remove Duplicates from Sorted Array
  8. Move Zeroes
  9. Squaring a Sorted Array                        

## Sliding window
- This technique involves creating a 'window' into the data structure and then moving that window around to gather
 specific information.
- Types of Sliding Window:
    1. Fixed Size Window
        - both pointers define a window of a fixed size and slide it across the data structure.
    2. Dynamic Size Window
        - both pointers define a window that can grow or shrink based on certain conditions.
    3. Minimum Size Window
        - both pointers define a window that must meet a minimum size requirement before it can be considered valid.

- example questions :
  1. Maximum Sum Subarray of Size K
  2. First Negative Integer in Every Window of Size K
  3. Longest Substring Without Repeating Characters
  4. Longest Repeating Character Replacement
  5. Minimum Window Substring
  6. Permutation in String
  7. Fruits into Baskets
  8. Sliding Window Maximum

## Merge intervals
- This pattern involves merging overlapping intervals in a list.
- Steps to solve:
  1. Sort the intervals based on the start time.
  2. Iterate through the sorted intervals and merge them if they overlap.
  3. Return the merged intervals.

- Types of Merging Intervals:
    1. Overlap Detection
    - When intervals are sorted by start time, you can easily detect overlaps by comparing the current interval's start time
    with the previous interval's end time.
    2. Merging
    - If two intervals overlap, you can merge them by updating the end time of the previous interval to the maximum of the two end times.
    3. Non-Overlapping Intervals
    - If you need to find the maximum number of non-overlapping intervals (e.g., scheduling problems), sorting by end time
    ensures you pick the interval that finishes earliest, leaving room for more intervals.
- example questions :
  1. Merge Intervals
  2. Insert Interval
  3. Non-overlapping Intervals
  4. Meeting Rooms
  5. Meeting Rooms II
  6. Employee Free Time
  7. Minimum Number of Arrows to Burst Balloons
  8. Car Pooling

// other coding patterns


# Table of Contents

- [How to approach a problem](#how-to-approach-a-problem)
- [Warnings section](#warnings-section)
- [Handling Different Types with Deep Copy Examples](#handling-different-types-with-deep-copy-examples)
    - [1D Array to 2D Array](#1-1d-array-to-2d-array)
    - [Primitive Arrays](#2-primitive-arrays)
    - [List of Strings](#3-list-of-strings)
    - [Maps](#4-maps)
    - [Nested Objects](#5-nested-objects)
    - [Collections](#6-collections)
    - [Serialization for Deep Copy](#7-serialization-for-deep-copy)
- [Hints/Tips](#hints-tips)
- [Coding patterns](#coding-patterns)
    - [Two pointer approach](#1-two-pointer-approach)
    - [Sliding window](#2sliding-window)
    - [Merge interval](#2merge-interval)
- [Stack](#stack)
- [Tree](#tree)
- [Approach To Solve](#approach-to-solve)

