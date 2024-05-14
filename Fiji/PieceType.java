// Represent different types of game pieces using enum

public enum PieceType {
    NULL, POINT, HOURGLASS, TIME, PLUS, SUN;

    // Chan Kar Kin
    @Override
    public String toString() {
        switch (this) {
            case NULL:
                return "Null";
            case POINT:
                return "Point";
            case HOURGLASS:
                return "Hourglass";
            case TIME:
                return "Time";
            case PLUS:
                return "Plus";
            case SUN:
                return "Sun";
            default:
                return "Null";
        }
    }
}