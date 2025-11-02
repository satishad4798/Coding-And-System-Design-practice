
# Core Java Snippets — 60 Questions with Answers & Explanations

This file contains **60 highly asked snippet-style questions** for **SDE-2 (Core Java)** interviews.
Each item includes a **code snippet**, the **expected output**, and a **clear explanation**.

**Topics covered:** class loading, constructors, static vs instance, overloading/overriding,
wrappers & autoboxing, strings & memory, equals/hashCode, collections, streams,
exceptions, I/O, immutability, cloning, serialization basics, and a taste of concurrency.

---

---

### 1. Order: Static block → Instance block → Constructor (Parent before Child)

```java
class A {
    static { System.out.println("Static A"); }
    { System.out.println("Instance A"); }
    A(){ System.out.println("Ctor A"); }
}
class B extends A {
    static { System.out.println("Static B"); }
    { System.out.println("Instance B"); }
    B(){ System.out.println("Ctor B"); }
}
public class Test {
    public static void main(String[] args){ new B(); }
}
```

**Output:**
```
Static A
Static B
Instance A
Ctor A
Instance B
Ctor B
```

**Explanation:**  
Class initialization happens once per class (static blocks). When creating `B`, the JVM initializes `A` first.
For each object: instance initializer(s) run before the constructor. Parent initialization precedes child.


---

### 2. Multiple static initializers execute in text order

```java
class T {
    static { System.out.println("S1"); }
    static { System.out.println("S2"); }
    public static void main(String[] args){}
}
```

**Output:**
```
S1
S2
```

**Explanation:**  
Static initializers execute exactly once per class, in the order they appear in source.


---

### 3. Static fields + static block text order

```java
class T {
    static int a = 10;
    static { a = 20; System.out.println(a); }
    public static void main(String[] args){ System.out.println(a); }
}
```

**Output:**
```
20
20
```

**Explanation:**  
Static members execute in textual order during class loading. The block sets `a` to 20 before main runs.


---

### 4. Final static compile-time constants inlining

```java
class A { static final int X = 10; }
class B {
    public static void main(String[] args){
        System.out.println(A.X);
    }
}
```

**Output:**
```
10
```

**Explanation:**  
`static final` primitives/Strings that are compile-time constants may be inlined by the compiler. Changing `A.X` and reusing old B.class may show stale values (classpath stale build pitfall).


---

### 5. Parent field hidden by child field (not override)

```java
class A { int x = 1; }
class B extends A { int x = 2; }
public class T {
    public static void main(String[] args){
        A a = new B();
        System.out.println(a.x);
    }
}
```

**Output:**
```
1
```

**Explanation:**  
Fields are **hidden**, not polymorphic. The field accessed is chosen by reference type (`A`), not runtime object (`B`).


---

### 6. Static method hiding vs instance overriding

```java
class P { static void s(){ System.out.println("P.s"); } }
class C extends P { static void s(){ System.out.println("C.s"); } }
public class T { public static void main(String[] args){ P p = new C(); p.s(); } }
```

**Output:**
```
P.s
```

**Explanation:**  
Static methods are resolved by **reference type** at compile time. No dynamic dispatch for statics.


---

### 7. Overloading chooses most specific method (null argument)

```java
class A {
    void m(Object o){ System.out.println("Object"); }
    void m(String s){ System.out.println("String"); }
}
public class T { public static void main(String[] args){ new A().m(null); } }
```

**Output:**
```
String
```

**Explanation:**  
Overload resolution picks the most specific applicable method; `String` is more specific than `Object`.


---

### 8. Covariant return types in overriding

```java
class P { Number f(){ return 1; } }
class C extends P { Integer f(){ return 2; } }
public class T { public static void main(String[] a){ System.out.println(new C().f()); } }
```

**Output:**
```
2
```

**Explanation:**  
Overriding allows **covariant** (more specific) return types.


---

### 9. Private methods are not overridden (they are hidden)

```java
class A { private void m(){ System.out.println("A"); } void call(){ m(); } }
class B extends A { void m(){ System.out.println("B"); } }
public class T { public static void main(String[] a){ new B().call(); } }
```

**Output:**
```
A
```

**Explanation:**  
`private` methods are not visible to subclasses and are **not** overridden. `call()` binds to `A.m()`.


---

### 10. Final method cannot be overridden

