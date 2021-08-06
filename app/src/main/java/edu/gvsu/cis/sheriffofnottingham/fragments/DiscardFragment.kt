package edu.gvsu.cis.sheriffofnottingham.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.ToggleButton
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import edu.gvsu.cis.sheriffofnottingham.R
import edu.gvsu.cis.sheriffofnottingham.cards.GoodsCard
import edu.gvsu.cis.sheriffofnottingham.cards.GoodsType
import edu.gvsu.cis.sheriffofnottingham.game.Player
import edu.gvsu.cis.sheriffofnottingham.models.PlayViewModel
import java.util.ArrayList

lateinit var playViewModelDiscard: PlayViewModel
private const val BAG_MAX = 5
private const val BAG_MIN = 1
lateinit var playerDiscard: MutableLiveData<Player>
var numPlayersDiscard: Int = 0
var numCardsInDiscard = 0
lateinit var cardsInDiscard: ArrayList<GoodsCard>


class DiscardFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_discard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        playViewModelDiscard = ViewModelProvider(requireActivity()).get(PlayViewModel::class.java)

        numPlayersDiscard = playViewModelHandDiscard.numPlayers.value!!

        playerDiscard = playViewModelHandDiscard.currPlayer.value!!


        val card_1_discard = view.findViewById<ToggleButton>(R.id.card_1_discard)
        val card_2_discard = view.findViewById<ToggleButton>(R.id.card_2_discard)
        val card_3_discard = view.findViewById<ToggleButton>(R.id.card_3_discard)
        val card_4_discard = view.findViewById<ToggleButton>(R.id.card_4_discard)
        val card_5_discard = view.findViewById<ToggleButton>(R.id.card_5_discard)
        val card_6_discard = view.findViewById<ToggleButton>(R.id.card_6_discard)

        val cards = arrayOf(card_1_discard, card_2_discard, card_3_discard, card_4_discard, card_5_discard, card_6_discard)

        val nameTextView = view.findViewById<TextView>(R.id.player_name_discard)
        nameTextView.setText(playerDiscard.value?.playerName.toString())

        view.findViewById<Button>(R.id.button_back_to_discard_hand).setOnClickListener {
            findNavController().navigate(R.id.action_discardFragment_to_playerHandToDiscardFragment)
        }

        view.findViewById<Button>(R.id.remove_from_discard).setOnClickListener {
            var cardsToRemove: ArrayList<GoodsCard> = ArrayList<GoodsCard>()
            for (i in 0..(cardsInDiscard.size-1)) {
                if (cards.get(i).isChecked)
                    cardsToRemove.add(cardsInDiscard.get(i))
            }
            when (playerDiscard.value?.playerNum) {
                1 -> playViewModelHandDiscard.removeCardsFromTempDiscard(cardsToRemove, playViewModelHandDiscard.player1)
                2 -> playViewModelHandDiscard.removeCardsFromTempDiscard(cardsToRemove, playViewModelHandDiscard.player2)
                3 -> playViewModelHandDiscard.removeCardsFromTempDiscard(cardsToRemove, playViewModelHandDiscard.player3)
                4 -> playViewModelHandDiscard.removeCardsFromTempDiscard(cardsToRemove, playViewModelHandDiscard.player4)
                5 -> playViewModelHandDiscard.removeCardsFromTempDiscard(cardsToRemove, playViewModelHandDiscard.player5)
            }

            when (playerDiscard.value?.playerNum) {
                1 -> cardsInDiscard = playViewModelHandDiscard.getPlayerTempDiscard(1) as ArrayList<GoodsCard>
                2 -> cardsInDiscard = playViewModelHandDiscard.getPlayerTempDiscard(2) as ArrayList<GoodsCard>
                3 -> cardsInDiscard = playViewModelHandDiscard.getPlayerTempDiscard(3) as ArrayList<GoodsCard>
                4 -> cardsInDiscard = playViewModelHandDiscard.getPlayerTempDiscard(4) as ArrayList<GoodsCard>
                5 -> cardsInDiscard = playViewModelHandDiscard.getPlayerTempDiscard(5) as ArrayList<GoodsCard>
            }
            refreshCards(cards)
        }

        setupCardListeners(cards)

        when (playerDiscard.value?.playerNum) {
            1 -> cardsInDiscard = playViewModelHandDiscard.getPlayerTempDiscard(1) as ArrayList<GoodsCard>
            2 -> cardsInDiscard = playViewModelHandDiscard.getPlayerTempDiscard(2) as ArrayList<GoodsCard>
            3 -> cardsInDiscard = playViewModelHandDiscard.getPlayerTempDiscard(3) as ArrayList<GoodsCard>
            4 -> cardsInDiscard = playViewModelHandDiscard.getPlayerTempDiscard(4) as ArrayList<GoodsCard>
            5 -> cardsInDiscard = playViewModelHandDiscard.getPlayerTempDiscard(5) as ArrayList<GoodsCard>
        }
        refreshCards(cards)
    }

    private fun setupCardListeners(cards: Array<ToggleButton>) {
        for (i in cards) {
            i.setOnCheckedChangeListener { _, isChecked ->
                if (numCardsInBag >= BAG_MAX) {
                    if (isChecked) {
                        i.setChecked(false)
                        numCardsInBag = BAG_MAX
                    } else {
                        numCardsInBag--
                    }
                } else {
                    if (isChecked) {
                        numCardsInBag++
                    } else {
                        numCardsInBag--
                    }
                }
            }
        }
    }

    private fun refreshCards(cards: Array<ToggleButton>) {
        var c: Int = 0
        for (i in cards) {
            if (c < cardsInDiscard.size) {
                when (cardsInDiscard.get(c).type) {
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