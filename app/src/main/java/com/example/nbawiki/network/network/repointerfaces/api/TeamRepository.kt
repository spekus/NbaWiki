package com.example.nbawiki.network.network.repointerfaces.api

import androidx.lifecycle.LiveData
import com.example.nbawiki.model.database.db.NewsDb
import com.example.nbawiki.model.database.db.PlayerDb
import com.example.nbawiki.model.database.db.TeamDb

interface TeamRepository : ApiRepository {
    val selectedTeam: LiveData<TeamDb>
    val players: LiveData<List<PlayerDb>>
    val news: LiveData<List<NewsDb>>

    suspend fun getTheTeam(id : Int)

}