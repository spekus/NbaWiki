package com.example.nbawiki.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.nbawiki.model.dto.asPresentationModel
import com.example.nbawiki.model.presentation.Player
import com.example.nbawiki.model.presentation.Team
import com.example.nbawiki.ui.main.util.api.ApiService
import com.example.nbawiki.ui.main.util.api.retrofit.WebService
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class TeamRepository(private val nbaApiService: ApiService) : Repository {

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
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.thesportsdb.com")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        val api = retrofit.create(WebService::class.java)

        val teamsDTO = api.getAllTeams("4387")

        val teams: List<Team> = teamsDTO.teams.map {
            it.asPresentationModel()
        }
        _teams.postValue(teams)
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
        val player = nbaApiService.getPlayers(theTeam!!.teamName).map { it.asPresentationModel() }
        theTeam.teamMembers = player
        _theTeam.postValue(theTeam)
    }

    private suspend fun refreshTeamNews(teamId: Int) {
        val news = nbaApiService.getNews(teamId.toString()).map { it.asPresentationModel() }
        var theTeam: Team? = getSelectedTeam(teamId)
        theTeam!!.news = news
        _theTeam.postValue(theTeam)
    }

    private fun getSelectedTeam(teamId: Int): Team? {
        return _teams.value?.first {
            it.id == teamId
        }
    }
}