```java
class A { final void m(){ System.out.println("A"); } }
class B extends A { /* void m(){ } // compile error */ }
public class T { public static void main(String[] a){ new B().m(); } }
```

**Output:**
```
A
```

**Explanation:**  
`final` prevents overriding. Attempting to override produces a compile-time error.


---

### 11. equals without hashCode breaks HashSet

```java
import java.util.*;
class Emp{
    int id;
    Emp(int id){this.id=id;}
    public boolean equals(Object o){ return o instanceof Emp && ((Emp)o).id==id; }
}
public class T{
    public static void main(String[] a){
        Set<Emp> s = new HashSet<>();
        s.add(new Emp(1)); s.add(new Emp(1));
        System.out.println(s.size());
    }
}
```

**Output:**
```
2
```

**Explanation:**  
If `hashCode()` isn’t consistent with `equals()`, hash-based collections may place equal objects in different buckets.


---

### 12. hashCode and equals consistent

```java
import java.util.*;
class Emp{
    int id;
    Emp(int id){this.id=id;}
    public boolean equals(Object o){ return o instanceof Emp && ((Emp)o).id==id; }
    public int hashCode(){ return Integer.hashCode(id); }
}
public class T{
    public static void main(String[] a){
        Set<Emp> s = new HashSet<>();
        s.add(new Emp(1)); s.add(new Emp(1));
        System.out.println(s.size());
    }
}
```

**Output:**
```
1
```

**Explanation:**  
Contract: equal objects must have equal hash codes. Implement both together.


---

### 13. TreeSet with Comparable vs Comparator

```java
import java.util.*;
class Emp implements Comparable<Emp>{
    int id; Emp(int id){this.id=id;}
    public int compareTo(Emp e){ return Integer.compare(id, e.id); }
}
public class T{
    public static void main(String[] a){
        Set<Emp> s = new TreeSet<>();
        s.add(new Emp(2)); s.add(new Emp(1)); s.add(new Emp(2));
        System.out.println(s.size());
    }
}
```

**Output:**
```
2
```

**Explanation:**  
TreeSet uses `compareTo`/`Comparator` for ordering *and* equality (no duplicates if compareTo==0).


---

### 14. Mutating a key used in HashMap breaks lookup

```java
import java.util.*;
class K{
    int id;
    K(int id){this.id=id;}
    public int hashCode(){return id;}
    public boolean equals(Object o){ return o instanceof K && ((K)o).id==id; }
}
public class T{
    public static void main(String[] a){
        Map<K,String> m = new HashMap<>();
        K k = new K(1);
        m.put(k, "v");
        k.id = 2; // mutated
        System.out.println(m.get(k));
    }
}
```

**Output:**
```
null
```

**Explanation:**  
Changing fields that contribute to `hashCode` after insertion corrupts bucket mapping. Keys must be immutable.


---

### 15. IdentityHashMap uses reference equality

```java
import java.util.*;
public class T{
    public static void main(String[] a){
        Map<String,String> m = new IdentityHashMap<>();
        m.put(new String("x"), "1");
        m.put(new String("x"), "2");
        System.out.println(m.size());
    }
}
```

**Output:**
```
2
```

**Explanation:**  
`IdentityHashMap` compares keys by `==` rather than `equals()`. Distinct String objects are distinct keys.


---

### 16. String pool vs heap

```java
public class T{
    public static void main(String[] a){
        String s1 = "Java";
        String s2 = new String("Java");
        String s3 = "Java";
        System.out.println(s1==s2);
        System.out.println(s1==s3);
    }
}
```

**Output:**
```
false
true
```

**Explanation:**  
Literals go to the intern pool. `new String` forces a new heap object.


---

### 17. intern() unifies references

```java
public class T{
    public static void main(String[] a){
        String s1 = new String("x");
        String s2 = s1.intern();
        String s3 = "x";
        System.out.println(s2==s3);
    }
}
```

**Output:**
```
true
```

**Explanation:**  
`intern()` returns the canonical pooled instance for that content.


---

### 18. StringBuilder vs String concatenation in loops

```java
public class T{
    public static void main(String[] a){
        String s = "";
        for(int i=0;i<3;i++){ s += i; }
        System.out.println(s);
    }
}
```

**Output:**
```
012
```

**Explanation:**  
`+=` in loops creates many intermediate Strings. Prefer `StringBuilder` for performance in hot loops.


---

### 19. substring() new String with shared char array (pre-Java 7u6)

