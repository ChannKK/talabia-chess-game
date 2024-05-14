// Represents a player

public class Player {
    private String color;

    // Chan Kar Kin
    // initialize player with a specific color
    public Player(String color) {
        setColor(color);
    }

    // Chan Kar Kin
    // set color of player
    public void setColor(String color) {
        this.color = color;
    }

    // Chan Kar Kin
    // get color of player
    public String getColor() {
        return color;
    }
}