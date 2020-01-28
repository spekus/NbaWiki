package com.example.nbawiki.dagger.modules

import com.example.nbawiki.repositories.PlayerRepo
import com.example.nbawiki.repositories.TeamRepo
import com.example.nbawiki.repositories.TeamsRepo
import com.example.nbawiki.repositories.interfaces.PlayerRepository
import com.example.nbawiki.repositories.interfaces.api.TeamListRepository
import com.example.nbawiki.repositories.interfaces.api.TeamRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule{

    @Binds
    @Singleton
    abstract fun providePlayerRepository(pr : PlayerRepo): PlayerRepository

    @Binds
    @Singleton
    abstract fun provideNewsRepository(tr : TeamsRepo): TeamListRepository

    @Binds
    @Singleton
    abstract fun provideTeamListRepository(tr :TeamRepo): TeamRepository
}