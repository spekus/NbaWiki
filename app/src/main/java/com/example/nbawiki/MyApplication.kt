package com.example.nbawiki

import android.app.Application
import com.example.nbawiki.datasource.database.getDatabase
import com.example.nbawiki.datasource.repositories.PlayerRepo
import com.example.nbawiki.datasource.repositories.TeamRepo
import com.example.nbawiki.datasource.repositories.TeamsRepo
import com.example.nbawiki.datasource.repositories.interfaces.PlayerRepository
import com.example.nbawiki.datasource.retrofit.Network
import com.example.nbawiki.util.TimePreferenceWizard


class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // manual injection for repositories
        val timePreferenceWizard = TimePreferenceWizard(this)
        val teamDao = getDatabase(this).teamDao()
        val newsDao = getDatabase(this).newsDao()
        val playerDao = getDatabase(this).playerDao()

        teamRepository = TeamRepo(Network.network, timePreferenceWizard, teamDao, playerDao, newsDao)
        teamsRepository = TeamsRepo(Network.network, timePreferenceWizard, teamDao)
        playerRepository = PlayerRepo(
            getDatabase(
                this
            ).playerDao())
    }

    companion object {
        lateinit var teamRepository: TeamRepo
        lateinit var teamsRepository: TeamsRepo
        lateinit var playerRepository: PlayerRepository
    }
}