# 🧠 Low-Level Design (LLD) Roadmap for MAANG Interviews (2025)

*Last updated: June 8, 2025*

This roadmap is built for software engineers preparing for LLD interviews at top-tier product companies like MAANG (Meta, Amazon, Apple, Netflix, Google) and other tech giants (Uber, Flipkart, etc.). It covers all the essential topics, patterns, and subtopics with a focus on hands-on system design and coding skills.

---

## 📑 Table of Contents
- [Phase 1: OOPs & Design Principles](#phase-1-oops--design-principles-foundational)
- [Phase 2: Design Patterns (Classic & GoF)](#phase-2-design-patterns-classic--gof)
- [Phase 3: Hands-on Design Practice](#phase-3-hands-on-design-practice-object-modeling)
- [Phase 4: Advanced Concepts for MAANG](#phase-4-advanced-concepts-for-maang)
- [Tools & Practice Platforms](#tools--practice-platforms)
- [How to Practice LLD Interviews](#how-to-practice-lld-interviews)
- [Final Advice for MAANG LLD Success](#final-advice-for-maang-lld-success)

---

## ✅ Phase 1: OOPs & Design Principles (Foundational)
Learn how to write clean, extensible, and maintainable code.

### Object-Oriented Programming (OOP)
- Classes & Objects
- Inheritance, Polymorphism, Encapsulation, Abstraction
- Composition vs Inheritance
- Aggregation vs Association
- Constructor chaining

### SOLID Principles
- **Single Responsibility Principle (SRP)**
- **Open/Closed Principle (OCP)**
- **Liskov Substitution Principle (LSP)**
- **Interface Segregation Principle (ISP)**
- **Dependency Inversion Principle (DIP)**

### Other Core Principles
- **DRY** (Don’t Repeat Yourself)
- **YAGNI** (You Aren’t Gonna Need It)
- **KISS** (Keep It Simple, Stupid)
- **Law of Demeter**

---

## ✅ Phase 2: Design Patterns (Classic & GoF)
Understand and apply reusable design patterns in interviews and real-world projects.

### Creational Patterns
- Singleton (Thread-safe, Lazy/Eager Initialization)
- Factory Method
- Abstract Factory
- Builder
- Prototype

### Structural Patterns
- Adapter
- Decorator
- Proxy (Static & Dynamic)
- Facade
- Composite
- Flyweight

### Behavioral Patterns
- Strategy
- Observer
- Command
- State
- Template Method
- Chain of Responsibility
- Mediator
- Iterator
- Memento

---

## ✅ Phase 3: Hands-on Design Practice (Object Modeling)
Learn how to model real-world problems in code using OOPs + Design Patterns.

### Must-Practice Real-World LLD Problems
- Design a Parking Lot
- Design an Elevator System
- Design a Tic-Tac-Toe / Chess Game
- Design a Movie Ticket Booking System
- Design a Cab Booking System (Uber)
- Design a Hotel Management System
- Design an ATM
- Design a Library Management System
- Design a Splitwise App
- Design a File System (Folder Structure)
- Design Snake and Ladder / Ludo
- Design Notification System (Email/SMS/Push)

#### What to Practice in Each Design
- Identify core entities and relationships
- Apply correct design principles (SRP, OCP)
- Choose and apply right design patterns
- Handle concurrency & multithreading where required
- Discuss scalability and extensibility
- Interface vs abstract class usage

---

## ✅ Phase 4: Advanced Concepts for MAANG
Deep dive into architecture and scalable components to impress interviewers.

### Concurrency & Multithreading
- Thread-safe Singleton
- Producer-Consumer
- BlockingQueue, ThreadPool
- Locks, Semaphores
- Event-driven architecture (Observer)

### Best Practices
- UML Diagrams (Class, Sequence, Activity)
- Testability of components
- Handling large data sets in memory (pagination, caching)
- Extensible plugin-based architecture

#### Principles to Keep in Mind
- Favor composition over inheritance
- Program to interface, not implementation
- Interface segregation
- Fail fast and recover gracefully

---

## 🛠️ Tools & Practice Platforms
- **UML Tools:** Lucidchart, draw.io, Creately
- **Practice:** InterviewBit, DesignGurus, LowLevelDesign.io, GitHub repos
- **Mock Interviews:** Pramp, Exponent, peers

---

## 🧪 How to Practice LLD Interviews
1. Choose a design problem
2. Draw class diagram (entity modeling)
3. Write class skeletons with methods
4. Identify design patterns if applicable
5. Add validations, edge cases
6. Discuss improvements, scalability

---

## 🎯 Final Advice for MAANG LLD Success
- Always start with requirements & assumptions
- Focus on extensibility, not overengineering
- Communicate while designing; it’s a discussion
- Think in layers: controller → service → model → data
- Read open-source Spring projects or Java repositories to see real-world architecture

💡 *Consistency in practicing 1–2 LLD problems per week for 2–3 months will significantly improve your chances of cracking MAANG-level design rounds.*
