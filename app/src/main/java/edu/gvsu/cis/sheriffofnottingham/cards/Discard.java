package edu.gvsu.cis.sheriffofnottingham.cards;

import java.util.ArrayList;

public class Discard {

    /* This is the initial size of the discard pile */
    private static final int START_SIZE = 5;
    private ArrayList<GoodsCard> goods = new ArrayList<>(START_SIZE);

    public Discard(Deck d) {
        while(goods.size() < START_SIZE) {
            goods.add(d.drawCard());
        }
    }

    /**
     * This method is intended to let the player look at the cards
     * in the discard piles.
     * @param index
     * @return
     */
    public GoodsCard lookAt(int index) {

        // Checking for index may be unnecessary as is, but leaves the option for more custom handling
        if(index < goods.size() && index >= 0) {
            return goods.get(index);
        }
        else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public GoodsCard draw() {
        if(!goods.isEmpty()) {
            return goods.remove(0);
        }
        else {
            // The player cannot draw from here if there are no cards
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    // Currently only used for testing
    public int getDiscardSize() {
        return this.goods.size();
    }
}