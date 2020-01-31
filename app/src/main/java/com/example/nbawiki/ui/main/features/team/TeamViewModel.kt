package com.example.nbawiki.ui.main.features.team

import androidx.lifecycle.*
import com.example.nbawiki.model.database.db.TeamDb
import com.example.nbawiki.repositories.interfaces.api.TeamRepository
import com.example.nbawiki.ui.main.features.team.models.TeamDetails
import com.example.nbawiki.ui.main.features.team.models.asTeamDetailsModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Named

class TeamViewModel @Inject constructor(
    private val teamRepository: TeamRepository,
    @Named("TeamId")
    private val teamId: Int
) : ViewModel() {

    private val _team: LiveData<TeamDb> = liveData(Dispatchers.IO) {
           val  teamDb =teamRepository.getTheTeam(teamId)
            emitSource(teamDb)
    }

    val team: LiveData<TeamDetails> = Transformations.map(_team) {
        it.asTeamDetailsModel()
    }
}

