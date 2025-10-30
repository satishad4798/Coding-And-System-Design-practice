> TO MAIN PAGE  - [ Back to main page ](README.md)

#  LLD Interview Framework

## ✅ Step 1. Clarify Requirements
- Ask what features are **must-have vs nice-to-have**.
- Clarify scope: OOP design? class diagram? interactions?

Example (Parking Lot):
- FRs: park/unpark vehicle, calculate charges.
- NFRs: scalable for large lots, extensible for future vehicle types.

---

## ✅ Step 2. Identify Core Entities (Classes / Objects)
Break system into real-world entities.

Example (Parking Lot):
- `Vehicle`, `ParkingSpot`, `ParkingFloor`, `Ticket`, `Payment`.

---

## ✅ Step 3. Relationships Between Entities
- Use OOP: Inheritance, Composition, Interfaces.

Example:
- `Car`, `Bike`, `Truck` extend `Vehicle`.
- `ParkingSpot` assigned to `Vehicle`.

---

## ✅ Step 4. Define Responsibilities (SRP from SOLID)
Each class should have one clear job.

Example:
- `ParkingLot` → manages floors & spots.
- `ParkingSpot` → tracks availability.
- `PaymentProcessor` → handles payments.

---

## ✅ Step 5. Apply Design Principles (SOLID)
- **S**: Single Responsibility
- **O**: Open/Closed
- **L**: Liskov Substitution
- **I**: Interface Segregation
- **D**: Dependency Inversion

---

## ✅ Step 6. Key Design Patterns (LLD Favorites)
- Factory Pattern → object creation (e.g., Payment gateway factory)
- Strategy Pattern → interchangeable algorithms (e.g., pricing strategy)
- Observer Pattern → event-driven (e.g., notifications)
- Singleton → global manager (e.g., ParkingLot instance, logger)
- Decorator → add features dynamically (e.g., coupon in billing)

---

## ✅ Step 7. Class Diagram (High-level Sketch)
Show classes & relationships (composition, inheritance).

---

## ✅ Step 8. Flow of Control (How it Works)
Walk through a use case step by step.

Example (Parking Lot):
- User enters → system assigns spot → ticket generated → payment on exit.

---

## ✅ Step 9. Edge Cases & Extensibility
- Lot is full
- Payment fails
- Lost ticket
- Future: EV charging spots, reserved parking

---

# 📌 Example: Parking Lot LLD


--------------------------------------------------------------------

> TO MAIN PAGE  - [ Back to main page ](README.md)
