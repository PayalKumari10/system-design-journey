---
title: "📅Week-2 (Day 1) - Learn SOLID Design Principles: Detailed Guide and Practical Code Samples part -2."
seoTitle: "SOLID Design Principles Guide & Code Samples"
seoDescription: "Learn SOLID design principles with detailed explanations and code samples. Enhance your system design skills in this 8-week journey with Coder Army"
datePublished: Tue May 20 2025 10:24:02 GMT+0000 (Coordinated Universal Time)
cuid: cmawd9rw9000008jv28v7ewr5
slug: week-2-day-1-learn-solid-design-principles-detailed-guide-and-practical-code-samples-part-2
cover: https://cdn.hashnode.com/res/hashnode/image/upload/v1747736364273/70a3c168-944e-4de2-bae2-ea92d794b836.png
ogImage: https://cdn.hashnode.com/res/hashnode/image/upload/v1747736546137/9b3cc2c3-7787-4fed-8ff0-11abc2ad27cb.png
tags: code, software-development, technology, coding, dsa, techblog, low-level-design, lld, systemdesign, coderarmy, 8weekslldchallenge, payalkumari11

---

> ***NOTE: - <mark>I started my 8-week system design journey with Coder Army. I will be journaling every day, recording what I learn, reflecting on it, and sharing it with my network to help newcomers to system design.</mark>***

## 💠 Quick Recap of SOLID Principles (Part-1)

Before we jump into the remaining two principles, let’s quickly refresh what we learned in Part-1.  
(Hindi: Aage badhne se pehle, chaliye pichle 3 principles ko ek baar dohra lete hain 👇)

### 1\. Single Responsibility Principle (SRP)

* **One class = One job**  
    A class should have only one reason to change.
    
* **Real-Life Example:**  
    Think of a **Washing Machine**. It only **washes clothes**. If we start using it to dry hair too, maintenance becomes difficult!
    

(Hindi: Har class ka ek hi kaam hona chahiye. Jaise washing machine sirf kapde dhone ka kaam karti hai.)

### 2\. Open/Closed Principle (OCP)

* **Open for extension, closed for modification**
    
* **Real-Life Example:**  
    Think of a **Power Strip**. You can plug in new devices anytime (**extension**) without changing the strip’s core system (**modification**).
    

(Hindi: Naye features add kar sakte ho bina purani code ko tod-phod kiye.)

### 3\. Liskov Substitution Principle (LSP)

* **Child class should behave like parent class**
    
* **Real-Life Example:**  
    If you have a **Box** class and a **GiftBox** class (which inherits from Box), then **GiftBox** should still behave like a normal box (you can store things in it).  
    If it explodes when you open it—❌ not good!
    

(Hindi: Agar koi class dusri class se banaayi gayi hai to uska behavior uske parent jaisa hona chahiye.)

We have already covered SRP, OCP, and LSP conceptually. What follows is a detailed breakdown of LSP guidelines, then full explanations of Interface Segregation Principle (ISP) and Dependency Inversion Principle (DIP) with illustrative examples.

---

## 💠 Liskov Substitution Principle (LSP)

*"Objects of a superclass should be replaceable with objects of a subclass without affecting the correctness of the program."*

(Hindi: "Agar aap ek parent class ki jagah uski child class ka object use karein, toh program sahi se kaam karta rahe — bina kisi error ke.")

📍Simple Explanation – LSP in Real Life

Let’s say you have a **transport app**.  
You create a class called `Vehicle` with a method called `startEngine()`.

Now, you make subclasses:

* `Car`
    
* `Bike`
    
* `ElectricScooter`
    

**Each subclass should be able to replace** `Vehicle` — without causing any issues!

**But what if ElectricScooter doesn't have an engine?**  
If its `startEngine()` method throws an error ❌, your app crashes!

➡️ That’s a **violation of LSP**.

📍Real-Life Example (LSP)

👩‍🏫 Imagine a teacher substitutes for another.

If the substitute teacher **doesn't know the subject** or **behaves differently**, students get confused and learning breaks down.

(Hindi: Agar ek maths teacher ki jagah koi science teacher aa jaaye aur woh maths padhana na jaane, toh students ka loss hoga.)

➡️ So, the **replacement should behave the same** and follow the same rules. That's LSP!

## 💠Why LSP "Breaks" Often?

* Inheritance ensures that subclasses have the same methods, but not necessarily the same behavior or contractual guarantees.
    
