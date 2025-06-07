### **Most asked Interview Questions**

1. **What is the purpose of the `@Autowired` annotation in Spring?**
    - `@Autowired` is used to tell Spring to automatically inject a dependency (a class or an interface) into another class. Instead of creating objects manually using `new`, Spring can handle the object creation and management for you. It helps to achieve loose coupling between components by letting Spring manage dependencies.
2. **How does `@Autowired` work internally in Spring Boot?**
    - When Spring Boot starts, it creates a container called the ApplicationContext. Inside this container, it looks for all the classes that are marked with Spring annotations (like `@Service`, `@Repository`, etc.). When `@Autowired` is used, Spring checks the container to see if there's an object (bean) of that type available and automatically injects it into the class where it's needed.
3. **Can you `@Autowired` a private field? Is it a good practice?**
    - Yes, you can autowire a private field in Spring using `@Autowired`, and Spring will still inject the dependency. However, it's not a good practice because it makes the code hard to test. Constructor-based injection is preferred because it makes it easier to write unit tests and ensures that dependencies are provided when the object is created.
4. **What are the different ways to autowire dependencies in Spring?**
    - In Spring, dependencies can be injected in three main ways:
        - **Constructor Injection**: The preferred way where Spring calls the constructor of the class and provides the required dependencies.
        - **Setter Injection**: Spring calls setter methods to provide the dependencies.
        - **Field Injection**: Spring directly injects the dependency into a class field, but this is generally not recommended for testing purposes.
5. **What happens if Spring finds multiple beans of the same type while autowiring?**
    - If there are multiple beans of the same type and Spring can't decide which one to inject, it will throw a `NoUniqueBeanDefinitionException`. To resolve this, you can use `@Qualifier` to specify which exact bean to use. For example:
        
        ```java
        @Autowired
        @Qualifier("specificBeanName")
        private MyBean myBean;
        ```
        
6. **What is the difference between `@Autowired` and `@Resource`?**
    - `@Autowired` works by **type**, meaning it tries to find a matching bean based on the class type.
    - `@Resource` works by **name**, meaning it tries to find a bean with the exact name of the field or property. In general, `@Autowired` is more commonly used in Spring, especially with Spring Boot.
7. **How do you handle circular dependencies with `@Autowired` in Spring?**
    - A circular dependency occurs when two or more beans depend on each other, creating a loop. To fix this, you can:
        - Use the `@Lazy` annotation on one of the beans to delay its initialization until it's really needed.
        - Redesign your classes to avoid circular dependencies, which is a better solution.
        
        Example using `@Lazy`:
        
        ```java
        @Autowired
        @Lazy
        private BeanA beanA;
        ```
        
8. **Can you autowire a collection of beans in Spring? How?**
    - Yes, you can inject a collection (like `List`, `Set`, or `Map`) of beans. Spring will automatically inject all the beans of that type into the collection. For example:
        
        ```java
        @Autowired
        private List<MyBean> myBeans;  // Injects all beans of type MyBean
        ```
        
    - This is useful when you have multiple beans of the same type and need to inject all of them at once.
9. **What is the difference between `@Autowired(required = false)` and `@Autowired`?**
    - By default, `@Autowired` expects a dependency to be present, and if it's missing, it will throw an error. However, with `@Autowired(required = false)`, Spring will skip the injection if the dependency isn't found and will not throw an error. This is useful when the dependency is optional.
10. **Is `@Autowired` mandatory in Spring Boot?**
    - No, `@Autowired` is not mandatory in constructors when using Spring Boot. If a class has only one constructor, Spring automatically wires the dependencies even without the `@Autowired` annotation. However, for clarity and to follow good practices, many developers still include the `@Autowired` annotation.

### Scenario based Questions

