package com.example.nbawiki.model

import androidx.lifecycle.LiveData

interface Repository {
    val NbaTeams: LiveData<List<Team>>

    fun getTeams() : LiveData<List<Team>>
    fun getTheTeam(id : Int) : LiveData<Team>
    fun getThePlayer(id : Int) : LiveData<Player>

}
