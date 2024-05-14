// represents the game board
import java.util.*;

public class Board {
    private final int BOARDWIDTH = 7; 
    private final int BOARDHEIGHT = 6; 
    private static Board boardInstance; 
    private LinkedList<Square> square = new LinkedList<Square>();

    // Ang Jin Nan
    // private constructor to initialize board, create square using linkedlist
    private Board() {
        square = new LinkedList<>();

        for (int i = 0; i < BOARDHEIGHT; i++) {
            for (int j = 0; j < BOARDWIDTH; j++) {
                square.add(new Square(i, j));
            }
        }
    }

    // Chan Kar Kin
    // Singleton pattern to get boardinstance
    public static Board getBoardInstance() {
        if (boardInstance == null) {
            boardInstance = new Board();
        }
        return boardInstance;
    }

    // Chan Kar Kin
    public int getWidth() {
        return BOARDWIDTH;
    }

    // Ang Jin Nan
    public int getHeight() {
        return BOARDHEIGHT;
    }

    // Chan Kar Kin
    public LinkedList<Square> getSquare() {
        return square;
    }

    // Chan Kar Kin
    // add square to position on board if position is valid
    private boolean isValidSquare(int row, int col) {
        return (row >= 0 && row < BOARDHEIGHT) && (col >= 0 && col < BOARDWIDTH);
    }

    // Chan Kar Kin
    // check if row and column are within valid range of the board
    Square getSquare(int row, int col) {
        int index = row * BOARDWIDTH + col;
        if (index >= 0 && index < square.size()) {
            return square.get(index);
        }
        return null;
    }

    // Chan Kar Kin, Ang Jin Nan
    // add piece to square if square position is valid on board
    public void addPiece(Piece piece, int row, int col) {
        if (isValidSquare(row, col)) {
            Square square = getSquare(row, col);
            if (square != null) {
                square.setPiece(piece);
            }
        }
    }

    // Ang Jin Nan
    // Clear the whole board
    public void clearBoard() {
        for (Square square : square) {
            square.setPiece(null);
        }
    }
}