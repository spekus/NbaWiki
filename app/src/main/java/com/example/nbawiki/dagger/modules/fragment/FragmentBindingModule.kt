package com.example.nbawiki.dagger.modules.fragment

import com.example.nbawiki.ui.main.features.player.PlayerFragment
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

    @ContributesAndroidInjector(modules = [TeamModule::class, TeamIdModule::class])
    abstract fun contributeTeamFragment(): TeamFragment
}