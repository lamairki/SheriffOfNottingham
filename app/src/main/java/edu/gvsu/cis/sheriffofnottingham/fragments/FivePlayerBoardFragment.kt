package edu.gvsu.cis.sheriffofnottingham

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import edu.gvsu.cis.sheriffofnottingham.game.Board
import edu.gvsu.cis.sheriffofnottingham.game.GamePhase
import edu.gvsu.cis.sheriffofnottingham.game.Player
import edu.gvsu.cis.sheriffofnottingham.models.PlayViewModel


class FivePlayerBoard : Fragment() {

    lateinit var playViewModel: PlayViewModel
    lateinit var currPlayer: MutableLiveData<Player>
    lateinit var sheriff: MutableLiveData<Player>
    lateinit var player1: Player
    lateinit var player2: Player
    lateinit var player3: Player
    lateinit var player4: Player
    lateinit var player5: Player
    var numPlayers: Int = 0
    lateinit var currGamePhase: GamePhase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_five_player_board, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        playViewModel = ViewModelProvider(requireActivity()).get(PlayViewModel::class.java)

        val buttonPlayer1 = view.findViewById<Button>(R.id.button_5p_p1)
        val buttonPlayer2 = view.findViewById<Button>(R.id.button_5p_p2)
        val buttonPlayer3 = view.findViewById<Button>(R.id.button_5p_p3)
        val buttonPlayer4 = view.findViewById<Button>(R.id.button_5p_p4)
        val buttonPlayer5 = view.findViewById<Button>(R.id.button_5p_p5)
        val buttonPhaseComplete = view.findViewById<Button>(R.id.phase_complete)
        val gameWinner_TV = view.findViewById<TextView>(R.id.game_winner)

        numPlayers = playViewModel.numPlayers.value!!
        sheriff = playViewModel.sheriff.value!!
        player1 = playViewModel.player1.value!!
        buttonPlayer1.text = player1.playerName.toString()
        player2 = playViewModel.player2.value!!
        buttonPlayer2.text = player2.playerName.toString()
        player3 = playViewModel.player3.value!!
        buttonPlayer3.text = player3.playerName.toString()
        player4 = playViewModel.player4.value!!
        buttonPlayer4.text = player4.playerName.toString()
        player5 = playViewModel.player5.value!!
        buttonPlayer5.text = player5.playerName.toString()
        currGamePhase = playViewModel.gamePhase.value!!
        when (currGamePhase) {
            GamePhase.MARKET -> buttonPhaseComplete.text = "Phase 'MARKET' Complete"
            GamePhase.LOAD_BAG -> buttonPhaseComplete.text = "Phase 'LOAD BAG' Complete"
            GamePhase.DECLARATION -> buttonPhaseComplete.text = "Phase 'DECLARATION' Complete"
            GamePhase.INSPECTION -> buttonPhaseComplete.text = "Phase 'INSPECTION' Complete"
            GamePhase.END_OF_ROUND -> buttonPhaseComplete.text = "Phase 'END OF ROUND' Complete"
        }
        currPlayer = playViewModel.currPlayer.value!!
        if (currGamePhase != GamePhase.INSPECTION) {
            if (currPlayer.value?.playerNum == 1) {
                buttonPlayer1.setEnabled(true)
                buttonPlayer2.setEnabled(false)
                buttonPlayer3.setEnabled(false)
                buttonPlayer4.setEnabled(false)
                buttonPlayer5.setEnabled(false)
            } else if (currPlayer.value?.playerNum == 2) {
                buttonPlayer1.setEnabled(false)
                buttonPlayer2.setEnabled(true)
                buttonPlayer3.setEnabled(false)
                buttonPlayer4.setEnabled(false)
                buttonPlayer5.setEnabled(false)
            } else if (currPlayer.value?.playerNum == 3) {
                buttonPlayer1.setEnabled(false)
                buttonPlayer2.setEnabled(false)
                buttonPlayer3.setEnabled(true)
                buttonPlayer4.setEnabled(false)
                buttonPlayer5.setEnabled(false)
            } else if (currPlayer.value?.playerNum == 4) {
                buttonPlayer1.setEnabled(false)
                buttonPlayer2.setEnabled(false)
                buttonPlayer3.setEnabled(false)
                buttonPlayer4.setEnabled(true)
                buttonPlayer5.setEnabled(false)
            } else {
                buttonPlayer1.setEnabled(false)
                buttonPlayer2.setEnabled(false)
                buttonPlayer3.setEnabled(false)
                buttonPlayer4.setEnabled(false)
                buttonPlayer5.setEnabled(true)
            }
        }

