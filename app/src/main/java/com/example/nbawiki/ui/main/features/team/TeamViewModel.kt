package com.example.nbawiki.ui.main.features.team

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nbawiki.model.database.db.asNewsListItem
import com.example.nbawiki.repositories.TeamRepo
import com.example.nbawiki.ui.main.features.team.models.NewsListElement
import com.example.nbawiki.repositories.interfaces.api.TeamRepository
import com.example.nbawiki.ui.main.features.team.models.TeamDetails
import com.example.nbawiki.ui.main.features.team.models.asTeamDetailsModel
import com.example.nbawiki.ui.main.features.team.models.PlayerListElement
import com.example.nbawiki.ui.main.features.team.models.asPlayerListItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

class TeamViewModel @Inject constructor(
    private val teamRepository: TeamRepository,
    @Named("TeamId")
    private val teamId : Int) : ViewModel() {
    val team: LiveData<TeamDetails> = Transformations.map(teamRepository.selectedTeam) {
        it.asTeamDetailsModel()
    }

    val didApiCallFail = teamRepository.didApiCallFail

    init {
        viewModelScope.launch(Dispatchers.IO) {
            teamRepository.getTheTeam(teamId)
        }
    }

//    fun initializeTeamData(teamId: Int) {
//        viewModelScope.launch(Dispatchers.IO) {
//            teamRepository.getTheTeam(teamId)
//        }
//    }
}

