# SOLID Principles - Interview Cheat Sheet with Examples

| Principle | Meaning                                 | Example                                  |
|-----------|------------------------------------------|------------------------------------------|
| **SRP**   | One reason to change                     | Separate `Invoice`, `Mailer`, `saveToDb`   |
| **OCP**   | Open to extend, closed to modify         | Saving to different DB types with interface; different payment types support using interface rather than if-else |
| **LSP**   | 1. Child should replace parent safely  <br> 2. If class B is subtype of class A, then B should be able to replace A without breaking behavior of the program <br> 3. Subclass should extend capability of parent, not narrow it down    | Ostrich shouldn't extend `FlyingBird`; Electric vehicle shouldn't extend `Car` with `turnOnEngine` method |
| **ISP**   | Don’t force unused methods               | Use `Workable`, `Eatable` separately. Ex: Waiter class should not implement `cookFood` (chef's work); Robot should not implement `eat` |
| **DIP**   | class should depend on interface rather than concrete classes    | Inject `Database` interface; Macbook with WiredKeyboard, WiredMouse, BluetoothMouse, BluetoothKeyboard (all via interfaces)            |


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
Suppose we’re saving data to different types of databases.

🛑 **Bad Design**:
```java
public class DataSaver {
    public void save(String data, String dbType) {
        if (dbType.equals("MYSQL")) {
            // Save to MySQL
        } else if (dbType.equals("MONGODB")) {
            // Save to MongoDB
        }
        // Add more if-else for new DB types
    }
}
```

✅ **Better Design (OCP Applied)**:
```java
interface DbSave {
    void save(String data);
}

class MySQLDatabase implements DbSave {
    public void save(String data) { /* Save to MySQL */ }
}

class MongoDBDatabase implements DbSave {
    public void save(String data) { /* Save to MongoDB */ }
}

class DataSaver {
    private Database db;
    public DataSaver(Database db) { this.db = db; }
    public void save(String data) { db.save(data); }
}
```

🎯 **Interview Tip**: “Now I can add new database types without modifying existing logic. This makes the code safer and extensible.”

- Payment example: Instead of adding if-else for each new payment type, define a Payment interface and extend with UPIPayment, CardPayment, etc.

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

```java
class Vehicle {
    public void startEngine() { ... }
}

class Car extends Vehicle {
    public void startEngine() { /* Start car engine */ }
}

class ElectricScooter extends Vehicle {
    public void startEngine() {
        throw new UnsupportedOperationException("No engine to start!");
    }
}
```

🛑 **Violation**: ElectricScooter is a Vehicle, but doesn't have an engine.

✅ **Fix: Separate hierarchy.**
```java
class Vehicle { }

interface EnginePowered {
    void startEngine();
}

class Car extends Vehicle implements EnginePowered {
    public void startEngine() { /* Start car engine */ }
}

class ElectricScooter extends Vehicle {
    // No engine, doesn't implement EnginePowered
}
```

🎯 **Interview Tip**: “A subclass should never break the behavior expected by the parent class.”

---

## ✅ 4. I – Interface Segregation Principle (ISP)
> 📌 “No class should be forced to implement methods it doesn’t use.”

### 👔 Interview-style Example:



🛑 **Bad: Forcing all employees to implement all methods**
```java
interface RestaurantEmployee {
    void washDishes();
    void cookFood(); // Only for chefs
    void serveFood(); // Only for waiters
}

class Waiter implements RestaurantEmployee {
    public void washDishes() { /* Not a waiter's responsibility */ }
    public void cookFood() { /* Not a waiter's responsibility */ }
    public void serveFood() { System.out.println("Serving food to customers"); }
}

class Chef implements RestaurantEmployee {
    public void washDishes() { /* Not a chef's responsibility */ }
    public void cookFood() { System.out.println("Cooking food in the kitchen"); }
    public void serveFood() { /* Not a chef's responsibility */ }
}
```

✅ **Better: Segregate interfaces by responsibility**
```java
interface DishWasher {
    void washDishes();
}
interface Cook {
    void cookFood();
}
interface Server {
    void serveFood();
}

class ISPWaiter implements Server {
    public void serveFood() {
        System.out.println("Serving food to customers");
    }
}

class ISPChef implements Cook {
    public void cookFood() {
        System.out.println("Cooking food in the kitchen");
    }
}
```

🎯 **Interview Tip**: “Clients should only see what they need.”

---

## ✅ 5. D – Dependency Inversion Principle (DIP)
> 📌 “High-level modules should not depend on low-level modules. Both should depend on abstractions.”

### 👔 Interview-style Example:


🛑 **Bad (Keyboard/Mouse Example)**:
```java
class WiredKeyboard {
    void type() { ... }
}
class WiredMouse {
    void click() { ... }
}
class Macbook {
    private WiredKeyboard keyboard = new WiredKeyboard();
    private WiredMouse mouse = new WiredMouse();
    // Tightly coupled to specific implementations
}
```

✅ **Better (using abstraction)**:
```java
interface Keyboard {
    void type();
}
interface Mouse {
    void click();
}

class WiredKeyboard implements Keyboard {
    public void type() { System.out.println("Typing with wired keyboard"); }
}
class BluetoothKeyboard implements Keyboard {
    public void type() { System.out.println("Typing with Bluetooth keyboard"); }
}
class WiredMouse implements Mouse {
    public void click() { System.out.println("Clicking with wired mouse"); }
}
class BluetoothMouse implements Mouse {
    public void click() { System.out.println("Clicking with Bluetooth mouse"); }
}

class Macbook {
    private Keyboard keyboard;
    private Mouse mouse;
    public Macbook(Keyboard keyboard, Mouse mouse) {
        this.keyboard = keyboard;
        this.mouse = mouse;
    }
    // Now Macbook can work with any keyboard or mouse implementation
}
```

🎯 **Interview Tip**: “This allows easy swapping of databases or input devices, better testing using mocks, and loose coupling.”

---

## 🔁 Summary Table

| Principle | Meaning                                 | Example                                  |
|-----------|------------------------------------------|------------------------------------------|
| **SRP**   | One reason to change                     | Separate `Invoice`, `Mailer`, `saveToDb`   |
| **OCP**   | Open to extend, closed to modify         | Saving to different DB types with interface; different payment types support using interface rather than if-else |
| **LSP**   | Child should replace parent safely       | Ostrich shouldn't extend `FlyingBird`; Electric vehicle shouldn't extend `Car` with `turnOnEngine` method |
| **ISP**   | Don’t force unused methods               | Use `Workable`, `Eatable` separately. Ex: Waiter class should not implement `cookFood` (chef's work); Robot should not implement `eat` |
| **DIP**   | Depend on abstractions, not concretes    | Inject `Database` interface; Macbook with WiredKeyboard, WiredMouse, BluetoothMouse, BluetoothKeyboard (all via interfaces) |

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

