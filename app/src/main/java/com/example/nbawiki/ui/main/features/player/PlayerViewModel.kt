package com.example.nbawiki.ui.main.features.player

import androidx.lifecycle.*
import com.example.nbawiki.model.database.db.PlayerDb
import com.example.nbawiki.model.database.db.asRepresentationModel
import com.example.nbawiki.repositories.interfaces.PlayerRepository
import com.example.nbawiki.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Named


class PlayerViewModel @Inject constructor(
    private val repository: PlayerRepository,
    @Named("Player Id123")
    private val playerid: Int
) : ViewModel() {
    //    private var playerId: MutableLiveData<Int> = MutableLiveData()
    var selectedPlayer = MutableLiveData<PlayerDb>()
//    var selectedPlayer = liveData(Dispatchers.IO) {
//        val smth = repository.getThePlayer(playerid)
//        emit(smth)
//    }

    var player: LiveData<Player> = Transformations.map(selectedPlayer) {
        it.asRepresentationModel()
    }

//
//
//    val onError = MutableLiveData<Exception>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            selectedPlayer.postValue( repository.getThePlayer(playerid) )
        }
    }
//
//    fun initializePlayerData(id: Int) {
//
//    }

//    fun onFetchData() {
//        viewModelScope.launch {
//            when (val result = repository.getPlayers()) {
//                is Result.Success -> player = result.data
//                is Result.Error -> onError.value = result.exception
//            }
//        }
//    }


}
