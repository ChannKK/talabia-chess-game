// Sun model
public class Sun extends Piece {

    // Chan Kar Kin
    // constructor to set piecetype, name, player and image path
    public Sun(Player player) {
        super(PieceType.SUN, "Sun", player, determineImagePath(player, "Sun.png"));
    }

    // Chan Kar Kin
    // method to check if move from one square to another square is valid for Sun piece, ensure Sun move 1 step in any direction
    @Override
    public boolean isValid(Board board, int fromX, int fromY, int toX, int toY) {
        if (!super.isValid(board, fromX, fromY, toX, toY)) {
            return false; // Check basic validity using the parent class method (in board)
        }

        // The Sun can move one step in any direction, including diagonally
        int changesInX = Math.abs(toX - fromX);
        int changesInY = Math.abs(toY - fromY);

        return changesInX <= 1 && changesInY <= 1;
    }

    // Chan Kar Kin
    // method to move Sun if square is valid
    @Override
    public void move(Board board, int fromX, int fromY, int toX, int toY) {
        if (isValid(board, fromX, fromY, toX, toY)) {
            super.move(board, fromX, fromY, toX, toY);
        }
    }

    // Chan Kar Kin
    @Override
    public PieceType getPieceType() {
        return PieceType.SUN;
    }
}
