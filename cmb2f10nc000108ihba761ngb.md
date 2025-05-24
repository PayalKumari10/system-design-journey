---
title: "📅Week-2 (Day 5) - Understanding the Singleton Design Pattern: Thread-Safe, Lazy, and Eager Initialization Explained"
seoTitle: "Singleton Patterns: Thread-Safe, Lazy, Eager"
seoDescription: "Learn Singleton Design Pattern: thread-safe, lazy, eager initialization, real-world use cases, pitfalls, and best practices"
datePublished: Sat May 24 2025 15:59:49 GMT+0000 (Coordinated Universal Time)
cuid: cmb2f10nc000108ihba761ngb
slug: week-2-day-5-understanding-the-singleton-design-pattern-thread-safe-lazy-and-eager-initialization-explained
cover: https://cdn.hashnode.com/res/hashnode/image/upload/v1748086310971/81ce6c43-a721-444f-87c1-31b175c4890e.png
ogImage: https://cdn.hashnode.com/res/hashnode/image/upload/v1748102353862/1b6bb46a-eb3a-42db-8b62-a7982ebe2d84.png
tags: cpp, java, technology, coding, system-architecture, system-design, dsa, techblog, coding-challenge, low-level-design, lld, coderarmy, 8weekslldchallenge, payalkumari11

---

> ***NOTE: - <mark>I started my 8-week system design journey with Coder Army. I will be journaling every day, recording what I learn, reflecting on it, and sharing it with my network to help newcomers to system design.</mark>***

## 💠 What is Singleton Design Pattern?

The **Singleton Pattern** ensures that a class **can have only one object (instance)** in the system — and gives a **global point of access** to it.

(Hindi : *"Ek aisi class jo sirf ek baar object banaye, aur agar dubara object banane ki koshish karo, toh wahi pehla object return kare!" )*

### 📍 Real-Life Analogy:

Imagine you have a **Government ID Generator**. You can have only **one Aadhaar Card** linked to you. If you try to make another, the system just refers back to your original Aadhaar. Same logic!

## 💠 How Singleton Works Behind The Scenes?

A computer memory has two parts:

* **Heap** – For storing objects (non-primitive data like custom classes)
    
* **Stack** – For storing primitive types (int, char) and function calls
    
    When we create an object like this:
    
    ```java
    A a = new A();
    ```
    
    It's stored in the **heap**. But if you keep writing:
    
    ```java
    A a = new A();
    A a2 = new A();
    A a3 = new A();
    ```
    
    Multiple objects are created. To **prevent this**, Singleton restricts object creation using a **private constructor** and **returns the same instance** using a method called `getInstance()`.
    

## 💠Step-by-Step: How to Implement Singleton in C++ & Java

### ✅ General Rules:

* **Private Constructor** – To prevent outside object creation.
    
* **Static Instance** – Stores the single instance.
    

**Static Method (getInstance)** – Returns that one instance.

```cpp
class Singleton {
private:
    static Singleton* instance;
    Singleton() {}  // Private constructor

public:
    static Singleton* getInstance() {
        if (!instance)
            instance = new Singleton();
        return instance;
    }
};

// Define and initialize the static member
Singleton* Singleton::instance = nullptr;
```

```java
public class Singleton {
    private static Singleton instance;
    private Singleton() {}  // Private constructor

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
```

* **NoSingleton**
    
    ```java
    public class NoSingleton {
        public NoSingleton() {
            System.out.println("Singleton Constructor called. New Object created.");
        }
    
        public static void main(String[] args) {
            NoSingleton s1 = new NoSingleton();
            NoSingleton s2 = new NoSingleton();
    
            System.out.println(s1 == s2);
        }
    }
    ```
    
* **SimpleSingleton**
    

```java
public class SimpleSingleton {
    private static SimpleSingleton instance = null;

    private SimpleSingleton() {
        System.out.println("Singleton Constructor called");
    }

    public static SimpleSingleton getInstance() {
        if (instance == null) {
            instance = new SimpleSingleton();
        }
        return instance;
    }

    public static void main(String[] args) {
        SimpleSingleton s1 = SimpleSingleton.getInstance();
        SimpleSingleton s2 = SimpleSingleton.getInstance();

        System.out.println(s1 == s2);
    }
}
```

* **ThreadSafeDoubleLockingSingleton**
    

```java
public class ThreadSafeDoubleLockingSingleton {
    private static ThreadSafeDoubleLockingSingleton instance = null;

    private ThreadSafeDoubleLockingSingleton() {
        System.out.println("Singleton Constructor Called!");
    }

    // Double check locking..
    public static ThreadSafeDoubleLockingSingleton getInstance() {
        if (instance == null) { // First check (no locking)
            synchronized (ThreadSafeDoubleLockingSingleton.class) { // Lock only if needed
                if (instance == null) { // Second check (after acquiring lock)
                    instance = new ThreadSafeDoubleLockingSingleton();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        ThreadSafeDoubleLockingSingleton s1 = ThreadSafeDoubleLockingSingleton.getInstance();
        ThreadSafeDoubleLockingSingleton s2 = ThreadSafeDoubleLockingSingleton.getInstance();

        System.out.println(s1 == s2);
    }
}
```

* **ThreadSafeEagerSingleton**
    

```java
public class ThreadSafeEagerSingleton {
    private static ThreadSafeEagerSingleton instance = new ThreadSafeEagerSingleton();

    private ThreadSafeEagerSingleton() {
        System.out.println("Singleton Constructor Called!");
    }

    public static ThreadSafeEagerSingleton getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        ThreadSafeEagerSingleton s1 = ThreadSafeEagerSingleton.getInstance();
        ThreadSafeEagerSingleton s2 = ThreadSafeEagerSingleton.getInstance();

        System.out.println(s1 == s2);
    }
}
```

