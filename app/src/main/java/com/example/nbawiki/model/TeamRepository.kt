package com.example.nbawiki.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nbawiki.model.DataCreator.createTeams
import com.example.nbawiki.ui.main.util.api.ApiService
import kotlinx.coroutines.*
import timber.log.Timber
import java.net.HttpURLConnection
import java.net.URL

class TeamRepository(private val nbaApiService : ApiService) : Repository{

    private var _teams: MutableLiveData<List<Team>> = MutableLiveData(createTeams())


    private val parentJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + parentJob)


    val NbaTeams: LiveData<List<Team>>
        get() = _teams

    override fun getTeams(): LiveData<List<Team>> {
         coroutineScope.launch(Dispatchers.Main) {
             val teamsFromApi =  nbaApiService.getAllteams()
//            val teamas = withContext(Dispatchers.Default) {nbaApiService.getAllteams() }
            Timber.e("team count - ${teamsFromApi.count()}")
            //here live data should be updated
         }


//        Log.e("TeamRepository", teamsss.toString())



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
