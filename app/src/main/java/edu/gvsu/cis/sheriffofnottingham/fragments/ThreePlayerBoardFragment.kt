package edu.gvsu.cis.sheriffofnottingham

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import edu.gvsu.cis.sheriffofnottingham.game.Board
import edu.gvsu.cis.sheriffofnottingham.game.Player
import edu.gvsu.cis.sheriffofnottingham.models.GameSettingsViewModel



class ThreePlayerBoard : Fragment() {

    lateinit var viewModel: GameSettingsViewModel
    var player1Name: String = ""
    lateinit var player1: Player
    var player2Name: String = ""
    lateinit var player2: Player
    var player3Name: String = ""
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
            val bundle = bundleOf("playerName" to player1Name, "numPlayers" to numPlayers)
            findNavController().navigate(R.id.action_threePlayerBoardFragment_to_playerHandFragment, bundle)
        }

        buttonPlayer2.setOnClickListener {
            val bundle = bundleOf("playerName" to player2Name, "numPlayers" to numPlayers)
            findNavController().navigate(R.id.action_threePlayerBoardFragment_to_playerHandFragment, bundle)
        }

        buttonPlayer3.setOnClickListener {
            val bundle = bundleOf("playerName" to player3Name, "numPlayers" to numPlayers)
            findNavController().navigate(R.id.action_threePlayerBoardFragment_to_playerHandFragment, bundle)
        }

        viewModel = ViewModelProvider(requireActivity()).get(GameSettingsViewModel::class.java)
        viewModel.numPlayers.observe(this, Observer { z ->
            numPlayers = z
        })
        viewModel.player1Name.observe(this, Observer { z ->
            player1Name = z
            buttonPlayer1.text = player1Name
        })
        viewModel.player2Name.observe(this, Observer { z ->
            player2Name = z
            buttonPlayer2.text = player2Name
        })
        viewModel.player3Name.observe(this, Observer { z ->
            player3Name = z
            buttonPlayer3.text = player3Name
        })
        viewModel.player1.observe(this, Observer { z ->
            player1 = z
        })
        viewModel.player2.observe(this, Observer { z ->
            player2 = z
        })
        viewModel.player3.observe(this, Observer { z ->
            player3 = z
        })

//        buttonPlayer1.drawableState.

        board = Board(3)

    }

}