package com.example.nbawiki.datasource.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.nbawiki.model.database.dao.NewDao
import com.example.nbawiki.model.database.dao.PlayerDao
import com.example.nbawiki.model.database.dao.TeamsDao
import com.example.nbawiki.model.database.db.NewsDb
import com.example.nbawiki.model.database.db.PlayerDb
import com.example.nbawiki.model.database.db.TeamDb

//class LocalDataSource {

    @Database(entities = arrayOf(TeamDb::class, PlayerDb::class, NewsDb::class), version = 8)
    abstract class AppDatabase : RoomDatabase() {
        abstract fun playerDao(): PlayerDao
        abstract fun teamDao(): TeamsDao
        abstract fun newsDao() : NewDao
    }

    private lateinit var INSTANCE: AppDatabase

    fun getDatabase(context: Context): AppDatabase {
//        synchronized(AppDatabase::class.java) {
//            if (!::INSTANCE.isInitialized) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "nba_database"
                ).fallbackToDestructiveMigration().build()
//            }
//        }
        return INSTANCE
    }
//}