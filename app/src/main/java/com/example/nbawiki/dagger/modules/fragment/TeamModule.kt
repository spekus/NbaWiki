package com.example.nbawiki.dagger.modules.fragment

import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.example.nbawiki.dagger.ViewModelKey
import com.example.nbawiki.ui.main.features.team.TeamFragment
import com.example.nbawiki.ui.main.features.team.TeamFragmentArgs
import com.example.nbawiki.ui.main.features.team.TeamViewModel
import com.example.nbawiki.ui.main.features.team.tabs.news.NewsListFragment
import com.example.nbawiki.ui.main.features.team.tabs.news.NewsListViewModel
import com.example.nbawiki.ui.main.features.team.tabs.players.PlayerListFragment
import com.example.nbawiki.ui.main.features.team.tabs.players.PlayerListViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import javax.inject.Named

@Module
abstract class TeamModule {
    @Binds
    @IntoMap
    @ViewModelKey(TeamViewModel::class)
    abstract fun bindTeamViewModel(customViewModel: TeamViewModel): ViewModel

    @ContributesAndroidInjector(modules = [PlayerListModule::class])
    abstract fun contributePlayerListFragment(): PlayerListFragment

    @ContributesAndroidInjector(modules = [NewsListModule::class])
    abstract fun contributeNewsListFragment(): NewsListFragment
}

@Module
abstract class PlayerListModule {
    @Binds
    @IntoMap
    @ViewModelKey(PlayerListViewModel::class)
    abstract fun bindPlayerListViewModel(customViewModel: PlayerListViewModel): ViewModel
}

@Module
abstract class NewsListModule {
    @Binds
    @IntoMap
    @ViewModelKey(NewsListViewModel::class)
    abstract fun bindNewsListViewModel(customViewModel: NewsListViewModel): ViewModel
}

@Module
class TeamIdModule{
    @Named("TeamId")
    @Provides
    fun getId(fragment: TeamFragment): Int =
        TeamFragmentArgs.fromBundle(fragment.requireArguments()).teamId
}
