---
title: "📅Week 2: Practice Low-Level Design with Problems for Day 6 and 7."
seoTitle: "Low-Level Design Practice: Week-2 (Days 6 & 7)"
seoDescription: "Week 2: Practice Low-Level Design with Problems for Day 6 and 7"
datePublished: Mon May 26 2025 20:38:09 GMT+0000 (Coordinated Universal Time)
cuid: cmb5junik000b09l46c9ccmki
slug: week-2-practice-low-level-design-with-problems-for-day-6-and-7
cover: https://cdn.hashnode.com/res/hashnode/image/upload/v1748270908717/76894333-0a7f-4c43-a6bb-1937320cb5db.png
ogImage: https://cdn.hashnode.com/res/hashnode/image/upload/v1748291824500/2fcc3e5d-642d-47ac-ad98-b16a9e7abc4d.png
tags: cpp, blogging, java, technology, hashnode, system-design, dsa, coder, coding-challenge, technical-writing-1, lld, coderarmy, lowleveldesign, 8weekslldchallenge, payalkumari11

---

## 📌 Introduction

Welcome to **Week 2** of my Low-Level Design (LLD) journey!  
In **Day 6 and Day 7**, I focused on applying the **SOLID principles** by solving a hands-on design problem:  
🎯 **Redesigning an Image Editor system** using clean object-oriented practices.

This blog post walks through the thought process, class structure, code, and implementation in Java using **VS Code**.

# <mark>📍Qs 1)</mark>

![](https://cdn.hashnode.com/res/hashnode/image/upload/v1748271272334/240b6d5e-eed7-4f29-a2dd-d2215e03d596.png align="center")

## 💠 Step 1: **Understand the Problem**

From the image:

* The `ImageEditor` class has too many responsibilities:
    
    * Capturing an image
        
    * Editing operations (crop, sharpen, apply color)
        
    * Saving the image
        
* This violates the **Single Responsibility Principle (SRP)**.
    
* Also, if we add a new filter or save to a different database, we'll have to **modify** this class — violating **Open/Closed Principle (OCP)**.
    

### 💠 Step 2: **Apply SOLID Principles**

We'll redesign the system using the following:

| Principle | What to Apply |
| --- | --- |
| **S - SRP** | Each class should have only one reason to change. |
| **O - OCP** | Add new filters or saving mechanisms **without modifying** existing code. |
| **L - LSP** | Subclasses should be replaceable with base types. |
| **I - ISP** | Don't force classes to implement unnecessary methods. |
| **D - DIP** | High-level modules should depend on abstractions, not concrete implementations. |

### ✅ Solution Using SOLID:

1. **SRP**:
    
    * Split responsibilities: `Camera` → capture, `Editor` → apply filters, `Storage` → save
        
2. **OCP**:
    
    * Introduced interfaces (`Filter`, `Storage`) to allow new features without changing existing code
        
3. **DIP**:
    
    * `ImageEditor` and `Main` depend on abstractions, not concrete classes
        

![](https://cdn.hashnode.com/res/hashnode/image/upload/v1748285158763/2768e664-510a-4fc8-b9c1-31f21db5b1ce.png align="center")

## 💠 Code: My GitHub Repo

🔗 GitHub: [Image Editor Using SOLID Principles](https://github.com/PayalKumari10/system-design-journey.git)

# <mark>📍Qs 2)</mark>

![](https://cdn.hashnode.com/res/hashnode/image/upload/v1748291720225/8bdf8aa9-e7a1-4a11-943d-818d32c50884.png align="center")

## 💠 Step 1: Understand the Problem Statement

The task is to build a **flexible sorting module** that supports multiple sorting strategies such as:

* ✅ **QuickSort**: Standard & Randomized
    
* ✅ **MergeSort**: Normal & In-Place
    
* ✅ Should allow for easy **addition of new algorithms** (like HeapSort)
    
* ✅ Should allow for **order direction** (Ascending / Descending)
    
* ✅ Should **follow OOP best practices**, especially the **Strategy Pattern**
    

## 💠 Step 2: Apply SOLID Principles

| Principle | Applied How? |
| --- | --- |
| **S - SRP** | Each class has a single responsibility: `SortHandler` delegates, strategies perform actual sorting. |
| **O - OCP** | Easy to add new sort strategies (HeapSort, BubbleSort, etc.) without modifying existing classes. |
| **L - LSP** | All strategies (`QuickSortStrategy`, `MergeSortStrategy`) can be used wherever `SortStrategy` is expected. |
| **I - ISP** | `SortStrategy` interface contains only `sort(...)` — no unnecessary methods forced. |
| **D - DIP** | `SortHandler` depends on abstraction (`SortStrategy`), not concrete classes. |

![](https://cdn.hashnode.com/res/hashnode/image/upload/v1748291258696/e731ba87-3f13-44f0-bfc7-8420ae2bc37e.png align="center")

### ✅ SOLID Principles Applied (Sorting Context LLD)

🔹 **SRP (Single Responsibility)**  
Each class has one job:

* `SortHandler`: Delegates sorting
    
* `QuickSortStrategy`, `MergeSortStrategy`: Implement specific sorting logic
    

🔹 **OCP (Open/Closed)**  
Add new strategies (e.g., `HeapSort`) without changing existing code.  
→ Thanks to the `SortStrategy` interface.

🔹 **LSP (Liskov Substitution)**  
All strategies (`QuickSort`, `MergeSort`) can be used interchangeably via `SortStrategy`.

🔹 **ISP (Interface Segregation)**  
The `SortStrategy` interface is minimal — only one method: `sort(int[], boolean)`.  
No unnecessary code for strategy classes.

🔹 **DIP (Dependency Inversion)**  
`SortHandler` depends on the **interface** `SortStrategy`, not concrete classes.  
→ Promotes loose coupling and easy testing.

## 💠 Code: My GitHub Repo

🔗 GitHub: [Image Editor Using SOLID Principles](https://github.com/PayalKumari10/system-design-journey.git)

### **Week -2 (Day 6 & 7) Completed ✅ System Design**

> ***NOTE : - A big thanks to my mentors*** [***<mark>Rohit Negi</mark>***](https://www.linkedin.com/in/rohit-negi9/) ***<mark>Sir </mark> and*** [***<mark>Aditya</mark>***](https://www.linkedin.com/in/adityatandon2/) [***<mark>Sir</mark> fo***](https://www.linkedin.com/in/adityatandon2/)***r launching this amazing <mark>8-week course</mark> absolutely <mark>free</mark> on YouTube via <mark>CoderArmy9 </mark> :-*** [***https://www.youtube.com/@CoderArmy9🙌***](https://www.youtube.com/@CoderArmy9)

👉 **Share this blog with your connections!** Let’s keep learning, growing, and supporting one another on this journey. 🚀

✍️ [***Payal Kumari***](https://www.linkedin.com/in/payalkumari10/) 👩‍💻

![](https://cdn.hashnode.com/res/hashnode/image/upload/v1748291565934/dce7711c-36ad-409d-998a-81ad1cbb4bb1.jpeg align="center")

**Jai Hind 🇮🇳 | #CoderArmy #LearningInPublic #SystemDesign #TechForAll #MentorshipMatters** [**#8weeksLLdChallenge**](https://www.youtube.com/hashtag/8weekslldchallenge) [**#LowLevelDesign** **#Code #LLD**](https://www.youtube.com/hashtag/8weekslldchallenge)

%[https://www.youtube.com/@CoderArmy9/playlists]