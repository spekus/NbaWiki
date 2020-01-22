package com.example.nbawiki.ui.main.features.player

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nbawiki.model.presentation.Player
import com.example.nbawiki.network.network.repointerfaces.PlayerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlayerViewModel(private val repository : PlayerRepository) : ViewModel() {
    var player : LiveData<Player> = repository.selectedPlayer

    fun initializePlayerData(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.refreshThePlayer(id)
        }
    }
}
