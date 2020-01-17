package com.example.nbawiki.ui.main.util.api.retrofit

import com.example.nbawiki.model.dto.TeamDTO
import com.example.nbawiki.model.dto.TeamsDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {

    @GET("/api/v1/json/1/lookup_all_teams.php/")
    suspend fun getAllTeams(@Query("id") leagueId : String) : TeamsDTO


    @GET("/api/v1/json/1/lookup_all_teams.php/?id=4387")
    suspend fun getAllTeams() : TeamsDTO




}