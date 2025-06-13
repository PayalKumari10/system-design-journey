---
title: "📅Week-5 (Day-3) - Build Payment Gateway System | System Design"
seoTitle: "Payment Gateway System Design Guide"
seoDescription: "Explore building a scalable payment gateway system with design patterns and retry strategies, part of an 8-week system design course"
datePublished: Tue Jun 10 2025 18:30:00 GMT+0000 (Coordinated Universal Time)
cuid: cmbuhviq4000502ju87wufqib
slug: week-5-day-3-build-payment-gateway-system-system-design
cover: https://cdn.hashnode.com/res/hashnode/image/upload/v1749790854514/85ef59c6-eeee-4c81-946b-20b59b4b21d2.png
ogImage: https://cdn.hashnode.com/res/hashnode/image/upload/v1749800067931/80d46cd5-14c3-43df-965e-98057295836c.png
tags: cpp, java, technology, coding, system-design, dsa, techblog, coding-challenge, technical-writing-1, coding-journey, low-level-design, lld, coderarmy, 8weekslldchallenge, payalkumari11

---

> ***NOTE: - <mark>I started my 8-week system design journey with Coder Army. I will be journaling every day, recording what I learn, reflecting on it, and sharing it with my network to help newcomers to system design.</mark>***

## 💠 What is a Payment Gateway?

A **Payment Gateway** is a layer or class that connects your application with the external world (like banks or third-party payment providers).

(Hindi: Payment Gateway ek aisa layer ya class hota hai jo aapke app se bahar jaane wali calls ko handle karta hai — jaise Paytm, Razorpay ke saath connection banana. Baaki application ko pata bhi nahi hota ki call kaise ja rahi hai.)

## 💠Requirements Breakdown

### 📍 Functional Requirements:

* Should support **multiple providers** like Paytm, Razorpay, etc.
    
* Should allow **easy addition** of new gateways in future
    
* Should have a **standardized payment flow** with validations
    
* Must handle **errors & retries** properly
    

### 📍 Non-Functional Requirements:

* Must be **scalable** to support millions of transactions
    
* Should follow **plug-and-play architecture**
    
* Follows **SOLID principles** for clean code
    

## 💠 Design Patterns Used

### 1️⃣ **Strategy Pattern**

Allows easy switching between Paytm Razorpay

🛒 Like choosing UPI or Card at checkout 💳

### 2️⃣ **Template Method Pattern**

Common flow: ✅ Validate → 💰 Process → ✔️ Confirm

(Hindi: Har payment ka ek standard flow hota hai — validate → deduct → confirm — yeh skeleton Template Pattern se banate hain.)

### 3️⃣ **Proxy Pattern**

🔁 Retry logic if payment fails (timeout, crash, etc.)

Automatically retries — user unaware

### 4️⃣ **Factory 🏭 + Singleton 🔐 Pattern**

* 🏭 Create gateways dynamically
    
* 🔐 Ensure global instance for access
    

## 💠Retry Strategies

Retry = must-have in payments

### Types:

* Linear Retry → retry every X secs: 3s, 3s, 3s ⏱️
    
* Exponential Backoff → 1s, 2s, 4s, 8s ⏲️
    

(Hindi: Payment fail ho gaya? System automatically retry karta hai — ya fixed ya growing delay mein.)

## 💠Subscriptions / Recurring

Like Netflix auto-pay monthly

* Scheduled job
    
* Retry connected
    
* Logs for audit
    

(Hindi: Har mahine paisa kaise auto-cut hota hai? That’s recurring flow.)

## 💠Real-Life Example 🍔

You order on Swiggy:

* Choose 💳 Paytm
    
* Swiggy validates ✅ → contacts Paytm 📞 → confirms 💰
    
* Patterns work behind scenes
    

You just tap 🖱️ 'Pay' — system handles rest 🛠️

