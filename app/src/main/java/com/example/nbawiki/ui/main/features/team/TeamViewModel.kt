package com.example.nbawiki.ui.main.features.team

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nbawiki.network.network.Repository
import com.example.nbawiki.model.presentation.Team
import com.example.nbawiki.ui.main.util.Constants
import com.example.nbawiki.ui.main.util.Constants.coroutineExceptionHandler
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class TeamViewModel(private val teamRepository : Repository) : ViewModel() {
    
    val team : LiveData<Team> = teamRepository.selectedTeam

    fun initializeTeamData(teamId: Int) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler){
            teamRepository.refreshTheTeam(teamId)
        }
    }
}
