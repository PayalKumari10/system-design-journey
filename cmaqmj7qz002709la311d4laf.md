---
title: "📅Week-1 (Day 4) - Understanding UML Diagrams: Real Examples of Class and Sequence Diagrams"
seoTitle: "UML Diagrams: Class & Sequence Examples"
seoDescription: "Explore UML diagrams in software design using class and sequence diagrams for improved visualization and collaboration"
datePublished: Fri May 16 2025 09:56:42 GMT+0000 (Coordinated Universal Time)
cuid: cmaqmj7qz002709la311d4laf
slug: day-4-understanding-uml-diagrams-real-examples-of-class-and-sequence-diagrams
cover: https://cdn.hashnode.com/res/hashnode/image/upload/v1747373091648/a3ff1f74-05d7-4cf9-b95a-f586ff5974c3.png
ogImage: https://cdn.hashnode.com/res/hashnode/image/upload/v1747389329907/6142acc2-02b3-4647-9394-8874bf6b32b1.png
tags: blogging, technology, coding, hashnode, system-design, dsa, techblog, technical-writing-1, lld, coderarmy, lowleveldesign, 8weekslldchallenge, payalkumari11

---

> ***NOTE: - <mark>I </mark>*** <mark>started my 8-week system design journey with Coder Army. I will be journaling every day, recording what I learn, reflecting on it, and sharing it with my network to help newcomers to system design.</mark>

---

## 1.) What is UML and why it’s used in Software Design?

### 💠 What is UML (Unified Modeling Language)?

UML stands for **Unified Modeling Language** – a standard way to **visually represent the design of a system**.

(Hindi: UML ka matlab hai ek aisa standardized diagram jisse hum software system ke design ko visual form mein dikhate hain.)

* **Imagine UML as the blueprint of a house**, but for software systems.
    

📍 **Real-life analogy**:

Before building a house, an architect creates floor plans and designs. Similarly, in software, developers create **UML diagrams** before writing code – so that the team understands what they’re building.

### 💠 Why is UML important in Software Design?

* Helps **visualize** the structure and flow of a system
    
* Makes **team collaboration** easier
    
* Simplifies **problem-solving** and planning
    
* Used in **Low-Level Design (LLD)** to build scalable, clean systems
    

*📍Real example:*

Suppose you’re building a food delivery app like Zomato. UML will help you design how:

* Users place orders 🍔
    
* Restaurants receive them 🍽️
    
* Delivery partners pick them up 🛵
    

Payments are handled 💳

## 💠 UML Diagram Parts: Structural & Behavioral

UML diagrams are mainly divided into **two parts**:

### 1) Structural (Static) Diagrams

These diagrams represent the **structure** of a system — what things exist and how they’re related.

(Hindi: Structural ya static diagrams system ke parts aur unke relationships ko dikhate hain — jaise ek skeleton ya blueprint.)

<mark>They focus on </mark> **<mark>what the system is</mark>**<mark>.</mark>

### 2) Behavioral (Dynamic) Diagrams

These diagrams represent **interactions** and **how things behave over time**.

(Hindi: Behavioral ya dynamic diagrams system ke components ke beech hone wali activity ko dikhate hain.)

<mark>They focus on </mark> **<mark>what the system does</mark>**<mark>.</mark>

### 📍Why These Are Useful?

* Better understanding of system structure & flow
    
* Team collaboration made easy
    
* Easy debugging and system maintenance
    
* Foundation for real-world coding
    
    ### 💠7 Types of Structural (Static) Diagrams
    
* **Class Diagram** – Most commonly used
    
* Object Diagram
    
* Component Diagram
    
* Deployment Diagram
    
* Composite Structure Diagram
    
* Package Diagram
    
* Profile Diagram
    
    <mark>🎯 </mark> **<mark>Most Used</mark>**<mark>: </mark> **<mark>Class Diagram</mark>**
    

📍 Real-life Example:  
In an e-commerce app like Amazon:

* You’ll have a **User class**, **Product class**, **Order class**
    
* These classes connect to show how users place orders, view products, and make payments.
    
    ### 💠7 Types of Behavioral (Dynamic) Diagrams
    
* **Sequence Diagram** – Most commonly used
    
* Use Case Diagram
    
* Activity Diagram
    
* Communication Diagram
    
* State Machine Diagram
    
* Timing Diagram
    

Interaction Overview Diagram

<mark>🎯 </mark> **<mark>Most Used</mark>**<mark>: </mark> **<mark>Sequence Diagram</mark>**

