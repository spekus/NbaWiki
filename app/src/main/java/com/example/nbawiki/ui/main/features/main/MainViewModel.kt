package com.example.nbawiki.ui.main.features.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.nbawiki.model.presentation.Team
import com.example.nbawiki.network.network.Repository
import com.example.nbawiki.ui.main.util.MyViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(teamRepository : Repository) : MyViewModel() {
    val teams : LiveData<List<Team>> = teamRepository.nbaTeams

    init {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler){
            teamRepository.refreshTeams()
        }
    }

}
