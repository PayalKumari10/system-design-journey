---
title: "📅 Day 3 - Understanding Inheritance and Polymorphism in Object-Oriented Programming"
seoTitle: "OOP: Inheritance & Polymorphism"
seoDescription: "Explore inheritance and polymorphism in OOP using real-world examples and Java to enhance reusability and flexibility"
datePublished: Thu May 15 2025 11:19:53 GMT+0000 (Coordinated Universal Time)
cuid: cmapa2ce6000209laey6f0bgg
slug: day-3-understanding-inheritance-and-polymorphism-in-object-oriented-programming
cover: https://cdn.hashnode.com/res/hashnode/image/upload/v1747296937825/cb259ebf-ad67-4df9-9873-6d7374f8c279.png
ogImage: https://cdn.hashnode.com/res/hashnode/image/upload/v1747307821697/4015721d-890f-407f-9675-2daf7ae3592d.png
tags: oop, technology, coding, oops, system-design, dsa, techblog, technical-writing-1, lld, coderarmy, 8weekslldchallenge, payalkumari11

---

> ***NOTE: - <mark>I </mark>*** <mark>started my 8-week system design journey with Coder Army. I will be journaling every day, recording what I learn, reflecting on it, and sharing it with my network to help newcomers to system design.</mark>

# 1) Inheritance

## 1.1 What is Inheritance?

Inheritance is a concept where one class (Child) inherits properties and behaviors (methods) from another class (Parent). This helps in code reusability and better structure.

(Hindi : Inheritance ek aisa concept hai jisme ek class (Child) doosri class (Parent) ke properties aur methods inherit karti hai. Isse code reuse hota hai aur system ka structure zyada organized banta hai.)

## 1.2 Real-World Mapping of Inheritance: Car Hierarchy

## ✅ Parent Class: Car (Generic)

A "car" is a general concept – every car, whether manual or electric, has certain basic features and functions in common.

#### 📍 Common Attributes:

* `Brand` – (e.g., Tata, Honda, Tesla)
    
* `Model` – (e.g., Nexon, Civic, Model S)
    
* `IsEngineOn` – (Boolean value indicating the engine on/off status)
    
* `CurrentSpeed` – (Current speed in km/h)
    

#### 📍 Common Behaviors (Methods):

* `startEngine()` – Car engine start
    
* `stopEngine()` – Engine off
    
* `accelerate()` – Speed increases
    
* `brake()` – Car slow or stop
    
    ```java
    class Car {
        String brand;
        String model;
        boolean isEngineOn;
        int currentSpeed;
    
        void startEngine() { /* logic */ }
        void stopEngine() { /* logic */ }
        void accelerate() { /* logic */ }
        void brake() { /* logic */ }
    }
    ```
    

> Note : (Hindi: Ye sab functionality har car mein hoti hai – chahe wo kis type ki bhi ho.)

## ✅Child Class:

### a) ManualCar

In a manual car the driver changes gears manually. So we have an additional feature or behaviour which is not there in a normal car.

(Hindi : Manual car mein driver gear change karta hai manually. Isliye isme ek extra attribute aur behavior hota hai jo generic Car mein nahi hota.)

📍 Special properties: currentGear - which gear the driver is in (e.g., 1, 2, 3, N)

📍 Special behavior: shiftGear() - how to shift gears

```java
class ManualCar extends Car {
    int currentGear;

    void shiftGear(int gear) {
        this.currentGear = gear;
    }
}
```

> Note : (Real World: Old Honda City manual version, Maruti 800, etc.)

### b) Electric Car

Electric cars do not run on conventional fuel - they run on batteries. So we have some different properties or behavior.

(Hindi : Electric cars traditional fuel pe nahi chalti – battery se chalti hain. Isliye isme kuch alag attributes aur behavior hote hain.)

📍 Specific attributes: BatteryPercentage – the current charge level of the battery (for example, 80%)

📍 Specific behavior: chargeBattery() – how to charge the battery

```java
class ElectricCar extends Car {
    int batteryPercentage;

    void chargeBattery() {
        batteryPercentage = 100; // fully charged
    }
}
```

> Note : (Real World: Tesla, Tata Nexon EV, Hyundai Kona EV)

## 1.3 Syntax of Inheritance (Java Version)

Declaring inheritance in Java is very straightforward.

```java
class Car {
    // common properties and methods
}

class ManualCar extends Car {
    // additional ManualCar-specific code
}

class ElectricCar extends Car {
    // additional ElectricCar-specific code
}
```