```java
// Historical note snippet (no runnable behavior change here)
```

**Output:**
```
(n/a)
```

**Explanation:**  
In older JDKs, `substring()` shared the original char[] causing memory leaks if a small substring referenced a large backing array. Modern JDKs copy the range.


---

### 20. UTF-16 length vs code points

```java
public class T{
    public static void main(String[] a){
        String s = "😀"; // surrogate pair
        System.out.println(s.length());       // UTF-16 code units
        System.out.println(s.codePointCount(0, s.length())); // code points
    }
}
```

**Output:**
```
2
1
```

**Explanation:**  
`String.length()` counts UTF‑16 code units, not Unicode code points. Use `codePointCount` for actual characters.


---

### 21. String equality `==` vs `.equals()`

```java
public class T{
    public static void main(String[] a){
        String x = new String("hi");
        String y = new String("hi");
        System.out.println(x==y);
        System.out.println(x.equals(y));
    }
}
```

**Output:**
```
false
true
```

**Explanation:**  
`==` compares references, `.equals()` compares content.


---

### 22. String immutability (no in-place change)

```java
public class T{
    public static void main(String[] a){
        String s = "ab";
        s.concat("c");
        System.out.println(s);
    }
}
```

**Output:**
```
ab
```

**Explanation:**  
`concat` returns a new String. `String` is immutable.


---

### 23. StringBuilder is mutable

```java
public class T{
    public static void main(String[] a){
        StringBuilder sb = new StringBuilder("ab");
        sb.append("c");
        System.out.println(sb.toString());
    }
}
```

**Output:**
```
abc
```

**Explanation:**  
`StringBuilder` modifies its internal buffer; call `toString()` to get a String.


---

### 24. Integer caching (−128 to 127)

```java
public class T{
    public static void main(String[] a){
        Integer i1=100, i2=100, i3=200, i4=200;
        System.out.println(i1==i2);
        System.out.println(i3==i4);
    }
}
```

**Output:**
```
true
false
```

**Explanation:**  
Integer cache covers −128..127 by default. Outside the range, autoboxing creates new objects.


---

### 25. Autoboxing pitfall with `==`

```java
public class T{
    public static void main(String[] a){
        Integer x=128, y=128;
        System.out.println(x==y);
    }
}
```

**Output:**
```
false
```

**Explanation:**  
Outside cache range, references differ. Use `.equals()` for value comparison.


---

### 26. NullPointerException via unboxing

```java
public class T{
    static Integer v = null;
    public static void main(String[] a){
        int x = v; // unbox
    }
}
```

**Output:**
```
NullPointerException
```

**Explanation:**  
Unboxing a null wrapper throws `NullPointerException`.


---

### 27. Wrapper vs primitive in comparisons

```java
public class T{
    public static void main(String[] a){
        Integer x = 10; int y = 10;
        System.out.println(x==y);
    }
}
```

**Output:**
```
true
```

**Explanation:**  
Wrapper is unboxed to primitive for `==` comparison.


---

### 28. BigDecimal equals vs compareTo

```java
import java.math.BigDecimal;
public class T{
    public static void main(String[] a){
        BigDecimal b1=new BigDecimal("1.0");
        BigDecimal b2=new BigDecimal("1.00");
        System.out.println(b1.equals(b2));
        System.out.println(b1.compareTo(b2)==0);
    }
}
```

**Output:**
```
false
true
```

**Explanation:**  
`equals` considers scale; `compareTo` compares numeric value.


---

### 29. Float/Double precision trap

```java
public class T{
    public static void main(String[] a){
        System.out.println(0.1+0.2);
        System.out.println((0.1+0.2)==0.3);
    }
}
```

**Output:**
```
0.30000000000000004
false
```

**Explanation:**  
Binary floating-point cannot represent some decimals exactly. Use `BigDecimal` for money.


---

### 30. Fail-fast iterator (ArrayList)

```java
import java.util.*;
public class T{
    public static void main(String[] a){
        List<Integer> list=new ArrayList<>(List.of(1,2));
        for(Integer i: list){ list.add(3); }
    }
}
```

**Output:**
```
Exception in thread "main" java.util.ConcurrentModificationException
```

**Explanation:**  
Structural modification during iteration triggers fail-fast behavior in most `java.util` collections.


---

### 31. Use CopyOnWriteArrayList for safe iteration during writes