```java
import java.util.*;

// ----------------------------
// Data structure for payment details
// ----------------------------
class PaymentRequest {
    public String sender;
    public String reciever;
    public double amount;
    public String currency;

    public PaymentRequest(String sender, String reciever, double amt, String curr) {
        this.sender   = sender;
        this.reciever = reciever;
        this.amount   = amt;
        this.currency = curr;
    }
}

// ----------------------------
// Banking System interface and implementations (Strategy for actual payment logic)
// ----------------------------
interface BankingSystem {
    boolean processPayment(double amount);
}

class PaytmBankingSystem implements BankingSystem {
    private Random rand = new Random();

    public PaytmBankingSystem() {}

    @Override
    public boolean processPayment(double amount) {
        // Simulate 20% success
        int r = rand.nextInt(100);
        return r < 80;
    }
}

class RazorpayBankingSystem implements BankingSystem {
    private Random rand = new Random();

    public RazorpayBankingSystem() {}

    @Override
    public boolean processPayment(double amount) {
        System.out.println("[BankingSystem-Razorpay] Processing payment of " + amount + "...");
        // Simulate 90% success
        int r = rand.nextInt(100);
        return r < 90;
    }
}

// ----------------------------
// Abstract base class for Payment Gateway (Template Method Pattern)
// ----------------------------
abstract class PaymentGateway {
    protected BankingSystem bankingSystem;

    public PaymentGateway() {
        this.bankingSystem = null;
    }

    // Template method defining the standard payment flow
    public boolean processPayment(PaymentRequest request) {
        if (!validatePayment(request)) {
            System.out.println("[PaymentGateway] Validation failed for " + request.sender + ".");
            return false;
        }
        if (!initiatePayment(request)) {
            System.out.println("[PaymentGateway] Initiation failed for " + request.sender + ".");
            return false;
        }
        if (!confirmPayment(request)) {
            System.out.println("[PaymentGateway] Confirmation failed for " + request.sender + ".");
            return false;
        }
        return true;
    }

    // Steps to be implemented by concrete gateways
    protected abstract boolean validatePayment(PaymentRequest request);
    protected abstract boolean initiatePayment(PaymentRequest request);
    protected abstract boolean confirmPayment(PaymentRequest request);
}

// ----------------------------
// Concrete Payment Gateway for Paytm
// ----------------------------
class PaytmGateway extends PaymentGateway {
    public PaytmGateway() {
        this.bankingSystem = new PaytmBankingSystem();
    }

    @Override
    protected boolean validatePayment(PaymentRequest request) {
        System.out.println("[Paytm] Validating payment for " + request.sender + ".");
        if (request.amount <= 0 || !"INR".equals(request.currency)) {
            return false;
        }
        return true;
    }

    @Override
    protected boolean initiatePayment(PaymentRequest request) {
        System.out.println("[Paytm] Initiating payment of " + request.amount
                + " " + request.currency + " for " + request.sender + ".");
        return bankingSystem.processPayment(request.amount);
    }

    @Override
    protected boolean confirmPayment(PaymentRequest request) {
        System.out.println("[Paytm] Confirming payment for " + request.sender + ".");
        // Confirmation always succeeds in this simulation
        return true;
    }
}

// ----------------------------
// Concrete Payment Gateway for Razorpay
// ----------------------------
class RazorpayGateway extends PaymentGateway {
    public RazorpayGateway() {
        this.bankingSystem = new RazorpayBankingSystem();
    }

    @Override
    protected boolean validatePayment(PaymentRequest request) {
        System.out.println("[Razorpay] Validating payment for " + request.sender + ".");
        if (request.amount <= 0) {
            return false;
        }
        return true;
    }

    @Override
    protected boolean initiatePayment(PaymentRequest request) {
        System.out.println("[Razorpay] Initiating payment of " + request.amount
                + " " + request.currency + " for " + request.sender + ".");
        return bankingSystem.processPayment(request.amount);
    }

    @Override
    protected boolean confirmPayment(PaymentRequest request) {
        System.out.println("[Razorpay] Confirming payment for " + request.sender + ".");
        // Confirmation always succeeds in this simulation
        return true;
    }
}

// ----------------------------
// Proxy class that wraps a PaymentGateway to add retries (Proxy Pattern)
// ----------------------------
class PaymentGatewayProxy extends PaymentGateway {
    private PaymentGateway realGateway;
    private int retries;

    public PaymentGatewayProxy(PaymentGateway gateway, int maxRetries) {
        this.realGateway = gateway;
        this.retries     = maxRetries;
    }

    @Override
    public boolean processPayment(PaymentRequest request) {
        boolean result = false;
        for (int attempt = 0; attempt < retries; ++attempt) {
            if (attempt > 0) {
                System.out.println("[Proxy] Retrying payment (attempt " + (attempt+1)
                        + ") for " + request.sender + ".");
            }
            result = realGateway.processPayment(request);
            if (result) break;
        }
        if (!result) {
            System.out.println("[Proxy] Payment failed after " + retries
                    + " attempts for " + request.sender + ".");
        }
        return result;
    }

    @Override
    protected boolean validatePayment(PaymentRequest request) {
        return realGateway.validatePayment(request);
    }

    @Override
    protected boolean initiatePayment(PaymentRequest request) {
        return realGateway.initiatePayment(request);
    }

    @Override
    protected boolean confirmPayment(PaymentRequest request) {
        return realGateway.confirmPayment(request);
    }
}

// ----------------------------
// Gateway Factory for creating gateway (Singleton)
// ----------------------------
enum GatewayType {
    PAYTM,
    RAZORPAY
}

class GatewayFactory {
    private static final GatewayFactory instance = new GatewayFactory();

    private GatewayFactory() {}

    public static GatewayFactory getInstance() {
        return instance;
    }

    public PaymentGateway getGateway(GatewayType type) {
        if (type == GatewayType.PAYTM) {
            PaymentGateway paymentGateway = new PaytmGateway();
            return new PaymentGatewayProxy(paymentGateway, 3);
        } else {
            PaymentGateway paymentGateway = new RazorpayGateway();
            return new PaymentGatewayProxy(paymentGateway, 1);
        }
    }
}

// ----------------------------
// Unified API service (Singleton)
// ----------------------------
class PaymentService {
    private static final PaymentService instance = new PaymentService();
    private PaymentGateway gateway;

    private PaymentService() {
        this.gateway = null;
    }

    public static PaymentService getInstance() {
        return instance;
    }

    public void setGateway(PaymentGateway g) {
        this.gateway = g;
    }

    public boolean processPayment(PaymentRequest request) {
        if (gateway == null) {
            System.out.println("[PaymentService] No payment gateway selected.");
            return false;
        }
        return gateway.processPayment(request);
    }
}

// ----------------------------
// Controller class for all client requests (Singleton)
// ----------------------------
class PaymentController {
    private static final PaymentController instance = new PaymentController();

    private PaymentController() {}

    public static PaymentController getInstance() {
        return instance;
    }

    public boolean handlePayment(GatewayType type, PaymentRequest req) {
        PaymentGateway paymentGateway = GatewayFactory.getInstance().getGateway(type);
        PaymentService.getInstance().setGateway(paymentGateway);
        return PaymentService.getInstance().processPayment(req);
    }
}

// ----------------------------
// Main: Client code now goes through controller
// ----------------------------
public class PaymentGatewayApplication {
    public static void main(String[] args) {
        PaymentRequest req1 = new PaymentRequest("Aditya", "Shubham", 1000.0, "INR");

        System.out.println("Processing via Paytm");
        System.out.println("------------------------------");
        boolean res1 = PaymentController.getInstance().handlePayment(GatewayType.PAYTM, req1);
        System.out.println("Result: " + (res1 ? "SUCCESS" : "FAIL"));
        System.out.println("------------------------------\n");

        PaymentRequest req2 = new PaymentRequest("Shubham", "Aditya", 500.0, "USD");

        System.out.println("Processing via Razorpay");
        System.out.println("------------------------------");
        boolean res2 = PaymentController.getInstance().handlePayment(GatewayType.RAZORPAY, req2);
        System.out.println("Result: " + (res2 ? "SUCCESS" : "FAIL"));
        System.out.println("------------------------------");
    }
}
```

