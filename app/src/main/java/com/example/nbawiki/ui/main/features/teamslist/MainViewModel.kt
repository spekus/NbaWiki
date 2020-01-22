package com.example.nbawiki.ui.main.features.teamslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nbawiki.model.presentation.Team
import com.example.nbawiki.network.network.TeamsRepo
import com.example.nbawiki.network.network.repointerfaces.api.TeamRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(teamsRepository : TeamsRepo) : ViewModel() {
    val teams : LiveData<List<Team>> = teamsRepository.allTeams
    val didApiCallFail = teamsRepository.didApiCallFail

    init {
        viewModelScope.launch(Dispatchers.IO){
            teamsRepository.getTeams()
        }
    }
}
