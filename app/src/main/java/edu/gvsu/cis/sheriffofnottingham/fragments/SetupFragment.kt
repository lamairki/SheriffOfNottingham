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
import edu.gvsu.cis.sheriffofnottingham.models.GameSettingsViewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SetupFragment : Fragment() {

    private var playerNumber: Int = 0
    lateinit var viewModel: GameSettingsViewModel
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
        viewModel = ViewModelProvider(requireActivity()).get(GameSettingsViewModel::class.java)
        viewModel.numPlayers.observe(this.viewLifecycleOwner, Observer { z ->
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
                addPlayerName(playerName, playerNumber)
                if (numPlayers == 3)
                    findNavController().navigate(R.id.action_SetupFragment_to_ThreePlayerBoardFragment)
                if (numPlayers == 4)
                    findNavController().navigate(R.id.action_SetupFragment_to_fourPlayerBoard)
                if (numPlayers == 5)
                    findNavController().navigate(R.id.action_SetupFragment_to_FivePlayerBoardFragment)
            }
            else {
                addPlayerName(playerName, playerNumber)
                playerNumber = playerNumber + 1
                val bundle = bundleOf("playerNum" to playerNumber)
                findNavController().navigate(R.id.action_SetupFragment_self, bundle)
            }
        }
    }

    fun addPlayerName (name: String, i: Int) {
        if (i == 1)
            viewModel.player1Name.value = name
        else if (i == 2)
            viewModel.player2Name.value = name
        else if (i == 3)
            viewModel.player3Name.value = name
        else if (i == 4)
            viewModel.player4Name.value = name
        else if (i == 5)
            viewModel.player5Name.value = name
    }
}