package com.example.nbawiki.ui.main.player

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.nbawiki.model.Player
import com.example.nbawiki.model.Repository
import com.example.nbawiki.model.Team

class PlayerViewModel : ViewModel() {
    lateinit var player : LiveData<Player>

    fun initializePlayerData(id: Int) {
        player  =  Repository.getThePlayer(id)
    }
}
