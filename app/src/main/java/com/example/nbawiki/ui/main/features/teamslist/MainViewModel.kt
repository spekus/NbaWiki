package com.example.nbawiki.ui.main.features.teamslist

import androidx.lifecycle.*
import com.example.nbawiki.model.database.db.TeamDb
import com.example.nbawiki.repositories.TeamsRepo
import com.example.nbawiki.util.Status
import com.example.nbawiki.util.wrapWithNewStatusInstance
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class MainViewModel @Inject constructor(teamsRepository : TeamsRepo) : ViewModel() {

    private val _teams : LiveData<Status<List<TeamDb?>>> = liveData(Dispatchers.IO) {
            val teamsEntity = teamsRepository.getTeamsWithResponse()
            emitSource(teamsEntity)
        }
    val teams : LiveData<Status<List<TeamCard>?>> = Transformations.map(_teams){
        wrapWithNewStatusInstance(it) {it.data?.asPresentationModel()}
    }


}