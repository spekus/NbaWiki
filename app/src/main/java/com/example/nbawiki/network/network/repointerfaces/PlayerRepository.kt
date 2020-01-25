package com.example.nbawiki.network.network.repointerfaces

import androidx.lifecycle.LiveData
import com.example.nbawiki.model.database.db.PlayerDb

interface PlayerRepository : Repository {
    val selectedPlayer: LiveData<PlayerDb>

    suspend fun refreshThePlayer(id : Int)
}