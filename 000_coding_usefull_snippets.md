# Java Code Snippets for SDE Interviews

## Basic Operations

### Array Operations
```java
// Array creation and initialization
int[] arr = new int[]{1, 2, 3};              // Array literal
int[][] matrix = new int[rows][cols];         // 2D array

// Array to List conversion
List<Integer> list = Arrays.asList(1, 2, 3);  // Fixed-size list
List<Integer> arrayList = new ArrayList<>(Arrays.asList(1, 2, 3));  // Modifiable

// List to Array conversion
List<int[]> result = new ArrayList<>();
int[][] array = result.toArray(new int[result.size()][2]);

// Populate 2D array
int[][] dp = new int[rows][cols];
for(int i=0; i<rows; i++) {
    Arrays.fill(dp[i], -1);                   // Fill each row with -1
}

// Quick array creation
return new int[]{-1, -1};                     // Inline array creation
```

### Character Operations
```java
char c = 'A';
// Character type checking
boolean isDigit = Character.isDigit(c);       // Is it a digit?
boolean isLetter = Character.isLetter(c);     // Is it a letter?
boolean isLetterOrDigit = Character.isLetterOrDigit(c); // Is it alphanumeric?
boolean isWhitespace = Character.isWhitespace(c); // Is it whitespace?
boolean isUpperCase = Character.isUpperCase(c);   // Is it uppercase?
boolean isLowerCase = Character.isLowerCase(c);   // Is it lowercase?
boolean isAlphabetic = Character.isAlphabetic(c); // Is it alphabetic?

// Character case conversion
char upper = Character.toUpperCase(c);
char lower = Character.toLowerCase(c);
```

## String Operations
```java
// String manipulation
String str = "Hello World";
// Character access
char c = str.charAt(0);                     // Get character at index
char[] chars = str.toCharArray();           // Convert to char array
for (char ch : str.toCharArray()) {         // Iterate characters
    System.out.println(ch);
}
for (int i = 0; i < str.length(); i++) {    // Alternative iteration
    char ch = str.charAt(i);
    System.out.println(ch);
}
char[] chars = str.toCharArray();              // Convert to char array
String[] words = str.split(" ");               // Split by delimiter
String trimmed = str.trim();                   // Remove whitespace
String sub = str.substring(1, 4);              // Substring (start, end)
boolean contains = str.contains("World");      // Check contains
String replaced = str.replace("old", "new");   // Replace all occurrences
StringBuilder sb = new StringBuilder(str);      // Mutable string

// String conversion
int num = Integer.parseInt("123");             // String to int
String strNum = String.valueOf(123);           // int to String
String hexString = Integer.toHexString(num);   // int to hex String
```

## Collections and Data Structures

### Priority Queue Operations
```java
// Min Heap (default)
PriorityQueue<Integer> pq = new PriorityQueue<>();
pq.offer(10); 
pq.offer(5);

// Max Heap
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
maxHeap.offer(10);

// Initialize from array
int[] arr = {3, 1, 4, 1, 5};
PriorityQueue<Integer> pq1 = new PriorityQueue<>(Arrays.stream(arr)
    .boxed()
    .collect(Collectors.toList()));

// Initialize from list
List<Integer> list = Arrays.asList(3, 1, 4, 1, 5);
PriorityQueue<Integer> pq2 = new PriorityQueue<>(list);
```

### Stack Operations
```java
Stack<Integer> stack = new Stack<>();
stack.push(1);           // Add element
stack.pop();            // Remove and return top element
stack.peek();           // View top element without removing
stack.isEmpty();        // Check if empty
stack.size();          // Get number of elements
```

### Map to List Conversions
```java
// Convert TreeMap values to flat list
TreeMap<Integer, ArrayList<Integer>> map = new TreeMap<>();
// Using streams
List<Integer> list1 = map.values().stream()
    .flatMap(Collection::stream)
    .collect(Collectors.toList());

// Using method reference
List<Integer> list2 = map.values().stream()
    .flatMap(list -> list.stream())
    .collect(Collectors.toList());

// Using traditional loop
ArrayList<Integer> list3 = new ArrayList<>();
map.values().forEach(list -> {
    list3.addAll(list);
});
```

