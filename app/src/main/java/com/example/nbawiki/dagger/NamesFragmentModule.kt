package com.example.nbawiki.dagger

import android.content.Context
import androidx.room.Room
import com.example.nbawiki.datasource.database.AppDatabase
import com.example.nbawiki.datasource.retrofit.Network
import com.example.nbawiki.model.database.dao.PlayerDao
import com.example.nbawiki.repositories.PlayerRepo
import dagger.Module
import dagger.Provides

@Module
class NamesFragmentModule(private val context: Context){


    @Provides
//    @FragmentScope
    fun provideViewModelFactory(
        repo: PlayerRepo
    ): NamesViewModelProviderFactory {
        return NamesViewModelProviderFactory(repo)
    }
    @Provides
//    @FragmentScope
    fun provideNamesRepository(dao: PlayerDao): PlayerRepo = PlayerRepo(dao)


    @Provides
    fun providePlayerDao(database : AppDatabase) : PlayerDao = database.playerDao()

    @Provides
    fun provideDatabase(context: Context) : AppDatabase = Room.databaseBuilder(
        context.applicationContext,
        AppDatabase::class.java,
        "nba_database"
    ).fallbackToDestructiveMigration().build()

    @Provides
//    @FragmentScope
    fun provideContext(): Context = context
}

//playerRepository = PlayerRepo(playerDao)