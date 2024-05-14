// set the condition for the movement of point pieces
public class Point extends Piece {
    ImageInverter invertImg;
    Piece pieceToMove;

    // Tai Qi Tong
    public Point(Player player) {
        super(PieceType.POINT, "Point", player, determineImagePath(player, "Point.png"));
        invertImg = new ImageInverter();
    }

    // Tai Qi Tong
    @Override
    public boolean isValid(Board board, int fromX, int fromY, int toX, int toY) {
        // Check if the move is within the bounds of the board
        if (super.isValid(board, fromX, fromY, toX, toY)) {
            pieceToMove = board.getSquare(fromY, fromX).getPiece();
            int distance = Math.abs(fromY - toY);
            if (distance > 2) {
                return false;
            }

            if (!pieceToMove.getImgPath().substring(0, 9).equals("inverted_")) {
                // yellow moving upwards
                if (pieceToMove.getPlayer().getColor().equals("Yellow")) {
                    if (fromY < toY) {
                        return false;
                    }
                }

                // blue moving downwards
                else if (pieceToMove.getPlayer().getColor().equals("Blue")) {
                    if (fromY > toY) {
                        return false;
                    }
                }
            }

            // moving downwards
            else {
                // yellow moving down
                if (pieceToMove.getPlayer().getColor().equals("Yellow")) {
                    if (fromY > toY) {
                        return false;
                    }
                }
                // blue moving up
                else if (pieceToMove.getPlayer().getColor().equals("Blue")) {
                    if (fromY < toY) {
                        return false;
                    }
                }
            }

            if (distance <= 2 && (fromX == toX)) {
                if (distance == 2) {
                    if (board.getSquare((fromY + toY) / 2, fromX).getPiece() != null) {
                        return false;
                    } else {
                        return true;
                    }
                }
                // distance =1
                else {
                    return true;
                }
            }
        }
        return false;
    }

    // Tai Qi Tong
// invert point pieces
    public String invertImage(Piece pieceToMove) {
        return invertImg.invertImage(pieceToMove.getImgPath());
    }

    // Tai Qi Tong
    public void move(Board board, int fromX, int fromY, int toX, int toY) {
        if (isValid(board, fromX, fromY, toX, toY)) {
            super.move(board, fromX, fromY, toX, toY);
            if (toY == board.getHeight() - 1 || toY == 0) {
                pieceToMove.setImgPath(invertImage(pieceToMove));
            }
        }
    }

    // Tai Qi Tong
    @Override
    public PieceType getPieceType() {
        return PieceType.POINT;
    }
}
