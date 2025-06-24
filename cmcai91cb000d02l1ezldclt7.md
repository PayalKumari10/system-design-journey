---
title: "Week-6 (Day-4) - Understanding the Iterator Pattern: System Design with UML and Code"
seoTitle: "Iterator Pattern: System Design Simplified"
seoDescription: "Explore the Iterator Pattern with UML and code examples, its use cases, and implementation for efficient data traversal in system design"
datePublished: Tue Jun 24 2025 12:31:54 GMT+0000 (Coordinated Universal Time)
cuid: cmcai91cb000d02l1ezldclt7
slug: week-6-day-4-understanding-the-iterator-pattern-system-design-with-uml-and-code
cover: https://cdn.hashnode.com/res/hashnode/image/upload/v1750765152384/e171a454-fbe4-4064-8b2c-e867082ba78c.png
ogImage: https://cdn.hashnode.com/res/hashnode/image/upload/v1750768295852/ecb93ebb-c600-4fc5-8f7a-60f14889a3c8.png
tags: cpp, code, java, technology, coding, system-design, dsa, coding-challenge, technical-writing-1, coding-journey, low-level-design, lld, coderarmy, 8weekslldchallenge, payalkumari11

---

> ***NOTE: - <mark>I started my 8-week system design journey with Coder Army. I will be journaling every day, recording what I learn, reflecting on it, and sharing it with my network to help newcomers to system design.</mark>***

## 💠Why Do We Need Iterators?

Ever tried navigating through a music playlist , a shopping cart , or a list of YouTube videos ? Each one lets you **move item by item**, forward or backward — **without needing to know how the data is stored.**

That’s the beauty of the Iterator Pattern.

(Hindi: Playlist mein ek-ek song ko traverse karna hota hai bina yeh jaane ki woh internally kaise store hua hai — isliye iterator ka use karte hain.)

## 💠What is the Iterator Pattern?

> Iterator provides a way to access the elements of an aggregate object sequentially without exposing its internal representation.

(Hindi: Iterator ek aisa pattern hai jo collection ke elements ko ek ek karke access karne ka method deta hai bina yeh bataye ki woh andar se kaise store hua hai.)

## 💠Iterator Pattern Use Cases

* Playlists
    
* Document Pages
    
* Tree Traversals (Binary Tree)
    
* Library Bookshelf
    
* Undo/Redo Stack
    
* Programming languages like Java, C++, Python internally use Iterators
    

## 💠How We’ll Implement It

### 1️⃣ Generic Iterator & Iterable Interfaces

* `hasNext()`, `next()`
    
* Can be extended to `hasPrev()`, `prev()`
    
    ```java
    
    interface Iterator<T> {
        boolean hasNext();
        T next();
    }
    
    interface Iterable<T> {
        Iterator<T> getIterator();
    }
    ```
    

### 2️⃣ LinkedListIterator (Sequential Access)

* Allows us to move node-by-node in a linked list
    
* Clean abstraction: no need to know internals
    

(Hindi: Linked list mein next element milta rahe — bas itna pata hona chahiye.)

### 3️⃣ InorderIterator ( Tree Traversal)

* In-order traversal of a binary tree
    
