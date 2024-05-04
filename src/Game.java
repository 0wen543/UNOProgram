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
        int turnCounter=0;
        int selectCard;
        String newColor;
        Card played;
        Pile thePile = new Pile();
        Boolean isPlayable = false;

        //asks how many people are playing
        Scanner scan = new Scanner(System.in);
        System.out.println("How many people will play this game? (Please type as an integer)");
        numPlayers=scan.nextInt();

        //shuffles the deck and creates hands for each of the players
        theDeck.shuffle();
        for (int i=0; i<numPlayers; i++){
            players.add(new Player(i));
            for(int f=0; f<startingCards; f++) {
                players.get(i).addCard(theDeck.draw());
            }
        }

        //Starts the pile with the first card on top of the deck after dealing hands
        thePile.addCard(theDeck.getCard());

        while(!players.get(turnCounter).hasWon()){
            try{
                //List out every card in the hand
                while(!isPlayable) {
                    System.out.printf("\nPlayer "+ players.get(turnCounter).getPlayNum() +".\n");
                    for (int i = 1; i <= players.get(turnCounter).handSize(); i++) {
                        System.out.println(i + ". " + players.get(turnCounter).getCard(i - 1).toString());
                    }
                    System.out.printf("\nThe top card is a " + thePile.topCard().toString() + "\n");
                    //check hand for any playable cards
                    if (!(handPlayability(players.get(turnCounter), thePile.topCard()))) {
                        //then player must draw
                        players.get(turnCounter).addCard(theDeck.draw());
                    } else {
                        System.out.println("Please type in number to play your card.");
                        selectCard = scan.nextInt();
                        played = players.get(turnCounter).getCard(selectCard - 1);
                        isPlayable = playability(played, thePile.topCard());

                        if (isPlayable) {
                            thePile.addCard(players.get(turnCounter).playCard(selectCard));
                            System.out.println("__________________________________________");
                        } else {
                            System.out.println("That card is not valid.");
                        }
                        //Occurs when a plus four is played
                        if (isPlusFour(played)) {
                            for (int i = 0; i < 4; i++) {
                                if (turnCounter+1==numPlayers){
                                    players.get(0).addCard(theDeck.draw());
                                }
                                players.get(turnCounter + 1).addCard(theDeck.draw());
                            }
                            System.out.println("Please choose a color.");
                            newColor = scan.next();
                            if (newColor.equals("yellow")) {
                                played.setType("yellow");
                            } else if (newColor.equals("red")) {
                                played.setType("red");
                            } else if (newColor.equals("green")) {
                                played.setType("green");
                            } else if (newColor.equals("blue")) {
                                played.setType("blue");
                            }
                            turnCounter++;
                        }


                        //occurs when a wild card is played
                        else if (isWild(played)) {
                            System.out.println("Please choose a color.");
                            newColor = scan.next();
                            if (newColor.equals("yellow")) {
                                played.setType("yellow");
                            } else if (newColor.equals("red")) {
                                played.setType("red");
                            } else if (newColor.equals("green")) {
                                played.setType("green");
                            } else if (newColor.equals("blue")) {
                                played.setType("blue");
                            }
                        }


                        //occurs when a skip is played
                        else if (isSkip(played)) {
                            turnCounter++;
                            if (turnCounter>=numPlayers){
                                turnCounter=0;
                            }
                        }


                        //occurs when a reverse card is played
                        else if (isReverse(played)) {
                            ArrayList<Player> reverse = new ArrayList<>();
                            for (int i = numPlayers-1; i>=0; i--) {
                                reverse.add(players.get(i));
                            }
                            players.clear();
                            for(int i=0; i<reverse.size(); i++){
                                players.add(reverse.get(i));
                            }
                        }


                        //occurs when a plus two is played
                        else if (isPlusTwo(played)) {
                            for (int i = 0; i < 2; i++) {
                                if (turnCounter+1==numPlayers){
                                    players.get(0).addCard(theDeck.draw());
                                }
                                players.get(turnCounter + 1).addCard(theDeck.draw());
                            }
                            turnCounter++;
                            if (turnCounter>=numPlayers){
                                turnCounter=0;
                            }
                        }
                    }
                }
                turnCounter++;
                if (turnCounter>=numPlayers) {
                    turnCounter = 0;
                    isPlayable=false;
                }else {
                    isPlayable=false;
                }
            }catch (NumberFormatException e) {
                //catches if a person tries to put in an invalid number
                System.out.println("That is not a valid integer, please re enter a number");
            }
            if (isEmpty(theDeck)){
                theDeck.newDeck(thePile.getPile());
            }
        }
        System.out.println("Congratulations Player "+players.get(turnCounter).getPlayNum()+" is the winner!!!!");
    }

    /**
     * verifies if the card is a plus four to trigger effect
     * @param c the played card
     * @return true if the card is a plus four, false if otherwise
     */
    public static Boolean isPlusFour(Card c){
        return c.getType().equals("plus four");
    }
    /**
     * verifies if the card is a plus two to trigger effect
     * @param c the played card
     * @return true if the card is a plus two, false if otherwise
     */
    public static Boolean isPlusTwo(Card c){
        return c.getType().equals("plus two");
    }
    /**
     * verifies if the card is a reverse to trigger effect
     * @param c the played card
     * @return true if the card is a reverse, false if otherwise
     */
    public static Boolean isReverse(Card c){
        return c.getType().equals("reverse");
    }
    /**
     * verifies if the card is a skip to trigger effect
     * @param c the played card
     * @return true if the card is a skip, false if otherwise
     */
    public static Boolean isSkip(Card c){
        return c.getType().equals("skip");
    }
    /**
     * verifies if the card is a wild to trigger effect
     * @param c the played card
     * @return true if the card is a wild card, false if otherwise
     */
    public static Boolean isWild(Card c){
        return c.getType().equals("");
    }
    /**
     * verifies if there is a winner and ends the game
     * @param p the player
     * @return true is someone has an empty hand, false if otherwise
     */
    public Boolean isWinner(Player p){
        return p.hasWon();
    }
    /**
     * verifies if the deck is empty to reset it with the pile
     * @param d the deck
     * @return true if the deck is empty and needs reshuffled, false if otherwise
     */
    public static Boolean isEmpty(Deck d){
        return d.emptyDeck();
    }

    public static boolean playability(Card there, Card theirs){
        if (there.getType().equals(theirs.getType())
            || there.getColor().equals(theirs.getColor())
            || there.getColor().equals("Wild")) {
            return true;
        }
        return false;
    }

    /**
     * checks if any cards in the current players hand, on their turn, are playable
     * @param p the hand of the current player
     * @param c the top card of Pile
     * @return true if the hand has a playable card, false if otherwise
     */
    public static boolean handPlayability(Player p, Card c){
        for (int i = 0; i < p.handSize(); i++) {
            if (playability(p.getCard(i), c)){
                return true;
            }
        }
        return false;
    }

}
