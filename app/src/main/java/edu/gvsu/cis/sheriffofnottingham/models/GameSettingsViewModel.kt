package edu.gvsu.cis.sheriffofnottingham.models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameSettingsViewModel : ViewModel() {
    private var _numPlayers = MutableLiveData<Int>()
    private var _p1Name = MutableLiveData<String>()
    private var _p2Name = MutableLiveData<String>()
    private var _p3Name = MutableLiveData<String>()
    private var _p4Name = MutableLiveData<String>()
    private var _p5Name = MutableLiveData<String>()

    val numPlayers
        get() = _numPlayers
    val player1Name
        get() = _p1Name
    val player2Name
        get() = _p2Name
    val player3Name
        get() = _p3Name
    val player4Name
        get() = _p4Name
    val player5Name
        get() = _p5Name
}