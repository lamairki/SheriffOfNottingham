package edu.gvsu.cis.sheriffofnottingham

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import edu.gvsu.cis.sheriffofnottingham.game.Board
import edu.gvsu.cis.sheriffofnottingham.game.Player
import edu.gvsu.cis.sheriffofnottingham.models.PlayViewModel


class ThreePlayerBoard : Fragment() {

    lateinit var playViewModel: PlayViewModel
    lateinit var currPlayer: MutableLiveData<Player>
    lateinit var player1: Player
    lateinit var player2: Player
    lateinit var player3: Player
    var numPlayers: Int = 0
    lateinit var board: Board

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_threeplayerboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        playViewModel = ViewModelProvider(requireActivity()).get(PlayViewModel::class.java)

        val buttonPlayer1 = view.findViewById<Button>(R.id.button_3p_p1)
        val buttonPlayer2 = view.findViewById<Button>(R.id.button_3p_p2)
        val buttonPlayer3 = view.findViewById<Button>(R.id.button_3p_p3)

        playViewModel.numPlayers.observe(this, Observer { z ->
            numPlayers = z
        })
        playViewModel.player1.observe(this, Observer { z ->
            player1 = z
            buttonPlayer1.text = player1.playerName
        })
        playViewModel.player2.observe(this, Observer { z ->
            player2 = z
            buttonPlayer2.text = player2.playerName
        })
        playViewModel.player3.observe(this, Observer { z ->
            player3 = z
            buttonPlayer3.text = player3.playerName
        })
        playViewModel.currPlayer.observe(this, Observer { z ->
            currPlayer = z
            if (currPlayer.value?.playerNum == 1) {
                buttonPlayer1.setEnabled(true)
                buttonPlayer2.setEnabled(false)
                buttonPlayer3.setEnabled(false)
            }
            else if (currPlayer.value?.playerNum == 2) {
                buttonPlayer1.setEnabled(false)
                buttonPlayer2.setEnabled(true)
                buttonPlayer3.setEnabled(false)
            }
            else {
                buttonPlayer1.setEnabled(false)
                buttonPlayer2.setEnabled(false)
                buttonPlayer3.setEnabled(true)
            }
        })

        buttonPlayer1.setOnClickListener {
//            val bundle = bundleOf("playerName" to player1.playerName, "playerNum" to 1, "numPlayers" to numPlayers)
            findNavController().navigate(R.id.action_threePlayerBoardFragment_to_playerHandFragment)
        }

        buttonPlayer2.setOnClickListener {
//            val bundle = bundleOf("playerName" to player2.playerName, "playerNum" to 2, "numPlayers" to numPlayers)
            findNavController().navigate(R.id.action_threePlayerBoardFragment_to_playerHandFragment)
        }

        buttonPlayer3.setOnClickListener {
//            val bundle = bundleOf("playerName" to player3.playerName, "playerNum" to 3, "numPlayers" to numPlayers)
            findNavController().navigate(R.id.action_threePlayerBoardFragment_to_playerHandFragment)
        }

    }

}