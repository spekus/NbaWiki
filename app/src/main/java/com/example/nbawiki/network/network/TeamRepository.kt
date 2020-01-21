package com.example.nbawiki.network.network

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.nbawiki.model.presentation.Player
import com.example.nbawiki.model.presentation.Team
import com.example.nbawiki.network.retrofit.WebService
import com.example.nbawiki.ui.main.util.Event


class TeamRepository(
    private val nbaApiService: WebService,
    private val context: Context,
    private val dataBase: LocalDataSource
) :
    Repository {

    private val _didApiCallFail = MutableLiveData<Event<Boolean>>()

    override val didApiCallFail: LiveData<Event<Boolean>>
        get() = _didApiCallFail

    private var _teams: MutableLiveData<List<Team>> = MutableLiveData()

    override val nbaTeams: LiveData<List<Team>>
        get() = _teams

    private var _theTeam: MutableLiveData<Team> = MutableLiveData()

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
        val teamsResponse = nbaApiService.getAllTeams(LEAGUE_KEY)
        if (teamsResponse.isSuccessful) {
            var teams: List<Team> = teamsResponse.body()!!.teams.map {
                it.getPresentationModel()
            }
            _teams.postValue(teams)
            teams.forEach {
                dataBase.putTeam(it)
            }

            teams = dataBase.getAllTeams()
        }else{
            _didApiCallFail.postValue(Event(true))
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
        var theTeam: Team? = getSelectedTeam(teamID)
        val playersResponse = nbaApiService.getAllPlayers(theTeam!!.teamName)
        if (playersResponse.isSuccessful) {
            val players = playersResponse.body()!!.player.map { it.getPresentationModel() }
            theTeam.teamMembers = players
            _theTeam.postValue(theTeam)

            players.forEach {
                dataBase.putPlayer(it, teamID)
            }
        }else{
            _didApiCallFail.postValue(Event(true))
        }
    }

    private suspend fun refreshTeamNews(teamId: Int) {
        val newsResponse = nbaApiService.getAllNews(teamId.toString())
        if (newsResponse.isSuccessful) {
            val news = newsResponse.body()!!.results.map { it.getPresentationModel() }
            val theTeam: Team? = getSelectedTeam(teamId)
            theTeam!!.news = news
            _theTeam.postValue(theTeam)

            news.forEach {
                dataBase.putNews(it, teamId)
            }
        }else{
            _didApiCallFail.postValue(Event(true))
        }
    }

    private fun getSelectedTeam(teamId: Int): Team? {
        return _teams.value?.first {
            it.id == teamId
        }
    }
}

const val LEAGUE_KEY = "4387"
