package edu.gvsu.cis.sheriffofnottingham

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import edu.gvsu.cis.sheriffofnottingham.game.Board
import edu.gvsu.cis.sheriffofnottingham.game.Player
import edu.gvsu.cis.sheriffofnottingham.models.GameSettingsViewModel
import edu.gvsu.cis.sheriffofnottingham.models.PlayViewModel


class ThreePlayerBoard : Fragment() {

    lateinit var playViewModel: PlayViewModel
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
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_threeplayerboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonPlayer1 = view.findViewById<Button>(R.id.button_3p_p1)
        val buttonPlayer2 = view.findViewById<Button>(R.id.button_3p_p2)
        val buttonPlayer3 = view.findViewById<Button>(R.id.button_3p_p3)

        buttonPlayer1.setOnClickListener {
            val bundle = bundleOf("playerName" to player1.playerName, "playerNum" to 1, "numPlayers" to numPlayers)
            findNavController().navigate(R.id.action_threePlayerBoardFragment_to_playerHandFragment, bundle)
        }

        buttonPlayer2.setOnClickListener {
            val bundle = bundleOf("playerName" to player2.playerName, "playerNum" to 2, "numPlayers" to numPlayers)
            findNavController().navigate(R.id.action_threePlayerBoardFragment_to_playerHandFragment, bundle)
        }

        buttonPlayer3.setOnClickListener {
            val bundle = bundleOf("playerName" to player3.playerName, "playerNum" to 3, "numPlayers" to numPlayers)
            findNavController().navigate(R.id.action_threePlayerBoardFragment_to_playerHandFragment, bundle)
        }

        playViewModel = ViewModelProvider(requireActivity()).get(PlayViewModel::class.java)
        playViewModel.numPlayers.observe(this, Observer { z ->
            numPlayers = z
        })
//        playViewModel.player1.observe(this, Observer { z ->
//            player1Name = z
//            buttonPlayer1.text = player1Name
//        })
//        viewModel.player2Name.observe(this, Observer { z ->
//            player2Name = z
//            buttonPlayer2.text = player2Name
//        })
//        viewModel.player3Name.observe(this, Observer { z ->
//            player3Name = z
//            buttonPlayer3.text = player3Name
//        })
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

//        buttonPlayer1.drawableState.

//        board = Board(3)

    }

}