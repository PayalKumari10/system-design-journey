---
title: "📅Week-5 (Day-4) - How to Design a Discount Coupon Engine: A Complete Guide"
seoTitle: "Designing a Discount Coupon Engine Guide"
seoDescription: "Learn how to design a coupon engine, covering functional requirements, design patterns, and practical examples for efficient discount management"
datePublished: Sat Jun 14 2025 06:56:19 GMT+0000 (Coordinated Universal Time)
cuid: cmbvvuydt000e02k2d35chy6t
slug: week-5-day-4-how-to-design-a-discount-coupon-engine-a-complete-guide
cover: https://cdn.hashnode.com/res/hashnode/image/upload/v1749878201212/aa5b20b6-b84e-4841-8681-bedba4a58b67.png
tags: cpp, java, technology, coding, hashnode, system-design, dsa, coding-challenge, technical-writing-1, coding-journey, low-level-design, lld, coderarmy, 8weekslldchallenge, payalkumari11

---

> ***NOTE: - <mark>I started my 8-week system design journey with Coder Army. I will be journaling every day, recording what I learn, reflecting on it, and sharing it with my network to help newcomers to system design.</mark>***

## 💠 What Is a Coupon Engine?

A **Coupon Engine** is a system that:

* Registers discount offers (like flat ₹100 off, or 20% up to ₹500)
    
* Applies them to products or entire carts 🛒
    
* Follows specific rules (like one-time use, bank-only, stackable or not)
    

(Hindi: Coupon Engine ek system hai jo alag-alag offers ko manage karta hai — jaise bank discount, festive coupon, ya loyalty reward.)

## 💠Functional Requirements

* **Add new coupons at runtime** — Live sale setup ready!
    
* **Cart-level & Product-level Discounts** — Apply at both levels.
    
* **Multiple Coupon Types** — Seasonal offers, loyalty programs, bank discounts
    
* **Supports Flat & Percentage Discounts** — ₹100 off OR 10% off
    
* **Stackable Rules** — One coupon may or may not allow another
    
* **Thread-Safe System** — Multiple users can safely apply coupons simultaneously
    

(Hindi: Aap naye coupon runtime mein add kar sakte ho, cart ya product pe laga sakte ho, aur system thread-safe bhi hoga!)

## 💠Design Patterns Used

### 1️⃣ Strategy Pattern (for Discount Rules)

* Used to support **FlatDiscount**, **PercentageDiscount**, and **CappedPercentageDiscount**.
    
* Just like choosing a different formula depending on the offer type.
    

(Hindi: Har coupon ka logic alag hota hai — isliye strategy pattern se alag-alag classes mein use define karte hain.)

### 2️⃣ Chain of Responsibility (for Coupon Sequence)

* Coupons form a **chain**, so they’re applied in order.
    
* Some can stop the chain, some pass to the next.
    

*Example: Apply a seasonal coupon, then bank offer if allowed.*

### 3️⃣ Singleton Pattern (for CouponManager)

* Manages the registration and execution of coupons.
    
* Ensures only one global manager (thread-safe)
    

## 🛒 Sample Cart Flow:

* Original total: ₹2,000
    
* Apply 10% bank offer → ₹200 off
    
* Apply flat ₹100 coupon → total now ₹1,700
    

👉 CouponManager keeps track of which offers were applied.

## 💠Real-Life Example:

You’re checking out:

* Flat ₹150 off from Paytm
    
* Plus 20% up to ₹200 from SBI Card
    
* Cart applies both (if allowed) — *discount stack!*
    

(Hindi: Aap cart mein ₹150 ka flat aur 20% SBI discount ek sath apply kar rahe ho — bas wahi chain of coupons hai!)

