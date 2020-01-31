package com.example.nbawiki.ui.main.features.teamslist

import androidx.lifecycle.*
import com.example.nbawiki.model.database.db.TeamDb
import com.example.nbawiki.repositories.TeamsRepo
import com.example.nbawiki.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class MainViewModel @Inject constructor(teamsRepository : TeamsRepo) : ViewModel() {

    private val _teams : LiveData<Resource<List<TeamDb?>>> = liveData(Dispatchers.IO) {
            val teamsEntity = teamsRepository.getTeamsWithResponse()
            emitSource(teamsEntity)
        }
    val teams = Transformations.map(_teams){
        when(it) {
            is Resource.Success -> Resource.Success(it.data?.asPresentationModel())
            is Resource.CachedData -> Resource.CachedData(it.data?.asPresentationModel())
            is Resource.Loading -> Resource.Loading()
            is Resource.Error -> Resource.Error(it.message ?: "Unknown issue")
        }
    }
}
// fun <T, P, Y  : Resource<T>> mapResponse(
//     resource: Y,
//     mapingFunction: () -> P
// ): Resource<P> {
//    val response =  mapingFunction()
//     resource.data = response
//     return resource
//}

//suspend fun <T> safeApiCall(responseFunction: suspend () -> Resource<T>): Resource<T> {
//    return try {
//        val response = withContext(Dispatchers.IO) { responseFunction() }
//        response
//    } catch (e: Exception) {
//        Resource.Error(e.message.toString())
//    }
//}