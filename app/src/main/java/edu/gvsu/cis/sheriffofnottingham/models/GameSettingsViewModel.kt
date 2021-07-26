package edu.gvsu.cis.sheriffofnottingham.models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.gvsu.cis.sheriffofnottingham.game.Player

class GameSettingsViewModel : ViewModel() {
    private var _numPlayers = MutableLiveData<Int>()
    private var _p1Name = MutableLiveData<String>()
    private var _p2Name = MutableLiveData<String>()
    private var _p3Name = MutableLiveData<String>()
    private var _p4Name = MutableLiveData<String>()
    private var _p5Name = MutableLiveData<String>()
    private var _player1 = MutableLiveData<Player>()
    private var _player2 = MutableLiveData<Player>()
    private var _player3 = MutableLiveData<Player>()
    private var _player4 = MutableLiveData<Player>()
    private var _player5 = MutableLiveData<Player>()


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

    val player1
        get() = _player1
    val player2
        get() = _player2
    val player3
        get() = _player3
    val player4
        get() = _player4
    val player5
        get() = _player5
}