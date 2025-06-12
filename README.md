


# LLD Cheat Sheet
### - [Solid principles](Interview_Notes_and_Cheat_sheets/LLD/SOLID_Principles_CheatSheet.md)
  


| Principle | Meaning                                 | Example                                  |
|-----------|------------------------------------------|------------------------------------------|
| **SRP**   | One reason to change                     | Separate `Invoice`, `Mailer`, `saveToDb`   |
| **OCP**   | Open to extend, closed to modify         | Saving to different DB types with interface; different payment types support using interface rather than if-else |
| **LSP**   | 1. Child should replace parent safely  <br> 2. If class B is subtype of class A, then B should be able to replace A without breaking behavior of the program <br> 3. Subclass should extend capability of parent, not narrow it down    | Ostrich shouldn't extend `FlyingBird`; Electric vehicle shouldn't extend `Car` with `turnOnEngine` method |
| **ISP**   | Don’t force unused methods               | Use `Workable`, `Eatable` separately. Ex: Waiter class should not implement `cookFood` (chef's work); Robot should not implement `eat` |
| **DIP**   | class should depend on interface rather than concrete classes    | Inject `Database` interface; Macbook with WiredKeyboard, WiredMouse, BluetoothMouse, BluetoothKeyboard (all via interfaces)            |


 
 
` Advantage of following SOLID principles:`
 - Avoid duplication of code
- **Modularity**: Each class or module has a single responsibility, making it easier to understand and modify.
- **Reusability**: Components can be reused across different parts of the application or in other projects.
- **Scalability**: New features can be added with minimal impact on existing code.
- **Loose Coupling**: Reduces dependencies between components, allowing for easier changes and testing.
- **Separation of Concerns**: Each class or module addresses a specific concern, leading to cleaner code.
- **Maintainability**: Code is easier to maintain and extend, reducing the risk of bugs.
- **Testability**: Facilitates unit testing by allowing mocking of dependencies.
- **Flexibility**: Code can adapt to changes without significant rewrites.

