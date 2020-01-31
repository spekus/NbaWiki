package com.example.nbawiki.ui.main.features.team.tabs.players

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.nbawiki.repositories.interfaces.api.TeamRepository
import com.example.nbawiki.ui.main.features.team.models.PlayerListElement
import com.example.nbawiki.ui.main.features.team.models.asPlayerListItem
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Named

class PlayerListViewModel @Inject constructor(
    teamRepository: TeamRepository,
    @Named("TeamId")
    private val teamId: Int
) : ViewModel() {

    var players: LiveData<List<PlayerListElement>> =
        Transformations.map(teamRepository.players) {
            it.asPlayerListItem()
        }
}