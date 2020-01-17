package com.example.nbawiki.network.retrofit

import com.example.nbawiki.model.dto.news.NewsListDTO
import com.example.nbawiki.model.dto.players.PlayersListDTO
import com.example.nbawiki.model.dto.teams.TeamsDTO

interface ApiService {
    suspend fun getAllTeams(leagueId : String) : TeamsDTO
    suspend fun getAllNews(teamId: String) : NewsListDTO
    suspend fun getAllPlayers(teamName: String) : PlayersListDTO
}