package com.example.nbawiki.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.*

object Repository {

    private var _teams : MutableLiveData<List<Team>> = MutableLiveData(mutableListOf(
        Team(teamName = "Raising ARIZONA",
            teamDescription = UUID.randomUUID().toString(),
            news = listOf(News("Raising Arizona","Vampire's Kiss"),
                News("Raising Arizona2","Vampire's Kiss2")),
            teamMembers = listOf(Player("James"), Player("Augustas"))
        ),
        Team("Vampire's Kiss", UUID.randomUUID().toString()),
        Team("Con Air", UUID.randomUUID().toString()),
        Team("Gone in 60 Seconds", UUID.randomUUID().toString()),
        Team("National Treasure", UUID.randomUUID().toString()),
        Team("The Wicker Man", UUID.randomUUID().toString()),
        Team("Ghost Rider", UUID.randomUUID().toString()),
        Team("Knowing", UUID.randomUUID().toString())
    ))

    val NbaTeams : LiveData<List<Team>>
        get() = _teams

    fun getTeams(): LiveData<List<Team>> {
        return NbaTeams
    }

    fun getTheTeam(teamName: String): LiveData<Team> {
        val theTeam : Team? = _teams.value?.first {
            it.teamName.equals(teamName)
        }
        return MutableLiveData<Team>(theTeam)
    }
}