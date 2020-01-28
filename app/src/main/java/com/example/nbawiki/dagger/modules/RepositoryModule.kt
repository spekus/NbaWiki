package com.example.nbawiki.dagger.modules

import android.content.Context
import androidx.room.Room
import com.example.nbawiki.datasource.database.AppDatabase
import com.example.nbawiki.datasource.retrofit.WebService
import com.example.nbawiki.model.database.dao.NewDao
import com.example.nbawiki.model.database.dao.PlayerDao
import com.example.nbawiki.model.database.dao.TeamsDao
import com.example.nbawiki.repositories.PlayerRepo
import com.example.nbawiki.repositories.TeamRepo
import com.example.nbawiki.repositories.TeamsRepo
import com.example.nbawiki.repositories.interfaces.PlayerRepository
import com.example.nbawiki.repositories.interfaces.api.TeamListRepository
import com.example.nbawiki.repositories.interfaces.api.TeamRepository
import com.example.nbawiki.util.TimePreferenceWizard
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class RepositoryModule{

    @Binds
    @Singleton
    abstract fun providePlayerRepository(pl : PlayerRepo): PlayerRepository

    @Binds
    @Singleton
    abstract fun provideNewsRepository(tm : TeamsRepo): TeamListRepository

    @Binds
    @Singleton
    abstract fun provideTeamListRepository(tm :TeamRepo): TeamRepository
}