// represents a square on the board

public class Square {
    private Piece piece;
    private int row;
    private int col;

    // Chan Kar Kin
    // initialize square with row and column
    public Square(int row, int col) {
        this.row = row;
        this.col = col;
    }

    // Chan Kar Kin
    // set piece on squar
    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    // Chan Kar Kin
    // get piece on square
    public Piece getPiece() {
        return piece;
    }

    // Chan Kar Kin
    // set row of square
    public void setRow(int row) {
        this.row = row;
    }

    // Chan Kar Kin
    // get row position of square
    public int getRow() {
        return row;
    }

    // Chan Kar Kin
    // set column position of square
    public void setCol(int col) {
        this.col = col;
    }

    // Chan Kar Kin
    // get column
    public int getCol() {
        return col;
    }

    // Chan Kar Kin
    @Override
    public String toString() {
        return row + ", " + col;
    }
}
