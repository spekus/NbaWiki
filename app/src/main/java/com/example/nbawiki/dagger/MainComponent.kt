package com.example.nbawiki.dagger

import com.example.nbawiki.dagger.modules.ContextModule
import com.example.nbawiki.dagger.modules.DataBaseModule
import com.example.nbawiki.dagger.modules.RepositoryModule
import com.example.nbawiki.dagger.modules.NetworkModule
import com.example.nbawiki.ui.main.features.player.PlayerFragment
import com.example.nbawiki.ui.main.features.team.TeamFragment
import com.example.nbawiki.ui.main.features.team.tabs.news.NewsListFragment
import com.example.nbawiki.ui.main.features.team.tabs.players.PlayerListFragment
import com.example.nbawiki.ui.main.features.teamslist.MainFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    RepositoryModule::class,
    NetworkModule::class,
    ContextModule::class,
    CustomViewModelFactoryModule::class,
    CustomViewModelModule::class,
    DataBaseModule::class
])

interface MainComponent {
    fun inject(playerFragment: PlayerFragment)
    fun inject(mainFragment: MainFragment)
    fun inject(teamFragment :TeamFragment)
    fun inject(newsListFragment: NewsListFragment)
    fun inject(playerListFragment: PlayerListFragment)
}