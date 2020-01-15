package com.example.nbawiki.ui.main.team

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.nbawiki.model.Repository
import com.example.nbawiki.model.TeamRepository
import com.example.nbawiki.model.Team

class TeamViewModel(private val teamRepository : Repository) : ViewModel() {
    lateinit var team : LiveData<Team>

    fun initializeTeamData(teamId: Int) {
        team  =  teamRepository.getTheTeam(teamId)
    }
}
