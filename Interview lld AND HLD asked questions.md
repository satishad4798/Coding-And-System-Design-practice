
# Design a simplified UPI-like payment system."
- company : PhonePay
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


# Implement an LRU Cache."
- company : PhonePay

Naive approach: Using LinkedHashMap.
Optimized approach: Doubly Linked List + HashMap for O(1) access and eviction.
Follow-up:

"What if multiple threads access the cache?" Here I discussed thread-safety approaches — ReentrantLock, synchronized blocks, and trade-offs with performance.
Lesson: They wanted depth in Java, not just definitions. Always tie theory back to real-world usage.