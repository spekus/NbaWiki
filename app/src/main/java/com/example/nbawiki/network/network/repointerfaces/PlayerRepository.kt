package com.example.nbawiki.network.network.repointerfaces

import androidx.lifecycle.LiveData
import com.example.nbawiki.model.database.PlayerDb
import com.example.nbawiki.model.presentation.Player

interface PlayerRepository : Repository {
    val selectedPlayer: LiveData<PlayerDb>

    suspend fun refreshThePlayer(id : Int)
}