package com.example.nbawiki.network.network

import androidx.lifecycle.LiveData
import com.example.nbawiki.model.presentation.Team
import com.example.nbawiki.ui.main.util.Event

interface TeamsRepository : Repository {
    val allTeams: LiveData<List<Team>>
    val selectedTeam: LiveData<Team>
    val didApiCallFail: LiveData<Event<Boolean>>

    suspend fun refreshTeams()
    suspend fun refreshTheTeam(id : Int)

}