import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MostUsedJavaSnippetsForInterview {

    public static void main(String[] args) {

        // Arrays
        arrayAndUtilMethods();

        hashMap();

        PriorityQueue();

        character();

        stacks();


        streams();


        //Threads and executor service
        threadsAndExecutorService();

        // Collection
        collectionsAndUtilMethods();

        // Thread safe collection creation
        threadSafeCollectionsCreation();

    }

    private static void PriorityQueue() {
        // Min-Heap (default behavior)
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        minHeap.offer(5);
        minHeap.offer(2);
        minHeap.offer(8);
        int min = minHeap.poll(); // Retrieves and removes the smallest element

        // Max-Heap using custom comparator
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());

        //old way : using collections.reverseOrder()
        PriorityQueue<Integer> maxHeap2 = new PriorityQueue<>(Collections.reverseOrder());


        // Initialize from list
        List<Integer> list = Arrays.asList(3, 1, 4, 1, 5);
        PriorityQueue<Integer> pq2 = new PriorityQueue<>(list);

        //heapify a list
        List<Integer> list = Arrays.asList(3, 1, 4, 1, 5);
        PriorityQueue<Integer> pq = new PriorityQueue<>(list);
            

    }


    private static void arrayAndUtilMethods() {
        //Array


// Populate 2D array
        int[][] dp = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            Arrays.fill(dp[i], -1);                   // Fill each row with -1
        }

        int[] arr = {5, 2, 8, 1, 3};
        //or
        int[] arr2 = new int[]{1, 2, 3};

        String[] strArr = {"banana", "apple", "orange"};

        // Sort array
        Arrays.sort(arr);

        //custom sort for objects
        Arrays.sort(strArr, Comparator.comparingInt(String::length));
        //or
        Arrays.sort(strArr, (a, b) -> a.length() - b.length());


        // Binary search in array
        int index = Arrays.binarySearch(arr, 3);

        //fill the array with a specific value
        Arrays.fill(arr, 10);

        // Convert array to list - different approaches
        // 1. Arrays.asList() - Returns fixed-size list (immutable size, but elements can be modified)
        List<String> fixedSizeList = Arrays.asList(strArr);  // Cannot add/remove elements

        // 2. Create mutable list using ArrayList constructor
        List<String> list = new ArrayList<>(Arrays.asList(strArr));  // Can add/remove elements

        // convert collection to array
        String[] array = list.toArray(new String[0]);

        List<String> result2 = Arrays.stream(strArr)
                .filter(s -> s.startsWith("b"))
                .map(String::toUpperCase)
                .collect(Collectors.toList());


        //return new array with specific values
        //  return new int[]{1,2,3,4,5};

    }

    private static void character() {

        String s = "Hello";
        //access character
        char c = s.charAt(0); // Accessing the first character

//iterate through each character in a string
        String str = "Hello";
        for (char eachChar : str.toCharArray()) {
            System.out.println(eachChar); // Prints each character
        }

// convert character array to string
        char[] charArray = {'H', 'e', 'l', 'l', 'o'};
        String str = new String(charArray); // Convert char array to String

// convert number in string format to int
        String str2 = "123";
        int num = Integer.parseInt(str2); // Convert String to int

// convert int to string 
        String strNum = String.valueOf(num); // Convert int to String
//or 
        String strNum = Integer.toString(num); // Convert int to String

//string builder
        StringBuilder sb = new StringBuilder(str);      // Mutable string


///////////////////////// other
//sting methods
        String str = "Hello";
        String upper = str.toUpperCase(); // Convert to uppercase
        String lower = str.toLowerCase(); // Convert to lowercase
        String trimmed = str.trim(); // Remove leading and trailing spaces
        String replaced = str.replace("e", "a"); // Replace 'e' with 'a'
        String substring = str.substring(1, 4); // Get substring from index 1 to 3
        String[] parts = str.split("l"); // Split string by 'l'


        boolean isDigit = Character.isDigit(c); // Check if character is a digit
        boolean isLetter = Character.isLetter(c); // Check if character is a letter
        boolean isLetterOrDigit = Character.isLetterOrDigit(c); // Check if character is a letter or digit
        boolean isWhitespace = Character.isWhitespace(c); // Check if character is whitespace
        boolean isUpperCase = Character.isUpperCase(c); // Check if character is uppercase
        boolean isLowerCase = Character.isLowerCase(c); // Check if character is lowercase
        boolean isAlphabetic = Character.isAlphabetic(c); // Check if character is alphabetic


    }

    private static void stacks() {

        Stack<Integer> stack = new Stack<>();
        //or
        Deque<Integer> stack2 = new ArrayDeque<>();
        //or
        
        stack.push(1);           // Add element
        stack.pop();            // Remove and return top element
        stack.peek();           // View top element without removing
        stack.isEmpty();        // Check if empty
        stack.size();          // Get number of elements

    }


    private static void hashMap() {
        Map<String, Integer> map = new HashMap<>();

        //basics
        map.put("five", 5);
        map.putIfAbsent("seven", 7);
        map.get("two");
        map.getOrDefault("three", 3);
        map.compute("five", (key, value) -> value + 5);
        map.remove("one");

        //convert map to list of values
        List<Integer> valuesList = new ArrayList<>(map.values());

        //convert Map<String,List<Integer>> to list of integers
        Map<String, List<Integer>> complexMap = new HashMap<>();
        ArrayList<Integer> result = new ArrayList<>();
        for (List<Integer> vals : complexMap.values()) {
            result.addAll(vals);
        }

        //using streams
        List<Integer> valuesListStream = complexMap.values().stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
        //or
        List<Integer> valuesListStream2 = complexMap.values().stream()
                .flatMap(vals -> vals.stream())
                .collect(Collectors.toList());

    }

    private static void threadsAndExecutorService() {
      
    /* 
    Difference between extending Thread and implementing Runnable?
     - Use Runnable if you want your class to extend something else (since Java doesn’t support multiple inheritance).

    Why not use Thread directly in real projects?
    - Because manual thread management is messy; you should use thread pools (ExecutorService).
    
    */

    /*
     Callable<V>
    - Represents a task that returns a result of type V and can throw checked exceptions.

    Future<V>
    - Represents the result of an asynchronous computation. It provides methods to check if the computation is complete, to wait for its completion, and to retrieve the result.

    CompletableFuture<T>
    - A more advanced version of Future that allows you to chain multiple asynchronous computations together and handle their results or exceptions in a more flexible way.


     */

        //create a fixed thread pool
        ExecutorService executor = Executors.newFixedThreadPool(5);

        //submit tasks to the pool
        executor.submit(() -> {
            System.out.println("Task 1 is running");
        });
        //add Runnable to the executor
        Runnable runnable = () -> {
            System.out.println("Runnable task is running");
        };
        executor.submit(runnable);


        //adding multiple in for loop
        for (int i = 0; i < 3; i++) {
            final int taskId = i;
            executor.submit(() -> {
                System.out.println("Task " + taskId + " is running");
            });
        }

        executor.submit(runnable);

        //shutdown the executor
        executor.shutdown();

        //executor with return value
        ExecutorService executorWithReturn = Executors.newFixedThreadPool(3);
        Future<Integer> futureResult = executorWithReturn.submit(() -> {
            //perform some computation
            return 42;  //return the result
        });
        try {
            int result = futureResult.get();
            System.out.println("Result: " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        executorWithReturn.shutdown();

        //CompletableFuture example
        CompletableFuture.supplyAsync(() -> {
                    return "Hello";
                }).thenApply(s -> s + " World")
                .thenAccept(System.out::println);

        //example with multiple thenApply

        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
                    // runs in a different thread
                    System.out.println("Running in: " + Thread.currentThread().getName());
                    return 10;
                })
                .thenApply(result -> result * 2)
                .thenAccept(finalResult -> {
                    System.out.println("Final result: " + finalResult);
                });
        // Optionally wait for completion
        future.join(); // or future.get() with try-catch


    }

    private static void collectionsAndUtilMethods() {

        //collection usage ( any kind of collection set,map or any
        List<String> list = new ArrayList<>();
        list.add("ram");
        list.add("jam");
        list.add("test");

        //sort any collections
        Collections.sort(list);

        //sort element using binary search
        int result = Collections.binarySearch(list, "sam");

        //reverse sort the elements
        Collections.reverse(list);

        //sort in custom order
        Collections.sort(list, Comparator.comparingInt(String::length));
        //or
        Collections.sort(list, (a, b) -> a.length() - b.length());


        //find min
        String min = Collections.min(list);

        //find max
        String max = Collections.max(list);

        //shuffle
        Collections.shuffle(list);

        //  populate a collection with values
        Collections.fill(list, "test");

        //convert collection to array
        String[] array = list.toArray(new String[0]);

        //convert array to collection mutable
        List<String> newList = new ArrayList<>(Arrays.asList(array));


        System.out.println(result + ":" + max + ":" + min);
    }

    public static void streams() {

        List<String> names = Arrays.asList("John", "Jane", "Bob", "Alice");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // 1. Basic Stream Operations
        // Filter - get even numbers
        List<Integer> evenNumbers = numbers.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());

        // Map - transform elements
        List<String> upperNames = names.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        // FlatMap - flatten nested collections
        List<List<Integer>> nestedList = Arrays.asList(
                Arrays.asList(1, 2),
                Arrays.asList(3, 4));
        List<Integer> flatList = nestedList.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        // 2. Terminal Operations
        // Reduce - sum all numbers
        int sum = numbers.stream()
                .reduce(0, Integer::sum);

        // Count, Min, Max
        long count = numbers.stream().count();
        Optional<Integer> min = numbers.stream().min(Integer::compareTo);
        Optional<Integer> max = numbers.stream().max(Integer::compareTo);

        // 3. Collecting Results
        // To List
        List<String> toList = names.stream()
                .collect(Collectors.toList());

        // To Set
        Set<String> toSet = names.stream()
                .collect(Collectors.toSet());

        // To Map
        Map<String, Integer> toMap = names.stream()
                .collect(Collectors.toMap(
                        name -> name,           // Key mapper
                        String::length          // Value mapper
                ));

        // Group By
        Map<Integer, List<String>> groupByLength = names.stream()
                .collect(Collectors.groupingBy(String::length));

        // 4. Advanced Operations
        // Distinct - remove duplicates
        List<Integer> distinct = numbers.stream()
                .distinct()
                .collect(Collectors.toList());

        // Sorted - sort elements
        List<String> sorted = names.stream()
                .sorted()
                .collect(Collectors.toList());

        // Custom sort
        List<String> customSorted = names.stream()
                .sorted(Comparator.comparing(String::length).reversed())
                .collect(Collectors.toList());

        // 5. Short-Circuit Operations
        // Find First
        Optional<String> first = names.stream()
                .filter(n -> n.startsWith("J"))
                .findFirst();

        // Any Match
        boolean hasShortNames = names.stream()
                .anyMatch(name -> name.length() < 4);

        // 6. Parallel Streams
        // Parallel processing
        List<String> parallelProcessed = names.parallelStream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        // 7. Numeric Streams
        // IntStream, LongStream, DoubleStream
        IntStream intStream = IntStream.range(1, 5); // 1,2,3,4
        double average = numbers.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0.0);

        // 8. Chaining Multiple Operations
        List<String> complexOperation = names.stream()
                .filter(n -> n.length() > 3)
                .map(String::toUpperCase)
                .sorted()
                .limit(2)
                .collect(Collectors.toList());
    }

    public static void threadSafeCollectionsCreation() {
        // 1. Using Collections.synchronizedXXX methods

        // Thread-safe but with performance overhead due to synchronization
        List<String> syncList = Collections.synchronizedList(new ArrayList<>());
        Set<String> syncSet = Collections.synchronizedSet(new HashSet<>());
        Map<String, String> syncMap = Collections.synchronizedMap(new HashMap<>());


// 2. Using Concurrent Collections (Recommended for most cases) :Better performance than synchronized collections due to internal segmentation

        // CopyOnWriteArrayList - Thread-safe list optimized for read-heavy scenarios
        List<String> copyOnWriteList = new CopyOnWriteArrayList<>(); //for read-heavy systems
        Set<String> copyOnWriteSet = new CopyOnWriteArraySet<>(); //for read-heavy systems

        // Queue implementations: ConcurrentLinkedQueue - Unbounded thread-safe queue
        Queue<String> concurrentQueue = new ConcurrentLinkedQueue<>();

        //Deque implementations
        Deque<String> concurrentDeque = new ConcurrentLinkedDeque<>();

        //  Maps:
        ConcurrentHashMap<String, Integer> modernMap = new ConcurrentHashMap<>();
        //Set
        Set<String> modernSet = ConcurrentHashMap.newKeySet();


    }

}
