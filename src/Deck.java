import java.util.LinkedList;
import java.util.Queue;
import java.util.*;

public class Deck {

    private Queue<Card> deck;

    /**
     * Creates a 112 card deck, as a queue to easily draw cards
     */
    public Deck(){
        deck = new LinkedList<>();

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
            deck.add(new Card(color,"reverse"));
            deck.add(new Card(color,"reverse"));
            deck.add(new Card(color,"skip"));
            deck.add(new Card(color,"skip"));
            deck.add(new Card(color,"plus two"));
            deck.add(new Card(color,"plus two"));
        }
        deck.add(new Card("Wild",""));
        deck.add(new Card("Wild",""));
        deck.add(new Card("Wild","plus four"));
        deck.add(new Card("Wild","plus four"));
    }

    /**
     * Swaps two individual cards and their placements
     * @param cardA
     * @param cardB
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
     * Runs the swap method 112 times to shuffle the deck
     */
    public void shuffle(){
        Random rand = new Random();
        ArrayList<Card> shuffler = new ArrayList<>(deck);
        for(int i=0; i<112; i++){
            swap(shuffler.get(i), shuffler.get(rand.nextInt(112)));
        }
        new Deck(shuffler);
    }

    /**
     * creates a new deck from the pile class to reuse used cards
     * @param pile
     */

    public Deck(LinkedList pile){

    }

    public Deck(ArrayList shuffle){
        this.deck = new LinkedList<Card>(shuffle);
    }
}
