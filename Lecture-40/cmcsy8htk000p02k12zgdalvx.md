---
title: "📅Week 8 (Day-5) – Ending Strong: Null Object Pattern, Anti-Patterns & System Design Insights"
seoTitle: "Week 8 Wrap-Up: Patterns & Design Insights"
seoDescription: "Explore anti-patterns, the Null Object Pattern, and key system design practices to craft scalable, clean code. Conclude the 8-week journey"
datePublished: Mon Jul 07 2025 10:19:14 GMT+0000 (Coordinated Universal Time)
cuid: cmcsy8htk000p02k12zgdalvx
slug: week-8-day-5-ending-strong-null-object-pattern-anti-patterns-and-system-design-insights
cover: https://cdn.hashnode.com/res/hashnode/image/upload/v1751877312451/692a144d-e72a-4e0a-96df-43eeac4ed2d2.png
ogImage: https://cdn.hashnode.com/res/hashnode/image/upload/v1751883475190/8029f057-055f-42f0-accc-9633647b6b72.png
tags: code, technology, tech, hashnode, system-design, dsa, techblog, coding-challenge, technical-writing-1, coding-journey, low-level-design, lld, coderarmy, 8weekslldchallenge, payalkumari11

---

> ***NOTE: - <mark>I started my 8-week system design journey with Coder Army. I will be journaling every day, recording what I learn, reflecting on it, and sharing it with my network to help newcomers to system design.</mark>***

### **Namaste Developers! 🙏**

  
Welcome to the final and reflective day of the #8WeeksLLDChallenge — and today, we’re wrapping up this incredible journey by exploring some **crucial concepts in System Design**:  
**Anti-Patterns**, the **Null Object Pattern**, and key **Best Practices** to write cleaner, scalable code.

Let’s learn not just how to build systems — but how to build them *right*! 🙌  
Thank you to everyone who’s been part of this enriching journey! 🚀

## 💠What are Anti-Patterns?

**Anti-patterns are bad design habits that seem like a good idea but often lead to poor, messy, and hard-to-maintain code.**

> (Hindi: Anti-patterns woh coding habits hai jo shuru me sahi lagti hain, lekin baad me system ko slow, complex aur unreliable bana deti hain.)

## 💠Common Anti-Patterns You Must Avoid:

### 1️⃣ **God Object**

* One class doing **too much** work — handling all responsibilities.
    
* Example: API class jisme sab kuch ho raha ho — logging, database, UI handling, etc.
    
* Solution: Delegate tasks to other small classes.
    

> (Hindi: Jab ek class har kaam kar rahi ho, toh woh God Object ban jata hai. Har kaam usi ke around ghoomta hai. Yeh avoid karo!)

### 2️⃣ **Spaghetti Code**

Code with no clear structure or entry/exit points

> Hindi: Code jisme koi structure, entry/exit point clear nahi ho.)

Tightly coupled, hard to debug, and full of errors

> (Hindi: Aisa code jo confuse kar de, samajh hi nahi aaye kya ho raha hai!)

### 3️⃣ **Hard Coding Values**

* Avoid hardcoding constants or values unless absolutely necessary.
    
* Use configuration files or environment variables.
    

> (Hindi: Code ke andar fixed values mat likho agar aage change hone ka chance ho.)

### 4️⃣ **Gold Plating (Over Engineering)**

* Making things too perfect by adding unnecessary features/designs.
    
* Focus on what's needed, not what “might” be needed.
    

> (Hindi: Har design pattern use karne ki zarurat nahi, sirf jo useful hai wahi use karo!)

### 5️⃣ **DRY Violation (Do Not Repeat Yourself)**

* **What it is**: Repeating the same code at multiple places in your project
    
* **What to do instead**: Create reusable **functions**, **methods**, or **helper classes**.
    

> (Hindi: *Baar-baar wahi code likhna. ek jagah define karo aur sab jagah use karo. Agar abhi har jagah same code paste kar diya, toh future mein agar changes karne pade,*  
> toh **har file ya line pe jakar change karna hoga**, which increases the complexity and chances of bugs  
> *Isse better hai ek class ya method mein define kar do — future mein sirf ek hi jagah change karna padega!)*

### 6️⃣ **Constructor Overloading Explosion**

* Too many overloaded constructors.
    
* Use **Builder Pattern** instead.
    

> (Hindi: Jab constructor bohot zyada overload ho jaye, builder pattern ka use karo!)

### 7️⃣ **Excessive Getters/Setters**

* Don’t blindly use getters/setters everywhere.
    
* Use only where encapsulation really needs it.
    

### 8️⃣ **Premature Optimization**

* Don’t try to make the code fast before making it work.
    
* First solve the problem with brute force, then optimize.
    

> (Hindi: Pehle chalne wala code likho, baad me fast banao!)

