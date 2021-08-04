package edu.gvsu.cis.sheriffofnottingham.models

import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.gvsu.cis.sheriffofnottingham.cards.Deck
import edu.gvsu.cis.sheriffofnottingham.cards.Discard
import edu.gvsu.cis.sheriffofnottingham.cards.GoodsCard
import edu.gvsu.cis.sheriffofnottingham.game.GamePhase
import edu.gvsu.cis.sheriffofnottingham.game.Player
import java.util.*

class PlayViewModel : ViewModel() {
    private var _numPlayers = MutableLiveData<Int>()
    private var _currPlayer = MutableLiveData<MutableLiveData<Player>>()
    private var _sheriff = MutableLiveData<MutableLiveData<Player>>()
    private var _player1 = MutableLiveData<Player>()
    private var _player2 = MutableLiveData<Player>()
    private var _player3 = MutableLiveData<Player>()
    private var _player4 = MutableLiveData<Player>()
    private var _player5 = MutableLiveData<Player>()
    private var _deck = MutableLiveData<Deck>()
    private var _discardStack = MutableLiveData<Discard>()
    private var _gamePhase = MutableLiveData<GamePhase>()


    val currPlayer
        get() = _currPlayer
    val sheriff
        get() = _sheriff
    val numPlayers
        get() = _numPlayers
    val player1
        get() = _player1
    val player2
        get() = _player2
    val player3
        get() = _player3
    val player4
        get() = _player4
    val player5
        get() = _player5
    val deck
        get() = _deck
    val gamePhase
        get() = _gamePhase
    val discardStack
        get() = _discardStack


    /**
     * This method fills the rest of the cards in their hand
     * from the deck. This is NOT to be used when drawing from
     * the discard piles.
     */
    fun fillHand(p: MutableLiveData<Player>, d: MutableLiveData<Deck>) {
        while (p.value?.hand?.size!! < 6) {
            p.value?.hand?.add(d.value?.drawCard())
        }
    }

    /**
     * Takes the deck of cards and shuffles them into a different order
     */
    fun shuffleDeck() {
        this.deck.value?.shuffle()
    }

    fun getPlayerHand(playerNum: Int): ArrayList<GoodsCard>? {
        when (playerNum) {
            1 -> return player1.value?.hand
            2 -> return player2.value?.hand
            3 -> return player3.value?.hand
            4 -> return player4.value?.hand
            5 -> return player5.value?.hand
        }
        return null
    }

    fun getPlayerBag(playerNum: Int): ArrayList<GoodsCard>? {
        when (playerNum) {
            1 -> return player1.value?.playerBag
            2 -> return player2.value?.playerBag
            3 -> return player3.value?.playerBag
            4 -> return player4.value?.playerBag
            5 -> return player5.value?.playerBag
        }
        return null
    }

    fun getPlayerTempDiscard(playerNum: Int): ArrayList<GoodsCard>? {
        when (playerNum) {
            1 -> return player1.value?.tempDiscard
            2 -> return player2.value?.tempDiscard
            3 -> return player3.value?.tempDiscard
            4 -> return player4.value?.tempDiscard
            5 -> return player5.value?.tempDiscard
        }
        return null
    }

    fun addCardsToBag(cardsToAdd: ArrayList<GoodsCard>, p: MutableLiveData<Player>) {
        for (card in cardsToAdd) {
            p.value?.addCardToBag(card)
        }
    }

    fun addCardsToHand(cardsToAdd: ArrayList<GoodsCard>, p: MutableLiveData<Player>) {
        for (card in cardsToAdd) {
            p.value?.addCardToHand(card)
        }
    }

    fun addCardsToTempDiscard(cardsToAdd: ArrayList<GoodsCard>, p: MutableLiveData<Player>) {
        for (card in cardsToAdd) {
            p.value?.addCardToTempDiscard(card)
        }
    }

