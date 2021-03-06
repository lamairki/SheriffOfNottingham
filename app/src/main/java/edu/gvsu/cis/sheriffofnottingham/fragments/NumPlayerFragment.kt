package edu.gvsu.cis.sheriffofnottingham.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import edu.gvsu.cis.sheriffofnottingham.R
import edu.gvsu.cis.sheriffofnottingham.cards.Deck
import edu.gvsu.cis.sheriffofnottingham.cards.Discard
import edu.gvsu.cis.sheriffofnottingham.models.PlayViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class NumPlayerFragment : Fragment() {

    private var numPlayers = 0

lateinit var playViewModel: PlayViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_num_player, container, false)
        // Inflate the layout for this fragment
        val spinner: Spinner = view.findViewById(R.id.num_player_dropdown)
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            context!!,
            R.array.player_selection,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        playViewModel = ViewModelProvider(requireActivity()).get(PlayViewModel::class.java)

        val spinner: Spinner = view.findViewById(R.id.num_player_dropdown)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>, view: View, i: Int, l: Long) {
                if( i == 0)
                    numPlayers = 3
                if(i == 1)
                    numPlayers = 4
                if(i == 2)
                    numPlayers = 5
            }
            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        }

        view.findViewById<Button>(R.id.button_to_setup).setOnClickListener {
            playViewModel.numPlayers.value = numPlayers
            playViewModel.deck.value = Deck()
            playViewModel.shuffleDeck()
            playViewModel.discardStack.value = Discard(playViewModel.deck.value)
            val bundle = bundleOf("playerNum" to 1)
            findNavController().navigate(R.id.action_numPlayerFragment_to_SetupFragment, bundle)

        }
    }
}