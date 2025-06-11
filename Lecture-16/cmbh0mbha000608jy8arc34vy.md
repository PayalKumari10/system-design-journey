---
title: "📅 Week-4 (Day-1) - Understanding the Adapter Design Pattern: Real-World Examples and Code 👩‍💻 What is the Adapter Design Pattern?"
seoTitle: "Adapter Design Pattern Explained with Examples"
seoDescription: "Learn about the Adapter Design Pattern with real-world examples and code, enabling incompatible interfaces to work together seamlessly"
datePublished: Tue Jun 03 2025 21:13:02 GMT+0000 (Coordinated Universal Time)
cuid: cmbh0mbha000608jy8arc34vy
slug: week-4-day-1-understanding-the-adapter-design-pattern-real-world-examples-and-code-what-is-the-adapter-design-pattern
cover: https://cdn.hashnode.com/res/hashnode/image/upload/v1748981616573/bc7afe3a-e0a3-466f-8a2b-3536f1933509.png
ogImage: https://cdn.hashnode.com/res/hashnode/image/upload/v1748985134621/4942a631-ce32-4589-acf3-c1af29efb701.png
tags: cpp, code, java, technology, coding, system-design, dsa, techblog, coding-challenge, technical-writing-1, coding-journey, low-level-design, lld, coderarmy, payalkumari11

---

> ***NOTE: - <mark>I </mark>*** <mark>started my 8-week system design journey with Coder Army. I will be journaling every day, recording what I learn, reflecting on it, and sharing it with my network to help newcomers to system design.</mark>

---

## 💠What is the Adapter Design Pattern?

Adapter converts the interface of a class into another interface that the client expects. It allows classes with incompatible interfaces to work together.

(Hindi: “Adapter ek class ke interface ko dusre interface mein convert karta hai jo client expect karta hai. Isse aise classes jo normally saath kaam nahi kar sakti, wo bhi ek saath kaam kar paati hain”)

## 💠Real-Life Examples to Understand

🧳 **Example 1**: Imagine you're traveling abroad. India ke socket aur abroad ke socket different hote hain, right? Toh kya karte ho? You carry an *adapter plug* so that your charger (India socket) can fit into their system. That’s an adapter at work! 🔌

📱 **Example 2**: You have a Type-C phone charger but your cable is Type-B. Instead of changing your entire phone or cable, you use a converter cable that connects both — that's another real-world adapter example.

## 💠Why is it important?

In real-world software projects, you often need to integrate **third-party APIs or legacy systems**. Sometimes, those systems return data in formats your app doesn't understand — like XML instead of JSON. But you can't just change the third-party system. So, what do you do?

👉 You write an **Adapter** — a bridge between their format and your expected format.

## 💠Real-World Use Case: XML to JSON Adapter

#### 🔹 Scenario:

You have a third-party service giving you data in XML format. But your application only understands JSON. Changing the app or the third-party is not an option.

➡️ So, you create an **XmlToJsonAdapter** to translate XML into JSON.

### 💠UML Class Diagram Explanation (Simplified):

1. **Target Interface**: What the client expects (JSON provider)
    
2. **Adaptee**: The third-party service (gives XML)
    
3. **Adapter**: Implements the Target Interface, wraps the Adaptee, and converts XML → JSON
    

## <mark>Example UML Diagram :</mark>

