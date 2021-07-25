package edu.gvsu.cis.sheriffofnottingham.cards;

/* Author: Dominic Smith
 * Description: This is the deck object. It is what holds all of
 *              the cards at the start of the game.
 */

import java.util.ArrayList;
import java.util.Random;

public class Deck {

    private static final int NUM_APPLES = 48;
    private static final int NUM_BREAD = 36;
    private static final int NUM_CHEESE = 36;
    private static final int NUM_CHICKENS = 24;
    private static final int NUM_PEPPER = 22;
    private static final int NUM_MEAD = 21;
    private static final int NUM_SILK = 12;
    private static final int NUM_CROSSBOW = 5;
    private static final int DECK_SIZE = NUM_APPLES + NUM_BREAD + NUM_CHICKENS + NUM_CHEESE +
                                         NUM_PEPPER + NUM_MEAD + NUM_SILK + NUM_CROSSBOW;

    private ArrayList<GoodsCard> goods = new ArrayList<>(DECK_SIZE);

    /**
     * Default constructor creates every card in the game and adds it
     * to the deck.
     */
    public Deck() {

        // Creates the appropriate amount of cards for each type
        for(int i = 0; i < NUM_APPLES; i++) {
            goods.add(new GoodsCard(GoodsType.APPLES));
        }
        for(int i = 0; i < NUM_CHEESE; i++) {
            goods.add(new GoodsCard(GoodsType.CHEESE));
        }
        for(int i = 0; i < NUM_BREAD; i++) {
            goods.add(new GoodsCard(GoodsType.BREAD));
        }
        for(int i = 0; i < NUM_CHICKENS; i++) {
            goods.add(new GoodsCard(GoodsType.CHICKENS));
        }
        for(int i = 0; i < NUM_PEPPER; i++) {
            goods.add(new GoodsCard(GoodsType.PEPPER));
        }
        for(int i = 0; i < NUM_MEAD; i++) {
            goods.add(new GoodsCard(GoodsType.MEAD));
        }
        for(int i = 0; i < NUM_SILK; i++) {
            goods.add(new GoodsCard(GoodsType.SILK));
        }
        for(int i = 0; i < NUM_CROSSBOW; i++) {
            goods.add(new GoodsCard(GoodsType.CROSSBOW));
        }
    }

    /**
     * Takes the deck of cards and shuffles them into a different order
     */
    public void shuffle() {
        ArrayList<GoodsCard> copyDeck = new ArrayList<>(DECK_SIZE);
        int randIndex = 0;
        Random rand = new Random();

        // Moves all cards from the deck to the copy in a random position
        for(int i = 0; i < DECK_SIZE; i++) {

            // Generates a random number from 0 - copyDeck.size()
            randIndex = rand.nextInt(copyDeck.size() + 1);

            // Cards must be removed from the original so there are no duplicates
            copyDeck.add(randIndex, this.goods.remove(0));
        }

        // Returns all cards to the original deck
        this.goods.addAll(copyDeck);
    }

    /**
     * Draw one card from the deck
     * @return the card drawn.
     */
    public GoodsCard drawCard() {
        if(!goods.isEmpty()) {
            return goods.remove(0);
        }
        else {
            // TODO Add a shuffle function here so calls don't need to worry if the deck is empty
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    // This is currently only used in testing
    public int getDeckSize() {
        return this.goods.size();
    }
}
