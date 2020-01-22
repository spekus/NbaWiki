package com.example.nbawiki.ui.main.features.team

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nbawiki.model.presentation.Team
import com.example.nbawiki.network.network.repointerfaces.TeamRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TeamViewModel(private val teamRepository: TeamRepository) : ViewModel() {

    var team: LiveData<Team> = teamRepository.selectedTeam
    val didApiCallFail = teamRepository.didApiCallFail

    fun initializeTeamData(teamId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            teamRepository.getTheTeam(teamId)
        }
    }
}
