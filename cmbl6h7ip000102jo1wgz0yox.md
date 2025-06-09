---
title: "📅Week-4 (Day 5) - Master the Template Method Pattern"
seoTitle: "Template Method Pattern Mastery Guide"
seoDescription: "Learn the Template Method Pattern for adaptable algorithms, enhancing system design skills with practical examples and use cases"
datePublished: Fri Jun 06 2025 19:08:06 GMT+0000 (Coordinated Universal Time)
cuid: cmbl6h7ip000102jo1wgz0yox
slug: week-4-day-5-master-the-template-method-pattern
cover: https://cdn.hashnode.com/res/hashnode/image/upload/v1749229873511/3cee3d0c-ba53-4481-b4a8-3fbcfe64f60d.png
ogImage: https://cdn.hashnode.com/res/hashnode/image/upload/v1749236731405/802f012c-7706-4023-9db3-812ad649a9fb.png
tags: cpp, code, java, technology, coding, system-design, dsa, techblog, coding-challenge, technical-writing-1, coding-journey, low-level-design, lld, coderarmy, payalkumari11

---

> ***NOTE: - <mark>I started my 8-week system design journey with Coder Army. I will be journaling every day, recording what I learn, reflecting on it, and sharing it with my network to help newcomers to system design.</mark>***

---

## 💠 What is the Template Method Pattern?

Template Method Pattern defines the **skeleton 🩻🦴of an algorithm** in a **base class**, while allowing **subclasses** to override specific steps — without changing the algorithm’s structure.

(Hindi: Template Method Pattern ek aise algorithm ka structure define karta hai jisme kuch steps base class mein fix hote hain aur kuch steps subclasses mein customize kiye jaate hain — bina overall structure badle.)

## 💠When to Use It?

Use this pattern when:

* The overall **workflow** stays the same, but some steps **differ** per implementation.
    
* You want to avoid **duplicating code** in similar algorithms.
    
* You want to allow **hooks** (optional steps) for customization.
    

## 💠Real-Life Example

Imagine you're baking a cake 🍰

* Steps like **preheat oven**, **mix ingredients**, and **bake** stay the same.
    
* But the step **"add flavor"** varies — one cake has chocolate 🍫, another has vanilla 🌼.
    

So your base recipe stays fixed, and only the flavors differ — that’s **Template Method**.

(Hindi: Sochiye aap cake bana rahe ho, har baar baking steps same hote hain, sirf flavor badalta hai — yahi Template Pattern karta hai.)

## 💠Real-World Use Case: ML Model Pipelines

Think of a system where you need to train multiple types of models (Neural Nets, Decision Trees, SVMs, etc.).

Rather than writing separate pipelines from scratch, use a **Template Method** that:

* Loads data
    
* Builds a model (custom per type)
    
* Evaluates it
    

(Hindi: Agar aapko alag-alag ML models train karne hain, toh har model ke liye naye steps likhne ke bajaye, ek common framework banao jo steps follow kare — aur sirf model ka step alag ho.)

## 💠Benefits of Template Method Pattern

* **Code Reusability**: Share core logic across classes
    
* **Customizability**: Override specific parts
    
* **Control the Algorithm**: Ensure important steps aren’t skipped
    
* **Clean Code**: Reduce duplication
    

## 💠Best Practices (In Simple Words)

* Use `final` keyword for the template method to avoid alteration
    
* Use abstract methods for steps that **must be implemented**
    
* Use hook methods for **optional customization**
    

## 💠 Summary

* Template Pattern ka matlab:
    

> Ek skeleton define karo jisme fixed steps ho, aur kuch steps subclasses customize kar saken.

🎂 Jaise cake recipe mein sab kuch same ho, bas flavor alag ho — waise hi software workflows mein bhi kuch cheezein fix hoti hain.

* Use cases:
    

> ML pipelines, UI rendering templates, testing frameworks, game engines!

## 💠UML Design

