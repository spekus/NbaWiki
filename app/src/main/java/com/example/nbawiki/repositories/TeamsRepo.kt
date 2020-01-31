package com.example.nbawiki.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData

import com.example.nbawiki.model.database.dao.TeamsDao
import com.example.nbawiki.model.database.db.TeamDb
import com.example.nbawiki.model.dto.teams.TeamDTO
import com.example.nbawiki.model.dto.teams.asDBModel
import com.example.nbawiki.repositories.interfaces.api.TeamListRepository
import com.example.nbawiki.datasource.retrofit.WebService
import com.example.nbawiki.util.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class TeamsRepo @Inject constructor(
    private val nbaApiService: WebService,
    private val wizard: TimePreferenceWizard,
    private val dataBase: TeamsDao
) : TeamListRepository {

    override suspend fun getTeamsWithResponse(): LiveData<Resource<List<TeamDb?>>> =
        liveData(Dispatchers.IO) {
            emit(Resource.CachedData(dataBase.getAllTeams())) //preLoad data

            when (isItTimeToUpdate()) {
                false -> emit(Resource.Success(dataBase.getAllTeams()))
                true -> {
                    emit(Resource.Loading())
                    val teams = returnApiCallResult()
                    emit(returnApiCallResult())
                    if (teams is Resource.Success) {
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

    private suspend fun returnApiCallResult(): Resource<List<TeamDb?>> {
        return safeApiCall {
            val req = nbaApiService.getAllTeams(LEAGUE_KEY)
            if (req.isSuccessful) {
                updateDatabase(req.body()!!.teams)
                Resource.Success(dataBase.getAllTeams())
            } else {
                Resource.Error(req.message(), dataBase.getAllTeams())
            }
        }
    }

    private fun updateDatabase(teams: List<TeamDTO>) {
        teams.map {
            it.asDBModel()
        }.forEach { dataBase.insertAll(it) }
    }
}

suspend fun <T> safeApiCall(responseFunction: suspend () -> Resource<T>): Resource<T> {
    return try {
        val response = withContext(Dispatchers.IO) { responseFunction() }
        response
    } catch (e: Exception) {
        Resource.Error(e.message.toString())
    }
}
