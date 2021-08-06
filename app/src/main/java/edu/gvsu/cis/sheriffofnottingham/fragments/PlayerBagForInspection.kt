package edu.gvsu.cis.sheriffofnottingham.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.ToggleButton
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import edu.gvsu.cis.sheriffofnottingham.R
import edu.gvsu.cis.sheriffofnottingham.cards.GoodsCard
import edu.gvsu.cis.sheriffofnottingham.cards.GoodsType
import edu.gvsu.cis.sheriffofnottingham.cards.Discard
import edu.gvsu.cis.sheriffofnottingham.game.Player
import edu.gvsu.cis.sheriffofnottingham.models.PlayViewModel
import java.util.ArrayList

lateinit var playViewModelInsp: PlayViewModel
private const val BAG_MAX = 5
private const val BAG_MIN = 1
lateinit var playerInsp: MutableLiveData<Player>
lateinit var sheriffInsp: MutableLiveData<Player>
var numPlayersInsp: Int = 0
var numCardsPass = 0
lateinit var discardInsp: MutableLiveData<Discard>
lateinit var cardsInspected: ArrayList<GoodsCard>


class PlayerBagForInspection : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_player_bag_for_inspection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        playViewModelInsp = ViewModelProvider(requireActivity()).get(PlayViewModel::class.java)

        numPlayersInsp = playViewModelInsp.numPlayers.value!!

        playerInsp = playViewModelInsp.currPlayer.value!!

        sheriffInsp = playViewModelInsp.sheriff.value!!

        cardsInspected = playerInsp.value?.playerBag!!

        discardInsp = playViewModelInsp.discardStack

        val card_1_insp = view.findViewById<ToggleButton>(R.id.card_1_insp)
        val card_2_insp = view.findViewById<ToggleButton>(R.id.card_2_insp)
        val card_3_insp = view.findViewById<ToggleButton>(R.id.card_3_insp)
        val card_4_insp = view.findViewById<ToggleButton>(R.id.card_4_insp)
        val card_5_insp = view.findViewById<ToggleButton>(R.id.card_5_insp)
        val card_6_insp = view.findViewById<ToggleButton>(R.id.card_6_insp)

        val cards = arrayOf(card_1_insp, card_2_insp, card_3_insp, card_4_insp, card_5_insp, card_6_insp)

        setupCardListeners(cards)

        val nameTextView = view.findViewById<TextView>(R.id.player_name_insp)
        nameTextView.setText(playerInsp.value?.playerName.toString())

        view.findViewById<Button>(R.id.button_truth).setOnClickListener {
            playerInsp.value?.addCardsToStand(playerInsp.value?.playerBag)
            var sheriffOwes = 0
            for (c in cardsInspected) {
                sheriffOwes += c.getPenalty()
            }
            playerInsp.value?.emptyBag()
            sheriffInsp.value?.takeGold(sheriffOwes)
            playerInsp.value?.addGold(sheriffOwes)
        }

        view.findViewById<Button>(R.id.button_lies).setOnClickListener {
            var cardsThatPass: ArrayList<GoodsCard> = ArrayList<GoodsCard>()
            var cardsToConfiscate: ArrayList<GoodsCard> = ArrayList<GoodsCard>()
            for (i in 0..(cardsInspected.size-1)) {
                if (cards.get(i).isChecked)
                    cardsThatPass.add(cardsInspected.get(i))
                else
                    cardsToConfiscate.add(cardsInspected.get(i))
            }
            var playerOwes = 0
            for (c in cardsToConfiscate) {
                playerOwes += c.getPenalty()
            }
            playerInsp.value?.takeGold(playerOwes)
            sheriffInsp.value?.addGold(playerOwes)
            playerInsp.value?.addCardsToStand(cardsThatPass)
            playViewModelInsp.addCardsToDiscardStackFromBag(playerInsp, cardsToConfiscate, discardInsp)
        }

        view.findViewById<Button>(R.id.exit_bag).setOnClickListener {
            findNavController().navigate(R.id.action_playerBagForInspection_to_threePlayerBoardFragment)
        }

        when (playerInsp.value?.playerNum) {
            1 -> cardsInspected = playViewModelInsp.getPlayerBag(1) as ArrayList<GoodsCard>
            2 -> cardsInspected = playViewModelInsp.getPlayerBag(2) as ArrayList<GoodsCard>
            3 -> cardsInspected = playViewModelInsp.getPlayerBag(3) as ArrayList<GoodsCard>
        }
        refreshCards(cards)

    }

    private fun setupCardListeners(cards: Array<ToggleButton>) {
        for (i in cards) {
            i.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    numCardsPass++
                }
                else {
                    numCardsPass--
                }
            }
        }
    }

    private fun refreshCards(cards: Array<ToggleButton>) {
        var c: Int = 0
        for (i in cards) {
            if (c < cardsInspected.size) {
                when (cardsInspected.get(c).type) {
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
            }
            c++
        }
    }
}