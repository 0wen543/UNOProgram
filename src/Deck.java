import java.util.LinkedList;
import java.util.Queue;
import java.util.*;

public class Deck {

    private Queue<Card> deck;

    /**
     * Creates a 108 card deck, as a queue to easily draw cards
     */
    public Deck(){
        deck = new ArrayDeque<>();

        String color = "";

        for(int i=0; i<4; i++){
            if(i==0){
                color = "red";
            }
            if(i==1){
                color = "blue";
            }
            if(i==2){
                color = "green";
            }
            if(i==3){
                color = "yellow";
            }
            deck.add(new Card(color,"zero"));
            deck.add(new Card(color,"one"));
            deck.add(new Card(color,"two"));
            deck.add(new Card(color,"three"));
            deck.add(new Card(color,"four"));
            deck.add(new Card(color,"five"));
            deck.add(new Card(color,"six"));
            deck.add(new Card(color,"seven"));
            deck.add(new Card(color,"eight"));
            deck.add(new Card(color,"nine"));
            deck.add(new Card(color,"zero"));
            deck.add(new Card(color,"one"));
            deck.add(new Card(color,"two"));
            deck.add(new Card(color,"three"));
            deck.add(new Card(color,"four"));
            deck.add(new Card(color,"five"));
            deck.add(new Card(color,"six"));
            deck.add(new Card(color,"seven"));
            deck.add(new Card(color,"eight"));
            deck.add(new Card(color,"nine"));
            deck.add(new Card(color,"reverse"));
            deck.add(new Card(color,"reverse"));
            deck.add(new Card(color,"skip"));
            deck.add(new Card(color,"skip"));
            deck.add(new Card(color,"+2"));
            deck.add(new Card(color,"+2"));
        }
        deck.add(new Card("Wild",""));
        deck.add(new Card("Wild",""));
        deck.add(new Card("Wild","+4"));
        deck.add(new Card("Wild","+4"));
    }

    /**
     * Swaps two individual cards and their placements
     * @param cardA one of the cards being swapped
     * @param cardB the other card being swapped
     */
    public void swap(Card cardA, Card cardB){
        Card holder = new Card("","");

        holder.setColor(cardA.getColor());
        holder.setType(cardA.getType());

        cardA.setColor(cardB.getColor());
        cardA.setType(cardB.getType());

        cardB.setColor(holder.getColor());
        cardB.setType(holder.getType());
    }

    /**
     * Draws a card from the deck by removing it and returning it
     * @return the card drawn
     */

    public Card draw(){
        return deck.remove();
    }

    /**
     * Runs the swap method 112 times to shuffle the deck
     */
    public void shuffle(){
        Random rand = new Random();
        ArrayList<Card> shuffler = new ArrayList<>(deck);
        for(int i=0; i<108; i++){
            swap(shuffler.get(i), shuffler.get(rand.nextInt(108)));
        }
        new Deck(shuffler);
    }

    /**
     * creates a new deck from the pile class to reuse used cards
     * @param pile the pile being shuffled into the deck
     */

    public void newDeck(Deque<Card> pile){
        ArrayList<Card> thePile = new ArrayList<>(pile);
        int pileSize = pile.size();
        for(int i=0; i<pileSize; i++){
            this.deck.add(thePile.get(i));
        }
    }

    /**
     * gets first card
     * @return first card
     */
    public Card getCard(){
        return deck.remove();
    }

    /**
     * Takes the ArrayList from shuffle and uses it as reference
     * @param shuffle the Arraylist that is shuffled
     */
    public Deck(ArrayList shuffle){
        this.deck = new ArrayDeque<>(shuffle);
    }

    /**
     * This checks to see if the deck is empty
     * @return a true of false response if the deck is or is not empty
     */
    public Boolean emptyDeck(){
        return this.deck.isEmpty();
    }
}
