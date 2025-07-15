---
title: "📅Week-7 (Day-3) - Create a Tic Tac Toe Game: Complete Guide with UML and Code Example"
seoTitle: "Tic Tac Toe Game Guide: UML & Example"
seoDescription: "Learn to design and build a Tic Tac Toe game using Java, UML, and modern design principles in this beginner-friendly system design guide"
datePublished: Wed Jul 02 2025 13:15:42 GMT+0000 (Coordinated Universal Time)
cuid: cmclzc6ld000302jphd2k8jzv
slug: week-7-day-3-create-a-tic-tac-toe-game-complete-guide-with-uml-and-code-example
cover: https://cdn.hashnode.com/res/hashnode/image/upload/v1751455643043/811bfbba-87ff-4918-ae84-767a9b906683.png
ogImage: https://cdn.hashnode.com/res/hashnode/image/upload/v1751462082529/62eb1438-2e28-4afb-824a-3b4e8aa42989.png
tags: cpp, code, java, technology, coding, system-design, dsa, coding-challenge, coding-journey, tic-tac-toe, low-level-design, lld, coderarmy, 8weekslldchallenge, payalkumari11

---

> ***NOTE: - <mark>I started my 8-week system design journey with Coder Army. I will be journaling every day, recording what I learn, reflecting on it, and sharing it with my network to help newcomers to system design.</mark>***

**Namaste developers! 👋**  
Welcome to another exciting day of the #8WeeksLLDChallenge — and we’re diving into a classic game we all know and love — **Tic Tac Toe**! 🎮

From childhood fun to coding interviews, Tic Tac Toe is simple to play but powerful when used to understand **System Design and Object-Oriented Programming**.

But have you ever thought about how to design it properly — using classes, objects, responsibilities, and clean architecture?

Today, we’ll **build a Tic Tac Toe system** using **Java and UML**, applying solid design principles like:

* **Players & Game Board**
    
* **Game State Management**
    
* **Winner Checking Logic**
    
* **Designing with OOP (SOLID) principles**
    
* **Using UML to visualize our structure**
    

(Hindi: Bachpan mein toh sabne tic tac toe khela hai, lekin kabhi socha hai is game ko system ke form mein kaise banayein — jahan har player, move aur result smartly manage ho?)

Aaj hum seekhenge kaise ek **modular, scalable Tic Tac Toe system** banaya jata hai — ekdum real-world ke tarah, with proper classes and interactions.

We’ll explore 3 powerful design patterns:

* **Strategy Pattern** – For flexible game rules
    
* **Factory Pattern** – To create game objects easily
    
* **Observer Pattern** – For real-time game notifications
    

(Hindi: Aaj hum Tic Tac Toe banayenge lekin ek system designer ki tarah! Isme hum 3 design patterns ka use karenge jo aapke coding ko architect-level bana denge!)

## 💠 Why System Design for Games?

System design makes your code scalable, testable and future-proof.

(Hindi: System Design se aapka code scalable, testable aur future-proof ban jata hai. Ek choti si game se hum bohot kuch seekh sakte hain:

* ✅ Code Reusability
    
* ✅ Clean Separation of Logic
    
* ✅ Extensibility (easily add new rules later)
    

Agar kal aapko 4x4 ya 5x5 wala Tic Tac Toe banana ho, toh aapko pura code nahi likhna padega — bus naye rules define karo aur ho gaya!)

## 💠Let's Break Down The Components

### **1\. Board Class**

* Initializes the **grid**
    
* Checks for **valid moves**
    
* Displays the **current state** of the board
    

📍 **Real-Life Analogy:**  
(Hindi: Socho ek **chess board** ki tarah — woh khud game nahi khelta, lekin **har piece ki position ko manage** karta hai.  
**Tic Tac Toe ka board bhi wahi karta hai** — game control nahi karta, bas track rakhta hai ki kya ho raha hai! )

### **2\. Player Class**

* Stores each player's **name**, **symbol** (X or O), and **score**
    
* Helps in deciding **whose turn is next**
    

📍 **Real-Life Analogy:**  
(Hindi: Bilkul real players ki tarah — **har kisi ka apna symbol hota hai**, aur turns ek **queue** mein chalte hain.  
Game fair chalana ho toh players ka proper tracking hona zaroori hai! )

### 3\. **Strategy Pattern - Game Rules**

**Interface:** `TicTacToeRules`

**Implementation:** `StandardTicTacToeRules`

Use: Define whether a move is valid, check if any player has won, and determine if the game has ended in a draw.

(Hindi: Define karo kya ek move valid hai, koi player jeeta ya nahi, aur game draw hua ya nahi.

**📍Real-Life Example:** Standard rules vs custom rules — jaise kabhi kabhi hum 4x4 Tic Tac Toe khelte hain apne doston ke sath. Bas rules change hue, logic same!

Strategy Pattern aapko multiple game logic implement karne ki flexibility deta hai!)

### 4\. **Factory Pattern - Game Creation**

We use `TicTacToeGameFactory` to create different types of games. Currently only `STANDARD`, but you can add more like `TIMED` or `CHALLENGE`!

