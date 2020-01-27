package com.example.nbawiki.ui.main.features.team

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nbawiki.model.database.db.asNewsListItem
import com.example.nbawiki.ui.main.features.team.models.NewsListElement
import com.example.nbawiki.repositories.interfaces.api.TeamRepository
import com.example.nbawiki.ui.main.features.team.models.TeamDetails
import com.example.nbawiki.ui.main.features.team.models.asTeamDetailsModel
import com.example.nbawiki.ui.main.features.team.models.PlayerListElement
import com.example.nbawiki.ui.main.features.team.models.asPlayerListItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TeamViewModel(private val teamRepository: TeamRepository) : ViewModel() {

    var team: LiveData<TeamDetails> = Transformations.map(teamRepository.selectedTeam) {
        it.asTeamDetailsModel()
    }

    var newsListElement : LiveData<List<NewsListElement>>  = Transformations.map(teamRepository.news) {
        it.asNewsListItem()
    }

    var players : LiveData<List<PlayerListElement>>  = Transformations.map(teamRepository.players) {
        it.asPlayerListItem()
    }

    val didApiCallFail = teamRepository.didApiCallFail

    fun initializeTeamData(teamId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            teamRepository.getTheTeam(teamId)
        }
    }
}

