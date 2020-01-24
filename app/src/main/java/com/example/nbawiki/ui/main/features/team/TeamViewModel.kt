package com.example.nbawiki.ui.main.features.team

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nbawiki.model.database.asPresentationModels
import com.example.nbawiki.model.presentation.News
import com.example.nbawiki.model.presentation.Team
import com.example.nbawiki.network.network.repointerfaces.api.TeamRepository
import com.example.nbawiki.ui.main.features.team.tabs.players.PlayerListElement
import com.example.nbawiki.ui.main.features.team.tabs.players.asPlayerList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TeamViewModel(private val teamRepository: TeamRepository) : ViewModel() {

    var team: LiveData<Team> = teamRepository.selectedTeam

    var news : LiveData<List<News>>  = Transformations.map(teamRepository.news) {
        it.asPresentationModels()
    }

    var players : LiveData<List<PlayerListElement>>  = Transformations.map(teamRepository.players) {
        it.asPlayerList()
    }

    val didApiCallFail = teamRepository.didApiCallFail

    fun initializeTeamData(teamId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            teamRepository.getTheTeam(teamId)
        }
    }
}

//
//data class PlayerListElement(
//    val id: Int,
//    val name :String = "",
//    val icon : String?
//)
