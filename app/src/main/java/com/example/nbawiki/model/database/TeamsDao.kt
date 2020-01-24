package com.example.nbawiki.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TeamsDao {

    @Query("SELECT * FROM TeamDb")
    fun getAllTeams() : List<TeamDb>

    @Query("SELECT * FROM TeamDb WHERE id =:teamId")
    fun getByID(teamId : Int) : TeamDb

    @Query("SELECT TEAM_NAME FROM TeamDb WHERE id =:teamId")
    fun getNameByID(teamId : Int) : String

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg users: TeamDb)
}