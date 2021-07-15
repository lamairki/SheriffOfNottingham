package edu.gvsu.cis.sheriffofnottingham

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NottinghamDataViewModel : ViewModel() {
    data class GameSettings(val numPlayers: Int) {
        var player1Name: String = ""
        var player2Name: String = ""
        var player3Name: String = ""
        var player4Name: String = ""
        var player5Name: String = ""
    }

    private var _settings = MutableLiveData<GameSettings>()

    // Declare properties with getters
    val settings get() = _settings


}