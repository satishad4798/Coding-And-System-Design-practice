
 - scalability
 - how and where to store the data.
 - extendability.


# How to approach HLD Questions


## Clarify Requirements /Understand the problem and establish design scope

- Ask clarifying questions to understand `functional` and `non-functional` requirements clearly.
- understand whats in interview mind. ( what his expectation from system).

- Understand the `scope`, `scale` (users, traffic), `expected features`, and constraints
   - latency, availability, budget).
  
- feature to support:
   - MUST have
   - Good to have ..

   NOTE : which kind of system is ? read heavy, write heavy ? chat based ?...

# 2. List of Functional requirements and Non-functional requirement
    - list functional and non-functional and mark which one is must have
    - 

- Summarize the functional requirements (core features) and non-functional ones (performance, scalability, reliability).
- Confirm these with the interviewer to get a buy-in on the problem scope.


# 3 : as how interview wants to drive the interview
  - [top down apporach ] - like start with high level diagram , then api,table 
  - [buttom up approach] - start with low level then high level
 
# 3.Propose High level design and get buy-in   

  - Draw a `high-level diagram` showing major components like `clients`,` API gateways`, backend services, `databases`, `caches`, and message `brokers`.
  - discuss about interaction between component.
  - Explain how data flows between these components, `highlighting` `synchronous` and `asynchronous` interactions.
  - **Storage**: 
        - how and 
        - which database 
  - **extendability**


# 4. Low level of system

## 4.1    Design apis : 
  - make sure to talk about 
  - pagination.
  - filtration.  
  - Rate limiting

## 4.2. Data model design /  Design DB entities /tables

   - make sure to add `metadata columns` : date_created,last_modified,created_by, last_modified_by
   - what `primary key` in that
   - `relationship` between tables.
   - proper index on table for better performance.
   - how and where to store the data.

## 4.3.  Discuss about internal logics 

# 5 : Trade-offs Discussion

- Explicitly discuss trade-offs in your design choices, such as:
   - Eventual consistency vs. strong consistency
   - Availability vs. partition tolerance (CAP theorem)
   - SQL vs. NoSQL
   - Monolith vs. microservices
   - Latency vs. throughput
   - is system is CP AP 
      - scalability vs availability
  - scaling ( vertical and horizontal )
  - replication ( db replication and scaling )
  - database sharding 

- Justify decisions based on requirements like scale, reliability, or development speed.

# 6. Scalability and Reliability
- Address how the system scales horizontally, 
    - handles failovers, 
    - caching strategies, 
    - load balancing, and 
    - data replication.

# Key Trade-offs to Highlight in a Backend System Design
- Consistency Models: Strong consistency guarantees correctness but can increase latency; 
- eventual consistency offers better availability and scalability.

- CAP Theorem: Balancing consistency, availability, and partition tolerance based on system needs.

- Database Choice: SQL for complex queries and strict consistency, `NoSQL for scalability and flexible schema`.

- Latency vs. Throughput: Low latency may require more caching; high throughput might need partitioning and parallelism.

- Synchronous vs. Asynchronous Processing: Real-time user experience vs. batch or delayed processing.



  