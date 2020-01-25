package com.example.nbawiki.model.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.nbawiki.model.database.db.NewsDb

@Dao
interface NewDao {
    @Query("SELECT * FROM news")
    fun getAll(): List<NewsDb>

    @Query("SELECT * FROM news WHERE TEAM_ID=:teamId")
    fun getPlayersByTeam(teamId : Int): List<NewsDb>

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    fun insertAll(vararg news: NewsDb)
}