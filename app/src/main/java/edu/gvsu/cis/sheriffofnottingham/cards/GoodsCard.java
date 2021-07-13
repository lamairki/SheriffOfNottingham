package edu.gvsu.cis.sheriffofnottingham.cards;

/* Author: Dominic Smith
 * Description: This is the card object. A collection of these
 *              cards is what makes up a deck, discard, hand, etc.
 */

public class GoodsCard {

    private final GoodsType type;
    private final int value;
    private final int penalty;
    private final boolean legal;

    public GoodsCard(GoodsType type) {

        if(type != null) {
            this.type = type;
            this.value = type.getValue();
            this.penalty = type.getPenalty();
            this.legal = type.isLegal();
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    public GoodsType getType() {
        return type;
    }

    public int getValue() {
        return value;
    }

    public int getPenalty() {
        return penalty;
    }

    public boolean isLegal() {
        return legal;
    }
}
