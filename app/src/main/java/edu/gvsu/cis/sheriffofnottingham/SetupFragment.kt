package edu.gvsu.cis.sheriffofnottingham

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import java.util.*
import kotlin.properties.Delegates

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SetupFragment : Fragment() {

    private var playerNumber: Int? = null
    lateinit var viewModel: NottinghamDataViewModel
    var numPlayers = 0

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
            if (playerNumber!! >= numPlayers)
                findNavController().navigate(R.id.action_SetupFragment_to_OpeningFragment)
            else {
                playerNumber = playerNumber!! + 1
                val bundle = bundleOf("playerNum" to playerNumber)
                findNavController().navigate(R.id.action_SetupFragment_self, bundle)
            }
        }
    }
    
}