package com.example.nbawiki.ui.main.team

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.nbawiki.model.Repository
import com.example.nbawiki.model.presentation.Team

class TeamViewModel(private val teamRepository : Repository) : ViewModel() {
    
    val team : LiveData<Team> = teamRepository.selectedTeam

    fun initializeTeamData(teamId: Int) {
         teamRepository.getTheTeam(teamId)
    }
}
