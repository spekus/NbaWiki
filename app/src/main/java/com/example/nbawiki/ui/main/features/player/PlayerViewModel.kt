package com.example.nbawiki.ui.main.features.player

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.nbawiki.model.presentation.Player
import com.example.nbawiki.network.network.Repository
import com.example.nbawiki.ui.main.util.MyViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlayerViewModel(val repositry : Repository) : MyViewModel() {
    var player : LiveData<Player> = repositry.selectedPlayer

    fun initializePlayerData(id: Int) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            repositry.refreshThePlayer(id)
        }
    }
}

