package edu.gvsu.cis.sheriffofnottingham.cards;

/* Author: Dominic Smith
 * Description: This is the card type enum. It is what determines
 *              how each card behaves.
 */

public enum GoodsType {
    CHICKENS(4, 2, true),
    BREAD(3, 2, true),
    CHEESE(3, 2, true),
    APPLES(2, 2, true),
    CROSSBOW(9, 4, false),
    SILK(8, 4, false),
    MEAD(7, 4, false),
    PEPPER(6, 4, false),
    BACK(0,0,false);

    private final int value;
    private final int penalty;
    private final boolean legal;

    GoodsType(int value, int penalty, boolean legal) {
        this.value = value;
        this.penalty = penalty;
        this.legal = legal;
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
