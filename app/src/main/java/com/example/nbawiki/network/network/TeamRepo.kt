package com.example.nbawiki.network.network

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nbawiki.database.LocalDataSource
import com.example.nbawiki.model.dto.Dto
import com.example.nbawiki.model.dto.news.NewsDTO
import com.example.nbawiki.model.dto.players.PlayerDTO
import com.example.nbawiki.model.dto.teams.TeamDTO
import com.example.nbawiki.model.presentation.News
import com.example.nbawiki.model.presentation.Player
import com.example.nbawiki.model.presentation.Team
import com.example.nbawiki.network.network.repointerfaces.TeamRepository
import com.example.nbawiki.network.retrofit.WebService
import com.example.nbawiki.ui.main.util.Event
import com.example.nbawiki.ui.main.util.UpdateTime
import com.github.guilhe.sharedprefsutils.ktx.get
import com.github.guilhe.sharedprefsutils.ktx.put
import timber.log.Timber
import java.util.*

class TeamRepo(
    private val nbaApiService: WebService,
    private val context: Context,
    private val dataBase: LocalDataSource
) : TeamRepository {
    private val sharedPref: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)

    private val _didApiCallFail = MutableLiveData<Event<Boolean>>()

    override val didApiCallFail: LiveData<Event<Boolean>>
        get() = _didApiCallFail

    private var _teams: MutableLiveData<List<Team>> = MutableLiveData()

    override val allTeams: LiveData<List<Team>>
        get() = _teams

    private var _selectedTeam: MutableLiveData<Team> = MutableLiveData()

    override val selectedTeam: LiveData<Team>
        get() = _selectedTeam

    override suspend fun getTeams() {
        if (isItTimeToUpdate(TEAM_PREF_KEY, UpdateTime.TEAM.timeBeforeUpdate)) {
            refreshTeams()
        }
        _teams.postValue(dataBase.getAllTeams())
    }

    private suspend fun refreshTeams() {
        val teamsResponse = nbaApiService.getAllTeams(LEAGUE_KEY)
        when (teamsResponse.isSuccessful) {
            true -> {
                updateDatabase(teamsResponse.body()!!.teams)
                updateTimePreferences(TEAM_PREF_KEY)
            }

            else -> _didApiCallFail.postValue(Event(true))
        }
    }

    override suspend fun getTheTeam(teamID: Int) {
        //load old data
        _selectedTeam.postValue(dataBase.getTheTeam(teamID))

        //refresh
        val shouldNewsBeUpdated =
            isItTimeToUpdate(NEWS_PREF_KEY + teamID, UpdateTime.EVENT.timeBeforeUpdate)
        val shouldPlayersBeUpdated =
            isItTimeToUpdate(PLAYER_PREF_KEY + teamID, UpdateTime.PLAYER.timeBeforeUpdate)
        if (shouldNewsBeUpdated) {
            refreshTeamNews(teamID)
        }
        if (shouldPlayersBeUpdated) {
            refreshTeamPlayer(teamID)
        }

        //update with new data if there was an api call
        if (shouldNewsBeUpdated || shouldNewsBeUpdated) {
            _selectedTeam.postValue(dataBase.getTheTeam(teamID))
        }
    }

    private suspend fun refreshTeamPlayer(teamID: Int) {
        var theTeam: Team? = dataBase.getTheTeam(teamID)
        val playersResponse = nbaApiService.getAllPlayers(theTeam!!.teamName)
        when (playersResponse.isSuccessful) {
            true -> {
                updateDatabase(playersResponse.body()!!.player, teamID)
                updateTimePreferences(PLAYER_PREF_KEY, teamID)
            }
            else -> _didApiCallFail.postValue(Event(true))
        }
    }

    private suspend fun refreshTeamNews(teamId: Int) {
        val newsResponse = nbaApiService.getAllNews(teamId.toString())
        when (newsResponse.isSuccessful) {
            true -> {
                updateDatabase(newsResponse.body()!!.results, teamId)
                updateTimePreferences(NEWS_PREF_KEY, teamId)
            }

            else -> _didApiCallFail.postValue(Event(true))
        }
    }

    private fun updateDatabase(body: List<Dto>, teamID: Int = 0) {
        when (body.first()) {
            is PlayerDTO -> body.map { it.getPresentationModel() }.forEach {
                dataBase.putPlayer(
                    it as Player,
                    teamID
                )
            }
            is NewsDTO -> body.map { it.getPresentationModel() }.forEach {
                dataBase.putNews(
                    it as News,
                    teamID
                )
            }
            is TeamDTO -> body.map { it.getPresentationModel() }.forEach { dataBase.putTeam(it as Team) }
        }
    }

    private fun isItTimeToUpdate(sharePrefId: String, timeBeforeUpdate: Long): Boolean {
        val lastUpdate = sharedPref.get(sharePrefId, Long::class.java, 1)
        if (lastUpdate == 1L) {
            Timber.e("could not get shared pref for key $sharePrefId")
        }
        val timePassed = System.currentTimeMillis() - lastUpdate
        return timePassed > timeBeforeUpdate
    }


    private fun updateTimePreferences(key: String, teamId: Int? = null) {
        val currentTime = Date(System.currentTimeMillis()).time
        when (teamId) {
            null -> sharedPref.put(key, currentTime)
            else -> sharedPref.put(key + teamId, currentTime)
        }
    }
}

const val LEAGUE_KEY = "4387"
const val PRIVATE_MODE = 0
const val PREF_NAME = "pref_data"
const val TEAM_PREF_KEY = "team_update_"
const val PLAYER_PREF_KEY = "player_update_"
const val NEWS_PREF_KEY = "news_update_"