package com.example.nbawiki.ui.main.features.player

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nbawiki.model.presentation.Player
import com.example.nbawiki.network.network.Repository
import com.example.nbawiki.ui.main.util.Constants.coroutineExceptionHandler
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class PlayerViewModel(val repositry : Repository) : ViewModel() {
    var player : LiveData<Player> = repositry.selectedPlayer

    fun initializePlayerData(id: Int) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            repositry.refreshThePlayer(id)
        }
    }
}

