---
title: "📅Week-6 (Day-5) - Learn the Flyweight Design Pattern"
seoTitle: "Flyweight Design Pattern Explained"
seoDescription: "Learn the Flyweight Design Pattern to optimize memory and performance in systems with numerous objects, by sharing intrinsic state efficiently"
datePublished: Tue Jun 24 2025 15:21:55 GMT+0000 (Coordinated Universal Time)
cuid: cmcaobogk000p02jj130thxra
slug: week-6-day-5-learn-the-flyweight-design-pattern
cover: https://cdn.hashnode.com/res/hashnode/image/upload/v1750775416855/4774fb2f-699f-4e86-aed0-21aae8a9500a.png
ogImage: https://cdn.hashnode.com/res/hashnode/image/upload/v1750778425981/fc4fa033-f612-4a9b-b08f-5387ae48728b.png
tags: cpp, code, java, technology, coding, system-design, dsa, coding-challenge, technical-writing-1, coding-journey, low-level-design, lld, coderarmy, 8weekslldchallenge, payalkumari11

---

> ***NOTE: - <mark>I started my 8-week system design journey with Coder Army. I will be journaling every day, recording what I learn, reflecting on it, and sharing it with my network to help newcomers to system design.</mark>***

## 💠What is the Flyweight Pattern?

> Flyweight pattern is used to support a large number of fine-grained objects efficiently by sharing common (intrinsic) state.

(Hindi: Jab thousands objects memory le rahe ho, toh common part ko share karke memory bachane ke liye Flyweight pattern use karte hain.)

## 💠 Core Concepts

🟢 **Intrinsic State** — Shared part (same color, shape, sprite)

🔴 **Extrinsic State** — Supplied by client (position, velocity)

Example:

```java

import java.util.Map;
import java.util.HashMap;

class AsteroidFlyweight {
    String color;
    String texture;
    float width, height;

    public AsteroidFlyweight(String color, String texture, float width, float height) {
        this.color = color;
        this.texture = texture;
        this.width = width;
        this.height = height;
    }
}

// Flyweight pool declaration
Map<String, AsteroidFlyweight> flyweightPool = new HashMap<>();
```

💡 *Instead of 1000 asteroid objects with same texture, make 1 flyweight and reuse it with different positions!*

(Hindi: Jaise sab asteroid ka rang shape ek hi hai — toh ek hi object banake, sabko use karwa dete hain.)

## 💠Real-World Example: Asteroids Game (Flyweight in Action)

Building a space game and need to spawn **10,000 asteroids**.

### 📍Naive Way (Bad Approach)

```java
for (int i = 0; i < 10000; i++) {
    Asteroid a = new Asteroid("red", "rough", 50f, 30f, randomPos());
}
```

❌ This creates a **new object** with the same texture and color every time.  
💥 Result: **High memory usage**, possible **RAM crash** on low-end devices.

### 📍Flyweight Way (Smart Approach)

```java
AsteroidFlyweight sharedRed = AsteroidFlyweightFactory.getFlyweight("red_rough_50x30", "red", "rough", 50f, 30f);

for (int i = 0; i < 10000; i++) {
    Asteroid a = new Asteroid(sharedRed, randomPos());
}
```

✅ Here, the appearance of the asteroid is **shared** using the flyweight object.  
🎯 Only the position and state are unique per asteroid

Using the **Flyweight pattern**, you reuse heavy visual properties and avoid duplication, which is perfect for performance-critical applications like games, simulations, or even maps.

> (Hindi: Flyweight pattern ka use karke hum heavy visual properties ko reuse karte hain, jisse same cheez baar-baar create nahi karni padti. Ye approach un cases mein perfect hoti hai jahan performance critical ho — jaise games, simulations, ya maps ke projects.)

## 💠Flyweight Class Structure (UML Friendly)

* `Flyweight`: the shared state class
    
* `FlyweightFactory`: manages pool/map
    
* `Client`: passes extrinsic state (position, speed)
    

📍`AsteroidFlyweight` – the shared state class

```java
class AsteroidFlyweight {
    String texture;
    float width, height;

    public AsteroidFlyweight(String texture, float width, float height) {
        this.texture = texture;
        this.width = width;
        this.height = height;
    }
}
```

#### 📍 `Asteroid` – the client object with extrinsic state

