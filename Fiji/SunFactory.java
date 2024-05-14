// factory to create instance of Sun piece

public class SunFactory implements PieceFactory {
    
    // Chan Kar Kin
    // create Sun piece with specified player
    @Override
    public Piece createPiece(Player player) {
        return new Sun(player);
    }
}
