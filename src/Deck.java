import java.util.LinkedList;
import java.util.Queue;

public class Deck {

    private Queue<Card> Deck;

    /**
     * Creates a 112 card deck, as a queue to easily draw cards
     */
    public Deck(){
        Deck = new LinkedList<>();
    }

    /**
     * Swaps two individual cards and their placements
     * @param cardA
     * @param cardB
     */
    public void swap(Card cardA, Card cardB){

    }

    /**
     * Runs the swap method 112 times to shuffle the deck
     */
    public void shuffle(){

    }

    /**
     * creates a new deck from the pile class to reuse used cards
     * @param pile
     */

    public Deck(LinkedList pile){

    }
}
