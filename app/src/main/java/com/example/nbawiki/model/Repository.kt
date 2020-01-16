package com.example.nbawiki.model

import androidx.lifecycle.LiveData
import com.example.nbawiki.model.presentation.Player
import com.example.nbawiki.model.presentation.Team

interface Repository {
    val nbaTeams: LiveData<List<Team>>
    val selectedTeam: LiveData<Team>

    fun getTeams() : LiveData<List<Team>>
    fun getTheTeam(id : Int) : LiveData<Team>
    fun getThePlayer(id : Int) : LiveData<Player>
}
