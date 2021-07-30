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
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import edu.gvsu.cis.sheriffofnottingham.R
import edu.gvsu.cis.sheriffofnottingham.cards.GoodsCard
import edu.gvsu.cis.sheriffofnottingham.cards.GoodsType
import edu.gvsu.cis.sheriffofnottingham.models.PlayViewModel
import java.util.*

private const val BAG_MAX = 4
private const val BAG_MIN = 1
var playerName: String = ""
var numPlayers: Int = 0
var playerNum = 0
var bagCards = 0
lateinit var playViewModel: PlayViewModel
lateinit var hand: ArrayList<GoodsCard>



class PlayerHandFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_player_hand, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            playerName = it.getString("playerName").toString()
            playerNum = it.getInt("playerNum")
            numPlayers = it.getInt("numPlayers")
        }
        playViewModel = ViewModelProvider(requireActivity()).get(PlayViewModel::class.java)

        val card_1 = view.findViewById<ToggleButton>(R.id.card_1)
        val card_2 = view.findViewById<ToggleButton>(R.id.card_2)
        val card_3 = view.findViewById<ToggleButton>(R.id.card_3)
        val card_4 = view.findViewById<ToggleButton>(R.id.card_4)
        val card_5 = view.findViewById<ToggleButton>(R.id.card_5)
        val card_6 = view.findViewById<ToggleButton>(R.id.card_6)

        val cards = arrayOf(card_1, card_2, card_3, card_4, card_5, card_6)

        val nameTextView = view.findViewById<TextView>(R.id.player_name)
        nameTextView.setText(playerName)

        view.findViewById<Button>(R.id.return_button).setOnClickListener {
            if(numPlayers == 3)
                findNavController().navigate(R.id.action_playerHandFragment_to_threePlayerBoardFragment)
            if(numPlayers == 4)
                findNavController().navigate(R.id.action_playerHandFragment_to_fourPlayerBoard)
            if(numPlayers == 5)
                findNavController().navigate(R.id.action_playerHandFragment_to_FivePlayerBoardFragment)
        }

        view.findViewById<Button>(R.id.in_bag_button).setOnClickListener {
            var cardsToBag: ArrayList<GoodsCard> = ArrayList<GoodsCard>()
            for (i in 0..(hand.size-1)) {
                if (cards.get(i).isChecked)
                    cardsToBag.add(hand.get(i))
            }
            when (playerNum) {
                1 -> playViewModel.addCardsToBag(cardsToBag, playViewModel.player1)
                2 -> playViewModel.addCardsToBag(cardsToBag, playViewModel.player2)
                3 -> playViewModel.addCardsToBag(cardsToBag, playViewModel.player3)
            }

            when (playerNum) {
                1 -> hand = playViewModel.getPlayerHand(1) as ArrayList<GoodsCard>
                2 -> hand = playViewModel.getPlayerHand(2) as ArrayList<GoodsCard>
                3 -> hand = playViewModel.getPlayerHand(3) as ArrayList<GoodsCard>
            }
            refreshCards(cards)
        }
        setupCardListeners(cards)

        when (playerNum) {
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