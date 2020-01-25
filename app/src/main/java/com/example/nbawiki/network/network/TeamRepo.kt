package com.example.nbawiki.network.network

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nbawiki.database.LocalDataSource
import com.example.nbawiki.model.database.db.NewsDb
import com.example.nbawiki.model.database.db.PlayerDb
import com.example.nbawiki.model.database.db.TeamDb
import com.example.nbawiki.model.dto.news.asDatabaseObject
import com.example.nbawiki.model.dto.players.asDataBaseObject
import com.example.nbawiki.network.network.repointerfaces.api.TeamRepository
import com.example.nbawiki.network.retrofit.WebService
import com.example.nbawiki.ui.main.util.*

class TeamRepo(
    private val nbaApiService: WebService,
    private val context: Context,
    private val dataBase: LocalDataSource
) : TeamRepository {
    private val teamDao = dataBase.getDatabase(context).teamDao()
    private val playerDao = dataBase.getDatabase(context).playerDao()
    private val newsDao = dataBase.getDatabase(context).newsDao()

    private val wizard = TimePreferenceWizard(context)

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
        //load old data
//        _selectedTeam.postValue(dataBase.getTheTeam(teamID))

        //refresh
//        val shouldNewsBeUpdated =
//            wizard.isItTimeToUpdate(NEWS_PREF_KEY + teamID, UpdateTime.EVENT.timeBeforeUpdate)
        val shouldPlayersBeUpdated =
            wizard.isItTimeToUpdate(PLAYER_PREF_KEY + teamID, UpdateTime.PLAYER.timeBeforeUpdate)
//        if (shouldNewsBeUpdated) {
        refreshTeamNews(teamID)
//        }
//        if (shouldPlayersBeUpdated) {
        refreshTeamPlayer(teamID)
//        }

        //update with new data if there was an api call
//        if (shouldNewsBeUpdated || shouldNewsBeUpdated) {
        _selectedTeam.postValue(teamDao.getByID(teamID))
//        }
    }

    private suspend fun refreshTeamPlayer(teamID: Int) {

//        val deamDao  = dataBase.getDatabase(context).teamDao()
        var teamName: String = teamDao.getNameByID(teamID)
        val playersResponse = nbaApiService.getAllPlayers(teamName)
        when (playersResponse.isSuccessful) {
            true -> {
//                updateDatabase(playersResponse.body()!!.player, teamID)

                playersResponse.body()!!.player.map {
                    it.asDataBaseObject(teamID)
                }.forEach {
                    playerDao.insertAll(it)
                }

                val allTeamPlayers = playerDao.getPlayersByTeam(teamID)

                _players.postValue(
                    allTeamPlayers
                )


                wizard.updateTimePreferences(PLAYER_PREF_KEY, teamID)
            }
            else -> _didApiCallFail.postValue(Event(true))
        }
    }

    private suspend fun refreshTeamNews(teamId: Int) {
        val newsResponse = nbaApiService.getAllNews(teamId.toString())
        when (newsResponse.isSuccessful) {
            true -> {
//                updateDatabase(newsResponse.body()!!.results, teamId)
                newsResponse.body()!!.results.map {
                    it.asDatabaseObject(teamId)
                }.forEach {
                    newsDao.insertAll(it)
                }

//                    player.map {
//                    it.asDataBaseObject(teamID) }.forEach {
//                    playerDao.insertAll(it)

//                }
                val news = newsDao.getPlayersByTeam(teamId)
                _news.postValue(news)


                wizard.updateTimePreferences(NEWS_PREF_KEY, teamId)
            }
            else -> _didApiCallFail.postValue(Event(true))
        }
    }

//    private fun updateDatabase(body: List<Dto>, teamID: Int = 0) {
//        when (body.first()) {
//            is PlayerDTO -> body.map { it.getPresentationModel() }.forEach {
////                dataBase.putPlayer(
////                    it as Player,
////                    teamID
////                )
//            }
//            is NewsDTO -> body.map { it.getPresentationModel() }.forEach {
////                dataBase.putNews(
////                    it as News,
////                    teamID
////                )
//            }
//        }
//    }
}

const val LEAGUE_KEY = "4387"
