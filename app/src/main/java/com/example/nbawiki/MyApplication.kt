package com.example.nbawiki

import android.app.Application
import com.example.nbawiki.dagger.DaggerMainComponent
import com.example.nbawiki.dagger.MainComponent
import com.example.nbawiki.dagger.modules.ContextModule
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

//    private lateinit var pocketTreasureComponent : PocketTreasureComponent
//    fun getDaggerComponent() = pocketTreasureComponent

//    val appComponent = DaggerApplicationComponent.create()

    lateinit var component: MainComponent
        private set

    override fun onCreate() {
        super.onCreate()

        INSTANCE = this
//        component = DaggerNamesFragmentComponent.builder()
//            .namesFragmentModule(NamesFragmentModule(this))
//            .build()

        component = DaggerMainComponent.builder()
            .contextModule(ContextModule(this))
            .build()

//        val contextModule = ContextModule(this)
//
//        pocketTreasureComponent = PocketTreasureComponent

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

        private var INSTANCE: MyApplication? = null
        @JvmStatic
        fun get(): MyApplication = INSTANCE!!
    }
//
//
//    class Injector private constructor() {
//        companion object {
//            fun get() : NamesFragmentComponent =
//                MyApplication.get().component
//        }
//    }
}