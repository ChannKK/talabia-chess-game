
public class Time extends Piece {
    // Ng Yun Shi , Chee Weng Kee
    // constructor to set piecetype, name, player and image path
    public Time(Player player) {
        super(PieceType.TIME, "Time", player, determineImagePath(player, "Time.png"));
    }

    // Ng Yun Shi , Chee Weng Kee
    // method to check if move from one square to another square is valid for Time piece,
    // ensure Time can only move diagonally in any distance and it will not skip over other pieces.
    @Override
    public boolean isValid(Board board, int fromX, int fromY, int toX, int toY) {
        if (!super.isValid(board, fromX, fromY, toX, toY)) {
            return false;
        }

        int deltaX = Math.abs(toX - fromX);
        int deltaY = Math.abs(toY - fromY);

        if (deltaX == deltaY && !isCheckPieces(board, fromX, fromY, toX, toY)) {
            return true;
        }
        return false;
    }

    // Ng Yun Shi , Chee Weng Kee
    // ensure Time will not skip over other pieces
    private boolean isCheckPieces(Board board, int fromX, int fromY, int toX, int toY) {
        int xStep = Integer.signum(toX - fromX);
        int yStep = Integer.signum(toY - fromY);

        for (int x = fromX + xStep, y = fromY + yStep; x != toX || y != toY; x += xStep, y += yStep) {
            if (board.getSquare(y, x).getPiece() != null) {
                return true; // Path is blocked
            }
        }
        return false; // Path is not blocked
    }

    // Ng Yun Shi , Chee Weng Kee
    // method to move Time if square is valid
    @Override
    public void move(Board board, int fromX, int fromY, int toX, int toY) {
        if (isValid(board, fromX, fromY, toX, toY)) {
            super.move(board, fromX, fromY, toX, toY);
        }
    }

    // Ng Yun Shi , Chee Weng Kee
    @Override
    public PieceType getPieceType() {
        return PieceType.TIME;
    }
}
