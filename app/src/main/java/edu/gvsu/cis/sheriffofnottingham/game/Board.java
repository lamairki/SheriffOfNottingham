package edu.gvsu.cis.sheriffofnottingham.game;

import edu.gvsu.cis.sheriffofnottingham.cards.Deck;
import edu.gvsu.cis.sheriffofnottingham.cards.Discard;

public class Board {

    private Player[] players;
    private Deck deck;
    private Discard discard1;
    private Discard discard2;

    public Board(int numPlayers) {
        deck = new Deck();
        discard1 = new Discard(deck);
        discard2 = new Discard(deck);
        players = new Player[numPlayers];

        for(int i = 0; i < numPlayers; i++) {
            players[i] = new Player(i);
            players[i].fillHand(deck);
        }
    }

    public Player getPlayer(int playerNum) {
        return players[playerNum];
    }

    public Deck getDeck() {
        return deck;
    }

    public Discard getDiscard(int pile) {
        if(pile == 1) {
            return discard1;
        }
        else if(pile == 2) {
            return discard2;
        }
        else {
            throw new IllegalArgumentException();
        }
    }
}
