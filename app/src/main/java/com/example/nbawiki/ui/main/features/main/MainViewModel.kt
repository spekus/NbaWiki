package com.example.nbawiki.ui.main.features.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.nbawiki.model.Repository
import com.example.nbawiki.model.Team

class MainViewModel(teamRepository : Repository) : ViewModel() {

    val teams : LiveData<List<Team>> = teamRepository.getTeams()

}
