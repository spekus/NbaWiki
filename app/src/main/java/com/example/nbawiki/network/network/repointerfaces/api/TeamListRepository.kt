package com.example.nbawiki.network.network.repointerfaces.api

import androidx.lifecycle.LiveData
import com.example.nbawiki.model.database.db.TeamDb

interface TeamListRepository : ApiRepository {
    val allTeams: LiveData<List<TeamDb>>

    suspend fun getTeams()
}