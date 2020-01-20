package com.example.nbawiki

import android.app.Application
import android.content.Context
import com.example.nbawiki.network.network.LocalDataSource
import com.example.nbawiki.network.network.Repository
import com.example.nbawiki.network.network.TeamRepository
import com.example.nbawiki.network.retrofit.Network


class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        repository = TeamRepository(Network.network, this, LocalDataSource(this))
    }

    companion object {
        lateinit var repository : Repository
    }
}