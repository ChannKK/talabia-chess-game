# Talabia Chess
Talabia Chess is a unique 7x6 board game with distinct pieces and rules. This README outlines the features, rules, and usage instructions for the game.

<img src="https://github.com/ChannKK/talabia-game-chess/assets/91399951/6f519cf0-47ca-48d2-a363-83873bb822f3" width=350 height=350>

## Features
- **Resizable Game Window**: Players can resize the game window by dragging its edges.
- **Turn-based Board Flipping**: The game starts with the Yellow player's turn, and the board flips according to the player's turn.
- **Legal Move Highlighting**: Clicking a piece highlights its legal moves in green. If no legal moves are available, a message prompts the player to select another piece.
- **Menu and Labels**: The top of the board features a menu and two labels:
  - **MENU**: Allows the player to save or load the game.
  - **Current Turn Label**: Displays the current player's turn.
  - **Turns Remaining Label**: Shows the number of turns remaining until Time and Plus pieces exchange roles.
- **Save and Load Game**: Players can save the game by selecting "MENU" and entering a file name. They can load a game by selecting a saved file from a pop-up frame.
- **Exit Confirmation**: Clicking the 'X' on the top right corner prompts an exit confirmation message.

## Piece Movements
The piece movements are as of below:

<img src="https://github.com/ChannKK/talabia-game-chess/assets/91399951/bd6f40a0-67ab-426a-9641-4d1bb26b0158" width=600 height=250>
 
### Additional Piece Movements
  - Every 2 turns (one Yellow move and one Blue move), all Time pieces turn into Plus pieces and vice versa.
  - The game ends when a Sun piece is captured.

## Compile and Run Instructions
1. Open a Terminal or Command Prompt:
  - On Windows, you can use the Command Prompt (cmd).
  - On Mac and Linux, you can use the Terminal.
2. Navigate to the directory containing your code:
  - Use the cd command to change to the directory where this folder is located.
3. Compile the Java code:
```
javac Board.java BoardView.java Game.java GameController.java
Hourglass.java HourglassFactory.java ImageInverter.java
Main.java Piece.java PieceFactory.java PieceType.java
Player.java Plus.java PlusFactory.java Point.java
PointFactory.java Square.java Sun.java SunFactory.java
Time.java TimeFactory.java
```
4. Run the compiled Java program:
```
java Main
```

## Usage Instructions
### Starting the Game
- Launch the game to begin with the Yellow player's turn.

### Making Moves
- Click on a piece to view its legal moves highlighted in green. 
- If no legal moves are available, a message will prompt you to select another piece.

### Saving the Game
- Click on "MENU" at the top right corner.
- Enter a desired file name and confirm to save the game.
- If a file name already exists, you can replace it or enter a new name.
- A message will confirm successful saving.

### Loading a Game
- Click on "MENU" and choose the load option.
- A pop-up frame will appear to select a saved game file.
- Enter the file name or click the file to load the game.

### Exiting the Game
- Click the 'X' symbol on the top right corner of the board.
- Confirm the exit in the prompt message to close the game.



