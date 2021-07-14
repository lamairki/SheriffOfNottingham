package edu.gvsu.cis.sheriffofnottingham

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NottinghamDataViewModel : ViewModel() {
    data class GameSettings(var numPlayers: Int)

    private var _settings = MutableLiveData<GameSettings>()

    // Declare properties with getters
    val settings get() = _settings


}