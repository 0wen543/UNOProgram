import java.util.ArrayList;
import java.util.InputMismatchException;

public class Player {

    private ArrayList<Card> theHand;

    //Player number for names sake
    private int playNum;

    /**
     * Creates a hand arrayList and gives the player a turn number, the higher the counter the later a person goes
     * @param turnNum The original turn order for the player
     */
    public Player(int turnNum){
        playNum=turnNum+1;
        theHand=new ArrayList<>();
    }

    /**
     * Adds a card to the players hand
     * @param theCard the card to be added to the hand
     */
    public void addCard(Card theCard){
        theHand.add(theCard);
    }


    /**
     * returns the hand size for the player's hand
     * @return an integer for the hand
     */
    public int handSize(){
        return this.theHand.size();
    }

    /**
     * Removes a card from the arrayList and returns it to be played on the pile
     * @param cardPosition the position of the card in the hand
     * @return the card to be played
     * @throws IllegalArgumentException If the input is invalid or the number was out of bounds
     */
    public Card playCard(int cardPosition) throws IllegalArgumentException{
        cardPosition=cardPosition-1;
        //verifies if the card is within bounds
        if (cardPosition>=theHand.size() || cardPosition<0) {
            throw new IllegalArgumentException("The number was out of bounds!");
        }else if (cardPosition>-1 || cardPosition<handSize()){
            Card selectCard = new Card(theHand.get(cardPosition).getColor(), theHand.get(cardPosition).getType());

            theHand.remove(cardPosition);

            return selectCard;
        }
        else {
            throw new IllegalArgumentException("That was not a valid input!");
        }
    }

    /**
     * gets a card from the hand, but does not remove it
     * @param n the location of the card in the hand
     * @return a card in the hand at the nth position
     * @throws IllegalArgumentException if the number was out of bounds
     */
    public Card getCard(int n)throws IllegalArgumentException{
        if (n>=theHand.size() || n<0) {
            throw new IllegalArgumentException("The number was out of bounds!");
        }else{
            return theHand.get(n);
        }
    }

    /**
     * returns the player number
     * @return an integer representing the player's number
     */
    public int getPlayNum(){return playNum;}

    /**
     * checks to see if a player has won
     * @return a true or false statement if the player won or not
     */
    public boolean hasWon(){
        return this.theHand.isEmpty();
    }
}
