package com.example.nbawiki.network.network

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.nbawiki.model.presentation.Player
import com.example.nbawiki.model.presentation.Team
import com.example.nbawiki.network.retrofit.WebService
import com.example.nbawiki.ui.main.util.Event
import com.example.nbawiki.ui.main.util.UpdateTime
import com.github.guilhe.sharedprefsutils.ktx.get
import com.github.guilhe.sharedprefsutils.ktx.put
import timber.log.Timber
import java.util.*


class TeamRepository(
    private val nbaApiService: WebService,
    private val context: Context,
    private val dataBase: LocalDataSource
) :
    Repository {
    private val sharedPref: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)

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
        get() = _selectedPlayer

    private var _selectedPlayer: LiveData<Player> = Transformations.switchMap(
        selectedPlayerId,
        ::getThePlayer
    )

    private fun getThePlayer(playerId: String): LiveData<Player> {
        return dataBase.getThePlayer(playerId.toInt())
    }

    override suspend fun refreshTeams() {
        when (isItTimeToUpdate(TEAM_PREF_KEY, UpdateTime.TEAM.timeBeforeUpdate)) {
            true -> {
                updateTeamsViaApi()
            }
            false -> {
                _teams.postValue(dataBase.getAllTeams())
            }
        }
    }


    override suspend fun refreshThePlayer(id: Int) {
        selectedPlayerId.postValue(id.toString())
    }

    override suspend fun refreshTheTeam(teamID: Int) {
        //load old data
        ////        _theTeam.postValue(dataBase.getTheTeam(teamID))
        //        //refresh
        if (isItTimeToUpdate(NEWS_PREF_KEY + teamID, UpdateTime.EVENT.timeBeforeUpdate)) {
            refreshTeamNews(teamID)
        }
        if (isItTimeToUpdate(PLAYER_PREF_KEY + teamID, UpdateTime.PLATER.timeBeforeUpdate)) {
            refreshTeamPlayer(teamID)
        }
        //update with new data
        _theTeam.postValue(dataBase.getTheTeam(teamID))
    }

    private suspend fun refreshTeamPlayer(teamID: Int) {
        Timber.e("teamIDy $teamID")
        var theTeam: Team? = dataBase.getTheTeam(teamID)
        val playersResponse = nbaApiService.getAllPlayers(theTeam!!.teamName)
        if (playersResponse.isSuccessful) {
            val players = playersResponse.body()!!.player.map { it.getPresentationModel() }

            players.forEach {
                dataBase.putPlayer(it, teamID)
            }
            updateTimePreferences(PLAYER_PREF_KEY, teamID)
        } else {
            _didApiCallFail.postValue(Event(true))
        }
    }

    private suspend fun refreshTeamNews(teamId: Int) {
        val newsResponse = nbaApiService.getAllNews(teamId.toString())
        if (newsResponse.isSuccessful) {
            val news = newsResponse.body()!!.results.map { it.getPresentationModel() }

            news.forEach {
                dataBase.putNews(it, teamId)
            }

            updateTimePreferences(NEWS_PREF_KEY, teamId)


        } else {
            _didApiCallFail.postValue(Event(true))
        }
    }


    private suspend fun updateTeamsViaApi() {
        val teamsResponse = nbaApiService.getAllTeams(LEAGUE_KEY)
        if (teamsResponse.isSuccessful) {
            var teams: List<Team> = teamsResponse.body()!!.teams.map {
                it.getPresentationModel()
            }
//            _teams.postValue(teams)
            teams.forEach {
                dataBase.putTeam(it)
            }

            _teams.postValue(dataBase.getAllTeams())

            val currentTime = Date(System.currentTimeMillis()).time
            sharedPref.put(TEAM_PREF_KEY, currentTime)

        } else {
            _didApiCallFail.postValue(Event(true))
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


    private fun updateTimePreferences(key: String, teamId: Int) {
        val currentTime = Date(System.currentTimeMillis()).time
        sharedPref.put(key + teamId, currentTime)
    }

}


const val LEAGUE_KEY = "4387"
const val PRIVATE_MODE = 0
const val PREF_NAME = "mindorks-welcome"
const val TEAM_PREF_KEY = "team_update_time"
const val PLAYER_PREF_KEY = "player_update_time"
const val NEWS_PREF_KEY = "news_update_time"
