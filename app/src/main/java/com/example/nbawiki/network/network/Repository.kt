package com.example.nbawiki.network.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nbawiki.model.presentation.Player
import com.example.nbawiki.model.presentation.Team

interface Repository {
    val nbaTeams: LiveData<List<Team>>
    val selectedTeam: LiveData<Team>
    val selectedPlayer: LiveData<Player>

    suspend fun refreshTeams()
    suspend fun refreshTheTeam(id : Int)
    suspend fun refreshThePlayer(id : Int)
}