![](https://cdn.hashnode.com/res/hashnode/image/upload/v1749884031406/079fd0ba-f9a8-4036-b1ac-3a81f8d9634d.png align="center")

```java
import java.util.*;
import java.util.concurrent.locks.*;

// ----------------------------
// Discount Strategy (Strategy Pattern)
// ----------------------------
interface DiscountStrategy {
    double calculate(double baseAmount);
}

class FlatDiscountStrategy implements DiscountStrategy {
    private double amount;

    public FlatDiscountStrategy(double amt) {
        this.amount = amt;
    }

    @Override
    public double calculate(double baseAmount) {
        return Math.min(amount, baseAmount);
    }
}

class PercentageDiscountStrategy implements DiscountStrategy {
    private double percent;

    public PercentageDiscountStrategy(double pct) {
        this.percent = pct;
    }

    @Override
    public double calculate(double baseAmount) {
        return (percent / 100.0) * baseAmount;
    }
}

class PercentageWithCapStrategy implements DiscountStrategy {
    private double percent;
    private double cap;

    public PercentageWithCapStrategy(double pct, double capVal) {
        this.percent = pct;
        this.cap     = capVal;
    }

    @Override
    public double calculate(double baseAmount) {
        double disc = (percent / 100.0) * baseAmount;
        return disc > cap ? cap : disc;
    }
}

enum StrategyType {
    FLAT,
    PERCENT,
    PERCENT_WITH_CAP
}

// ----------------------------
// DiscountStrategyManager (Singleton)
// ----------------------------
class DiscountStrategyManager {
    private static DiscountStrategyManager instance;

    private DiscountStrategyManager() {}

    public static synchronized DiscountStrategyManager getInstance() {
        if (instance == null) {
            instance = new DiscountStrategyManager();
        }
        return instance;
    }

    public DiscountStrategy getStrategy(StrategyType type, double param1, double param2) {
        switch(type) {
            case FLAT:
                return new FlatDiscountStrategy(param1);
            case PERCENT:
                return new PercentageDiscountStrategy(param1);
            case PERCENT_WITH_CAP:
                return new PercentageWithCapStrategy(param1, param2);
            default:
                return null;
        }
    }
}

// ----------------------------
// Assume existing Cart and Product classes
// ----------------------------
class Product {
    private String name;
    private String category;
    private double price;

    public Product(String name, String category, double price) {
        this.name     = name;
        this.category = category;
        this.price    = price;
    }

    public String getName() {
        return name;
    }
    public String getCategory() {
        return category;
    }
    public double getPrice() {
        return price;
    }
}

class CartItem {
    private Product product;
    private int quantity;

    public CartItem(Product prod, int qty) {
        this.product  = prod;
        this.quantity = qty;
    }

    public double itemTotal() {
        return product.getPrice() * quantity;
    }

    public Product getProduct() {
        return product;
    }
}

class Cart {
    private List<CartItem> items = new ArrayList<>();
    private double originalTotal = 0.0;
    private double currentTotal  = 0.0;
    private boolean loyaltyMember;
    private String paymentBank;

    public Cart() {
        this.loyaltyMember = false;
        this.paymentBank   = "";
    }

    public void addProduct(Product prod, int qty) {
        CartItem item = new CartItem(prod, qty);
        items.add(item);
        originalTotal += item.itemTotal();
        currentTotal  += item.itemTotal();
    }

    public double getOriginalTotal() {
        return originalTotal;
    }

    public double getCurrentTotal() {
        return currentTotal;
    }

    public void applyDiscount(double d) {
        currentTotal -= d;
        if (currentTotal < 0) {
            currentTotal = 0;
        }
    }

    public void setLoyaltyMember(boolean member) {
        this.loyaltyMember = member;
    }

    public boolean isLoyaltyMember() {
        return loyaltyMember;
    }

    public void setPaymentBank(String bank) {
        this.paymentBank = bank;
    }

    public String getPaymentBank() {
        return paymentBank;
    }

    public List<CartItem> getItems() {
        return items;
    }
}

// ----------------------------
// Coupon base class (Chain of Responsibility)
// ----------------------------
abstract class Coupon {
    private Coupon next;

    public Coupon() {
        this.next = null;
    }

    public void setNext(Coupon nxt) {
        this.next = nxt;
    }

    public Coupon getNext() {
        return next;
    }

    public void applyDiscount(Cart cart) {
        if (isApplicable(cart)) {
            double discount = getDiscount(cart);
            cart.applyDiscount(discount);
            System.out.println(name() + " applied: " + discount);
            if (!isCombinable()) {
                return;
            }
        }
        if (next != null) {
            next.applyDiscount(cart);
        }
    }

    public abstract boolean isApplicable(Cart cart);
    public abstract double getDiscount(Cart cart);
    public boolean isCombinable() {
        return true;
    }
    public abstract String name();
}

// ----------------------------
// Concrete Coupons
// ----------------------------
class SeasonalOffer extends Coupon {
    private double percent;
    private String category;
    private DiscountStrategy strat;

    public SeasonalOffer(double pct, String cat) {
        this.percent  = pct;
        this.category = cat;
        this.strat    = DiscountStrategyManager.getInstance()
                            .getStrategy(StrategyType.PERCENT, percent, 0.0);
    }

    @Override
    public boolean isApplicable(Cart cart) {
        for (CartItem item : cart.getItems()) {
            if (item.getProduct().getCategory().equals(category)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public double getDiscount(Cart cart) {
        double subtotal = 0.0;
        for (CartItem item : cart.getItems()) {
            if (item.getProduct().getCategory().equals(category)) {
                subtotal += item.itemTotal();
            }
        }
        return strat.calculate(subtotal);
    }

    @Override
    public String name() {
        return "Seasonal Offer " + (int)percent + "% off " + category;
    }
}

class LoyaltyDiscount extends Coupon {
    private double percent;
    private DiscountStrategy strat;

    public LoyaltyDiscount(double pct) {
        this.percent = pct;
        this.strat   = DiscountStrategyManager.getInstance()
                            .getStrategy(StrategyType.PERCENT, percent, 0.0);
    }

    @Override
    public boolean isApplicable(Cart cart) {
        return cart.isLoyaltyMember();
    }

    @Override
    public double getDiscount(Cart cart) {
        return strat.calculate(cart.getCurrentTotal());
    }

    @Override
    public String name() {
        return "Loyalty Discount " + (int)percent + "% off";
    }
}

class BulkPurchaseDiscount extends Coupon {
    private double threshold;
    private double flatOff;
    private DiscountStrategy strat;

    public BulkPurchaseDiscount(double thr, double off) {
        this.threshold = thr;
        this.flatOff   = off;
        this.strat     = DiscountStrategyManager.getInstance()
                             .getStrategy(StrategyType.FLAT, flatOff, 0.0);
    }

    @Override
    public boolean isApplicable(Cart cart) {
        return cart.getOriginalTotal() >= threshold;
    }

    @Override
    public double getDiscount(Cart cart) {
        return strat.calculate(cart.getCurrentTotal());
    }

    @Override
    public String name() {
        return "Bulk Purchase Rs " + (int)flatOff + " off over " + (int)threshold;
    }
}

class BankingCoupon extends Coupon {
    private String bank;
    private double minSpend;
    private double percent;
    private double offCap;
    private DiscountStrategy strat;

    public BankingCoupon(String b, double ms, double percent, double offCap) {
        this.bank    = b;
        this.minSpend= ms;
        this.percent = percent;
        this.offCap  = offCap;
        this.strat   = DiscountStrategyManager.getInstance()
                            .getStrategy(StrategyType.PERCENT_WITH_CAP, percent, offCap);
    }

    @Override
    public boolean isApplicable(Cart cart) {
        return cart.getPaymentBank().equals(bank)
            && cart.getOriginalTotal() >= minSpend;
    }

    @Override
    public double getDiscount(Cart cart) {
        return strat.calculate(cart.getCurrentTotal());
    }

    @Override
    public String name() {
        return bank + " Bank Rs " + (int)percent + " off upto " + (int)offCap;
    }
}

// ----------------------------
// CouponManager (Singleton)
// ----------------------------
class CouponManager {
    private static CouponManager instance;
    private Coupon head;
    private final Lock lock = new ReentrantLock();

    private CouponManager() {
        this.head = null;
    }

    public static synchronized CouponManager getInstance() {
        if (instance == null) {
            instance = new CouponManager();
        }
        return instance;
    }

    public void registerCoupon(Coupon coupon) {
        lock.lock();
        try {
            if (head == null) {
                head = coupon;
            } else {
                Coupon cur = head;
                while (cur.getNext() != null) {
                    cur = cur.getNext();
                }
                cur.setNext(coupon);
            }
        } finally {
            lock.unlock();
        }
    }

    public List<String> getApplicable(Cart cart) {
        lock.lock();
        try {
            List<String> res = new ArrayList<>();
            Coupon cur = head;
            while (cur != null) {
                if (cur.isApplicable(cart)) {
                    res.add(cur.name());
                }
                cur = cur.getNext();
            }
            return res;
        } finally {
            lock.unlock();
        }
    }

    public double applyAll(Cart cart) {
        lock.lock();
        try {
            if (head != null) {
                head.applyDiscount(cart);
            }
            return cart.getCurrentTotal();
        } finally {
            lock.unlock();
        }
    }
}

// ----------------------------
// Main: Client code
// ----------------------------
public class DiscountCoupon {
    public static void main(String[] args) {
        CouponManager mgr = CouponManager.getInstance();
        mgr.registerCoupon(new SeasonalOffer(10, "Clothing"));
        mgr.registerCoupon(new LoyaltyDiscount(5));
        mgr.registerCoupon(new BulkPurchaseDiscount(1000, 100));
        mgr.registerCoupon(new BankingCoupon("ABC", 2000, 15, 500));

        Product p1 = new Product("Winter Jacket", "Clothing", 1000);
        Product p2 = new Product("Smartphone", "Electronics", 20000);
        Product p3 = new Product("Jeans", "Clothing", 1000);
        Product p4 = new Product("Headphones", "Electronics", 2000);

        Cart cart = new Cart();
        cart.addProduct(p1, 1);
        cart.addProduct(p2, 1);
        cart.addProduct(p3, 2);
        cart.addProduct(p4, 1);
        cart.setLoyaltyMember(true);
        cart.setPaymentBank("ABC");

        System.out.println("Original Cart Total: " + cart.getOriginalTotal() + " Rs");

        List<String> applicable = mgr.getApplicable(cart);
        System.out.println("Applicable Coupons:");
        for (String name : applicable) {
            System.out.println(" - " + name);
        }

        double finalTotal = mgr.applyAll(cart);
        System.out.println("Final Cart Total after discounts: " + finalTotal + " Rs");
    }
}
```

### **Week - 5 (Day-4) Completed ✅ System Design**

> ***NOTE : - A big thanks to my mentors <mark>Rohit Negi</mark> <mark>Sir </mark> and*** [***<mark>Aditya</mark>***](https://www.linkedin.com/in/adityatandon2/) [***<mark>Sir</mark> fo***](https://www.linkedin.com/in/adityatandon2/)***r launching this amazing <mark>8-week course</mark> absolutely <mark>free</mark> on YouTube via <mark>CoderArmy9 </mark> :-*** [***youtube.com/@CoderArmy9***](http://youtube.com/@CoderArmy9) [***. 🙌***](https://www.youtube.com/@CoderArmy9)

👉 **Share this blog with your connections!** Let’s keep learning, growing, and supporting one another on this journey. 🚀

✍️ [***Payal Kumari***](https://www.linkedin.com/in/payalkumari10/) 👩‍💻

![](https://cdn.hashnode.com/res/hashnode/image/upload/v1749879577248/b3f1fe9d-485d-44e7-a81f-db58f87a1c5b.jpeg align="center")

**Jai Hind 🇮🇳 | #CoderArmy #LearningInPublic #SystemDesign #TechForAll #MentorshipMatters** [**#8weeksLLdChallenge**](https://www.youtube.com/hashtag/8weekslldchallenge) [**#LowLevelDesign**](https://www.youtube.com/hashtag/lowleveldesign) [**#LLD**](https://www.youtube.com/hashtag/lld) **👩‍💻**

%[https://youtu.be/jbVevoGN_pM?si=LuL-L9UNEN9gJUD4]