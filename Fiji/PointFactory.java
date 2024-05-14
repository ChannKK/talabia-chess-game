// factory to create instance of Point piece 

public class PointFactory implements PieceFactory {
    
    // Chan Kar Kin
    // create Point piece with specified player
    @Override
    public Piece createPiece(Player player) {
        return new Point(player);
    }
}
