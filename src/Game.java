public class Game {
    /**
     * Will run the game and have affect the state based on special cards
     * @param args
     */
    public static void main(String[] args) {

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
     * @param c
     * @return
     */
    public Boolean isWinner(Card c){

        return false;
    }
    /**
     * verifies if the deck is empty to reset it with the pile
     * @param d
     * @return
     */
    public Boolean isEmpty(Deck d){

        return false;
    }
}