### Set Conversions
```java
// Convert Set<List<Integer>> to List<Integer>
Set<List<Integer>> set = new HashSet<>();
List<Integer> flatList = set.stream()
    .flatMap(Collection::stream)
    .collect(Collectors.toList());

//without using ::
set.stream()
    .flatMap(list -> list.stream())
    .collect(Collectors.toList());
```

```java

// Alternative without streams
List<Integer> flatList = new ArrayList<>();
for (List<Integer> sublist : set) {
    flatList.addAll(sublist);
}

// convert Set<Integer> to List<Integer>
List<Integer> list = new ArrayList<>(set);
```

```java

// Convert Set<List<Integer>> to List<List<Integer>>
List<List<Integer>> listOfLists = new ArrayList<>(set);
```

### Queue Operations
```java
Queue<Integer> queue = new LinkedList<>();
queue.offer(1);      // Add element (returns boolean)
queue.add(2);        // Add element (throws exception if full)
queue.poll();        // Remove and return head (returns null if empty)
queue.remove();      // Remove and return head (throws exception if empty)
queue.peek();        // View head without removing (returns null if empty)
queue.element();     // View head without removing (throws exception if empty)
```
```java
// Priority Queue (Min Heap by default)
PriorityQueue<Integer> minHeap = new PriorityQueue<>();
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
maxHeap.offer(1);                             // Add element
int top = maxHeap.poll();                     // Remove and return top element
int peek = maxHeap.peek();                    // View top element

// HashMap Operations
Map<String, Integer> map = new HashMap<>();
map.put("key", map.getOrDefault("key", 0) + 1);  // Increment counter
map.computeIfAbsent("key", k -> new ArrayList<>()); // Initialize if absent
map.compute("key", (k, v) -> v == null ? 1 : v + 1); // Update value
map.merge("key", 1, Integer::sum);            // Add to existing value

// TreeMap (Sorted Map)
TreeMap<Integer, String> treeMap = new TreeMap<>();
Map.Entry<Integer, String> ceiling = treeMap.ceilingEntry(key);  // Least key >= given key
Map.Entry<Integer, String> floor = treeMap.floorEntry(key);      // Greatest key <= given key

// Thread-safe Collections
Map<String, Integer> concurrentMap = new ConcurrentHashMap<>();
List<String> syncList = Collections.synchronizedList(new ArrayList<>());
```

## Advanced Data Structure Operations
```java
// Custom Comparator
PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
    if (a[0] != b[0]) return a[0] - b[0];  // Sort by first element
    return b[1] - a[1];                     // Then by second element
});

// Trie Implementation
class TrieNode {
    TrieNode[] children = new TrieNode[26];
    boolean isEndOfWord;
}

// Union-Find (Disjoint Set)
class UnionFind {
    int[] parent;
    int[] rank;
    
    public UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
    }
    
    public int find(int x) {
        if (parent[x] != x) 
            parent[x] = find(parent[x]);
        return parent[x];
    }
    
    public void union(int x, int y) {
        int px = find(x), py = find(y);
        if (px == py) return;
        if (rank[px] < rank[py]) parent[px] = py;
        else if (rank[px] > rank[py]) parent[py] = px;
        else {
            parent[py] = px;
            rank[px]++;
        }
    }
}
```

## Java 8 Stream Operations
```java
// Common Stream operations
List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5);
int sum = nums.stream()
    .filter(n -> n % 2 == 0)           // Filter even numbers
    .map(n -> n * n)                   // Square each number
    .reduce(0, Integer::sum);          // Sum all numbers

// Complex transformations
Map<String, List<Employee>> deptMap = employees.stream()
    .collect(Collectors.groupingBy(Employee::getDepartment));

// Parallel streams for performance
long count = nums.parallelStream()
    .filter(n -> n > 100)
    .count();
```

## Concurrency Patterns
```java
// Thread Pool
ExecutorService executor = Executors.newFixedThreadPool(4);
Future<Integer> future = executor.submit(() -> {
    // Complex computation
    return result;
});
executor.shutdown();

// CompletableFuture for async operations
CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Hello");
CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "World");
CompletableFuture.allOf(future1, future2).join();  // Wait for all to complete

// Thread-safe Singleton
public class Singleton {
    private static volatile Singleton instance;
    private Singleton() {}
    
    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
```

## Data Structure Internals

