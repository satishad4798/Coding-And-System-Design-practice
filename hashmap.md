# HashMap Overview

- Initially, a HashMap has a default capacity of 16 entries.
- HashMap capacities are always powers of 2 (e.g., 2, 4, 8, 16, etc.).
- Each bucket has a threshold limit of 8 entries.
- When a bucket exceeds this limit, its linked list is transformed into a balanced tree to achieve O(log n) lookup performance.

## When Resizing/Rehashing Happens

- Resizing/Rehashing occurs when the number of entries exceeds `capacity × load factor`.
- By default, the load factor is 0.75.
- Example: If the initial capacity is 16, resizing happens after inserting the 13th entry (16 × 0.75 = 12).
- During resizing, the capacity doubles and all entries are rehashed into the new bucket array.