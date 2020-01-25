package com.example.nbawiki.ui.main.features.teamslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nbawiki.datasource.repositories.TeamsRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(teamsRepository : TeamsRepo) : ViewModel() {
    val teams : LiveData<List<TeamCard>> = Transformations.map(teamsRepository.allTeams) {
        it.asPresentationModel()
    }

    val didApiCallFail = teamsRepository.didApiCallFail

    init {
        viewModelScope.launch(Dispatchers.IO){
            teamsRepository.getTeams()
        }
    }
}
