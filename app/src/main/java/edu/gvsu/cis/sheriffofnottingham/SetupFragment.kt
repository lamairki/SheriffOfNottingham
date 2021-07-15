package edu.gvsu.cis.sheriffofnottingham

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import java.util.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SetupFragment : Fragment() {

    private var playerNumber: Int = 0
    lateinit var viewModel: NottinghamDataViewModel
    var numPlayers = 0
    var playerName: String = ""

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(NottinghamDataViewModel::class.java)
        viewModel.settings.observe(this.viewLifecycleOwner, { z ->
            numPlayers = z.numPlayers
        })

        arguments?.let {
            playerNumber = it.getInt("playerNum")
        }
        val playerNumberName_TV = view.findViewById<TextView>(R.id.tv_player_name)
        playerNumberName_TV.text = "Player " + playerNumber + " Name:"

        view.findViewById<Button>(R.id.button_continue_setup).setOnClickListener {
            val playerName_ET = view.findViewById<EditText>(R.id.et_player_name)
            playerName = playerName_ET.text.toString()
            if (playerNumber >= numPlayers) {
                addPlayerName(playerName, playerNumber)
                findNavController().navigate(R.id.action_SetupFragment_to_BoardFragment)
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
            if (viewModel.settings.value?.player1Name != null)
                viewModel.settings.value?.player1Name = playerName
        else if (i == 2)
            if (viewModel.settings.value?.player2Name != null)
                viewModel.settings.value?.player2Name = playerName
        else if (i == 3)
            if (viewModel.settings.value?.player3Name != null)
                viewModel.settings.value?.player3Name = playerName
        else if (i == 4)
            if (viewModel.settings.value?.player4Name != null)
                viewModel.settings.value?.player4Name = playerName
        else if (i == 5)
            if (viewModel.settings.value?.player5Name != null)
                viewModel.settings.value?.player5Name = playerName
    }
}