> Note : (Hindi: Yaha `extends` keyword ka use hota hai, jo batata hai ki ek class dusri class ke properties aur behaviors inherit kar rahi hai.)

> **Note:** Java mein sirf **single inheritance** supported hoti hai — ek class sirf ek hi class se inherit kar sakti hai. Agar aapko multiple inheritances chahiye toh uske liye **interfaces** ka use hota hai (which we’ll cover later).

### 📍Inheritance Type (Java): **Public by Default**

When you declare inheritance in Java using extends, by default the inheritance is public - means child class can access public and protected members of the parent.

## 1.4 Access Specifiers in Inheritance

### a) **Public**

```java
public class Car {
    public String brand;

    public void startEngine() {
        System.out.println("Engine started");
    }
}
```

Agar parent class ke members `public` hain:

* Toh child class mein directly accessible honge.
    

Outside the class (other packages) bhi accessible honge.

```java
ManualCar m = new ManualCar();
m.startEngine(); // Accessible
```

> Note : (Hindi: Public ka matlab sab jagah se access milta hai, including child class and outside classes.)

### b) **Protected**

```java
public class Car {
    protected int currentSpeed;

    protected void brake() {
        System.out.println("Applying brakes");
    }
}
```

* Accessible inside the same package.
    

Accessible in child classes, even in different packages.

```java
ElectricCar e = new ElectricCar();
e.brake(); // Accessible if called within subclass
```

> Note : (Hindi: Protected members subclass ke liye visible hote hain, lekin class ke bahar directly access nahi kiya ja sakta.)

### c) **Private**

```java
public class Car {
    private boolean isEngineOn;

    private void engineCheck() {
        System.out.println("Checking engine...");
    }
}
```

* Completely **hidden** from child classes and outside code.
    

Not inherited.

```java
ManualCar m = new ManualCar();
// m.isEngineOn ❌ Not allowed
```

> Note : (Hindi: Private members sirf usi class tak limited rehte hain jaha unhe define kiya gaya hai.)

