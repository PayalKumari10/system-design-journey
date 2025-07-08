---
title: "📅Week-7 (Day-4) - Step-by-Step System Design for a Snake and Ladder Game with UML Diagrams and Code"
seoTitle: "Snake and Ladder Game System Design Guide"
seoDescription: "Learn to design a modular Snake and Ladder game system with UML and Java, applying SOLID principles and design patterns for scalability"
datePublished: Wed Jul 02 2025 17:49:08 GMT+0000 (Coordinated Universal Time)
cuid: cmcm93tie001502jvabqu83wa
slug: week-7-day-4-step-by-step-system-design-for-a-snake-and-ladder-game-with-uml-diagrams-and-code
cover: https://cdn.hashnode.com/res/hashnode/image/upload/v1751470850037/abdf2bf1-96f8-41de-8d46-e8768aa7ac83.png
ogImage: https://cdn.hashnode.com/res/hashnode/image/upload/v1751478499811/a2cf8d34-7885-41fc-b500-f93ef0fd28b8.png
tags: cpp, code, java, hashnode, system-design, dsa, coding-challenge, codenewbies, technical-writing-1, coding-journey, low-level-design, lld, coderarmy, 8weekslldchallenge, payalkumari11

---

> ***NOTE: - <mark>I started my 8-week system design journey with Coder Army. I will be journaling every day, recording what I learn, reflecting on it, and sharing it with my network to help newcomers to system design.</mark>***

**Namaste developers! 👋**  
Welcome to another exciting day of the **#8WeeksLLDChallenge** — and we’re rolling the dice 🎲 on a nostalgic game we all grew up playing — **Snake and Ladder!**

From fun childhood memories to serious system design, this game is a perfect example of how we can model **real-world rules** using **OOP and design principles.**

But have you ever thought — how do we design a flexible system that handles standard boards, custom configurations, snakes, ladders, and random dice rolls?

We’ll build a **Snake and Ladder system** using **Java and UML**, keeping it **modular, extensible, and clean**, just like a real-world product.

### 📍What we’ll cover:

✅ Standard Snake & Ladder setup  
✅ Random board generation  
✅ Custom user-defined configurations  
✅ Class design for Player, Dice, Cell, Snake, Ladder, and Game Loop  
✅ Applying **SOLID principles** and **design best practices**

---

(Hindi: Snake & Ladder toh hum sabne khela hai, lekin aaj sochenge system designer ki tarah — har snake, ladder, player move aur dice roll ko object-oriented tarike se manage karenge.)

Aaj ka goal hai ek aisa system banana jo sirf game khelne tak simit na ho — balki **easily scalable aur reusable** ho across multiple use cases.

### 💡 Bonus — 3 Powerful Design Patterns:

**a) Strategy Pattern** – Flexible board setup & rules  
b) **Factory Pattern** – Simplified game object creation  
c) **Observer Pattern** – Real-time notifications when the game progresses

Let’s build, design, and code like real engineers! 💻🐍🪜

## 💠Why System Design for Games?

System Design ensures:

* ✅ Code reusability & clean structure
    
* ✅ Custom game creation support
    
* ✅ Easy to test & debug
    
* ✅ SOLID principles follow hoti hain
    

> (Hindi: Agar kal ko aapko new game rules ya custom difficulty add karni ho toh pura code rewrite nahi karna pade — bas naye strategies bana lo!)

## 💠 UML Diagram Overview

Diagram me clearly dikh raha hai kaise `Game` class sab components ko manage karti hai aur kaise `BoardSetupStrategy`, `Rules`, aur `Observer` plug-in ki tarah kaam karte hain.

