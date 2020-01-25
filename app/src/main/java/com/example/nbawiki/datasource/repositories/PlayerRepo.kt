package com.example.nbawiki.datasource.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nbawiki.model.database.dao.PlayerDao
import com.example.nbawiki.model.database.db.PlayerDb
import com.example.nbawiki.datasource.repositories.interfaces.PlayerRepository

class PlayerRepo(private val database: PlayerDao) :
    PlayerRepository {

    override val selectedPlayer: LiveData<PlayerDb>
        get() = _selectedPlayer

    private var _selectedPlayer: MutableLiveData<PlayerDb> = MutableLiveData()

    override suspend fun refreshThePlayer(id: Int) {
        _selectedPlayer.postValue(database.getThePlayer(id))
    }
}