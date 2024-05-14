
public abstract class Piece {
    private PieceType pieceType;
    private String pieceName;
    private Player player;
    private String imgPath;
    private int x;
    private int y;
    private boolean moved;
    private static final String IMG_PATH_BLUE = "blue";
    private static final String IMG_PATH_YELLOW = "yellow";

    // Ang Jin Nan
    // create instances of pieces for board
    public Piece(PieceType pieceType, String pieceName, Player player, String imgPath) {
        this.pieceType = pieceType;
        this.pieceName = pieceName;
        this.player = player;
        this.imgPath = imgPath;
    }

    public Piece(PieceType pieceType, String pieceName, Player player) {
        this(pieceType, pieceName, player, ""); 
    }

    // Ang Jin Nan
    // Get image path according to the player’s color
    public static String determineImagePath(Player player, String image) {
        String color = player.getColor();
        if (color != null && color.equals("Blue")) {
            return IMG_PATH_BLUE + image;
        } else if (color != null && color.equals("Yellow")) {
            return IMG_PATH_YELLOW + image;
        } else {
            return "";
        }
    }

    // Chan Kar Kin
    public abstract PieceType getPieceType();

    // Ang Jin Nan
    // get player to whom the piece belongs
    public Player getPlayer() {
        return player;
    }

    // Ang Jin Nan
    // get file path or source path to the piece image
    public String getImgPath() {
        return imgPath;
    }

    // Ang Jin Nan
    // set file path or source path to the piece image
    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    // Chan Kar Kin
    public String getPieceName() {
        return pieceName;
    }

    // Ang Jin Nan
    // get coordinate x of the piece
    public int getX() {
        return x;
    }

    // Ang Jin Nan
    // get coordinate y of the piece
    public int getY() {
        return y;
    }

    // Ang Jin Nan
    // set position (x, y) of the piece
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Chan Kar Kin
    // check if 2 pieces have the same player
    public boolean isSamePlayer(Piece otherPiece) {
        return otherPiece != null && this.getPlayer() == otherPiece.getPlayer();
    }

    // Tai Qi Tong
    // to check validity of user to be move or not
    // player will not move if trying to move to same position, out of the board, or
    // moving to position of same player’s piece
    public boolean isValid(Board board, int fromX, int fromY, int toX, int toY) {
        if ((toX == fromX && toY == fromY)
                || (toX < 0 || toX >= board.getWidth() || fromX < 0 || fromX >= board.getWidth() || toY < 0
                        || toY >= board.getHeight() || fromY < 0 || fromY >= board.getHeight())) {
            return false;
        }
        Piece destinationPiece = board.getSquare(toY, toX).getPiece();
        if (destinationPiece != null && isSamePlayer(destinationPiece)) {
            return false;
        }
        return true; 
    }

    // Tai Qi Tong
    // get whether player has moved on his/her turn or not
    public boolean getMoved() {
        return moved;
    }

    // Tai Qi Tong
    // set a player has moved on his/her turn or not
    public void setMoved(boolean moved) {
        this.moved = moved;
    }

    // Tai Qi Tong
    // set the new position of the pieces on the board if the pieces moving to a valid position
    public void move(Board board, int fromX, int fromY, int toX, int toY) {
        if (isValid(board, fromX, fromY, toX, toY)) {
            Piece pieceToMove = board.getSquare(fromY, fromX).getPiece();
            pieceToMove.setPosition(toY, toX);
            board.getSquare(toY, toX).setPiece(pieceToMove);
            board.getSquare(fromY, fromX).setPiece(null);
            setMoved(true);
        }
    }

    // Ang Jin Nan
    @Override
    public String toString() {
        return pieceName;
    }
}