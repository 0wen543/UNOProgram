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
     * @param c
     */
    public void addCard(Card c){
        stack.addFirst(c);
    }

    /**
     * Clears the stack, will be used once a new deck is made after the deck has been used up
     */
    public void ClearPile(){
        stack.clear();
    }

    public Card topCard(){
        return stack.getLast();
    }
}