![](https://cdn.hashnode.com/res/hashnode/image/upload/v1747302304093/ee543a17-4f7c-40e9-b534-318b0a33de53.png align="center")

## 1.5 Real Life Analogy: Access in a Family

Let’s take a fun analogy.

* **Public** = Family ki common room – har koi jaa sakta hai
    
* **Protected** = Bedroom – sirf family members (child classes) allowed
    
* **Private** = Locker – sirf owner (parent class) access kar sakta hai
    

Jab aap ek system design karte ho, toh har data ko openly share nahi kar sakte. Isliye access control important hota hai for security, maintainability, and clean architecture.

---

# 2) Polymorphism

## 2.1 What is Polymorphism?

Polymorphism comes from two Greek words:

> **"Poly"** = many  
> **"Morph"** = forms

In software terms, **Polymorphism means**:

> *"One interface, multiple implementations"*
> 
> Note : (Hindi: Ek hi naam/function hone ke bawajood, alag-alag objects ya situations mein woh alag tareeke se behave kare.)

## 2.2 Real-Life Scenarios of Polymorphism

### 🔹 Scenario 1: Different Creatures, Same Behavior – Different Actions

```java
class Animal {
    void run() {
        System.out.println("Animal is running...");
    }
}

class Duck extends Animal {
    void run() {
        System.out.println("Duck waddles while running");
    }
}

class Tiger extends Animal {
    void run() {
        System.out.println("Tiger sprints fiercely!");
    }
}

class Human extends Animal {
    void run() {
        System.out.println("Human runs upright");
    }
}
```

```java
Animal a1 = new Duck();
Animal a2 = new Tiger();
Animal a3 = new Human();

a1.run(); // Duck's run()
a2.run(); // Tiger's run()
a3.run(); // Human's run()
```

> Note : (Hindi: Sabhi classes mein `run()` method hai, lekin har object us method ko apne tareeke se implement karta hai. Isi flexibility ko hum polymorphism kehte hain.)

### 🔹 Scenario 2: Same Person, Different Behavior

Imagine you’re a runner.

1. Jab aap tired ho, toh aap slowly run karte ho.
    

Jab aapke peeche koi Tiger aa jaaye, toh aap lightning speed mein bhaagte ho 😄

```java
class Human {
    void run(String situation) {
        if(situation.equals("tired")) {
            System.out.println("Running slowly...");
        } else if(situation.equals("beingChased")) {
            System.out.println("Running like Flash! 🏃‍♂️");
        }
    }
}
```

```java
Human h = new Human();
h.run("tired");         // Output: Running slowly...
h.run("beingChased");   // Output: Running like Flash!
```

> ### Note : (Hindi: Yaha same object — ek hi insaan — lekin situation ke hisaab se uska behavior change ho raha hai.)

## 2.3 Types of Polymorphism in Java

1. **Compile-time (Static) Polymorphism** – Achieved by **method overloading**
    

```java
void run() {}
void run(int speed) {}
```

2. **Runtime (Dynamic) Polymorphism** – Achieved by **method overriding**
    

```java
class Animal { void sound() {} }
class Dog extends Animal { void sound() { System.out.println("Bark"); } }
```

> Note : (Hindi: Compile time polymorphism mein method name same hota hai but parameters alag hote hain. Runtime polymorphism mein subclass method parent ke method ko override karta hai.)

# 3) Static Polymorphism (Method Overloading)

Static Polymorphism refers to the ability of a class to have **multiple methods with the same name**, but **different parameter lists**.

**(Hindi: Static Polymorphism ka matlab hai ki ek hi class mein ek hi naam ke kai methods ho sakte hain, bas unka parameter alag hona chahiye.)**

It is also called **Method Overloading**, and it is **resolved at compile-time**.

> Note : This means the compiler decides which version of the method to call based on the number and type of arguments passed. Yeh sab kuch code compile hone ke samay decide ho jaata hai, na ki runtime par.

## 3.1) Real-Life Analogy – Think Like a Human!

Imagine you're calling a friend named *Rahul*:

* If you just say **"Rahul!"**, he'll simply respond to your voice.
    
* If you say **"Rahul, come fast!"**, he'll understand that you're in a hurry.
    
* If you say **"Rahul, bring water with you!"**, he gets a different context.
    

Yahan par **name wahi hai (Rahul)**, but depending on **how** and **what** you say, his **reaction (method behavior)** changes.

```java
class ManualCar {
    void accelerate() {
        System.out.println("Accelerating normally...");
    }

    void accelerate(int speed) {
        System.out.println("Accelerating at " + speed + " km/h");
    }
}
```

Here, `accelerate()` is overloaded.

* One version runs **without any input**
    
* The other version takes **speed as an argument**
    

> Note : (Hindi: Dono ka naam same hai, par ek mein input hai aur ek mein nahi. Compiler samajh jaata hai ki kaunsa method call karna hai.)

## 3.2) Rules of Method Overloading

![](https://cdn.hashnode.com/res/hashnode/image/upload/v1747304323192/55609bbf-d194-4c91-8019-245d21d6bd26.png align="center")

📌 *Important*: You **can't** do overloading like this:

```java
void greet() {}
int greet() {} // ❌ Not allowed! Only return type changed
```

# 4) Dynamic Polymorphism (Method Overriding)

## 4.1) **What is Dynamic Polymorphism?**

Dynamic Polymorphism means redefining the **same method signature** in **child classes**, where each subclass provides its **own version** of the method.

**(Hindi: Dynamic Polymorphism ka matlab hai ki ek method ka signature same hota hai, par uska implementation har child class mein alag hota hai.)**

It is also known as **Method Overriding**, and it is **resolved at runtime** (not compile-time).

## 4.2) Real-Life Example – Same Action, Different Behavior

Imagine you say to different people:  
**"Drive the vehicle."**

* A **Manual Car driver** will use gear and clutch.
    
* An **Electric Car driver** will just press the pedal.
    
* A **Bike rider** will twist the handle.
    

Command toh same hai: "Drive."  
But har kisi ka **implementation** alag hai.

> (Hindi: Sab log ek hi kaam – “drive” – kar rahe hain, par apne tareeke se. Yeh hi dynamic polymorphism hai.)

```java
// Parent class
abstract class Car {
    abstract void accelerate(); // Abstract method
}

// Child class 1
class ManualCar extends Car {
    @Override
    void accelerate() {
        System.out.println("Accelerating using gear and clutch...");
    }
}

// Child class 2
class ElectricCar extends Car {
    @Override
    void accelerate() {
        System.out.println("Accelerating silently with battery power...");
    }
}

// Demo class to test
public class Main {
    public static void main(String[] args) {
        Car manual = new ManualCar();      // Upcasting
        Car electric = new ElectricCar();  // Upcasting

        manual.accelerate();   // Output: Accelerating using gear and clutch...
        electric.accelerate(); // Output: Accelerating silently with battery power...
    }
}
```

Here, `accelerate()` is a **virtual function**.  
This tells the compiler:

> “Don't decide which method to call yet. Let the program decide during runtime based on the object type.”

(Hindi: `virtual` keyword compiler ko bolta hai ki method call run-time pe decide hoga.)

## 4.3) Key Differences Between Overloading and Overriding

![](https://cdn.hashnode.com/res/hashnode/image/upload/v1747305169754/d1b23e1e-6795-49f3-9088-a8c877ecff9f.png align="center")

# 5.Combined Use of OOP Pillars

* ✅ **Abstraction**
    
* ✅ **Encapsulation**
    
* ✅ **Inheritance**
    
* ✅ **Polymorphism**
    
    OOP is not just about learning terms — it's about using these principles together.  
    Aap jab real-world projects banaoge – chaahe woh Android app ho ya web backend – yeh 4 pillars hamesha kaam aayenge.
    
    ![](https://cdn.hashnode.com/res/hashnode/image/upload/v1747305527080/1b177856-b6df-4c84-ac9a-7d0f3e85bedc.png align="center")
    
    ```java
    // Base Car class
    abstract class Car {
        protected String brand;
        protected String model;
        protected boolean isEngineOn;
        protected int currentSpeed;
    
        public Car(String brand, String model) {
            this.brand = brand;
            this.model = model;
            this.isEngineOn = false;
            this.currentSpeed = 0;
        }
    
        //Common methods for All cars.
        public void startEngine() {
            isEngineOn = true;
            System.out.println(brand + " " + model + " : Engine started.");
        }
    
        public void stopEngine() {
            isEngineOn = false;
            currentSpeed = 0;
            System.out.println(brand + " " + model + " : Engine turned off.");
        }
    
        public abstract void accelerate();             // Abstract method for Dynamic Polymorphism
    
        public abstract void accelerate(int speed);    //Abstract method for Static Polymorphism
        
        public abstract void brake();                  // Abstract method for Dynamic Polymorphism
    }
    
    class ManualCar extends Car {
        private int currentGear;
    
        public ManualCar(String brand, String model) {
            super(brand, model);
            this.currentGear = 0;
        }
    
        //Specialized method for Manual Car
        public void shiftGear(int gear) {
            currentGear = gear;
            System.out.println(brand + " " + model + " : Shifted to gear " + currentGear);
        }
    
        // Overriding accelerate - Dynamic Polymorphism
        @Override
        public void accelerate() {
            if (!isEngineOn) {
                System.out.println(brand + " " + model + " : Cannot accelerate! Engine is off.");
                return;
            }
            currentSpeed += 20;
            System.out.println(brand + " " + model + " : Accelerating to " + currentSpeed + " km/h");
        }
    
        //overriding and overloading accelerate at the same time.
        @Override
        public void accelerate(int speed) {
            if (!isEngineOn) {
                System.out.println(brand + " " + model + " : Cannot accelerate! Engine is off.");
                return;
            }
            currentSpeed += speed;
            System.out.println(brand + " " + model + " : Accelerating to " + currentSpeed + " km/h");
        }
    
        // Overriding brake - Dynamic Polymorphism
        @Override
        public void brake() {
            currentSpeed -= 20;
            if (currentSpeed < 0) currentSpeed = 0;
            System.out.println(brand + " " + model + " : Braking! Speed is now " + currentSpeed + " km/h");
        }
    }
    
    class ElectricCar extends Car {
        private int batteryLevel;
    
        public ElectricCar(String brand, String model) {
            super(brand, model);
            this.batteryLevel = 100;
        }
    
        //specialized method for Electric Car
        public void chargeBattery() {
            batteryLevel = 100;
            System.out.println(brand + " " + model + " : Battery fully charged!");
        }
    
        // Overriding accelerate - Dynamic Polymorphism
        @Override
        public void accelerate() {
            if (!isEngineOn) {
                System.out.println(brand + " " + model + " : Cannot accelerate! Engine is off.");
                return;
            }
            if (batteryLevel <= 0) {
                System.out.println(brand + " " + model + " : Battery dead! Cannot accelerate.");
                return;
            }
            batteryLevel -= 10;
            currentSpeed += 15;
            System.out.println(brand + " " + model + " : Accelerating to " + currentSpeed +
                               " km/h. Battery at " + batteryLevel + "%.");
        }
    
        // Overriding accelerate - Dynamic Polymorphism
        @Override
        public void accelerate(int speed) {
            if (!isEngineOn) {
                System.out.println(brand + " " + model + " : Cannot accelerate! Engine is off.");
                return;
            }
            if (batteryLevel <= 0) {
                System.out.println(brand + " " + model + " : Battery dead! Cannot accelerate.");
                return;
            }
            batteryLevel -= 10 + speed;
            currentSpeed += speed;
            System.out.println(brand + " " + model + " : Accelerating to " + currentSpeed +
                               " km/h. Battery at " + batteryLevel + "%.");
        }
    
        // Overriding brake - Dynamic Polymorphism
        @Override
        public void brake() {
            currentSpeed -= 15;
            if (currentSpeed < 0) currentSpeed = 0;
            System.out.println(brand + " " + model +
                               " : Regenerative braking! Speed is now " + currentSpeed +
                               " km/h. Battery at " + batteryLevel + "%.");
        }
    }
    
    // Main function
    public class StaticAndDynamicPolymorphism {
        public static void main(String[] args) {
            Car myManualCar = new ManualCar("Ford", "Mustang");
            myManualCar.startEngine();
            myManualCar.accelerate();
            myManualCar.accelerate();
            myManualCar.brake();
            myManualCar.stopEngine();
    
            System.out.println("----------------------");
    
            Car myElectricCar = new ElectricCar("Tesla", "Model S");
            myElectricCar.startEngine();
            myElectricCar.accelerate();
            myElectricCar.accelerate();
            myElectricCar.brake();
            myElectricCar.stopEngine();
        }
    }
    ```
    
    ## 💠 **Protected in Java:**
    
* When working with Object-Oriented Programming in Java, **access modifiers** help us control where our variables and methods can be accessed from. The four access modifiers in Java are:  
    `private`, `default`, `protected`, and `public`.
    

## 💠What is `protected`?

* The `protected` access modifier allows a **variable** or **method** to be:
    
    * ✅ **Accessed inside the same class**
        
    * ✅ **Accessed from any class in the same package**
        
    * ✅ **Accessed from a subclass (child class)** even if the subclass is in a **different package**
        
    * ❌ **Not accessible from just any class outside the package**
        
    
    ```java
    // Base class
    class Car {
        protected String brand = "Generic Car";  // 🔐 Protected
    }
    
    // Subclass in the same package
    class ManualCar extends Car {
        public void displayBrand() {
            // ✅ Can access 'brand' because it's protected
            System.out.println("Brand is: " + brand);
        }
    }
    ```
    
    ## 💠 **What is Operator Overloading?**
    
    Operator overloading is the ability to **define or redefine the behavior of standard operators** (like `+`, `-`, `*`) **for user-defined types (classes/objects)**.
    

## 💠Why is Operator Overloading Available in C++ But Not in Java?

* C++ ✅ supports it using `operator+` syntax, giving developers more control.
    
* Java ❌ doesn’t support it (except `String +`) to keep code simple and clear.
    
* Python ✅ supports it via special methods like `__add__`, balancing flexibility and structure.
    

### **Day 3 Completed ✅ System Design**

> ***NOTE : - A big thanks to my mentors*** [***<mark>Rohit Negi</mark>***](https://www.linkedin.com/in/rohit-negi9/) ***<mark>Sir </mark> and*** [***<mark>Aditya</mark>***](https://www.linkedin.com/in/adityatandon2/) [***<mark>Sir</mark> fo***](https://www.linkedin.com/in/adityatandon2/)***r launching this amazing <mark>8-week course</mark> absolutely <mark>free</mark> on YouTube via <mark>CoderArmy9 </mark> :-*** [https://www.youtube.com/@CoderArmy9***🙌***](https://www.youtube.com/@CoderArmy9)

👉 **Share this blog with your connections!** Let’s keep learning, growing, and supporting one another on this journey. 🚀

✍️ [***Payal Kumari***](https://www.linkedin.com/in/payalkumari10/) 👩‍💻

![](https://cdn.hashnode.com/res/hashnode/image/upload/v1747307319384/f7ef61cd-cf8b-4f6e-89d1-d791706a6c7f.jpeg align="right")

**Jai Hind 🇮🇳 | #CoderArmy #LearningInPublic #SystemDesign #TechForAll #MentorshipMatters** [**#8weeksLLdChallenge**](https://www.youtube.com/hashtag/8weekslldchallenge) [**#LowLevelDesign**](https://www.youtube.com/hashtag/8weekslldchallenge) [#Code **#LLD**](https://www.youtube.com/hashtag/lowleveldesign) [**#OOP**](https://www.youtube.com/hashtag/lld) **👩‍💻**

%[https://youtu.be/KGOEK0-XBIg?si=SowJU5WyB0AfvQc-]