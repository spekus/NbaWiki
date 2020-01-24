package com.example.nbawiki.model.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "player")
data class PlayerDb (
    @PrimaryKey val playerId : Int,
    @ColumnInfo(name = "NAME_DESCRIPTION") val description: String?,
    @ColumnInfo(name = "HEIGHT") val height: String?,
    @ColumnInfo(name = "IMAGE_URL") val imageUrl: String?,
    @ColumnInfo(name = "NAME") val name: String?,
    @ColumnInfo(name = "WEIGHT") val weight: String?,
    @ColumnInfo(name = "AGE") val age: String?,
    @ColumnInfo(name = "TEAM_ID") val teamId: String?
)

//const val SQL_CREATE_PLAYERS  =
//    "CREATE TABLE ${DataBaseContract.PlayerEntry.TABLE_NAME} (" +
//            "${DataBaseContract.PlayerEntry.COLUMN_NAME_DESCRIPTION} TEXT," +
//            "${DataBaseContract.PlayerEntry.COLUMN_NAME_EXTERNAL_PLAYER_ID} INTEGER PRIMARY KEY," +
//            "${DataBaseContract.PlayerEntry.COLUMN_NAME_HEIGHT} TEXT," +
//            "${DataBaseContract.PlayerEntry.COLUMN_NAME_IMAGE_URL} TEXT," +
//            "${DataBaseContract.PlayerEntry.COLUMN_NAME_SURENAME} TEXT," +
//            "${DataBaseContract.PlayerEntry.COLUMN_NAME_NAME} TEXT," +
//            "${DataBaseContract.PlayerEntry.COLUMN_NAME_WEIGHT} TEXT," +
//            "${DataBaseContract.PlayerEntry.COLUMN_NAME_AGE} TEXT," +
//            "${DataBaseContract.PlayerEntry.COLUMN_NAME_TEAM_ID} INTEGER)"