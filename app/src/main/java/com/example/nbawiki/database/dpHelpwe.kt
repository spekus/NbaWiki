package com.example.nbawiki.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import com.example.nbawiki.database.DataBaseCommands.SQL_CREATE_ENTRIES
import com.example.nbawiki.database.DataBaseCommands.SQL_CREATE_PLAYERS
import com.example.nbawiki.database.DataBaseCommands.SQL_CREATE_TEAMS

object DataBaseCommands {
    const val SQL_DELETE_TEAM_TABLE = "DROP TABLE IF EXISTS ${DataBaseContract.TeamEntry.TABLE_NAME}"
    const val SQL_DELETE_NEWS_TABLE = "DROP TABLE IF EXISTS ${DataBaseContract.NewsEntry.TABLE_NAME}"
    const val SQL_DELETE_PLAYERS_TABLE = "DROP TABLE IF EXISTS ${DataBaseContract.PlayerEntry.TABLE_NAME}"

    const val SQL_CREATE_ENTRIES =
        "CREATE TABLE ${DataBaseContract.TeamEntry.TABLE_NAME} (" +
                "${DataBaseContract.TeamEntry.COLUMN_NAME_TITLE} TEXT," +
                "${DataBaseContract.TeamEntry.COLUMN_NAME_ICON_URL} TEXT," +
                "${DataBaseContract.TeamEntry.COLUMN_NAME_IMAGE_URL} TEXT," +
                "${DataBaseContract.TeamEntry.COLUMN_NAME_EXTERNAL_ID} INTEGER PRIMARY KEY," +
                "${DataBaseContract.TeamEntry.COLUMN_NAME_DESCRIPTION} TEXT)"

    const val SQL_CREATE_TEAMS =
        "CREATE TABLE ${DataBaseContract.NewsEntry.TABLE_NAME} (" +
                "${DataBaseContract.NewsEntry.COLUMN_NAME_DATE} TEXT," +
                "${DataBaseContract.NewsEntry.COLUMN_NAME_ENEMY_TEAM} TEXT," +
                "${DataBaseContract.NewsEntry.COLUMN_NAME_HOME_TEAM} TEXT," +
                "${DataBaseContract.NewsEntry.COLUMN_NAME_TEAM_ID} INTEGER," +
                "${BaseColumns._ID} INTEGER PRIMARY KEY)"

    const val SQL_CREATE_PLAYERS  =
        "CREATE TABLE ${DataBaseContract.PlayerEntry.TABLE_NAME} (" +
                "${DataBaseContract.PlayerEntry.COLUMN_NAME_DESCRIPTION} TEXT," +
                "${DataBaseContract.PlayerEntry.COLUMN_NAME_EXTERNAL_PLAYER_ID} INTEGER PRIMARY KEY," +
                "${DataBaseContract.PlayerEntry.COLUMN_NAME_HEIGHT} TEXT," +
                "${DataBaseContract.PlayerEntry.COLUMN_NAME_IMAGE_URL} TEXT," +
                "${DataBaseContract.PlayerEntry.COLUMN_NAME_SURENAME} TEXT," +
                "${DataBaseContract.PlayerEntry.COLUMN_NAME_NAME} TEXT," +
                "${DataBaseContract.PlayerEntry.COLUMN_NAME_WEIGHT} TEXT," +
                "${DataBaseContract.PlayerEntry.COLUMN_NAME_TEAM_ID} INTEGER)"
}


class FeedReaderDbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
        db.execSQL(SQL_CREATE_TEAMS)
        db.execSQL(SQL_CREATE_PLAYERS)
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(DataBaseCommands.SQL_DELETE_TEAM_TABLE)
        db.execSQL(DataBaseCommands.SQL_DELETE_NEWS_TABLE)
        db.execSQL(DataBaseCommands.SQL_DELETE_PLAYERS_TABLE)
        onCreate(db)
    }
    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }
    companion object {
        const val DATABASE_VERSION = 10
        const val DATABASE_NAME = "FeedReader.db"
    }

}