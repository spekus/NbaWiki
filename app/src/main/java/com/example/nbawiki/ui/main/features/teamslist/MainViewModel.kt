package com.example.nbawiki.ui.main.features.teamslist

import androidx.lifecycle.*
import com.example.nbawiki.model.database.db.TeamDb
import com.example.nbawiki.repositories.TeamsRepo
import com.example.nbawiki.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(teamsRepository : TeamsRepo) : ViewModel() {

    private val _teams : LiveData<Resource<List<TeamDb?>>> = liveData(Dispatchers.IO) {
            val teamsEntity = teamsRepository.getTeamsWithResponse()
            emitSource(teamsEntity)
        }
    val teams = Transformations.map(_teams){
        when(it){
            is Resource.Success -> Resource.Success(it.data?.asPresentationModel())
            is Resource.Loading -> Resource.Loading(it.data?.asPresentationModel() ?: emptyList())
            is Resource.Error -> Resource.Error("something bad happenened")
        }
    }

//    val didApiCallFail = teamsRepository.didApiCallFail

    init {
        viewModelScope.launch(Dispatchers.IO){
//            teamsRepository.getTeams()
        }
    }
}