📍 Real-life Example:  
In a **food delivery app**:

* A user places an order
    
* The restaurant gets the order
    
* The delivery partner picks it up
    
* Payment confirmation is sent
    

The **Sequence Diagram** shows how this flow happens **step by step over time**.

---

## 2.) How to draw and understand **Class Diagrams** (with real-world examples)

**💠What is a Class Structure?**

Class diagrams show the main entities (classes) of the system, their attributes, and the connections between them.

(Hindi : **Class Diagram** system ke main entities (classes), unke attributes aur unke beech ke connections ko show karta hai.)

📍 **Real-world example:**  
**Class:** `Car`  
**Attributes:** `brand`, `model`, `engine`  
**Methods:** `startEngine()`, `brake()`

```java
class Car {
  String brand;
  String model;
  void startEngine() { ... }
}
```

![](https://cdn.hashnode.com/res/hashnode/image/upload/v1747382281233/cf9471a0-7e55-48ba-b231-148148f30495.png align="center")

**Visibility Notation:**

* `+` for public (visible to all classes)
    
* `-` for private (visible only within the class)
    
* `#` for protected (visible to subclasses)
    
* `~` for package or default visibility (visible to classes in the same package)
    

![](https://cdn.hashnode.com/res/hashnode/image/upload/v1747382496163/f8e4aee0-6cfd-475b-8048-10e70eafdf8f.png align="center")

![](https://cdn.hashnode.com/res/hashnode/image/upload/v1747383841686/599711cb-a43e-44df-8c6a-8ccec44b626c.png align="center")

**💠What is an Association / Connection?**

An **association** represents a **bi-directional relationship** between two classes. It means instances of one class are connected or linked to instances of another class.

(Hindi : Association ek bi-directional relationship hoti hai do classes ke beech. Iska matlab hai ki ek class ke instances doosri class ke instances se connected ya linked hote hain.)

Association types :

1. Class Association : a) Inheritance
    
2. Object Association : a) Simple Association , b) Aggregation, c) Composition
    

* **Depicted as:** A solid line connecting two class boxes in UML.
    
* **Optional arrows** can indicate direction (one-way or two-way).
    
    📍 **Real-world example:**
    
    **Class 1:** `Library`
    
    **Class 2:** `Book`
    
    The `Library` class **has a collection of** `Book` objects.
    
    * `Library` is the **source class** (has a reference to books).
        
* `Book` is the **target class** (belongs to a specific library).
    
    ```java
    public class Library {
        private List<Book> books;
    }
    
    public class Book {
        private String title;
    }
    ```
    
    💠Inheritance
    
* **Represents:** "is-a" relationship
    
* **Arrow:** Solid line with **empty arrowhead** pointing to the parent (`--▷`)
    

**Example:** `Dog` is an `Animal`

```java
class A {
    void method1() {
        System.out.println("A");
    }
}

class B extends A {
    void method2() {
        System.out.println("B");
    }
}

public class Main {
    public static void main(String[] args) {
        B b = new B();
        b.method1(); // from A
        b.method2(); // from B
    }
}
```

**💠Simple Association**

* **Represents**: "uses-a" or "has-a" relationship (loose connection)
    
* **Arrow**: Solid line (**→**) or solid line with optional direction (no triangle for simple association)
    
* **Example**:  
    `Arjun → House`  
    *(Arjun lives in a house)*
    

#### **💠Aggregation**

* **Represents:** "has-a" but loosely bound
    
* **Arrow:** **Empty diamond arrow** (`<>---`)
    
* **Example:**  
    `Sofa <>--- Room`, `Chair <>--- Room`  
    (Room can exist even if sofa/chair is removed)
    

**💠Composition**

* **Represents:** Strong containment
    
* **Arrow:** **Filled diamond arrow** (`<~>---`)
    

