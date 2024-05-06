import java.util.Deque;
import java.util.LinkedList;

public class Pile {

    private Deque<Card> stack;

    /**
     * Creates a stack that will be filled with cards
     */
    public Pile(){
        stack=new LinkedList<>();
    }

    /**
     * Adds a card to the stack
     * @param c The card that was played and now in the pile
     */
    public void addCard(Card c){
        stack.addFirst(c);
    }

    /**
     * Clears the stack, will be used once a new deck is made after the deck has been used up
     * and adds the old top card to the top of the pile
     */
    public void ClearPile(){
        //holds the top card
        Card c= topCard();

        //removes the top card
        stack.removeFirst();

        //clears the deck and adds the top card
        stack.clear();
        stack.add(c);
    }

    /**
     * @return returns the pile stack of cards
     */
    public Deque<Card> getPile(){
        return this.stack;
    }

    /**
     * checks the top card of the deck
     * @return the last card played
     */
    public Card topCard(){
        return stack.getFirst();
    }
}
