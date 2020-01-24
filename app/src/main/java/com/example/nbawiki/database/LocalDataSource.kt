package com.example.nbawiki.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.nbawiki.model.database.*

class LocalDataSource(val context: Context) {

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
}