1. **Scenario: Multiple Beans of the Same Type**
    - **Question**: You have two beans of the same type, `MyServiceImpl1` and `MyServiceImpl2`, and both implement the `MyService` interface. How would you use `@Autowired` to inject a specific bean into your class?
    - **Expected Answer**: Use `@Qualifier` with `@Autowired` to specify the exact bean to inject:
        
        ```java
        @Autowired
        @Qualifier("myServiceImpl1")
        private MyService myService;
        
        ```
        
2. **Scenario: Optional Dependency Injection**
    - **Question**: Suppose your service class has an optional dependency that may or may not be present at runtime. How can you handle this using `@Autowired`?
    - **Expected Answer**: Use `@Autowired(required = false)` to mark the dependency as optional. If the bean is not available, Spring won't throw an exception:
        
        ```java
        @Autowired(required = false)
        private OptionalService optionalService;
        ```
        
3. **Scenario: Circular Dependency**
    - **Question**: Two beans, `BeanA` and `BeanB`, depend on each other (i.e., `BeanA` depends on `BeanB` and vice versa). How would you resolve this circular dependency using `@Autowired`?
    - **Expected Answer**: Use the `@Lazy` annotation on one of the beans to delay its initialization until it's actually needed:
        
        ```java
        @Autowired
        @Lazy
        private BeanB beanB;
        ```
        
4. **Scenario: Constructor vs Field Injection**
    - **Question**: In your class, you have several dependencies to inject. Should you use constructor injection, field injection, or setter injection? Why?
    - **Expected Answer**: Prefer constructor injection as it ensures that dependencies are provided when the object is created, making it easier to write unit tests. Field injection is less preferred because it can make testing difficult.
5. **Scenario: Injecting a List of Beans**
    - **Question**: You have multiple implementations of the `NotificationService` interface, and you need to inject all of them into a service class. How would you do this using `@Autowired`?
    - **Expected Answer**: Use `@Autowired` on a collection (e.g., `List<NotificationService>`) to inject all beans of that type:
        
        ```java
        @Autowired
        private List<NotificationService> notificationServices;
        ```
        
6. **Scenario: Injecting a Bean by Name**
    - **Question**: You have a class `UserService` and want to inject it by the name `customUserService`. How would you accomplish this using `@Autowired`?
    - **Expected Answer**: Use `@Qualifier` with the bean's name to inject it:
        
        ```java
        @Autowired
        @Qualifier("customUserService")
        private UserService userService;
        ```
        
7. **Scenario: Changing Dependencies at Runtime**
    - **Question**: You have two different implementations of the `PaymentService`, and depending on the environment (dev or prod), you want to inject one. How would you configure Spring to handle this scenario using `@Autowired`?
    - **Expected Answer**: Use profiles with `@Profile` annotation to configure beans for different environments. Spring will inject the correct bean based on the active profile.
        
        ```java
        @Profile("dev")
        @Service
        public class DevPaymentService implements PaymentService { ... }
        
        @Profile("prod")
        @Service
        public class ProdPaymentService implements PaymentService { ... }
        ```
        