* Without clear rules, a subclass may override a method incorrectly (e.g., throwing unexpected exceptions, changing return values or method signatures), causing client code to fail.
    

📍Real-Life Example:

🍴 Parent: “Every utensil in this drawer can be used to eat.”

* Spoon ✅
    
* Fork ✅
    
* Knife ❌ – It's there, but it doesn’t behave like the others.
    

If you grab a knife assuming it behaves like a spoon, you’ll cut your lip.

(Hindi: Drawer mein sabhi cheezein khane ke liye hain, aapne spoon samajh ke knife utha liya – LSP fail ho gaya!)

### 📍**Inheritance ≠ Same Behavior**

✅ Inheritance guarantees that child classes will have the **same methods**.

❌ But it **doesn’t guarantee** that those methods will work the **same way**.

*Just having the same method name isn't enough. The behavior and rules must be consistent.*

### 📍 What Happens When LSP Breaks?

* App crashes or throws runtime exceptions
    
* Unexpected results or bugs appear
    
* Testing becomes difficult – because behavior changes silently
    
* Code becomes fragile – tightly coupled and hard to extend
    

## 1) Signature Rules

*These rules ensure that a subclass's method matches the contract of the parent class*

### a) Method Argument Rule

➡️ Child class method should accept:

* The **same** parameter type as the parent
    
* Or a **wider** type (i.e., a parent type)
    

✅ **Correct Example:**

```java
Parent: void print(String text)  
Child: void print(Object text) // Allowed, since Object is a supertype of String
```

❌ **Incorrect Example:**  
Child method taking `Integer` when parent method takes `String`.

(Hindi: Agar parent class method `String` leta hai, toh child class bhi ya toh `String` le ya usse bada type jaise `Object`. `Integer` nahi chalega!)

### b) Return Type Rule

➡️ Child method return type should be:

* The **same** as the parent
    
* Or a **narrower** type (subtype)
    

✅ **Correct Example:**

```java
Parent: Animal getAnimal()  
Child: Dog getAnimal() // Allowed – Dog is a type of Animal
```

❌ **Incorrect Example:**

```java
Child: Object getAnimal() // Not allowed – Object is broader than Animal
```

### c) Exception Rule

➡️ Child methods can:

* Throw **fewer** or **more specific** exceptions
    
* ❌ But **not broader** or unexpected ones
    

✅ **Example:**  
If parent method throws `IOException`, child can throw `FileNotFoundException` (a subtype)

❌ **Wrong Example:**  
Child throws `OutOfMemoryError` — completely unrelated!

(Hindi: Agar parent class method sirf `RuntimeError` throw karta hai, toh child usi ke under ke exceptions throw kare – koi alag exception nahi.)

## 2) Property Rules

*These rules make sure subclasses preserve the logic and behavior of the parent class.*

### a) Class Invariant

➡️ *Class invariant* = Condition that must **always** be true for a class

**Example:**

```java
BankAccount.balance >= 0 // Always positive
```

❌ **Example:**  
`CheatAccount` allows negative balances = ❌ LSP broken

(Hindi: Agar parent class keh rahi hai ki balance kabhi negative nahi hoga, toh child class us rule ko todh nahi sakti.)

### b) History Constraint

➡️ Subclass must not:

* Break the **expected lifecycle** or flow of the parent
    
* Disable features that the client expects
    

**Example:**  
If parent class `Account` allows withdrawals anytime,  
but subclass `FixedDepositAccount` throws an exception when withdrawal is attempted — ❌ LSP broken

(Hindi: Agar parent class mein withdrawal ka feature hai, toh child class usse hata nahi sakti ya disable nahi kar sakti.)

📍Real-Life Example:

Parent: “ATM se kabhi bhi paisa nikaal sakte ho.”

Child: “FixedDeposit ATM – paisa nikalna mana hai!”

⚠️ Client expects money anytime, but suddenly gets an error. That’s a **clear LSP violation**.

## 3) Method Rules

### **a) Precondition** (Before the Method Runs)

A *precondition* is what must be true **before** the method executes.

📍**Rule:**  
✔️ Subclass can **weaken** the precondition (allow more inputs)  
❌ But it must **not strengthen** it (ask for stricter inputs)

#### ✅Correct Example:

Parent method allows `0 ≤ x ≤ 5`  
Child method allows `0 ≤ x ≤ 10` (Weaker – more inputs accepted)

#### ❌ Incorrect Example:

