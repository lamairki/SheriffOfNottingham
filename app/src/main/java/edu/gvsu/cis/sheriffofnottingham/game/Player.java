package edu.gvsu.cis.sheriffofnottingham.game;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;

import edu.gvsu.cis.sheriffofnottingham.cards.Deck;
import edu.gvsu.cis.sheriffofnottingham.cards.GoodsCard;
import edu.gvsu.cis.sheriffofnottingham.cards.GoodsType;

/* Author: Dominic Smith
 * Description: This is the player object. It keeps track of the
 *              cards in your hand, market, and whether or not they
 *              are the sheriff.
 */

public class Player implements Serializable {

    private static final int HAND_SIZE = 6;
    private static final int START_GOLD = 10;
    private String playerName = "";
    private ArrayList<GoodsCard> hand = new ArrayList<>(HAND_SIZE);
    private ArrayList<GoodsCard> market = new ArrayList<>();
    private ArrayList<GoodsCard> playerBag = new ArrayList<>();
    private ArrayList<GoodsCard> tempDiscard = new ArrayList<>();
    private ArrayList<GoodsCard> chickenStand = new ArrayList<GoodsCard>();
    private ArrayList<GoodsCard> breadStand = new ArrayList<GoodsCard>();
    private ArrayList<GoodsCard> cheeseStand = new ArrayList<GoodsCard>();
    private ArrayList<GoodsCard> applesStand = new ArrayList<GoodsCard>();
    private ArrayList<GoodsCard> contrabandStand = new ArrayList<GoodsCard>();
    private boolean sheriff;
    private final int playerNum;
    private int gold;

    public Player(int playerNum) {
        this.playerNum = playerNum;
        this.gold = START_GOLD;
    }

    public Player(int playerNum, String pName) {
        this.playerNum = playerNum;
        this.playerName = pName;
        this.gold = START_GOLD;
    }

    public int getGold() { return this.gold; }

    /**
     * Getter method for player name
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Getter for player number
     */
    public int getPlayerNum() { return playerNum; }

    public ArrayList<GoodsCard> getTempDiscard() { return tempDiscard; }

    /**
     * This method fills the rest of the cards in their hand
     * from the deck. This is NOT to be used when drawing from
     * the discard piles.
     */
    public void fillHand(Deck d) {
        while(hand.size() < HAND_SIZE) {
            hand.add(d.drawCard());
        }
    }

    public ArrayList<GoodsCard> getHand() {
        return this.hand;
    }

    public int getHandSize(){
        return this.HAND_SIZE;
    }

    public ArrayList<GoodsCard> getPlayerBag() { return this.playerBag; }

    /**
     * This method takes the entire contents of the bag and
     * adds them to their market.
     */
    public void addBagToMarket() {
        while(!playerBag.isEmpty()) {
            market.add(playerBag.remove(0));
        }
    }

    /**
     * This method added to add cards to the players bags from their hand
     */
    public void addCardToBag(GoodsCard gc) {
        this.playerBag.add(gc);
        this.hand.remove(gc);
    }

    /**
     * This method added to add cards to the players hand from their bag
     */
    public void addCardToHand(GoodsCard gc) {
        this.hand.add(gc);
        this.playerBag.remove(gc);
    }

    /**
     * This method added to add cards to the players temp discard from their hand
     */
    public void addCardToTempDiscard(GoodsCard gc) {
        this.tempDiscard.add(gc);
        this.hand.remove(gc);
    }

    /**
     * This method added to clear players Bag following sheriff inspection
     */
    public void emptyBag() {
        this.playerBag.clear();
    }

    /**
     * This method added to clear players temp discard stack when discarding cards
     */
    public void clearTempDiscard() {
        this.tempDiscard.clear();
    }

    /**
     * This method added to add cards to the players temp discard from their hand
     */
    public void removeCardFromTempDiscard(GoodsCard gc) {
        this.hand.add(gc);
        this.tempDiscard.remove(gc);
    }

    public void addGold(int gold) {

        if(gold < 0) {
            throw new IllegalArgumentException();
        }
        this.gold += gold;
    }

    public void addCardsToStand( ArrayList<GoodsCard> cards) {
        for ( int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getType() == GoodsType.CHICKENS) {
                this.chickenStand.add(cards.get(i));
            }
            else if (cards.get(i).getType() == GoodsType.BREAD) {
                this.breadStand.add(cards.get(i));
            }
            else if (cards.get(i).getType() == GoodsType.CHEESE) {
                this.cheeseStand.add(cards.get(i));
            }
            else if (cards.get(i).getType() == GoodsType.APPLES) {
                this.applesStand.add(cards.get(i));
            }
            else{
                this.contrabandStand.add(cards.get(i));
            }
        }
    }

    public ArrayList<GoodsCard> getApplesStand() {
        return this.applesStand;
    }

    public ArrayList<GoodsCard> getChickenStand() {
        return this.chickenStand;
    }

    public ArrayList<GoodsCard> getBreadStand() {
        return this.breadStand;
    }

    public ArrayList<GoodsCard> getCheeseStand() {
        return this.cheeseStand;
    }

    public ArrayList<GoodsCard> getContrabandStand() {
        return this.contrabandStand;
    }



    public void takeGold(int gold) {

        if(gold < this.gold) {
            this.gold -= gold;
        }
        else {
            //TODO look at rules for how players pay more than they own in gold
            //PLayer must pay with legal goods first (the value = gold) if no legal goods
            //then must pay with contraband.  If all legal and illegal goods have been exhausted,
            //then the debt is considered paid.
            //(If you owe 1 coin but only have an apple (value 2) to pay with, you forfeit the
            //entire value of the apple
        }
    }
}
