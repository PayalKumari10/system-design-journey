---
title: "📅Week-8 (Day-3) - Understanding the Visitor Design Pattern in System Design"
seoTitle: "Visitor Design Pattern Simplified"
seoDescription: "Explore the Visitor Design Pattern to enhance your system design skills and add new operations without altering existing structures"
datePublished: Sun Jul 06 2025 11:17:08 GMT+0000 (Coordinated Universal Time)
cuid: cmcrkv40u000002js89zg9a9p
slug: week-8-day-3-understanding-the-visitor-design-pattern-in-system-design
cover: https://cdn.hashnode.com/res/hashnode/image/upload/v1751796685494/e66f9e9e-632f-4ecb-af67-b6bb1d7c0134.png
ogImage: https://cdn.hashnode.com/res/hashnode/image/upload/v1751800479068/3c76ddb7-57ff-4386-b283-47ddf15298a0.png
tags: cpp, code, java, technology, coding, hashnode, system-design, dsa, techblog, coding-challenge, low-level-design, lld, coderarmy, 8weekslldchallenge, payalkumari11

---

> ### ***NOTE: - <mark>I started my 8-week system design journey with Coder Army. I will be journaling every day, recording what I learn, reflecting on it, and sharing it with my network to help newcomers to system design.</mark>***

### **Namaste Developers!**

  
Welcome to another exciting day of the **#8WeeksLLDChallenge** — and today, we’re unlocking a powerful design pattern that brings true flexibility to your codebase: the **Visitor Design Pattern**.

## 💠What is the Visitor Pattern?

The **Visitor Pattern** allows you to add new operations to an existing object structure **without changing its classes**. It separates the operations from the objects on which they operate.

or

**Visitor Pattern** allows you to **add new operations** to an object structure **without modifying its classes.**

(Hindi: Visitor Pattern aapko ye ability deta hai ki aap kisi bhi object structure mein naye kaam (functionality) add kar sako **bina unki existing classes ko badle**.)

## 💠Why & When to Use Visitor Pattern?

✅ You have a stable class structure  
✅ You want to perform different, unrelated operations on those classes  
✅ You don’t want to mess with existing logic  
✅ You believe in **Open/Closed Principle** – “Open for extension, closed for modification”  
✅ You want to separate **algorithms from objects**

## 💠Real-World Analogy – Think Airport Security!

Imagine airport pe aapke bag check karne ke liye alag-alag log hain:

* Security officer scans for dangerous items
    
* Size checker checks luggage size
    
* Lock checker checks if bag is locked
    

You don’t change the **bag** (object). Instead, alag-alag visitors aake **bag ko inspect karte hain** for different purposes.

**This is Visitor Pattern.**

## 💠Double Dispatcher vs Single Dispatcher

### 🔸 Single Dispatcher:

*Only one reference (object) decides which method to call.*

**Example:**

```java
animal.makeSound();
```

👉 `animal` decides at runtime which class's method to run.

(Hindi: Yahan sirf `animal` decide karega ki dog ka `bark()` chalega ya cat ka `meow()`.)

### 🔸 Double Dispatcher:

💡 *Two objects (visitor + element)* ***together*** *decide which method to execute.*

```java
file.accept(visitor);
```

* `file` decides which `visit()` gets called
    
* `visitor` decides how to act on `file`
    

(Hindi: Dono milke decide karte hain kaun sa method chalega - visitor kaunsa hai aur object ka type kya hai.)

**Visitor Pattern is a classic use case of double dispatch.**

## 💠Real-World Analogy — File System Inspections

Imagine you have files of different types:

* Text Files (`.txt`)
    
* Image Files (`.jpg`)
    
* Video Files (`.mp4`)
    

And you want to perform these operations:

* Size calculation
    
* Compression
    
* Virus scanning
    

**Problem:** Should we write all logic inside each file class?

❌ *No! That would violate SRP and OCP.*

✅ Instead, we use **Visitor Pattern**:

* Create visitors for each operation.
    
* Each file "accepts" the visitor to perform the job.
    

## 💠UML