### 9️⃣ **Complex Inheritance Hierarchies**

* Avoid multiple inheritance if not needed.
    
* Use composition, strategy, or abstract classes.
    

> (Hindi: Inheritance ke chakkar me code ko confuse mat karo, composition is better!)

## 💠Null Object Pattern — The Silent Hero

Null Object Pattern helps avoid null checks in your code by using a default object.

### 📍Problem:

```java
if(obj != null) {
    obj.doSomething();
} else {
    // handle null
}
```

> (Hindi: Har jagah pe `if(obj != null)` check karna padta hai, warna NullPointerException aajayega!)

### ✅ Solution:

![](https://cdn.hashnode.com/res/hashnode/image/upload/v1751882885128/056506b4-d499-49e1-ab4a-9e450ff34361.png align="center")

Create a `NullObject` that does nothing, but prevents errors!

```java
interface FlyStrategy {
    void fly();
}

class FlyWithWings implements FlyStrategy {
    public void fly() { System.out.println("Flying with wings"); }
}

class FlyWithJet implements FlyStrategy {
    public void fly() { System.out.println("Flying with jet"); }
}

class NoFly implements FlyStrategy {
    public void fly() { /* Do nothing */ }
}
```

When you assign `NoFly` instead of `null`, code won’t break!

> (Hindi: Null ki jagah NoFly object use karo, taaki program crash na ho!)

## 💠Where to Use Null Object Pattern?

* Game Characters: If player doesn’t have a weapon, assign a `NullWeapon`.
    
* Logging: If logger is null, assign `NullLogger` which does nothing.
    
* UI Fields: If form field is missing, use a placeholder `NullInput`.
    

## 💠Bonus Tip: Double Dispatcher Explained

> Double Dispatch means – method call is decided by both object **and** visitor type.

### Example:

```java
visitor.visit(element);
```

* Both `visitor` and `element` decide what happens.
    
* Common in Visitor Design Pattern.
    

> (Hindi: Single dispatch me sirf object decide karta hai method call, par double dispatch me dono — object aur visitor!)

## 💠Summary

* Avoid common anti-patterns to write clean, scalable code
    
* Use Null Object Pattern to avoid null pointer issues
    
* Think smartly, don’t over-engineer
    
* Follow SOLID, DRY
    

> (Hindi: Clean code likhna ek skill hai, aur yeh Anti-Patterns aur Null Object Pattern usme bohot help karte hain!)

## **💠Final Thoughts**

**Week - 8 (Day-5) Completed ✅ System Design**  
<mark>And with that, I’ve officially wrapped up the </mark> **<mark>#8WeeksLLDChalleng</mark>e**! 🎉

This journey has been full of deep insights into Low-Level Design, Design Patterns, and System Design principles. Each day brought something new to learn and apply.

> ***NOTE : - A big thanks to my mentors*** [***<mark>Rohit Negi</mark>***](https://www.linkedin.com/in/rohit-negi9/) ***<mark>Sir </mark> and*** [***<mark>Aditya</mark>***](https://www.linkedin.com/in/adityatandon2/) [](https://www.linkedin.com/in/adityatandon2/)[***<mark>Sir</mark>***](https://www.linkedin.com/in/adityatandon2/) [***fo***](https://www.linkedin.com/in/adityatandon2/)***r launching this amazing <mark>8-week course</mark> absolutely <mark>free</mark> on YouTube via <mark>CoderArmy9 </mark> :-*** [***youtube.com/@CoderArmy9***](http://youtube.com/@CoderArmy9) [***. 🙌***](https://www.youtube.com/@CoderArmy9)

👉 **If you found this blog helpful, do share it with your friends and connections.**  
Let’s keep learning, growing, and uplifting each other in our tech journeys. 🚀

✍️ [***Payal Kumari***](https://www.linkedin.com/in/payalkumari10/) 👩‍💻

  
*Officially* ***<mark>completed</mark>*** *the* ***<mark>#8WeeksLLDChallenge blog series</mark>*** *on* ***<mark>my end!</mark>***

![](https://cdn.hashnode.com/res/hashnode/image/upload/v1751800371278/76b699d0-4d6d-4e1e-8e6e-a6ca3d5412be.jpeg?auto=compress,format&format=webp align="left")

**Jai Hind 🇮🇳 | #CoderArmy #LearningInPublic #SystemDesign #TechForAll #MentorshipMatters** [**#8weeksLLdChallenge**](https://www.youtube.com/hashtag/8weekslldchallenge) [**#LowLevelDesign**](https://www.youtube.com/hashtag/lowleveldesign) [**#LLD**](https://www.youtube.com/hashtag/lld) **👩‍💻**

%[https://youtu.be/iXHlc_S3Ae4?si=qpA9eMcnibms6D33]