        buttonPlayer1.setOnClickListener {
            if (currGamePhase == GamePhase.INSPECTION) {
                playViewModel.currPlayer.value = playViewModel.player1
                findNavController().navigate(R.id.action_FivePlayerBoardFragment_to_playerBagForInspection)
            } else
                findNavController().navigate(R.id.action_FivePlayerBoardFragment_to_playerStandFragment)
        }
        buttonPlayer2.setOnClickListener {
            if (currGamePhase == GamePhase.INSPECTION) {
                playViewModel.currPlayer.value = playViewModel.player2
                findNavController().navigate(R.id.action_FivePlayerBoardFragment_to_playerBagForInspection)
            } else
                findNavController().navigate(R.id.action_FivePlayerBoardFragment_to_playerStandFragment)
        }
        buttonPlayer3.setOnClickListener {
            if (currGamePhase == GamePhase.INSPECTION) {
                playViewModel.currPlayer.value = playViewModel.player3
                findNavController().navigate(R.id.action_FivePlayerBoardFragment_to_playerBagForInspection)
            } else
                findNavController().navigate(R.id.action_FivePlayerBoardFragment_to_playerStandFragment)
        }
        buttonPlayer4.setOnClickListener {
            if (currGamePhase == GamePhase.INSPECTION) {
                playViewModel.currPlayer.value = playViewModel.player4
                findNavController().navigate(R.id.action_FivePlayerBoardFragment_to_playerBagForInspection)
            } else
                findNavController().navigate(R.id.action_FivePlayerBoardFragment_to_playerStandFragment)
        }
        buttonPlayer5.setOnClickListener {
            if (currGamePhase == GamePhase.INSPECTION) {
                playViewModel.currPlayer.value = playViewModel.player5
                findNavController().navigate(R.id.action_FivePlayerBoardFragment_to_playerBagForInspection)
            } else
                findNavController().navigate(R.id.action_FivePlayerBoardFragment_to_playerStandFragment)
        }

