package com.example.nbawiki.dagger

import com.example.nbawiki.datasource.retrofit.WebService
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface PocketTreasureComponent{

    fun getWebService() : WebService

}