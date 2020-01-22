package com.example.nbawiki.network.network.repointerfaces.api

import androidx.lifecycle.LiveData
import com.example.nbawiki.model.presentation.Team

interface TeamRepository : ApiRepository {
    val selectedTeam: LiveData<Team>

    suspend fun getTheTeam(id : Int)

}