        buttonPhaseComplete.setOnClickListener {
            playViewModel.nextPhase()
            currGamePhase = playViewModel.gamePhase.value!!
            when (currGamePhase) {
                GamePhase.MARKET -> buttonPhaseComplete.text = "Phase 'MARKET' Complete"
                GamePhase.LOAD_BAG -> buttonPhaseComplete.text = "Phase 'LOAD BAG' Complete"
                GamePhase.DECLARATION -> buttonPhaseComplete.text = "Phase 'DECLARATION' Complete"
                GamePhase.INSPECTION -> buttonPhaseComplete.text = "Phase 'INSPECTION' Complete"
                GamePhase.END_OF_ROUND -> buttonPhaseComplete.text = "Phase 'END OF ROUND' Complete"
            }
            currPlayer = playViewModel.currPlayer.value!!
            if (currGamePhase == GamePhase.INSPECTION) {
                if (sheriff.value?.playerNum == 1) {
                    buttonPlayer1.setEnabled(false)
                    buttonPlayer2.setEnabled(true)
                    buttonPlayer3.setEnabled(true)
                    buttonPlayer4.setEnabled(true)
                    buttonPlayer5.setEnabled(true)
                } else if (sheriff.value?.playerNum == 2) {
                    buttonPlayer1.setEnabled(true)
                    buttonPlayer2.setEnabled(false)
                    buttonPlayer3.setEnabled(true)
                    buttonPlayer4.setEnabled(true)
                    buttonPlayer5.setEnabled(true)
                } else if (sheriff.value?.playerNum == 3) {
                    buttonPlayer1.setEnabled(true)
                    buttonPlayer2.setEnabled(true)
                    buttonPlayer3.setEnabled(false)
                    buttonPlayer4.setEnabled(true)
                    buttonPlayer5.setEnabled(true)
                } else if (sheriff.value?.playerNum == 4) {
                    buttonPlayer1.setEnabled(true)
                    buttonPlayer2.setEnabled(true)
                    buttonPlayer3.setEnabled(true)
                    buttonPlayer4.setEnabled(false)
                    buttonPlayer5.setEnabled(true)
                } else if (sheriff.value?.playerNum == 5) {
                    buttonPlayer1.setEnabled(true)
                    buttonPlayer2.setEnabled(true)
                    buttonPlayer3.setEnabled(true)
                    buttonPlayer4.setEnabled(true)
                    buttonPlayer5.setEnabled(false)
                }
            }
            else {
                if (currPlayer.value?.playerNum == 1) {
                    buttonPlayer1.setEnabled(true)
                    buttonPlayer2.setEnabled(false)
                    buttonPlayer3.setEnabled(false)
                    buttonPlayer4.setEnabled(false)
                    buttonPlayer5.setEnabled(false)
                } else if (currPlayer.value?.playerNum == 2) {
                    buttonPlayer1.setEnabled(false)
                    buttonPlayer2.setEnabled(true)
                    buttonPlayer3.setEnabled(false)
                    buttonPlayer4.setEnabled(false)
                    buttonPlayer5.setEnabled(false)
                } else if (currPlayer.value?.playerNum == 3) {
                    buttonPlayer1.setEnabled(false)
                    buttonPlayer2.setEnabled(false)
                    buttonPlayer3.setEnabled(true)
                    buttonPlayer4.setEnabled(false)
                    buttonPlayer5.setEnabled(false)
                } else if (currPlayer.value?.playerNum == 4) {
                    buttonPlayer1.setEnabled(false)
                    buttonPlayer2.setEnabled(false)
                    buttonPlayer3.setEnabled(false)
                    buttonPlayer4.setEnabled(true)
                    buttonPlayer5.setEnabled(false)
                } else {
                    buttonPlayer1.setEnabled(false)
                    buttonPlayer2.setEnabled(false)
                    buttonPlayer3.setEnabled(false)
                    buttonPlayer4.setEnabled(false)
                    buttonPlayer5.setEnabled(true)
                }
            }
            if (currGamePhase == GamePhase.END_OF_ROUND) {
                player1.addCardsToStand(player1.playerBag)
                player2.addCardsToStand(player2.playerBag)
                player3.addCardsToStand(player3.playerBag)
                player4.addCardsToStand(player4.playerBag)
                player5.addCardsToStand(player5.playerBag)
                if (playViewModel.getNumTurnsAsSheriff() == 2) {
                    val player1Points = totalPoints(player1)
                    val player2Points = totalPoints(player2)
                    val player3Points = totalPoints(player3)
                    val player4Points = totalPoints(player4)
                    val player5Points = totalPoints(player5)

                    if (player1Points > player2Points && player1Points > player3Points &&
                            player1Points > player4Points && player1Points > player5Points)
                        gameWinner_TV.text = player1.playerName.toString() + " WINS!!!"
                    else if (player2Points > player3Points && player2Points > player4Points &&
                            player2Points > player5Points)
                        gameWinner_TV.text = player2.playerName.toString() + " WINS!!!"
                    else if (player3Points > player4Points && player3Points > player5Points)
                        gameWinner_TV.text = player3.playerName.toString() + " WINS!!!"
                    else if (player4Points > player5Points)
                        gameWinner_TV.text = player4.playerName.toString() + " WINS!!!"
                    else
                        gameWinner_TV.text = player5.playerName.toString() + " WINS!!!"


                }
            }
        }
    }

        fun totalPoints(p: Player): Int {
            var points: Int = 0
            for (apples in p.applesStand)
                points += apples.value
            for (bread in p.breadStand)
                points += bread.value
            for (cheese in p.cheeseStand)
                points += cheese.value
            for (chickens in p.chickenStand)
                points += chickens.value
            for (contraband in p.contrabandStand)
                points += contraband.value
            points += p.gold

            return points
        }

}