![](https://cdn.hashnode.com/res/hashnode/image/upload/v1748984519623/272b0afe-5851-4e68-9cd7-1fc6f5b0e0cf.png align="center")

## <mark>Standard UML Diagram</mark>

![](https://cdn.hashnode.com/res/hashnode/image/upload/v1748984836338/6f0a37d6-b746-4272-83ab-2f5f563b0fa4.png align="center")

## <mark>Code</mark>

```java


// 1. Target interface expected by the client
interface IReports {
    // now takes the raw data string and returns JSON
    String getJsonData(String data);
}

// 2. Adaptee: provides XML data from a raw input
class XmlDataProvider {
    // Expect data in "name:id" format (e.g. "Alice:42")
    String getXmlData(String data) {
        int sep = data.indexOf(':');
        String name = data.substring(0, sep);
        String id   = data.substring(sep + 1);
        // Build an XML representation
        return "<user>"
                + "<name>" + name + "</name>"
                + "<id>"   + id   + "</id>"
                + "</user>";
    }
}

// 3. Adapter: implements IReports by converting XML → JSON
class XmlDataProviderAdapter implements IReports {
    private XmlDataProvider xmlProvider;
    public XmlDataProviderAdapter(XmlDataProvider provider) {
        this.xmlProvider = provider;
    }

    public String getJsonData(String data) {
        // 1. Get XML from the adaptee
        String xml = xmlProvider.getXmlData(data);

        // 2. Naïvely parse out <name> and <id> values
        int startName = xml.indexOf("<name>") + 6;
        int endName   = xml.indexOf("</name>");
        String name   = xml.substring(startName, endName);

        int startId = xml.indexOf("<id>") + 4;
        int endId   = xml.indexOf("</id>");
        String id    = xml.substring(startId, endId);

        // 3. Build and return JSON
        return "{\"name\":\"" + name + "\", \"id\":" + id + "}";
    }
}

// 4. Client code works only with IReports
class Client {
    public void getReport(IReports report, String rawData) {
        System.out.println("Processed JSON: "
            + report.getJsonData(rawData));
    }
}

public class AdapterPattern {
    public static void main(String[] args) {
        // 1. Create the adaptee
        XmlDataProvider xmlProv = new XmlDataProvider();

        // 2. Make our adapter
        IReports adapter = new XmlDataProviderAdapter(xmlProv);

        // 3. Give it some raw data
        String rawData = "Alice:42";

        // 4. Client prints the JSON
        Client client = new Client();

        client.getReport(adapter, rawData);
        // → Processed JSON: {"name":"Alice", "id":42}
    }
}
```

### 📚 Summary

**Adapter Pattern kya karta hai?**

> Ek system ko dusre system ke sath kaam karne layak banata hai jab unke interface alag hote hain.

**Kab use karna chahiye?**

> Jab aapko kisi dusre API ya service se data lena ho aur format match na kare. Jaise XML data ko JSON me dikhana.

**Kaise kaam karta hai?**

> Ek adapter class banate ho jo XML le kar use JSON me convert karti hai, bina client code ko change kiye.

### **Week - 4 ( Day-1 ) Completed ✅ System Design**

> ***NOTE : - A big thanks to my mentors <mark>Rohit Negi</mark> <mark>Sir </mark> and*** [***<mark>Aditya</mark>***](https://www.linkedin.com/in/adityatandon2/) [***<mark>Sir</mark> fo***](https://www.linkedin.com/in/adityatandon2/)***r launching this amazing <mark>8-week course</mark> absolutely <mark>free</mark> on YouTube via <mark>CoderArmy9 </mark> :-*** [***https://www.youtube.com/@CoderArmy9***](https://www.youtube.com/@CoderArmy9) [***. 🙌***](https://www.youtube.com/@CoderArmy9)

👉 **Share this blog with your connections!** Let’s keep learning, growing, and supporting one another on this journey. 🚀

✍️ [*Payal Kumari*](https://www.linkedin.com/in/payalkumari10/) 👩‍💻

![](https://cdn.hashnode.com/res/hashnode/image/upload/v1748984940048/46798c1c-ecc7-4b96-af71-cafaa89da20b.jpeg align="center")

**Jai Hind 🇮🇳 | #CoderArmy #LearningInPublic #SystemDesign #TechForAll #MentorshipMatters** [**#8weeksLLdChallenge**](https://www.youtube.com/hashtag/8weekslldchallenge) [**#LowLevelDesign**](https://www.youtube.com/hashtag/lowleveldesign) #Code [**#LLD**](https://www.youtube.com/hashtag/lld) **#OOP** **👩‍💻**

%[https://youtu.be/FV3x69rpwm0?si=C4JpzPl945Ts7pcx]