```java
class Position {
    float x, y;

    public Position(float x, float y) {
        this.x = x;
        this.y = y;
    }
}

class Asteroid {
    AsteroidFlyweight flyweight; // shared (intrinsic)
    Position position;           // unique (extrinsic)

    public Asteroid(AsteroidFlyweight flyweight, Position position) {
        this.flyweight = flyweight;
        this.position = position;
    }
}
```

#### 📍`FlyweightFactory` – manages the pool of shared flyweights

```java
import java.util.HashMap;
import java.util.Map;

class FlyweightFactory {
    private final Map<String, AsteroidFlyweight> pool = new HashMap<>();

    public AsteroidFlyweight get(String key, String texture, float width, float height) {
        return pool.computeIfAbsent(key, k -> new AsteroidFlyweight(texture, width, height));
    }
}
```

## 💠UML

![](https://cdn.hashnode.com/res/hashnode/image/upload/v1750777870193/140112c4-a429-409d-a1ee-ec901d7805e3.png align="center")

```java
//********WithFlyWeight.java*****************

import java.util.*;

// Flyweight - Stores INTRINSIC state only
class AsteroidFlyweight {
    // Intrinsic properties (shared among asteroids of same type)
    private int length;          
    private int width;           
    private int weight;          
    private String color;       
    private String texture;      
    private String material;    

    public AsteroidFlyweight(int l, int w, int wt, String col, String tex, String mat) {
        this.length = l;
        this.width = w;
        this.weight = wt;
        this.color = col;
        this.texture = tex;
        this.material = mat;
    }

    public void render(int posX, int posY, int velocityX, int velocityY) {
        System.out.println("Rendering " + color + ", " + texture + ", " + material 
            + " asteroid at (" + posX + "," + posY 
            + ") Size: " + length + "x" + width
            + " Velocity: (" + velocityX + ", " 
            + velocityY + ")");
    }

    public static long getMemoryUsage() {
        return Integer.BYTES * 3 +            // length, width, weight
                40 * 3;                       // Approximate string data
    }
}

// Flyweight Factory
class AsteroidFactory {
    private static Map<String, AsteroidFlyweight> flyweights = new HashMap<>();

    public static AsteroidFlyweight getAsteroid(int length, int width, int weight, 
                                                String color, String texture, String material) {

        String key = length + "_" + width + "_" + weight + "_" + color + "_" + texture + "_" + material;

        if (!flyweights.containsKey(key)) {
            flyweights.put(key, new AsteroidFlyweight(length, width, weight, color, texture, material));
        }

        return flyweights.get(key);
    }

    public static int getFlyweightCount() {
        return flyweights.size();
    }

    public static long getTotalFlyweightMemory() {
        return flyweights.size() * AsteroidFlyweight.getMemoryUsage();
    }

    public static void cleanup() {
        flyweights.clear();
    }
}

// Context - Stores EXTRINSIC state only
class AsteroidContext {
    private AsteroidFlyweight flyweight;
    private int posX, posY; // 8 bytes (position)
    private int velocityX, velocityY; // 8 bytes (velocity)

    public AsteroidContext(AsteroidFlyweight fw, int posX, int posY, int velX, int velY) {
        this.flyweight = fw;
        this.posX = posX;
        this.posY = posY;
        this.velocityX = velX;
        this.velocityY = velY;
    }

    public void render() {
        flyweight.render(posX, posY, velocityX, velocityY);
    }

    public static long getMemoryUsage() {
        return 8 + Integer.BYTES * 4; // approximate pointer + ints
    }
}

class SpaceGameWithFlyweight {
    private List<AsteroidContext> asteroids = new ArrayList<>();

    public void spawnAsteroids(int count) {
        System.out.println("\n=== Spawning " + count + " asteroids ===");

        String[] colors = {"Red", "Blue", "Gray"};
        String[] textures = {"Rocky", "Metallic", "Icy"};
        String[] materials = {"Iron", "Stone", "Ice"};
        int[] sizes = {25, 35, 45};

        for (int i = 0; i < count; i++) {
            int type = i % 3;

            AsteroidFlyweight flyweight = AsteroidFactory.getAsteroid(
                sizes[type], sizes[type], sizes[type] * 10,
                colors[type], textures[type], materials[type]
            );

            asteroids.add(new AsteroidContext(
                flyweight,
                100 + i * 50, // Simple x: 100, 150, 200, 250...
                200 + i * 30, // Simple y: 200, 230, 260, 290...
                1, // All move right with velocity 1
                2  // All move down with velocity 2
            ));
        }

        System.out.println("Created " + asteroids.size() + " asteroid contexts");
        System.out.println("Total flyweight objects: " + AsteroidFactory.getFlyweightCount());
    }

    public void renderAll() {
        System.out.println("\n--- Rendering first 5 asteroids ---");
        for (int i = 0; i < Math.min(5, asteroids.size()); i++) {
            asteroids.get(i).render();
        }
    }

    public long calculateMemoryUsage() {
        long contextMemory = asteroids.size() * AsteroidContext.getMemoryUsage();
        long flyweightMemory = AsteroidFactory.getTotalFlyweightMemory();
        return contextMemory + flyweightMemory;
    }

    public int getAsteroidCount() {
        return asteroids.size();
    }
}

public class WithFlyWeight {
    public static void main(String[] args) {
        final int ASTEROID_COUNT = 1_000_000;

        System.out.println("\nTESTING WITH FLYWEIGHT PATTERN");
        SpaceGameWithFlyweight game = new SpaceGameWithFlyweight();

        game.spawnAsteroids(ASTEROID_COUNT);

        // Show first 5 asteroids to see the pattern
        game.renderAll();

        // Calculate and display memory usage
        long totalMemory = game.calculateMemoryUsage();

        System.out.println("\n=== MEMORY USAGE ===");
        System.out.println("Total asteroids: " + ASTEROID_COUNT);
        System.out.println("Memory per asteroid: " + AsteroidContext.getMemoryUsage() + " bytes");
        System.out.println("Total memory used: " + totalMemory + " bytes");
        System.out.println("Memory in MB: " + (totalMemory / (1024.0 * 1024.0)) + " MB");
    }
}
```

```java
//***********************WithoutFlyWeight.java*********************


import java.util.ArrayList;
import java.util.List;

class Asteroid {
    // Intrinsic properties (same for many asteroids) - DUPLICATED FOR EACH OBJECT
    private int length;                          
    private int width;                          
    private int weight;                          
    private String color;                      
    private String texture;                    
    private String material;                    

    // Extrinsic properties (unique for each asteroid)
    private int posX, posY;                
    private int velocityX, velocityY;            

    public Asteroid(int l, int w, int wt, String col, String tex, 
        String mat, int posX, int posY, int velX, int velY) {
        this.length = l;
        this.width = w;
        this.weight = wt;
        this.color = col;
        this.texture = tex;
        this.material = mat;
        this.posX = posX;
        this.posY = posY;
        this.velocityX = velX;
        this.velocityY = velY;
    }

    public void render() {
        System.out.println("Rendering " + color + ", " + texture + ", " + material 
            + " asteroid at (" + posX + "," + posY 
            + ") Size: " + length + "x" + width
            + " Velocity: (" + velocityX + ", " 
            + velocityY + ")");
    }

    // Calculate approximate memory usage per object
    public static long getMemoryUsage() {
        return Integer.BYTES * 7 +                // length, width, weight, x, y, velocityX, velocityY 
               40 * 3;                            // Approximate string data (assuming average 10 chars each)
    }
}

class SpaceGame {
    private List<Asteroid> asteroids = new ArrayList<>();

    public void spawnAsteroids(int count) {
        System.out.println("\n=== Spawning " + count + " asteroids ===");

        String[] colors = {"Red", "Blue", "Gray"};
        String[] textures = {"Rocky", "Metallic", "Icy"};
        String[] materials = {"Iron", "Stone", "Ice"};
        int[] sizes = {25, 35, 45};

        for (int i = 0; i < count; i++) {
            int type = i % 3;

            asteroids.add(new Asteroid(
                sizes[type], sizes[type], sizes[type] * 10,
                colors[type], textures[type], materials[type],
                100 + i * 50,         // Simple x: 100, 150, 200, 250...
                200 + i * 30,         // Simple y: 200, 230, 260, 290...
                1,                    // All move right with velocity 1
                2                     // All move down with velocity 2
            ));
        }

        System.out.println("Created " + asteroids.size() + " asteroid objects");
    }

    public void renderAll() {
        System.out.println("\n--- Rendering first 5 asteroids ---");
        for (int i = 0; i < Math.min(5, asteroids.size()); i++) {
            asteroids.get(i).render();
        }
    }

    public long calculateMemoryUsage() {
        return asteroids.size() * Asteroid.getMemoryUsage();
    }

    public int getAsteroidCount() {
        return asteroids.size();
    }
}

public class WithoutFlyWeight {
    public static void main(String[] args) {
        final int ASTEROID_COUNT = 1_000_000;

        System.out.println("\n TESTING WITHOUT FLYWEIGHT PATTERN");
        SpaceGame game = new SpaceGame();

        game.spawnAsteroids(ASTEROID_COUNT);

        // Show first 5 asteroids to see the pattern
        game.renderAll();

        // Calculate and display memory usage
        long totalMemory = game.calculateMemoryUsage();

        System.out.println("\n=== MEMORY USAGE ===");
        System.out.println("Total asteroids: " + ASTEROID_COUNT);
        System.out.println("Memory per asteroid: " + Asteroid.getMemoryUsage() + " bytes");
        System.out.println("Total memory used: " + totalMemory + " bytes");
        System.out.println("Memory in MB: " + totalMemory / (1024.0 * 1024.0) + " MB");
    }
}
```

## 💠Performance Comparison

| Scenario | Objects | Memory Used |
| --- | --- | --- |
| ❌ Without Flyweight | 10,000 | High (MBs) |
| ✅ With Flyweight | 10,000 | 80% Less RAM |

✅ Reuse of data structures ✅ Immutable shared objects ✅ Faster performance

## 💠Benefits of Flyweight Pattern:

* Massive memory optimization
    
* Decouple shared & unique data cleanly
    
* Easy reuse with factory methods
    
* Reduced app crash risk
    

## 💠Real-World Use Cases:

* Games like GTA V, Call of Duty
    
* Map tile renderers (Google Maps)
    
* Font character glyph caching
    
* UI systems that render many components
    

(Hindi: Jab same cheez repeat ho rahi ho thousands times — Flyweight use karke memory bachao.)

## 💠Summary

* **Flyweight Pattern** ka kaam: repeat data ko share karna
    
* **Intrinsic State** = shared part (color, texture)
    
* **Extrinsic State** = unique part (position, speed)
    
* **Use when thousands of objects are same internally but differ externally**
    

<mark>Flyweight = memory ka ninja! 🥷</mark>

> When designing performance-heavy systems (games, simulators, rendering tools), this pattern helps scale smoothly. Flyweight keeps your **RAM light**, your **code modular**, and your **objects reusable**.

### **Week - 6 (Day-5) Completed ✅ System Design**

> ***NOTE : - A big thanks to my mentors <mark>Rohit Negi</mark> <mark>Sir </mark> and*** [***<mark>Aditya</mark>***](https://www.linkedin.com/in/adityatandon2/) [***<mark>Sir</mark> fo***](https://www.linkedin.com/in/adityatandon2/)***r launching this amazing <mark>8-week course</mark> absolutely <mark>free</mark> on YouTube via <mark>CoderArmy9 </mark> :-*** [***youtube.com/@CoderArmy9***](http://youtube.com/@CoderArmy9) [***. 🙌***](https://www.youtube.com/@CoderArmy9)

👉 **Share this blog with your connections!** Let’s keep learning, growing, and supporting one another on this journey. 🚀

✍️ [***Payal Kumari***](https://www.linkedin.com/in/payalkumari10/) 👩‍💻

![](https://cdn.hashnode.com/res/hashnode/image/upload/v1750778106876/2b9dc7cf-42f3-46bd-a066-8d52723e2617.jpeg align="center")

**Jai Hind 🇮🇳 | #CoderArmy #LearningInPublic #SystemDesign #TechForAll #MentorshipMatters** [**#8weeksLLdChallenge**](https://www.youtube.com/hashtag/8weekslldchallenge) [**#LowLevelDesign**](https://www.youtube.com/hashtag/lowleveldesign) [**#LLD**](https://www.youtube.com/hashtag/lld) **👩‍💻**

%[https://youtu.be/vNSRcegCO8E?si=mVfa3KckbY7mVbCU]