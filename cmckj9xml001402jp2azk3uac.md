---
title: "📅Week-7 (Day-2) - How to Design a Vending Machine Using the State Pattern: UML and Code Explained"
seoTitle: "Designing Vending Machines with State Pattern"
seoDescription: "Learn how to design a vending machine using the State Pattern with UML, code examples, and clear state transitions for effective system design"
datePublished: Tue Jul 01 2025 12:58:17 GMT+0000 (Coordinated Universal Time)
cuid: cmckj9xml001402jp2azk3uac
slug: week-7-day-2-how-to-design-a-vending-machine-using-the-state-pattern-uml-and-code-explained
cover: https://cdn.hashnode.com/res/hashnode/image/upload/v1751369181030/590b52b1-8968-4035-8247-9b93b1e97e3d.png
ogImage: https://cdn.hashnode.com/res/hashnode/image/upload/v1751374610332/48c55deb-f1d6-4bc0-9f43-4f7da129eece.png
tags: cpp, code, java, technology, coding, system-design, dsa, coding-challenge, technical-writing-1, coding-journey, low-level-design, lld, coderarmy, 8weekslldchallenge, payalkumari11

---

> ***NOTE: - <mark>I started my 8-week system design journey with Coder Army. I will be journaling every day, recording what I learn, reflecting on it, and sharing it with my network to help newcomers to system design.</mark>***

**Namaste developers! 👋**  
Welcome to another exciting day of the **#8WeeksLLDChallenge** — and today, we’re diving into something we all interact with but rarely design ourselves — a **Vending Machine**!

From snacks to sodas, vending machines are everywhere. But have you ever thought about how they handle different states like waiting for a coin, processing a request, or dispensing an item?

Today, we’ll **build a Vending Machine system** using the **State Design Pattern**, exploring how to handle transitions between different states like `NoCoin`, `HasCoin`, `Dispensing`, and `SoldOut`. Along the way, we’ll also explore concepts of **UML, State Interface, and Concrete States**.

**(Hindi: Kabhi socha hai vending machines kaise samajhti hain ki kab coin dala gaya hai, kab item dena hai, aur kab stock khatam ho gaya hai?)**  
Aaj hum banayenge ek **smart vending machine system** jo har state ko clearly manage karega — just like in the real world.

## 💠 What is the State Design Pattern?

> The State Design Pattern allows an object to alter its behavior when its internal state changes. It helps remove complex if-else or switch-case conditions and makes the code **extensible and clean**.

**(Hindi)**: State Design Pattern ek aisa design hai jisme ek object apne state ke according behavior change karta hai. Ye pattern code ko modular, clean aur extendable banata hai.

## 💠Real-Life Example: Vending Machine

Imagine you’re using a water bottle vending machine.Sometimes when you put a coin in the machine, the item is not dispensed, or it is out of stock. Right?

> (Hindi: Imagine ki aap paanee kee botal bechane vaalee masheen ka use kar rahe hain  
> Kabhi kabhi machine me coin daalne par item nahi milta, ya stock khatam ho jata hai. Right?)

That machine goes through different **states**:

* No Coin Inserted
    
* Coin Inserted
    
* Dispensing Item
    
* Sold Out
    

Using **State Design Pattern**, we can model this real-world flow beautifully.

## 💠States & Transitions (Visual Explained)

👇 Below is the **UML + State Table + Flow** you can refer:

Each state handles only what it’s responsible for. Based on actions like `insertCoin()`, `selectItem()`, `dispense()`, or `refill()`, the machine transitions to another state.

