import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Game {
    /**
     * Will run the game and have affect the state based on special cards
     * @param args
     */
    public static void main(String[] args) {
        //list of all variables in main method
        int numPlayers=0;
        final int startingCards=5;
        ArrayList<Player> players = new ArrayList<>();
        Deck theDeck = new Deck();
        int turnCounter=0;
        int selectCard;
        String newColor="";
        Card played;
        Pile thePile = new Pile();
        Boolean isPlayable = false;
        Boolean normalTurnOrder = true;
        Boolean isValidColor = false;
        String playerEntry;

        Scanner scan = new Scanner(System.in);

        //asks how many people are playing
        while (numPlayers < 2 || numPlayers > 10){
            try {
                System.out.println("How many people will play this game? (Please type as an integer)");
                playerEntry = scan.next();
                numPlayers=Integer.parseInt(playerEntry);
                if (numPlayers < 2 || numPlayers > 10) {
                    System.out.println("Can't play with that number of players");
                }
            }catch(NumberFormatException I){
                System.out.println("That is not a number");
            }
        }


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

        //runs the game loop
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
                        //then player must draw until they could play a card
                        players.get(turnCounter).addCard(theDeck.draw());
                    }
                    else {
                        System.out.println("Please type in number to play your card.");
                        playerEntry = scan.next();
                        selectCard=Integer.parseInt(playerEntry);
                        played = players.get(turnCounter).getCard(selectCard - 1);
                        isPlayable = playability(played, thePile.topCard());

                        if (isPlayable) {
                            thePile.addCard(players.get(turnCounter).playCard(selectCard));
                            System.out.println("__________________________________________");
                        } else {
                            System.out.println("That card is not valid.");
                        }

                        //Occurs when a wild is played
                        if (isWild(played)) {
                            while(!isValidColor) {
                                System.out.println("Please choose a color.(lowercase)");
                                newColor = scan.next();
                                if (newColor.equals("yellow")||newColor.equals("red")||newColor.equals("green")||newColor.equals("blue")){
                                    isValidColor=true;
                                }else {
                                    System.out.println("That color is not valid.");
                                }
                            }
                            if (newColor.equals("yellow")) {
                                thePile.topCard().setColor("yellow");
                            } else if (newColor.equals("red")) {
                                thePile.topCard().setColor("red");
                            } else if (newColor.equals("green")) {
                                thePile.topCard().setColor("green");
                            } else if (newColor.equals("blue")) {
                                thePile.topCard().setColor("blue");
                            }
                        }
                        //occurs when a +4 card is played
                        else if (isPlusFour(played)) {
                            for (int i = 0; i < 4; i++) {
                                if (turnCounter+1==numPlayers){
                                    players.get(0).addCard(theDeck.draw());
                                }
                                players.get(turnCounter + 1).addCard(theDeck.draw());
                            }
                            turnCounter++;
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
                            if (!normalTurnOrder){
                                turnCounter++;
                                normalTurnOrder = true;
                            }
                            else{
                                turnCounter--;
                                normalTurnOrder = false;
                            }
                        }

                        //occurs when a plus two is played
                        else if (isPlusTwo(played)) {
                            for (int i = 0; i < 2; i++) {
                                if (turnCounter+1==numPlayers){
                                    players.get(0).addCard(theDeck.draw());
                                }
                                else{
                                    players.get(turnCounter + 1).addCard(theDeck.draw());
                                }
                            }
                            turnCounter++;
                            if (turnCounter>=numPlayers){
                                turnCounter=0;
                            }
                        }
                    }
                    //checks to see if the player has won
                    if(isWinner(players.get(turnCounter))){
                        System.out.println("Congratulations Player "+players.get(turnCounter).getPlayNum()+" is the winner!!!!");
                        System.exit(0);
                    }
                }
                //adds one to the turn counter
                turnCounter++;

                //checks to see if the turn counter needs reset
                if (turnCounter>=numPlayers) {
                    turnCounter = 0;
                    isPlayable=false;
                }else {
                    isPlayable=false;
                }

                //checks to see if the deck is emptied and the needs to reshuffle the pile into the deck
                if (isEmpty(theDeck)){
                    theDeck.newDeck(thePile.getPile());
                    thePile.ClearPile();
                    theDeck.shuffle();
                }

            }catch (NumberFormatException e) {
                //catches if a person tries to put in an invalid number
                System.out.println("That was not a number!");
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * verifies if the card is a plus four to trigger effect
     * @param c the played card
     * @return true if the card is a plus four, false if otherwise
     */
    public static Boolean isPlusFour(Card c){
        return c.getType().equals("+4");
    }
    /**
     * verifies if the card is a plus two to trigger effect
     * @param c the played card
     * @return true if the card is a plus two, false if otherwise
     */
    public static Boolean isPlusTwo(Card c){
        return c.getType().equals("+2");
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
        return c.getColor().equals("Wild");
    }
    /**
     * verifies if there is a winner and ends the game
     * @param p the player
     * @return true is someone has an empty hand, false if otherwise
     */
    public static Boolean isWinner(Player p){
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
