package com.example.nbawiki.ui.main.features.player

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nbawiki.model.presentation.Player
import com.example.nbawiki.model.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class PlayerViewModel(val repositry : Repository) : ViewModel() {
    var player : LiveData<Player> = repositry.selectedPlayer

    fun initializePlayerData(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repositry.refreshThePlayer(id)
        }
    }
}
