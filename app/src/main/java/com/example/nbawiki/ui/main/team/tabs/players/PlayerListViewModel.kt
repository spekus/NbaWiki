package com.example.nbawiki.ui.main.team.tabs.players

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.nbawiki.model.Repository
import com.example.nbawiki.model.Team

class PlayerListViewModel: ViewModel() {

    val teams : LiveData<List<Team>> = Repository.getTeams()

}