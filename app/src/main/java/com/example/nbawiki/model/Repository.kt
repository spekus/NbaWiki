package com.example.nbawiki.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nbawiki.model.presentation.Player
import com.example.nbawiki.model.presentation.Team

interface Repository {
    val nbaTeams: LiveData<List<Team>>
    val selectedTeam: LiveData<Team>

    fun updateTeams()
    fun updateTheTeam(id : Int)
    fun updateThePlayer(id : Int) : MutableLiveData<Player>
}
