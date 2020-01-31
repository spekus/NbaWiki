package com.example.nbawiki.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.nbawiki.model.database.dao.NewDao
import com.example.nbawiki.model.database.dao.PlayerDao
import com.example.nbawiki.model.database.dao.TeamsDao
import com.example.nbawiki.model.database.db.NewsDb
import com.example.nbawiki.model.database.db.PlayerDb
import com.example.nbawiki.model.database.db.TeamDb
import com.example.nbawiki.model.dto.news.asDatabaseObject
import com.example.nbawiki.model.dto.players.asDataBaseObject
import com.example.nbawiki.repositories.interfaces.api.TeamRepository
import com.example.nbawiki.datasource.retrofit.WebService
import com.example.nbawiki.model.dto.news.NewsListDTO
import com.example.nbawiki.model.dto.players.PlayersListDTO
import com.example.nbawiki.util.*
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class TeamRepo @Inject constructor(
    private val nbaApiService: WebService,
    private val wizard: TimePreferenceWizard,
    private val teamDao: TeamsDao,
    private val playerDao: PlayerDao,
    private val newsDao: NewDao
) : TeamRepository {

    override suspend fun getTheTeam(teamId: Int): LiveData<TeamDb> {
        return teamDao.getByID(teamId)
    }
    //pre-load old data from db to live data
//        loadCachedData(teamID)
//
//        val shouldNewsBeUpdated =
//            wizard.isItTimeToUpdate(NEWS_PREF_KEY + teamID, UpdateTime.EVENT.timeBeforeUpdate)
//        if (shouldNewsBeUpdated) {
//            refreshTeamNewsInDataBase(teamID)
//            refreshNewsLiveData(teamID)
//        }
//
//
//        val shouldPlayersBeUpdated =
//            wizard.isItTimeToUpdate(PLAYER_PREF_KEY + teamID, UpdateTime.PLAYER.timeBeforeUpdate)
//        if (shouldPlayersBeUpdated) {
//            refreshTeamPlayerInDataBase(teamID)
//            refreshPlayersLiveData(teamID)
//        }


    override suspend fun getPlayers(teamId: Int): LiveData<Status<List<PlayerDb>>> {
        return liveData(Dispatchers.IO) {
            val players = playerDao.getPlayersByTeam(teamId)
            emit(Status.CachedData(players))

            when (shouldPlayersBeUpdated(teamId)) {
                true ->{
                    emit(Status.Loading())
                    emit(
                        getPlayerApiCallResult(teamId)
                    )

                }
                false -> emit(Status.Success(players))
            }
        }
    }

    private suspend fun getPlayerApiCallResult(teamId: Int): Status<List<PlayerDb>> {
        return safeApiCall {
            val teamName: String = teamDao.getNameByID(teamId)
            val response = nbaApiService.getAllPlayers(teamName)
            if (response.isSuccessful) {
                updateDatabasePlayerWith(response.body(), teamId)
                wizard.updateTimePreferences(PLAYER_PREF_KEY, teamId)
                Status.Success(playerDao.getPlayersByTeam(teamId))
            }else{
                Status.Error(response.message(), playerDao.getPlayersByTeam(teamId))
            }
        }
    }

    private fun updateDatabasePlayerWith(body: PlayersListDTO?, teamId: Int) {
        body!!.player.map {
            it.asDataBaseObject(teamId)
        }.forEach {
            playerDao.insertAll(it)
        }
    }

    private fun shouldPlayersBeUpdated(teamID: Int): Boolean {
        return wizard.isItTimeToUpdate(PLAYER_PREF_KEY + teamID, UpdateTime.PLAYER.timeBeforeUpdate)
    }

    override suspend fun getNews(teamId: Int): LiveData<Status<List<NewsDb>>> {
        return liveData(Dispatchers.IO) {
            val news = newsDao.getNewsByTeam(teamId)
            emit(Status.CachedData(news))
            when (shouldNewsBeUpdated(teamId)) {
                true -> {
                    emit(Status.Loading())
                    emit(getNewsApiCallResult(teamId))
                }
                false -> emit(Status.Success(news))
            }
        }
    }

    private suspend fun getNewsApiCallResult(teamId: Int): Status<List<NewsDb>> {
        return safeApiCall {
            val response = nbaApiService.getAllNews(teamId.toString())
            if (response.isSuccessful) {
                updateDatabaseWith(response.body(), teamId)
                wizard.updateTimePreferences(NEWS_PREF_KEY, teamId)
                Status.Success(newsDao.getNewsByTeam(teamId))
            }else{
                Status.Error(response.message(), newsDao.getNewsByTeam(teamId))
            }
        }
    }

    private fun shouldNewsBeUpdated(teamID: Any): Boolean {
        return wizard.isItTimeToUpdate(NEWS_PREF_KEY + teamID, UpdateTime.EVENT.timeBeforeUpdate)
    }


    private fun updateDatabaseWith(body: NewsListDTO?, teamId: Int) {
        body!!.results.map {
            it.asDatabaseObject(teamId)
        }.forEach {
            newsDao.insertAll(it)
        }
    }

//    private fun loadCachedData(teamId: Int) {
//        // refreshes live data with data from database
//        refreshNewsLiveData(teamId)
//        refreshPlayersLiveData(teamId)
//        refreshTeamLiveData(teamId)
//    }
//
//    private fun refreshNewsLiveData(teamId: Int) {
//        val news = newsDao.getNewsByTeam(teamId)
//        _news.postValue(news)
//    }
//
//    private fun refreshPlayersLiveData(teamId: Int) {
//        val allTeamPlayers = playerDao.getPlayersByTeam(teamId)
//        _players.postValue(allTeamPlayers)
//    }
//
//    private fun refreshTeamLiveData(teamId: Int) {
//        _selectedTeam.postValue(teamDao.getByID(teamId))
//    }

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
//            else -> _didApiCallFail.postValue(Event(true))
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
//            else -> _didApiCallFail.postValue(Event(true))
        }
    }
}

const val LEAGUE_KEY = "4387"
