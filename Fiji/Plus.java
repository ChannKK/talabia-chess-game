// set the condition for the movement of plus pieces

public class Plus extends Piece {
    // Ng Yun Shi
    // constructor to set piecetype, name, player and image path
    public Plus(Player player) {
        super(PieceType.PLUS, "Plus", player, determineImagePath(player, "Plus.png"));
    }

    // Ng Yun Shi
    // method to check if move from one square to another square is valid for Plus
    // piece, ensure Plus moves horizontally and vertically only but can go any
    // distance and will not skip over other pieces.
    @Override
    public boolean isValid(Board board, int fromX, int fromY, int toX, int toY) {
        // Check if the move is within the bounds of the board
        if (!super.isValid(board, fromX, fromY, toX, toY)) {
            return false;
        }

        // Check if the move is either horizontal or vertical
        if ((fromX == toX || fromY == toY) && !isCheckPieces(board, fromX, fromY, toX, toY)) {
            return true;
        }
        return false;
    }

    // Ng Yun Shi
    // ensure Plus will not skip over other pieces
    private boolean isCheckPieces(Board board, int fromX, int fromY, int toX, int toY) {
        // Check if the path is blocked by other pieces
        if (fromX == toX) {
            // Moving vertically
            int minY = Math.min(fromY, toY);
            int maxY = Math.max(fromY, toY);

            for (int y = minY + 1; y < maxY; y++) {
                if (board.getSquare(y, fromX).getPiece() != null) {
                    return true;
                }
            }
        } else if (fromY == toY) {
            // Moving horizontally
            int minX = Math.min(fromX, toX);
            int maxX = Math.max(fromX, toX);

            for (int x = minX + 1; x < maxX; x++) {
                if (board.getSquare(fromY, x).getPiece() != null) {
                    return true;
                }
            }
        } else {
            return true; // Cannot move diagonally
        }
        return false;
    }

    // Ng Yun Shi
    // method to move Plus if square is valid
    @Override
    public void move(Board board, int fromX, int fromY, int toX, int toY) {
        if (isValid(board, fromX, fromY, toX, toY)) {
            super.move(board, fromX, fromY, toX, toY);
        }
    }

    // Ng Yun Shi
    @Override
    public PieceType getPieceType() {
        return PieceType.PLUS;
    }
}
