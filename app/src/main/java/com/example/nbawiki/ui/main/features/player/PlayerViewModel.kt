package com.example.nbawiki.ui.main.features.player

import androidx.lifecycle.*
import com.example.nbawiki.model.database.db.asRepresentationModel
import com.example.nbawiki.repositories.interfaces.PlayerRepository
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Named


class PlayerViewModel @Inject constructor(
    private val repository: PlayerRepository,
    @Named("Player Id123")
    private val playerid: Int
) : ViewModel() {

    private val selectedPlayer = liveData(Dispatchers.IO) {
        val playerFromRepository = repository.getThePlayer(playerid)
        emitSource(playerFromRepository)
    }

    val playerAsUiElement: LiveData<Player> = Transformations.map(selectedPlayer) {
        it.asRepresentationModel()
    }

//
//
//    val onError = MutableLiveData<Exception>()
//
//    init {
//        viewModelScope.launch(Dispatchers.IO) {
//            selectedPlayer.postValue( repository.getThePlayer(playerid) )
//        }
//    }
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