**Example:**  
`Wheel <~> Chair`, `Arms <~> Chair`  
(Without chair, its parts don't exist)

```java
class A {
    void method1() {
        System.out.println("From A");
    }
}

class B {
    A a;

    B() {
        a = new A(); // Strong containment (composition)
    }

    void method2() {
        System.out.println("From B");
    }
}

public class Main {
    public static void main(String[] args) {
        B b = new B();
        b.method2();       // From B
        b.a.method1();     // From A (through composition)
    }
}
```

## 3) How to create and analyze **Sequence Diagrams** to visualize system interactions

A sequence diagram is a behavioral UML diagram that shows the interactions between different objects in a time sequence.

(Hindi : Sequence Diagram ek **Behavioral UML Diagram** hai jo different objects ke beech mein hone wali interactions ko **time ke order** mein dikhata hai.)

📍Use When:

When you want to model how different components interact in a specific use case.

💠Main Components of a Sequence Diagram:

* **Objects / Classes**  
    Example: A, B, C, D (entities involved)
    
* **Lifeline**  
    When the object is active in the system
    
* **Activation Bar**  
    The time period till which the object is active (send/receive messages)
    
* **Messages**
    
    * **Synchronous**: Sender waits for response
        
    * **Asynchronous**: Sender does **not wait** for response
        
    
    💠Special messages:
    
* Create message: When an object is created
    
* Destroy message: When an object is destroyed
    
* Lost message: Message was sent but not received
    
* Found message: Message was received but not known who sent it
    

```plaintext
User -> App: Request Ride  
App -> Server: Check Availability  
Server -> Driver: Send Request  
Driver -> App: Accept Ride  
App -> User: Ride Confirmed
```

## 4\. ) How UML helps in real-life Low-Level Design and interview discussions

**UML (Unified Modeling Language)** is like a visual blueprint for software design. It helps you explain and plan systems clearly, especially during coding and interviews. Here’s why it matters:

1. **Clear Visuals**  
    UML diagrams (like class and sequence diagrams) break down complex systems into simple parts, making it easy for everyone to understand.
    
2. **Shows Relationships**  
    It clearly represents key relationships like inheritance (is-a), aggregation (has-a), and composition (strong has-a), which helps build solid designs.
    
3. **Makes Coding Easier**  
    With UML, you know what to build and how parts connect, reducing guesswork and speeding up development.
    
4. **Impresses Interviewers**  
    Using UML in interviews shows you think systematically, understand OOP principles, and can communicate complex ideas clearly.
    
5. **Bridges Teams**  
    UML helps developers and non-technical stakeholders (like product managers) stay on the same page.
    

```plaintext
User            ATM           Account        Transaction       CashDispenser
 |               |                |                |                  |
 |--insertCard()->|               |                |                  |
 |--enterPIN()--->|               |                |                  |
 |               |--validatePin->|                |                  |
 |               |<--valid/invalid--|             |                  |
 |--selectWithdraw(amount)------->|               |                  |
 |               |--createTxn()------------------->|                |
 |               |                  |--initiate()-->|                |
 |               |<----------------|<--complete()---|                |
 |               |--debit()------->|                |                |
 |               |<--success--------|               |                |
 |               |--dispenseCash()------------------------------->|  
 |               |<------------------cash dispensed---------------|
 |<--Take cash--------------------|                |                |
```

### 📍 [Diagram](https://github.com/adityatandon15/LLD/tree/c1eebc52cf09983bbba7658e0b9c49b4acf3630f/Lecture%204)

### **Day 4 Completed ✅ System Design**

> ***NOTE : - A big thanks to my mentors*** [***<mark>Rohit Negi</mark>***](https://www.linkedin.com/in/rohit-negi9/) ***<mark>Sir </mark> and*** [***<mark>Aditya</mark>***](https://www.linkedin.com/in/adityatandon2/) [***<mark>Sir</mark> fo***](https://www.linkedin.com/in/adityatandon2/)***r launching this amazing <mark>8-week course</mark> absolutely <mark>free</mark> on YouTube via <mark>CoderArmy9 </mark> :-*** [https://www.youtube.com/@CoderArmy9***🙌***](https://www.youtube.com/@CoderArmy9)

👉 **Share this blog with your connections!** Let’s keep learning, growing, and supporting one another on this journey. 🚀

✍️ [***Payal Kumari***](https://www.linkedin.com/in/payalkumari10/) 👩‍💻

[![](https://cdn.hashnode.com/res/hashnode/image/upload/v1747389027408/819a1024-a90b-4efa-b3f8-8dbab441d83e.jpeg align="center")](https://www.linkedin.com/in/payalkumari10/)

**Jai Hind 🇮🇳 | #CoderArmy #LearningInPublic #SystemDesign #TechForAll #MentorshipMatters** [**#8weeksLLdChallenge**](https://www.youtube.com/hashtag/8weekslldchallenge) [**#LowLevelDesign**](https://www.youtube.com/hashtag/8weekslldchallenge) [#Code **#LLD**](https://www.youtube.com/hashtag/lowleveldesign) [**#OOP**](https://www.youtube.com/hashtag/lld) **#UMLDiagrams** **👩‍💻**

%[https://youtu.be/nPJyyO9pb5s?si=ru0ZXSgmji2JnOuw]