**📍Real-Life Analogy:** (Hindi: Pizza bana rahe ho aur factory se alag-alag type ka base milta hai (thin crust, cheese burst). Base change, baaki same! 🍕)

### 5\. **Observer Pattern - Notification System**

We use `ConsoleNotifier` which implements `IObserver`. It notifies when:

* Game starts
    
* Player makes move
    
* Someone wins or it’s a draw
    

**📍Real-Life Analogy:** (Hindi: Like cricket match me commentator announce karta hai jab run ya wicket hota hai — wahi kaam yaha `ConsoleNotifier` karta hai! )

## 💠 UML Diagram Overview (Visual Architecture)

![](https://cdn.hashnode.com/res/hashnode/image/upload/v1751461977629/20390c78-2a22-4b94-bdda-656e67d5c908.png align="center")

## 💠Code

```java
import java.util.*;

// Observer Pattern - for future notification service
interface IObserver {
    void update(String msg);
}

// Sample observer implementation
class ConsoleNotifier implements IObserver {
    public void update(String msg) {
        System.out.println("[Notification] " + msg);
    }
}

// Symbol/Mark class
class Symbol {
    private char mark;
    
    public Symbol(char m) {
        mark = m;
    }
    
    public char getMark() {
        return mark;
    }
}

// Board class - Dumb object that only manages the grid
class Board {
    private Symbol[][] grid;
    private int size;
    private Symbol emptyCell;
    
    public Board(int s) {
        size = s;
        emptyCell = new Symbol('-');
        grid = new Symbol[size][size];
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                grid[i][j] = emptyCell;
            }
        }
    }
    
    public boolean isCellEmpty(int row, int col) {
        if(row < 0 || row >= size || col < 0 || col >= size) {
            return false;
        }
        return grid[row][col] == emptyCell;
    }
    
    public boolean placeMark(int row, int col, Symbol mark) {
        if(row < 0 || row >= size || col < 0 || col >= size) {
            return false;
        }
        if(!isCellEmpty(row, col)) {
            return false;
        }
        grid[row][col] = mark;
        return true;
    }
    
    public Symbol getCell(int row, int col) {
        if(row < 0 || row >= size || col < 0 || col >= size) {
            return emptyCell;
        }
        return grid[row][col];
    }
    
    public int getSize() {
        return size;
    }
    
    public Symbol getEmptyCell() {
        return emptyCell;
    }
    
    public void display() {
        System.out.print("\n  ");
        for(int i = 0; i < size; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        
        for(int i = 0; i < size; i++) {
            System.out.print(i + " ");
            for(int j = 0; j < size; j++) {
                System.out.print(grid[i][j].getMark() + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}

// Player class --> 
class TicTacToePlayer {
    private int playerId;
    private String name;
    private Symbol symbol;
    private int score;
    
    public TicTacToePlayer(int playerId, String n, Symbol s) {
        this.playerId = playerId;
        name = n;
        symbol = s;
        score = 0;
    }
    
    // Getters and setters
    public String getName() { 
        return name; 
    }

    public Symbol getSymbol() { 
        return symbol; 
    }

    public int getScore() { 
        return score; 
    }

    public void incrementScore() { 
        score++;
    }
}

// Strategy Pattern for game rules
interface TicTacToeRules {
    boolean isValidMove(Board board, int row, int col);
    boolean checkWinCondition(Board board, Symbol symbol);
    boolean checkDrawCondition(Board board);
}

// Standard Tic Tac Toe rules
class StandardTicTacToeRules implements TicTacToeRules {
    public boolean isValidMove(Board board, int row, int col) {
        return board.isCellEmpty(row, col);
    }
    
    public boolean checkWinCondition(Board board, Symbol symbol) {
        int size = board.getSize();
        
        // Check rows
        for(int i = 0; i < size; i++) {
            boolean win = true;
            for(int j = 0; j < size; j++) {
                if(board.getCell(i, j) != symbol) {
                    win = false;
                    break;
                }
            }
            if(win) return true;
        }
        
        // Check columns
        for(int j = 0; j < size; j++) {
            boolean win = true;
            for(int i = 0; i < size; i++) {
                if(board.getCell(i, j) != symbol) {
                    win = false;
                    break;
                }
            }
            if(win) return true;
        }
        
        // Check main diagonal
        boolean win = true;
        for(int i = 0; i < size; i++) {
            if(board.getCell(i, i) != symbol) {
                win = false;
                break;
            }
        }
        if(win) return true;
        
        // Check anti-diagonal
        win = true;
        for(int i = 0; i < size; i++) {
            if(board.getCell(i, size-1-i) != symbol) {
                win = false;
                break;
            }
        }
        return win;
    }
    
    // If all cells are filled and no winner
    public boolean checkDrawCondition(Board board) {
        int size = board.getSize();
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if(board.getCell(i, j) == board.getEmptyCell()) {
                    return false;
                }
            }
        }
        return true;
    }
}

// Game class --> Observable
class TicTacToeGame {
    private Board board;
    private Deque<TicTacToePlayer> players;
    private TicTacToeRules rules;
    private List<IObserver> observers;
    private boolean gameOver;
    
    public TicTacToeGame(int boardSize) {
        board = new Board(boardSize);
        players = new ArrayDeque<>();
        rules = new StandardTicTacToeRules();
        observers = new ArrayList<>();
        gameOver = false;
    }
    
    public void addPlayer(TicTacToePlayer player) {
        players.addLast(player);
    }
    
    public void addObserver(IObserver observer) {
        observers.add(observer);
    }

    public void notify(String msg) {
        for(IObserver observer : observers) {
            observer.update(msg);
        }
    }
    
    public void play() {
        if(players.size() < 2) {
            System.out.println("Need at least 2 players!");
            return;
        }
        
        notify("Tic Tac Toe Game Started!");
        
        Scanner scanner = new Scanner(System.in);
        
        while(!gameOver) {
            board.display();
            
            // Take out the current player from dequeue
            TicTacToePlayer currentPlayer = players.peekFirst();
            System.out.print(currentPlayer.getName() + " (" + currentPlayer.getSymbol().getMark() + ") - Enter row and column: ");
            
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            
            // check if move is valid
            if(rules.isValidMove(board, row, col)) {
                board.placeMark(row, col, currentPlayer.getSymbol());
                notify(currentPlayer.getName() + " played (" + row + "," + col + ")");
                
                if(rules.checkWinCondition(board, currentPlayer.getSymbol())) {
                    board.display();
                    System.out.println(currentPlayer.getName() + " wins!");
                    currentPlayer.incrementScore();

                    notify(currentPlayer.getName() + " wins!");

                    gameOver = true;
                }
                else if(rules.checkDrawCondition(board)) {
                    board.display();
                    
                    System.out.println("It's a draw!");
                    notify("Game is Draw!");

                    gameOver = true;
                }
                else {
                    // Move player to back of queue
                    players.removeFirst();
                    players.addLast(currentPlayer);
                }
            }
            else {
                System.out.println("Invalid move! Try again.");
            }
        }
    }
}

// Enum & Factory Pattern for game creation
enum GameType {
    STANDARD
}

class TicTacToeGameFactory {
    public static TicTacToeGame createGame(GameType gt, int boardSize) {
        if(GameType.STANDARD == gt) {
            return new TicTacToeGame(boardSize);
        }
        return null;
    }
}

// Main class for Tic Tac Toe
public class TicTacToeMain {
    public static void main(String[] args) {
        System.out.println("=== TIC TAC TOE GAME ===");
        
        // Create game with custom board size
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter board size (e.g., 3 for 3x3): ");
        int boardSize = scanner.nextInt();
        
        TicTacToeGame game = TicTacToeGameFactory.createGame(GameType.STANDARD, boardSize);
        
        // Add observer
        IObserver notifier = new ConsoleNotifier();
        game.addObserver(notifier);
        
        // Create players with custom symbols
        TicTacToePlayer player1 = new TicTacToePlayer(1, "Aditya", new Symbol('X'));
        TicTacToePlayer player2 = new TicTacToePlayer(2, "Harshita", new Symbol('O'));
        
        game.addPlayer(player1);
        game.addPlayer(player2);
        
        // Play the game
        game.play();
        
        scanner.close();
    }
}
```

## 💠Benefits of This Design

* Modular Design – Easy to extend
    
* Testable – Each class is testable individually
    
* Maintainable – Less bugs in future
    
* Design Patterns used in real-world applications
    

## 💠Final Thoughts

System Design is not just for interviews – it’s a real skill for clean code and better architecture.

### **Week - 7 (Day-3) Completed ✅ System Design**

> ***NOTE : - A big thanks to my mentors <mark>Rohit Negi</mark> <mark>Sir </mark> and*** [***<mark>Aditya</mark>***](https://www.linkedin.com/in/adityatandon2/) [***<mark>Sir</mark> fo***](https://www.linkedin.com/in/adityatandon2/)***r launching this amazing <mark>8-week course</mark> absolutely <mark>free</mark> on YouTube via <mark>CoderArmy9 </mark> :-*** [***youtube.com/@CoderArmy9***](http://youtube.com/@CoderArmy9) [***. 🙌***](https://www.youtube.com/@CoderArmy9)

👉 **Share this blog with your connections!** Let’s keep learning, growing, and supporting one another on this journey. 🚀

✍️ [***Payal Kumari***](https://www.linkedin.com/in/payalkumari10/) 👩‍💻

![](https://cdn.hashnode.com/res/hashnode/image/upload/v1751458555182/eacfc262-6e05-4ab5-82af-eda8accf042c.jpeg align="center")

**Jai Hind 🇮🇳 | #CoderArmy #LearningInPublic #SystemDesign #TechForAll #MentorshipMatters** [**#8weeksLLdChallenge**](https://www.youtube.com/hashtag/8weekslldchallenge) [**#LowLevelDesign**](https://www.youtube.com/hashtag/lowleveldesign) [**#LLD**](https://www.youtube.com/hashtag/lld) **👩‍💻**

%[https://youtu.be/BGFzYjGtRP4?si=VjMv-RsIzgsp5yMM]