* Uses stack for simulation
    
    ```java
    
    class InorderIterator implements Iterator<TreeNode> {
        private Stack<TreeNode> stack = new Stack<>();
    
        private void pushLeft(TreeNode node);
        public boolean hasNext();
        public TreeNode next();
    }
    ```
    
    ### 4️⃣ PlaylistIterator ( Real Example)
    
    * Go song-by-song
        
    * Easy to switch to shuffle mode or reverse with prev()
        
    
    (Hindi: Playlist ko sequential ya reverse order mein traverse karna — iterator perfect fit hai.)
    
    ## 💠 Benefits of Iterator Pattern:
    
    * Separation of traversal logic from data structure
        
    * Supports multiple traversal strategies
        
    * Cleaner code (just loop!)
        
    * Supports forward/backward access
        
    
    ## 💠UML Designs
    
    ![](https://cdn.hashnode.com/res/hashnode/image/upload/v1750767973928/9219c804-40ea-468a-b8b0-3f5e25710623.jpeg align="center")
    
    ![](https://cdn.hashnode.com/res/hashnode/image/upload/v1750768017711/4f716386-690c-4b9e-9348-53940ef26cb8.jpeg align="center")
    
    ```java
    import java.util.*;
    
    interface Iterator<T> {
        boolean hasNext();
        T next();
    }
    
    interface Iterable<T> {
        Iterator<T> getIterator();
    }
    
    // Linked List
    class LinkedList implements Iterable<Integer> {
        public int data;
        public LinkedList next;
    
        public LinkedList(int value) {
            data = value;
            next = null;
        }
    
        public Iterator<Integer> getIterator() {
            return new LinkedListIterator(this);
        }
    }
    
    // Binary Tree
    class BinaryTree implements Iterable<Integer> {
        public int data;
        public BinaryTree left;
        public BinaryTree right;
    
        public BinaryTree(int value) {
            data = value;
            left = null;
            right = null;
        }
    
        public Iterator<Integer> getIterator() {
            return new BinaryTreeInorderIterator(this);
        }
    }
    
    // Song and Playlist
    class Song {
        public String title;
        public String artist;
    
        public Song(String t, String a) {
            title = t;
            artist = a;
        }
    }
    
    class Playlist implements Iterable<Song> {
        public List<Song> songs = new ArrayList<>();
    
        public void addSong(Song s) {
            songs.add(s);
        }
    
        public Iterator<Song> getIterator() {
            return new PlaylistIterator(songs);
        }
    }
    
    // Concrete Iterators
    
    class LinkedListIterator implements Iterator<Integer> {
        private LinkedList current;
    
        public LinkedListIterator(LinkedList head) {
            current = head;
        }
    
        public boolean hasNext() {
            return current != null;
        }
    
        public Integer next() {
            int val = current.data;
            current = current.next;
            return val;
        }
    }
    
    class BinaryTreeInorderIterator implements Iterator<Integer> {
        private Deque<BinaryTree> stk = new ArrayDeque<>();
    
        private void pushLefts(BinaryTree node) {
            while (node != null) {
                stk.push(node);
                node = node.left;
            }
        }
    
        public BinaryTreeInorderIterator(BinaryTree root) {
            pushLefts(root);
        }
    
        public boolean hasNext() {
            return !stk.isEmpty();
        }
    
        public Integer next() {
            BinaryTree node = stk.pop();
            int val = node.data;
            if (node.right != null) {
                pushLefts(node.right);
            }
            return val;
        }
    }
    
    class PlaylistIterator implements Iterator<Song> {
        private List<Song> vec;
        private int index = 0;
    
        public PlaylistIterator(List<Song> v) {
            vec = v;
        }
    
        public boolean hasNext() {
            return index < vec.size();
        }
    
        public Song next() {
            return vec.get(index++);
        }
    }
    
    // Main
    public class IteratorPattern {
        public static void main(String[] args) {
            //------------------------------------------------
            // LinkedList: 1 → 2 → 3
            LinkedList list = new LinkedList(1);
            list.next = new LinkedList(2);
            list.next.next = new LinkedList(3);
    
            Iterator<Integer> iterator1 = list.getIterator();
    
            System.out.print("LinkedList contents: ");
            while (iterator1.hasNext()) {
                System.out.print(iterator1.next() + " ");
            }
            System.out.println();
    
            //------------------------------------------------
    
            // BinaryTree:
            //    2
            //   / \
            //  1   3
            BinaryTree root = new BinaryTree(2);
            root.left  = new BinaryTree(1);
            root.right = new BinaryTree(3);
    
            Iterator<Integer> iterator2 = root.getIterator();
    
            System.out.print("BinaryTree inorder: ");
            while (iterator2.hasNext()) {
                System.out.print(iterator2.next() + " ");
            }
            System.out.println();
    
            //------------------------------------------------
    
            // Playlist
            Playlist playlist = new Playlist();
            playlist.addSong(new Song("Admirin You", "Karan Aujla"));
            playlist.addSong(new Song("Husn", "Anuv Jain"));
    
            Iterator<Song> iterator3 = playlist.getIterator();
    
            System.out.println("Playlist songs:");
            while (iterator3.hasNext()) {
                Song s = iterator3.next();
                System.out.println("  " + s.title + " by " + s.artist);
            }
        }
    }
    ```
    
    ## 💠 Homework Challenge
    
    **Try implementing your own custom iterator with these methods:**
    
    * `hasNext()` – check if there's a next element
        
    * `next()` – move forward and return the next element
        
    * `hasPrev()` – check if there's a previous element
        
    * `prev()` – move backward and return the previous element
        
    
    💡 Use cases to try:
    
    * **Linked List** traversal
        
    * **Binary Tree** (Inorder/Preorder/Postorder)
        
    * **Music Playlist** with shuffle or reverse mode
        
    
    (Hindi: Yeh homework aapko backward traversal jaise `prev()` aur `hasPrev()` jaise methods likhne ka mauka dega — jo kaafi powerful aur real-world useful hai.)
    
    ## 💠Homework Solution Example
    
    ```java
    
    class Node {
        int data;
        Node next;
        Node prev;
    
        Node(int data) {
            this.data = data;
        }
    }
    
    
    class DoublyLinkedListIterator {
        private Node current;
    
        public DoublyLinkedListIterator(Node head) {
            this.current = head;
        }
    
        public boolean hasNext() {
            return current != null && current.next != null;
        }
    
        public int next() {
            if (hasNext()) {
                current = current.next;
                return current.data;
            }
            throw new IndexOutOfBoundsException("No next element");
        }
    
        public boolean hasPrev() {
            return current != null && current.prev != null;
        }
    
        public int prev() {
            if (hasPrev()) {
                current = current.prev;
                return current.data;
            }
            throw new IndexOutOfBoundsException("No previous element");
        }
    }
    ```
    
    This iterator walks both forward and backward through a doubly linked list.
    
    🛠️ You can modify this base to support `PlaylistIterator`, `TreeIterator`, or even undo/redo stacks.
    
    (Hindi: Ye code aapko next aur previous dono direction me move karne wala iterator banane me madad karega.)
    

### **Week - 6 (Day-4) Completed ✅ System Design**

> ***NOTE : - A big thanks to my mentors <mark>Rohit Negi</mark> <mark>Sir </mark> and*** [***<mark>Aditya</mark>***](https://www.linkedin.com/in/adityatandon2/) [***<mark>Sir</mark> fo***](https://www.linkedin.com/in/adityatandon2/)***r launching this amazing <mark>8-week course</mark> absolutely <mark>free</mark> on YouTube via <mark>CoderArmy9 </mark> :-*** [***youtube.com/@CoderArmy9***](http://youtube.com/@CoderArmy9) [***. 🙌***](https://www.youtube.com/@CoderArmy9)

👉 **Share this blog with your connections!** Let’s keep learning, growing, and supporting one another on this journey. 🚀

✍️ [***Payal Kumari***](https://www.linkedin.com/in/payalkumari10/) 👩‍💻

![](https://cdn.hashnode.com/res/hashnode/image/upload/v1750767821256/aac88012-410d-44ff-9c8d-c4128695fa04.jpeg align="center")

**Jai Hind 🇮🇳 | #CoderArmy #LearningInPublic #SystemDesign #TechForAll #MentorshipMatters** [**#8weeksLLdChallenge**](https://www.youtube.com/hashtag/8weekslldchallenge) [**#LowLevelDesign**](https://www.youtube.com/hashtag/lowleveldesign) [**#LLD**](https://www.youtube.com/hashtag/lld) **👩‍💻**

%[https://youtu.be/30fveBRLqMw?si=DPFkllIYJ_eeJm_K]