![](https://cdn.hashnode.com/res/hashnode/image/upload/v1751478237366/c6c35af2-6b72-47ad-b368-c22a68728f57.png align="center")

## 💠 UML Breakdown: Components of Snake & Ladder

### 1\. `Game` Class

* Holds board, players, dice, and rules.
    
* Manages the game loop, notifications, and game state.
    

**Real-Life Analogy:** Think of this class as a referee jo poore game ko control karta hai.

### 2.`Dice` Class

* `roll()` method returns random number between 1 to 6.
    

(Hindi: Ye bilkul waise hi kaam karta hai jaise hum asli life me dice ghumate hain!)

### 3\. `Player` Class

* Stores player ID, name, position, and score.
    

**Example:** "Ramesh is at position 42, rolled a 4, reached 46."

### 4\. `BoardEntity` Abstract Class

* Base class for `Snake` and `Ladder`
    
* Contains `start` and `end` positions + `display()`
    

### `Snake` & `Ladder` Classes

* Inherit from `BoardEntity`
    
* Check validity (Snake end &lt; start, Ladder end &gt; start)
    

**Real-Life Example:** Snake takes you down, Ladder lifts you up — just like in the real game!

### 5\. `Board` Class

* Maintains size, stores all entities (snake/ladder)
    
* Supports `addEntity()`, `display()`, `getEntity()` methods
    

(Hindi: Ye class pura game board represent karti hai, jaha snakes aur ladders place kiye jaate hain.)

### 6\. Strategy Pattern: `BoardSetupStrategy`

Humne yaha 3 types ke strategies banaye hain:

* `StandardBoardSetupStrategy` – Traditional layout
    
* `RandomBoardSetupStrategy` – Difficulty-based board
    
* `CustomCountBoardSetupStrategy` – User-defined snakes/ladders
    

**Example:**

* Easy → Zyada ladders
    
* Hard → Zyada snakes
    
* Custom /Medium → Tum khud decide karo kahaan kya hoga or (Equal Ladder and Snake ).
    

### 7\. Observer Pattern: `IObserver`

* Real-time notifications through `ConsoleNotifier`
    
* Game events jaise dice roll, snake/ladder encounter pe notification
    

**Real-Life Example:** Like cricket match commentary — bataya jaata hai ki kya ho raha hai!

### 8\. Strategy Pattern for Rules

Interface: `SnakeAndLadderRules`

* `isValidMove()`
    
* `calculateNewPosition()`
    
* `checkWinCondition()`
    

(Hindi: Ye rules define karte hain ki move valid hai ya nahi, aur kya koi player jeet gaya?)

### 9\. Factory Pattern: `GameFactory`

* Create standard, random or custom game configurations
    

**Real-Life Analogy:** Ek pizza factory jahan se alag-alag base ban ke nikalte hain — yaha base = board configuration.

## 💠Code

```java
import java.util.*;
import java.util.Scanner;

// Observer Pattern
interface IObserver {
    void update(String msg);
}

// Sample observer implementation
class SnakeAndLadderConsoleNotifier implements IObserver {
    public void update(String msg) {
        System.out.println("[NOTIFICATION] " + msg);
    }
}

// Dice class
class Dice {
    private int faces;
    
    public Dice(int f) {
        faces = f;
    }
    
    public int roll() {
        return (int)(Math.random() * faces) + 1;
    }
}

// Base class for Snake and Ladder (both have start and end positions)
abstract class BoardEntity {
    protected int startPosition;
    protected int endPosition;
    
    public BoardEntity(int start, int end) {
        startPosition = start;
        endPosition = end;
    }
    
    public int getStart() { 
        return startPosition; 
    }

    public int getEnd() { 
        return endPosition;
    }
    
    public abstract void display();
    public abstract String name();
}

// Snake class
class Snake extends BoardEntity {
    public Snake(int start, int end) {
        super(start, end);
        if(end >= start) {
            System.out.println("Invalid snake! End must be less than start.");
        }
    }
    
    @Override
    public void display() {
        System.out.println("Snake: " + startPosition + " -> " + endPosition);
    }

    @Override
    public String name() {
        return "SNAKE";
    }
}

// Ladder class
class Ladder extends BoardEntity {
    public Ladder(int start, int end) {
        super(start, end);
        if(end <= start) {
            System.out.println("Invalid ladder! End must be greater than start.");
        }
    }
    
    @Override
    public void display() {
        System.out.println("Ladder: " + startPosition + " -> " + endPosition);
    }

    @Override
    public String name() {
        return "LADDER";
    }
}

// Board class
class Board {
    private int size;
    private List<BoardEntity> snakesAndLadders;
    private Map<Integer, BoardEntity> boardEntities;
    
    public Board(int s) {
        size = s * s;  // m*m board
        snakesAndLadders = new ArrayList<>();
        boardEntities = new HashMap<>();
    }
    
    public boolean canAddEntity(int position) {
        return !boardEntities.containsKey(position);
    }
    
    public void addBoardEntity(BoardEntity boardEntity) {
        if(canAddEntity(boardEntity.getStart())) {
            snakesAndLadders.add(boardEntity);
            boardEntities.put(boardEntity.getStart(), boardEntity);
        }
    }
    
    public void setupBoard(BoardSetupStrategy strategy) {
        strategy.setupBoard(this);
    }
    
    public BoardEntity getEntity(int position) {
        return boardEntities.get(position);
    }
    
    public int getBoardSize() { 
        return size;
    }
    
    public void display() {
        System.out.println("\n=== Board Configuration ===");
        System.out.println("Board Size: " + size + " cells");

        int snakeCount = 0;
        int ladderCount = 0;
        for(BoardEntity entity : snakesAndLadders) {
            if(entity.name().equals("SNAKE")) snakeCount++;
            else ladderCount++;
        }
        
        System.out.println("\nSnakes: " + snakeCount);
        for(BoardEntity entity : snakesAndLadders) {
            if(entity.name().equals("SNAKE")) {
                entity.display();
            }
        }
        
        System.out.println("\nLadders: " + ladderCount);
        for(BoardEntity entity : snakesAndLadders) {
            if(entity.name().equals("LADDER")) {
                entity.display();
            }
        }
        System.out.println("=========================");
    }
}

// Strategy Pattern for Board Setup
interface BoardSetupStrategy {
    void setupBoard(Board board);
}

// Random Strategy with difficulty levels
class RandomBoardSetupStrategy implements BoardSetupStrategy {
    public enum Difficulty {
        EASY,    // More ladders, fewer snakes
        MEDIUM,  // Equal snakes and ladders
        HARD     // More snakes, fewer ladders
    }
    
    private Difficulty difficulty;
    
    private void setupWithProbability(Board board, double snakeProbability) {
        int boardSize = board.getBoardSize();
        int totalEntities = boardSize / 10; // Roughly 10% of board has entities
        
        for(int i = 0; i < totalEntities; i++) {
            double prob = Math.random();
            
            if(prob < snakeProbability) {
                // Add snake
                int attempts = 0;
                while(attempts < 50) {
                    int start = (int)(Math.random() * (boardSize - 10)) + 10;
                    int end = (int)(Math.random() * (start - 1)) + 1;
                    
                    if(board.canAddEntity(start)) {
                        board.addBoardEntity(new Snake(start, end));
                        break;
                    }
                    attempts++;
                }
            } else {
                // Add ladder
                int attempts = 0;
                while(attempts < 50) {
                    int start = (int)(Math.random() * (boardSize - 10)) + 1;
                    int end = (int)(Math.random() * (boardSize - start)) + start + 1;
                    
                    if(board.canAddEntity(start) && end < boardSize) {
                        board.addBoardEntity(new Ladder(start, end));
                        break;
                    }
                    attempts++;
                }
            }
        }
    }
    
    public RandomBoardSetupStrategy(Difficulty d) {
        difficulty = d;
    }
    
    @Override
    public void setupBoard(Board board) {
        switch(difficulty) {
            case EASY:
                setupWithProbability(board, 0.3);  // 30% snakes, 70% ladders
                break;
            case MEDIUM:
                setupWithProbability(board, 0.5);  // 50% snakes, 50% ladders
                break;
            case HARD:
                setupWithProbability(board, 0.7);  // 70% snakes, 30% ladders
                break;
        }
    }
}

// Custom Strategy - User provides count
class CustomCountBoardSetupStrategy implements BoardSetupStrategy {
    private int numSnakes;
    private int numLadders;
    private boolean randomPositions;
    private List<Pair<Integer, Integer>> snakePositions;
    private List<Pair<Integer, Integer>> ladderPositions;
    
    // Simple Pair class for Java
    private static class Pair<T, U> {
        public final T first;
        public final U second;
        
        public Pair(T first, U second) {
            this.first = first;
            this.second = second;
        }
    }
    
    public CustomCountBoardSetupStrategy(int snakes, int ladders, boolean random) {
        numSnakes = snakes;
        numLadders = ladders;
        randomPositions = random;
        snakePositions = new ArrayList<>();
        ladderPositions = new ArrayList<>();
    }
    
    public void addSnakePosition(int start, int end) {
        snakePositions.add(new Pair<>(start, end));
    }
    
    public void addLadderPosition(int start, int end) {
        ladderPositions.add(new Pair<>(start, end));
    }
    
    @Override
    public void setupBoard(Board board) {
        if(randomPositions) {
            // Random placement with user-defined counts
            int boardSize = board.getBoardSize();
            
            // Add snakes
            int snakesAdded = 0;
            while(snakesAdded < numSnakes) {
                int start = (int)(Math.random() * (boardSize - 10)) + 10;
                int end = (int)(Math.random() * (start - 1)) + 1;
                
                if(board.canAddEntity(start)) {
                    board.addBoardEntity(new Snake(start, end));
                    snakesAdded++;
                }
            }
            
            // Add ladders
            int laddersAdded = 0;
            while(laddersAdded < numLadders) {
                int start = (int)(Math.random() * (boardSize - 10)) + 1;
                int end = (int)(Math.random() * (boardSize - start)) + start + 1;
                
                if(board.canAddEntity(start) && end < boardSize) {
                    board.addBoardEntity(new Ladder(start, end));
                    laddersAdded++;
                }
            }
        } 
        else {
            // User-defined positions
            for(Pair<Integer, Integer> pos : snakePositions) {
                if(board.canAddEntity(pos.first)) {
                    board.addBoardEntity(new Snake(pos.first, pos.second));
                }
            }
            
            for(Pair<Integer, Integer> pos : ladderPositions) {
                if(board.canAddEntity(pos.first)) {
                    board.addBoardEntity(new Ladder(pos.first, pos.second));
                }
            }
        }
    }
}

// Standard Board Strategy - Traditional Snake & Ladder positions
class StandardBoardSetupStrategy implements BoardSetupStrategy {
    @Override
    public void setupBoard(Board board) {
        // Only works for 10x10 board (100 cells)
        if(board.getBoardSize() != 100) {
            System.out.println("Standard setup only works for 10x10 board!");
            return;
        }
        
        // Standard snake positions (based on traditional board)
        board.addBoardEntity(new Snake(99, 54));
        board.addBoardEntity(new Snake(95, 75));
        board.addBoardEntity(new Snake(92, 88));
        board.addBoardEntity(new Snake(89, 68));
        board.addBoardEntity(new Snake(74, 53));
        board.addBoardEntity(new Snake(64, 60));
        board.addBoardEntity(new Snake(62, 19));
        board.addBoardEntity(new Snake(49, 11));
        board.addBoardEntity(new Snake(46, 25));
        board.addBoardEntity(new Snake(16, 6));
        
        // Standard ladder positions
        board.addBoardEntity(new Ladder(2, 38));
        board.addBoardEntity(new Ladder(7, 14));
        board.addBoardEntity(new Ladder(8, 31));
        board.addBoardEntity(new Ladder(15, 26));
        board.addBoardEntity(new Ladder(21, 42));
        board.addBoardEntity(new Ladder(28, 84));
        board.addBoardEntity(new Ladder(36, 44));
        board.addBoardEntity(new Ladder(51, 67));
        board.addBoardEntity(new Ladder(71, 91));
        board.addBoardEntity(new Ladder(78, 98));
        board.addBoardEntity(new Ladder(87, 94));
    }
}

// Player class
class SnakeAndLadderPlayer {
    private int playerId;
    private String name;
    private int position;
    private int score;
    
    public SnakeAndLadderPlayer(int playerId, String n) {
        this.playerId = playerId;
        name = n;
        position = 0;
        score = 0;
    }
    
    // Getters and Setters
    public String getName() { 
        return name;
    }
    public int getPosition() { 
        return position; 
    }
    public void setPosition(int pos) { 
        position = pos; 
    }
    public int getScore() { 
        return score;
    }
    public void incrementScore() { 
        score++; 
    }
}

// Strategy Pattern for game rules
interface SnakeAndLadderRules {
    boolean isValidMove(int currentPos, int diceValue, int boardSize);
    int calculateNewPosition(int currentPos, int diceValue, Board board);
    boolean checkWinCondition(int position, int boardSize);
}

// Standard rules
class StandardSnakeAndLadderRules implements SnakeAndLadderRules {
    @Override
    public boolean isValidMove(int currentPos, int diceValue, int boardSize) {
        return (currentPos + diceValue) <= boardSize;
    }
    
    @Override
    public int calculateNewPosition(int currentPos, int diceValue, Board board) {
        int newPos = currentPos + diceValue;
        BoardEntity entity = board.getEntity(newPos);
        
        if(entity != null) {
            return entity.getEnd();
        }
        return newPos;
    }
    
    @Override
    public boolean checkWinCondition(int position, int boardSize) {
        return position == boardSize;
    }
}

// Game class
class SnakeAndLadderGame {
    private Board board;
    private Dice dice;
    private Deque<SnakeAndLadderPlayer> players;
    private SnakeAndLadderRules rules;
    private List<IObserver> observers;
    private boolean gameOver;
    
    public SnakeAndLadderGame(Board b, Dice d) {
        board = b;
        dice = d;
        players = new ArrayDeque<>();
        rules = new StandardSnakeAndLadderRules();
        observers = new ArrayList<>();
        gameOver = false;
    }
    
    public void addPlayer(SnakeAndLadderPlayer player) {
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
    
    public void displayPlayerPositions() {
        System.out.println("\n=== Current Positions ===");
        for(SnakeAndLadderPlayer player : players) {
            System.out.println(player.getName() + ": " + player.getPosition());
        }
        System.out.println("=======================");
    }
    
    public void play() {
        if(players.size() < 2) {
            System.out.println("Need at least 2 players!");
            return;
        }
        
        notify("Game started");

        board.display();
        
        Scanner scanner = new Scanner(System.in);
        
        while(!gameOver) {
            SnakeAndLadderPlayer currentPlayer = players.peekFirst();
            
            System.out.println("\n" + currentPlayer.getName() + "'s turn. Press Enter to roll dice...");
            scanner.nextLine();
            
            int diceValue = dice.roll();
            System.out.println("Rolled: " + diceValue);
            
            int currentPos = currentPlayer.getPosition();
            
            if(rules.isValidMove(currentPos, diceValue, board.getBoardSize())) {
                int intermediatePos = currentPos + diceValue;
                int newPos = rules.calculateNewPosition(currentPos, diceValue, board);
                
                currentPlayer.setPosition(newPos);
                
                // Check if player encountered snake or ladder
                BoardEntity entity = board.getEntity(intermediatePos);
                if(entity != null) {
                    boolean isSnake = entity.name().equals("SNAKE");
                    if(isSnake) {
                        System.out.println("Oh no! Snake at " + intermediatePos + "! Going down to " + newPos);
                        notify(currentPlayer.getName() + " encountered snake at " + intermediatePos + " now going down to " + newPos);
                    }
                    else {
                        System.out.println("Great! Ladder at " + intermediatePos + "! Going up to " + newPos);
                        notify(currentPlayer.getName() + " encountered ladder at " + intermediatePos + " now going up to " + newPos);
                    }
                }
                
                notify(currentPlayer.getName() + " played. New Position : " + newPos);
                displayPlayerPositions();
                
                if(rules.checkWinCondition(newPos, board.getBoardSize())) {
                    System.out.println("\n" + currentPlayer.getName() + " wins!");
                    currentPlayer.incrementScore();

                    notify("Game Ended. Winner is : " + currentPlayer.getName());
                    gameOver = true;
                }
                else {
                    // Move player to back of queue
                    players.removeFirst();
                    players.addLast(currentPlayer);
                }
            }
            else {
                System.out.println("Need exact roll to reach " + board.getBoardSize() + "!");
                // Move player to back of queue
                players.removeFirst();
                players.addLast(currentPlayer);
            }
        }

        scanner.close();
    }
}

// Factory Pattern
class SnakeAndLadderGameFactory {
    public static SnakeAndLadderGame createStandardGame() {
        Board board = new Board(10);  // Standard 10x10 board
        BoardSetupStrategy strategy = new StandardBoardSetupStrategy();
        board.setupBoard(strategy);
        
        Dice dice = new Dice(6);  // Standard 6-faced dice
        
        return new SnakeAndLadderGame(board, dice);
    }
    
    public static SnakeAndLadderGame createRandomGame(int boardSize, RandomBoardSetupStrategy.Difficulty difficulty) {
        Board board = new Board(boardSize);
        BoardSetupStrategy strategy = new RandomBoardSetupStrategy(difficulty);
        board.setupBoard(strategy);
        
        Dice dice = new Dice(6);
        
        return new SnakeAndLadderGame(board, dice);
    }
    
    public static SnakeAndLadderGame createCustomGame(int boardSize, BoardSetupStrategy strategy) {
        Board board = new Board(boardSize);
        board.setupBoard(strategy);
        
        Dice dice = new Dice(6);
        
        return new SnakeAndLadderGame(board, dice);
    }
}

// Main class for Snake and Ladder
public class SnakeAndLadder {
    public static void main(String[] args) {
        System.out.println("=== SNAKE AND LADDER GAME ===");
        
        SnakeAndLadderGame game = null;
        Board board = null;
        
        System.out.println("Choose game setup:");
        System.out.println("1. Standard Game (10x10 board with traditional positions)");
        System.out.println("2. Random Game with Difficulty");
        System.out.println("3. Custom Game");
        
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        
        if(choice == 1) {
            // Standard game
            game = SnakeAndLadderGameFactory.createStandardGame();
            board = new Board(10);
            
        }
        else if(choice == 2) {
            // Random game with difficulty
            System.out.print("Enter board size (e.g., 10 for 10x10 board): ");
            int boardSize = scanner.nextInt();
            
            System.out.println("Choose difficulty:");
            System.out.println("1. Easy (more ladders)");
            System.out.println("2. Medium (balanced)");
            System.out.println("3. Hard (more snakes)");
            
            int diffChoice = scanner.nextInt();
            
            RandomBoardSetupStrategy.Difficulty diff;
            switch(diffChoice) {
                case 1: diff = RandomBoardSetupStrategy.Difficulty.EASY; break;
                case 2: diff = RandomBoardSetupStrategy.Difficulty.MEDIUM; break;
                case 3: diff = RandomBoardSetupStrategy.Difficulty.HARD; break;
                default: diff = RandomBoardSetupStrategy.Difficulty.MEDIUM;
            }
            
            game = SnakeAndLadderGameFactory.createRandomGame(boardSize, diff);
            board = new Board(boardSize);
            
        } 
        else if(choice == 3) {
            // Custom game
            System.out.print("Enter board size (e.g., 10 for 10x10 board): ");
            int boardSize = scanner.nextInt();
            
            System.out.println("Choose custom setup type:");
            System.out.println("1. Specify counts only (random placement)");
            System.out.println("2. Specify exact positions");
            
            int customChoice = scanner.nextInt();
            
            if(customChoice == 1) {
                System.out.print("Enter number of snakes: ");
                int numSnakes = scanner.nextInt();
                System.out.print("Enter number of ladders: ");
                int numLadders = scanner.nextInt();
                
                BoardSetupStrategy strategy = new CustomCountBoardSetupStrategy(numSnakes, numLadders, true);
                game = SnakeAndLadderGameFactory.createCustomGame(boardSize, strategy);
                
            } 
            else {
                System.out.print("Enter number of snakes: ");
                int numSnakes = scanner.nextInt();
                System.out.print("Enter number of ladders: ");
                int numLadders = scanner.nextInt();
                
                CustomCountBoardSetupStrategy strategy = new CustomCountBoardSetupStrategy(numSnakes, numLadders, false);
                
                // Get snake positions
                for(int i = 0; i < numSnakes; i++) {
                    System.out.print("Enter snake " + (i+1) + " start and end positions: ");
                    int start = scanner.nextInt();
                    int end = scanner.nextInt();
                    strategy.addSnakePosition(start, end);
                }
                
                // Get ladder positions
                for(int i = 0; i < numLadders; i++) {
                    System.out.print("Enter ladder " + (i+1) + " start and end positions: ");
                    int start = scanner.nextInt();
                    int end = scanner.nextInt();
                    strategy.addLadderPosition(start, end);
                }
                
                game = SnakeAndLadderGameFactory.createCustomGame(boardSize, strategy);
            }
            
            board = new Board(boardSize);
        }
        
        if(game == null) {
            System.out.println("Invalid choice!");
            scanner.close();
            return;
        }
        
        // Add observer
        IObserver notifier = new SnakeAndLadderConsoleNotifier();
        game.addObserver(notifier);
        
        // Create players
        System.out.print("Enter number of players: ");
        int numPlayers = scanner.nextInt();
        scanner.nextLine(); // consume newline
        
        for(int i = 0; i < numPlayers; i++) {
            System.out.print("Enter name for player " + (i+1) + ": ");
            String name = scanner.nextLine();
            SnakeAndLadderPlayer player = new SnakeAndLadderPlayer(i+1, name);
            game.addPlayer(player);
        }
        
        // Play the game
        game.play();
        
        scanner.close();
    }
}
```

🎲 Player rolls dice → check snake/ladder → update position → notify → win check 🔁

## 💠Benefits of This Design

* Easily switch between game types
    
* Solid & scalable architecture
    
* Perfect for extension (e.g. power-ups, AI bot)
    
* Code separation = better testing & debugging
    

## **💠Final Thoughts**

System Design is not just for interviews – it’s a real skill for clean code and better architecture.

### **Week - 7 (Day-4) Completed ✅ System Design**

> ***NOTE : - A big thanks to my mentors <mark>Rohit Negi</mark> <mark>Sir </mark> and*** [***<mark>Aditya</mark>***](https://www.linkedin.com/in/adityatandon2/) [***<mark>Sir</mark> fo***](https://www.linkedin.com/in/adityatandon2/)***r launching this amazing <mark>8-week course</mark> absolutely <mark>free</mark> on YouTube via <mark>CoderArmy9 </mark> :-*** [***youtube.com/@CoderArmy9***](http://youtube.com/@CoderArmy9) [***. 🙌***](https://www.youtube.com/@CoderArmy9)

👉 **Share this blog with your connections!** Let’s keep learning, growing, and supporting one another on this journey. 🚀

✍️ [***Payal Kumari***](https://www.linkedin.com/in/payalkumari10/) 👩‍💻

![](https://cdn.hashnode.com/res/hashnode/image/upload/v1751473518822/54e66903-f428-48eb-bb82-3c9906003b6f.jpeg align="center")

**Jai Hind 🇮🇳 | #CoderArmy #LearningInPublic #SystemDesign #TechForAll #MentorshipMatters** [**#8weeksLLdChallenge**](https://www.youtube.com/hashtag/8weekslldchallenge) [**#LowLevelDesign**](https://www.youtube.com/hashtag/lowleveldesign) [**#LLD**](https://www.youtube.com/hashtag/lld) **👩‍💻**

%[https://youtu.be/1NJB54UB8nE?si=0eIIfOCoDrYb5I3w]