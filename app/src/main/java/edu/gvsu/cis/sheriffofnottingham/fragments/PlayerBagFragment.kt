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

lateinit var playViewModelBag: PlayViewModel
private const val BAG_MAX = 5
private const val BAG_MIN = 1
lateinit var playerBag: MutableLiveData<Player>
var numPlayersBag: Int = 0
var numCardsInBag = 0
lateinit var cardsInBag: ArrayList<GoodsCard>

class PlayerBagFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_player_bag, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        playViewModelBag = ViewModelProvider(requireActivity()).get(PlayViewModel::class.java)

        numPlayersBag = playViewModelBag.numPlayers.value!!

        playerBag = playViewModelBag.currPlayer.value!!


        val card_1_bag = view.findViewById<ToggleButton>(R.id.card_1_bag)
        val card_2_bag = view.findViewById<ToggleButton>(R.id.card_2_bag)
        val card_3_bag = view.findViewById<ToggleButton>(R.id.card_3_bag)
        val card_4_bag = view.findViewById<ToggleButton>(R.id.card_4_bag)
        val card_5_bag = view.findViewById<ToggleButton>(R.id.card_5_bag)
        val card_6_bag = view.findViewById<ToggleButton>(R.id.card_6_bag)

        val cards = arrayOf(card_1_bag, card_2_bag, card_3_bag, card_4_bag, card_5_bag, card_6_bag)

        val nameTextView = view.findViewById<TextView>(R.id.player_name_bag)
        nameTextView.setText(playerBag.value?.playerName.toString())

        view.findViewById<Button>(R.id.button_back_to_hand).setOnClickListener {
            findNavController().navigate(R.id.action_playerBagFragment_to_playerHandFragment)
        }

        view.findViewById<Button>(R.id.remove_from_bag).setOnClickListener {
            var cardsToBag: ArrayList<GoodsCard> = ArrayList<GoodsCard>()
            for (i in 0..(cardsInBag.size-1)) {
                if (cards.get(i).isChecked)
                    cardsToBag.add(cardsInBag.get(i))
            }
            when (playerBag.value?.playerNum) {
                1 -> playViewModelBag.addCardsToHand(cardsToBag, playViewModelBag.player1)
                2 -> playViewModelBag.addCardsToHand(cardsToBag, playViewModelBag.player2)
                3 -> playViewModelBag.addCardsToHand(cardsToBag, playViewModelBag.player3)
                4 -> playViewModelBag.addCardsToHand(cardsToBag, playViewModelBag.player4)
                5 -> playViewModelBag.addCardsToHand(cardsToBag, playViewModelBag.player5)
            }

            when (playerBag.value?.playerNum) {
                1 -> cardsInBag = playViewModelBag.getPlayerBag(1) as ArrayList<GoodsCard>
                2 -> cardsInBag = playViewModelBag.getPlayerBag(2) as ArrayList<GoodsCard>
                3 -> cardsInBag = playViewModelBag.getPlayerBag(3) as ArrayList<GoodsCard>
                4 -> cardsInBag = playViewModelBag.getPlayerBag(4) as ArrayList<GoodsCard>
                5 -> cardsInBag = playViewModelBag.getPlayerBag(5) as ArrayList<GoodsCard>
            }
            refreshCards(cards)
        }

        setupCardListeners(cards)

        when (playerBag.value?.playerNum) {
            1 -> cardsInBag = playViewModelBag.getPlayerBag(1) as ArrayList<GoodsCard>
            2 -> cardsInBag = playViewModelBag.getPlayerBag(2) as ArrayList<GoodsCard>
            3 -> cardsInBag = playViewModelBag.getPlayerBag(3) as ArrayList<GoodsCard>
            4 -> cardsInBag = playViewModelBag.getPlayerBag(4) as ArrayList<GoodsCard>
            5 -> cardsInBag = playViewModelBag.getPlayerBag(5) as ArrayList<GoodsCard>
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
            if (c < cardsInBag.size) {
                when (cardsInBag.get(c).type) {
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