Child only allows `0 ≤ x ≤ 3` (Stronger – restricts input further)

📍**Real-life Example:**  
Parent ATM accepts PINs between 1000 and 9999.  
Child ATM should not suddenly accept only PINs from 1000–5000. That would break client expectations!

(Hindi: Agar parent method kuch range ka input allow karta hai, toh child us range ko aur zyada inclusive bana sakta hai, kam nahi.)

### b) **Postcondition** (After the Method Runs)

A *postcondition* is what must be true **after** the method completes.

📍 **Rule:**  
✔️ Subclass can **strengthen** the postcondition (give more guarantees)  
❌ But must **not weaken** it (give less guarantees)

#### ✅ Correct Example:

Parent method `brake()` ensures: speed decreases  
Child `HybridCar.brake()` ensures: speed decreases **\+ battery charges** ✅ (more benefit)

#### ❌ Incorrect Example:

Child `brake()` doesn't reduce speed ❌ (breaks expectation)

🔁 **Real-life analogy:**  
Imagine a car’s brake system. If the parent car **always slows down**, the child car should also **at least slow down**, maybe even charge the battery too — but it must **not fail to slow down**!

(Hindi: Agar parent class method kuch kaam karke guarantee deta hai, toh child uss guarantee ko aur accha bana sakta hai, kam nahi.)

## 💠Key Takeaways for LSP

* **Always ask yourself:**  
    Does this child class truly behave like the parent? Or is it just compiling without error?
    
* Use the **3-part checklist** when designing or overriding classes:
    
    1️⃣ **Signature Rules**  
    2️⃣ **Property Rules**  
    3️⃣ **Method Rules**
    
    (Hindi: Ye teen rules ek checklist ki tarah kaam karte hain jab aap classes design karte ho.)
    
* **Watch out for LSP Violations**:
    
    * Unexpected exceptions thrown
        
    * Incorrect return types
        
    * Broken guarantees (like postconditions or invariants)
        
        📍 Real-Life Example:
        
    
    🍔 Parent: “A Veg Burger always has a patty and lettuce.”
    
    Child: “My Veg Burger only has buns.” ❌  
    🛑 That’s a **violation**! It's not really a "substitute" anymore
    

## 💠Interface Segregation Principle (ISP)

"Clients should not be forced to depend on interfaces they do not use."

(Hindi: Clients ko un interfaces par depend nahi hona chahiye jo wo use hi nahi karte.)

### 📍Key Idea:

Break large, bloated interfaces into smaller, more focused ones — tailored to specific client needs.

> "Ek bada interface banana sabko suit nahi karta. Har client ke liye alag, chhota interface banana zyada behtar hai."

## 💠The Problem with “Fat” Interfaces 🍔

Let’s say we create a general interface called `Shape` that includes **all kinds of operations** — both 2D and 3D methods:

```java
interface Shape {
    double area();
    double volume();  // Not all shapes need this
}
```

📍Real-Life Example:

Imagine a remote control with **100 buttons**, but your TV only uses 5.  
Why force everyone to use the same remote? 🤷  
**ISP says:** Give each user only the buttons they actually need!

(Hindi: Har user ko uske kaam ke hi buttons dijiye, nahi toh use confuse hoga.)

```java
// 2D Shape Interface
interface TwoDShape {
    double area();
}

// Square implements 2DShape
class Square implements TwoDShape {
    private double side;

    public Square(double side) {
        this.side = side;
    }

    @Override
    public double area() {
        return side * side;
    }
}

// Rectangle implements 2DShape
class Rectangle implements TwoDShape {
    private double length;
    private double width;

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    @Override
    public double area() {
        return length * width;
    }
}

// 3D Shape Interface
interface ThreeDShape {
    double area();
    double volume();
}

// Cube implements 3DShape
class Cube implements ThreeDShape {
    private double side;

    public Cube(double side) {
        this.side = side;
    }

    @Override
    public double area() {
        return 6 * side * side;
    }

    @Override
    public double volume() {
        return side * side * side;
    }
}
```

📍Benefits:

* Each implementer only deals with methods it actually uses.
    
* Code is cleaner, adheres to SRP, and avoids unnecessary stubs or exceptions.
    

## 💠Dependency Inversion Principle (DIP)

* **High-level modules** should not depend on **low-level modules**. Both should depend on **abstractions**.
    
* **Abstractions** should not depend on **details**. **Details** should depend on **abstractions**.
    

