package com.example.nbawiki.ui.main.features.player

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nbawiki.model.database.db.asRepresentationModel
import com.example.nbawiki.repositories.interfaces.PlayerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlayerViewModel(private val repository : PlayerRepository) : ViewModel() {
    var player : LiveData<Player> = Transformations.map(repository.selectedPlayer) {
        it.asRepresentationModel()
    }

    fun initializePlayerData(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.refreshThePlayer(id)
        }
    }
}
