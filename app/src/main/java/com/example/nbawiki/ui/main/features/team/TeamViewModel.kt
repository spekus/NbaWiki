package com.example.nbawiki.ui.main.features.team

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nbawiki.model.presentation.Team
import com.example.nbawiki.network.network.Repository
import com.example.nbawiki.network.network.TeamsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TeamViewModel(private val teamRepository: TeamsRepository) : ViewModel() {

    var team: LiveData<Team> = teamRepository.selectedTeam
    val didApiCallFail = teamRepository.didApiCallFail

    fun initializeTeamData(teamId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            teamRepository.refreshTheTeam(teamId)
        }
    }
}