```java
import java.util.*;
import java.util.concurrent.*;
public class T{
    public static void main(String[] a){
        List<Integer> list=new CopyOnWriteArrayList<>(List.of(1,2));
        for(Integer i: list){ list.add(3); }
        System.out.println(list);
    }
}
```

**Output:**
```
[1, 2, 3, 3]
```

**Explanation:**  
Copy-on-write iterators operate on a snapshot; modifications don’t throw CME but are costly for frequent writes.


---

### 32. LinkedHashMap access order (LRU cache behavior)

```java
import java.util.*;
public class T{
    public static void main(String[] a){
        Map<Integer,String> m=new LinkedHashMap<>(16,0.75f,true);
        m.put(1,"A"); m.put(2,"B"); m.put(3,"C");
        m.get(2);
        System.out.println(m);
    }
}
```

**Output:**
```
{1=A, 3=C, 2=B}
```

**Explanation:**  
With accessOrder=true, recently accessed entries move to end; useful to build LRU caches.


---

### 33. TreeMap sorted keys

```java
import java.util.*;
public class T{
    public static void main(String[] a){
        Map<Integer,String> m=new TreeMap<>();
        m.put(3,"C"); m.put(1,"A"); m.put(2,"B");
        System.out.println(m);
    }
}
```

**Output:**
```
{1=A, 2=B, 3=C}
```

**Explanation:**  
TreeMap maintains keys in natural (or comparator) order.


---

### 34. PriorityQueue natural ordering

```java
import java.util.*;
public class T{
    public static void main(String[] a){
        PriorityQueue<Integer> pq=new PriorityQueue<>();
        pq.add(3); pq.add(1); pq.add(2);
        System.out.println(pq.poll()+" "+pq.poll()+" "+pq.poll());
    }
}
```

**Output:**
```
1 2 3
```

**Explanation:**  
`PriorityQueue` is a min-heap by default (natural order). It does not guarantee full ordering when iterating.


---

### 35. Unmodifiable view is shallow

```java
import java.util.*;
public class T{
    public static void main(String[] a){
        List<List<Integer>> base = new ArrayList<>();
        base.add(new ArrayList<>(List.of(1)));
        List<List<Integer>> unmod = List.copyOf(base);
        base.get(0).add(2);
        System.out.println(unmod);
    }
}
```

**Output:**
```
[[1, 2]]
```

**Explanation:**  
`List.copyOf` creates an unmodifiable *view* of the outer structure; nested elements can still be mutable.


---

### 36. Arrays.asList is fixed-size view

```java
import java.util.*;
public class T{
    public static void main(String[] a){
        List<Integer> l = Arrays.asList(1,2,3);
        l.add(4);
    }
}
```

**Output:**
```
Exception in thread "main" java.lang.UnsupportedOperationException
```

**Explanation:**  
`Arrays.asList` returns a fixed-size list backed by the array: cannot add/remove, only set.


---

### 37. ComputeIfAbsent atomic insert

```java
import java.util.*;
public class T{
    public static void main(String[] a){
        Map<String,List<Integer>> m=new HashMap<>();
        m.computeIfAbsent("k", k->new ArrayList<>()).add(1);
        System.out.println(m);
    }
}
```

**Output:**
```
{k=[1]}
```

**Explanation:**  
`computeIfAbsent` safely initializes and inserts a value if the key is absent.


---

### 38. Collectors.groupingBy

```java
import java.util.*;
import java.util.stream.*;
public class T{
    record Emp(String dept,int sal){}
    public static void main(String[] a){
        var emps=List.of(new Emp("HR",100), new Emp("IT",300), new Emp("HR",100));
        Map<String,Integer> sum = emps.stream().collect(Collectors.groupingBy(Emp::dept, Collectors.summingInt(Emp::sal)));
        System.out.println(sum);
    }
}
```

**Output:**
```
{HR=200, IT=300}
```

**Explanation:**  
`groupingBy` + `summingInt` mirrors SQL `GROUP BY dept SUM(sal)`.


---

### 39. Stream reuse throws IllegalStateException

```java
import java.util.*;
import java.util.stream.*;
public class T{
    public static void main(String[] a){
        Stream<Integer> s = Stream.of(1,2,3);
        s.forEach(System.out::println);
        s.count(); // reuse
    }
}
```

**Output:**
```
Exception in thread "main" java.lang.IllegalStateException: stream has already been operated upon or closed
```

