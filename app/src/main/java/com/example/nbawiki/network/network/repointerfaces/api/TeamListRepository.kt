package com.example.nbawiki.network.network.repointerfaces.api

import androidx.lifecycle.LiveData
import com.example.nbawiki.model.presentation.Team

interface TeamListRepository : ApiRepository {
    val allTeams: LiveData<List<Team>>

    suspend fun getTeams()
}