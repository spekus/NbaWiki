package com.example.nbawiki.network.retrofit

import com.example.nbawiki.model.dto.news.NewsListDTO
import com.example.nbawiki.model.dto.players.PlayersListDTO
import com.example.nbawiki.model.dto.teams.TeamsDTO
import retrofit2.Response

interface ApiService {
    suspend fun getAllTeams(leagueId : String) : Response<TeamsDTO>
    suspend fun getAllNews(teamId: String) : Response<NewsListDTO>
    suspend fun getAllPlayers(teamName: String) : Response<PlayersListDTO>
}