package com.example.nbawiki.network.network.repointerfaces

import androidx.lifecycle.LiveData
import com.example.nbawiki.model.presentation.Team
import com.example.nbawiki.ui.main.util.Event

interface TeamRepository : Repository {
    val allTeams: LiveData<List<Team>>
    val selectedTeam: LiveData<Team>
    val didApiCallFail: LiveData<Event<Boolean>>

    suspend fun getTeams()
    suspend fun getTheTeam(id : Int)

}