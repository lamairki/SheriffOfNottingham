package edu.gvsu.cis.sheriffofnottingham.game;

import java.lang.reflect.Array;
import java.util.ArrayList;

import edu.gvsu.cis.sheriffofnottingham.cards.Deck;
import edu.gvsu.cis.sheriffofnottingham.cards.GoodsCard;
import edu.gvsu.cis.sheriffofnottingham.cards.GoodsType;

/* Author: Dominic Smith
 * Description: This is the player object. It keeps track of the
 *              cards in your hand, market, and whether or not they
 *              are the sheriff.
 */

public class Player {

    private static final int HAND_SIZE = 5;
    private static final int START_GOLD = 10;
    private ArrayList<GoodsCard> hand = new ArrayList<>(HAND_SIZE);
    private ArrayList<GoodsCard> market = new ArrayList<>();
    private ArrayList<GoodsCard> playerBag = new ArrayList<>();
    private boolean sheriff;
    private final int playerNum;
    private int gold;

    public Player(int playerNum) {
        this.playerNum = playerNum;
        this.gold = START_GOLD;
    }

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

    public void addToBag(GoodsCard card) {
        this.playerBag.add(card);
    }

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
     * This method is planned to be used when players take
     * from other players bags.
     * @param cardType
     * @return
     */
    public GoodsCard takeFromBag(GoodsType cardType){

        for(int i = 0; i < playerBag.size(); i++) {

            if(playerBag.get(i).getType() == cardType) {
                return playerBag.remove(i);
            }
        }
        throw new IllegalArgumentException("Type not found in this bag.");
    }

    /**
     * This method is similar to the one above but it takes
     * any card that is not the type specified. Useful when
     * taking "everything but the chickens". Returns 1 card
     * at a time.
     * @param cardType
     * @return
     */
    public GoodsCard takeFromBagAnyExcept(GoodsType cardType){

        for(int i = 0; i < playerBag.size(); i++) {

            if(playerBag.get(i).getType() != cardType) {
                return playerBag.remove(i);
            }
        }
        throw new IllegalArgumentException("All cards in bag are this type.");
    }

    public boolean isSheriff() {
        return sheriff;
    }

    public void setSheriff(boolean sheriff) {
        this.sheriff = sheriff;
    }

    public void addGold(int gold) {

        if(gold < 0) {
            throw new IllegalArgumentException();
        }
        this.gold += gold;
    }

    public void takeGold(int gold) {

        if(gold < this.gold) {
            this.gold -= gold;
        }
        else {
            //TODO look at rules for how players pay more than they own in gold
        }
    }

    public ArrayList<GoodsCard> getHand() {
        return this.hand;
    }

    public ArrayList<GoodsCard> getPlayerBag() {
        return this.playerBag;
    }

    public ArrayList<GoodsCard> getMarket() {
        return this.market;
    }

    public int getGold() {
        return this.gold;
    }
}
