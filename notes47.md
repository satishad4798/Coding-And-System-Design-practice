## Methods in Collections Class (java.util.Collections)

The Collections class provides utility methods for lists, sets, and maps.

| Method | Description |
| --- | --- |
| Method | Description |
| --- | --- |
| `Collections.sort(List<T> list)`              | Sorts a List in ascending order. |
| `Collections.reverse(List<T> list)`           | Reverses the order of a List. |
| `Collections.shuffle(List<T> list)`           | Randomly shuffles elements in a List. |
| `Collections.swap(List<T> list, int i, int j)` | Swaps two elements in a List. |
| `Collections.min(Collection<T> coll)`             | Finds the minimum element in a collection. |
| `Collections.max(Collection<T> coll)`             | Finds the maximum element in a collection. |
| `Collections.fill(List<T> list, T value)`         | Fills a List with a specific value. |
| `Collections.frequency(Collection<T> coll, T obj)` | Counts the occurrences of an element. |
| `Collections.nCopies(int n, T obj)`               | Creates a List with n copies of an element. |
| `Collections.unmodifiableList(List<T> list)`      | Creates a read-only List. |
| `Collections.synchronizedList(List<T> list)`          | Creates a thread-safe List. |
| `Collections.emptyList()`, `Collections.emptySet()`, `Collections.emptyMap()` | Creates an empty immutable collection. |

### Example Usage

# Stream 

2. filter(Predicate<T>)

```java
fruits.stream().filter(fruit -> fruit.startsWith("B")).collect(Collectors.toList());
```
3. map(Function<T, R>)
```java

```

4. flatMap(Function<T, Stream<R>>)

5. sorted()

```java

List<Student> sds = names.stream().sorted((a, b) -> a.age - b.age).collect(Collectors.toList());

List<Student> sds = names.stream().sorted((a, b) -> {
            int diff = a.age - b.age;
            if (diff > 0) {
                return a.marks - b.marks;
            } else
                return a.age - b.age;
        }).collect(Collectors.toList());
```

6. distinct()
7. limit(long)
8. skip(long)
9. forEach(Consumer<T>)
10. collect(Collector<T, A, R>)

11. reduce(BinaryOperator<T>)
12. boolean : anyMatch(Predicate<T>) : Checks if any element matches the given condition.
13. boolean : allMatch(Predicate<T>) : Checks if all elements match the given condition.


Terminal Operations
These operations are eager and produce a result or side-effect. They trigger the processing of the stream.

Examples:

forEach(Consumer<T>)

collect(Collector<T, A, R>)

reduce(BinaryOperator<T>)

count()

min(Comparator<T>)

max(Comparator<T>)

anyMatch(Predicate<T>)

allMatch(Predicate<T>)

noneMatch(Predicate<T>)







# Queue
add /offer : to add element
remove /poll : to remove element

peek() /element : to view top element


# Stack

Stack<Integer> s = new Stack<>();

# map
```java
map.compute("Apple", (key, value) -> value + 5); // Update Apple's count by 5
map.computeIfAbsent("Orange", key -> 25); // Add Orange if not present

```


# Linked list :

advantages :
 - it improves memory utilization efficiency.
 - Another advantage is that nodes can be connected when needed and removed when not needed. 
 - You never have to worry about resizing or moving data

 disadvantages :
 - The biggest advantage of arrays is the ability to quickly access elements via indices, which linked lists do not support.

# hashcode /hashmap

hash collisions
 - chaining  
 - linear probing (also often called open addressing).

## chaining
In chaining, the underlying array of the hash table doesn't store value types directly but stores a linked list. When multiple different keys map to the same index, these key -> value pairs are stored in the linked list, effectively resolving hash collisions.

## linear probing 
![alt text](notes47/image.png)