![](https://cdn.hashnode.com/res/hashnode/image/upload/v1751371312126/828528fc-3032-4ad4-beaa-8d8c8f714430.jpeg align="center")

## 💠Working of Each State

### 📍NoCoinState

* Coin Accept karta hai
    
* Item selection, dispense ya return coin is state me allow nahi hai
    
* Transition: `insertCoin()` → moves to **HasCoinState**
    

### 📍HasCoinState

* Coin already inserted hota hai
    
* Allows item selection
    
* Coin return bhi allowed hai
    
* Refill not allowed in this state
    
* Transition: `selectItem()` → **DispenseState**, `returnCoin()` → **NoCoinState**
    

### 📍DispenseState

* Item dispense hota hai
    
* Change return ho sakta hai (if any)
    
* No coin insert or selection during this state
    
* Transition: if items left → **NoCoinState**, else → **SoldOutState**
    

### 📍SoldOutState

* Coin accept nahi karega
    
* Item selection ya dispense bhi nahi hoga
    
* Only allows `refill()`
    
* Transition: `refill()` → **NoCoinState**
    

## 💠What Will We Learn:

🔹 Define the Context & Interface:

* Interface: `VendingState`
    
* Context: `VendingMachine`
    

🔹 Implement 4 Concrete States:

* `NoCoinState`
    
* `HasCoinState`
    
* `DispenseState`
    
* `SoldOutState`
    

🔹 Handle Transitions Cleanly:

* Transitions return a **new state**
    
* No `if-else` chaos
    

🔹 Add Real-Life Flow:

* Insert coins
    
* Select item
    
* Get change
    
* Handle sold-out scenarios
    

🔹 Benefit:

✅ Simplifies messy logic

✅ Adheres to **Open/Closed Principle**

✅ Super easy to add new behavior

## 💠UML

![](https://cdn.hashnode.com/res/hashnode/image/upload/v1751372499968/58ad7eff-dc2f-46c8-a4db-262c8188f1f6.png align="center")

## 💠Standard UML

![](https://cdn.hashnode.com/res/hashnode/image/upload/v1751374303394/b6febec2-e9ff-4b9b-9d06-ff5e552f4bf9.png align="center")

## 💠Code

```java
// Abstract State Interface
interface VendingState {
    VendingState insertCoin(VendingMachine machine, int coin);
    VendingState selectItem(VendingMachine machine);
    VendingState dispense(VendingMachine machine);
    VendingState returnCoin(VendingMachine machine);
    VendingState refill(VendingMachine machine, int quantity);
    String getStateName();
}

// Context Class - Vending Machine
class VendingMachine {
    private VendingState currentState;
    private int itemCount;
    private int itemPrice;
    private int insertedCoins;
    
    // State objects (we'll initialize these)
    private VendingState noCoinState;
    private VendingState hasCoinState;
    private VendingState dispenseState;
    private VendingState soldOutState;
    
    public VendingMachine(int itemCount, int itemPrice) {
        this.itemCount = itemCount;
        this.itemPrice = itemPrice;
        this.insertedCoins = 0; 
        
        // Create state objects
        noCoinState = new NoCoinState();
        hasCoinState = new HasCoinState();
        dispenseState = new DispenseState();
        soldOutState = new SoldOutState();
        
        // Set initial state
        if (itemCount > 0) {
            currentState = noCoinState;
        } else {
            currentState = soldOutState;
        }
    }
    
    // Delegate to current state and update state based on return value
    public void insertCoin(int coin) {
        currentState = currentState.insertCoin(this, coin);
    }
    
    public void selectItem() {
        currentState = currentState.selectItem(this);
    }
    
    public void dispense() {
        currentState = currentState.dispense(this);
    }
    
    public void returnCoin() {
        currentState = currentState.returnCoin(this);
    }
    
    public void refill(int quantity) {
        currentState = currentState.refill(this, quantity);
    }
        
    // Print the status of Vending Machine
    public void printStatus() {
        System.out.println("\n--- Vending Machine Status ---");
        System.out.println("Items remaining: " + itemCount);
        System.out.println("Inserted coin: Rs " + insertedCoins);
        System.out.println("Current state: " + currentState.getStateName() + "\n");
    }
    
    // Getters for states
    public VendingState getNoCoinState() { 
        return noCoinState;
    }
    public VendingState getHasCoinState() { 
        return hasCoinState;
    }
    public VendingState getDispenseState() { 
        return dispenseState; 
    }
    public VendingState getSoldOutState() { 
        return soldOutState;
    }
    
    // Data access methods
    public int getItemCount() { 
        return itemCount; 
    }
    public void decrementItemCount() { 
        itemCount--; 
    }
    public void incrementItemCount(int count) {
        itemCount += count;
    }
    public void incrementItemCount() {
        itemCount += 1;
    }
    public int getInsertedCoin() { 
        return insertedCoins;
    }
    public void setInsertedCoin(int coin) { 
        insertedCoins = coin;
    }
    public void addCoin(int coin) { 
        insertedCoins += coin;
    }
    public int getPrice() {
        return this.itemPrice;
    }
    public void setPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }
}

// Concrete State: No Coin Inserted
class NoCoinState implements VendingState {
    public VendingState insertCoin(VendingMachine machine, int coin) {
        machine.setInsertedCoin(coin); // Rs 10
        System.out.println("Coin inserted. Current balance: Rs " + coin);
        return machine.getHasCoinState(); // Transition to HasCoinState
    }
    
    public VendingState selectItem(VendingMachine machine) {
        System.out.println("Please insert coin first!");
        return machine.getNoCoinState(); // Stay in same state
    }
    
    public VendingState dispense(VendingMachine machine) {
        System.out.println("Please insert coin and select item first!");
        return machine.getNoCoinState(); // Stay in same state
    }
    
    public VendingState returnCoin(VendingMachine machine) {
        System.out.println("No coin to return!");
        return machine.getNoCoinState(); // Stay in same state
    }

    public VendingState refill(VendingMachine machine, int quantity) {
        System.out.println("Items refilling");
        machine.incrementItemCount(quantity);
        return machine.getNoCoinState(); // Stay in same state
    }
    
    public String getStateName() {
        return "NO_COIN";
    }
}

// Concrete State: Coin Inserted
class HasCoinState implements VendingState {
    public VendingState insertCoin(VendingMachine machine, int coin) {
        machine.addCoin(coin);
        System.out.println("Additional coin inserted. Current balance: Rs " + machine.getInsertedCoin());
        return machine.getHasCoinState(); // Stay in same state
    }
    
    public VendingState selectItem(VendingMachine machine) {
        if (machine.getInsertedCoin() >= machine.getPrice()) {
            System.out.println("Item selected. Dispensing...");
            
            int change = machine.getInsertedCoin() - machine.getPrice();
            if (change > 0) {
                System.out.println("Change returned: Rs " + change);
            }
            machine.setInsertedCoin(0);
            
            return machine.getDispenseState(); // Transition to DispenseState
        } 
        else {
            int needed = machine.getPrice() - machine.getInsertedCoin();
            System.out.println("Insufficient funds. Need Rs " + needed + " more.");
            return machine.getHasCoinState(); // Stay in same state
        }
    }
    
    public VendingState dispense(VendingMachine machine) {
        System.out.println("Please select an item first!");
        return machine.getHasCoinState(); // Stay in same state
    }
    
    public VendingState returnCoin(VendingMachine machine) {
        System.out.println("Coin returned: Rs " + machine.getInsertedCoin());
        machine.setInsertedCoin(0);
        return machine.getNoCoinState(); // Transition to NoCoinState
    }

    public VendingState refill(VendingMachine machine, int quantity) {
        System.out.println("Can't refil in this state");
        return machine.getHasCoinState(); // Stay in same state
    }
    
    public String getStateName() {
        return "HAS_COIN";
    }
}

// Concrete State: Item Sold
class DispenseState implements VendingState {
    public VendingState insertCoin(VendingMachine machine, int coin) {
        System.out.println("Please wait, already dispensing item. Coin returned: Rs " + coin);
        return machine.getDispenseState();  // Stay in same state
    }
    
    public VendingState selectItem(VendingMachine machine) {
        System.out.println("Already dispensing item. Please wait.");
        return machine.getDispenseState(); // Stay in same state
    }
    
    public VendingState dispense(VendingMachine machine) {
        System.out.println("Item dispensed!");
        machine.decrementItemCount();
        
        if (machine.getItemCount() > 0) {
            return machine.getNoCoinState(); // Transition to NoCoinState
        } 
        else {
            System.out.println("Machine is now sold out!");
            return machine.getSoldOutState(); // Transition to SoldOutState
        }
    }
    
    public VendingState returnCoin(VendingMachine machine) {
        System.out.println("Cannot return coin while dispensing item!");
        return machine.getDispenseState(); // Stay in same state
    }

    public VendingState refill(VendingMachine machine, int quantity) {
        System.out.println("Can't refil in this state");
        return machine.getDispenseState(); // Stay in same state
    }

    public String getStateName() {
        return "DISPENSING";
    }
}

// Concrete State: Sold Out
class SoldOutState implements VendingState {
    public VendingState insertCoin(VendingMachine machine, int coin) {
        System.out.println("Machine is sold out. Coin returned: Rs " + coin);
        return machine.getSoldOutState(); // Stay in same state
    }
    
    public VendingState selectItem(VendingMachine machine) {
        System.out.println("Machine is sold out!");
        return machine.getSoldOutState(); // Stay in same state
    }
    
    public VendingState dispense(VendingMachine machine) {
        System.out.println("Machine is sold out!");
        return machine.getSoldOutState(); // Stay in same state
    }
    
    public VendingState returnCoin(VendingMachine machine) {
        System.out.println("Machine is sold out. No coin inserted.");
        return machine.getSoldOutState(); // Stay in same state
    }

    public VendingState refill(VendingMachine machine, int quantity) {
        System.out.println("Items refilling");
        machine.incrementItemCount(quantity);
        return machine.getNoCoinState();
    }
    
    public String getStateName() {
        return "SOLD_OUT";
    }
}

// Main class for Vending Machine
public class VendingMachineMain {
    public static void main(String[] args) {
        System.out.println("=== Water Bottle VENDING MACHINE ===");
        
        int itemCount = 2;
        int itemPrice = 20;

        VendingMachine machine = new VendingMachine(itemCount, itemPrice);
        machine.printStatus();
        
        // Test scenarios - each operation potentially changes state
        System.out.println("1. Trying to select item without coin:");
        machine.selectItem();  // Should ask for coin, no state change
        machine.printStatus();
        
        System.out.println("2. Inserting coin:");
        machine.insertCoin(10);  // State changes to HAS_COIN
        machine.printStatus();
        
        System.out.println("3. Selecting item with insufficient funds:");
        machine.selectItem();  // Insufficient funds, stays in HAS_COIN
        machine.printStatus();
        
        System.out.println("4. Adding more coins:");
        machine.insertCoin(10);  // Add more money, stays in HAS_COIN
        machine.printStatus();
        
        System.out.println("5. Selecting item Now");
        machine.selectItem();  // State changes to SOLD
        machine.printStatus();
        
        System.out.println("6. Dispensing item:");
        machine.dispense(); // State changes to NO_COIN (items remaining)
        machine.printStatus();
        
        System.out.println("7. Buying last item:");
        machine.insertCoin(20);  // State changes to HAS_COIN
        machine.selectItem();  // State changes to SOLD
        machine.dispense(); // State changes to SOLD_OUT (no items left)
        machine.printStatus();
        
        System.out.println("8. Trying to use sold out machine:");
        machine.insertCoin(5);  // Coin returned, stays in SOLD_OUT

        System.out.println("9. Trying to use sold out machine:");
        machine.refill(2);
        machine.printStatus(); // State changes NO_COIN
    }
}
```

## 💠Advantages of State Pattern in System Design

🔸 Clean separation of logic 🔸 Easy testing of each state individually 🔸 Reusable & modular state objects 🔸 Easier debugging and extension

(Hindi): Is pattern ka use karne se aapka code easily manageable, readable aur reusable ho jata hai. Agar kal aapko koi naya state add karna hai (e.g. Maintenance Mode), to aap bina purane code ko touch kiye add kar sakte ho!

## 💠Real-Life Examples

State Design Pattern sirf vending machine tak limited nahi hai, real life me kai cheezein is pattern ka example hain:

* **ATM Machine** – Card insert hone se state change hoti hai
    
* **Traffic Light** – Har few seconds me state automatically switch hoti hai
    
* **Coffee Machine** – Coin, brewing, sold-out state
    
* **Metro Entry Gate** – Swipe karne se gate unlock hota hai
    

> Har example me object (machine/gate/etc.) ka behavior change hota hai depending on **its current state** – aur yahi State Pattern ka magic hai! ✨

## 💠Final Thoughts

System Design is not just for interviews – it’s a real skill for clean code and better architecture.

### **Week - 7 (Day-2) Completed ✅ System Design**

> ***NOTE : - A big thanks to my mentors <mark>Rohit Negi</mark> <mark>Sir </mark> and*** [***<mark>Aditya</mark>***](https://www.linkedin.com/in/adityatandon2/) [***<mark>Sir</mark> fo***](https://www.linkedin.com/in/adityatandon2/)***r launching this amazing <mark>8-week course</mark> absolutely <mark>free</mark> on YouTube via <mark>CoderArmy9 </mark> :-*** [***youtube.com/@CoderArmy9***](http://youtube.com/@CoderArmy9) [***. 🙌***](https://www.youtube.com/@CoderArmy9)

👉 **Share this blog with your connections!** Let’s keep learning, growing, and supporting one another on this journey. 🚀

✍️ [***Payal Kumari***](https://www.linkedin.com/in/payalkumari10/) 👩‍💻

![](https://cdn.hashnode.com/res/hashnode/image/upload/v1751028834513/20d4e6f8-3526-43a0-a33e-54c73f4f0d3e.jpeg?auto=compress,format&format=webp align="left")

**Jai Hind 🇮🇳 | #CoderArmy #LearningInPublic #SystemDesign #TechForAll #MentorshipMatters** [**#8weeksLLdChallenge**](https://www.youtube.com/hashtag/8weekslldchallenge) [**#LowLevelDesign**](https://www.youtube.com/hashtag/lowleveldesign) [**#LLD**](https://www.youtube.com/hashtag/lld) **👩‍💻**

%[https://youtu.be/bJPmvie_p4w?si=hDbAt_4jNkUh82NM]