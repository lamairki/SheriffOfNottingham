package edu.gvsu.cis.sheriffofnottingham;

import org.junit.Test;

import edu.gvsu.cis.sheriffofnottingham.cards.Deck;
import edu.gvsu.cis.sheriffofnottingham.cards.Discard;
import edu.gvsu.cis.sheriffofnottingham.cards.GoodsCard;
import edu.gvsu.cis.sheriffofnottingham.cards.GoodsType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class CardsTest {

    // Testing the Deck class
    @Test
    public void testDeckConstructor() {
        Deck myDeck = new Deck();
        assertEquals(204, myDeck.getDeckSize());
    }

    @Test
    public void testDrawCard() {
        Deck myDeck = new Deck();
        GoodsCard card0;
        int size;

        size = myDeck.getDeckSize();
        card0 = myDeck.drawCard();

        assertEquals(GoodsType.APPLES, card0.getType());
        assertEquals(size - 1, myDeck.getDeckSize());
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testFailDrawCard() {
        Deck myDeck = new Deck();

        for(int i = 0; i < 204; i++) {
            myDeck.drawCard();
        }

        myDeck.drawCard();
    }

    @Test
    public void testShuffle(){
        Deck nonShuff = new Deck();
        Deck shuff = new Deck();
        GoodsCard nonShuffCard;
        GoodsCard shuffCard;
        boolean sameness = true;

        shuff.shuffle();

        assertEquals(204, shuff.getDeckSize());

        for(int i = 0; i < 204; i++) {
            nonShuffCard = nonShuff.drawCard();
            shuffCard = shuff.drawCard();

            if(nonShuffCard.getType() != shuffCard.getType()) {
                sameness = false;
            }
        }

        assertFalse(sameness);
    }

    // Testing the Discard Class
    @Test
    public void testDiscardConstructor(){
        Deck myDeck = new Deck();
        myDeck.shuffle();
        Discard discard1 = new Discard(myDeck);

        assertEquals(5, discard1.getDiscardSize());
    }

    @Test(expected = NullPointerException.class)
    public void testDiscardFromNullDeck(){
        Discard discard = new Discard(null);
    }

    @Test
    public void testDrawFromDiscard(){
        Deck deck = new Deck();
        Discard discard = new Discard(deck);
        GoodsCard card;
        int size;

        size = discard.getDiscardSize();
        card = discard.draw();

        assertNotNull(card);
        assertEquals(size - 1, discard.getDiscardSize());
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testDrawFromEmptyDiscard(){
        Deck deck = new Deck();
        Discard discard = new Discard(deck);

        for(int i = 0; i < 5; i++) {
            discard.draw();
        }

        discard.draw();
    }

    @Test
    public void testLookAt() {
        Deck deck = new Deck();
        Discard discard = new Discard(deck);
        GoodsCard card;
        int size;

        size = discard.getDiscardSize();
        card = discard.lookAt(0);

        assertEquals(size, discard.getDiscardSize());
        assertNotNull(card);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testLookAtInvalid1() {
        Deck deck = new Deck();
        Discard discard = new Discard(deck);

        discard.lookAt(-1);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testLookAtInvalid2() {
        Deck deck = new Deck();
        Discard discard = new Discard(deck);

        discard.lookAt(5000);
    }

    // Goods Cards Tests
    @Test
    public void testCardConstructor() {
        GoodsCard cardA = new GoodsCard(GoodsType.APPLES);
        GoodsCard cardB = new GoodsCard(GoodsType.CHICKENS);
        GoodsCard cardC = new GoodsCard(GoodsType.BREAD);
        GoodsCard cardD = new GoodsCard(GoodsType.CHEESE);
        GoodsCard cardE = new GoodsCard(GoodsType.CROSSBOW);
        GoodsCard cardF = new GoodsCard(GoodsType.MEAD);
        GoodsCard cardG = new GoodsCard(GoodsType.SILK);
        GoodsCard cardH = new GoodsCard(GoodsType.PEPPER);

        assertNotNull(cardA);
        assertNotNull(cardB);
        assertNotNull(cardC);
        assertNotNull(cardD);
        assertNotNull(cardE);
        assertNotNull(cardF);
        assertNotNull(cardG);
        assertNotNull(cardH);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCardConstructorNullArg() {
        GoodsCard card = new GoodsCard(null);
    }

    @Test
    public void testAttributes() {
        GoodsCard card = new GoodsCard(GoodsType.APPLES);

        assertEquals(GoodsType.APPLES, card.getType());
        assertEquals(GoodsType.APPLES.getValue(), card.getValue());
        assertEquals(GoodsType.APPLES.getPenalty(), card.getPenalty());
        assertEquals(GoodsType.APPLES.isLegal(), card.isLegal());
    }
}
