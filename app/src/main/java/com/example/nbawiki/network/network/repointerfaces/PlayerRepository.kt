package com.example.nbawiki.network.network.repointerfaces

import androidx.lifecycle.LiveData
import com.example.nbawiki.model.presentation.Player

interface PlayerRepository :
    Repository {
    val selectedPlayer: LiveData<Player>

    suspend fun refreshThePlayer(id : Int)
}