package com.example.nbawiki.ui.main.features.team.tabs.players

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.nbawiki.model.database.db.PlayerDb
import com.example.nbawiki.repositories.interfaces.api.TeamRepository
import com.example.nbawiki.ui.main.features.team.models.PlayerListElement
import com.example.nbawiki.ui.main.features.team.models.asPlayerListItem
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Named

class PlayerListViewModel @Inject constructor(
    val teamRepository: TeamRepository,
    @Named("TeamId")
    private val teamId: Int
) : ViewModel() {

    val _players : LiveData<List<PlayerDb>> =  liveData(Dispatchers.IO) {
        val playerEntitities = teamRepository.getPlayers(teamId)
        emitSource(playerEntitities)
    }

    var players: LiveData<List<PlayerListElement>> =
        Transformations.map(_players) {
            it.asPlayerListItem()
        }
}