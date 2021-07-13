package edu.gvsu.cis.sheriffofnottingham

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NottinghamDataViewModel : ViewModel() {
    val numPlayers = MutableLiveData<Int>()

}