* **ThreadSafeLockingSingleton**
    
    ```java
    public class ThreadSafeLockingSingleton {
        private static ThreadSafeLockingSingleton instance = null;
    
        private ThreadSafeLockingSingleton() {
            System.out.println("Singleton Constructor Called!");
        }
    
        public static ThreadSafeLockingSingleton getInstance() {
            synchronized (ThreadSafeLockingSingleton.class) { // Lock for thread safety
                if (instance == null) {
                    instance = new ThreadSafeLockingSingleton();
                }
                return instance;
            }
        }
    
        public static void main(String[] args) {
            ThreadSafeLockingSingleton s1 = ThreadSafeLockingSingleton.getInstance();
            ThreadSafeLockingSingleton s2 = ThreadSafeLockingSingleton.getInstance();
    
            System.out.println(s1 == s2);
        }
    }
    ```
    

## 💠 Lazy vs Eager Initialization

### 1) Lazy Initialization

* Object is **created only when needed**.
    
* 🟢 **Advantage**: Saves memory
    
* 🔴 **Disadvantage**: Not thread-safe by default
    

### 2) Eager Initialization

* Object is **created at the time of class loading**.
    
* 🟢 **Advantage**: Simple and thread-safe
    
* 🔴 **Disadvantage**: If object is heavy, it wastes memory.
    

#### 📍 Tip:

> *“Eager tabhi use karo jab object lightweight ho. Agar object expensive hai, lazy better hai!”*

## 💠Thread-Safe Singleton (Right Way to Do It)

In a **multi-threaded environment**, multiple threads can create multiple instances in lazy mode.

To fix this, we use:

### 1\. **Thread Safe with Synchronized (Java)**

```java
public synchronized static Singleton getInstance() {
    if (instance == null)
        instance = new Singleton();
    return instance;
}
```

### 2\. **Double Checked Locking (Efficient)**

```java
public static Singleton getInstance() {
    if (instance == null) {
        synchronized (Singleton.class) {
            if (instance == null)
                instance = new Singleton();
        }
    }
    return instance;
}
```

## 💠Real-World Use Cases of Singleton

### 1\. Logging Systems

> Only one instance logs all the errors or info.

### 2\. Database Connections

> One database object avoids unnecessary multiple DB connections.

### 3\. Configuration Manager

> All modules use the same config settings.

### 4\. Cache or Memory Management

> One central point to manage caching

## 💠Where NOT to Use Singleton ❌

* In **gaming apps**, where each player needs their own object.
    
* When you need **multiple independent instances**.
    
* In **unit tests**, where singletons might introduce **global state issues**.
    

> 📍*“Jahaan pe har object ka alag behavior ho, wahan Singleton avoid karo.”*

## 💠Common Pitfalls & Bottlenecks (Quick Summary)

* **Global State:**  
    Singleton acts as a global object – changes in one place affect everywhere. Hard to manage & test.
    
* **Tight Coupling:**  
    Code gets tightly connected with the Singleton – hard to modify or replace components later.
    
* **Concurrency Issues:**  
    Without proper thread-safety, Singleton can cause race conditions in multithreaded apps.
    
* **Testing Challenges:**  
    Singletons make unit testing difficult due to shared global state across tests.
    
* **Hidden Dependencies:**  
    Dependencies on Singleton are often unclear – making the code harder to maintain.
    
* **Limited Extensibility:**  
    Difficult to subclass or extend a Singleton class.
    
* **Performance Bottlenecks:**  
    Lazy loading can delay the first access; heavy objects waste memory in eager initialization.
    

**Scalability Problems:**  
Managing Singleton across multiple threads/servers becomes tricky in large-scale systems.

## 💠 How to Avoid These Problems?

* Use **Dependency Injection** instead of global access
    
* Prefer **IoC Containers** (e.g., Spring)
    
* Always implement **thread-safe Singleton**
    
* Consider **alternatives** like Factory or Service classes
    

> *"Ek ghar ka ek hi raja hota hai. Waise hi system ka ek hi singleton instance hona chahiye!"* 👑

---

### **Week - 2 (Day 5) Completed ✅ System Design**

> ***NOTE : - A big thanks to my mentors*** [***<mark>Rohit Negi</mark> <mark>Sir</mark>***](https://www.linkedin.com/in/rohit-negi9/) ***and*** [***<mark>Aditya</mark> <mark>Sir</mark> fo***](https://www.linkedin.com/in/adityatandon2/)***r launching this amazing <mark>8-week course</mark> absolutely <mark>free</mark> on YouTube via <mark>CoderArmy9 </mark> :-*** [***youtube.com/@CoderArmy9***](http://youtube.com/@CoderArmy9) [***. 🙌***](https://www.youtube.com/@CoderArmy9)

👉 **Share this blog with your connections!** Let’s keep learning, growing, and supporting one another on this journey. 🚀

✍️ [***Payal Kumari***](https://www.linkedin.com/in/payalkumari10/) 👩‍💻 [**Github**](https://github.com/PayalKumari10)

![](https://cdn.hashnode.com/res/hashnode/image/upload/v1748076871803/86ddb6dc-a4f6-48d8-b81e-09494a1c902f.jpeg align="center")

%[https://youtu.be/CD3meit-WDc?si=DvK-8BR4cG84LcTK]