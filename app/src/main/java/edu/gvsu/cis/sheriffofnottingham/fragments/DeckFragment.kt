package edu.gvsu.cis.sheriffofnottingham.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
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

lateinit var playViewModelDeck: PlayViewModel
private const val HAND_MAX = 6
lateinit var playerDeck: MutableLiveData<Player>
lateinit var playersTempDiscardStack: ArrayList<GoodsCard>

class DeckFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_deck, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        playViewModelDeck = ViewModelProvider(requireActivity()).get(PlayViewModel::class.java)

        playerDeck = playViewModelHandDiscard.currPlayer.value!!


        val button_topOfDiscard = view.findViewById<Button>(R.id.top_of_discard)
        val currentHandSize_TV = view.findViewById<TextView>(R.id.current_hand_size_num)

        currentHandSize_TV.text = playerDeck.value?.hand?.size.toString()

        view.findViewById<Button>(R.id.add_cards_to_discard).setOnClickListener {
            playViewModelDeck.addCardsToDiscardStackFromTempDiscard(playerDeck,
                playerDeck.value?.tempDiscard, playViewModelDeck.discardStack)
            findNavController().navigate(R.id.action_deckFragment_to_playerStandFragment)
        }

        view.findViewById<Button>(R.id.top_of_deck).setOnClickListener {
            playerDeck.value?.fillHand(playViewModelDeck.deck.value)
            currentHandSize_TV.text = playerDeck.value?.hand?.size.toString()
        }

        button_topOfDiscard.setOnClickListener {
            if (playerDeck.value?.hand?.size!! < HAND_MAX && playViewModelDeck.discardStack.value?.peekAtTop() != GoodsCard(GoodsType.BACK)) {
                playViewModelDeck.cardToHandFromDiscard(playerDeck)
                refreshDiscard(playViewModelDeck.discardStack.value?.peekAtTop(), button_topOfDiscard)
                currentHandSize_TV.text = playerDeck.value?.hand?.size.toString()
            }
            else {}
        }

    }

    private fun refreshDiscard(gc: GoodsCard?, b: Button) {
        when (gc?.type) {
            GoodsType.CHICKENS -> b.setBackground(ContextCompat.getDrawable(requireActivity(), R.drawable.legal_goods_chicken))
            GoodsType.BREAD -> b.setBackground(ContextCompat.getDrawable(requireActivity(), R.drawable.legal_goods_bread))
            GoodsType.CHEESE -> b.setBackground(ContextCompat.getDrawable(requireActivity(), R.drawable.legal_goods_cheese))
            GoodsType.APPLES -> b.setBackground(ContextCompat.getDrawable(requireActivity(), R.drawable.legal_goods_apples))
            GoodsType.CROSSBOW -> b.setBackground(ContextCompat.getDrawable(requireActivity(), R.drawable.contraband_crossbow))
            GoodsType.SILK -> b.setBackground(ContextCompat.getDrawable(requireActivity(), R.drawable.contraband_silk))
            GoodsType.MEAD -> b.setBackground(ContextCompat.getDrawable(requireActivity(), R.drawable.contraband_mead))
            GoodsType.PEPPER -> b.setBackground(ContextCompat.getDrawable(requireActivity(), R.drawable.contraband_pepper))
            GoodsType.BACK -> b.setBackground(ContextCompat.getDrawable(requireActivity(), R.drawable.card_back))
            null -> b.setBackground(ContextCompat.getDrawable(requireActivity(), R.color.transparent))
        }
    }
}