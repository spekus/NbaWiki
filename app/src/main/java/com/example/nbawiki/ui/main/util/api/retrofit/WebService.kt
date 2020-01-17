package com.example.nbawiki.ui.main.util.api.retrofit

import com.example.nbawiki.model.dto.*
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {
    @GET("/api/v1/json/1/lookup_all_teams.php/")
    suspend fun getAllTeams(@Query("id") leagueId : String) : TeamsDTO


    @GET("/api/v1/json/1/eventslast.php")
    suspend fun getAllNews(@Query("id") teamId: String): NewsListDTO


    @GET("/api/v1/json/1/searchplayers.php")
    suspend fun getAllPlayers(@Query("t") teamName: String): PlayersDTO
}