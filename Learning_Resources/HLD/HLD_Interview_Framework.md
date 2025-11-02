
# 🏗️ HLD Interview Framework (SDE-2 Cheat Sheet)

Use this step-by-step approach for any High-Level Design (HLD) interview question.

---

## ✅ Step 1. Clarify the Problem
- Who are the users? (global vs internal, scale)
- What is in-scope vs out-of-scope?
- Ask: real-time? analytics? expiry? consistency?

---

## ✅ Step 2. Define Requirements
**Functional Requirements (FRs):**
- Core features system must support.

**Non-Functional Requirements (NFRs):**
- Scale, performance, latency, availability, consistency, cost.

---

## ✅ Step 3. API Design (Optional but Useful)
Define core APIs:
```
POST /shorten { longUrl } → { shortUrl }
GET /{shortUrl} → redirects to longUrl
```

---

## ✅ Step 4. High-Level Architecture
Typical blocks:
- Client → Load Balancer → App Servers → DB
- Cache (Redis, Memcached)
- Message Queue (Kafka, RabbitMQ)
- CDN for static content

---

## ✅ Step 5. Data Modeling
- Define tables/collections.
- Choose SQL vs NoSQL (justify).
- Example:
```
Messages: msg_id | sender | receiver | content | timestamp | status
Users: user_id | name | phone | last_seen
```

---

## ✅ Step 6. Scaling Considerations
- Caching (Redis, Memcached)
- Sharding & Partitioning
- Replication for HA
- Queue for spikes (Kafka, SQS)
- CDN for static content
- CAP theorem trade-offs

---

## ✅ Step 7. Trade-offs
- SQL vs NoSQL
- Strong vs Eventual consistency
- Push vs Pull notifications
- Monolith vs Microservices

---

## ✅ Step 8. Extra (Brownie Points ⭐)
- Monitoring (Prometheus, Grafana)
- Logging (ELK stack)
- Security (auth, rate limiting, encryption)
- Fault tolerance (retries, circuit breakers)
- Future extensions (analytics, ML, recommendations)

---

## ✅ Step 9. Summarize
- Show how design meets requirements
- Key trade-offs made
- Improvements for V2

---

# 📌 Example: Design URL Shortener
1. **Clarify** → global scale? expiry? analytics?
2. **FRs** → shorten URL, redirect.  
   **NFRs** → 1B URLs, <50ms lookup, high availability.  
3. **API** → POST /shorten, GET /{shortUrl}  
4. **Architecture** → Client → LB → App → Cache + DB  
5. **Data Model** → `shortId, longUrl, createdAt, expiry`  
6. **Scaling** → Redis cache, DB sharding, consistent hashing.  
7. **Trade-offs** → SQL (consistency) vs NoSQL (scale).  
8. **Extras** → rate limiting, monitoring.  
9. **Summarize** → system supports scale & low latency.  

#

---

💡 Memorize this flow → Answer any HLD in 10–15 mins.
