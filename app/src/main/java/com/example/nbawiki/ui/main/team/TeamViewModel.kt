package com.example.nbawiki.ui.main.team

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.nbawiki.model.TeamRepository
import com.example.nbawiki.model.Team

class TeamViewModel : ViewModel() {
    lateinit var team : LiveData<Team>

    fun initializeTeamData(teamId: Int) {
        team  =  TeamRepository.getTheTeam(teamId)
    }
}
