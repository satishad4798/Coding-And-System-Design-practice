# @Autowired Annotation in Spring

## Most Asked Interview Questions

### 1. What is the purpose of the `@Autowired` annotation in Spring?
- `@Autowired` tells Spring to automatically inject a dependency (a class or interface) into another class. Instead of creating objects manually, Spring manages object creation and dependency injection, promoting loose coupling.

### 2. How does `@Autowired` work internally in Spring Boot?
- When Spring Boot starts, it creates an `ApplicationContext` container. It scans for classes with Spring annotations (like `@Service`, `@Repository`, etc.). When `@Autowired` is used, Spring checks the container for a matching bean and injects it where needed.

### 3. Can you `@Autowired` a private field? Is it a good practice?
- Yes, you can autowire a private field. However, it's not recommended because it makes testing harder. Constructor-based injection is preferred for better testability and to ensure dependencies are provided at object creation.

### 4. What are the different ways to autowire dependencies in Spring?
- **Constructor Injection** (preferred): Spring calls the constructor and provides dependencies.
- **Setter Injection**: Spring calls setter methods to provide dependencies.
- **Field Injection**: Spring injects directly into fields (not recommended for testing).

### 5. What happens if Spring finds multiple beans of the same type while autowiring?
- Spring throws a `NoUniqueBeanDefinitionException`. Use `@Qualifier` to specify which bean to inject:
  ```java
  @Autowired
  @Qualifier("specificBeanName")
  private MyBean myBean;
  ```

### 6. What is the difference between `@Autowired` and `@Resource`?
- `@Autowired` works by type (class type matching).
- `@Resource` works by name (bean name matching).
- `@Autowired` is more common in Spring Boot.

### 7. How do you handle circular dependencies with `@Autowired` in Spring?
- Use `@Lazy` on one of the beans to delay initialization, or redesign classes to avoid circular dependencies.
  ```java
  @Autowired
  @Lazy
  private BeanA beanA;
  ```

### 8. Can you autowire a collection of beans in Spring? How?
- Yes. Spring injects all beans of a type into a collection:
  ```java
  @Autowired
  private List<MyBean> myBeans;
  ```

### 9. What is the difference between `@Autowired(required = false)` and `@Autowired`?
- By default, `@Autowired` expects a dependency to be present. With `required = false`, Spring skips injection if the dependency is missing (useful for optional dependencies).

### 10. Is `@Autowired` mandatory in Spring Boot?
- No. If a class has only one constructor, Spring auto-wires dependencies even without `@Autowired`. However, using `@Autowired` is still a good practice for clarity.

---

## Scenario-Based Questions

### Scenario: Multiple Beans of the Same Type
**Q:** You have two beans (`MyServiceImpl1`, `MyServiceImpl2`) implementing `MyService`. How do you inject a specific bean?
**A:** Use `@Qualifier`:
```java
@Autowired
@Qualifier("myServiceImpl1")
private MyService myService;
```

### Scenario: Optional Dependency Injection
**Q:** How do you handle an optional dependency?
**A:** Use `@Autowired(required = false)`:
```java
@Autowired(required = false)
private OptionalService optionalService;
```

### Scenario: Circular Dependency
**Q:** How do you resolve a circular dependency between `BeanA` and `BeanB`?
**A:** Use `@Lazy` on one bean:
```java
@Autowired
@Lazy
private BeanB beanB;
```

### Scenario: Constructor vs Field Injection
**Q:** Which injection type should you use?
**A:** Prefer constructor injection for better testability and to ensure dependencies are provided at creation.

### Scenario: Injecting a List of Beans
**Q:** How do you inject all implementations of `NotificationService`?
**A:**
```java
@Autowired
private List<NotificationService> notificationServices;
```

### Scenario: Injecting a Bean by Name
**Q:** How do you inject `UserService` by the name `customUserService`?
**A:**
```java
@Autowired
@Qualifier("customUserService")
private UserService userService;
```

### Scenario: Changing Dependencies at Runtime
**Q:** How do you inject different `PaymentService` implementations based on environment?
**A:** Use `@Profile`:
```java
@Profile("dev")
@Service
public class DevPaymentService implements PaymentService { ... }

@Profile("prod")
@Service
public class ProdPaymentService implements PaymentService { ... }
```

### Scenario: Autowiring a Bean with No Public Constructor
**Q:** How do you autowire a class with no public constructor?
**A:** Ensure the class has a public constructor. Spring cannot autowire without one.

### Scenario: Testing with `@Autowired`
**Q:** How do you inject a mock in a unit test?
**A:** Use Mockito with `@MockBean` or `@InjectMocks`:
```java
@MockBean
private MyService myService;
```

### Scenario: Handling NullPointerException with `@Autowired`
**Q:** How do you debug a `NullPointerException` due to missing autowiring?
**A:**
- Check if the bean is defined and annotated (`@Component`, `@Service`, etc.).
- Ensure the class is managed by Spring.
- Use `@Qualifier` if multiple beans exist.
- Use `@Autowired(required = false)` for optional beans.

---

## How `@Autowired` Annotations Work

1. **Bean Creation and Registration**
   - Spring creates an `ApplicationContext` and scans for stereotype annotations (`@Component`, `@Service`, etc.), registering them as beans.
2. **Dependency Resolution**
   - `@Autowired` signals that a field, constructor, or setter needs injection. Spring uses reflection to inject dependencies at runtime.
3. **Injection Points**
   - Spring supports constructor, setter, and field injection (constructor preferred).
4. **Type-based Dependency Matching**
   - Spring matches beans by type. If multiple exist, use `@Primary` or `@Qualifier`.
5. **Handling Multiple Beans**
   - Use `@Qualifier` or `@Primary` to resolve ambiguity.
6. **Optional Dependencies**
   - Use `@Autowired(required = false)` for optional beans.
7. **Circular Dependency Resolution**
   - Spring uses early reference resolution or `@Lazy` to break circular dependencies.
8. **Post-Processing**
   - `AutowiredAnnotationBeanPostProcessor` scans and injects dependencies during post-processing.
9. **Proxy-based Injection (AOP Compatibility)**
   - Spring injects proxies for beans with AOP concerns (e.g., transactions, security).
10. **Lifecycle Hooks**
    - After injection, use `@PostConstruct` for further initialization based on injected beans.
