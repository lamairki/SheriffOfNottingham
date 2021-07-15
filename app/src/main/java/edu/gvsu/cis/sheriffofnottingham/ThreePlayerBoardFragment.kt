package edu.gvsu.cis.sheriffofnottingham

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider

class BoardFragment : Fragment() {

    lateinit var viewModel: NottinghamDataViewModel
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

        viewModel = ViewModelProvider(requireActivity()).get(NottinghamDataViewModel::class.java)
        viewModel.settings.observe(this.viewLifecycleOwner, { z ->
            numPlayers = z.numPlayers
            player1Name = z.player1Name
            player2Name = z.player2Name
            player3Name = z.player3Name
        })

        val buttonPlayer1 = view.findViewById<Button>(R.id.button_3p_p1)
        val buttonPlayer2 = view.findViewById<Button>(R.id.button_3p_p2)
        val buttonPlayer3 = view.findViewById<Button>(R.id.button_3p_p3)

        buttonPlayer1.setText(player1Name)
        buttonPlayer2.setText(player2Name)
        buttonPlayer3.setText(player3Name)

    }

}