package edu.gvsu.cis.sheriffofnottingham.game;

import java.util.Scanner;

import edu.gvsu.cis.sheriffofnottingham.cards.Deck;
import edu.gvsu.cis.sheriffofnottingham.cards.Discard;

public class MARKET {

    Player player;
    Deck deck;
    Discard discard;

    public MARKET(Player player) {

        this.player = player;

        while (player.getHand().size() != player.getHandSize()) {

            Scanner scan = new Scanner(System.in);
            System.out.println("Draw from discard pile? (True/False");      //Unsure how to ask player this in Kotlin
            boolean drawFromDiscardPile = scan.nextBoolean();               //Continuation of previous comment

            if (drawFromDiscardPile) {
                player.getHand().add(discard.draw());
            } else {
                player.fillHand(deck);
            }
        }

        /**
         * Actually place the "set aside" cards on top of the discard pile
         * If the player's hand is full.
         */
        if (player.getHand().size() == player.getHandSize()){
            discard.placeOnDiscardPile(player.getTempDiscard());
        }
    }
}
