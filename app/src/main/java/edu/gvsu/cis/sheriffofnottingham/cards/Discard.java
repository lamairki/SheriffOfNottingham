package edu.gvsu.cis.sheriffofnottingham.cards;

import java.util.ArrayList;

public class Discard {

    /* This is the initial size of the discard pile */
    private static final int START_SIZE = 5;
    private ArrayList<GoodsCard> goods = new ArrayList<>(START_SIZE);
    private int topOfDiscard = -1;

    public Discard(Deck d) {
        while(goods.size() < START_SIZE) {
            goods.add(d.drawCard());
            topOfDiscard = topOfDiscard + 1;
        }
    }

    public GoodsCard peekAtTop() {
        if(topOfDiscard > -1)
            return this.goods.get(topOfDiscard);
        else
            return new GoodsCard(GoodsType.BACK);
    }

    public void removeFromTopOfStack() {
        this.goods.remove(topOfDiscard);
        topOfDiscard = topOfDiscard - 1;
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
    /**
     * Places the players temporary discard pile on top of the collective discard pile
     * Assuming that the top of the pile is the last element of the array
     * @param tempDiscardPile
     */
    public void placeOnDiscardPile(ArrayList<GoodsCard> tempDiscardPile){
        topOfDiscard = topOfDiscard + tempDiscardPile.size();
        this.goods.addAll(tempDiscardPile);

        }
}

