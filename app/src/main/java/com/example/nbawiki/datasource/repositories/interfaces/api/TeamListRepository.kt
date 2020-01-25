package com.example.nbawiki.datasource.repositories.interfaces.api

import androidx.lifecycle.LiveData
import com.example.nbawiki.model.database.db.TeamDb

interface TeamListRepository : ApiErrorHolder {
    val allTeams: LiveData<List<TeamDb>>

    suspend fun getTeams()
}