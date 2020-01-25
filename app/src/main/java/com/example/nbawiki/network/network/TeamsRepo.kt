package com.example.nbawiki.network.network

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nbawiki.model.database.dao.TeamsDao
import com.example.nbawiki.model.database.db.TeamDb
import com.example.nbawiki.model.dto.teams.TeamDTO
import com.example.nbawiki.model.dto.teams.asDBModel
import com.example.nbawiki.network.network.repointerfaces.api.TeamListRepository
import com.example.nbawiki.network.retrofit.WebService
import com.example.nbawiki.ui.main.util.Event
import com.example.nbawiki.ui.main.util.TEAM_PREF_KEY
import com.example.nbawiki.ui.main.util.TimePreferenceWizard
import com.example.nbawiki.ui.main.util.UpdateTime

class TeamsRepo(
    private val nbaApiService: WebService,
    private val context: Context,
    private val dataBase: TeamsDao
) : TeamListRepository {


    private val wizard = TimePreferenceWizard(context)

    private val _didApiCallFail = MutableLiveData<Event<Boolean>>()

    override val didApiCallFail: LiveData<Event<Boolean>>
        get() = _didApiCallFail

    private var _teams: MutableLiveData<List<TeamDb>> = MutableLiveData()

    override val allTeams: LiveData<List<TeamDb>>
        get() = _teams


    override suspend fun getTeams() {
        if (wizard.isItTimeToUpdate(TEAM_PREF_KEY, UpdateTime.TEAM.timeBeforeUpdate)) {
            refreshTeams()
        }
        _teams.postValue(dataBase.getAllTeams() )
    }

    private suspend fun refreshTeams() {
        val teamsResponse = nbaApiService.getAllTeams(LEAGUE_KEY)
        when (teamsResponse.isSuccessful) {
            true -> {
                updateDatabase(teamsResponse.body()!!.teams)
                wizard.updateTimePreferences(TEAM_PREF_KEY)
            }

            else -> _didApiCallFail.postValue(Event(true))
        }
    }

    private fun updateDatabase(teams: List<TeamDTO>) {
        teams.map {
            it.asDBModel()
        }
            .forEach { dataBase.insertAll(it) }
    }
}