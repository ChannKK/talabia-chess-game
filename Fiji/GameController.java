// Tai Qi Tong
// interface to be implements by Game.java to reduce coupling between BoardView.java and Game.java
public interface GameController {
    void setTurnCount(int turnCount);

    int getTurnCount();

    void setCurrentColor(String currentColor);

    String getCurrentColor();

    void handleButtonClick(Square square);

    void transformPieces();

    void highlightLegalMove(Piece piece);

    void reverse();

    boolean isSunCaptured(Player player);

    void restartGame();

    void refresh();

    void saveGame();

    void loadGame();

    Piece createPiece(Player player, PieceType pieceType);

    void initializePiece();

    void capturedSun();
}
