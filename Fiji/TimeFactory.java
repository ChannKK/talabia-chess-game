// factory to create instance of Time piece 

public class TimeFactory implements PieceFactory {

    // Chan Kar Kin
    // create Time piece with specified player
    @Override
    public Piece createPiece(Player player) {
        return new Time(player);
    }
}
