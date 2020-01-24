package com.example.nbawiki.network.network

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nbawiki.database.LocalDataSource
import com.example.nbawiki.model.database.asPresentationModel
import com.example.nbawiki.model.dto.Dto
import com.example.nbawiki.model.dto.news.NewsDTO
import com.example.nbawiki.model.dto.players.PlayerDTO
import com.example.nbawiki.model.presentation.News
import com.example.nbawiki.model.presentation.Player
import com.example.nbawiki.model.presentation.Team
import com.example.nbawiki.network.network.repointerfaces.api.TeamRepository
import com.example.nbawiki.network.retrofit.WebService
import com.example.nbawiki.ui.main.util.*
import java.util.*

class TeamRepo(
    private val nbaApiService: WebService,
    private val context: Context,
    private val dataBase: LocalDataSource
) : TeamRepository {
    private val teamDao = dataBase.getDatabase(context).teamDao()

    private val wizard = TimePreferenceWizard(context)

    private val _didApiCallFail = MutableLiveData<Event<Boolean>>()

    override val didApiCallFail: LiveData<Event<Boolean>>
        get() = _didApiCallFail

    private var _selectedTeam: MutableLiveData<Team> = MutableLiveData()

    override val selectedTeam: LiveData<Team>
        get() = _selectedTeam

    override suspend fun getTheTeam(teamID: Int) {
        //load old data
//        _selectedTeam.postValue(dataBase.getTheTeam(teamID))

        //refresh
//        val shouldNewsBeUpdated =
//            wizard.isItTimeToUpdate(NEWS_PREF_KEY + teamID, UpdateTime.EVENT.timeBeforeUpdate)
//        val shouldPlayersBeUpdated =
//            wizard.isItTimeToUpdate(PLAYER_PREF_KEY + teamID, UpdateTime.PLAYER.timeBeforeUpdate)
//        if (shouldNewsBeUpdated) {
//            refreshTeamNews(teamID)
//        }
//        if (shouldPlayersBeUpdated) {
//            refreshTeamPlayer(teamID)
//        }

        //update with new data if there was an api call
//        if (shouldNewsBeUpdated || shouldNewsBeUpdated) {
            _selectedTeam.postValue(teamDao.getByID(teamID).asPresentationModel())
//        }
    }

    private suspend fun refreshTeamPlayer(teamID: Int) {
//        val deamDao  = dataBase.getDatabase(context).teamDao()
//        var theTeam: Team? = dataBase.getTheTeam(teamID)
//        val playersResponse = nbaApiService.getAllPlayers(theTeam!!.teamName)
//        when (playersResponse.isSuccessful) {
//            true -> {
//                updateDatabase(playersResponse.body()!!.player, teamID)
//                wizard.updateTimePreferences(PLAYER_PREF_KEY, teamID)
//            }
//            else -> _didApiCallFail.postValue(Event(true))
//        }
    }

    private suspend fun refreshTeamNews(teamId: Int) {
        val newsResponse = nbaApiService.getAllNews(teamId.toString())
        when (newsResponse.isSuccessful) {
            true -> {
                updateDatabase(newsResponse.body()!!.results, teamId)
                wizard.updateTimePreferences(NEWS_PREF_KEY, teamId)
            }

            else -> _didApiCallFail.postValue(Event(true))
        }
    }

    private fun updateDatabase(body: List<Dto>, teamID: Int = 0) {
        when (body.first()) {
            is PlayerDTO -> body.map { it.getPresentationModel() }.forEach {
//                dataBase.putPlayer(
//                    it as Player,
//                    teamID
//                )
            }
            is NewsDTO -> body.map { it.getPresentationModel() }.forEach {
//                dataBase.putNews(
//                    it as News,
//                    teamID
//                )
            }
        }
    }
}

const val LEAGUE_KEY = "4387"
