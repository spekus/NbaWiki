package com.example.nbawiki.ui.main.features.player

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nbawiki.model.presentation.Player
import com.example.nbawiki.network.network.PlayerRepository
import com.example.nbawiki.network.network.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlayerViewModel(val repositry : PlayerRepository) : ViewModel() {
    var player : LiveData<Player> = repositry.selectedPlayer

    fun initializePlayerData(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repositry.refreshThePlayer(id)
        }
    }
}
