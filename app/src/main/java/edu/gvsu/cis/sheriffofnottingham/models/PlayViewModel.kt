package edu.gvsu.cis.sheriffofnottingham.models

import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.gvsu.cis.sheriffofnottingham.cards.Deck
import edu.gvsu.cis.sheriffofnottingham.cards.GoodsCard
import edu.gvsu.cis.sheriffofnottingham.game.Player
import java.util.*

class PlayViewModel : ViewModel() {
    private var _numPlayers = MutableLiveData<Int>()
    private var _player1 = MutableLiveData<Player>()
    private var _player2 = MutableLiveData<Player>()
    private var _player3 = MutableLiveData<Player>()
    private var _player4 = MutableLiveData<Player>()
    private var _player5 = MutableLiveData<Player>()
    private var _deck = MutableLiveData<Deck>()
    private var _currPlayer = MutableLiveData<Player>()


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

    fun addCardsToBag(cardsToAdd: ArrayList<GoodsCard>, p: MutableLiveData<Player>) {
        for (card in cardsToAdd) {
            p.value?.addCardToBag(card)
        }
    }
}