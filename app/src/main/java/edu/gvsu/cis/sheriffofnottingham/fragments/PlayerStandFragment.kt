package edu.gvsu.cis.sheriffofnottingham.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import edu.gvsu.cis.sheriffofnottingham.R
import edu.gvsu.cis.sheriffofnottingham.cards.GoodsCard
import edu.gvsu.cis.sheriffofnottingham.game.GamePhase
import edu.gvsu.cis.sheriffofnottingham.game.Player
import edu.gvsu.cis.sheriffofnottingham.models.PlayViewModel
import java.util.ArrayList

lateinit var standPlayViewModel: PlayViewModel
private const val BAG_MAX = 5
private const val BAG_MIN = 1
lateinit var standPlayer: MutableLiveData<Player>
var numPlayersStand: Int = 0
lateinit var stand: ArrayList<GoodsCard>
lateinit var currPhase: GamePhase

class PlayerStandFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_player_stand, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        standPlayViewModel = ViewModelProvider(requireActivity()).get(PlayViewModel::class.java)

        numPlayersStand = standPlayViewModel.numPlayers.value!!

        standPlayer = standPlayViewModel.currPlayer.value!!

        currPhase = standPlayViewModel.gamePhase.value!!

        val nameTV = view.findViewById<TextView>(R.id.player_name_stand)
        nameTV.setText(standPlayer.value?.playerName.toString())

        val appleQtyTV = view.findViewById<TextView>(R.id.apple_qty)
        appleQtyTV.setText("Qty: " + standPlayer.value?.applesStand?.size.toString())
        val breadQtyTV = view.findViewById<TextView>(R.id.bread_qty)
        breadQtyTV.setText("Qty: " + standPlayer.value?.breadStand?.size.toString())
        val cheeseQtyTV = view.findViewById<TextView>(R.id.cheese_qty)
        cheeseQtyTV.setText("Qty: " + standPlayer.value?.cheeseStand?.size.toString())
        val chickenQtyTV = view.findViewById<TextView>(R.id.chicken_qty)
        chickenQtyTV.setText("Qty: " + standPlayer.value?.chickenStand?.size.toString())
        val contrabandQtyTV = view.findViewById<TextView>(R.id.contraband_qty)
        contrabandQtyTV.setText("Qty: " + standPlayer.value?.contrabandStand?.size.toString())


        view.findViewById<Button>(R.id.button_to_board).setOnClickListener {
            if(numPlayersStand == 3) {
                standPlayViewModel.turnComplete(standPlayer)
                findNavController().navigate(R.id.action_playerStandFragment_to_threePlayerBoardFragment)
            }
            if(numPlayersStand == 4) {
                standPlayViewModel.turnComplete(standPlayer)
                findNavController().navigate(R.id.action_playerStandFragment_to_fourPlayerBoard)
            }
            if(numPlayersStand == 5) {
                standPlayViewModel.turnComplete(standPlayer)
                findNavController().navigate(R.id.action_playerStandFragment_to_FivePlayerBoardFragment)
            }
        }

        view.findViewById<Button>(R.id.button_to_hand).setOnClickListener {
            if (currPhase == GamePhase.MARKET)
                findNavController().navigate(R.id.action_playerStandFragment_to_playerHandToDiscardFragment)
            else if (currPhase == GamePhase.LOAD_BAG)
                findNavController().navigate(R.id.action_playerStandFragment_to_playerHandFragment)
        }

    }


}