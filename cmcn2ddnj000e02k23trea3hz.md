---
title: "📅Week-7 (Day-5) - How to Create a Chat Room Using the Mediator Design Pattern: UML and Coding Guide"
seoTitle: "Create Chat Room with Mediator Design Pattern"
seoDescription: "Learn how to create a chat room using the Mediator Design Pattern with this comprehensive guide on UML diagrams and coding"
datePublished: Thu Jul 03 2025 07:28:23 GMT+0000 (Coordinated Universal Time)
cuid: cmcn2ddnj000e02k23trea3hz
slug: week-7-day-5-how-to-create-a-chat-room-using-the-mediator-design-pattern-uml-and-coding-guide
cover: https://cdn.hashnode.com/res/hashnode/image/upload/v1751523781250/483e344d-60ce-474c-8fc3-1c5bb1436f03.png
ogImage: https://cdn.hashnode.com/res/hashnode/image/upload/v1751527655490/6a3bf462-2e50-453b-8b83-5d7346bb6b2e.png
tags: code, java, coding, hashnode, system-design, dsa, techblog, coding-challenge, technical-writing-1, coding-journey, low-level-design, lld, coderarmy, 8weekslldchallenge, payalkumari11

---

> ***NOTE: - <mark>I started my 8-week system design journey with Coder Army. I will be journaling every day, recording what I learn, reflecting on it, and sharing it with my network to help newcomers to system design.</mark>***

Namaste developers! 👋

Welcome to another power-packed day of the **#8WeeksLLDChallenge** — and today we’re diving into something that every large system needs but no one talks about enough — **the Mediator Design Pattern!**

