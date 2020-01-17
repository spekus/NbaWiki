package com.example.nbawiki.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.nbawiki.model.dto.TeamDTO
import com.example.nbawiki.model.presentation.Player
import com.example.nbawiki.model.presentation.Team
import com.example.nbawiki.ui.main.util.api.retrofit.ApiService


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
        val teamsDTO = nbaApiService.getAllTeams(LEAGUE_KEY)

        val teams: List<Team> = teamsDTO.teams.map {

            it.getPresentationModel()
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
        val playersDTO = nbaApiService.getAllPlayers(theTeam!!.teamName )
        val players = playersDTO.player.map { it.getPresentationModel() }
        theTeam.teamMembers = players
        _theTeam.postValue(theTeam)
    }

    private suspend fun refreshTeamNews(teamId: Int) {
        val newsDto = nbaApiService.getAllNews(teamId.toString())
        val news = newsDto.results.map { it.getPresentationModel() }
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

const val LEAGUE_KEY = "4387"