**Explanation:**  
Streams are single-use. Create a new stream for a new pipeline.


---

### 40. parallelStream has non-deterministic order (forEach)

```java
import java.util.*; import java.util.stream.*;
public class T{
    public static void main(String[] a){
        List.of("a","b","c","d").parallelStream().forEach(System.out::println);
    }
}
```

**Output:**
```
(letters printed in unspecified order)
```

**Explanation:**  
`forEach` doesn’t preserve encounter order in parallel. Use `forEachOrdered` to preserve order.


---

### 41. forEachOrdered preserves order

```java
import java.util.*; import java.util.stream.*;
public class T{
    public static void main(String[] a){
        List.of("a","b","c","d").parallelStream().forEachOrdered(System.out::println);
    }
}
```

**Output:**
```
a
b
c
d
```

**Explanation:**  
`forEachOrdered` preserves encounter order even in parallel streams (with potential performance cost).


---

### 42. map vs flatMap

```java
import java.util.*; import java.util.stream.*;
public class T{
    public static void main(String[] a){
        List<List<Integer>> ll = List.of(List.of(1,2), List.of(3));
        System.out.println(ll.stream().flatMap(List::stream).toList());
    }
}
```

**Output:**
```
[1, 2, 3]
```

**Explanation:**  
`flatMap` flattens nested streams into a single stream.


---

### 43. Optional.orElse vs orElseGet

```java
import java.util.*; 
public class T{
    static String slow(){ System.out.println("slow"); return "default"; }
    public static void main(String[] a){
        Optional<String> o = Optional.of("x");
        System.out.println(o.orElse(slow()));
        System.out.println(o.orElseGet(() -> slow()));
    }
}
```

**Output:**
```
slow
x
x
slow
```

**Explanation:**  
`orElse` evaluates its argument eagerly; `orElseGet` is lazy. The first call prints `slow` even though value is present.


---

### 44. findFirst vs findAny in parallel

```java
import java.util.*; import java.util.stream.*;
public class T{
    public static void main(String[] a){
        System.out.println(List.of(1,2,3,4).parallelStream().filter(n->n%2==0).findAny().isPresent());
    }
}
```

**Output:**
```
true
```

**Explanation:**  
`findAny` may return any matching element (faster in parallel). `findFirst` preserves order but may be slower.


---

### 45. reduce vs collect

```java
import java.util.*; import java.util.stream.*;
public class T{
    public static void main(String[] a){
        int sum = List.of(1,2,3).stream().reduce(0, Integer::sum);
        System.out.println(sum);
    }
}
```

**Output:**
```
6
```

**Explanation:**  
`reduce` combines elements into a single value. Use `collect` for mutable reductions (e.g., building collections).


---

### 46. peek is for debugging only

```java
import java.util.*; import java.util.stream.*;
public class T{
    public static void main(String[] a){
        var r = List.of(1,2,3).stream().peek(System.out::println).map(n->n*2).toList();
        System.out.println(r);
    }
}
```

**Output:**
```
1
2
3
[2, 4, 6]
```

**Explanation:**  
`peek` should be used for diagnostics; side-effects inside streams are discouraged.


---

### 47. Short-circuiting with limit

```java
import java.util.*; import java.util.stream.*;
public class T{
    public static void main(String[] a){
        Stream.of("a","b","c","d").peek(System.out::println).limit(2).toList();
    }
}
```

**Output:**
```
a
b
```

**Explanation:**  
Short-circuiting ops like `limit`/`findFirst` stop the pipeline early.


---

### 48. finally overrides return (anti-pattern)

```java
public class T{
    static int m(){ try{ return 1; } finally{ return 2; } }
    public static void main(String[] a){ System.out.println(m()); }
}
```

**Output:**
```
2
```

**Explanation:**  
`finally` runs after `try` and can override returns/throws; avoid returning in `finally`.


---

### 49. Catch order: specific before general

```java
public class T{
    public static void main(String[] a){
        try{ throw new ArithmeticException(); }
        catch(Exception e){ System.out.println("E"); }
        // catch(ArithmeticException e){} // compile error if placed after Exception
    }
}
```

**Output:**
```
E
```

**Explanation:**  
More specific catch blocks must come before broader ones; otherwise compile-time error.


---

### 50. try-with-resources auto-close order

