// factory to create instance of Plus piece

public class PlusFactory implements PieceFactory {

    // Chan Kar Kin
    // create Plus piece with specified player
    @Override
    public Piece createPiece(Player player) {
        return new Plus(player);
    }
}
