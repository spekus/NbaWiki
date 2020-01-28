package com.example.nbawiki.ui.main.features.team.tabs.players

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.nbawiki.repositories.interfaces.api.TeamRepository
import com.example.nbawiki.ui.main.features.team.models.PlayerListElement
import com.example.nbawiki.ui.main.features.team.models.asPlayerListItem
import javax.inject.Inject

class PlayerListViewModel @Inject constructor(teamRepository: TeamRepository) : ViewModel() {

    var players : LiveData<List<PlayerListElement>> =
        Transformations.map(teamRepository.players) {
        it.asPlayerListItem()
    }
}