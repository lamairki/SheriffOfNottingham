package edu.gvsu.cis.sheriffofnottingham.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import edu.gvsu.cis.sheriffofnottingham.R
import edu.gvsu.cis.sheriffofnottingham.cards.GoodsCard
import edu.gvsu.cis.sheriffofnottingham.cards.GoodsType
import edu.gvsu.cis.sheriffofnottingham.game.Player

var playerName: String = ""
var numPlayers: Int = 0
//lateinit var player: Player


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
//            player = it.get("player") as Player
            numPlayers = it.getInt("numPlayers")
        }

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

        val card_1 = view.findViewById<ImageView>(R.id.card_1)
        val card_2 = view.findViewById<ImageView>(R.id.card_2)
        val card_3 = view.findViewById<ImageView>(R.id.card_3)
        val card_4 = view.findViewById<ImageView>(R.id.card_4)
        val card_5 = view.findViewById<ImageView>(R.id.card_5)

        val cards = arrayOf(card_1, card_2, card_3, card_4, card_5)
//        val hand = player.hand.toList()
//        var c: Int = 0
        for (i in cards) {
            i.setImageResource(R.drawable.apple_card)
//            when(hand.get(c).type) {
//                GoodsType.CHICKENS -> i.setImageResource(R.drawable.chicken_card)
//                GoodsType.BREAD -> i.setImageResource(R.drawable.bread_card)
//                GoodsType.CHEESE -> i.setImageResource(R.drawable.cheese_card)
//                GoodsType.APPLES -> i.setImageResource(R.drawable.apple_card)
//                GoodsType.CROSSBOW -> i.setImageResource(R.drawable.crossbow_card)
//                GoodsType.SILK -> i.setImageResource(R.drawable.silk_card)
//                GoodsType.MEAD -> i.setImageResource(R.drawable.mead_card)
//                GoodsType.PEPPER -> i.setImageResource(R.drawable.pepper_card)
//            }
//            c++
        }
    }
}