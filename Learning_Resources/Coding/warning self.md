

## Warnings section

1. Don't do shallow copy : next iteration will affect the review result
2. unvisit the nodes while reverting back if required.
    - ex : word search if didnot find . while going back unvisit that node , so if other path can use that path.

### Handling Different Types with Deep Copy Examples

#### 1. **1D Array to 2D Array**

- **Problem**: If you save a 1D array to a 2D array without deep copying, changes to the 1D array will reflect in the 2D
  array.
- **Solution**:

```java
List<List<Integer>> matrix = new ArrayList<>();
List<Integer> currentRow = new ArrayList<>();
matrix.

add(new ArrayList<>(currentRow)); // Add a DEEP COPY of currentRow
```

#### 2. **Primitive Arrays**

- **Problem**: Adding a primitive array directly to a list without cloning will cause changes to the original array to
  reflect in the list.
- **Solution**:

```java
List<int[]> matrix = new ArrayList<>();
int[] currentRow = new int[2]; // Example size

// Iteration 1
currentRow[0]=1;
currentRow[1]=2;
        matrix.

add(currentRow.clone()); // Deep copy
```

#### 3. **List of Strings**

- **Problem**: If you add a `List<String>` to another list and modify the original list, the changes will reflect in the
  outer list.
- **Solution**:

```java
List<List<String>> outerList = new ArrayList<>();
List<String> innerList = new ArrayList<>();
innerList.

add("example");
outerList.

add(new ArrayList<>(innerList)); // Deep copy
```

#### 4. **Maps**

- **Problem**: Adding a map directly to another map or modifying the original map will affect the copied map.
- **Solution**:

```java
Map<String, String> originalMap = new HashMap<>();
originalMap.

put("key","value");

Map<String, String> deepCopiedMap = new HashMap<>(originalMap); // Deep copy

// For nested maps:
Map<String, Map<String, String>> nestedMap = new HashMap<>();
Map<String, String> innerMap = new HashMap<>();
innerMap.

put("innerKey","innerValue");
nestedMap.

put("outerKey",new HashMap<>(innerMap)); // Deep copy
```

#### 5. **Recursion / Dynamic Programming Problem**

- how to solve DP question
    1. Determine the recurrence relation for the problem.
    2. Identify the base case(s) to stop the recursion.
    3. Implement the recursive solution based on the recurrence relation.
    4. Optimize the solution by adding memoization to store intermediate results.
    5. Transform the recursive solution into an iterative "Bottom-Up" dynamic programming approach.
    6. Apply further optimizations, if possible, to improve time or space complexity.
 
- **Climb Stairs Question**:
    - Start by writing a recursive solution and visualize it using a tree diagram.
    - Identify any repeating subtrees in the diagram. If found, store their results to reuse them later.
      ![img.png](static/img.png)
    - **Key Insight**: When climbing `n` steps, you can take either 1 or 2 steps at a time.
        - Check if the result for a specific number of steps has already been calculated.
        - Save the result of `climbStairs(x)` to avoid recalculating it in the future.

#### 5. **Nested Objects**

- **Problem**: If an object contains nested objects, changes to the nested objects will reflect in the original object.
- **Solution**:

```java
class Person {
    String name;
    Address address;

    Person(String name, Address address) {
        this.name = name;
        this.address = new Address(address.city); // Deep copy of Address
    }
}

class Address {
    String city;

    Address(String city) {
        this.city = city;
    }
}

Person original = new Person("John", new Address("New York"));
Person deepCopy = new Person(original.name, original.address);
```

#### 6. **Collections**

- **Problem**: Collections like `List`, `Set`, or `Map` containing mutable objects require deep copying to avoid shared
  references.
- **Solution**:

```java
List<Person> originalList = new ArrayList<>();
originalList.

add(new Person("John", new Address("New York")));
List<Person> deepCopiedList = new ArrayList<>();
for(
Person person :originalList){
        deepCopiedList.

add(new Person(person.name, person.address)); // Deep copy
        }
```

#### 7. **Serialization for Deep Copy**

- **Problem**: Manual deep copying can be tedious for complex objects.
- **Solution**: Use serialization to create a deep copy.

```java
import java.io.*;

public static Object deepCopy(Object object) throws IOException, ClassNotFoundException {
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    ObjectOutputStream oos = new ObjectOutputStream(bos);
    oos.writeObject(object);
    ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
    ObjectInputStream ois = new ObjectInputStream(bis);
    return ois.readObject();
}
```

#### Summary

- Always use deep copy when working with nested or mutable objects to avoid unintended side effects.
- Use appropriate methods like `clone()`, constructors, or serialization based on the complexity of the object.
