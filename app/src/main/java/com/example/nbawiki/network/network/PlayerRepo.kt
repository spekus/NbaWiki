package com.example.nbawiki.network.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nbawiki.database.LocalDataSource
import com.example.nbawiki.model.database.PlayerDao
import com.example.nbawiki.model.database.PlayerDb
import com.example.nbawiki.model.presentation.Player
import com.example.nbawiki.network.network.repointerfaces.PlayerRepository

class PlayerRepo(private val playerDao: PlayerDao) : PlayerRepository {

    override val selectedPlayer: LiveData<PlayerDb>
        get() = _selectedPlayer

    private var _selectedPlayer: MutableLiveData<PlayerDb> = MutableLiveData()
//            =
//        MutableLiveData(PlayerDb(1))

    override suspend fun refreshThePlayer(id: Int) {
        _selectedPlayer.postValue(playerDao.getThePlayer(id))
    }
}