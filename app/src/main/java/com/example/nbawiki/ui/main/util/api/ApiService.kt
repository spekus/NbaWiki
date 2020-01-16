package com.example.nbawiki.ui.main.util.api

import com.example.nbawiki.model.dto.NewsDto
import com.example.nbawiki.model.dto.PlayerDTO
import com.example.nbawiki.model.dto.TeamsGenerated

interface ApiService {


    suspend fun getAllTeams() : List<TeamsGenerated>
    suspend fun getTheTeam(id : String) : TeamsGenerated

    suspend fun getNews(teamID : String) : List<NewsDto>
    suspend fun getPlayers(teamName : String) : List<PlayerDTO>

}
