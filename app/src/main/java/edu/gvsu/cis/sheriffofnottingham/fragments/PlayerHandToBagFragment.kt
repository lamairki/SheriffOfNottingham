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

lateinit var playViewModel: PlayViewModel
private const val BAG_MAX = 5
private const val BAG_MIN = 1
lateinit var player: MutableLiveData<Player>
var numPlayers: Int = 0
var bagCards = 0
lateinit var hand: ArrayList<GoodsCard>



class PlayerHandToBagFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_player_hand_to_bag, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        playViewModel = ViewModelProvider(requireActivity()).get(PlayViewModel::class.java)

        numPlayers = playViewModel.numPlayers.value!!

        player = playViewModel.currPlayer.value!!


        val card_1_hand_to_bag = view.findViewById<ToggleButton>(R.id.card_1_hand)
        val card_2_hand_to_bag = view.findViewById<ToggleButton>(R.id.card_2_hand)
        val card_3_hand_to_bag = view.findViewById<ToggleButton>(R.id.card_3_hand)
        val card_4_hand_to_bag = view.findViewById<ToggleButton>(R.id.card_4_hand)
        val card_5_hand_to_bag = view.findViewById<ToggleButton>(R.id.card_5_hand)
        val card_6_hand_to_bag = view.findViewById<ToggleButton>(R.id.card_6_hand)

        val cards = arrayOf(card_1_hand_to_bag, card_2_hand_to_bag, card_3_hand_to_bag, card_4_hand_to_bag, card_5_hand_to_bag, card_6_hand_to_bag)

        val nameTextView = view.findViewById<TextView>(R.id.player_name_hand)
        nameTextView.setText(player.value?.playerName.toString())

        view.findViewById<Button>(R.id.return_button_hand).setOnClickListener {
            findNavController().navigate(R.id.action_playerHandFragment_to_playerStandFragment)
        }

        view.findViewById<Button>(R.id.in_bag_button_hand).setOnClickListener {
            var cardsToBag: ArrayList<GoodsCard> = ArrayList<GoodsCard>()
            for (i in 0..(hand.size-1)) {
                if (cards.get(i).isChecked)
                    cardsToBag.add(hand.get(i))
            }
            when (player.value?.playerNum) {
                1 -> playViewModel.addCardsToBag(cardsToBag, playViewModel.player1)
                2 -> playViewModel.addCardsToBag(cardsToBag, playViewModel.player2)
                3 -> playViewModel.addCardsToBag(cardsToBag, playViewModel.player3)
            }

            when (player.value?.playerNum) {
                1 -> hand = playViewModel.getPlayerHand(1) as ArrayList<GoodsCard>
                2 -> hand = playViewModel.getPlayerHand(2) as ArrayList<GoodsCard>
                3 -> hand = playViewModel.getPlayerHand(3) as ArrayList<GoodsCard>
            }
            refreshCards(cards)
            findNavController().navigate(R.id.action_playerHandFragment_to_playerBagFragment)
        }

        setupCardListeners(cards)

        when (player.value?.playerNum) {
            1 -> hand = playViewModel.getPlayerHand(1) as ArrayList<GoodsCard>
            2 -> hand = playViewModel.getPlayerHand(2) as ArrayList<GoodsCard>
            3 -> hand = playViewModel.getPlayerHand(3) as ArrayList<GoodsCard>
        }
        refreshCards(cards)
    }

    private fun setupCardListeners(cards: Array<ToggleButton>) {
        for (i in cards) {
            i.setOnCheckedChangeListener { _, isChecked ->
                if (bagCards >= BAG_MAX) {
                    if (isChecked) {
                        i.setChecked(false)
                        bagCards = BAG_MAX
                    } else {
                        bagCards--
                    }
                } else {
                    if (isChecked) {
                        bagCards++
                    } else {
                        bagCards--
                    }
                }
            }
        }
    }

    private fun refreshCards(cards: Array<ToggleButton>) {
        var c: Int = 0
        for (i in cards) {
            if (c < hand.size) {
                when (hand.get(c).type) {
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