package com.example.nbawiki.dagger.modules

import android.content.Context
import androidx.room.Room
import com.example.nbawiki.dagger.NamesViewModelProviderFactory
import com.example.nbawiki.datasource.database.AppDatabase
import com.example.nbawiki.model.database.dao.PlayerDao
import com.example.nbawiki.repositories.PlayerRepo
import com.example.nbawiki.repositories.interfaces.PlayerRepository
import dagger.Module
import dagger.Provides

@Module
class DataBaseModule(){
//class NamesFragmentModule(private val context: Context){


//
//    @Provides
////    @FragmentScope
////    fun provideViewModelFactory(
////        repo: PlayerRepo
////    ): NamesViewModelProviderFactory {
////        return NamesViewModelProviderFactory(repo)
////    }
//    fun provideViewModelFactory(
//        repo: PlayerRepo
//    ): NamesViewModelProviderFactory {
//        return NamesViewModelProviderFactory(repo)
//    }

    @Provides
//    @FragmentScope
    fun provideNamesRepository(dao: PlayerDao): PlayerRepository = PlayerRepo(dao)


    @Provides
    fun providePlayerDao(database : AppDatabase) : PlayerDao = database.playerDao()

    @Provides
    fun provideDatabase(context: Context) : AppDatabase = Room.databaseBuilder(
        context.applicationContext,
        AppDatabase::class.java,
        "nba_database"
    ).fallbackToDestructiveMigration().build()
//
//    @Provides
////    @FragmentScope
//    fun provideContext(): Context = context
}

//playerRepository = PlayerRepo(playerDao)