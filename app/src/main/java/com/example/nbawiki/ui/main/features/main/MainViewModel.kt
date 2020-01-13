package com.example.nbawiki.ui.main.features.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.nbawiki.model.TeamRepository
import com.example.nbawiki.model.Team

class MainViewModel : ViewModel() {

    val teams : LiveData<List<Team>> = TeamRepository.getTeams()

}
