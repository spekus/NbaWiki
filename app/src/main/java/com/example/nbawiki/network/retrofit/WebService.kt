package com.example.nbawiki.network.retrofit

import com.example.nbawiki.model.dto.news.NewsListDTO
import com.example.nbawiki.model.dto.players.PlayersListDTO
import com.example.nbawiki.model.dto.teams.TeamsDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService : ApiService {
    @GET("/api/v1/json/1/lookup_all_teams.php/")
    override suspend fun getAllTeams(@Query("id") leagueId : String) : Response<TeamsDTO>


    @GET("/api/v1/json/1/eventslast.php")
    override suspend fun getAllNews(@Query("id") teamId: String): Response<NewsListDTO>


    @GET("/api/v1/json/1/searchplayers.php")
    override suspend fun getAllPlayers(@Query("t") teamName: String): Response<PlayersListDTO>
}