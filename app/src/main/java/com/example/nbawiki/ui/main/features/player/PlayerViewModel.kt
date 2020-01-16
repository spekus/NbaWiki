package com.example.nbawiki.ui.main.features.player

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.nbawiki.model.presentation.Player
import com.example.nbawiki.model.Repository

class PlayerViewModel(val repositry : Repository) : ViewModel() {
    var player : LiveData<Player> = repositry.selectedPlayer

    fun initializePlayerData(id: Int) {
        repositry.refreshThePlayer(id)
    }
}
