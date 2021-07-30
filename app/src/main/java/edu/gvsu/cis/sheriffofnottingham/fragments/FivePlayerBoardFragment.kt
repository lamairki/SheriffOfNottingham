package edu.gvsu.cis.sheriffofnottingham

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import edu.gvsu.cis.sheriffofnottingham.game.Board
import edu.gvsu.cis.sheriffofnottingham.models.PlayViewModel


class FivePlayerBoard : Fragment() {

    lateinit var playViewModel: PlayViewModel
    var player1Name: String = ""
    var player2Name: String = ""
    var player3Name: String = ""
    var player4Name: String = ""
    var player5Name: String = ""
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


        return inflater.inflate(R.layout.fragment_five_player_board, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonPlayer1 = view.findViewById<Button>(R.id.button_5p_p1)
        val buttonPlayer2 = view.findViewById<Button>(R.id.button_5p_p2)
        val buttonPlayer3 = view.findViewById<Button>(R.id.button_5p_p3)
        val buttonPlayer4 = view.findViewById<Button>(R.id.button_5p_p4)
        val buttonPlayer5 = view.findViewById<Button>(R.id.button_5p_p5)

        playViewModel = ViewModelProvider(requireActivity()).get(PlayViewModel::class.java)
        playViewModel.numPlayers.observe(this, Observer { z ->
            numPlayers = z
        })
//        playViewModel.player1Name.observe(this, Observer { z ->
//            player1Name = z
//            buttonPlayer1.text = player1Name
//        })
//        playViewModel.player2Name.observe(this, Observer { z ->
//            player2Name = z
//            buttonPlayer2.text = player2Name
//        })
//        playViewModel.player3Name.observe(this, Observer { z ->
//            player3Name = z
//            buttonPlayer3.text = player3Name
//        })
//        playViewModel.player4Name.observe(this, Observer { z ->
//            player4Name = z
//            buttonPlayer4.text = player4Name
//        })
//        playViewModel.player5Name.observe(this, Observer { z ->
//            player5Name = z
//            buttonPlayer5.text = player5Name
//        })

        board = Board(numPlayers)


    }

}