![](https://cdn.hashnode.com/res/hashnode/image/upload/v1749236471110/1c5e1f9e-4dfe-4232-844d-e25e9f08e34b.png align="center")

![](https://cdn.hashnode.com/res/hashnode/image/upload/v1749233803795/467537ad-6a52-4db8-b0fa-106ebd0b2318.png align="center")

## 💠Code

```java
// ───────────────────────────────────────────────────────────
// 1. Base class defining the template method
// ───────────────────────────────────────────────────────────
abstract class ModelTrainer {
    // The template method — final so subclasses can’t change the sequence
    public final void trainPipeline(String dataPath) {
        loadData(dataPath);
        preprocessData();
        trainModel();      // subclass-specific
        evaluateModel();   // subclass-specific
        saveModel();       // subclass-specific or default
    }

    protected void loadData(String path) {
        System.out.println("[Common] Loading dataset from " + path);
        // e.g., read CSV, images, etc.
    }

    protected void preprocessData() {
        System.out.println("[Common] Splitting into train/test and normalizing");
    }

    protected abstract void trainModel();
    protected abstract void evaluateModel();

    // Provide a default save, but subclasses can override if needed
    protected void saveModel() {
        System.out.println("[Common] Saving model to disk as default format");
    }
}

// ───────────────────────────────────────────────────────────
// 2. Concrete subclass: Neural Network
// ───────────────────────────────────────────────────────────
class NeuralNetworkTrainer extends ModelTrainer {
    @Override
    protected void trainModel() {
        System.out.println("[NeuralNet] Training Neural Network for 100 epochs");
        // pseudo-code: forward/backward passes, gradient descent...
    }

    @Override
    protected void evaluateModel() {
        System.out.println("[NeuralNet] Evaluating accuracy and loss on validation set");
    }

    @Override
    protected void saveModel() {
        System.out.println("[NeuralNet] Serializing network weights to .h5 file");
    }
}

// ───────────────────────────────────────────────────────────
// 3. Concrete subclass: Decision Tree
// ───────────────────────────────────────────────────────────
class DecisionTreeTrainer extends ModelTrainer {
    // Use the default preprocessData() (train/test split + normalize)

    @Override
    protected void trainModel() {
        System.out.println("[DecisionTree] Building decision tree with max_depth=5");
        // pseudo-code: recursive splitting on features...
    }

    @Override
    protected void evaluateModel() {
        System.out.println("[DecisionTree] Computing classification report (precision/recall)");
    }
    // use the default saveModel()
}

// ───────────────────────────────────────────────────────────
// 4. Usage
// ───────────────────────────────────────────────────────────
public class TemplateMethodPattern {
    public static void main(String[] args) {
        System.out.println("=== Neural Network Training ===");
        ModelTrainer nnTrainer = new NeuralNetworkTrainer();
        nnTrainer.trainPipeline("data/images/");

        System.out.println("\n=== Decision Tree Training ===");
        ModelTrainer dtTrainer = new DecisionTreeTrainer();
        dtTrainer.trainPipeline("data/iris.csv");
    }
}
```

### **Week - 4 (Day-5) Completed ✅ System Design**

> ***NOTE : - A big thanks to my mentors <mark>Rohit Negi</mark> <mark>Sir </mark> and*** [***<mark>Aditya</mark>***](https://www.linkedin.com/in/adityatandon2/) [***<mark>Sir</mark> fo***](https://www.linkedin.com/in/adityatandon2/)***r launching this amazing <mark>8-week course</mark> absolutely <mark>free</mark> on YouTube via <mark>CoderArmy9 </mark> :-*** [***youtube.com/@CoderArmy9***](http://youtube.com/@CoderArmy9) [***. 🙌***](https://www.youtube.com/@CoderArmy9)

👉 **Share this blog with your connections!** Let’s keep learning, growing, and supporting one another on this journey. 🚀

✍️ [***Payal Kumari***](https://www.linkedin.com/in/payalkumari10/) 👩‍💻

![](https://cdn.hashnode.com/res/hashnode/image/upload/v1749236575540/027ca7b3-20ef-4970-a0b7-d48f766e447f.jpeg align="center")

**Jai Hind 🇮🇳 | #CoderArmy #LearningInPublic #SystemDesign #TechForAll #MentorshipMatters** [**#8weeksLLdChallenge**](https://www.youtube.com/hashtag/8weekslldchallenge) [**#LowLevelDesign** **#Code #LLD**](https://www.youtube.com/hashtag/8weekslldchallenge) [**#OOP**](https://www.youtube.com/hashtag/lowleveldesign)

%[https://youtu.be/8-vE_bmEt18?si=UpyoRQ7KLkZQvioH]