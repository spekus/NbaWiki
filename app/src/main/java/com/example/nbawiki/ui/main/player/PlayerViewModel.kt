package com.example.nbawiki.ui.main.player

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.nbawiki.model.presentation.Player
import com.example.nbawiki.model.Repository

class PlayerViewModel(val repositry : Repository) : ViewModel() {
    lateinit var player : LiveData<Player>

    fun initializePlayerData(id: Int) {
        player  =  repositry.getThePlayer(id)
    }


}
