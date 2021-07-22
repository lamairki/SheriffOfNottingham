package edu.gvsu.cis.sheriffofnottingham

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import edu.gvsu.cis.sheriffofnottingham.models.GameSettingsViewModel



class BoardFragment : Fragment() {

    lateinit var viewModel: GameSettingsViewModel
    var player1Name: String = ""
    var player2Name: String = ""
    var player3Name: String = ""
    var numPlayers: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // TODO: Update retrieval of information
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
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



    }

}