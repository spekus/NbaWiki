package com.example.nbawiki.network.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.nbawiki.model.presentation.Player
import com.example.nbawiki.model.presentation.Team
import com.example.nbawiki.network.retrofit.ApiService
import com.example.nbawiki.network.retrofit.WebService
import com.example.nbawiki.ui.main.util.Constants.repository


class TeamRepository(private val nbaApiService: ApiService) :
    Repository {

    private var _teams: MutableLiveData<List<Team>> = MutableLiveData()

    private var _theTeam: MutableLiveData<Team> = MutableLiveData()

    override val nbaTeams: LiveData<List<Team>>
        get() = _teams

    override val selectedTeam: LiveData<Team>
        get() = _theTeam


    private var selectedPlayerId: MutableLiveData<String> = MutableLiveData()

    override val selectedPlayer: LiveData<Player>
        get() = _player

    private var _player: LiveData<Player> = Transformations.switchMap(
        selectedPlayerId,
        ::getPlayer
    )

    private fun getPlayer(playerId: String): LiveData<Player> {
        return MutableLiveData(selectedTeam?.value?.teamMembers?.firstOrNull() { it.id.toString() == playerId }
            ?: Player())
    }

    override suspend fun refreshTeams() {
        val apiResponse = nbaApiService.getAllTeams(LEAGUE_KEY)
        if(apiResponse.isSuccessful){
            val teams: List<Team> = apiResponse.body()!!.teams.map {
                it.getPresentationModel()
            }
            _teams.postValue(teams)
        }
    }

    override suspend fun refreshThePlayer(id: Int) {
        selectedPlayerId.postValue(id.toString())
    }

    override suspend fun refreshTheTeam(teamID: Int) {
        refreshTeamNews(teamID)
        refreshTeamPlayer(teamID)
    }

    private suspend fun refreshTeamPlayer(teamID: Int) {
        val theTeam: Team? = getSelectedTeam(teamID)
        val apiResponse = nbaApiService.getAllPlayers(theTeam!!.teamName )
        if(apiResponse.isSuccessful)  {
            val players = apiResponse.body()!!.player.map { it.getPresentationModel() }
            theTeam.teamMembers = players
            _theTeam.postValue(theTeam)
        }
    }

    private suspend fun refreshTeamNews(teamId: Int) {
        val apiResponse = nbaApiService.getAllNews(teamId.toString())
        if(apiResponse.isSuccessful){
            val news = apiResponse.body()!!.results.map { it.getPresentationModel() }
            val theTeam: Team? = getSelectedTeam(teamId)
            theTeam!!.news = news
            _theTeam.postValue(theTeam)
        }
    }

    private fun getSelectedTeam(teamId: Int): Team? {
        return _teams.value?.first {
            it.id == teamId
        }
    }
}

const val LEAGUE_KEY = "4387"
