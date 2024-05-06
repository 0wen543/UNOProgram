public class Card {
    private String color;
    private String type;

    /**
     * Creates an uno card with a string for the color and type, either a number or effect
     * @param theColor the color of the card
     * @param theType the number or effect the card has
     */
    public Card(String theColor, String theType){
        color=theColor;
        type=theType;
    }

    /**
     * returns the color of the card
     * @return the color of the card
     */

    public String getColor(){
        return color;
    }

    /**
     * returns the type or number of the card
     * @return the type of the card
     */
    public String getType(){
        return type;
    }

    /**
     * This sets the card's color when it needs to be swapped
     * @param color1 the original color of the card
     */
    public void setColor(String color1){
        this.color=color1;
    }
    /**
     * This sets the card's type when it needs to be swapped
     * @param type1 the original type of the card
     */
    public void setType(String type1){
        this.type=type1;
    }

    /**
     * prints the card's name
     * @return the string of the card name
     */
    @Override
    public String toString() {
        return this.color + " " + this.type;
    }
}
