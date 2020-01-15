package com.example.nbawiki.ui.main.util.api

import com.example.nbawiki.model.dto.TeamDTO

interface ApiService {

    fun getATeams(id : String) :TeamDTO
    fun getAllteams() : List<TeamDTO>

}
