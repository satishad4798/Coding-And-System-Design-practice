

# HLD of sample System.


## Know system :
Clarify: 1-1 chat? Group chat? Media support? Delivery guarantees?


## Functional requirements :
FRs: send/receive message, read receipts, offline storage.


## Non-functional requirements :

NFRs: low latency, highly available, scale to millions of users.


## Components involved :

- Client App → Load Balancer → Chat Service → DB (Cassandra / DynamoDB)
- Message Queue (Kafka) for async processing.
- Redis for unread counts.
- Notification service (push).


## Scaling:
- Shard by user_id.

Use Kafka for ordering within a conversation.

## Trade-offs: ( CAP )

- Do we allow eventual consistency?
- How do we ensure delivery in network failures?

## Extras:
 -  monitoring, encryption, spam detection.