
# Linked list notes


```java
public class DoublyListNode {
    int val;
    DoublyListNode next;            
    DoublyListNode prev;
    DoublyListNode() {}
    DoublyListNode(int val) { this.val = val; }
    DoublyListNode(int val, DoublyListNode next, DoublyListNode prev) {
        this.val = val;
        this.next = next;
        this.prev = prev;
    }
}
```

## tips
- if assigning next or prev value , take backup those values first in temporary variable.
- use ***dummy head*** and tail nodes to simplify edge cases
- **take backup swap elements**


# 🚀 Java Maps & Concurrency – Last Minute Revision Notes

## 1. Map Implementations in Java
- **HashMap**
  - Unordered, allows one null key, many null values.
  - O(1) average for put/get.
- **LinkedHashMap**
  - Maintains insertion order (or access order if configured).
  - Used for LRU cache.
- **TreeMap**
  - Sorted order (natural or custom comparator).
  - O(log n) operations.
  - Provides navigation methods (floorKey, ceilingKey, higherKey, lowerKey).
- **ConcurrentHashMap**
  - Thread-safe without blocking whole map.
  - Uses CAS + bucket-level locking.
  - Null keys/values **not allowed**.
- **Hashtable**
  - Legacy, synchronized (slower).
  - Allows neither null keys nor values.
- **WeakHashMap**
  - Keys stored as weak references (GC removes entries when key no longer referenced).
- **IdentityHashMap**
  - Uses `==` instead of `.equals()` for key comparison.

---



---


---


