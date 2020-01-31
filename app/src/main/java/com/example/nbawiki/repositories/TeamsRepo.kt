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

    suspend fun getTeamsWithResponse(): LiveData<Resource<List<TeamDb?>>> = liveData(Dispatchers.IO) {
        emit(Resource.Loading(dataBase.getAllTeams())) //preLoad data
//
//        when (isItTimeToUpdate()){
//            false -> emit(Resource.Success(dataBase.getAllTeams()))
//        }

        emit(Resource.Loading(dataBase.getAllTeams())) //preLoad data
        try {
            val req = nbaApiService.getAllTeams(LEAGUE_KEY)
            if (req.isSuccessful) {
                updateDatabase(req.body()!!.teams)
                emit(Resource.Success(dataBase.getAllTeams()))
            }else if (!req.isSuccessful){
                emit(Resource.Error("", dataBase.getAllTeams()))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.toString()))
        }
    }

    fun isItTimeToUpdate() : Boolean{
        return wizard.isItTimeToUpdate(TEAM_PREF_KEY, UpdateTime.TEAM.timeBeforeUpdate)
    }


    override suspend fun getTeams() {
        // pre load data
//        _teams.postValue(dataBase.getAllTeams())
//
//        if (wizard.isItTimeToUpdate(TEAM_PREF_KEY, UpdateTime.TEAM.timeBeforeUpdate)) {
//            refreshTeams()
//        }
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
//
//suspend fun getData2(): LiveData<Resource<List<TeamDb>>> {
//    val result = MutableLiveData<Resource<List<TeamDb>>>()
//
//}
//
//fun getData(): LiveData<Resource<somedata>> {
//    val result = MutableLiveData<Resource<somedata>>()
//    launch {
//        if (isDataOld) {
//            try {
//                val response = remoteService.getData() // getData() is a suspend function returns a Response object
//                if(response.isSuccessful) {
//                    myDao.addData(response.body)
//                    val dataFromDb = myDao.getData() // returns new data
//                    withContext(Dispatchers.Main) { result.value = Resource.success(dataFromDb)}
//                }
//            } catch (e: Exception) {
//                val dataFromDb = myDao.getData() // returns old data but include error message
//                withContext(Dispatchers.Main) { result.value = Resource.error(e.message, dataFromDb) }
//            }
//        }
//    }
//    return result
//}