> TO MAIN PAGE - [ Back to main page ](README.md)

# Table of Contents

- [Design Patterns in Applications](#tell-about-design-pattern-used-in-your-application)
- [PhonePay](#phonepay)
    - [UPI Payment System Design](#design-a-simplified-upi-like-payment-system)
    - [LRU Cache Implementation](#implement-an-lru-cache)
- [JP Morgan](#jp-morgan)
    - [News Aggregator](#implement-a-news-aggregator)
- [Blogging Platform Microservice](#blogging-platform---code-microservice-live)
- [JFrog](#jforg)
    - [High-Performance Scheduler](#implement-a-high-performance-scheduler)
- [M-Pocket](#m-pocket)
    - [E-commerce Database Design](#design-e-commerce-db)

# Tell about design pattern used in your application

# PhonePay

## Design a simplified UPI-like payment system :

Requirements: idempotency (no duplicate payments), strong consistency (no ghost money), high availability.
Database choice: SQL for transactions, since ACID was crucial.
Transaction Service: With unique transaction IDs and retry logic.
Concurrency Handling: Locks at transaction level, optimistic concurrency control.
Async Work: Message queues for notifications.

Follow-up Questions
"What if the DB crashes mid-transaction?"
"How do you scale to 1B requests/day?"
"How do you ensure exactly-once delivery for payments?"
💡 Lesson: They weren't looking for the perfect system. They wanted to see structured thinking and trade-off analysis.

## Implement an LRU Cache :

Naive approach: Using LinkedHashMap.
Optimized approach: Doubly Linked List + HashMap for O(1) access and eviction.
Follow-up:

"What if multiple threads access the cache?" Here I discussed thread-safety approaches — ReentrantLock, synchronized
blocks, and trade-offs with performance.
Lesson: They wanted depth in Java, not just definitions. Always tie theory back to real-world usage.

# jp morgan

## Implement a news Aggregator :

Requirements: Fetch news from multiple sources, and show messages in real-time.

- should support multiple categories (sports, politics, tech).
- should support user preferences.
- option to search by keyword and filter by

> design apis, hld diagram ...

# Blogging Platform - Code microservice live :

- User registration/login.
- Post creation, comments, likes.
- Full-text search

# Jforg :

### Implement a high-performance scheduler :

Implement a high-performance, thread-safe job scheduler that allows users to schedule and cancel jobs dynamically. The
system should be able to handle thousands of jobs efficiently.

1. schedule(Runnable job, int delayMs):
    * Users can schedule a job to execute after delayMs milliseconds.
    * The system must handle multiple concurrent scheduling requests safely.
2. cancel(UUID jobId):
    * Users can cancel a job before it starts using its unique identifier.
    * If the job is already running, cancellation should have no effect.
3. Thread-Safe Execution:
    * Jobs must execute in order of their scheduled time.
    * The system should allow multiple threads to schedule and cancel jobs concurrently.
4. High Performance:
    * The scheduler should efficiently handle thousands of jobs with minimal overhead.

# m-pocket

## design e-commerce  db :

"Imagine you are designing the e-commerce category menu system. This system powers the menus you see on the homepage,
app, and search filters. It must handle a deep, multi-level hierarchy, support dynamic updates, and serve millions of
requests reliably."

📂 Example Hierarchy

Electronics (Level 1)
├── Mobiles & Accessories (Level 2)
│ ├── Smartphones (Level 3)
│ │ ├── Android Phones (Level 4)
│ │ │ ├── 5G Phones (Level 5)
│ │ │ │ ├── Flagship Phones (Level 6)
│ │ │ │ │ ├── Foldable Phones (Level 7)
│ │ │ │ │ ├── Gaming Phones (Level 7)
│ │ │ ├── Budget Phones (Level 5)
│ ├── Feature Phones (Level 3)
│ ├── Accessories (Level 3)
│ ├── Cases & Covers (Level 4)
│ ├── Power Banks (Level 4)
│ ├── Screen Guards (Level 4)

How would you design the database schema to store this deep hierarchy?
What tables/columns would you create?

How do you store parent-child relations?

--------------------------------------------------------------------

#

> TO MAIN PAGE - [ Back to main page ](README.md)
