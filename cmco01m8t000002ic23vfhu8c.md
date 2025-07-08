---
title: "📅Week-8 (Day-1) - How to Implement Prototype Design Pattern: A Guide with UML and Code"
seoTitle: "Implementing Prototype Design Pattern: Guide & UML"
seoDescription: "Learn how to implement the Prototype Design Pattern with UML and code examples. Boost efficiency through object cloning, ideal for large systems"
datePublished: Thu Jul 03 2025 23:11:01 GMT+0000 (Coordinated Universal Time)
cuid: cmco01m8t000002ic23vfhu8c
slug: week-8-day-1-how-to-implement-prototype-design-pattern-a-guide-with-uml-and-code
cover: https://cdn.hashnode.com/res/hashnode/image/upload/v1751563529188/317dece0-ba9d-4f6f-a12c-fd6e7e72ed35.png
ogImage: https://cdn.hashnode.com/res/hashnode/image/upload/v1751584109870/4f37c121-951f-4b8d-8ffb-4099248e6e47.png
tags: cpp, code, java, technology, hashnode, system-design, dsa, techblog, coding-challenge, technical-writing-1, low-level-design, lld, coderarmy, 8weekslldchallenge, payalkumari11

---

> ***NOTE: - <mark>I started my 8-week system design journey with Coder Army. I will be journaling every day, recording what I learn, reflecting on it, and sharing it with my network to help newcomers to system design.</mark>***

**Namaste developers!** 👋  
Welcome to another power-packed day of the #8WeeksLLDChallenge — and today, we’re diving into something that every large system needs but is often overlooked — the **Mediator Design Pattern**!

**Aaj ka challenge:** Chat Room System — but with a twist!  
Instead of users communicating with each other directly (which creates chaos in large systems), we’ll use a **Mediator** to handle message passing — creating a cleaner, more scalable architecture.

## 💠 What is the Prototype Design Pattern?

The **Prototype Pattern** allows you to create new objects by **cloning existing ones**, instead of building them from scratch every time.

(Hindi: Prototype Pattern aapko naye object banane ke liye existing object ka **clone banane** ki suvidha deta hai. Agar ek baar object bana liya hai, toh baar-baar naye create karne ki zarurat nahi — usi ka duplicate use karke kaam ho jata hai.)

## 💠Why Use It?

Creating a complex object from scratch every time can be:

* ❌ Time-consuming
    
* ❌ Expensive (resource-heavy calculations, DB calls, setup)
    
* ❌ Error-prone (repeat same values again & again)
    

Instead, just **clone** an existing template object and tweak it!

## 💠Real-Life Analogy

> NPC Cloning in Games!

Imagine you're building a game like PUBG or Free Fire 🎮, and you need to create **100 aliens** (Non-Player Characters).

* Without prototype: har alien ke liye manually stats bharne padenge
    
* With prototype: ek baar ek alien banao, baaki sab **copy-paste (clone)**!
    

(Hindi: Ek hi base alien se 100 copy banao — time bacha, kaam asaan hua )

## 💠UML Diagram

