package com.example.nbawiki.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData

import com.example.nbawiki.model.database.dao.TeamsDao
import com.example.nbawiki.model.database.db.TeamDb
import com.example.nbawiki.model.dto.teams.TeamDTO
import com.example.nbawiki.model.dto.teams.asDBModel
import com.example.nbawiki.repositories.interfaces.api.TeamListRepository
import com.example.nbawiki.datasource.retrofit.WebService
import com.example.nbawiki.util.*
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class TeamsRepo @Inject constructor(
    private val nbaApiService: WebService,
    private val wizard: TimePreferenceWizard,
    private val dataBase: TeamsDao
) : TeamListRepository {

    override suspend fun getTeamsWithResponse(): LiveData<Status<List<TeamDb?>>> =
        liveData(Dispatchers.IO) {
            emit(Status.CachedData(dataBase.getAllTeams())) //preLoad data

            when (isItTimeToUpdate()) {
                true -> {
                    emit(Status.Loading())
                    val teams = returnApiCallResult()
                    emit(teams)
                    if (teams is Status.Success) {
                        changeUpdateTimeToNow()
                    }
                }
            }
        }

    private fun isItTimeToUpdate(): Boolean {
        return wizard.isItTimeToUpdate(TEAM_PREF_KEY, UpdateTime.TEAM.timeBeforeUpdate)
    }

    private fun changeUpdateTimeToNow() {
        wizard.updateTimePreferences(TEAM_PREF_KEY)
    }

    private suspend fun returnApiCallResult(): Status<List<TeamDb?>> {
        return safeApiCall {
            val req = nbaApiService.getAllTeams(LEAGUE_KEY)
            if (req.isSuccessful) {
                updateDatabaseWithDto(req.body()!!.teams)
                Status.Success(dataBase.getAllTeams())
            } else {
                Status.Error(req.message(), dataBase.getAllTeams())
            }
        }
    }

    private fun updateDatabaseWithDto(teams: List<TeamDTO>) {
        teams.map {
            it.asDBModel()
        }.forEach { dataBase.insertAll(it) }
    }
}


