package com.example.nbawiki.dagger.modules

import android.content.Context
import androidx.room.Room
import com.example.nbawiki.datasource.database.AppDatabase
import com.example.nbawiki.model.database.dao.NewDao
import com.example.nbawiki.model.database.dao.PlayerDao
import com.example.nbawiki.model.database.dao.TeamsDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DataBaseModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context) : AppDatabase = Room.databaseBuilder(
        context.applicationContext,
        AppDatabase::class.java,
        "nba_database"
    ).fallbackToDestructiveMigration().build()


    @Provides
    @Singleton
    fun provideTeamsDao(database : AppDatabase) : TeamsDao = database.teamDao()

    @Provides
    @Singleton
    fun provideNewsDao(database : AppDatabase) : NewDao = database.newsDao()

    @Provides
    @Singleton
    fun providePlayerDao(database : AppDatabase) : PlayerDao = database.playerDao()

}