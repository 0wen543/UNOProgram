import java.util.ArrayList;

public class Player {

    private ArrayList<Card> theHand;

    //Player number for names sake
    private int playNum;

    private int turn;

    /**
     * Creates a hand arrayList and gives the player a turn number, the higher the counter the later a person goes
     * @param turnNum
     */

    public Player(int turnNum){
        turn=turnNum;
        playNum=turnNum+1;
        theHand=new ArrayList<>();
    }

    /**
     * Adds a card to the players hand
     * @param theCard
     */
    public void addCard(Card theCard){
        theHand.add(theCard);
    }

    public int handSize(){
        return this.theHand.size();
    }

    /**
     * resets the player's turn orders if a reverse is played
     * @param newTurn
     */
    public void setTurn(int newTurn){
        this.turn=newTurn;
    }

    /**
     * Removes a card from the arrayList and returns it to be played on the pile
     * @param cardPosition
     * @return
     */
    public Card playCard(int cardPosition){
        cardPosition=cardPosition-1;
        //verifies if the card is within bounds
        if (cardPosition>=theHand.size() || cardPosition<0) {
            throw new IllegalArgumentException("The number was out of bounds");
        }else {
            Card selectCard = new Card(theHand.get(cardPosition).getColor(), theHand.get(cardPosition).getType());

            theHand.remove(cardPosition);

            return selectCard;
        }
    }

    public Card getCard(int n){
        return theHand.get(n);
    }

    public int getTurn(){return turn;}

    public int getPlayNum(){return playNum;}

    public boolean hasWon(){
        return this.theHand.isEmpty();
    }
}
