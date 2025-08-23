# topics to revise before your interview

## Core Java
- Internal working of `HashMap`, `HashSet`, and `TreeSet`
- `Comparable` vs `Comparator`
- Collections utility classes (`Collections`, `Arrays`, etc.)
- Fail-fast vs fail-safe iterators
- Java 8 Features
- Stream API
- Lambda Expressions and Functional Interfaces
- Multithreading and Lock Strategies
- String Class
- Static Keyword (blocks, classes, variables)
- Global and Instance Variables
- Java Memory Model
- Collections (All, HashMap - synchronized and other types)
  - Using model classes as keys in HashMap or Set
- Design Patterns (Singleton, Factory, Observer, etc.)
- SOLID Principles
- Java Version Usage and Java 8 Differences
- Abstract Class and Functional Interfaces (example-based questions)
- Try-Catch-Finally (detailed)
- Main Function Miscellaneous Questions
- Operator Precedence
- Constructor Chaining

### Generics
- Wildcards (`? extends`, `? super`)
- Bounded and unbounded types
- Generic methods and classes

### Stream API (Advanced)
- Introduction to streams and functional programming in Java
- Intermediate and terminal operations (`filter`, `map`, `reduce`)
- `Optional` class and its usage
- Deep dive into stream parallelism
- Advanced usage of Collectors and custom collectors
- Reactive programming with Java

### Lambda Expressions and Functional Interfaces (Advanced)
- `Function`, `Consumer`, `Supplier`, `Predicate` interfaces
- Method references and constructor references

## Concurrency and Multithreading
- Thread safety, race conditions, and deadlock
- Executors, `Callable`, and `Future`
- Synchronized blocks, locks (`ReentrantLock`, `ReadWriteLock`)
- `volatile`, `ThreadLocal`, and Atomic variables
- Multithreading and Lock Strategies
- `ConcurrentHashMap`, `CopyOnWriteArrayList`, `BlockingQueue`
- `ForkJoinPool` and parallel streams
- `CompletableFuture` and asynchronous programming
- Non-blocking algorithms, CAS (Compare-And-Swap)
- Advanced lock-free programming techniques
- `Phaser`, `CyclicBarrier`, and `Exchanger` in concurrency

## JVM Internals & Memory Management
- JVM architecture and components (`ClassLoader`, JIT, garbage collector)
- Memory management (heap, stack, meta space)
- Garbage collection algorithms (CMS, G1)
- Deep dive into garbage collection (GC tuning, GC logs, troubleshooting)
- JVM tuning and optimization
- Profiling tools (VisualVM, JMC, JProfiler)
- Heap dump analysis and memory leak detection
- Thread dump analysis and troubleshooting performance issues
- Understanding G1, ZGC, Shenandoah GC
- Tuning GC for high throughput and low-latency applications
- Byte code analysis and optimization
- Optimizing data structures for performance
- Understanding JIT and its optimizations
- Java memory model and volatile semantics
- False sharing and memory barriers
- Optimizing memory footprint of applications

## Reflection, Annotations, and Class Loading
- Understanding reflection and its use cases
- Creating and processing custom annotations
- Performance considerations of reflection
- ClassLoader hierarchy (bootstrap, system, extension)
- Dynamic class loading and reloading
- Module system and modular JARs (Java 9+)
- Benefits of modularity and encapsulation

## Testing
- Writing unit tests using JUnit and TestNG
- Mocking dependencies (Mockito)
- Understanding code coverage tools

## Security in Java
- Secure coding practices
- Java Security APIs (JCA, JCE, JAAS)
- Encryption, decryption, and digital signatures

## Databases
- Basic Concepts
- Normalization
- Query Problems
  - Aggregation functions
  - Order and group by
  - Clauses (like, string split functions, ranking)
- Connection Pool
- Database Optimization Techniques
  - General
  - Spring Boot-specific

## Spring Boot
- Basic Concepts (Annotations, Injection)
- Stereotype Annotations
- Bean Life Cycle and Scopes
- Configuration (Property Files, YAML, PostConstruct)
- REST API Concepts
  - All mappings
  - PUT vs POST scenarios
  - XML/JSON
  - Headers
  - Variables
  - Interceptor
  - Naming conventions
- Exception Handling
- JUnit/Mocking Basics (@SpringBootTest)
- Spring Security (JWT, Access Tokens, TLS, SSL, Message Layer Security)
- Repository
- Maven/build tools
- Database Connection
- Kafka Knowledge (optional)

## Microservices
- Design Patterns
- Inter-Service Communication
- Monolithic vs Microservices (Advantages and Disadvantages)
- Resiliency and Efficiency
- Scenario-Based Questions
  - Service availability
  - Deployment strategies
- Deployment Strategies (lower downtime, handle pending requests)
- Step-by-Step Service Calling Process
- Service Discovery
- Load Balancing
- API Gateway
- HTTP Status Codes

## Deployment CI/CD
- Docker and Kubernetes Basics
  - Docker Image
  - Container vs Pod
  - Communication between pods/containers
- Pipeline Stages
- Code Quality and Vulnerability Checks
  - Code quality scan (Prisma/other tools)
  - Vulnerability scan (Checkmark/other tools)
- End-to-End Deployment Strategy
- Server, Data centers, Instances
  - Handling traffic
  - Rolling out updates strategies
- Open shift (if applicable)
  - Secret map
  - Config map