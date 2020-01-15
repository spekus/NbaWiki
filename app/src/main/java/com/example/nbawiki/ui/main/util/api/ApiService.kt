package com.example.nbawiki.ui.main.util.api

import com.example.nbawiki.model.dto.TeamDTO
import com.example.nbawiki.model.dto.TeamsGenerated

interface ApiService {

    fun getATeams(id : String) :TeamDTO
    suspend fun getAllteams() : List<TeamsGenerated>
//    suspend fun getNews() :

}
