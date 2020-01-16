package com.example.nbawiki.ui.main.features.player

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.nbawiki.model.presentation.Player
import com.example.nbawiki.model.Repository
import com.example.nbawiki.model.presentation.Team

class PlayerViewModel(val repositry : Repository) : ViewModel() {
    var player : LiveData<Player> = repositry.selectedPlayer

    fun initializePlayerData(id: Int) {
        repositry.updateThePlayer(id)
    }
}
