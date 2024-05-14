
public class Hourglass extends Piece {

    // Ng Yun Shi
    // constructor to set piecetype, name, player and image path
    public Hourglass(Player player) {
        super(PieceType.HOURGLASS, "Hourglass", player, determineImagePath(player, "Hourglass.png"));
    }

    // Ng Yun Shi
    // method to check if move from one square to another square is valid for
    // Hourglass piece, ensure Hourglass moves in a 3x2 L shape in any orientation
    @Override
    public boolean isValid(Board board, int fromX, int fromY, int toX, int toY) {
        if (!super.isValid(board, fromX, fromY, toX, toY)) {
            return false;
        }

        int deltaX = Math.abs(toX - fromX);
        int deltaY = Math.abs(toY - fromY);

        if (deltaX == 1 && deltaY == 2) {
            return true;
        } else if (deltaX == 2 && deltaY == 1) {
            return true;
        }
        return false;
    }

    // Ng Yun Shi
    // method to move Hourglass if square is valid
    @Override
    public void move(Board board, int fromX, int fromY, int toX, int toY) {
        if (isValid(board, fromX, fromY, toX, toY)) {
            super.move(board, fromX, fromY, toX, toY);
        }
    }

    // Ng Yun Shi
    @Override
    public PieceType getPieceType() {
        return PieceType.HOURGLASS;
    }
}
