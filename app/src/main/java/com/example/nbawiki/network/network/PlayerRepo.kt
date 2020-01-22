package com.example.nbawiki.network.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nbawiki.database.LocalDataSource
import com.example.nbawiki.model.presentation.Player
import com.example.nbawiki.network.network.repointerfaces.PlayerRepository

class PlayerRepo(private val dataBase: LocalDataSource) :
    PlayerRepository {

//    private var selectedPlayerId: MutableLiveData<String> = MutableLiveData()

    override val selectedPlayer: LiveData<Player>
        get() = _selectedPlayer

    private var _selectedPlayer: MutableLiveData<Player> = MutableLiveData(Player())
//    private var _selectedPlayer: LiveData<Player> = Transformations.switchMap(
//        selectedPlayerId,
//        ::getThePlayer
//    )

    override suspend fun refreshThePlayer(id: Int) {
//        selectedPlayerId.postValue(id.toString())
        _selectedPlayer.postValue(dataBase.getThePlayer(id).value)
    }
//
//    private fun getThePlayer(playerId: String): LiveData<Player> {
//        val player = dataBase.getThePlayer(playerId.toInt())
//        return player
//    }
}