Aaj ka topic hai **Chat Room System** — but with a twist!  
Instead of letting every user talk directly to each other (which creates a communication *mess*, we’ll learn how to **design a clean, scalable system** using the **Mediator Design Pattern**.

## 💠What is Mediator Design Pattern?

**<mark>"Defines an object that encapsulates how a set of objects interact and promotes loose coupling by preventing them from referring to each other directly."</mark>**

**Definition:** Mediator Pattern centralizes complex communications and control between related objects (called colleagues), so they don't communicate directly with each other.

(Hindi: Mediator Design Pattern ek central hub create karta hai jiske through sab users communicate karte hain. Iss se har class independent hoti hai aur direct dependency nahi hoti.)

## 💠Real-Life Analogy

**Classroom Example**: Imagine a classroom where all students communicate only through the teacher — if someone has a question or wants to answer, they tell the teacher, and the teacher communicates it to everyone else.

(Hindi: Socho ek classroom hai jahan sab students sirf teacher ke through baat karte hain — kisi ko question poochhna ho, ya answer dena ho, wo teacher ko bolte hain aur teacher sabko communicate karta hai.)

That **teacher is the Mediator!**

(Hindi: Agar sab students directly baat karte toh sab confuse ho jaate — lekin teacher ke through sabka communication ekdum smooth hota hai!)

## 💠Problem Without Mediator (Tight Coupling)

If we design without a mediator, then each user would have to connect with every other user manually.

(Hindi: Agar hum bina mediator ke design karein, toh har user ko har doosre user ke saath manually connect karna padta hai:

* For N users, we need N×(N–1)/2 connections
    
* Har user ke paas apna mute logic, message logic
    
* Code duplication and hard to maintain
    

### Example Without Mediator:

```java
User rohan = new User("Rohan");
User neha = new User("Neha");
User mohan = new User("Mohan");

rohan.addPeer(neha);
rohan.addPeer(mohan);
... and so on
```

This becomes complex as your user count increases.

## 💠 Solution: Use Mediator Design Pattern

Now we introduce a **mediator** — a central controller that:

* **Registers** users
    
* **Passes messages** between them
    
* **Handles** private messaging and mute features
    

Ek tarah se yeh mediator pura system manage karta hai, bina users ko directly ek dusre se connect kare.

### 📍 Benefits:

* Loose coupling
    
* Reusability
    
* Easy to manage
    
* Scalability
    

## 💠Components in Mediator Pattern

### 📍 `IMediator` Interface

* `registerColleague(Colleague c)`
    
* `send(from, msg)`
    
* `sendPrivate(from, to, msg)`
    

### 📍 `Colleague` (Abstract User)

* Har user mediator ke reference se kaam karta hai
    

### 📍 `ChatMediator` (Concrete Mediator)

* Handles registration, broadcast, private messaging, and mute
    

### 📍 `User` (Concrete Colleague)

* Har user ka name hota hai
    
* Messages ko send/receive karta hai
    

## 💠UML Diagram

![](https://cdn.hashnode.com/res/hashnode/image/upload/v1751527471197/2a16774d-e86e-45b9-9c62-e9ef01875678.png align="center")

## 💠Code

```java
import java.util.*;

// ─────────────── Mediator Interface ───────────────
interface IMediator {
    void registerColleague(Colleague c);
    void send(String from, String msg);
    void sendPrivate(String from, String to, String msg);
}

// ─────────────── Colleague Interface ───────────────
abstract class Colleague {
    protected IMediator mediator;  
    
    public Colleague(IMediator m) {
        mediator = m;
        mediator.registerColleague(this);
    }
    
    public abstract String getName();
    public abstract void send(String msg);
    public abstract void sendPrivate(String to, String msg);
    public abstract void receive(String from, String msg);
}

// Simple Pair class
class Pair<T, U> {
    public final T first;
    public final U second;
    
    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }
}

// ─────────────── Concrete Mediator ───────────────
class ChatMediator implements IMediator {
    private List<Colleague> colleagues;
    private List<Pair<String, String>> mutes; // (muter, muted)
    
    public ChatMediator() {
        colleagues = new ArrayList<>();
        mutes = new ArrayList<>();
    }
    
    public void registerColleague(Colleague c) {
        colleagues.add(c);
    }
    
    public void mute(String who, String whom) {
        mutes.add(new Pair<>(who, whom));
    }
    
    public void send(String from, String msg) {
        System.out.println("[" + from + " broadcasts]: " + msg);
        for (Colleague c : colleagues) {
            // Don't send msg to itself.
            if (c.getName().equals(from)) {
                continue;
            }

            boolean isMuted = false;
            // Ignore if person is muted
            for (Pair<String, String> p : mutes) {
                if (from.equals(p.second) && c.getName().equals(p.first)) {
                    isMuted = true;
                    break;
                }
            }
            if (!isMuted) {
                c.receive(from, msg);
            }
        }
    }
    
    public void sendPrivate(String from, String to, String msg) {
        System.out.println("[" + from + "→" + to + "]: " + msg);
        for (Colleague c : colleagues) {
            if (c.getName().equals(to)) {
                for (Pair<String, String> p : mutes) {
                    //Dont send if muted
                    if (from.equals(p.second) && to.equals(p.first)) {
                        System.out.println("\n[Message is muted]\n");
                        return;
                    }
                }
                c.receive(from, msg);
                return;
            }
        }
        System.out.println("[Mediator] User \"" + to + "\" not found]");
    }
}

// ─────────────── Concrete Colleague ───────────────
class User extends Colleague {
    private String name;
    
    public User(String n, IMediator m) {
        super(m);
        name = n;
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public void send(String msg) {
        mediator.send(name, msg);
    }
    
    @Override
    public void sendPrivate(String to, String msg) {
        mediator.sendPrivate(name, to, msg);
    }
    
    @Override
    public void receive(String from, String msg) {
        System.out.println("    " + name + " got from " + from + ": " + msg);
    }
}

// ─────────────── Demo ───────────────
public class MediatorPattern {
    public static void main(String[] args) {
        ChatMediator chatRoom = new ChatMediator();
        
        User user1 = new User("Rohan", chatRoom);
        User user2 = new User("Neha", chatRoom);
        User user3 = new User("Mohan", chatRoom);
        
        // Rohan mutes Mohan
        chatRoom.mute("Rohan", "Mohan");
        
        // broadcast from Rohan
        user1.send("Hello Everyone!");
        
        // private from Mohan to Neha
        user3.sendPrivate("Neha", "Hey Neha!");
    }
}
```

```java
import java.util.*;

// Each User knows *all* the others directly.
// If you have N users, you wind up wiring N*(N–1)/2 connections,
// and every new feature (mute, private send, logging...) lives in User too.
class User {
    private String name;
    private List<User> peers;
    private List<String> mutedUsers;
    
    public User(String n) {
        name = n;
        peers = new ArrayList<>();
        mutedUsers = new ArrayList<>();
    }
    
    // must manually connect every pair → N^2 wiring
    public void addPeer(User u) {
        peers.add(u);
    }
    
    // duplication: everyone has its own mute list
    public void mute(String userToMute) {
        mutedUsers.add(userToMute);
    }
    
    // broadcast to all peers
    public void send(String msg) {
        System.out.println("[" + name + " broadcasts]: " + msg);
        for (User peer : peers) {
            
            // if they have muted me dont send.
            if(!peer.isMuted(name)) {
                peer.receive(name, msg);
            }
        }
    }
    
    public boolean isMuted(String userName) {
        for(String name : mutedUsers) {
            if(name.equals(userName)) {
                return true;
            }
        }
        return false;
    }
    
    // private send - duplicated in every class
    public void sendTo(User target, String msg) {
        System.out.println("[" + name + "→" + target.name + "]: " + msg);
        if(!target.isMuted(name)) {
            target.receive(name, msg);
        }
    }
    
    public void receive(String from, String msg) {
        System.out.println("    " + name + " got from " + from + ": " + msg);
    }
}

public class WithoutMediator {
    public static void main(String[] args) {
        // create users
        User user1 = new User("Rohan");
        User user2 = new User("Neha");
        User user3 = new User("Mohan");
        
        // wire up peers (each knows each other) → n*(n-1)/2 connections
        user1.addPeer(user2);   
        user2.addPeer(user1);

        user1.addPeer(user3);   
        user3.addPeer(user1);
        
        user2.addPeer(user3); 
        user3.addPeer(user2);
        
        // mute example: Mohan mutes Rohan (Hence Rohan add Mohan to its muted list).
        user1.mute("Mohan");
        
        // broadcast
        user1.send("Hello everyone!");
        
        // private
        user3.sendTo(user2, "Hey Neha!");
    }
}
```

## 💠Advantages of Mediator Design Pattern

🔹 Centralized Control  
🔹 Reduces Complexity  
🔹 Makes adding new features easy  
🔹 Loosely coupled and testable code

## 💠Where Can You Use Mediator Pattern?

* Chat applications (like WhatsApp, Slack)
    
* Air Traffic Control systems
    
* Event Managers in GUI libraries
    
* Gaming lobby systems
    

(Hindi: Jahan multiple objects ek dusre se baat karte hain — mediator pattern ek boss ki tarah control leta hai!)

## 💠 Final Thoughts

Design Patterns are like magic spells for engineers

Mediator pattern ek real-world friendly aur scalable design approach deta hai — jise use karke hum chat room jaise complex interactions ko asaan aur manageable bana sakte hain.

Next time jab chat system banana ho ya multiple classes ke beech coordination chahiye ho — remember this: **Use a Mediator!**

System Design is not just for interviews – it’s a real skill for clean code and better architecture.

### **Week - 7 (Day-5) Completed ✅ System Design**

> ***NOTE : - A big thanks to my mentors <mark>Rohit Negi</mark> <mark>Sir </mark> and*** [***<mark>Aditya</mark>***](https://www.linkedin.com/in/adityatandon2/) [***<mark>Sir</mark> fo***](https://www.linkedin.com/in/adityatandon2/)***r launching this amazing <mark>8-week course</mark> absolutely <mark>free</mark> on YouTube via <mark>CoderArmy9 </mark> :-*** [***youtube.com/@CoderArmy9***](http://youtube.com/@CoderArmy9) [***. 🙌***](https://www.youtube.com/@CoderArmy9)

👉 **Share this blog with your connections!** Let’s keep learning, growing, and supporting one another on this journey. 🚀

✍️ [***Payal Kumari***](https://www.linkedin.com/in/payalkumari10/) 👩‍💻

![](https://cdn.hashnode.com/res/hashnode/image/upload/v1751525609844/cefc27b2-8176-451b-849b-c6f578a84267.jpeg align="center")

**Jai Hind 🇮🇳 | #CoderArmy #LearningInPublic #SystemDesign #TechForAll #MentorshipMatters** [**#8weeksLLdChallenge**](https://www.youtube.com/hashtag/8weekslldchallenge) [**#LowLevelDesign**](https://www.youtube.com/hashtag/lowleveldesign) [**#LLD**](https://www.youtube.com/hashtag/lld) **👩‍💻**

%[https://youtu.be/3lGIICzgyQQ?si=BVVaDHNRUMorN2vu]