### HashMap Internal Working
```java
/*
Hash Collision Resolution:
1. Chaining: Uses LinkedList/Tree to store colliding elements
   - If bucket size > 8, converts to TreeNode
   - If bucket size < 6, converts back to LinkedList
   
2. Linear Probing:
   - Looks for next empty slot if collision occurs
   - More cache-friendly but suffers from clustering
*/

// Custom hashCode() and equals() for HashMap keys
class CustomKey {
    private String field1;
    private int field2;
    
    @Override
    public int hashCode() {
        return Objects.hash(field1, field2);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CustomKey)) return false;
        CustomKey other = (CustomKey) obj;
        return Objects.equals(field1, other.field1) 
            && field2 == other.field2;
    }
}
```

### LinkedList Characteristics
```java
/*
Advantages:
1. Dynamic size - no resizing needed
2. Efficient insertion/deletion at any position
3. Memory utilization - only allocates needed memory

Disadvantages:
1. No random access - O(n) to access nth element
2. Extra memory for node pointers
3. Not cache-friendly due to non-contiguous memory
*/
```

## Performance Optimization
```java
// StringBuilder for string concatenation
StringBuilder sb = new StringBuilder();
for (String s : strings) {
    sb.append(s);
}
String result = sb.toString();

// Bulk operations on collections
List<Integer> list = new ArrayList<>();
list.ensureCapacity(1000);  // Preallocate capacity
Collections.addAll(list, array);  // Bulk add

// Using appropriate data structures
Set<Integer> set = new HashSet<>(list);  // Remove duplicates efficiently
```

## Common Algorithm Templates
```java
// Binary Search Template
public int binarySearch(int[] nums, int target) {
    int left = 0, right = nums.length - 1;
    while (left <= right) {
        int mid = left + (right - left) / 2;
        if (nums[mid] == target) return mid;
        if (nums[mid] < target) left = mid + 1;
        else right = mid - 1;
    }
    return -1;
}

// Sliding Window Template
public int slidingWindow(String s) {
    Map<Character, Integer> window = new HashMap<>();
    int left = 0, right = 0;
    int result = 0;
    
    while (right < s.length()) {
        char c = s.charAt(right);
        window.put(c, window.getOrDefault(c, 0) + 1);
        right++;
        
        while (/* window needs shrinking */) {
            char d = s.charAt(left);
            window.put(d, window.get(d) - 1);
            left++;
        }
        result = Math.max(result, right - left);
    }
    return result;
}

// Graph DFS Template
public void dfs(int node, Set<Integer> visited, Map<Integer, List<Integer>> graph) {
    if (visited.contains(node)) return;
    
    visited.add(node);
    // Process node
    
    for (int neighbor : graph.getOrDefault(node, new ArrayList<>())) {
        dfs(neighbor, visited, graph);
    }
}

// BFS Template
public void bfs(int start, Map<Integer, List<Integer>> graph) {
    Queue<Integer> queue = new LinkedList<>();
    Set<Integer> visited = new HashSet<>();
    
    queue.offer(start);
    visited.add(start);
    
    while (!queue.isEmpty()) {
        int node = queue.poll();
        // Process node
        
        for (int neighbor : graph.getOrDefault(node, new ArrayList<>())) {
            if (!visited.contains(neighbor)) {
                visited.add(neighbor);
                queue.offer(neighbor);
            }
        }
    }
}
```

## Collections Utility Methods

| Method | Description | Example |
|--------|-------------|---------|
| `Collections.sort(List)` | Sorts list in natural order | `Collections.sort(list)` |
| `Collections.reverse(List)` | Reverses list order | `Collections.reverse(list)` |
| `Collections.shuffle(List)` | Randomly reorders elements | `Collections.shuffle(list)` |
| `Collections.swap(List,int,int)` | Swaps elements at positions | `Collections.swap(list, i, j)` |
| `Collections.min/max(Collection)` | Finds min/max element | `Collections.min(list)` |
| `Collections.fill(List,T)` | Sets all elements to value | `Collections.fill(list, 0)` |
| `Collections.frequency(Collection,Object)` | Counts occurrences | `Collections.frequency(list, "x")` |
| `Collections.nCopies(int,T)` | Creates list with n copies | `List<Integer> zeros = Collections.nCopies(10, 0)` |
| `Collections.unmodifiableList(List)` | Makes list read-only | `List<String> immutable = Collections.unmodifiableList(list)` |
| `Collections.synchronizedList(List)` | Makes list thread-safe | `List<String> syncList = Collections.synchronizedList(list)` |

