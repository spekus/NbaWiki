package com.example.nbawiki.network.network.repointerfaces.api

import androidx.lifecycle.LiveData
import com.example.nbawiki.model.database.PlayerDb
import com.example.nbawiki.model.presentation.Team

interface TeamRepository : ApiRepository {
    val selectedTeam: LiveData<Team>
    val players: LiveData<List<PlayerDb>>

    suspend fun getTheTeam(id : Int)

}