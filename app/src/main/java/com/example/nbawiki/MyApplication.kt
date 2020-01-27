package com.example.nbawiki

import android.app.Application
import com.example.nbawiki.dagger.PocketTreasureComponent
import com.example.nbawiki.datasource.database.getDatabase
import com.example.nbawiki.repositories.PlayerRepo
import com.example.nbawiki.repositories.TeamRepo
import com.example.nbawiki.repositories.TeamsRepo
import com.example.nbawiki.repositories.interfaces.PlayerRepository
import com.example.nbawiki.datasource.retrofit.Network
import com.example.nbawiki.util.TimePreferenceWizard
import dagger.Component

@Component
interface ApplicationComponent

class MyApplication : Application()  {

    private lateinit var pocketTreasureComponent : PocketTreasureComponent
    fun getDaggerComponent() = pocketTreasureComponent

//    val appComponent = DaggerApplicationComponent.create()

    override fun onCreate() {
        super.onCreate()

        pocketTreasureComponent = PocketTreasureComponent

        // manual injection for repositories
        val timePreferenceWizard = TimePreferenceWizard(this)
        val teamDao = getDatabase(this).teamDao()
        val newsDao = getDatabase(this).newsDao()
        val playerDao = getDatabase(this).playerDao()

        teamRepository = TeamRepo(Network.network, timePreferenceWizard, teamDao, playerDao, newsDao)
        teamsRepository = TeamsRepo(Network.network, timePreferenceWizard, teamDao)
        playerRepository = PlayerRepo(playerDao)
    }

    companion object {
        lateinit var teamRepository: TeamRepo
        lateinit var teamsRepository: TeamsRepo
        lateinit var playerRepository: PlayerRepository
    }
}