package com.example.nbawiki.repositories.interfaces.api

import androidx.lifecycle.LiveData
import com.example.nbawiki.model.database.db.NewsDb
import com.example.nbawiki.model.database.db.PlayerDb
import com.example.nbawiki.model.database.db.TeamDb
import com.example.nbawiki.util.Status

interface TeamRepository  {
    suspend fun getTheTeam(teamId : Int) : LiveData<TeamDb>
    suspend fun getPlayers(teamId : Int) : LiveData<Status<List<PlayerDb>>>
    suspend fun getNews(teamId : Int) : LiveData<Status<List<NewsDb>>>
}