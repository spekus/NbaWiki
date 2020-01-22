package com.example.nbawiki.network.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nbawiki.database.LocalDataSource
import com.example.nbawiki.model.presentation.Player
import com.example.nbawiki.network.network.repointerfaces.PlayerRepository

class PlayerRepo(private val dataBase: LocalDataSource) : PlayerRepository {

    override val selectedPlayer: LiveData<Player>
        get() = _selectedPlayer

    private var _selectedPlayer: MutableLiveData<Player> = MutableLiveData(Player())

    override suspend fun refreshThePlayer(id: Int) {
        _selectedPlayer.postValue(dataBase.getThePlayer(id).value)
    }
}