package com.example.nbawiki.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nbawiki.model.database.dao.PlayerDao
import com.example.nbawiki.model.database.db.PlayerDb
import com.example.nbawiki.repositories.interfaces.PlayerRepository
import javax.inject.Inject

class PlayerRepo @Inject constructor (private val database: PlayerDao) :
    PlayerRepository {


//    var selectedPlayer = liveData {
//        val smth = getThePlayer(playerid)
//        emit(smth)
//    }

    override val selectedPlayer: LiveData<PlayerDb>
        get() = _selectedPlayer

    private var _selectedPlayer: MutableLiveData<PlayerDb> = MutableLiveData()
//
//    override suspend fun refreshThePlayer(id: Int) {
//        _selectedPlayer.postValue(database.getThePlayer(id))
//    }

    override suspend fun getThePlayer(id: Int): LiveData<PlayerDb> {
        return database.getThePlayer(id)
    }
//
//    suspend fun getPlayers(teamId: Int) : Result<LiveData<List<Player>>> {
//        withContext(Dispatchers.IO) {
//            try {
//                if (prefs.outOfDate()) {
//                    when (val result = refreshThePlayer(teamId)) {
//                        is Result.Success -> {
//                            return@withContext Result.Success(database)
//                        }
//                        is Result.Error -> return@withContext Result.Error
//                    }
//                }
//                return@withContext Result.Success(players)
//
//            } catch (e: Exception) {
//                return@withContext Result.Error<LiveData<List<Player>>>(e.also { Timber.e(it) })
//            }
//        }
//    }
}