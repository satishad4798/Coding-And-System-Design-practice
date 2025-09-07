# code snippets useful for Java programming

## access character in string

```java
String s = "Hello";
char c = s.charAt(0); // Accessing the first character
```

## populate 2D array with values

```java
int[][] dp = new int[rows][cols];
for(int i=0; i<rows; i++) {
    Arrays.fill(dp[i], -1); // Fill each row with -1
}
```

# string to int 

```java
String str = "123";
int num = Integer.parseInt(str); // Convert String to int
```

# convert ArrayList to int[]


```java
List<int[]> result = new ArrayList<>();
   result.toArray(new int[result.size()][2]);
``` 
# Priority Queue


```java
PriorityQueue<Integer> pq = new PriorityQueue<>();
pq.offer(10); // Add element
pq.offer(5);
//max heap
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
maxHeap.offer(10); // Add element
//heapify an array
int[] arr = {3, 1, 4, 1, 5};
PriorityQueue<Integer> pq = new PriorityQueue<>(Arrays.stream(arr).boxed().collect(Collectors.toList()));

//heapify a list
List<Integer> list = Arrays.asList(3, 1, 4, 1, 5);
PriorityQueue<Integer> pq = new PriorityQueue<>(list);

```

## HashMap getOrDefault

```java
Map<String, Integer> map = new HashMap<>();
map.put("Apple", 10);
int count = map.getOrDefault("Banana", 0); // Returns 0 if "Banana" is not present

map.put("Banana", map.getOrDefault("Banana", 0) + 1); // Increment count for "Banana"

```

## create a array with value and return it

```java
        return new int[]{-1,-1};

```

## to iterate a sting and access each character

```java
String str = "Hello";
for (char c : str.toCharArray()) {
    System.out.println(c); // Prints each character
}
```
```java
String str = "Hello";
for (int i = 0; i < str.length(); i++) {
    char c = str.charAt(i);
    System.out.println(c); // Prints each character
}
```
//other string methods
```java
String str = "Hello";
String upper = str.toUpperCase(); // Convert to uppercase
String lower = str.toLowerCase(); // Convert to lowercase
String trimmed = str.trim(); // Remove leading and trailing spaces
String replaced = str.replace("e", "a"); // Replace 'e' with 'a'
String substring = str.substring(1, 4); // Get substring from index 1 to 3
String[] parts = str.split("l"); // Split string by 'l'
```

# convert string to int and vice versa

```java
String str = "123";
int num = Integer.parseInt(str); // Convert String to int
String strNum = String.valueOf(num); // Convert int to String
// or
String strNum = Integer.toString(num); // Convert int to String
```     

# to check if character is digit or letter

```java
char c = 'A';
boolean isDigit = Character.isDigit(c); // Check if character is a digit
boolean isLetter = Character.isLetter(c); // Check if character is a letter
boolean isLetterOrDigit = Character.isLetterOrDigit(c); // Check if character is a letter or digit
boolean isWhitespace = Character.isWhitespace(c); // Check if character is whitespace
boolean isUpperCase = Character.isUpperCase(c); // Check if character is uppercase
boolean isLowerCase = Character.isLowerCase(c); // Check if character is lowercase
boolean isAlphabetic = Character.isAlphabetic(c); // Check if character is alphabetic

```
## Convert Set<List<Integer>> to List<Integer>

```java
Set<List<Integer>> set = new HashSet<>();
List<Integer> list = set.stream()
    .flatMap(Collection::stream)
    .collect(Collectors.toList());
``` 
without using ::
```java
Set<List<Integer>> set = new HashSet<>();
List<Integer> list = new ArrayList<>();
for (List<Integer> sublist : set) {
    list.addAll(sublist);
}
```


## Convert Set<List<Integer>> to List<List<Integer>>
```java
Set<List<Integer>> set = new HashSet<>();
List<List<Integer>> list = new ArrayList<>(set);
```

## create custom hashCode and equals method

```java
@Override
public int hashCode() {
    return Objects.hash(field1, field2); // Use fields that define equality
}
@Override
public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    MyClass other = (MyClass) obj;
    return Objects.equals(field1, other.field1) && Objects.equals(field2, other.field2);
}
```

## ExecutorService example


```java
ExecutorService executor = Executors.newFixedThreadPool(3);
executor.submit(() -> {
    System.out.println("Task running in thread: " + Thread.currentThread().getName());
});
executor.shutdown();

```


##  map to list convert
```java

 TreeMap<Integer,ArrayList<Integer>> map=new TreeMap<>();

List<Integer> list = map.values().stream()
    .flatMap(Collection::stream)
    .collect(Collectors.toList());

or

List<Integer> list = map.values().stream()
    .flatMap(list -> list.stream())
    .collect(Collectors.toList());

or    

ArrayList<Integer> result=new ArrayList<>();
    map.values().forEach(list->{
        result.addAll(list);
});

```







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

List<coding.Student> sds = names.stream().sorted((a, b) -> a.age - b.age).collect(Collectors.toList());

List<coding.Student> sds = names.stream().sorted((a, b) -> {
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




## 




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
