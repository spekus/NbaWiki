package com.example.nbawiki.dagger

import com.example.nbawiki.dagger.modules.ContextModule
import com.example.nbawiki.dagger.modules.DataBaseModule
import com.example.nbawiki.dagger.modules.NetworkModule
import com.example.nbawiki.repositories.PlayerRepo
import com.example.nbawiki.repositories.interfaces.PlayerRepository
import com.example.nbawiki.ui.main.features.player.PlayerFragment
import dagger.Component
import javax.inject.Singleton

@Singleton //not sure if this needed
@Component(modules = [
    DataBaseModule::class,
    NetworkModule::class,
    ContextModule::class,
    CustomViewModelFactoryModule::class,
    CustomViewModelModule::class
])
//    , dependencies = [PocketTreasureComponent::class])
interface MainComponent {
    fun inject(playerFragment: PlayerFragment)
    fun getRepo() : PlayerRepository
//    fun getAdress() : String
//    fun getContext() : Context
}