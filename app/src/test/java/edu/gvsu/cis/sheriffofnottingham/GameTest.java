package edu.gvsu.cis.sheriffofnottingham;

import org.junit.Ignore;
import org.junit.Test;

import edu.gvsu.cis.sheriffofnottingham.cards.Deck;
import edu.gvsu.cis.sheriffofnottingham.cards.Discard;
import edu.gvsu.cis.sheriffofnottingham.cards.GoodsCard;
import edu.gvsu.cis.sheriffofnottingham.cards.GoodsType;
import edu.gvsu.cis.sheriffofnottingham.game.Board;
import edu.gvsu.cis.sheriffofnottingham.game.Player;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class GameTest {

    @Test
    public void testBoardConstructor() {
        Board board = new Board(3);

        assertNotNull(board);
    }

    @Test
    public void testGetDiscard() {
        Board board = new Board(3);
        Discard discard1, discard2;

        discard1 = board.getDiscard(1);
        discard2 = board.getDiscard(2);

        assertNotNull(discard1);
        assertNotNull(discard2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetDiscardInvalid1() {
        Board board = new Board(3);
        board.getDiscard(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetDiscardInvalid2() {
        Board board = new Board(3);
        board.getDiscard(3);
    }

    @Test
    public void testGetPlayers(){
        Board board = new Board(3);
        Player p0, p1, p2;

        p0 = board.getPlayer(0);
        p1 = board.getPlayer(1);
        p2 = board.getPlayer(2);

        assertNotNull(p0);
        assertNotNull(p1);
        assertNotNull(p2);
    }

    @Test
    public void testGetDeck() {
        Board board = new Board(3);
        Deck deck;

        deck = board.getDeck();

        assertNotNull(deck);
    }

    // Player Tests
    @Test
    public void testPlayerConstructor() {
        Player p = new Player(0);

        assertNotNull(p);
    }

    @Test
    public void testFillHand() {
        Deck deck = new Deck();
        Player p = new Player(0);
        int handSize;

        p.fillHand(deck);
        assertNotNull(p.getHand());
        handSize = p.getHand().size();

        assertEquals(5, handSize);
    }

    @Test
    public void testAddToBag() {
        Deck deck = new Deck();
        Player p = new Player(0);
        int size;

        p.fillHand(deck);
        p.addToBag(p.getHand().get(0));
        size = p.getPlayerBag().size();

        assertEquals(1, size);

        p.addToBag(p.getHand().get(1));
        size = p.getPlayerBag().size();

        assertEquals(2, size);
    }

    @Test
    public void testAddBagToMarket() {
        Deck deck = new Deck();
        Player p = new Player(0);

        p.fillHand(deck);
        p.addToBag(p.getHand().get(0));
        p.addToBag(p.getHand().get(1));
        p.addToBag(p.getHand().get(2));

        p.addBagToMarket();

        assertEquals(3, p.getMarket().size());
    }

    @Test
    public void testTakeFromBag(){
        Deck deck = new Deck();
        Player p0 = new Player(0);
        Player p1 = new Player(1);
        int bagSize;
        GoodsCard card;

        p0.fillHand(deck);
        p0.addToBag(p0.getHand().get(0));
        p0.addToBag(p0.getHand().get(1));
        p0.addToBag(p0.getHand().get(2));
        bagSize = p0.getPlayerBag().size();

        assertEquals(3, bagSize);

        card = p0.takeFromBag(GoodsType.APPLES);
        bagSize = p0.getPlayerBag().size();

        assertNotNull(card);
        assertEquals(2, bagSize);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTakeFromBagNotFound() {
        Deck deck = new Deck();
        Player p0 = new Player(0);

        p0.fillHand(deck);
        p0.addToBag(p0.getHand().get(0));
        p0.addToBag(p0.getHand().get(1));
        p0.addToBag(p0.getHand().get(2));

        p0.takeFromBag(GoodsType.SILK);
    }

    @Test
    public void testTakeFromBagAnyExcept() {
        Deck deck = new Deck();
        Player p0 = new Player(0);
        GoodsCard card;

        p0.fillHand(deck);
        p0.addToBag(p0.getHand().get(0));
        p0.addToBag(p0.getHand().get(1));
        p0.addToBag(p0.getHand().get(2));

        card = p0.takeFromBagAnyExcept(GoodsType.SILK);

        assertNotNull(card);
    }

    @Test
    public void testAddGold() {
        Player p = new Player(0);
        int goldAmount;

        goldAmount = p.getGold();

        assertEquals(10, goldAmount);

        p.addGold(3);
        goldAmount = p.getGold();

        assertEquals(13, goldAmount);
    }

    @Test
    public void testTakeGold(){
        Player p = new Player(0);
        int goldAmount;

        goldAmount = p.getGold();

        assertEquals(10, goldAmount);

        p.takeGold(3);
        goldAmount = p.getGold();

        assertEquals(7, goldAmount);
    }

    @Ignore("Way to handle invalid amounts is not yet implemented")
    @Test//(expected = IllegalArgumentException.class)
    public void testTakeGoldInvalid() {
        Player p = new Player(0);

        p.takeGold(-3); //Currently not failing
        p.takeGold(5000);
    }

    @Test
    public void testSetSheriff() {
        Player p = new Player(0);

        p.setSheriff(false);
        assertFalse(p.isSheriff());

        p.setSheriff(true);
        assertTrue(p.isSheriff());
    }
}