```java
class A implements AutoCloseable{ public void close(){ System.out.println("A"); } }
class B implements AutoCloseable{ public void close(){ System.out.println("B"); } }
public class T{
    public static void main(String[] a) throws Exception {
        try(A a1=new A(); B b1=new B()){ }
    }
}
```

**Output:**
```
B
A
```

**Explanation:**  
Resources are closed in **reverse** declaration order.


---

### 51. Suppressed exceptions are recorded

```java
class R implements AutoCloseable{
    public void close(){ throw new RuntimeException("close"); }
}
public class T{
    public static void main(String[] a){
        try(R r=new R()){ throw new RuntimeException("try"); }
        catch(RuntimeException e){
            System.out.println(e.getMessage());
            for(Throwable t: e.getSuppressed()) System.out.println(t.getMessage());
        }
    }
}
```

**Output:**
```
try
close
```

**Explanation:**  
Exceptions thrown during `close()` are stored as **suppressed** on the primary exception.


---

### 52. Checked vs unchecked in overriding

```java
import java.io.*;
class P{ void m() throws IOException{} }
class C extends P{ void m() throws FileNotFoundException{} }
public class T{}
```

**Output:**
```
(compiles)
```

**Explanation:**  
Overriding method may declare a **narrower** checked exception or an unchecked one, not a broader/new checked type.


---

### 53. System.exit prevents finally

```java
public class T{
    public static void main(String[] a){
        try{ System.exit(0); }
        finally{ System.out.println("finally"); }
    }
}
```

**Output:**
```
(no output)
```

**Explanation:**  
`System.exit` halts the JVM without executing `finally` blocks.


---

### 54. Defensive copy in getter

```java
import java.util.Date;
class Emp{
    private final Date doj;
    Emp(Date d){ this.doj = new Date(d.getTime()); }
    public Date getDoj(){ return new Date(doj.getTime()); }
}
public class T{
    public static void main(String[] a){
        Date d=new Date(0);
        Emp e=new Emp(d);
        d.setTime(123);
        System.out.println(e.getDoj().getTime());
    }
}
```

**Output:**
```
0
```

**Explanation:**  
Constructor and getter make defensive copies to protect internal state (immutability pattern).


---

### 55. Cloneable default is shallow

```java
class Node implements Cloneable{
    int v; Node next;
    public Node clone() throws CloneNotSupportedException { return (Node)super.clone(); }
}
public class T{
    public static void main(String[] a) throws Exception{
        Node n1=new Node(); n1.v=1; n1.next=new Node();
        Node n2=n1.clone();
        System.out.println(n1.next==n2.next);
    }
}
```

**Output:**
```
true
```

**Explanation:**  
`Object.clone()` performs a field-wise shallow copy; nested objects are shared unless you deep-clone them.


---

### 56. readResolve to preserve singleton on deserialization

```java
import java.io.*;
class Singleton implements Serializable{
    private static final Singleton INST = new Singleton();
    private Singleton(){}
    Object readResolve(){ return INST; }
    static Singleton get(){ return INST; }
}
```

**Output:**
```
(n/a)
```

**Explanation:**  
Implement `readResolve` to return the canonical instance so deserialization doesn’t create a new object.


---

### 57. serialVersionUID mismatch

```java
// If serialVersionUID differs between write/read classes, deserialization fails with InvalidClassException
```

**Output:**
```
(n/a)
```

**Explanation:**  
Always define explicit `serialVersionUID` for Serializable classes to maintain compatibility across versions.


---

### 58. count++ is not atomic

```java
class Counter{ int c=0; void inc(){ c++; } }
```

**Output:**
```
(n/a)
```

**Explanation:**  
`c++` expands to load/add/store; interleavings lose increments under concurrency. Use `synchronized` or `AtomicInteger`.


---

### 59. volatile ensures visibility, not atomicity

```java
class T{ volatile int x=0; void inc(){ x++; } }
```

**Output:**
```
(n/a)
```

**Explanation:**  
`volatile` guarantees reads see latest writes but does not make compound actions (like `x++`) atomic.


---

### 60. wait releases monitor; sleep does not

```java
class T {
    final Object lock = new Object();
    void demo() throws Exception {
        synchronized(lock){
            lock.wait(100); // releases lock while waiting
        }
        Thread.sleep(100); // does not release any lock
    }
}
```

**Output:**
```
(n/a)
```

**Explanation:**  
`Object.wait` releases the monitor and reacquires it on wake; `Thread.sleep` just parks the thread without releasing locks.
