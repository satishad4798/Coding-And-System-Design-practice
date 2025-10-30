> TO MAIN PAGE  - [ Back to main page ](README.md)

# Tell about design pattern used in your application

>Memory 
 creational : single factory builder.
 behavioral : observer pattern,
 structural : decorator pattern, strategy pattern,

In my application, I have utilized several design patterns to enhance code maintainability, scalability, and readability. Here are some of the key design patterns implemented:

1. **Singleton Pattern**: This pattern is used to ensure that a class has only one instance and provides a global point of access to it. In my application, I used the Singleton pattern for `managing database connections`, ensuring that only one connection instance is created and reused throughout the application. and `logger`

2. **Factory Pattern**: The Factory pattern helps to create objects without specifying the exact class of the object that will be created. This is particularly useful in scenarios where the application needs to create different types of objects based on certain conditions. In my application, I used the Factory pattern to create different types of user notifications (e.g., email, SMS, push notifications) based on user preferences.

    Additionally, selecting the database implementation (e.g., PostgreSQL or Oracle) at runtime based on a configuration property is another example of the Factory pattern. Here, the factory is responsible for instantiating the appropriate database connector or service class depending on the runtime property, allowing the application to remain decoupled from specific database implementations.

- mode of connection in FEEM : kafka, api, stomp protocal etc.

3. **Observer Pattern**: This pattern is used to` establish a one-to-many dependency between objects,` allowing one object (the subject) to n`otify multiple dependent objects (observers) of any state changes.` In my application, I implemented the Observer pattern for real-time updates, where multiple components need to be notified when a specific event occurs, such as a change in user status or new data availability.

- early caching example . where when user login,it notify all the dependent services like cart, wishlist, user profile to load the data in cache.

4. **Decorator Pattern**: The Decorator pattern is used to `add new functionality to an object` `dynamically without altering its structure`.

- In my application, I used the Decorator pattern to enhance the functionality of user accounts by adding features such as `overdraft protection` and `transaction notifications` without modifying the original account class. This allows for greater flexibility and adherence to the Open/Closed Principle.


5. **Strategy Pattern**: This pattern is used to define a` family of algorithms`, encapsulate `each one, and make them interchangeable`. 

- In my application, I implemented the Strategy pattern for `calculating transaction fees` based on different account types `(e.g., premium, regular, business)`. Each account type has its own fee calculation strategy, allowing the application to switch between strategies at runtime without modifying the core logic.

```java

public interface FeeCalculationStrategy {
    BigDecimal calculateFee(Transaction transaction);
}

public class PremiumAccountFeeStrategy implements FeeCalculationStrategy {
    @Override
    public BigDecimal calculateFee(Transaction transaction) {
        // Premium accounts have no fees
        return BigDecimal.ZERO;
    }
}

public class RegularAccountFeeStrategy implements FeeCalculationStrategy {
    @Override
    public BigDecimal calculateFee(Transaction transaction) {
        // Regular accounts have a flat fee of 2.5
        return new BigDecimal("2.50");
    }
}

public class BusinessAccountFeeStrategy implements FeeCalculationStrategy {
    @Override
    public BigDecimal calculateFee(Transaction transaction) {
        // Business accounts have volume-based fees
        if (transaction.getAmount().compareTo(new BigDecimal("10000")) > 0) {
            return new BigDecimal("10.00");
        }
        return new BigDecimal("5.00");
    }
}
// Usage in service
@Service
public class FeeService {
    private Map<AccountType, FeeCalculationStrategy> strategies;
    @Autowired
    public FeeService() {
        strategies = new HashMap<>();
        strategies.put(AccountType.PREMIUM, new PremiumAccountFeeStrategy());
        strategies.put(AccountType.REGULAR, new RegularAccountFeeStrategy());
        strategies.put(AccountType.BUSINESS, new BusinessAccountFeeStrategy());
    }
    public BigDecimal calculateTransactionFee(Account account, Transaction transaction) {
        FeeCalculationStrategy strategy = strategies.get(account.getType());
        return strategy.calculateFee(account, transaction); 
    }
}



```

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


    
# design e-commerce  db :  m-pocket

"Imagine you are designing the e-commerce category menu system. This system powers the menus you see on the homepage, app, and search filters. It must handle a deep, multi-level hierarchy, support dynamic updates, and serve millions of requests reliably."

📂 Example Hierarchy

Electronics (Level 1)
 ├── Mobiles & Accessories (Level 2)
 │    ├── Smartphones (Level 3)
 │    │    ├── Android Phones (Level 4)
 │    │    │    ├── 5G Phones (Level 5)
 │    │    │    │    ├── Flagship Phones (Level 6)
 │    │    │    │    │    ├── Foldable Phones (Level 7)
 │    │    │    │    │    ├── Gaming Phones (Level 7)
 │    │    │    ├── Budget Phones (Level 5)
 │    ├── Feature Phones (Level 3)
 │    ├── Accessories (Level 3)
 │         ├── Cases & Covers (Level 4)
 │         ├── Power Banks (Level 4)
 │         ├── Screen Guards (Level 4)



How would you design the database schema to store this deep hierarchy?
What tables/columns would you create?


How do you store parent-child relations?