![](https://cdn.hashnode.com/res/hashnode/image/upload/v1751800243320/6c2208dd-02a1-4c21-a2f1-26313fdf7e6b.png align="center")

## 💠Code

```java
class TextFile extends FileSystemItem {
    private String content;
    
    public TextFile(String fileName, String fileContent) {
        super(fileName);
        this.content = fileContent;
    }
    
    public String getContent() {
        return content;
    }
    
    @Override
    public void accept(FileSystemVisitor visitor) {
        visitor.visit(this);
    }
}

class ImageFile extends FileSystemItem {
    
    public ImageFile(String fileName) {
        super(fileName);
    }
    
    @Override
    public void accept(FileSystemVisitor visitor) {
        visitor.visit(this);
    }
}

class VideoFile extends FileSystemItem {
    public VideoFile(String fileName) {
        super(fileName);
    }
    
    @Override
    public void accept(FileSystemVisitor visitor) {
        visitor.visit(this);
    }
}

// Visitor Interface
interface FileSystemVisitor {
    void visit(TextFile file);
    void visit(ImageFile file);
    void visit(VideoFile file);
}

abstract class FileSystemItem {
    protected String name;
    
    public FileSystemItem(String itemName) {
        this.name = itemName;
    }
    
    public String getName() {
        return name;
    }
    
    public abstract void accept(FileSystemVisitor visitor);
}

// 1. Size calculation visitor
class SizeCalculationVisitor implements FileSystemVisitor {
    @Override
    public void visit(TextFile file) {
        System.out.println("Calculating size for TEXT file: " + file.getName());
    }
    
    @Override
    public void visit(ImageFile file) {
        System.out.println("Calculating size for IMAGE file: " + file.getName());
    }
    
    @Override
    public void visit(VideoFile file) {
        System.out.println("Calculating size for VIDEO file: " + file.getName());
    }
}

// 2. Compression Visitor
class CompressionVisitor implements FileSystemVisitor {
    @Override
    public void visit(TextFile file) {
        System.out.println("Compressing TEXT file: " + file.getName());
    }
    
    @Override
    public void visit(ImageFile file) {
        System.out.println("Compressing IMAGE file: " + file.getName());
    }
    
    @Override
    public void visit(VideoFile file) {
        System.out.println("Compressing VIDEO file: " + file.getName());
    }
}

// 3. Virus Scanning Visitor
class VirusScanningVisitor implements FileSystemVisitor {
    @Override
    public void visit(TextFile file) {
        System.out.println("Scanning TEXT file: " + file.getName());
    }
    
    @Override
    public void visit(ImageFile file) {
        System.out.println("Scanning IMAGE file: " + file.getName());
    }
    
    @Override
    public void visit(VideoFile file) {
        System.out.println("Scanning VIDEO file: " + file.getName());
    }
}

public class VisitorPattern {
    public static void main(String[] args) {

        FileSystemItem img1 = new ImageFile("sample.jpg");

        img1.accept(new SizeCalculationVisitor());
        img1.accept(new CompressionVisitor());
        img1.accept(new VirusScanningVisitor());

        FileSystemItem vid1 = new VideoFile("test.mp4");
        vid1.accept(new CompressionVisitor());
    }
}
```

## 💠 Strategy Pattern vs Visitor Pattern

| Feature | Strategy Pattern | Visitor Pattern |
| --- | --- | --- |
| Focus | Change **how** behavior is done | Add **new operations** |
| Example | Fly with wings or jet | fly(), talk(), walk() |
| Behavior | Same behavior, different implementation | Different behaviors altogether |
| Implementation | Follows single dispatch | Follows double dispatch |

### 💠Real-life Analogy:

* **Strategy Pattern:**  
    Flying ka behavior same hai — bas mode change hua:  
    👉 Fly with wings vs Fly with Jet
    
* **Visitor Pattern:**  
    **Completely different actions** on object:  
    👉 walk(), talk(), fly()
    

## 💠Benefits of Visitor Pattern

✅ Easy to add new operations (just add a new visitor!)  
✅ Keeps file structure untouched (OCP followed!)  
✅ Clean separation of logic  
✅ Great for compiler, file systems, rendering engines

## 💠Conclusion

✅ Use Visitor Pattern when:

* You want to **add multiple operations** to objects like files
    
* You don’t want to touch existing object logic
    
* You want clean, extensible, testable code
    

## **💠Final Thoughts**

### **Week - 8 (Day-3) Completed ✅ System Design**

> ***NOTE : - A big thanks to my mentors <mark>Rohit Negi</mark> <mark>Sir </mark> and*** [***<mark>Aditya</mark>***](https://www.linkedin.com/in/adityatandon2/) [***<mark>Sir</mark> fo***](https://www.linkedin.com/in/adityatandon2/)***r launching this amazing <mark>8-week course</mark> absolutely <mark>free</mark> on YouTube via <mark>CoderArmy9 </mark> :-*** [***youtube.com/@CoderArmy9***](http://youtube.com/@CoderArmy9) [***. 🙌***](https://www.youtube.com/@CoderArmy9)

👉 **Share this blog with your connections!** Let’s keep learning, growing, and supporting one another on this journey. 🚀

✍️ [***Payal Kumari***](https://www.linkedin.com/in/payalkumari10/) 👩‍💻

![](https://cdn.hashnode.com/res/hashnode/image/upload/v1751800371278/76b699d0-4d6d-4e1e-8e6e-a6ca3d5412be.jpeg align="center")

**Jai Hind 🇮🇳 | #CoderArmy #LearningInPublic #SystemDesign #TechForAll #MentorshipMatters** [**#8weeksLLdChallenge**](https://www.youtube.com/hashtag/8weekslldchallenge) [**#LowLevelDesign**](https://www.youtube.com/hashtag/lowleveldesign) [**#LLD**](https://www.youtube.com/hashtag/lld) **👩‍💻**  

%[https://youtu.be/DnmsxnlCyl0?si=eP4Ztn3KcST7pZSF]