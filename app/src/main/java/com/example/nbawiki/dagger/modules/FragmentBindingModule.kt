package com.example.nbawiki.dagger.modules

import com.example.nbawiki.ui.main.features.player.PlayerFragment
import com.example.nbawiki.ui.main.features.player.PlayerModule
import com.example.nbawiki.ui.main.features.team.TeamFragment
import com.example.nbawiki.ui.main.features.team.tabs.news.NewsListFragment
import com.example.nbawiki.ui.main.features.team.tabs.players.PlayerListFragment
import com.example.nbawiki.ui.main.features.teamslist.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBindingModule {
    @ContributesAndroidInjector
    abstract fun contributeCatFragment(): MainFragment

    @ContributesAndroidInjector(modules = [PlayerModule::class])
    abstract fun contributePlayerFragment(): PlayerFragment

    @ContributesAndroidInjector
    abstract fun contributePlayerListFragment(): PlayerListFragment

    @ContributesAndroidInjector
    abstract fun contributeNewsListFragment(): NewsListFragment

    @ContributesAndroidInjector
    abstract fun contributeTeamFragment(): TeamFragment
}