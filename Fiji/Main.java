// Initialize game board and sets up main window
// Ang Jin Nan

import javax.swing.*;

public class Main extends JFrame {
    private BoardView boardView;
    private GameController gameControl;

    public Main() {
        setSize(650, 650);  
        setLocationRelativeTo(null);
        Board board = Board.getBoardInstance();
        boardView = new BoardView(board);
        boardView.setVisible(true);
        gameControl = new Game(boardView, board);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main());
    }
}