    fun removeCardsFromTempDiscard(cardsToAdd: ArrayList<GoodsCard>, p: MutableLiveData<Player>) {
        for (card in cardsToAdd) {
            p.value?.removeCardFromTempDiscard(card)
        }
    }

    fun addCardsToDiscardStack(p: MutableLiveData<Player>, cardsToDiscard: ArrayList<GoodsCard>?, d: MutableLiveData<Discard>) {
        d.value?.placeOnDiscardPile(cardsToDiscard)
        p.value?.clearTempDiscard()
    }

    fun cardToHandFromDiscard(p: MutableLiveData<Player>) {
        val cardToAdd: GoodsCard? = discardStack.value?.peekAtTop()
        p.value?.hand?.add(cardToAdd)
        discardStack.value?.removeFromTopOfStack()
    }

    fun turnComplete(p: MutableLiveData<Player>) {
        // Determines player to follow player 1
        if (p.value == player1.value) {
            if (sheriff.value == player2)
                currPlayer.value = player3
            else
                currPlayer.value = player2
        }

        // Determines player to follow player 2
        if (p.value == player2.value) {
            if (numPlayers.value == 3)
                if (sheriff.value == player3)
                    currPlayer.value = player1
                else
                    currPlayer.value = player3
            else
                if (sheriff.value == player3)
                    currPlayer.value = player4
                else
                    currPlayer.value = player3
        }

        // Determines player to follow player 3
        if (p.value == player3.value) {
            if (numPlayers.value == 3)
                if (sheriff.value == player1)
                    currPlayer.value = player2
                else
                    currPlayer.value = player1
            else if (numPlayers.value == 4)
                if (sheriff.value == player4)
                    currPlayer.value = player1
                else
                    currPlayer.value = player4
            else
                if (sheriff.value == player4)
                    currPlayer.value = player5
                else
                    currPlayer.value = player4
        }

        // Determines player to follow player 4
        if (p.value == player4.value) {
            if (numPlayers.value == 4)
                if (sheriff.value == player1)
                    currPlayer.value = player2
                else
                    currPlayer.value = player1
            else
                if (sheriff.value == player5)
                    currPlayer.value = player1
                else
                    currPlayer.value = player5
        }

        // Determines player to follow player 5
        if (p.value == player5.value) {
            if (sheriff.value == player1)
                currPlayer.value = player2
            else
                currPlayer.value = player1
        }
    }

    fun nextPhase() {
        when (gamePhase.value) {
            GamePhase.MARKET -> gamePhase.value = GamePhase.LOAD_BAG
            GamePhase.LOAD_BAG -> gamePhase.value = GamePhase.DECLARATION
            GamePhase.DECLARATION -> gamePhase.value = GamePhase.INSPECTION
            GamePhase.INSPECTION -> gamePhase.value = GamePhase.ENF_OF_ROUND
            GamePhase.ENF_OF_ROUND -> {
                gamePhase.value = GamePhase.MARKET
                newSheriff(sheriff.value)
            }
        }
    }

    fun newSheriff(s: MutableLiveData<Player>?) {
        if (s?.value == player1.value) {
            sheriff.value = player2
            currPlayer.value = player3
        }
        if (s?.value == player2.value) {
            if (numPlayers.value == 3) {
                sheriff.value = player3
                currPlayer.value = player1
            }
            else {
                sheriff.value = player3
                currPlayer.value = player4
            }
        }
        if (s?.value == player3.value) {
            if (numPlayers.value == 3) {
                sheriff.value = player1
                currPlayer.value = player2
            }
            else if (numPlayers.value == 4){
                sheriff.value = player4
                currPlayer.value = player1
            }
            else {
                sheriff.value = player4
                currPlayer.value = player5
            }
        }
        if (s?.value == player4.value) {
            if (numPlayers.value == 4) {
                sheriff.value = player1
                currPlayer.value = player2
            }
            else {
                sheriff.value = player5
                currPlayer.value = player1
            }
        }
        if (s?.value == player5.value) {
            sheriff.value = player1
            currPlayer.value = player2
        }
    }
}