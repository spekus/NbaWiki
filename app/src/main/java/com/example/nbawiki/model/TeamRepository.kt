package com.example.nbawiki.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nbawiki.model.DataCreator.createTeams
import com.example.nbawiki.ui.main.util.api.ApiService
import kotlinx.coroutines.*
import timber.log.Timber

class TeamRepository(private val nbaApiService : ApiService) : Repository{

//    private var _teams: MutableLiveData<List<Team>> = MutableLiveData(createTeams())
    private var _teams: MutableLiveData<List<Team>> = MutableLiveData()


    private val parentJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + parentJob)


    override val NbaTeams: LiveData<List<Team>>
        get() = _teams

    override fun getTeams(): LiveData<List<Team>> {
         coroutineScope.launch(Dispatchers.Main) {
             val teamsDTO =  nbaApiService.getAllteams()
//            val teamas = withContext(Dispatchers.Default) {nbaApiService.getAllteams() }
            Timber.e("team count - ${teamsDTO.count()}")

            val teams :List<Team> =  teamsDTO.map {
                 Team(
                     id = it.idTeam,
                     teamName = it.strTeam ?: "",
                     teamDescription = it.strDescriptionEN ?: "",
                     imageUrl  = it.strTeamBanner ?: "https://i.picsum.photos/id/1/200/200.jpg"
                 )
             }
             _teams.postValue(teams)

//             val id : Int ,
//             val teamName : String = "DefaultName",
//             val teamDescription : String = "DefaultDescription",
//             val teamIcon : String = "iconLink",
//             val teamMembers : List<Player> = emptyList(),
//             val news : List<News> = emptyList(),
//             val imageUrl : String = "https://i.picsum.photos/id/$id/200/200.jpg"


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
