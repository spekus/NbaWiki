package com.example.nbawiki.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PlayerDao {

    @Query("SELECT * FROM player")
    fun getAll(): List<PlayerDb>

    @Query("SELECT * FROM player WHERE playerId=:id")
    fun getThePlayer(id : Int): PlayerDb

    @Insert
    fun insertAll(vararg players: PlayerDb)
}