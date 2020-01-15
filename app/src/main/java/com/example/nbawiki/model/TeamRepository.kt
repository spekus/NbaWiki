package com.example.nbawiki.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nbawiki.model.DataCreator.createTeams
import com.example.nbawiki.ui.main.util.api.ApiService
import java.net.HttpURLConnection
import java.net.URL

class TeamRepository(private val nbaApiService : ApiService) : Repository{
    init {

    }
    private var _teams: MutableLiveData<List<Team>> = MutableLiveData(createTeams())

    val NbaTeams: LiveData<List<Team>>
        get() = _teams

    override fun getTeams(): LiveData<List<Team>> {
        return NbaTeams
    }

    override fun getTheTeam(id: Int): LiveData<Team> {
        val theTeam: Team? = _teams.value?.first {
            it.id == id
        }
        return MutableLiveData<Team>(theTeam)
    }

    override fun getThePlayer(id: Int): LiveData<Player> {
        return MutableLiveData<Player>(_teams.value!!.find { it.teamMembers.any { it.id == id } }!!.teamMembers.find { it.id == id })
    }
}
