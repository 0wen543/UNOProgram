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


    /**
     * This sets the card's color when it needs to be swapped
     * @param color1
     */
    public void setColor(String color1){
        this.color=color1;
    }
    /**
     * This sets the card's type when it needs to be swapped
     * @param type1
     */
    public void setType(String type1){
        this.type=type1;
    }

    @Override
    public String toString() {
        return this.type + " of " + this.color;
    }
}
