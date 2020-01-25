package com.example.nbawiki.datasource.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nbawiki.model.database.dao.NewDao
import com.example.nbawiki.model.database.dao.PlayerDao
import com.example.nbawiki.model.database.dao.TeamsDao
import com.example.nbawiki.model.database.db.NewsDb
import com.example.nbawiki.model.database.db.PlayerDb
import com.example.nbawiki.model.database.db.TeamDb
import com.example.nbawiki.model.dto.news.asDatabaseObject
import com.example.nbawiki.model.dto.players.asDataBaseObject
import com.example.nbawiki.datasource.repositories.interfaces.api.TeamRepository
import com.example.nbawiki.datasource.retrofit.WebService
import com.example.nbawiki.util.*

class TeamRepo(
    private val nbaApiService: WebService,
    private val wizard: TimePreferenceWizard,
    private val teamDao: TeamsDao,
    private val playerDao: PlayerDao,
    private val newsDao: NewDao
) : TeamRepository {

    private val _didApiCallFail = MutableLiveData<Event<Boolean>>()

    override val didApiCallFail: LiveData<Event<Boolean>>
        get() = _didApiCallFail

    private var _selectedTeam: MutableLiveData<TeamDb> = MutableLiveData()

    override val selectedTeam: LiveData<TeamDb>
        get() = _selectedTeam

    private var _players: MutableLiveData<List<PlayerDb>> = MutableLiveData()

    override val players: LiveData<List<PlayerDb>>
        get() = _players

    private var _news: MutableLiveData<List<NewsDb>> = MutableLiveData()

    override val news: LiveData<List<NewsDb>>
        get() = _news


    override suspend fun getTheTeam(teamID: Int) {
        //pre-load old data from db
        getCachedData(teamID)


        val shouldNewsBeUpdated =
            wizard.isItTimeToUpdate(NEWS_PREF_KEY + teamID, UpdateTime.EVENT.timeBeforeUpdate)
        if (shouldNewsBeUpdated) {
            refreshTeamNewsInDataBase(teamID)
            refreshNewsLiveData(teamID)
        }


        val shouldPlayersBeUpdated =
            wizard.isItTimeToUpdate(PLAYER_PREF_KEY + teamID, UpdateTime.PLAYER.timeBeforeUpdate)
        if (shouldPlayersBeUpdated) {
            refreshTeamPlayerInDataBase(teamID)
            refreshPlayersLiveData(teamID)
        }
    }

    private fun getCachedData(teamId: Int) {
        // refreshes live data with data from database
        refreshNewsLiveData(teamId)
        refreshPlayersLiveData(teamId)
        refreshTeamLiveData(teamId)
    }

    private fun refreshNewsLiveData(teamId: Int) {
        val news = newsDao.getNewsByTeam(teamId)
        _news.postValue(news)
    }

    private fun refreshPlayersLiveData(teamId: Int) {
        val allTeamPlayers = playerDao.getPlayersByTeam(teamId)
        _players.postValue(allTeamPlayers)
    }

    private fun refreshTeamLiveData(teamId: Int) {
        _selectedTeam.postValue(teamDao.getByID(teamId))
    }

    private suspend fun refreshTeamPlayerInDataBase(teamID: Int) {
        var teamName: String = teamDao.getNameByID(teamID)
        val playersAPIResponse = nbaApiService.getAllPlayers(teamName)
        when (playersAPIResponse.isSuccessful) {
            true -> {
                playersAPIResponse.body()!!.player.map {
                    it.asDataBaseObject(teamID)
                }.forEach {
                    playerDao.insertAll(it)
                }
                wizard.updateTimePreferences(PLAYER_PREF_KEY, teamID)
            }
            else -> _didApiCallFail.postValue(Event(true))
        }
    }

    private suspend fun refreshTeamNewsInDataBase(teamId: Int) {
        val newsAPIResponse = nbaApiService.getAllNews(teamId.toString())
        when (newsAPIResponse.isSuccessful) {
            true -> {
                newsAPIResponse.body()!!.results.map {
                    it.asDatabaseObject(teamId)
                }.forEach {
                    newsDao.insertAll(it)
                }
                wizard.updateTimePreferences(NEWS_PREF_KEY, teamId)
            }
            else -> _didApiCallFail.postValue(Event(true))
        }
    }
}

const val LEAGUE_KEY = "4387"
