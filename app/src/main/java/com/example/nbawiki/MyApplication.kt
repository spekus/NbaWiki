package com.example.nbawiki

import android.app.Application
import com.example.nbawiki.database.LocalDataSource
import com.example.nbawiki.network.network.PlayerRepo
import com.example.nbawiki.network.network.repointerfaces.PlayerRepository
import com.example.nbawiki.network.network.TeamRepo
import com.example.nbawiki.network.network.TeamsRepo
import com.example.nbawiki.network.retrofit.Network


class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        teamRepository = TeamRepo(Network.network, this, LocalDataSource(this))
        teamsRepository = TeamsRepo(Network.network, this, LocalDataSource(this))
        playerRepository = PlayerRepo(LocalDataSource(this))
    }

    companion object {
        lateinit var teamRepository : TeamRepo
        lateinit var teamsRepository : TeamsRepo
        lateinit var playerRepository : PlayerRepository
    }
}