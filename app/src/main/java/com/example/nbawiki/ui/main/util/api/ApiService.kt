package com.example.nbawiki.ui.main.util.api

import com.example.nbawiki.model.dto.NewsDTO
import com.example.nbawiki.model.dto.PlayerDTO
import com.example.nbawiki.model.dto.TeamsDTO

interface ApiService {


    suspend fun getAllTeams() : List<TeamsDTO>
    suspend fun getTheTeam(id : String) : TeamsDTO

    suspend fun getNews(teamID : String) : List<NewsDTO>
    suspend fun getPlayers(teamName : String) : List<PlayerDTO>

}
