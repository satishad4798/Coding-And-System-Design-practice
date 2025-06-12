


# LLD Cheat Sheet
### - [Solid principles](Interview_Notes_and_Cheat_sheets/LLD/SOLID_Principles_CheatSheet.md)
  


| Principle | Meaning                                 | Example                                  |
|-----------|------------------------------------------|------------------------------------------|
| **SRP**   | One reason to change                     | Separate `Invoice`, `Mailer`, `saveToDb`   |
| **OCP**   | Open to extend, closed to modify         | Saving to different DB types with interface; different payment types support using interface rather than if-else |
| **LSP**   | 1. Child should replace parent safely  <br> 2. If class B is subtype of class A, then B should be able to replace A without breaking behavior of the program <br> 3. Subclass should extend capability of parent, not narrow it down    | Ostrich shouldn't extend `FlyingBird`; Electric vehicle shouldn't extend `Car` with `turnOnEngine` method |
| **ISP**   | Don’t force unused methods               | Use `Workable`, `Eatable` separately. Ex: Waiter class should not implement `cookFood` (chef's work); Robot should not implement `eat` |
| **DIP**   | class should depend on interface rather than concrete classes    | Inject `Database` interface; MacBook with WiredKeyboard, WiredMouse, BluetoothMouse, BluetoothKeyboard (all via interfaces)            |