8. **Scenario: Autowiring a Bean with No Public Constructor**
    - **Question**: You need to autowire a class that doesn't have a public constructor. How would you handle this situation with `@Autowired`?
    - **Expected Answer**: Ensure that the class has a public constructor. Spring cannot autowire a class without a public or accessible constructor, so you need to add one or use reflection-based configuration (though it's not recommended).
9. **Scenario: Testing with `@Autowired`**
    - **Question**: In a unit test, you want to inject a mock implementation of a service rather than the actual implementation. How would you manage this with `@Autowired`?
    - **Expected Answer**: Use a testing framework like Mockito to create mocks and inject them using `@MockBean` or `@InjectMocks` with Spring’s `@Autowired`. For example, in a Spring Boot test:
        
        ```java
        @MockBean
        private MyService myService;
        ```
        
10. **Scenario: Handling Null Pointer Exception with `@Autowired`**
- **Question**: During runtime, you're facing a `NullPointerException` because one of your dependencies isn't being autowired. How would you debug and fix this?
- **Expected Answer**:
    - Check if the bean is correctly defined and annotated with `@Component`, `@Service`, etc.
    - Ensure that the class where you are injecting the bean is being managed by Spring (e.g., annotated with `@Component` or is in a Spring-managed package).
    - Check if you need to use `@Qualifier` for multiple beans of the same type.
    - Use `@Autowired(required = false)` if the bean is optional.
    - 

### How Autowired Annotations Work

### 1. **Bean Creation and Registration**:

- When a Spring application starts, it creates an `ApplicationContext`, which is the container responsible for managing beans.
- The container scans the classpath for classes annotated with Spring stereotypes like `@Component`, `@Service`, `@Repository`, and `@Controller`. These classes are then instantiated and registered as beans in the `ApplicationContext`.

### 2. **Dependency Resolution**:

- The `@Autowired` annotation signals that the annotated field, constructor, or setter method requires dependency injection.
- Spring uses **reflection** to analyze the fields or constructors of the class and injects the required dependencies at runtime.

### 3. **Injection Points**:

- Spring determines injection points (fields, setters, constructors) marked with `@Autowired`. It supports three types of injection:
    - **Constructor Injection**: Spring looks for a constructor and injects dependencies. If a class has a single constructor, Spring will inject the dependencies without needing the `@Autowired` annotation explicitly.
    - **Setter Injection**: Spring calls the setter methods with the required dependencies.
    - **Field Injection**: Spring injects dependencies directly into the fields, but this approach is less preferred due to testing difficulties.

### 4. **Type-based Dependency Matching**:

- Spring resolves dependencies by **type**. When `@Autowired` is used, Spring searches the `ApplicationContext` for a bean of the required type.
- If multiple beans of the same type are found, Spring will throw a `NoUniqueBeanDefinitionException` unless one of the beans is qualified using `@Primary` or `@Qualifier`.

### 5. **Handling Multiple Beans**:

- If there are multiple candidates, you can use the `@Qualifier` annotation to specify which bean to inject:
    
    ```java
    @Autowired
    @Qualifier("beanName")
    private MyService myService;
    ```
    
- Alternatively, Spring will use the bean marked with `@Primary` if multiple candidates exist.

### 6. **Optional Dependencies**:

- By default, Spring expects all `@Autowired` dependencies to be present in the container. However, you can use `@Autowired(required = false)` to indicate that a dependency is optional:
    
    ```java
    @Autowired(required = false)
    private OptionalService optionalService;
    ```
    

### 7. **Circular Dependency Resolution**:

- If two beans depend on each other (circular dependency), Spring cannot instantiate them directly. To resolve this, Spring uses a two-step process:
    - **Early Reference Resolution**: Spring injects an uninitialized proxy of one bean into the other to break the circular dependency.
    - **Use of `@Lazy`**: You can annotate one of the dependencies with `@Lazy` to delay its instantiation until it's actually needed, breaking the circular reference.

### 8. **Post-Processing**:

- The actual injection of dependencies happens during the **post-processing phase**. Spring uses `BeanPostProcessor` implementations like `AutowiredAnnotationBeanPostProcessor` to process beans and inject dependencies marked with `@Autowired`.
- The `AutowiredAnnotationBeanPostProcessor` class scans for fields, constructors, or methods annotated with `@Autowired`, retrieves the required beans from the `ApplicationContext`, and injects them.

### 9. **Proxy-based Injection (AOP Compatibility)**:

- When working with AOP (e.g., with transactional beans), Spring often injects proxies rather than direct instances. These proxies wrap the target object to handle cross-cutting concerns like transactions or security, while `@Autowired` seamlessly injects the correct proxy to maintain expected behavior.

### 10. **Lifecycle Hooks**:

- After injection, Spring provides lifecycle hooks like `@PostConstruct` for further initialization tasks. If a bean has dependencies injected by `@Autowired`, this hook can be used to perform any initialization based on the injected beans.

[Annotation-5 @Bean](https://www.notion.so/Annotation-5-Bean-20b975e6ec60818e8c91e5d90a623236?pvs=21)