(Hindi: High-level code ko low-level details pe depend nahi hona chahiye. Dono ko abstraction (interface) pe depend hona chahiye.)

📍The Problem with Direct Coupling

* A high-level class (e.g., UserService) that directly calls concrete low-level classes (SqlDatabase, MongoDatabase) becomes tightly coupled.
    
* Changing the low-level implementation (e.g., swapping MongoDB for Cassandra) forces modifications in the high-level class-violating OCP.
    

### 📍Define An Abstraction

```java
// Define an Abstraction (Interface)
interface Persistence {
    void save(User u);
}

// User Class
class User {
    String name;
    int id;

    public User(String name, int id) {
        this.name = name;
        this.id = id;
    }
}
```

### 📍Low-Level Classes Depend on the Abstraction

```java
// SQL Database Implementation
class SqlDatabase implements Persistence {
    @Override
    public void save(User u) {
        System.out.println("Saving user to SQL Database: " + u.name);
    }
}

// MongoDB Implementation
class MongoDatabase implements Persistence {
    @Override
    public void save(User u) {
        System.out.println("Saving user to MongoDB: " + u.name);
    }
}
```

### 📍High-Level Module Depends Only on Abstraction

```java
class UserService {
    private final Persistence db; // injected dependency

    public UserService(Persistence db) {
        this.db = db;
    }

    public void storeUser(User u) {
        db.save(u);
    }
}
```

📍Dependency Injection

* At runtime, instantiate UserService with either new SqlDatabase( .. ) or new MongoDatabase ( .. ) (or a future CassandraDatabase), without changing UserService itself.
    

📍Real-World Analogy

* A company CEO (high-level) doesn't instruct individual developers (low-level) directly. Instead, a manager (abstraction) relays requirements.
    
* The CEO depends only on the manager's interface; developers depend on the manager for directives. Swapping out developers doesn't affect the CEO's workflow.
    
* **UserService = CEO**
    
* **Persistence (Interface) = Manager**
    
* **SqlDatabase/MongoDatabase = Developers**
    

## 💠Final Thoughts & Trade-Offs

* SOLID principles are guidelines, not hard laws. In practice, business requirements and performance constraints may necessitate trade-offs.
    
* Adhering to these principles generally leads to more maintainable, scalable, and extensible code-but balance is key.
    
* Whenever you find yourself violating one principle, check whether it's in service of a higher-priority need (e.g., performance) and document your reasoning.
    

By following these LSP guidelines and applying ISP and DIP judiciously, you'll write cleaner, more robust object-oriented code that stands the test of evolving requirements.

### **Week - 2 (Day 1) Completed ✅ System Design**

> ***NOTE : - A big thanks to my mentors*** [***<mark>Rohit Negi</mark> <mark>Sir</mark>***](https://www.linkedin.com/in/rohit-negi9/) ***and*** ***<mark>Aditya</mark>*** [](https://www.linkedin.com/in/adityatandon2/)[***<mark>Sir</mark> fo***](https://www.linkedin.com/in/adityatandon2/)***r launching this amazing <mark>8-week course</mark> absolutely <mark>free</mark> on YouTube via <mark>CoderArmy9 </mark> :-*** [***youtube.com/@CoderArmy9***](https://www.youtube.com/@CoderArmy9) [***. 🙌***](https://www.youtube.com/@CoderArmy9)

👉 **Share this blog with your connections!** Let’s keep learning, growing, and supporting one another on this journey. 🚀

✍️ [***Payal Kumari***](https://www.linkedin.com/in/payalkumari10/) 👩‍💻 [Github](https://github.com/PayalKumari10)

[![](https://cdn.hashnode.com/res/hashnode/image/upload/v1747735871722/57a3968e-d0cc-4be1-9a84-f9a248565e29.jpeg align="center")](https://www.linkedin.com/in/payalkumari10/)

**Jai Hind 🇮🇳 | #CoderArmy #LearningInPublic #SystemDesign #TechForAll #MentorshipMatters** [**#8weeksLLdChallenge**](https://www.youtube.com/hashtag/8weekslldchallenge) [**#LowLevelDesign**](https://www.youtube.com/hashtag/8weekslldchallenge) [#Code **#LLD**](https://www.youtube.com/hashtag/lowleveldesign) [**#OOP**](https://www.youtube.com/hashtag/lld) **👩‍💻**

%[https://youtu.be/hU9koy6A2I0?si=D1CfWaHSrrAa0GFw]