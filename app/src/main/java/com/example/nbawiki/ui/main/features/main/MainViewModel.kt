package com.example.nbawiki.ui.main.features.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nbawiki.model.presentation.Team
import com.example.nbawiki.network.network.repointerfaces.TeamRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(teamRepository : TeamRepository) : ViewModel() {
    val teams : LiveData<List<Team>> = teamRepository.allTeams
    val didApicallFail = teamRepository.didApiCallFail

    init {
        viewModelScope.launch(Dispatchers.IO){
            teamRepository.getTeams()
        }
    }
}