![](https://cdn.hashnode.com/res/hashnode/image/upload/v1749833537381/e2b937b8-5dc6-4c95-95f7-400229fe46c3.png align="center")

## 📚 Homework: Retry Strategy + Subscription Flow

### Retry Strategies: Linear & Exponential 🔁

💡 Retry = must-have in payments 💳 to recover from temporary network/server issues.

#### 🔁 Types:

* 📏 **Linear Retry** → retry every 2 seconds for 3 attempts
    
* 📈 **Exponential Backoff** → retry at 1s, 2s, 4s intervals
    

```java
interface RetryStrategy {
    void retry(Runnable task);
}

class LinearRetryHandler implements RetryStrategy {
    public void retry(Runnable task) {
        for (int i = 0; i < 3; i++) {
            try {
                task.run();
                break;
            } catch (Exception e) {
                Thread.sleep(2000); // 2s fixed delay
            }
        }
    }
}

class ExponentialRetryHandler implements RetryStrategy {
    public void retry(Runnable task) {
        int delay = 1000; // Start at 1s
        for (int i = 0; i < 3; i++) {
            try {
                task.run();
                break;
            } catch (Exception e) {
                Thread.sleep(delay);
                delay *= 2; // Double the delay
            }
        }
    }
}
```

![](https://cdn.hashnode.com/res/hashnode/image/upload/v1749795041166/28b117bc-6e02-46e3-be23-97f3ab22f73c.png align="center")

### **Week - 5 (Day-3) Completed ✅ System Design**

> ***NOTE : - A big thanks to my mentors <mark>Rohit Negi</mark> <mark>Sir </mark> and*** [***<mark>Aditya</mark>***](https://www.linkedin.com/in/adityatandon2/) [***<mark>Sir</mark> fo***](https://www.linkedin.com/in/adityatandon2/)***r launching this amazing <mark>8-week course</mark> absolutely <mark>free</mark> on YouTube via <mark>CoderArmy9 </mark> :-*** [***youtube.com/@CoderArmy9***](http://youtube.com/@CoderArmy9) [***. 🙌***](https://www.youtube.com/@CoderArmy9)

👉 **Share this blog with your connections!** Let’s keep learning, growing, and supporting one another on this journey. 🚀

✍️ [***Payal Kumari***](https://www.linkedin.com/in/payalkumari10/) 👩‍💻

![](https://cdn.hashnode.com/res/hashnode/image/upload/v1749633344625/92845077-6d27-443d-9f52-8af59e98014b.jpeg align="center")

**Jai Hind 🇮🇳 | #CoderArmy #LearningInPublic #SystemDesign #TechForAll #MentorshipMatters** [**#8weeksLLdChallenge**](https://www.youtube.com/hashtag/8weekslldchallenge) [**#LowLevelDesign**](https://www.youtube.com/hashtag/lowleveldesign) [**#LLD**](https://www.youtube.com/hashtag/lld) **👩‍💻**

%[https://youtu.be/36FDqIRBGRg?si=ErDmbooTdPX4Ao2p]