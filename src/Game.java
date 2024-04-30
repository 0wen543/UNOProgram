import java.util.ArrayList;
import java.util.Scanner;
public class Game {
    /**
     * Will run the game and have affect the state based on special cards
     * @param args
     */
    public static void main(String[] args) {
        //list of all variables in main method
        int numPlayers;
        final int startingCards=5;
        ArrayList<Player> players = new ArrayList<>();
        Deck theDeck = new Deck();
        int turnCounter=1;

        //asks how many people are playing
        Scanner scan = new Scanner(System.in);
        System.out.println("How many people will play this game? (Please type as an integer)");
        numPlayers=scan.nextInt();

        //creates hand for each of the players
        for (int i=0; i<numPlayers; i++){
            players.add(new Player(i+1));
            for(int f=0; f<startingCards; f++) {
                players.get(i).addCard(theDeck.draw());
            }
        }

        while(!players.get(turnCounter).hasWon()){
            try{
                //List out every card in the hand
                for (int i=1; i<=players.get(i).handSize(); i++){
                    System.out.println(i + ". "+players.get(i));
                }

            }catch (NumberFormatException e) {
                //catches if a person tries to put in an invalid number
                System.out.println("That is not a valid integer, please re enter a number");
            }
        }

    }

    /**
     * verifies if the card is a plus four to trigger effect
     * @param c
     * @return
     */
    public Boolean isPlusFour(Card c){

        return c.getType().equals("plus four");
    }
    /**
     * verifies if the card is a plus two to trigger effect
     * @param c
     * @return
     */
    public Boolean isPlusTwo(Card c){

        return c.getType().equals("plus two");
    }
    /**
     * verifies if the card is a reverse to trigger effect
     * @param c
     * @return
     */
    public Boolean isReverse(Card c){

        return c.getType().equals("reverse");
    }
    /**
     * verifies if the card is a skip to trigger effect
     * @param c
     * @return
     */
    public Boolean isSkip(Card c){

        return c.getType().equals("skip");
    }
    /**
     * verifies if the card is a wild to trigger effect
     * @param c
     * @return
     */
    public Boolean isWild(Card c){

        return c.getType().equals("");
    }
    /**
     * verifies if there is a winner and ends the game
     * @param p
     * @return
     */
    public Boolean isWinner(Player p){
        return p.hasWon();
    }
    /**
     * verifies if the deck is empty to reset it with the pile
     * @param d
     * @return
     */
    public Boolean isEmpty(Deck d){
        return d.emptyDeck();
    }
}
