package com.example.nbawiki.repositories.interfaces

import androidx.lifecycle.LiveData
import com.example.nbawiki.model.database.db.PlayerDb

interface PlayerRepository {
    val selectedPlayer: LiveData<PlayerDb>

    suspend fun refreshThePlayer(id : Int)
}