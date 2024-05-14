// factory to create instance of Hourglass piece 

public class HourglassFactory implements PieceFactory {

    // Chan Kar Kin
    // create Hourglass piece with specified player
    @Override
    public Piece createPiece(Player player) {
        return new Hourglass(player);
    }
}
