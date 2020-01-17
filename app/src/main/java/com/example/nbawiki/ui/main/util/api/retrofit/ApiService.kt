package com.example.nbawiki.ui.main.util.api.retrofit

import com.example.nbawiki.model.dto.NewsListDTO
import com.example.nbawiki.model.dto.PlayersDTO
import com.example.nbawiki.model.dto.TeamsDTO

interface ApiService {
    suspend fun getAllTeams(leagueId : String) : TeamsDTO
    suspend fun getAllNews(teamId: String) : NewsListDTO
    suspend fun getAllPlayers(teamName: String) : PlayersDTO
}