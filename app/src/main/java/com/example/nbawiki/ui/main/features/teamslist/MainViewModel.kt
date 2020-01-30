package com.example.nbawiki.ui.main.features.teamslist

import androidx.lifecycle.*
import com.example.nbawiki.repositories.TeamsRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(teamsRepository : TeamsRepo) : ViewModel() {
    val teams = Transformations.map(teamukai) {
//        it.asPresentationModel()
//        it?.asPresentationModel()
    }

    private val teamukai = liveData(Dispatchers.IO) {
        val teamsss = teamsRepository.getTeamsWithResponse()
        emitSource(teamsss)
}

    val didApiCallFail = teamsRepository.didApiCallFail

    init {
        viewModelScope.launch(Dispatchers.IO){
            teamsRepository.getTeams()
        }
    }
}