![](https://cdn.hashnode.com/res/hashnode/image/upload/v1751583776249/24145901-52dd-40a7-9cb6-15961296ca67.png align="center")

## 💠Code

### 📍<mark>without Prototype Pattern</mark>

```java
#include <iostream>
#include <string>
#include <vector>
using namespace std;

// Simple NPC class — no Prototype
class NPC {
public:
    string name;
    int health;
    int attack;
    int defense;

    // “Heavy” constructor: every field must be provided
    NPC(const string& name, int health, int attack, int defense) {

        // call database
        // complex calc
        this->name = name;
        this->health = health;
        this->attack = attack;
        this->defense = defense;

        cout << "Creating NPC '" << name << "' [HP:" << health << ", ATK:" 
             << attack << ", DEF:" << defense << "]\n";
    }

    void describe() {
        cout << "  NPC: " << name << " | HP=" << health << " ATK=" << attack
             << " DEF=" << defense << "\n";
    }
};

int main() {
    // Base Alien
    NPC* alien = new NPC("Alien", 30, 5, 2);
    alien->describe();

    // Powerful Alien — must re-pass all stats, easy to make mistakes
    NPC* alien2 = new NPC("Powerful Alien", 30, 5, 5);
    alien2->describe();

    // If you want 100 aliens, you'd repeat this 100 times…

    // cleanup
    delete alien;
    delete alien2;
    return 0;
}
```

**Benefits:**

Every time you want a new NPC, you have to:

* Call constructor
    
* Refill all values again
    
* Risk mistakes and redundancy
    

### 📍<mark>with Prototype Pattern</mark>

```java
#include <iostream>
#include <string>
#include <vector>
using namespace std;

// Cloneable (aka Prototype) interface
class Cloneable {
public:
    virtual Cloneable* clone() const = 0;
    virtual ~Cloneable() {}
};

class NPC : public Cloneable {
public:
    string name;
    int health;
    int attack;
    int defense;

    NPC(const string& name, int health, int attack, int defense) {
        // call database
        // complex calc
        this->name = name; 
        this->health = health; 
        this->attack = attack; 
        this->defense = defense;
        cout << "Setting up template NPC '" << name << "'\n";
    }

    // copy‐ctor used by clone()
    NPC(const NPC& other) {
        name = other.name;
        health = other.health;
        attack = other.attack;
        defense = other.defense;
        cout << "Cloning NPC '" << name << "'\n";
    }

    // the clone method required by Prototype
    Cloneable* clone() const override {
        return new NPC(*this);
    }

    void describe() {
        cout << "NPC " << name  << " [HP=" << health  << " ATK=" << attack 
             << " DEF=" << defense << "]\n";
    }

    // setters to tweak the clone…
    void setName(const string& n) { 
        name = n;
    }
    void setHealth(int h) { 
        health = h;
    }
    void setAttack(int a) {
         attack = a; 
    }
    void setDefense(int d){ 
        defense = d;
    }
};

int main() {
    // 1) build one “heavy” template
    NPC* alien = new NPC("Alien", 30, 5, 2);

    // 2) quickly clone + tweak as many variants as you like:
    NPC* alienCopied1 = dynamic_cast<NPC*>(alien->clone());
    alienCopied1->describe();

    NPC* alienCopied2 = dynamic_cast<NPC*>(alien->clone());
    alienCopied2->setName("Powerful Alien");
    alienCopied2->setHealth(50);
    alienCopied2->describe();

    // cleanup
    delete alien;
    delete alienCopied1;
    delete alienCopied2;
}

        
```

**Benefits:**

* Only one object is setup-heavy
    
* Rest are just copied (cloned)
    
* Easy to customize each new object
    
* Super efficient for games, simulations, testing, prototyping
    

## 💠Advantages of Prototype Pattern

✅ Performance boost (no re-computation)  
✅ Reduced memory/resource consumption  
✅ Makes code DRY (Don’t Repeat Yourself)  
✅ Super useful in games, simulations, design tools, IDEs

## 💠When to Use

Use Prototype Pattern when:

* Object creation is **expensive** or **slow**
    
* You need **many similar instances** with small changes
    
* You're building **test data**, **simulations**, or **design templates**
    

## 💠 Summary

| Without Prototype | With Prototype |
| --- | --- |
| Manually set all fields | Clone from base template |
| Expensive & repetitive | Fast and efficient cloning |
| Risk of typo or mistakes | Less chance of error |

## 💠Final Thoughts

Prototype Pattern isn’t just a design pattern — it’s a **developer’s shortcut** when you need speed, scale, and simplicity

(Hindi: Prototype pattern aapki development ko fast aur efficient banata hai — kam coding, zyada cloning)

### **Week - 8 (Day-1) Completed ✅ System Design**

> ***NOTE : - A big thanks to my mentors <mark>Rohit Negi</mark> <mark>Sir </mark> and*** [***<mark>Aditya</mark>***](https://www.linkedin.com/in/adityatandon2/) [***<mark>Sir</mark> fo***](https://www.linkedin.com/in/adityatandon2/)***r launching this amazing <mark>8-week course</mark> absolutely <mark>free</mark> on YouTube via <mark>CoderArmy9 </mark> :-*** [***youtube.com/@CoderArmy9***](http://youtube.com/@CoderArmy9) [***. 🙌***](https://www.youtube.com/@CoderArmy9)

👉 **Share this blog with your connections!** Let’s keep learning, growing, and supporting one another on this journey. 🚀

✍️ [***Payal Kumari***](https://www.linkedin.com/in/payalkumari10/) 👩‍💻

![](https://cdn.hashnode.com/res/hashnode/image/upload/v1751584000910/793a9062-52e3-4789-9683-dd358255868f.jpeg align="center")

**Jai Hind 🇮🇳 | #CoderArmy #LearningInPublic #SystemDesign #TechForAll #MentorshipMatters** [**#8weeksLLdChallenge**](https://www.youtube.com/hashtag/8weekslldchallenge) [**#LowLevelDesign**](https://www.youtube.com/hashtag/lowleveldesign) [**#LLD**](https://www.youtube.com/hashtag/lld) **👩‍💻**

%[https://youtu.be/KMQFNV8LFec?si=nUNsx6JXbMZcJHps]