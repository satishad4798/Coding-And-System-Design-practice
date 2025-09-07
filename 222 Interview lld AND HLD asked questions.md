
# Design a simplified UPI-like payment system : PhonePay
- company : 
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


# Implement an LRU Cache : PhonePay

Naive approach: Using LinkedHashMap.
Optimized approach: Doubly Linked List + HashMap for O(1) access and eviction.
Follow-up:

"What if multiple threads access the cache?" Here I discussed thread-safety approaches — ReentrantLock, synchronized blocks, and trade-offs with performance.
Lesson: They wanted depth in Java, not just definitions. Always tie theory back to real-world usage.



# Implement a news Aggregator : jp morgan
-
Requirements: Fetch news from multiple sources, and show messages in real-time.
- should support multiple categories (sports, politics, tech).
- should support user preferences.
- option to search  by keyword and filter by 

>  design apis, hld diagram ... 


# Blogging Platform -  Code microservice live : 
- User registration/login.
- Post creation, comments, likes.
- Full-text search

#  Implement a high-performance scheduler : Jforg :
Implement a high-performance, thread-safe job scheduler that allows users to schedule and cancel jobs dynamically. The system should be able to handle thousands of jobs efficiently.

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


    
