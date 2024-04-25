public class Card {
    private String color;
    private String type;

    /**
     * Creates an uno card with a string for the color and type, either a number or effect
     * @param theColor
     * @param theType
     */
    public Card(String theColor, String theType){
        color=theColor;
        type=theType;
    }

    /**
     * returns the color of the card
     * @return
     */

    public String getColor(){
        return color;
    }

    /**
     * returns the type or number of the card
     * @return
     */
    public String getType(){
        return type;
    }
}
