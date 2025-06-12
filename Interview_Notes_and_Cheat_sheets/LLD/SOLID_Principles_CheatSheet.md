    
# SOLID Principles - Interview Cheat Sheet with Examples

## ✅ 1. S – Single Responsibility Principle (SRP)
> 📌 “One class should have one reason to change.”

### 👔 Interview-style Example:
You have a class `Invoice` that:
- Calculates totals  : invoice added later causing change in class 
- Saves invoice to database : introduction of new database will cause change in class
- Sends the invoice by email : change in email provider will cause change in class

```java
public class Invoice {
    public double calculateTotal() { ... }
    public void saveToDb() { ... }
    public void sendEmail() { ... }
}
```

🛑 **Violation**: Multiple responsibilities.

✅ **Fix: Separate classes**
```java
public class Invoice {
    public double calculateTotal() { ... }
}

public class InvoiceRepository {
    public void saveToDb(Invoice invoice) { ... }
}

public class InvoiceMailer {
    public void sendEmail(Invoice invoice) { ... }
}
```

🎯 **Interview Tip**: Explain how separating concerns leads to better testability and maintainability.

---

## ✅ 2. O – Open/Closed Principle (OCP)
> 📌 “Open for extension, closed for modification.”

### 👔 Interview-style Example:
Suppose we’re calculating **employee bonuses**.

🛑 **Bad Design**:
```java
public class BonusCalculator {
    public double calculateBonus(Employee emp) {
        if (emp.getType().equals("PERMANENT"))
            return emp.getSalary() * 0.1;
        else if (emp.getType().equals("CONTRACT"))
            return emp.getSalary() * 0.05;
    }
}
```

✅ **Better Design (OCP Applied)**:
```java
interface Bonus {
    double calculate(double salary);
}

class PermanentBonus implements Bonus {
    public double calculate(double salary) { return salary * 0.1; }
}

class ContractBonus implements Bonus {
    public double calculate(double salary) { return salary * 0.05; }
}
```

🎯 **Interview Tip**: “Now I can add new bonus types without touching existing logic. This makes the code safer and extensible.”


- example 2

🔧 Bad: Adding if-else for each new payment type inside PaymentProcessor.
✅ Good:

Define interface Payment

Extend with UPIPayment, CardPayment, etc.

🧠 Interview Tip: "Add new features without modifying tested code."



---

## ✅ 3. L – Liskov Substitution Principle (LSP)
> 📌 “Subtypes must be substitutable for their base types.”

### 👔 Interview-style Example:
```java
class Bird {
    public void fly() { ... }
}

class Ostrich extends Bird {
    public void fly() {
        throw new UnsupportedOperationException("Can't fly!");
    }
}
```

🛑 **Violation**: Ostrich is a Bird, but can’t fly.

✅ **Fix: Reorganize hierarchy.**
```java
class Bird { }

interface FlyingBird {
    void fly();
}

class Sparrow extends Bird implements FlyingBird {
    public void fly() { ... }
}

class Ostrich extends Bird {
    // Doesn't implement fly - now valid
}
```

🎯 **Interview Tip**: “A subclass should never break the behavior expected by the parent class.”

---

## ✅ 4. I – Interface Segregation Principle (ISP)
> 📌 “No class should be forced to implement methods it doesn’t use.”

### 👔 Interview-style Example:

🛑 **Bad interface**:
```java
interface Worker {
    void work();
    void eat();
}
```

🛑 **Class Violation**:
```java
class Robot implements Worker {
    public void work() { ... }
    public void eat() {
        throw new UnsupportedOperationException();
    }
}
```

✅ **Better: Separate interfaces**
```java
interface Workable {
    void work();
}
interface Eatable {
    void eat();
}

class Human implements Workable, Eatable {
    public void work() { ... }
    public void eat() { ... }
}

class Robot implements Workable {
    public void work() { ... }
}
```

🎯 **Interview Tip**: “Clients should only see what they need.”

---

## ✅ 5. D – Dependency Inversion Principle (DIP)
> 📌 “High-level modules should not depend on low-level modules. Both should depend on abstractions.”

### 👔 Interview-style Example:

🛑 **Bad**:
```java
class MySQLDatabase {
    void save(String data) { ... }
}

class ReportService {
    MySQLDatabase db = new MySQLDatabase();
    void generateReport(String data) {
        db.save(data);  // Tightly coupled
    }
}
```

✅ **Better (using abstraction)**:
```java
interface Database {
    void save(String data);
}

class MySQLDatabase implements Database {
    public void save(String data) { ... }
}

class ReportService {
    private Database db;

    public ReportService(Database db) {
        this.db = db;
    }

    public void generateReport(String data) {
        db.save(data);  // Uses abstraction
    }
}
```

🎯 **Interview Tip**: “This allows easy swapping of databases, better testing using mocks, and loose coupling.”

---

## 🔁 Summary Table

| Principle | Meaning                                 | Example                                  |
|-----------|------------------------------------------|------------------------------------------|
| **SRP**   | One reason to change                     | Separate `Invoice`, `Mailer`, `Logger`   |
| **OCP**   | Open to extend, closed to modify         | Bonus strategies with `Bonus` interface  |
| **LSP**   | Child should replace parent safely       | `Ostrich` shouldn't extend `FlyingBird`  |
| **ISP**   | Don’t force unused methods               | Use `Workable`, `Eatable` separately     |
| **DIP**   | Depend on abstractions, not concretes    | Inject `Database` interface              |

---

## Advantages of Following SOLID Principles
- **Modularity**: Each class or module has a single responsibility, making it easier to understand and modify.
- **Reusability**: Components can be reused across different parts of the application or in other projects.
- **Scalability**: New features can be added with minimal impact on existing code.
- **Loose Coupling**: Reduces dependencies between components, allowing for easier changes and testing.
- **Separation of Concerns**: Each class or module addresses a specific concern, leading to cleaner code.
- **Maintainability**: Code is easier to maintain and extend, reducing the risk of bugs.
- **Testability**: Facilitates unit testing by allowing mocking of dependencies.
- **Flexibility**: Code can adapt to changes without significant rewrites.  

---

