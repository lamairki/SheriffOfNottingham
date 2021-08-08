package edu.gvsu.cis.sheriffofnottingham.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import edu.gvsu.cis.sheriffofnottingham.R
import edu.gvsu.cis.sheriffofnottingham.game.GamePhase
import edu.gvsu.cis.sheriffofnottingham.game.Player
import edu.gvsu.cis.sheriffofnottingham.models.PlayViewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SetupFragment : Fragment() {

    private var playerNumber: Int = 0
    lateinit var playViewModel: PlayViewModel
    var numPlayers = 0
//    var playerName: String = ""

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        playViewModel = ViewModelProvider(requireActivity()).get(PlayViewModel::class.java)
        playViewModel.numPlayers.observe(this.viewLifecycleOwner, Observer { z ->
            numPlayers = z
        })

        arguments?.let {
            playerNumber = it.getInt("playerNum")
        }
        val playerNumberName_TV = view.findViewById<TextView>(R.id.tv_player_name)
        playerNumberName_TV.text = "Player " + playerNumber + " Name:"

        view.findViewById<Button>(R.id.button_continue_setup).setOnClickListener {
            val playerName_ET = view.findViewById<EditText>(R.id.et_player_name)
            val playerName = playerName_ET.text.toString()
            if (playerNumber >= numPlayers) {
                addPlayer(playerName, playerNumber)
                if (numPlayers == 3) {
                    playViewModel.sheriff.value = playViewModel.player1
                    playViewModel.currPlayer.value = playViewModel.player2
                    playViewModel.gamePhase.value = GamePhase.MARKET
                    findNavController().navigate(R.id.action_SetupFragment_to_ThreePlayerBoardFragment)
                }
                if (numPlayers == 4) {
                    playViewModel.sheriff.value = playViewModel.player1
                    playViewModel.currPlayer.value = playViewModel.player2
                    playViewModel.gamePhase.value = GamePhase.MARKET
                    findNavController().navigate(R.id.action_SetupFragment_to_fourPlayerBoard)
                }
                if (numPlayers == 5) {
                    playViewModel.sheriff.value = playViewModel.player1
                    playViewModel.currPlayer.value = playViewModel.player2
                    playViewModel.gamePhase.value = GamePhase.MARKET
                    findNavController().navigate(R.id.action_SetupFragment_to_FivePlayerBoardFragment)
                }
            }
            else {
                addPlayer(playerName, playerNumber)
                playerNumber = playerNumber + 1
                val bundle = bundleOf("playerNum" to playerNumber)
                findNavController().navigate(R.id.action_SetupFragment_self, bundle)
            }
        }
    }

    fun addPlayer (name: String, i: Int) {
        if (i == 1) {
            playViewModel.player1.value = Player(i, name)
            playViewModel.fillHand(playViewModel.player1, playViewModel.deck)
        }
        else if (i == 2) {
            playViewModel.player2.value = Player(i, name)
            playViewModel.fillHand(playViewModel.player2, playViewModel.deck)
        }
        else if (i == 3) {
            playViewModel.player3.value = Player(i, name)
            playViewModel.fillHand(playViewModel.player3, playViewModel.deck)
        }
        else if (i == 4) {
            playViewModel.player4.value = Player(i, name)
            playViewModel.fillHand(playViewModel.player4, playViewModel.deck)
        }
        else if (i == 5) {
            playViewModel.player5.value = Player(i, name)
            playViewModel.fillHand(playViewModel.player5, playViewModel.deck)
        }
    }
}