package com.example.nbawiki.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nbawiki.model.DataCreator.createTeams
import com.github.javafaker.Faker
import java.util.*

object Repository {
    private var _teams: MutableLiveData<List<Team>> = MutableLiveData(createTeams())

    val NbaTeams: LiveData<List<Team>>
        get() = _teams

    fun getTeams(): LiveData<List<Team>> {
        NbaTeams
        return NbaTeams
    }

    fun getTheTeam(teamName: String): LiveData<Team> {
        val theTeam: Team? = _teams.value?.first {
            it.teamName.equals(teamName)
        }
        return MutableLiveData<Team>(theTeam)
    }

    fun getThePlayer(playerId: Int): LiveData<Player> {
        return MutableLiveData<Player>(_teams.value!!.find { it.teamMembers.any { it.id == playerId } }!!.teamMembers.find { it.id == playerId })
    }
}
