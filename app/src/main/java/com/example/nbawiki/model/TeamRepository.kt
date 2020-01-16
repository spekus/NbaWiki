package com.example.nbawiki.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nbawiki.model.dto.asPresentationModel
import com.example.nbawiki.model.presentation.Player
import com.example.nbawiki.model.presentation.Team
import com.example.nbawiki.ui.main.util.api.ApiService
import kotlinx.coroutines.*
import timber.log.Timber

class TeamRepository(private val nbaApiService : ApiService) : Repository{

//    private var _teams: MutableLiveData<List<Team>> = MutableLiveData(createTeams())
    private var _teams: MutableLiveData<List<Team>> = MutableLiveData()

    private var _theTeam: MutableLiveData<Team> = MutableLiveData()

    override val nbaTeams: LiveData<List<Team>>
        get() = _teams

    override val selectedTeam: LiveData<Team>
        get() = _theTeam


    private val parentJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + parentJob)



    override fun updateTeams(){
         coroutineScope.launch(Dispatchers.Main) {
             val teamsDTO =  nbaApiService.getAllTeams()
//            val teamas = withContext(Dispatchers.Default) {nbaApiService.getAllteams() }
            Timber.e("team count - ${teamsDTO.count()}")

            val teams :List<Team> =  teamsDTO.map {
                    it.asPresentationModel()
             }
             _teams.postValue(teams)

//             val id : Int ,
//             val teamName : String = "DefaultName",
//             val teamDescription : String = "DefaultDescription",
//             val teamIcon : String = "iconLink",
//             val teamMembers : List<com.example.nbawiki.model.dto.Player> = emptyList(),
//             val news : List<News> = emptyList(),
//             val imageUrl : String = "https://i.picsum.photos/id/$id/200/200.jpg"


            //here live data should be updated
         }
//        Log.e("TeamRepository", teamsss.toString())
//        return nbaTeams
    }

    override fun updateTheTeam(id: Int) {
        // BAD THIS IS CALLED ALL THE TIME
        coroutineScope.launch(Dispatchers.Main) {
            val news = nbaApiService.getNews(id.toString()).map { it.asPresentationModel() }

//            _theTeam.postValue(nbaApiService.getATeams(id.toString()))

            var theTeam: Team? = _teams.value?.first {
                it.id == id
            }

            theTeam!!.news = news

            _theTeam.postValue(theTeam)

            val player = nbaApiService.getPlayers(theTeam.teamName).map { it.asPresentationModel() }
            theTeam.teamMembers = player
            _theTeam.postValue(theTeam)
        }

        val theTeam: Team? = _teams.value?.first {
            it.id == id
        }

//        return MutableLiveData<Team>(theTeam)
    }

    override fun updateThePlayer(id: Int) : MutableLiveData<Player> {
//        coroutineScope.launch(Dispatchers.Main) {
////            val value = nbaApiService.getPlayers("")
////            value.get(0)
//        }


        return MutableLiveData<Player>(_teams.value!!.find { it.teamMembers.any { it.id == id } }!!.teamMembers.find { it.id == id })
    }
}
