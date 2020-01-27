package com.example.nbawiki.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nbawiki.model.database.dao.TeamsDao
import com.example.nbawiki.model.database.db.TeamDb
import com.example.nbawiki.model.dto.teams.TeamDTO
import com.example.nbawiki.model.dto.teams.asDBModel
import com.example.nbawiki.repositories.interfaces.api.TeamListRepository
import com.example.nbawiki.datasource.retrofit.WebService
import com.example.nbawiki.util.Event
import com.example.nbawiki.util.TEAM_PREF_KEY
import com.example.nbawiki.util.TimePreferenceWizard
import com.example.nbawiki.util.UpdateTime
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

class TeamsRepo @Inject constructor (
    private val nbaApiService: WebService,
    private val wizard: TimePreferenceWizard,
    private val dataBase: TeamsDao
) : TeamListRepository {

    private val _didApiCallFail = MutableLiveData<Event<Boolean>>()

    override val didApiCallFail: LiveData<Event<Boolean>>
        get() = _didApiCallFail

    private var _teams: MutableLiveData<List<TeamDb?>> = MutableLiveData()

    override val allTeams: LiveData<List<TeamDb?>>
        get() = _teams


    override suspend fun getTeams() {
        // pre load data
        _teams.postValue(dataBase.getAllTeams())

        if (wizard.isItTimeToUpdate(TEAM_PREF_KEY, UpdateTime.TEAM.timeBeforeUpdate)) {
            refreshTeams()
        }
    }

    private suspend fun refreshTeams() {
        try {
            val teamsResponse = nbaApiService.getAllTeams(LEAGUE_KEY)
            when (teamsResponse.isSuccessful) {
                true -> {
                    updateDatabase(teamsResponse.body()!!.teams)
                    wizard.updateTimePreferences(TEAM_PREF_KEY)
                    _teams.postValue(dataBase.getAllTeams())
                    _didApiCallFail.postValue(Event(false))
                }

                else -> _didApiCallFail.postValue(Event(true))
            }
        } catch (exception: Exception) {
            Timber.e(exception)
            _didApiCallFail.postValue(Event(true))
        }
    }

    private fun updateDatabase(teams: List<TeamDTO>) {
        teams.map {
            it.asDBModel()
        }.forEach { dataBase.insertAll(it) }
    }
}