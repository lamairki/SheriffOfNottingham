package edu.gvsu.cis.sheriffofnottingham.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.ToggleButton
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import edu.gvsu.cis.sheriffofnottingham.R
import edu.gvsu.cis.sheriffofnottingham.cards.GoodsCard
import edu.gvsu.cis.sheriffofnottingham.cards.GoodsType
import edu.gvsu.cis.sheriffofnottingham.game.Player
import edu.gvsu.cis.sheriffofnottingham.models.PlayViewModel
import java.util.*

lateinit var playViewModelHandDiscard: PlayViewModel
private const val BAG_MAX = 5
private const val BAG_MIN = 1
lateinit var playerHandDiscard: MutableLiveData<Player>
var numPlayersHandDiscard: Int = 0
var discardCards = 0

lateinit var discardHand: ArrayList<GoodsCard>



class PlayerHandToDiscardFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_player_hand_to_discard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        playViewModelHandDiscard = ViewModelProvider(requireActivity()).get(PlayViewModel::class.java)

        numPlayersHandDiscard = playViewModelHandDiscard.numPlayers.value!!

        playerHandDiscard = playViewModelHandDiscard.currPlayer.value!!


        val card_1_hand_to_discard = view.findViewById<ToggleButton>(R.id.card_1_hand_to_discard)
        val card_2_hand_to_discard = view.findViewById<ToggleButton>(R.id.card_2_hand_to_discard)
        val card_3_hand_to_discard = view.findViewById<ToggleButton>(R.id.card_3_hand_to_discard)
        val card_4_hand_to_discard = view.findViewById<ToggleButton>(R.id.card_4_hand_to_discard)
        val card_5_hand_to_discard = view.findViewById<ToggleButton>(R.id.card_5_hand_to_discard)
        val card_6_hand_to_discard = view.findViewById<ToggleButton>(R.id.card_6_hand_to_discard)

        val cards = arrayOf(card_1_hand_to_discard, card_2_hand_to_discard, card_3_hand_to_discard, card_4_hand_to_discard, card_5_hand_to_discard, card_6_hand_to_discard)

        val nameTextView = view.findViewById<TextView>(R.id.player_name_hand_to_discard)
        nameTextView.setText(playerHandDiscard.value?.playerName.toString())

        view.findViewById<Button>(R.id.return_button_hand_to_discard).setOnClickListener {
            findNavController().navigate(R.id.action_playerHandToDiscardFragment_to_deckFragment)
        }

        view.findViewById<Button>(R.id.discard_button).setOnClickListener {
            var cardsToDiscard: ArrayList<GoodsCard> = ArrayList<GoodsCard>()
            for (i in 0..(discardHand.size-1)) {
                if (cards.get(i).isChecked)
                    cardsToDiscard.add(discardHand.get(i))
            }
            when (playerHandDiscard.value?.playerNum) {
                1 -> playViewModelHandDiscard.addCardsToTempDiscard(cardsToDiscard, playViewModelHandDiscard.player1)
                2 -> playViewModelHandDiscard.addCardsToTempDiscard(cardsToDiscard, playViewModelHandDiscard.player2)
                3 -> playViewModelHandDiscard.addCardsToTempDiscard(cardsToDiscard, playViewModelHandDiscard.player3)
            }

            when (playerHandDiscard.value?.playerNum) {
                1 -> discardHand = playViewModelHandDiscard.getPlayerHand(1) as ArrayList<GoodsCard>
                2 -> discardHand = playViewModelHandDiscard.getPlayerHand(2) as ArrayList<GoodsCard>
                3 -> discardHand = playViewModelHandDiscard.getPlayerHand(3) as ArrayList<GoodsCard>
            }
            refreshCards(cards)
            findNavController().navigate(R.id.action_playerHandToDiscardFragment_to_discardFragment)
        }

        setupCardListeners(cards)

        when (playerHandDiscard.value?.playerNum) {
            1 -> discardHand = playViewModelHandDiscard.getPlayerHand(1) as ArrayList<GoodsCard>
            2 -> discardHand = playViewModelHandDiscard.getPlayerHand(2) as ArrayList<GoodsCard>
            3 -> discardHand = playViewModelHandDiscard.getPlayerHand(3) as ArrayList<GoodsCard>
        }
        refreshCards(cards)
    }

    private fun setupCardListeners(cards: Array<ToggleButton>) {
        for (i in cards) {
            i.setOnCheckedChangeListener { _, isChecked ->
                if (discardCards >= BAG_MAX) {
                    if (isChecked) {
                        i.setChecked(false)
                        discardCards = BAG_MAX
                    } else {
                        discardCards--
                    }
                } else {
                    if (isChecked) {
                        discardCards++
                    } else {
                        discardCards--
                    }
                }
            }
        }
    }

    private fun refreshCards(cards: Array<ToggleButton>) {
        var c: Int = 0
        for (i in cards) {
            if (c < discardHand.size) {
                when (discardHand.get(c).type) {
                    GoodsType.CHICKENS -> i.setBackground(ContextCompat.getDrawable(requireActivity(), R.drawable.legal_goods_chicken))
                    GoodsType.BREAD -> i.setBackground(ContextCompat.getDrawable(requireActivity(), R.drawable.legal_goods_bread))
                    GoodsType.CHEESE -> i.setBackground(ContextCompat.getDrawable(requireActivity(), R.drawable.legal_goods_cheese))
                    GoodsType.APPLES -> i.setBackground(ContextCompat.getDrawable(requireActivity(), R.drawable.legal_goods_apples))
                    GoodsType.CROSSBOW -> i.setBackground(ContextCompat.getDrawable(requireActivity(), R.drawable.contraband_crossbow))
                    GoodsType.SILK -> i.setBackground(ContextCompat.getDrawable(requireActivity(), R.drawable.contraband_silk))
                    GoodsType.MEAD -> i.setBackground(ContextCompat.getDrawable(requireActivity(), R.drawable.contraband_mead))
                    GoodsType.PEPPER -> i.setBackground(ContextCompat.getDrawable(requireActivity(), R.drawable.contraband_pepper))
                    GoodsType.BACK -> {}
                    null -> i.setBackground(ContextCompat.getDrawable(requireActivity(), R.color.transparent))
                }
            }
            else {
                i.setBackground(ContextCompat.getDrawable(requireActivity(), R.color.transparent))
                i.setEnabled(false)
            }
            c++
        }
        for (i in cards)
            i.setChecked(false)
    }
}