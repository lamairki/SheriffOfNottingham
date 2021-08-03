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
        if(index >= goods.size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        else {
            return goods.get(index);
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

    /**
     * Places the players temporary discard pile on top of the collective discard pile
     * Assuming that the top of the pile is the last element of the array
     * @param tempDiscardPile
     */
    public void placeOnDiscardPile(ArrayList<GoodsCard> tempDiscardPile){
        goods.addAll(tempDiscardPile);

        }
    }

}