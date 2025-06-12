


# LLD Cheat Sheet
### - [Solid principles](Interview_Notes_and_Cheat_sheets/LLD/SOLID_Principles_CheatSheet.md)
  


| Principle | Meaning                                 | Example                                  |
|-----------|------------------------------------------|------------------------------------------|
| **Single Responsibility Principle**   | One reason to change                     | Separate `Invoice`, `Mailer`, `Logger`   |
| **Open/Closed Principle**   | Open to extend, closed to modify         | Bonus strategies with `Bonus` interface  |
| **Liskov Substitution Principle**   | Child should replace parent safely       | `Ostrich` shouldn't extend `FlyingBird`  |
| **Interface Segregation Principle**   | Don’t force unused methods               | Use `Workable`, `Eatable` separately     |
| **Dependency Inversion Principle**   | Depend on abstractions, not concretes    | Inject `Database` interface              |


 
 
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

